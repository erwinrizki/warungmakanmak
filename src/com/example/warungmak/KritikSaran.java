package com.example.warungmak;


import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KritikSaran extends Activity {
	Button kirim;
	EditText pesan;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kritik_saran);
		
		kirim = (Button)findViewById(R.id.kirimsms);
		pesan = (EditText)findViewById(R.id.pesan);
		
		kirim.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String pesan1 = pesan.getText().toString();
				String isi = "Komplain: " +pesan1;
				
				if(pesan1.length()>0) {
					savePreference();
					sendSMS(pesan1);
				} else {
					Toast.makeText(getBaseContext(), "Tolong Masukkan Pesan", Toast.LENGTH_SHORT).show();
				}
			}
		});
		loadSavedPreference();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.kritik_saran, menu);
		return true;
	}
	
	private void sendSMS(String pesan) {
    	String kirim = "Komplain Terkirim";
    	String deliver = "Komplain Tersampaikan";
    	String nomer = "081915160170";
    	
    	PendingIntent kirimPI = PendingIntent.getBroadcast(this, 0, new Intent(kirim), 0);
    	PendingIntent deliverPI = PendingIntent.getBroadcast(this, 0, new Intent(deliver), 0);
    	
    	registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				switch(getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "Komplain Terkirim", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
						Toast.makeText(getBaseContext(), "Gagal Bro", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NO_SERVICE:
						Toast.makeText(getBaseContext(), "Tak Ada Service", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_NULL_PDU:
						Toast.makeText(getBaseContext(), "Tak Ada PDU", Toast.LENGTH_SHORT).show();
						break;
					case SmsManager.RESULT_ERROR_RADIO_OFF:
						Toast.makeText(getBaseContext(), "Radio OFF", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		}, new IntentFilter(kirim));
    	
    	registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				switch(getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "Komplain Tersampaikan", Toast.LENGTH_SHORT).show();
						break;
					case Activity.RESULT_CANCELED:
						Toast.makeText(getBaseContext(), "Komplain Tak Tersampaikan", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		}, new IntentFilter(deliver));
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(nomer, null, pesan, kirimPI, deliverPI);
    }
	
	public void savePreference() {
		pesan = (EditText)findViewById(R.id.pesan);
		
		SharedPreferences preference1 = getSharedPreferences("preferenceku", 0);
		SharedPreferences.Editor editor = preference1.edit();
		editor.putString("pesan", pesan.getText().toString());
		
		editor.commit();
		Toast.makeText(getBaseContext(), "Data Tersimpan", Toast.LENGTH_LONG).show();
	}
	
	public void loadSavedPreference() {
		pesan = (EditText)findViewById(R.id.pesan);
		
		try {
			SharedPreferences preference1 = getSharedPreferences("preferenceku", Activity.MODE_PRIVATE);
			pesan.setText(preference1.getString("pesan", null));
		} catch(Exception e) {
			
		}
	}

}
