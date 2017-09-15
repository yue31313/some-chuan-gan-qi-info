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
 * �¶ȴ�����
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
		infoViews();// ��ʼ���ؼ�

	}

	private void infoViews() {

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);// TYPE_TEMPERATURE�ѹ�ʱ��������ʹ��

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
		tv_context.setText("�¶ȴ�����");

	}

	private Sensor mAccelerometer;

	private SensorManager mSensorManager;

	private MyNewAmbientActivity mnaa;

	@Override
	protected void onResume() {

		if (mAccelerometer != null) {
			mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		} else {
			Toast.makeText(getApplicationContext(), "���豸û���¶ȴ�����", 0).show();
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
		tv_context.setText("X���¶�ֵ����"+values[0]+"\nY���¶�ֵ����"+values[1]+"\nZ���¶�ֵ����"+values[2]);

		if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {

			tv_context.setText(event.values[0] + "");
			mnaa.setGetDataForHardware(new GetDataForHardware() {

				@Override
				public float getAmbientData() {

					return event.values[0];
				}
			});
			current = event.values[0];
			Log.e("AmbientActivity", "�¶�ֵ����" + event.values[0] + "");
		}
	}
}