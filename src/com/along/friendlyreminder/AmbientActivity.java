package com.along.friendlyreminder;

import com.along.friendlyreminder.my.MyNewAmbientActivity;
import com.along.friendlyreminder.my.MyNewAmbientActivity.GetDataForHardware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 温度传感器
 * @author Windows
 *
 */
public class AmbientActivity extends Activity implements SensorEventListener {

	private Button btn;

	private TextView tv_context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoViews();// 初始化控件

	}

	private void infoViews() {

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);// TYPE_TEMPERATURE已过时，不建议使用

		mnaa = new MyNewAmbientActivity();

		mnaa.setGetDataForHardware(new GetDataForHardware() {

			@Override
			public float getAmbientData() {

				return 55.0f;
			}
		});

		btn = (Button) findViewById(R.id.btn_sensor);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(AmbientActivity.this, MyNewAmbientActivity.class));
			}
		});
		tv_context = (TextView) findViewById(R.id.tv_context);
		tv_context.setText("温度传感器");

	}

	private Sensor mAccelerometer;

	private SensorManager mSensorManager;

	private MyNewAmbientActivity mnaa;

	@Override
	protected void onResume() {

		if (mAccelerometer != null) {
			mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		} else {
			Toast.makeText(getApplicationContext(), "此设备没有温度传感器", 0).show();
		}

		super.onResume();
	}

	protected void onPause() {

		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

		// Log.d(TAG, accuracy + "--"+sensor.getMaximumRange());
	}

	private float current;

	public void onSensorChanged(final SensorEvent event) {
		float[] values=event.values;
		tv_context.setText("X轴温度值：："+values[0]+"\nY轴温度值：："+values[1]+"\nZ轴温度值：："+values[2]);

		if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {

			tv_context.setText(event.values[0] + "");
			mnaa.setGetDataForHardware(new GetDataForHardware() {

				@Override
				public float getAmbientData() {

					return event.values[0];
				}
			});
			current = event.values[0];
			Log.e("AmbientActivity", "温度值：：" + event.values[0] + "");
		}
	}
}
