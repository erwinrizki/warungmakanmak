package com.example.warungmak;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {
	public static final String[] titles = new String[] {"Daftar Makanan","Daftar Minuman","Pemesanan Makanan","Keluhan Konsumen","Menu Favorit","Gallery","Alamat WM","Help","About"};
	public static final String[] desc = new String[] {"Menu Makanan dan Harga","Menu Minuman dan Harga","Cara Pemesanan Makanan","Kritik dan Saran Konsumen","Voting dan Kepuasan Konsumen","Kumpulan Foto - Foto","Alamat Warung Makan","Bantuan","Tentang Aplikasi"};
	public static final Integer[] images = {R.drawable.makanan,R.drawable.minuman,R.drawable.pesan,R.drawable.kritik,R.drawable.favorit,R.drawable.gallery,R.drawable.map,R.drawable.help,R.drawable.about};
	ListView listview;
	List<RowItem> rowItems;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listmenu);
		
		int i;
		rowItems = new ArrayList<RowItem>();
		for(i=0;i<titles.length;i++) {
			RowItem item = new RowItem(images[i], titles[i], desc[i]);
			rowItems.add(item);
		}
		
		listview = (ListView)findViewById(R.id.list);
		CustomListViewAdapter adapter = new CustomListViewAdapter(this,R.layout.listitem,rowItems);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		if(position == 0) {
			Intent i = new Intent(MainActivity.this,DaftarMakanan.class);
			startActivity(i);
		} else if(position == 1) {
			Intent i = new Intent(MainActivity.this,DaftarMinuman.class);
			startActivity(i);
		} else if(position == 2) {
			Intent i = new Intent(MainActivity.this,PemesananMakanan.class);
			startActivity(i);
		} else if(position == 3) {
			Intent i = new Intent(MainActivity.this,KritikSaran.class);
			startActivity(i);
		} else if(position == 4) {
			Intent i = new Intent(MainActivity.this,MenuFavorit.class);
			startActivity(i);
		} else if(position == 5) {
			Intent i = new Intent(MainActivity.this,Gallery.class);
			startActivity(i);
		} else if(position == 6) {
			Intent i = new Intent(MainActivity.this,Alamat.class);
			startActivity(i);
		} else if(position == 7) {
			Intent i = new Intent(MainActivity.this,Help.class);
			startActivity(i);
		} else if(position == 8) {
			Siji();
		}
	}
	
	public void Siji() {
		String alert1 = "Warung Makan Mak v1";
		String alert2 = "Copyright ERA 2014";
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
		alertDialog.setTitle("Tentang Aplikasi");
		alertDialog.setMessage(alert1+ "\n" +alert2);
		alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Anda Telah Pilih Tombol OK", Toast.LENGTH_SHORT).show();
				
			}
		});
		alertDialog.show();
	}

}
