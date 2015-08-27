package ca.uwaterloo.lab4_202_08;

import java.util.Map;

import ca.uwaterloo.sensortoy.LineGraphView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.TextView;

public class SensorListener implements SensorEventListener {
    private Map<String, View> textViewHashMap;
    LineGraphView line_graph;
    private static int steps = 0;
	private float[] R = new float[9];
	private float[] I = new float[9];
	private float[] currentOrientation = {0, 0, 0};
	private float[] accelerometer = {0, 0, 0};
	private float[] geoMagnetic = {0, 0, 0};
	private static float orientationMemory; 
	private static float northSteps;
	private static float eastSteps = 0; 
	private static float displacement;
	private static float distance; 
	private float[] previousOrientation = {0, 0, 0};
	private float[] filteredOrientation = {0, 0, 0};
	

    public SensorListener(Map<String, View> textViewHashMap, LineGraphView line_graph) {
        this.textViewHashMap = textViewHashMap;
        this.line_graph = line_graph;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    	// On Sensor Changed calling method getLinearAccelerometer to interpret data
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            getLinearAccelerometer(event);
        } else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
        	getMagneticField(event);
        } else if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        	getAccelerometer(event);
        }
    }

	private void getAccelerometer(SensorEvent event) {
		accelerometer = event.values;
		
		if(SensorManager.getRotationMatrix(R, I, accelerometer, geoMagnetic)) {
			SensorManager.getOrientation(R, currentOrientation);	
		}
		
		for(int i = 0; i < 3; i++) {
			if(currentOrientation[i] > filteredOrientation[i]) {
				filteredOrientation[i] += 0.009;
			} else if(currentOrientation[i] < filteredOrientation[i]) {
				filteredOrientation[i] -= 0.009;
			}
			else {
				filteredOrientation[i] = currentOrientation[i];
			}
		}
		
		((TextView) textViewHashMap.get("orientation_x")).setText("orientation_x: " + String.valueOf(Math.toDegrees(currentOrientation[0])+180));
//      ((TextView) textViewHashMap.get("orientation_y")).setText("orientation_y: " + String.valueOf(Math.toDegrees(currentOrientation[1])+180));
		((TextView) textViewHashMap.get("orientation_z")).setText("Filtered orientation_x: " + String.valueOf(Math.toDegrees(filteredOrientation[0])+180));
	}

	private void getMagneticField(SensorEvent event) {
		// TODO Auto-generated method stub
		geoMagnetic = event.values;
		
	}

	private void getLinearAccelerometer(SensorEvent event) {
        float[] values = event.values;
        
       /* // Calculating record values
        rec_acc_x = Math.abs(values[0]) > Math.abs(rec_acc_x)? values[0] : Math.abs(rec_acc_x);
        rec_acc_y = Math.abs(values[1]) > Math.abs(rec_acc_y)? values[1] : Math.abs(rec_acc_y);
        rec_acc_z = Math.abs(values[2]) > Math.abs(rec_acc_z)? values[2] : Math.abs(rec_acc_z);
        
        // Outputting current sensor values
        ((TextView) textViewHashMap.get("acc_x")).setText("X: " + String.valueOf(values[0]));
        ((TextView) textViewHashMap.get("acc_y")).setText("Y: " + String.valueOf(values[1]));
        ((TextView) textViewHashMap.get("acc_z")).setText("Z: " + String.valueOf(values[2]));
        
        // Outputting record sensor values
        ((TextView) textViewHashMap.get("acc_rec_x")).setText("X: " + String.valueOf(rec_acc_x));
        ((TextView) textViewHashMap.get("acc_rec_y")).setText("Y: " + String.valueOf(rec_acc_y));
        ((TextView) textViewHashMap.get("acc_rec_z")).setText("Z: " + String.valueOf(rec_acc_z));
        
        // Displaying points on line graph
        line_graph.addPoint(values);*/
        
        // Determining if step was taken
        if(StepCountingAlgorithm.isStepTaken(values[0], values[1], values[2], filteredOrientation[0])){
        	steps = steps + 1; 
        	distance = steps;  
        	orientationMemory = StepCountingAlgorithm.orientation;
        	northSteps += Math.cos(orientationMemory);
            eastSteps += Math.sin(orientationMemory);
            
            //Finding vector magnitude under assumption each step is 1
            displacement = (float) Math.sqrt(northSteps * northSteps + eastSteps * eastSteps);
        }
        
        ((TextView) textViewHashMap.get("direction_NE")).setText("N: " + northSteps + ", E: " + eastSteps);
        ((TextView) textViewHashMap.get("orientation_y")).setText("RecordedOrientation: " + String.valueOf(Math.toDegrees(orientationMemory) +180));
        
        ((TextView) textViewHashMap.get("displacement")).setText("Displacement: " + String.valueOf(displacement) + " m");
        ((TextView) textViewHashMap.get("steps")).setText(String.valueOf(steps));
        ((TextView) textViewHashMap.get("distance")).setText("Distance: " + String.valueOf(distance) + " m");
    }
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	
	// Method to reset the number of steps taken to 0
	public static void resetStepCounter() {
		steps = 0;
		
		//distance is also reset
		distance = 0;
	}
	
	// Method to reset the record values
	public static void resetRecordValues() {
		 /*rec_acc_x = 0; rec_acc_y = 0; rec_acc_z = 0;*/ orientationMemory = 0;  
	}
	
	//method to reset (zero) current displacement 
	public static void resetCurrentDisplacement() {
		northSteps = 0; eastSteps = 0; displacement = 0;
	}
}
