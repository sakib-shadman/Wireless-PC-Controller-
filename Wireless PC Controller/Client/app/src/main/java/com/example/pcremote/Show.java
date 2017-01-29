package com.example.pcremote;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Show extends ListActivity implements AdapterView.OnItemLongClickListener{

    ListView lv;
    String data,item,ipaddres;
    private FileOperations filedboperation;
    private file stud;

    private Socket client;
    private Boolean flag = false;
    private PrintWriter printwriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
             data =(String) b.get("name");

        }

        filedboperation = new FileOperations(this);
        filedboperation.open();

        List values = filedboperation.getAllFiles();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);


         adapter = (ArrayAdapter) getListAdapter();

        //EditText text = (EditText) findViewById(R.id.editText1);
        if(data!=null)
        {
            stud = filedboperation.addFile(data);
            adapter.add(stud);
        }

        ListView lv = getListView();

        lv.setLongClickable(true);
        lv.setOnItemLongClickListener(this);

        //MainActivity obj = new MainActivity();
        ipaddres ="192.168.173.1";


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show, menu);
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

  /*  public void addUser(View view) {

        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

        //EditText text = (EditText) findViewById(R.id.editText1);
        file stud = filedboperation.addStudent(data);

        adapter.add(stud);


    } */

 /*   public void deleteFirstUser(View view) {

        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        file stud = null;

        if (getListAdapter().getCount() > 0) {
            stud = (file) getListAdapter().getItem(0);
           filedboperation.deleteStudent(stud);
            adapter.remove(stud);
        }

    } */

    @Override
    protected void onResume() {
       filedboperation.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
       filedboperation.close();
        super.onPause();
    }
    @Override
    protected void onListItemClick(ListView l, View v, final int position, long id) {


         item = l.getItemAtPosition(position).toString();
         item="y "+item;
        //String item = (String) getListAdapter().getItem(0);
        //Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();

        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
        file stud = null;

        if (getListAdapter().getCount() > 0) {
            stud = (file) getListAdapter().getItem(i);
           filedboperation.deleteFile(stud);
            adapter.remove(stud);
        }
        return true;
    }

    private class SendMessage extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //String str = new String("116.202.91.218");
                client = new Socket(ipaddres, 4444); // connect to the server
                printwriter = new PrintWriter(client.getOutputStream(), true);
                printwriter.write(item); // write the message to output stream
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
