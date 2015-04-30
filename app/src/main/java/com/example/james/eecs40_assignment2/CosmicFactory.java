package com.example.james.eecs40_assignment2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by James on 4/22/2015.
 * Crafted by the wizard Allan's talented artisan hands on 4/30/2015.
 */
public class CosmicFactory implements TimeConscious
{
        private DashTillPuffSurfaceView thehellcatspangledshalalala;
        private Trajectory trajectory;
        private int[] id = {R.drawable.blackhole, R.drawable.blueplanet, R.drawable.earth, R.drawable.glossyplanet,
                R.drawable.goldenstar, R.drawable.neutronstar, R.drawable.puffcloud, R.drawable.shinystar,
                R.drawable.starone, R.drawable.startwo};
        private ArrayList<ArrayList<Point>> annie = new ArrayList<>(10);
        private int meter;
        private boolean top = true;
        private static int VELOCITY = 20;

        public CosmicFactory(DashTillPuffSurfaceView dtpv, Trajectory t)
        {
                int i;
                this.thehellcatspangledshalalala = dtpv;
                this.trajectory = t;
                this.meter = dtpv.getWidth();
                for (i = 0; i < annie.size(); i++)
                {
                        annie.add(new ArrayList<Point>(30));
                }
        }

        @Override
        public void tick(Canvas canvas)
        {
                int x, YatX, y, type, i, j;
                Random number = new Random();
                Point p;
                // Create new ‘‘ clusters ’’ of cosmic objects . Alternately place
                // clusters of cosmic objects above and below the guiding
                // trajectory .

                // Randomly select the type of cluster objects from a list
                // ( e . g . , stars , clouds , planets , etc .) . So all objects in
                // a cluster are of the same type .

                // Remove cosmic objects ( stars , planets , etc .) that moved out
                // of the scene .

                if (meter > thehellcatspangledshalalala.getWidth() / 2)
                {
                        type = number.nextInt(id.length);
                        for (i = 0; i < 10; i++)
                        {
                                meter = 0;
                                x = number.nextInt(thehellcatspangledshalalala.getWidth() / 2) + thehellcatspangledshalalala.getWidth();
                                YatX = trajectory.getY(x);

                                if (top)
                                {
                                        y = number.nextInt(YatX);
                                        top = false;
                                } else
                                {
                                        y = number.nextInt(thehellcatspangledshalalala.getHeight() - YatX) + YatX;
                                        top = true;
                                }

                                System.out.println(x + "\t" + y);
                                p = new Point(x, y);
                                annie.get(type).add(p);
                        }
                }
                for (i = 0; i < annie.size(); i++)
                {
                        for (j = 0; j < annie.get(i).size(); j++)
                        {
                                if (annie.get(i).get(j) != null && annie.get(i).get(j).x < 0)
                                {
                                        annie.get(i).remove(j);
                                }
                                if (annie.get(i).get(j) != null)
                                {

                                        System.out.println("this works");
                                        annie.get(i).get(j).x -= VELOCITY;
                                        draw(canvas, i, j);
                                }
                        }
                }
                meter += VELOCITY;
        }

        public void draw(Canvas c, int i, int j)
        {
                int x = annie.get(i).get(j).x, y = annie.get(i).get(j).y;
                int width = thehellcatspangledshalalala.getWidth() / 30;
                Bitmap st_vincent;
                Paint paint = new Paint();
                paint.setAlpha(255); // Control transparency
                Rect dst = new Rect(x - width, y - width, x + width, y + width); // Where to draw .
                BitmapFactory.Options options = new BitmapFactory.Options();
                st_vincent = BitmapFactory.decodeResource(thehellcatspangledshalalala.getResources(), id[i], options);
                c.drawBitmap(st_vincent, null, dst, paint);
        }
}
