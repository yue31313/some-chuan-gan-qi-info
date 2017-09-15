package com.along.friendlyreminder.speak;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.along.friendlyreminder.R;

/**
 * 语音识别的界面
 * 
 * @author Windows
 * 
 */

public class VoiceRecognitionActivity extends Activity implements OnClickListener {

	private static final int VOICE_RECOGNITIONING_REQUEST_CODE = 1234;

	private Button btn_speek;

	private ListView lv_list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_voice_recognition);
		infoViews();
	}

	private void infoViews() {

		PackageManager pm = getPackageManager();

		List<ResolveInfo> resolves = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

		if (resolves.size() > 0) {
			btn_speek = (Button) findViewById(R.id.btn_speek);
		} else {
			btn_speek.setClickable(false);
			btn_speek.setText("Recognizer not present");
		}
		lv_list = (ListView) findViewById(R.id.lv_list);
		btn_speek.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.btn_speek:
				startVoiceRecognitionActivity();
			break;

			default:
			break;
		}

	}

	private void startVoiceRecognitionActivity() {

		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
		startActivityForResult(intent, VOICE_RECOGNITIONING_REQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == VOICE_RECOGNITIONING_REQUEST_CODE && resultCode == RESULT_OK) {
			// Fill the list view with the strings the recognizer thought it
			// could have heard
			ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			lv_list.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, matches));
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

}
