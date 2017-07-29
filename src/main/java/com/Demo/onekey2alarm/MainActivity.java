package com.Demo.onekey2alarm;

import java.io.InputStream;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;



import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.LocationSource;



public class MainActivity extends FragmentActivity implements CompoundButton.OnCheckedChangeListener
,AMapLocationListener,LocationSource,OnClickListener{
	
	  	/////////////////////////////////////////
  	private LinearLayout Menu;
	private LinearLayout Map_Located;
	private LinearLayout Me;
	

	private ImageButton mImgMenu;
	private ImageButton mImgMap_Located;
	private ImageButton mImgMe;

	private Fragment Left_Menu;
	private Fragment Map_Locat;
	private Fragment setting;
  	
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initEvent();
		setSelect(2);

	}

	private void initEvent()
	{
		Menu.setOnClickListener(this);
		Map_Located.setOnClickListener(this);
		Me.setOnClickListener(this);
		
	}

	private void initView()
	{
		
		Menu = (LinearLayout) findViewById(R.id.Mmenu);
		Map_Located= (LinearLayout) findViewById(R.id.Mmap_Locat);
		Me = (LinearLayout) findViewById(R.id.Msettings);

		
		mImgMenu = (ImageButton) findViewById(R.id.Mmenu_img);
		mImgMap_Located = (ImageButton) findViewById(R.id.Mmap_Locat_img);
		mImgMe = (ImageButton) findViewById(R.id.Msettings_img);
	}

	private void setSelect(int i)
	{
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragment(transaction);
		// 把图片设置为亮的
		// 设置内容区域
		switch (i)
		{
		case 1:
			if (Left_Menu == null)
			{
				Left_Menu = new menu();
				transaction.add(R.id.id_content, Left_Menu);
			} else
			{
				transaction.show(Left_Menu);
			}
			mImgMenu.setImageResource(R.drawable.menu_press);
			break;
		case 2:
			if (Map_Locat == null)
			{
				Map_Locat = new map_Located();
				transaction.add(R.id.id_content, Map_Locat);
			} else
			{
				transaction.show(Map_Locat);
				
			}
			mImgMap_Located.setImageResource(R.drawable.map_press);
			break;
		case 3:
			if (setting == null)
			{
				setting =  new Setting();
				transaction.add(R.id.id_content, setting);
			} else
			{
				transaction.show(setting);
			}
			mImgMe.setImageResource(R.drawable.me_press);
			break;
		

		default:
			break;
		}

		transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction)
	{
		if (Left_Menu != null)
		{
			transaction.hide(Left_Menu);
		}
		if (Map_Locat != null)
		{
			transaction.hide(Map_Locat);
		}
		if (setting != null)
		{
			transaction.hide(setting);
		}
	}

	@Override
	public void onClick(View v)
	{
		resetImgs();
		switch (v.getId())
		{
		
		case R.id.Mmenu:
			setSelect(1);
			break;
		case R.id.Mmap_Locat:
			setSelect(2);
			break;
		case R.id.Msettings:
			setSelect(3);
			break;

		default:
			break;
		}
	}

	/**
	 * 切换图片至暗色
	 */
	private void resetImgs()
	{
		
		mImgMenu.setImageResource(R.drawable.menu_normal);
		mImgMap_Located.setImageResource(R.drawable.map_normal);
		mImgMe.setImageResource(R.drawable.me_normal);
	}



	@Override
	public void activate(OnLocationChangedListener arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onLocationChanged(AMapLocation arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		
	}
										
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		super.onActivityResult(arg0, arg1, arg2);	
		if(arg1 == 2)
		{
			setSelect(3);
		}
		else if(arg1 == 3)
		{
			setSelect(3);
		}
	}
	
	
      
	}
