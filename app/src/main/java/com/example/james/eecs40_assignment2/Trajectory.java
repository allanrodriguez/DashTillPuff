package com.example.james.eecs40_assignment2;

import android.graphics.*;
import java.util.*;


/**
 * Created by a handsome man on 4/22/2015.
 */
public class Trajectory implements TimeConscious
{
	private DashTillPuffSurfaceView view;
	private ArrayList<Point> points = new ArrayList<>();
	private int delta_x;

	public Trajectory(DashTillPuffSurfaceView v)
	{
		this.view = v;
		this.delta_x = 0;
	}

	private int randomY()
	{
		Random r = new Random();
		int y = r.nextInt(view.getHeight() - view.getShip_size()) + view.getShip_size()/2;
		return y;
	}

	public void initTrajectory()
	{
		this.delta_x = view.getWidth()/4;
		Point p00 = new Point(delta_x, view.getHeight() - view.getShip_size()/2);  // hehe
		Point p01 = new Point(2*delta_x, randomY());
		Point p10 = new Point(3*delta_x, randomY());
		Point p11 = new Point(4*delta_x, randomY());
		points.add(p00);
		points.add(p01);
		points.add(p10);
		points.add(p11);
	}

        public int getY(int x)
        {
                int i, y;
                double slope = 0.0;

                for (i = 1; i < points.size(); i++)
                {
                        if (points.get(i - 1) != null && x >= points.get(i - 1).x && points.get(i) != null && x < points.get(i).x)
                        {
                                slope = ((double) (points.get(i).y - points.get(i - 1).y)) / (double) delta_x;
                                break;
                        }
                }

                y = points.get(i - 1).y;
                slope = slope * (x - points.get(i - 1).x) + y;
                if (slope < 0.0)
                {
                        return 0;
                }
                return (int) (slope + 0.5);
        }

	@Override
	public void tick(Canvas canvas)
	{
		// As time ticks , append more points to the trajectory and
		// discard those points that have crossed the entire
		// span of the screen .
		for (int i = 0; i < points.size(); i++)
		{
			if(points.get(i) != null)
			{
				points.get(i).x -= view.getGotta_go_fast();
				if (points.get(i).x < -delta_x)
				{
					points.remove(i);
				}
			}
		}
		if(points.size()<6)
		{
			Point lol = new Point(delta_x + points.get(points.size()-1).x, randomY());
			points.add(lol);
		}
		draw(canvas, view.isTouched_for_the_very_first_time());
	}

	public void draw(Canvas c, boolean touched)
	{
		if(touched)
		{
			Path path = new Path();
			path.moveTo(points.get(0).x, points.get(0).y); // Move to first point
			for (int i = 1; i < points.size(); ++i)
			{
				if (points.get(i) != null)
				{
					path.lineTo(points.get(i).x, points.get(i).y);
				}
			}
			Paint paint = new Paint();
			// Set paint color , alpha , line width , dashed style , etc .
			paint.setAlpha(255); // Control transparency
			paint.setColor(Color.WHITE);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(view.getHeight() / 100);
			paint.setPathEffect(new DashPathEffect(new float[]{10, 50}, 0));
			c.drawPath(path, paint);
		}
	}
}