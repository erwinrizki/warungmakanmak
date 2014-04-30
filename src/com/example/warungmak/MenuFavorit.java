package com.example.warungmak;



import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MenuFavorit extends Activity {
	String[] makanan = {"Pecel","Sop/Bening","Rames","Kangkung"};
	String[] minuman = {"Es Teh","Es Marimas","Es Milo","Es Susu","Teh Hangat"};
	Spinner spin, spin1;
	Button kirim;
	RadioGroup rg1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_favorit);
		
		spin = (Spinner)findViewById(R.id.spinner1);
		spin1 = (Spinner)findViewById(R.id.spinner2);
		kirim = (Button)findViewById(R.id.button1);
		rg1 = (RadioGroup)findViewById(R.id.kepuasan);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,makanan);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		
		spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String item = arg0.getItemAtPosition(arg2).toString();
				Toast.makeText(arg0.getContext(), item, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,minuman);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(adapter1);
		
		spin1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String item = arg0.getItemAtPosition(arg2).toString();
				Toast.makeText(arg0.getContext(), item, Toast.LENGTH_LONG).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		kirim.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int selectedspin = spin.getSelectedItemPosition();
				int selectedspin1 = spin1.getSelectedItemPosition();
				String makan = spin.getSelectedItem().toString();
				String minum = spin1.getSelectedItem().toString();
				String selection = null;
				
				if(rg1.getCheckedRadioButtonId()!=-1) {
				    int id= rg1.getCheckedRadioButtonId();
				    View radioButton = rg1.findViewById(id);
				    int radioId = rg1.indexOfChild(radioButton);
				    RadioButton btn = (RadioButton) rg1.getChildAt(radioId);
				    selection = (String) btn.getText();
				}
				
				String isi = "Makanan Favorit: " +makan+ " Minuman Favorit: " +minum+ "Puas: " +selection;
				
				if(rg1.getCheckedRadioButtonId()!=-1) {
					sendSMS(isi);
				} else {
					Toast.makeText(getBaseContext(), "Isi Order dan Alamat Dulu", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_favorit, menu);
		return true;
	}
	
	private void sendSMS(String pesan) {
    	String kirim = "Terkirim";
    	String deliver = "Tersampaikan";
    	String nomer = "081915160170";
    	
    	PendingIntent kirimPI = PendingIntent.getBroadcast(this, 0, new Intent(kirim), 0);
    	PendingIntent deliverPI = PendingIntent.getBroadcast(this, 0, new Intent(deliver), 0);
    	
    	registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				// TODO Auto-generated method stub
				switch(getResultCode()) {
					case Activity.RESULT_OK:
						Toast.makeText(getBaseContext(), "Terkirim", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(getBaseContext(), "Order Tersampaikan", Toast.LENGTH_SHORT).show();
						break;
					case Activity.RESULT_CANCELED:
						Toast.makeText(getBaseContext(), "Order Tak Tersampaikan", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		}, new IntentFilter(deliver));
    	SmsManager sms = SmsManager.getDefault();
    	sms.sendTextMessage(nomer, null, pesan, kirimPI, deliverPI);
    }

}
