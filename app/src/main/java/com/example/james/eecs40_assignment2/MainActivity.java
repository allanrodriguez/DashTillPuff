package com.example.james.eecs40_assignment2;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new DashTillPuffSurfaceView(getBaseContext()));
	}
}