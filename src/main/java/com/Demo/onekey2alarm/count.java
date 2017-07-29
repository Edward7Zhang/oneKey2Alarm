package com.Demo.onekey2alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class count extends Activity{
	Button change_count,bt_back_count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.count);
		bt_back_count = (Button) findViewById(R.id.bt_back_count);
		change_count = (Button) findViewById(R.id.bt_change_count);
		change_count.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  intent = new Intent(count.this,FinstIn.class);
				startActivity(intent);
				onDestroy();
				
			}
		});
		bt_back_count.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
	}
}
