package com.example.pcremote;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;

public class Open extends ActionBarActivity {

    Button b;
    EditText e;
    public static String data,ipaddres;
    private Socket client;
    private Boolean flag = false;
    private PrintWriter printwriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        b=(Button)findViewById(R.id.open);
        e=(EditText)findViewById(R.id.et);

        MainActivity obj = new MainActivity();
        ipaddres ="192.168.173.1";
    }


    public void open_file(View v)
    {
        data="y "+e.getText().toString();
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
    }

    public void save_file(View v)
    {
        data = e.getText().toString();
        Intent ii=new Intent(Open.this, Show.class);
        ii.putExtra("name", data);
        startActivity(ii);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.showlist) {
            Intent ii=new Intent(getApplicationContext(),Show.class);
            data=null;
            ii.putExtra("name", data);
            startActivity(ii);
        }

        else if(item.getItemId()==R.id.help)
        {
            Intent h=new Intent(getApplicationContext(),Help.class);
            startActivity(h);
        }

        return super.onOptionsItemSelected(item);
    }



    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //String str = new String("116.202.91.218");
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
