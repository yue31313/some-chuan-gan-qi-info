package com.along.friendlyreminder.heart;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.along.friendlyreminder.R;
/**
 * 这个界面提供一个红色的值和确定的心跳
 * @author Windows
 *
 */

public class HeartRateMonitorActivity extends Activity {

	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_heart_rate);
	    surfaceView = (SurfaceView) findViewById(R.id.preview);
	    surfaceHolder = surfaceView.getHolder();
	    surfaceHolder.addCallback(new SurfaceHolder.Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
			
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
			
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			
				// TODO Auto-generated method stub
				
			}
		});
	    surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	    }
	
}
