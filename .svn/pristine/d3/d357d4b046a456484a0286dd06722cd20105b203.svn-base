package ca.uwaterloo.lab4_202_08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import mapper.MapLoader;
import mapper.MapView;
import mapper.NavigationalMap;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import ca.uwaterloo.sensortoy.LineGraphView;

public class MainActivity extends ActionBarActivity {
	private HashMap<String, View> textViewHashMap;
	LineGraphView line_graph;
	MapView mapView;
	ScrollView scrollView;
	LinearLayout mainLayout; 
    SensorManager sensorManager;
    List<Sensor> sensor_type_all;
    SensorEventListener sensorEventListener; 
    PositionChanged positionChanged; 
    private static NavigationalMap map;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    
		// Creating mainLayout to hold all components
	    mainLayout = new LinearLayout(this);
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		
		// ScrollView to hold mainLayout to enable scrolling when there is over flow
		scrollView = new ScrollView(this);
		scrollView.addView(mainLayout);
		
		line_graph = new LineGraphView(this, 100, Arrays.asList("x", "y", "z"));
        mapView = new MapView(getApplicationContext(), 1000, 1000, 40, 40);
        mapView.addListener(positionChanged);
		
		//TextViewHashMap holds reference to all necessary TextViews to add information to
		textViewHashMap = GUI.createAndShowGUI(mainLayout, line_graph, this);
		setContentView(scrollView);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		// Storing all necessary sensors as a list 
        sensor_type_all = Arrays.asList(sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), 
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));

        // Registering Listeners
        sensorEventListener = new SensorListener(textViewHashMap, line_graph);
        for (Sensor sensor : sensor_type_all) {
            sensorManager.registerListener(sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
        
	    registerForContextMenu ( mapView );
	    map = MapLoader . loadMap ( getExternalFilesDir(null),
	     		"E2-3344.svg");
	    mapView.setMap (map);
   		mainLayout.addView(mapView);
	}
	
	@Override
	public void onCreateContextMenu ( ContextMenu menu , View v, ContextMenuInfo menuInfo ) {
	super . onCreateContextMenu (menu , v, menuInfo );
	mapView. onCreateContextMenu (menu , v, menuInfo );
	}
	
	@Override
	public boolean onContextItemSelected ( MenuItem item ) {
	return super . onContextItemSelected ( item ) || mapView . onContextItemSelected ( item );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present. 
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.resetRecord) {
			SensorListener.resetRecordValues();
		} else if(id == R.id.resetSteps) {
			SensorListener.resetStepCounter();
		} else if (id == R.id.resetDisplacement) {
			SensorListener.resetCurrentDisplacement();
		}
		return super.onOptionsItemSelected(item);
	}
	
	protected void onResume() {
        super.onResume();
        // register listener
        for (Sensor sensor : sensor_type_all) {
            sensorManager.registerListener(sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
    
    public static NavigationalMap getNavigationalMapInstance() {
    	return map;
    }
}
