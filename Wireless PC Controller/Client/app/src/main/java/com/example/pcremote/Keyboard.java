package com.example.pcremote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.PrintWriter;
import java.net.Socket;

public class Keyboard extends Activity  {


    String value, message, str1;
    private Socket client;
    private Boolean flag = false;
    private PrintWriter printwriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard);




       // MainActivity obj = new MainActivity();
        str1 = "192.168.173.1";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_keyboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch(item.getItemId())
       {
           case R.id.save_data:
               message="k "+"save";
               break;
           case R.id.clear:
               message="k "+"clear";
               break;
           case R.id.close:
               message="k "+"close";
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
        Intent h=new Intent(Keyboard.this,Help.class);
        startActivity(h);

    }

    public  void write(View v) {
        switch (v.getId()) {

            case R.id.button1:
                message = "k "+"q";
                break;
            case R.id.button2:
                message = "k "+"w";
                break;
            case R.id.button3:
                message = "k "+"e";
                break;
            case R.id.button4:
                message = "k "+"r";
                break;
            case R.id.button5:
                message = "k "+"t";
                break;
            case R.id.button6:
                message = "k "+"y";
                break;
            case R.id.button7:
                message = "k "+"u";
                break;
            case R.id.button8:
                message = "k "+"i";
                break;
            case R.id.button9:
                message = "k "+"o";
                break;
            case R.id.button10:
                message = "k "+"p";
                break;
            case R.id.button11:
                message = "k "+"a";
                break;
            case R.id.button12:
                message = "k "+"s";
                break;

            case R.id.button13:
                message = "k "+"d";
                break;
            case R.id.button14:
                message = "k "+"f";
                break;
            case R.id.button15:
                message = "k "+"g";
                break;

            case R.id.button16:
                message = "k "+"h";
                break;
            case R.id.button17:
                message = "k "+"j";
                break;
            case R.id.button18:
                message = "k "+"k";
                break;
            case R.id.button19:
                message = "k "+"l";
                break;
            case R.id.button20:
                message = "k "+"z";
                break;
            case R.id.button21:
                message = "k "+"x";
                break;

            case R.id.button22:
                message = "k "+"CAPS_LOCK";
                break;
            case R.id.button23:
                message = "k "+"c";
                break;
            case R.id.button24:
                message = "k "+"v";
                break;

            case R.id.button25:
                message = "k "+"b";
                break;
            case R.id.button26:
                message = "k "+"n";
                break;
            case R.id.button27:
                message = "k "+"m";
                break;

            case R.id.button28:
                message = "k "+"ENTER";
                break;
            case R.id.button55:
                message = "k "+"TAB";
                break;
            case R.id.button56:
                message = "k "+"LEFT";
                break;

            case R.id.button59:
                message = "k "+"SPACE";
                break;
            case R.id.button58:
                message = "k "+"RIGHT";
                break;
            case R.id.button57:
                message = "k "+"BACK_SPACE";
                break;


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


    public  void numeric(View v) {

        switch (v.getId()) {

            case R.id.button31:
                message = "k "+"0";
                break;
            case R.id.button32:
                message = "k "+"1";
                break;
            case R.id.button33:
                message = "k "+"2";
                break;
            case R.id.button34:
                message = "k "+"3";
                break;
            case R.id.button35:
                message = "k "+"4";
                break;
            case R.id.button36:
                message = "k "+"5";
                break;
            case R.id.button37:
                message = "k "+"6";
                break;
            case R.id.button38:
                message = "k "+"+";
                break;
            case R.id.button39:
                message = "k "+"(";
                break;
            case R.id.button40:
                message = "k "+"7";
                break;
            case R.id.button41:
                message = "k "+"8";
                break;
            case R.id.button42:
                message = "k "+"9";
                break;

            case R.id.button43:
                message = "k "+")";
                break;
            case R.id.button44:
                message = "k "+"-";
                break;
            case R.id.button45:
                message = "k "+"@";
                break;

            case R.id.button46:
                message = "k "+"*";
                break;
            case R.id.button47:
                message = "k "+"/";
                break;
            case R.id.button48:
                message = "k "+"=";
                break;
            case R.id.button49:
                message = "k "+"_";
                break;
            case R.id.button50:
                message = "k "+",";
                break;
            case R.id.button51:
                message = "k "+".";
                break;

            case R.id.button52:
                message = "k "+";";
                break;
            case R.id.button53:
                message = "k "+":";
                break;
            case R.id.button54:
                message = "k "+"?";
                break;

            case R.id.button61:
                message = "k "+"!";
                break;
            case R.id.button62:
                message = "k "+"%";
                break;
            case R.id.button63:
                message = "k "+"#";
                break;

            case R.id.button65:
                message = "k "+"$";
                break;
            case R.id.button55:
                message = "k "+"TAB";
                break;
            case R.id.button56:
                message = "k "+"LEFT";
                break;

            case R.id.button59:
                message = "k "+"SPACE";
                break;
            case R.id.button58:
                message = "k "+"RIGHT";
                break;
            case R.id.button57:
                message = "k "+"BACK_SPACE";
                break;


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
                client = new Socket(str1, 4444); // connect to the server
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
