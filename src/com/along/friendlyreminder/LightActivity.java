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
 * 光线传感器
 * 
 * 光线传感器的类型常量是Sensor.TYPE_LIGHT。values数组只有第一个元素（values[0]）有意义。表示光线的强度。
 * 最大的值是120000.0f。Android SDK将光线强度分为不同的等级，每一个等级的最大值由一个常量表示，
 * 这些常量都定义在SensorManager类中，代码如下：
 * 
 * public static final float LIGHT_SUNLIGHT_MAX =120000.0f;
 * 
 * public static final float LIGHT_SUNLIGHT=110000.0f;
 * 
 * public static final float LIGHT_SHADE=20000.0f;
 * 
 * public static final float LIGHT_OVERCAST= 10000.0f;
 * 
 * public static final float LIGHT_SUNRISE= 400.0f;
 * 
 * public static final float LIGHT_CLOUDY= 100.0f;
 * 
 * public static final float LIGHT_FULLMOON= 0.25f;
 * 
 * public static final float LIGHT_NO_MOON= 0.001f;
 * 
 * 上面的八个常量只是临界值。读者在实际使用光线传感器时要根据实际情况确定一个范围。
 * 例如，当太阳逐渐升起时，values[0]的值很可能会超过LIGHT_SUNRISE，当values[0]的值逐渐增大时，
 * 就会逐渐越过LIGHT_OVERCAST，而达到LIGHT_SHADE，当然，如果天特别好的话，也可能会达到LIGHT_SUNLIGHT，甚至更高
 * 
 * @author Windows
 * 
 */
public class LightActivity extends Activity implements SensorEventListener {

	private static String TAG = "LightActivity光线传感器";

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
		tv_context.setText("光线感应传感器");
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
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

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

		// Log.d(TAG, accuracy + "--" + sensor.getMaximumRange());
	}

	public void onSensorChanged(SensorEvent event) {

		float[] values = event.values;
		tv_context.setText("X轴光传感器的值：："+values[0]+"\nY光传感器的值：："+values[1]+"\nZ轴光传感器的值：："+values[2]);
		Log.d(TAG, "第一个参数有用，光照强度：" + values[0]);
		if(values[0]<100){
			//提示光线太弱
		}else if(values[0]>320){
			//提示光线太强
		}
	}
}
