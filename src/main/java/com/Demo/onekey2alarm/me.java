package com.Demo.onekey2alarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class me extends Activity implements OnClickListener{
		private Button aid,count,address,me_back;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.me);
			me_back = (Button) findViewById(R.id.bt_back_me);
			aid = (Button) findViewById(R.id.bt_aid);
			count = (Button) findViewById(R.id.bt_count);
			address = (Button) findViewById(R.id.bt_address);
			aid.setOnClickListener(this);
			count.setOnClickListener(this);
			address.setOnClickListener(this);
			me_back.setOnClickListener(this);
		}
		@Override
		public void onClick(View v5) {
			switch (v5.getId()) {
            case R.id.bt_aid:
    			Intent intent = new Intent(me.this, aid.class);
    			startActivity(intent);
    			break;
    		case R.id.bt_count:
    			Intent intent1 = new Intent(me.this, count.class);
    			startActivity(intent1);
    			break;
    		case R.id.bt_address:
    			Intent intent2 = new Intent(me.this, address.class);
    			startActivity(intent2);
    			break;
    		case R.id.bt_back_me:
    			setResult(3);
				finish();
    		}
			
		}
}
