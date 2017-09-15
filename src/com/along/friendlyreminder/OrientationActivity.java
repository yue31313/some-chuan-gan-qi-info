package com.along.friendlyreminder;

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
import android.widget.Toast;

/**
 * 方向传感器
 * 
 * 1.1方向传感器
 * 
 * 在方向传感器中values变量的3个值都表示度数，它们的含义如下：
 * 
 * values[0]：该值表示方位，也就是手机绕着Z轴旋转的角度。 0表示北（North）；90表示东（East）；180表示南（South）；
 * 270表示西（West）。如果values[0]的值正好是这4个值，并且手机是水平放置，
 * 表示手机的正前方就是这4个方向。可以利用这个特性来实现电子罗盘，实例76将详细介绍电子罗盘的实现过程。
 * 
 * 
 * values[1]：该值表示倾斜度，或手机翘起的程度。当手机绕着X轴倾斜时该值发生变化。 values[1]的取值范围是-180≤values[1]
 * ≤180。假设将手机屏幕朝上水平放在桌子上，这时如果桌子是完全水平的，
 * values[1]的值应该是0（由于很少有桌子是绝对水平的，因此，该值很可能不为0，但一般都是-5和5之间的某个值）。
 * 这时从手机顶部开始抬起，直到将手机沿X轴旋转180度（屏幕向下水平放在桌面上）。
 * 在这个旋转过程中，values[1]会在0到-180之间变化，也就是说，从手机顶部抬起时，values[1]的值会逐渐变小，直到等于-180。
 * 如果从手机底部开始抬起，直到将手机沿X轴旋转180度，这时values[1]会在0到180之间变化。
 * 也就是values[1]的值会逐渐增大，直到等于180。可以利用values[1]和下面要介绍的values[2]来测量桌子等物体的倾斜度。
 * 
 * 
 * values[2]：表示手机沿着Y轴的滚动角度。取值范围是-90≤values[2]≤90。假设将手机屏幕朝上水平放在桌面上，
 * 这时如果桌面是平的，values[2]的值应为0。将手机左侧逐渐抬起时，values[2]的值逐渐变小，直到手机垂直于桌面放置，
 * 这时values[2]的值是-90。将手机右侧逐渐抬起时，values[2]的值逐渐增大，直到手机垂直于桌面放置，这时values[2]的值是90。
 * 在垂直位置时继续向右或向左滚动，values[2]的值会继续在-90至90之间变化。
 * 
 * @author Windows
 * 
 */

public class OrientationActivity extends Activity implements SensorEventListener {

	public static final String TAG = "OrientationActivity方向传感器";

	private Button btn;

	private TextView tv_context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_orien);
		infoViews();// 初始化控件

	}

	private void infoViews() {

		btn = (Button) findViewById(R.id.btn_sensor);
		tv_context = (TextView) findViewById(R.id.tv_context);
		tv_context.setText("指南针");
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		image = (ImageView) findViewById(R.id.main_iv);
	}

	private Sensor mAccelerometer;

	private SensorManager mSensorManager;

	@Override
	protected void onResume() {

		if (mAccelerometer != null) {
			mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
			Toast.makeText(getApplicationContext(), "此设备有方向传感器", 0).show();
		}else{
			Toast.makeText(getApplicationContext(), "此设备没有方向传感器", 0).show();
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

	// 记录指南针图片转过的角度
	private float currentDegree = 0f;

	private ImageView image;

	public void onSensorChanged(SensorEvent event) {

		// 如果真机上触发event的传感器类型为水平传感器类型
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			// 获取绕Z轴旋转的角度
			float degree = event.values[0];
			// 创建旋转动画（反向转过degree度）
			RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
			        Animation.RELATIVE_TO_SELF, 0.5f);
			// 设置动画的持续时间
			ra.setDuration(200);
			// 设置动画结束后的保留状态
			ra.setFillAfter(true);
			// 启动动画
			image.startAnimation(ra);
			currentDegree = -degree;
		}

	}
}
