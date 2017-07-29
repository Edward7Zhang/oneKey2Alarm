package com.Demo.onekey2alarm;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FinstIn extends ActionBarActivity  {
	Button btSignin,btLogin;
	String APPKEY="18330fbc82ee4";
	String APPSECRETE="ac9ba6c116dce49c36b372a54e20ee2e";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_in);
		//初始化sdk
		SMSSDK.initSDK(this, APPKEY, APPSECRETE);
		
		btSignin = (Button) this.findViewById(R.id.bt_signin);
		btLogin = (Button) this.findViewById(R.id.bt_login);
		btSignin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View vs) {
				//注册手机号
				RegisterPage registerPage = new RegisterPage();
				registerPage.setRegisterCallback(new EventHandler() {
				public void afterEvent(int event, int result, Object data) {
				// 解析注册结果
				if (result == SMSSDK.RESULT_COMPLETE) {
					
					@SuppressWarnings("unchecked")
					HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
					String country = (String) phoneMap.get("country");
					String phone = (String) phoneMap.get("phone");
				// 提交用户信息（此方法可以不调用）
				submitUserInfo(country, phone);
				}
			}
		});
				registerPage.show(FinstIn.this);
				Intent  intent = new Intent(FinstIn.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		btLogin.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View vl) {
				Intent intentL = new Intent(FinstIn.this, LogIn.class);
				startActivity(intentL);
				finish();
			}
		});	
	}
	public void submitUserInfo(String country,String phone){
		Random r = new Random();
		String uid = Math.abs(r.nextInt())+"";
		String nickName = "Edward";
		SMSSDK.submitUserInfo(uid, nickName, null, country, phone);
	}
}
