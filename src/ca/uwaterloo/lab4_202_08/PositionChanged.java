package ca.uwaterloo.lab4_202_08;

import java.util.List;

import android.graphics.PointF;
import mapper.InterceptPoint;
import mapper.LineSegment;
import mapper.MapView;
import mapper.NavigationalMap;
import mapper.PositionListener;

public class PositionChanged implements PositionListener {

	PointF userPoint, originPoint, destPoint, endPoint, firstTurn, secondTurn;
	List<PointF> pathPoints;
	NavigationalMap map;
	List<InterceptPoint> interceptList;
	LineSegment lineSegment;
	
	@Override
	public void originChanged(MapView source, PointF loc) {
		// TODO Auto-generated method stub
		userPoint = originPoint = loc;
		if(map.calculateIntersections(originPoint, destPoint).size() == 0) {
			findPath(source);
		} else {
			pathPoints.clear();
			pathPoints.add(originPoint);
			pathPoints.add(destPoint);
			source.setUserPath(pathPoints);
		}
	}

	@Override
	public void destinationChanged(MapView source, PointF dest) {
		// TODO Auto-generated method stub
		destPoint = dest;
		findPath(source);
	}

	public void findPath(MapView source) {
		pathPoints.clear();
		pathPoints.add(originPoint);
		
		interceptList = map.calculateIntersections(originPoint, destPoint);
		lineSegment = interceptList.get(0).getLine();
		endPoint = lineSegment.end;
		float[] unitVector = lineSegment.findUnitVector();
		firstTurn = new PointF (endPoint.x + unitVector[0], endPoint.y + unitVector[1]);
		secondTurn = new PointF (destPoint.x, firstTurn.y);
		
		pathPoints.add(firstTurn);
		pathPoints.add(secondTurn);
		pathPoints.add(destPoint);
		
		source.setUserPath(pathPoints);
		
	}
}
