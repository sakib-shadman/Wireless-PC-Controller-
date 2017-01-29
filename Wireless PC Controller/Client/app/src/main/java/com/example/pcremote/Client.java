package com.example.pcremote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Activity implements View.OnTouchListener {
	private Socket client;
	private Boolean flag = false;
	private PrintWriter printwriter;
	private String message, str,data_from_main;
	TextView mousePad;

	private boolean isConnected=false;
	private boolean mouseMoved=false;


	private float initX =0;
	private float initY =0;
	private float disX =0;
	private float disY =0;

	// EditText command, msg;
	Button leftclick,rightclick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client);


		setTitle("MousePad");
		leftclick=(Button)findViewById(R.id.left_click);
		rightclick=(Button)findViewById(R.id.right_click);
        mousePad=(TextView)findViewById(R.id.mousepad);

		mousePad.setOnTouchListener(this);

		 // MainActivity obj = new MainActivity();
		  str = "192.168.173.1";




	}



	/*protected void onPause() {
		super.onPause();
		finish();
	}*/



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_client, menu);
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
		Intent h=new Intent(Client.this,Help.class);
		startActivity(h);

	}


	public void toast (View v){




		switch (v.getId()) {

			case R.id.left_click:
				message = "MOUSE_LEFT_CLICK";
				break;
			case R.id.right_click:
				message = "MOUSE_RIGHT_CLICK";
				break;


		}





		if (flag == true) {
			Toast.makeText(Client.this, "Connection failed or Invalid IpAddress !!", Toast.LENGTH_LONG).show();
			// msg.setText("Could not Connected or Invalid IpAddress !!");
			flag = false;

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);

			builder.setMessage("Connection failed or Invalid IpAddress !!")
					.setCancelable(false)
					.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							finish();
						}
					});


			AlertDialog alert = builder.create();
			alert.setTitle("AlertDialog");
			alert.show();
			setContentView(R.layout.activity_client);

		}

		SendMessage sendMessageTask = new SendMessage();
		sendMessageTask.execute();

	}



	@Override
	public boolean onTouch(View view, MotionEvent event) {

		switch(event.getAction()){


			case MotionEvent.ACTION_DOWN:
				//save X and Y positions when user touches the TextView
				initX =event.getX();
				initY =event.getY();
				mouseMoved=false;
				break;
			case MotionEvent.ACTION_MOVE:
				disX = event.getX()- initX; //Mouse movement in x direction
				disY = event.getY()- initY; //Mouse movement in y direction
                            /*set init to new position so that continuous mouse movement
                            is captured */
				initX = event.getX();
				initY = event.getY();
				if(disX !=0|| disY !=0){
					//printwriter.write(disX +","+ disY); //send mouse movement to server

					message=disX+","+disY;
				}
				mouseMoved=true;
				break;
			case MotionEvent.ACTION_UP:
				//consider a tap only if usr did not move mouse after ACTION_DOWN
				if(!mouseMoved){
					// out.println(SyncStateContract.Constants.MOUSE_LEFT_CLICK);
					message="MOUSE_LEFT_CLICK";
				}
		}

		SendMessage sendMessageTask = new SendMessage();
		sendMessageTask.execute();
		return true;

	}


	private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				//String str = new String("116.202.91.218");
				client = new Socket(str, 4444); // connect to the server
				printwriter = new PrintWriter(client.getOutputStream(), true);
				printwriter.write(message); // write the message to output stream
				printwriter.flush();
				printwriter.close();
				client.close(); // closing the connection

			} catch (Exception e) {
				flag = true;
			}
			return null;

		}
	}
}

