package com.Demo.onekey2alarm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class about extends Activity implements OnClickListener{
	private Button about_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		about_back = (Button) findViewById(R.id.bt_back_about);
		about_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		setResult(5);
		finish();
		
	}
}
