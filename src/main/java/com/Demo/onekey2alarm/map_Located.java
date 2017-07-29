package com.Demo.onekey2alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class map_Located extends Fragment implements CompoundButton.OnCheckedChangeListener
,AMapLocationListener,LocationSource{
	private  MapView mapView;
	private  AMap aMap;
	
	  
	//��λ�����ࡣ�����ṩ���ζ�λ��������λ������Χ�������λ����ع���
    private AMapLocationClient aMapLocationClient;
    //������λ�ص�������
    private OnLocationChangedListener listener;
    //��λ��������
    private AMapLocationClientOption aMapLocationClientOption;
    //�˵���ť
   
    private LatLng myLocation = null;
    //��ʶ�������ж��Ƿ�ֻ��ʾһ�ζ�λ��Ϣ���û����¶�λ
    private boolean isFirstLoc = true;
    private UiSettings mUiSettings;
    private boolean isPerview;
    //������ť
  	private Button bt;
  	//messages
  	static String Messages,send_Messages;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.map, container, false); 
		bt = (Button) view.findViewById(R.id.bt_Location);
		

		mapView = (MapView) view.findViewById(R.id.map);

		mapView.onCreate(savedInstanceState);
		
		//��ʼ����λ
		initMap();
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v1) {
				for (int i = 0; i < contacts.listView.getChildCount(); i++) {
					LinearLayout layout = (LinearLayout) contacts.listView.getChildAt(i);
					TextView contacts_num = (TextView) layout.findViewById(R.id.contacts_num);
					String Number = contacts_num.getText().toString();
				
				//Toast.makeText(MainActivity.this, "�ѱ���",1).show();
				Toast.makeText(getActivity(), Messages,1).show();
				 
				send_Messages = messages.msn.getText().toString()+'\n'+"������ȵ�����λ�ã�"+Messages;
				sendMessages(Number,send_Messages);
				}
			}
		});
		return view;
		}
	
	private void sendMessages(String number, String message){
	    String SENT = "sms_sent";
	    String DELIVERED = "sms_delivered";
	    
	    PendingIntent sentPI = PendingIntent.getActivity(getActivity(), 0, new Intent(SENT), 0);
	    PendingIntent deliveredPI = PendingIntent.getActivity(getActivity(), 0, new Intent(DELIVERED), 0);
	    
	    getActivity().registerReceiver(new BroadcastReceiver(){
	 
	            @Override
	            public void onReceive(Context context, Intent intent) {
	                switch(getResultCode())
	                {
	                    case Activity.RESULT_OK:
	                        Log.i("====>", "Activity.RESULT_OK");
	                       Toast.makeText(getActivity(), "���ŷ��ͳɹ�",1).show();
	                        
	                        break;
	                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	                        Log.i("====>", "RESULT_ERROR_GENERIC_FAILURE");
	                        Toast.makeText(getActivity(), "���ŷ���ʧ��",1).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NO_SERVICE:
	                        Log.i("====>", "RESULT_ERROR_NO_SERVICE");
	                        Toast.makeText(getActivity(), "�޷���",1).show();
	                        break;
	                    case SmsManager.RESULT_ERROR_NULL_PDU:
	                        Log.i("====>", "RESULT_ERROR_NULL_PDU");
	                        break;
	                    case SmsManager.RESULT_ERROR_RADIO_OFF:
	                        Log.i("====>", "RESULT_ERROR_RADIO_OFF");
	                        break;
	                }
	            }
	    }, new IntentFilter(SENT));
	    
	    getActivity().registerReceiver(new BroadcastReceiver(){
	        @Override
	        public void onReceive(Context context, Intent intent){
	            switch(getResultCode())
	            {
	                case Activity.RESULT_OK:
	                    Log.i("====>", "RESULT_OK");
	                    break;
	                case Activity.RESULT_CANCELED:
	                    Log.i("=====>", "RESULT_CANCELED");
	                    break;
	            }
	        }
	    }, new IntentFilter(DELIVERED));
	    
	        SmsManager smsm = SmsManager.getDefault();
	        smsm.sendTextMessage(number, null, message, sentPI, deliveredPI);
	}
	  

	/** 
     * ��ʼ��AMap���� 
     */  
	private void initMap() {  
         
            aMap = mapView.getMap();
            mUiSettings = aMap.getUiSettings();  
            
        // �Զ���ϵͳ��λС����  
        MyLocationStyle myLocationStyle = new MyLocationStyle();  
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory  
                .fromResource(R.drawable.transdrawable));// ����С�����ͼ��  
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// ����Բ�εı߿���ɫ  
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// ����Բ�ε������ɫ  
        myLocationStyle.strokeWidth(0f);// ����Բ�εı߿��ϸ
       
        aMap.setMyLocationStyle(myLocationStyle);  
        aMap.setMyLocationRotateAngle(180);  
        aMap.setLocationSource(this);// ���ö�λ����  
        mUiSettings.setMyLocationButtonEnabled(true); // �Ƿ���ʾĬ�ϵĶ�λ��ť  
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);  
        mUiSettings.setTiltGesturesEnabled(false);// ���õ�ͼ�Ƿ������б  
        mUiSettings.setScaleControlsEnabled(true);// ���õ�ͼĬ�ϵı������Ƿ���ʾ  
        mUiSettings.setZoomControlsEnabled(false);  
        
        //���õ�ͼ����
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        //�Զ��嶨λͼ��
        MyLocationStyle locationStyle = new MyLocationStyle();
        locationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.transdrawable));
        //locationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// ����Բ�ε������ɫ  
        locationStyle.strokeColor(Color.RED);
        locationStyle.strokeWidth(5);
        aMap.setMyLocationStyle(locationStyle);
        
        // ���ö�λ����
        aMap.setLocationSource((LocationSource) this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//����Ĭ�϶�λ��ť�Ƿ���ʾ
        aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
        // ���ö�λ������Ϊ��λģʽ���μ���AMap
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        //������
        aMap.getUiSettings().setScaleControlsEnabled(false);
        // ȥ�����Ű�ť
        aMap.getUiSettings().setZoomControlsEnabled(false);
        mUiSettings.setTiltGesturesEnabled(false);// ���õ�ͼ�Ƿ������б  
        // ���ָ����  
        //aMap.getUiSettings().setCompassEnabled(true);
        //��ʼ����λ
	    aMapLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
	    //���ö�λ�ص�����
	    aMapLocationClient.setLocationListener((AMapLocationListener) this);
		
	    //��ʼ����λ����
	    aMapLocationClientOption = new AMapLocationClientOption();
	    //���ö�λģʽΪ�߾���ģʽ��Battery_SavingΪ�͹���ģʽ��Device_Sensors�ǽ��豸ģʽ
	    aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
	    //�����Ƿ񷵻ص�ַ��Ϣ��Ĭ�Ϸ��ص�ַ��Ϣ��
	    aMapLocationClientOption.setNeedAddress(true);
	    //�����Ƿ�ֻ��λһ��,Ĭ��Ϊfalse
	    aMapLocationClientOption.setOnceLocation(false);
	    //�����Ƿ�ǿ��ˢ��WIFI��Ĭ��Ϊǿ��ˢ��
	    aMapLocationClientOption.setWifiActiveScan(true);
	    //�����Ƿ�����ģ��λ��,Ĭ��Ϊfalse��������ģ��λ��
	    aMapLocationClientOption.setMockEnable(false);
	    //���ö�λ���,��λ����,Ĭ��Ϊ2000ms
	    aMapLocationClientOption.setInterval(2000);
	    //����λ�ͻ��˶������ö�λ����
	    aMapLocationClient.setLocationOption(aMapLocationClientOption);
	    //������λ
	    aMapLocationClient.startLocation();	    
	}
	/**
	    * ��λ�ص�����������λ��ɺ���ô˷���
	    * @param aMapLocation
	    */
		@Override
		public void onLocationChanged(AMapLocation aMapLocation) {
			if(listener!=null && aMapLocation!=null) {
	           listener.onLocationChanged(aMapLocation);// ��ʾϵͳС����
	           if (aMapLocation.getErrorCode() == 0) {
	               //��λ�ɹ��ص���Ϣ�����������Ϣ
	               aMapLocation.getLocationType();//��ȡ��ǰ��λ�����Դ�������綨λ����������λ���ͱ�
	               aMapLocation.getLatitude();//��ȡ����
	               aMapLocation.getLongitude();//��ȡγ��;
	               aMapLocation.getAccuracy();//��ȡ������Ϣ
	               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	               Date date = new Date(aMapLocation.getTime());
	               df.format(date);//��λʱ��
	               aMapLocation.getAddress();//��ַ�����option������isNeedAddressΪfalse����û�д˽��
	               aMapLocation.getCountry();//������Ϣ
	               aMapLocation.getProvince();//ʡ��Ϣ
	               aMapLocation.getCity();//������Ϣ
	               aMapLocation.getDistrict();//������Ϣ
	               aMapLocation.getRoad();//�ֵ���Ϣ
	               aMapLocation.getCityCode();//���б���
	               aMapLocation.getAdCode();//��������
	           		} // ��������ñ�־λ����ʱ���϶���ͼʱ�����᲻�Ͻ���ͼ�ƶ�����ǰ��λ��
	           		if (isFirstLoc) {
	        	   
 	               //�������ż���
 	               aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
 	               //����ͼ�ƶ�����λ��
 	               aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(  
 	                       aMapLocation.getLatitude(), aMapLocation.getLongitude())));  
 	               //�����λ��ť �ܹ�����ͼ�������ƶ�����λ��
 	               listener.onLocationChanged(aMapLocation);
 	               //���ͼ��
 	               //aMap.addMarker(getMarkerOptions(aMapLocation));
 	               //��ȡ��λ��Ϣ
 	               StringBuffer buffer = new StringBuffer();
 	               buffer.append(aMapLocation.getCountry() + "," + aMapLocation.getProvince() + "," + aMapLocation.getCity() + "," + aMapLocation.getProvince() + "," + aMapLocation.getDistrict() + "," + aMapLocation.getStreet() + "," + aMapLocation.getStreetNum());
 	               Messages = buffer.toString();
 	               Toast.makeText(getActivity().getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
 	               isFirstLoc = false;
	           	}
 	       } else {
 	           //��ʾ������ϢErrCode�Ǵ����룬errInfo�Ǵ�����Ϣ������������
 	           Log.e("AmapError", "location Error, ErrCode:"
 	                   + aMapLocation.getErrorCode() + ", errInfo:"
 	                   + aMapLocation.getErrorInfo());
 	           Toast.makeText(getActivity().getApplicationContext(), "��λʧ��", Toast.LENGTH_LONG).show();
 	       }
	      
			}
	    

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	   }
	   @Override
	   public void activate(OnLocationChangedListener onLocationChangedListener) {
		    
		   listener = onLocationChangedListener;
	   }
	   @Override
	   public void deactivate() {
		   listener = null;
	   }
	
		
	   	@Override
		public void onDestroy() {
		    super.onDestroy();
		    //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���
		    mapView.onDestroy();
		    //���ٶ�λ�ͻ���
		    if(aMapLocationClient!=null)
		    {
	            aMapLocationClient.onDestroy();
	            aMapLocationClient = null;
	            aMapLocationClientOption = null;
	        }
	    }
		 
		 @Override
		public void onResume() {
		    super.onResume();
		    //��activityִ��onResumeʱִ��mMapView.onResume ()��ʵ�ֵ�ͼ�������ڹ���
		    mapView.onResume();
		    }
		 @Override
		public void onPause() {
		    super.onPause();
		    //��activityִ��onPauseʱִ��mMapView.onPause ()��ʵ�ֵ�ͼ�������ڹ���
		    mapView.onPause();
		    }
		 @Override
		public void onSaveInstanceState(Bundle outState) {
		    super.onSaveInstanceState(outState);
		    //��activityִ��onSaveInstanceStateʱִ��mMapView.onSaveInstanceState (outState)��ʵ�ֵ�ͼ�������ڹ���
		    mapView.onSaveInstanceState(outState);
		  }
		
	
	
}
