package com.along.friendlyreminder;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

/**
 * 陀螺仪传感器
 * 陀螺仪传感器的类型常量是Sensor.TYPE_GYROSCOPE。values数组的三个元素表示的含义如下：values[0]：延X轴旋转的角速度。
 * values[1]：延Y轴旋转的角速度。 values[2]：延Z轴旋转的角速度。
 * 当手机逆时针旋转时，角速度为正值，顺时针旋转时，角速度为负值。陀螺仪传感器经常被用来计算手机已转动的角度， 代码如下：private static
 * final float NS2S = 1.0f / 1000000000.0f;
 * 
 * 
 * 
 * @author Windows
 * 
 */

public class GyroscopeActivity extends Activity implements SensorEventListener {

	public static final String TAG = "GyroscopeActivity陀螺仪传感器";

	private Button btn;

	private TextView tv_context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoViews();// 初始化控件

	}

	private void infoViews() {

		btn = (Button) findViewById(R.id.btn_sensor);
		tv_context = (TextView) findViewById(R.id.tv_context);
		tv_context.setText("陀螺仪传感器");
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
	}

	private Sensor mAccelerometer;

	private SensorManager mSensorManager;

	@Override
	protected void onResume() {

		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		super.onResume();
	}

	protected void onPause() {

		super.onPause();
		mSensorManager.unregisterListener(this);
	}
	//传感器精确度变化时调用 
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

		// Log.d(TAG, accuracy + "--"+sensor.getMaximumRange());
	}

	private float timestamp;

	float[] angle = new float[3];

	private static final float NS2S = 1.0f / 1000000000.0f;

	/*
	 * 代码中通过陀螺仪传感器相邻两次获得数据的时间差（dT）来分别计算在这段时间内手机延X、
	 * Y、Z轴旋转的角度，并将值分别累加到angle数组的不同元素上。
	 */
	//传感器数据变化时调用 
	public void onSensorChanged(SensorEvent event) {
		float[] values=event.values;
		tv_context.setText("X轴旋转的角速度：："+values[0]+"\nY轴旋转的角速度：："+values[1]+"\nZ轴旋转的角速度：："+values[2]);
		if (timestamp != 0) {
			final float dT = (event.timestamp - timestamp) * NS2S;

			angle[0] += event.values[0] * dT;

			angle[1] += event.values[1] * dT;

			angle[2] += event.values[2] * dT;

			Log.d(TAG, "X轴旋转的角速度：" + angle[0] + ":Y轴旋转的角速度:" + angle[1] + ":Z轴旋转的角速度:" + angle[2]);
		}
		timestamp = event.timestamp;// event.timesamp表示当前的时间，单位是纳秒（1百万分之一毫秒）
	}

}
