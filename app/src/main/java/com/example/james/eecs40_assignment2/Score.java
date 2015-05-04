package com.example.james.eecs40_assignment2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by James on 4/30/2015.
 */
public class Score
{
	private DashTillPuffSurfaceView view;
	private int size;
	private int x;
	private int y;
	private int score;
	private int time;

	public Score(DashTillPuffSurfaceView v)
	{
		view = v;
		score = x = y = size = 0;
	}

	public void init()
	{
		x = view.getHeight()/20;
		y = view.getHeight()/15;
		size = view.getHeight()/15;
		score = 0;
	}

	public void tick()
	{
		time += 1;
		if(time%5 == 0)
		{
			score++;
		}
	}

	public void draw(Canvas c, boolean not_touched_yet)
	{
		if(not_touched_yet)
		{
			Paint p = new Paint();
			p.setColor(Color.WHITE);
			p.setStyle(Paint.Style.FILL);
			p.setTextSize(view.getHeight()/10);
			if(score > 0)
			{
				c.drawText("Touch to start\nScore: "+String.valueOf(score), view.getWidth()/3, view.getHeight()/3, p);
			}
			else
			{
				c.drawText("Touch to start", view.getWidth()/3, view.getHeight()/3, p);
			}
		}
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setTextSize(size);
		c.drawText(String.valueOf(score), x, y, paint);
	}
}
