package com.along.friendlyreminder.my;

import com.along.friendlyreminder.R;
import com.along.friendlyreminder.R.id;
import com.along.friendlyreminder.R.layout;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * ���������������ʹ���������д��һ��ָ����
 * @author Windows
 *
 */
public class MyOrientationActivity extends Activity implements SensorEventListener {

	public static final String TAG = "MyOrientationActivity���򴫸���";

	private Button btn;

	private TextView tv_context;

	float[] accelerometerValues = new float[3];

	float[] magneticFieldValues = new float[3];

	float[] values = new float[3];

	float[] rotate = new float[9];

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_orien);
		infoViews();// ��ʼ���ؼ�
	}

	private void infoViews() {

		btn = (Button) findViewById(R.id.btn_sensor);
		tv_context = (TextView) findViewById(R.id.tv_context);
		tv_context.setText("ָ����");
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		aSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		image = (ImageView) findViewById(R.id.main_iv);
	}

	private ImageView image;

	private SensorManager mSensorManager;

	private Sensor aSensor;

	private Sensor mSensor;

	@Override
	protected void onResume() {

		super.onResume();
		mSensorManager.registerListener(this, aSensor, SensorManager.SENSOR_DELAY_GAME);
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onPause() {

		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	float currentDegree = 0f;

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			accelerometerValues = event.values;
		}
		if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
			magneticFieldValues = event.values;
		}
		SensorManager.getRotationMatrix(rotate, null, accelerometerValues, magneticFieldValues);
		SensorManager.getOrientation(rotate, values);
		// ����SensorManager.getOrientation(rotate, values);�õ���valuesֵΪ����
		// ת��Ϊ�Ƕ�
		float degree = (float) Math.toDegrees(values[0]);
		// ������ת����������ת��degree�ȣ�
		RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
		        Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(300);// ���ö����ĳ���ʱ��
		ra.setFillAfter(true);// ���ö�����ת������״̬
		image.startAnimation(ra);
		currentDegree = -degree;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

		// TODO Auto-generated method stub

	}

}