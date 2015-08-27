package ca.uwaterloo.lab4_202_08;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import ca.uwaterloo.sensortoy.LineGraphView;

public class GUI {
	
	private static HashMap<String, View> textViewHashMap = new HashMap<String, View>();
   
	/*private static TextView acc_title, acc_x, acc_y, acc_z;
	private static TextView acc_rec_title, acc_rec_x, acc_rec_y, acc_rec_z;*/
	private static TextView steps_title, steps;
	private static TextView orientation_title, orientation_x, orientation_y, orientation_z;
	private static TextView displacement_title, displacement, direction_NE_title, direction_NE;
	private static TextView distance_title, distance;
	public static TextView test; 
	
	
	public static HashMap<String, View> createAndShowGUI(LinearLayout mainLayout, LineGraphView line_graph, Context context) {
		
		/*mainLayout.addView(line_graph);
		
		// Adding Linear Acceleration Readings
		acc_title = new TextView (context);
		acc_title.setText("---Acceleration (linear) (m/s^2)---");
		acc_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(acc_title);
		
		acc_x = new TextView(context);
		acc_x.setText("X: ");
		textViewHashMap.put("acc_x", acc_x);
		mainLayout.addView(acc_x);
		
		acc_y = new TextView(context);
		acc_y.setText("Y: ");
		textViewHashMap.put("acc_y", acc_y);
		mainLayout.addView(acc_y);
		
		acc_z = new TextView(context);
		acc_z.setText("Z: ");
		textViewHashMap.put("acc_z", acc_z);
		mainLayout.addView(acc_z);
		
		// Adding Linear Acceleration Record Readings
		acc_rec_title = new TextView (context);
		acc_rec_title.setText("---Record Acceleration (linear) (m/s^2)---");
		acc_rec_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(acc_rec_title);
		
		acc_rec_x = new TextView(context);
		acc_rec_x.setText("X: ");
		textViewHashMap.put("acc_rec_x", acc_rec_x);
		mainLayout.addView(acc_rec_x);
		
		acc_rec_y = new TextView(context);
		acc_rec_y.setText("Y: ");
		textViewHashMap.put("acc_rec_y", acc_rec_y);
		mainLayout.addView(acc_rec_y);
		
		acc_rec_z = new TextView(context);
		acc_rec_z.setText("Z: ");
		textViewHashMap.put("acc_rec_z", acc_rec_z);
		mainLayout.addView(acc_rec_z);
		*/
		// Adding Steps Taken
		steps_title = new TextView (context);
		steps_title.setText("Steps Taken: ");
		steps_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(steps_title);
		
		steps = new TextView(context);
		steps.setText("0");
		textViewHashMap.put("steps", steps);
		mainLayout.addView(steps);
		
		// Orientation Taken
		orientation_title = new TextView (context);
		orientation_title.setText("Current Orientation: ");
		orientation_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(orientation_title);
		
		orientation_x = new TextView(context);
		orientation_x.setText("orientation_x: 0");
		textViewHashMap.put("orientation_x", orientation_x);
		mainLayout.addView(orientation_x);
		
		orientation_y = new TextView(context);
		orientation_y.setText("orientation_y: 0");
		textViewHashMap.put("orientation_y", orientation_y);
		mainLayout.addView(orientation_y);
		
		orientation_z = new TextView(context);
		orientation_z.setText("orientation_z: 0");
		textViewHashMap.put("orientation_z", orientation_z);
		mainLayout.addView(orientation_z);
		
		// Steps
		direction_NE_title = new TextView (context);
		direction_NE_title.setText("Current Displacement (in Components): ");
		direction_NE_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(direction_NE_title);
		
		direction_NE = new TextView(context);
		direction_NE.setText("Displacement:  0 N, 0 E");
		textViewHashMap.put("direction_NE", direction_NE);
		mainLayout.addView(direction_NE);
		
		displacement_title = new TextView (context);
		displacement_title.setText("Current Displacement: ");
		displacement_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(displacement_title);
		
		displacement = new TextView(context);
		displacement.setText("Displacement: ");
		textViewHashMap.put("displacement", displacement);
		mainLayout.addView(displacement);
		
		distance_title = new TextView(context);
		distance_title.setText("Distance Travelled: ");
		distance_title.setTypeface(null, Typeface.BOLD);
		mainLayout.addView(distance_title);
		
		distance = new TextView(context);
		distance.setText("Distance: ");
		textViewHashMap.put("distance", distance);
		mainLayout.addView(distance);
		
		test = new TextView(context);
		test.setText("Geometry: ");
		textViewHashMap.put("test", test);
		mainLayout.addView(test);
		
		return textViewHashMap;
	}
	
	public static HashMap<String, View> getTextViewHashMap() {
		return textViewHashMap;
	}
	
}
