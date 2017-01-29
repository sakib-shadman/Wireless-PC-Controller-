package com.example.pcremote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends Activity {

	Button bconnect;

	public static String ipaddres,data;
	private WifiManager wifiManager;
	private boolean j;
	private Socket client;
	private Boolean flag = false;
	private PrintWriter printwriter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ipaddres ="192.168.173.1";
		wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		j = wifiManager.isWifiEnabled();
		//Toast.makeText(getApplicationContext(), "" + j, Toast.LENGTH_SHORT).show();

		if (j == false) {
			Toast.makeText(getApplicationContext(), "Please turn on your wifi", Toast.LENGTH_SHORT).show();
		}

		 /*if (wifiManager.getConnectionInfo() != null) {
			 String ssid = wifiManager.getConnectionInfo().toString();
			 boolean i = wifiManager.isWifiEnabled();

		 } */
		//textField = (EditText) findViewById(R.id.editText1);
		bconnect = (Button) findViewById(R.id.button1);
		// bconnect.setOnClickListener(this);
	}

	public void client(View v) {

		String ssid = wifiManager.getConnectionInfo().getSSID();
		//Toast.makeText(getApplicationContext(), ssid, Toast.LENGTH_SHORT).show();
		//String ss=""
		// if(ssid !="0x")
		{
			//	 j=true;
		}
		//else
		//j=false;
		if (ssid.equalsIgnoreCase( "\"sakibpc\"")) {
			// ip = textField.getText().toString();
			Intent intent = new Intent(this, SecondActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "No active wifi connection", Toast.LENGTH_SHORT).show();
		}


	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}



	public void HelpGo(MenuItem item) {
		///Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
		Intent h=new Intent(MainActivity.this,Help.class);
		startActivity(h);

	}

	public  void disconnect(View v)
	{
                data="disconnect";
		SendMessage sendMessageTask = new SendMessage();
		sendMessageTask.execute();
	}

	private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {

				client = new Socket(ipaddres, 4444); // connect to the server
				printwriter = new PrintWriter(client.getOutputStream(), true);
				printwriter.write(data); // write the message to output stream
				printwriter.flush();
				printwriter.close();
				client.close();

			} catch (Exception e) {
				flag = true;
			}
			return null;

		}
	}

}