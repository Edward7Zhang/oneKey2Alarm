package com.Demo.onekey2alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class StartLoading extends Activity{
	private static final int SHOW_TIME_MIN = 800;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		final View view = View.inflate(this, R.layout.loading, null);
		setContentView(view);
		
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(3000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
			
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				redirectTo();
			}
		});
	}

	protected void redirectTo() {
		// TODO Auto-generated method stub
		Intent  intent = new Intent(StartLoading.this,MainActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
	
