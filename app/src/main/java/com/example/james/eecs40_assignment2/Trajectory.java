package com.example.james.eecs40_assignment2;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.ArrayList;


/**
 * Created by a handsome man on 4/22/2015.
 */
public class Trajectory implements TimeConscious
{
//	private ArrayList<Point2D.Double> points = new ArrayList<>();


	@Override
	public void tick(Canvas canvas)
	{
		// As time ticks , append more points to the trajectory and
		// discard those points that have crossed the entire
		// span of the screen .

		draw(canvas);
	}

	private void draw(Canvas c)
	{


/*		Path path = new Path();
		path.moveTo(); // Move to first point
		for (int i = 1; i < points.size(); ++i)
		{

			path.lineTo(p.x, p.y);
		}
		Paint paint = new Paint();

		// Set paint color , alpha , line width , dashed style , etc .

		c.drawPath(path, paint);
*/	}
}