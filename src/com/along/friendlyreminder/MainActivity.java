package com.along.friendlyreminder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.along.friendlyreminder.my.MyNewAmbientActivity;
import com.along.friendlyreminder.my.MyOrientationActivity;

public class MainActivity extends ListActivity {

	private SensorManager sensorManager;

	// private List<Sensor> sensors;

	Activity[] activitys = new Activity[] { new AccelerometerActivity(), new AmbientActivity(), new GravityActivity(),
	        new GyroscopeActivity(), new LightActivity(), new LinearActivity(), new MagneticFieldActivity(),
	        new OrientationActivity(), new PressureActivity(), new ProximityActivity(), new RotationActivity(),
	        new MyOrientationActivity()};//,new MyNewAmbientActivity()

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

		setListAdapter(new SimpleAdapter(this, getListValues(), android.R.layout.simple_expandable_list_item_2,
		        new String[] { "NAME", "DESC" }, new int[] { android.R.id.text1, android.R.id.text2 }));

		// 获取各种感应的控件
		// getSensorSevice();
	}

	private List<Map<String, String>> getListValues() {

		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		int length = activitys.length;
		for (int i = 0; i < length; i++) {
			Map<String, String> v = new HashMap<String, String>();
			if (i < length - 2) {
				v.put("NAME", activitys[i].getClass().getSimpleName());
				v.put("DESC", activitys[i].getClass().getPackage().getName());
				values.add(v);
			} else if(i==length-1){
				v.put("NAME", activitys[i].getClass().getSimpleName());
				v.put("DESC", "专业的手机指南针");
				values.add(v);
			}
//			else {
//				v.put("NAME", activitys[i].getClass().getSimpleName());
//				v.put("DESC", "温度计");
//				values.add(v);
//			}
		}
		return values;
	}

	private void getSensorSevice() {

		Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);// 1加速度传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);// 2磁力传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);// 3方向传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);// 4陀螺仪传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);// 5光线感应
		sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);// 6压力传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);// 7温度传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);// 8接近，近距离传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);// 9重力传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);// 10线性加速度传感器
		sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);// 11旋转矢量传感器

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onListItemClick(ListView listView, View view, int position, long id) {

		Intent intent = new Intent(MainActivity.this, activitys[position].getClass());
		startActivity(intent);
		super.onListItemClick(listView, view, position, id);
	}

}
