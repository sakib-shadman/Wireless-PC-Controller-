package com.example.pcremote;


        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.Toast;

        import java.io.PrintWriter;
        import java.net.Socket;

public class Media extends Activity {

    public static String data,ipaddres;
    private Socket client;
    private Boolean flag = false;
    private PrintWriter printwriter;
    public  int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

       // MainActivity obj = new MainActivity();
        ipaddres ="192.168.173.1";

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_media, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(item.getItemId())
        {
            case R.id.fl:
                data="m "+"fullscreen";
                break;
            case R.id.mn:
                data="m "+"minimize";
                break;
            case R.id.stp:
                data="m "+"stop";
                break;
            case R.id.help:
                Intent h=new Intent(getApplicationContext(),Help.class);
                startActivity(h);
                break;
            default:
                break;
        }
        SendMessage sendMessageTask = new SendMessage();
        sendMessageTask.execute();
        return super.onOptionsItemSelected(item);
    }

    public void HelpGo(MenuItem item) {
        ///Toast.makeText(getApplicationContext(), "Helppppp", Toast.LENGTH_SHORT).show();
        Intent h=new Intent(Media.this,Help.class);
        startActivity(h);

    }
    public void execute(View view) {
        switch (view.getId())
        {
            case R.id.forward:
                data="RightArrow";
                break;
            case R.id.back:
                data="LeftArrow";
                break;
            case R.id.up:
                data="UpArrow";
                break;
            case R.id.down:
                data="DownArrow";
                break;
            case R.id.play:
            {
                count++;
                data="space";
                if(count%2==1)
                {
                    ImageButton b = (ImageButton) findViewById(R.id.play);
                    b.setBackgroundResource(R.drawable.play);
                }
                else
                {
                    ImageButton b = (ImageButton) findViewById(R.id.play);
                    b.setBackgroundResource(R.drawable.pause);
                }
                break;
            }
        }

        if (flag == true) {
            Context context = getApplicationContext();

            CharSequence text = "Connection failed or Invalid IpAddress !!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //   Toast.makeText("Connection failed or Invalid IpAddress !!", Toast.LENGTH_LONG).show();
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
