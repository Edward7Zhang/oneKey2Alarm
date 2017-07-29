package com.Demo.onekey2alarm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Setting extends Fragment implements OnClickListener{
	private Button bt_c,bt_m,bt_myself,bt_set,bt_about;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.setting, container, false);  
		bt_c = (Button) view.findViewById(R.id.bt_contacts);
		bt_m = (Button) view.findViewById(R.id.bt_messages);
		bt_myself = (Button) view.findViewById(R.id.bt_myself);
		bt_set = (Button) view.findViewById(R.id.bt_set);
		bt_about = (Button) view.findViewById(R.id.bt_about);
		bt_c.setOnClickListener(this);
		bt_m.setOnClickListener(this);
		bt_myself.setOnClickListener(this);
		bt_set.setOnClickListener(this);
		bt_about.setOnClickListener(this);
        return view; 
	}
        @Override
    	public void onClick(View v4) {
    		// TODO Auto-generated method stub
    		switch (v4.getId()) {
            case R.id.bt_contacts:
    			Intent intent = new Intent(Setting.this.getActivity(), contacts.class);
    			startActivityForResult(intent, 1);
    			break;
    		case R.id.bt_messages:
    			Intent intent1 = new Intent(Setting.this.getActivity(), messages.class);
    			startActivityForResult(intent1, 2);
    			break;
    		case R.id.bt_myself:
    			Intent intent2 = new Intent(Setting.this.getActivity(), me.class);
    			startActivityForResult(intent2, 3);
    			break;
    		case R.id.bt_set:
    			Intent intent3 = new Intent(Setting.this.getActivity(), set.class);
    			startActivityForResult(intent3, 4);
    			break;
    		case R.id.bt_about:
    			Intent intent4 = new Intent(Setting.this.getActivity(), about.class);
    			startActivityForResult(intent4, 5);
    			break;
    		}
    		
	}
}
