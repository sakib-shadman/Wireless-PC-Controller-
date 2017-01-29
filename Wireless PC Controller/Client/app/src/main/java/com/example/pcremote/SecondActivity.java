package com.example.pcremote;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SecondActivity extends Activity {

    ListView list;
    String[] itemname ={
            "Mouse",
            "Keyboard",
            "Media",
            "Presenter",
            "Essentials",
            "Open Files"

    };

    Integer[] imgid={

            R.drawable.mouse,
            R.drawable.keyboard,
            R.drawable.media,
            R.drawable.slide,
            R.drawable.essential,
            R.drawable.file,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

         setTitle("Menus");
       CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                if(position==0)
                {
                    Intent intent=new Intent(getApplicationContext(),Client.class);
                    startActivity(intent);
                }
                else if(position==1)
                {
                    Intent intent=new Intent(getApplicationContext(),Keyboard.class);
                    startActivity(intent);
                }
                else if(position==4)
                {
                    Intent intent=new Intent(getApplicationContext(),Power.class);
                    startActivity(intent);
                }

                else if(position==2)
                {
                    Intent intent=new Intent(getApplicationContext(),Media.class);
                    startActivity(intent);
                }

                else if(position==3)
                {
                    Intent intent=new Intent(getApplicationContext(),Presenter.class);

                    startActivity(intent);
                }

                else if(position==5)
                {
                    Intent intent=new Intent(getApplicationContext(),Open.class);

                    startActivity(intent);
                }



            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
        Intent h=new Intent(SecondActivity.this,Help.class);
        startActivity(h);

    }
}
