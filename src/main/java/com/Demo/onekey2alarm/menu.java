package com.Demo.onekey2alarm;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class menu extends Fragment implements OnClickListener{
	 private Button bt_110,bt_120,bt_122,bt_119,bt_SP;
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.left_menu, container, false); 
		
		
		bt_120 = (Button) view.findViewById(R.id.bt_120);
		bt_122 = (Button) view.findViewById(R.id.bt_122);
		bt_119 = (Button) view.findViewById(R.id.bt_119);
		bt_110 = (Button) view.findViewById(R.id.bt_110);
		bt_SP = (Button) view.findViewById(R.id.bt_schoolpolice);
		
		bt_120.setOnClickListener(this);
		bt_122.setOnClickListener(this);
		bt_119.setOnClickListener(this);
		bt_110.setOnClickListener(this);
		bt_SP.setOnClickListener(this);
		return view;
		}
	@Override
	public void onClick(View v3) {
		// TODO Auto-generated method stub
		switch (v3.getId()) {
		
		case R.id.bt_110:
			Intent intent_110 = new Intent();
			intent_110.setAction(Intent.ACTION_VIEW);
			Uri uri_110 = Uri.parse("tel:110");
			intent_110.setData(uri_110);
			startActivity(intent_110);
			break;
		case R.id.bt_119:
			Intent intent_119 =new Intent();
			intent_119.setAction(Intent.ACTION_VIEW);
			Uri uri_119 = Uri.parse("tel:119");
			intent_119.setData(uri_119);
			startActivity(intent_119);
			break;
		case R.id.bt_122:
			Intent intent_122 = new Intent();
			intent_122.setAction(Intent.ACTION_VIEW);
			Uri uri_122 = Uri.parse("tel:112");
			intent_122.setData(uri_122);
			startActivity(intent_122);
			break;
		case R.id.bt_120:
			Intent intent_120 = new Intent();
			intent_120.setAction(Intent.ACTION_VIEW);
			Uri uri_120 = Uri.parse("tel:120");
			intent_120.setData(uri_120);
			startActivity(intent_120);
			break;
		case R.id.bt_schoolpolice:
			
			Intent intent_SP = new Intent();
			intent_SP.setAction(Intent.ACTION_VIEW);
			Uri uri_SP = Uri.parse("tel:59750110");
			intent_SP.setData(uri_SP);
			startActivity(intent_SP);
			break;
		
		}
	}
}