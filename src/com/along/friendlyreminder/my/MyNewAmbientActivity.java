package com.along.friendlyreminder.my;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.along.friendlyreminder.R;

public class MyNewAmbientActivity extends Activity implements Callback {

	private SurfaceView surface;

	private SurfaceHolder mHolder;

	private EditText et_tem;

	private Button btn_draw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_ambient);
		infoViews();// 初始化view
	}

	private void infoViews() {

		surface = (SurfaceView) findViewById(R.id.surface);
		et_tem = (EditText) findViewById(R.id.et_tem);
		btn_draw = (Button) findViewById(R.id.btn_draw);
		mHolder = surface.getHolder();
		mHolder.addCallback(this);

		btn_draw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				draw(tem);

			}
		});
	}

	private void draw(float tem) {

		int y = 380 - (int) (tem - 35);

		Canvas canvas = mHolder.lockCanvas();// 设置画布
		Paint mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		canvas.drawRect(60, 160, 80, 400, mPaint);
		Paint paintCircle = new Paint();
		paintCircle.setColor(Color.RED);
		Paint paintLine = new Paint();
		paintLine.setColor(Color.WHITE);
		canvas.drawRect(60, 380 - y * 2, 80, 400, paintCircle);
		canvas.drawCircle(70, 420, 25, paintCircle);
		int ydegree = 380;
		int temInt = 35;
		while (ydegree > 55) {
			canvas.drawLine(80, ydegree, 90, ydegree, paintLine);
			if (ydegree % 20 == 0) {
				canvas.drawLine(80, ydegree, 95, ydegree, paintLine);
				canvas.drawText(temInt + "", 95, ydegree + 4, paintLine);
				temInt = temInt + 2;
			}
			ydegree = ydegree - 2;

		}
		mHolder.unlockCanvasAndPost(canvas);// 更新屏幕显示内容

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		// TODO Auto-generated method stub

	}

	public interface GetDataForHardware {

		public float getAmbientData();
	}

	public GetDataForHardware gdfh;

	private float tem;

	public void setGetDataForHardware(GetDataForHardware gdfh) {

		Log.e("执行setGetDataForHardware", (gdfh == null) + "");
		this.gdfh = gdfh;
		Log.e("执行setGetDataForHardware", (this.gdfh == null) + "");
		Log.e("测试空数据", (this.gdfh == null) + "");

		tem = this.gdfh.getAmbientData();
		Log.e("测试", tem + "");
	}

}
