package com.example.pcremote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Power extends Activity {

    //Button shutdown, sleep, restart, lock, hibernet;
    public static String data,ipaddres;
    private Socket client;
    private Boolean flag = false;
    private PrintWriter printwriter;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);

        setTitle("Essentials");
        MainActivity obj = new MainActivity();
        ipaddres ="192.168.173.1";



      /*  shutdown = (Button) findViewById(R.id.shutdown_button);
        sleep = (Button) findViewById(R.id.sleep_button);
        lock = (Button) findViewById(R.id.lock_button);
        restart = (Button) findViewById(R.id.restart_button);
        hibernet = (Button) findViewById(R.id.hibernet_button); */


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        //  expListView.setOnChildClickListener();
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if(groupPosition==0)
                {
                    if(childPosition==0)
                    {
                       data="y"+"shutdown.exe /s /t 00";
                    }
                    else  if(childPosition==1)
                    {
                        data = "y"+"shutdown.exe /r /t 00";
                    }
                    else  if(childPosition==2)
                    {
                        data = "y"+"rundll32.exe PowrProf.dll,SetSuspendState";
                    }
                    else  if(childPosition==3)
                    {
                        data ="y"+ "rundll32.exe powrprof.dll,SetSuspendState 0,1,0";
                    }
                    else  if(childPosition==4)
                    {
                        data = "y"+"Rundll32.exe User32.dll,LockWorkStation";
                    }


                }

                else if(groupPosition==1)
                {
                    if(childPosition==0)
                    {
                        data="up";
                    }
                    else  if(childPosition==1)
                    {
                       data="down";
                    }
                    else  if(childPosition==2)
                    {
                        data="UpArrow";
                    }
                    else  if(childPosition==3)
                    {
                        data="DownArrow";
                    }
                    else  if(childPosition==4)
                    {
                        data="LeftArrow";
                    }
                    else  if(childPosition==5)
                    {
                        data="RightArrow";
                    }

                }


              /*  if (flag == true) {
                    Toast.makeText(Power.this, "Connection failed or Invalid IpAddress !!", Toast.LENGTH_LONG).show();
                    // msg.setText("Could not Connected or Invalid IpAddress !!");
                    flag = false;

                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
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

                }  */


                SendMessage sendMessageTask = new SendMessage();
                sendMessageTask.execute();


                return false;
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_power, menu);
        return true;
    }


    /*protected void onPause() {
        super.onPause();
        finish();
    }*/


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
        Intent h=new Intent(Power.this,Help.class);
        startActivity(h);

    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding group
        listDataHeader.add("Power Options");
        listDataHeader.add("Others");


        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("ShutDown");
        top250.add("Restart");
        top250.add("Hibernet");
        top250.add("Sleep");
        top250.add("Lock");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Volume Up");
        nowShowing.add("Volume Down");
        nowShowing.add("Up Arrow");
        nowShowing.add("Down Arrow");
        nowShowing.add("Left Arrow");
        nowShowing.add("Right Arrow");



        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);

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
                client.close(); // closing the connection

            } catch (Exception e) {
                flag = true;
            }
            return null;

        }
    }
}