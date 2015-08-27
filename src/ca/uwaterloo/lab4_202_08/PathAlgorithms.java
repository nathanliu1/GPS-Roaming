package ca.uwaterloo.lab4_202_08;

import java.util.HashMap;
import java.util.List;

import mapper.LineSegment;
import mapper.MapView;
import mapper.NavigationalMap;
import android.graphics.PointF;
import android.util.Log;
import android.view.View;

public abstract class PathAlgorithms {
	private static PointF origin = null;
	private static PointF currentPosition = null;
	private static PointF destination = null; 
	private static NavigationalMap map = null;
	
	public static void findPathPoints(MapView source) {
		origin = source.getOriginPoint();
		currentPosition = source.getUserPoint();
		destination = source.getDestinationPoint();
		map = MainActivity.getNavigationalMapInstance();
		
		if(origin != null && destination != null && currentPosition != null) {
			
		}
		
		
	}
	
	public static boolean isPositionValid(PointF loc) {
		
		if(!isOutsideMap(loc) && !isInsideObstacle(loc)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isInsideObstacle(PointF loc) {
		// TODO Auto-generated method stub
		map = MainActivity.getNavigationalMapInstance();
		
		return false;
	}

	public static boolean isOutsideMap(PointF loc) {
		// TODO Auto-generated method stub
		
		String testString = "";
		map = MainActivity.getNavigationalMapInstance();
		List<LineSegment> test = map.getGeometryAtPoint(loc);
		
		Log.d("random", "random");
		return false;
	}

}
