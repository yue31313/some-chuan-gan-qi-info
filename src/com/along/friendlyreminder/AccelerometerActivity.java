package com.along.friendlyreminder;

import com.along.friendlyreminder.speak.VoiceRecognitionActivity;

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
 * 加速度传感器
 * onSensorChanged方法只有一个SensorEvent类型的参数event，其中SensorEvent类有一个values变量非常重要，
 * 该变量的类型是float[]。但该变量最多只有3个元素，而且根据传感器的不同，values变量中元素所代表的含义也不同。
 * 该传感器的values变量的3个元素值分别表示X、Y、Z轴的加速值。例如，水平放在桌面上的手机从左侧向右侧移动，
 * values[0]为负值；从右向左移动，values[0]为正值。读者可以通过本节的例子来体会加速传感器中的值的变化。
 * 要想使用相应的传感器，仅实现SensorEventListener接口是不够的，还需要使用下面的代码来注册相应的传感器
 * 
 * 
 * @author Windows
 * 
 */
public class AccelerometerActivity extends Activity implements SensorEventListener {

	private static String TAG = "AccelerometerActivity";

	private Button btn;

	private TextView tv_context;

	private Sensor mAccelerometer;

	private SensorManager mSensorManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		infoViews();// 初始化控件

	}

	private void infoViews() {

		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// getDefaultSensor方法的参数值，
		// 例如，注册加速传感器可以使用Sensor.TYPE_ACCELEROMETER。在Sensor类中还定义了很多传感器常量，
		// 但要根据手机中实际的硬件配置来注册传感器。如果手机中没有相应的传感器硬件，就算注册了相应的传感器也不起任何作用。
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
		btn = (Button) findViewById(R.id.btn_sensor);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				startActivity(new Intent(AccelerometerActivity.this,VoiceRecognitionActivity.class));
				
			}
		});
		tv_context = (TextView) findViewById(R.id.tv_context);
		tv_context.setText("加速度传感器");
	}

	@Override
	protected void onResume() {

		// registerListener方法的第1个参数值，
		// 例如，注册加速传感器可以使用Sensor.TYPE_ACCELEROMETER。在Sensor类中还定义了很多传感器常量，
		// 但要根据手机中实际的硬件配置来注册传感器。如果手机中没有相应的传感器硬件，就算注册了相应的传感器也不起任何作用。
		// registerListener方法的第2个参数表示获得传感器数据的速度。
		// SensorManager.SENSOR_DELAY_ FASTEST表示尽可能快地获得传感器数据。
		// 除了该值以外，还可以设置3个获得传感器数据的速度值，这些值如下：
		//
		// SensorManager.SENSOR_DELAY_NORMAL：默认的获得传感器数据的速度。
		// SensorManager.SENSOR_DELAY_GAME：如果利用传感器开发游戏，建议使用该值。
		// SensorManager.SENSOR_DELAY_UI：如果使用传感器更新UI中的数据，建议使用该值。
		if(mAccelerometer!=null){
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		}else{
			Toast.makeText(getApplicationContext(), "此设备没有加速度传感器", 0).show();
		}
		super.onResume();
	}

	protected void onPause() {

		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	public void onSensorChanged(SensorEvent event) {
		float[] values=event.values;
		tv_context.setText("X轴加速度的值：："+values[0]+"\nY轴加速度的值：："+values[1]+"\nZ轴加速度的值：："+values[2]);
	}

}
