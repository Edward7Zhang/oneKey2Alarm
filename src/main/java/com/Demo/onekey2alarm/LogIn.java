package com.Demo.onekey2alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LogIn extends Activity{
	Button bt_login_yes,bt_back_first;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.log_in);
		bt_login_yes = (Button) findViewById(R.id.bt_login_yes);
		bt_back_first = (Button) findViewById(R.id.bt_back_first);
		bt_back_first.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent  intentF = new Intent(LogIn.this,FinstIn.class);
				startActivity(intentF);
				finish();
			}
		});
		bt_login_yes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent  intent = new Intent(LogIn.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		});
	}
}
