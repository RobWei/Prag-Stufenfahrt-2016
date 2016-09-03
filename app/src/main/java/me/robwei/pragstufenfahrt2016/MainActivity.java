package me.robwei.pragstufenfahrt2016;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.os.Bundle;

import android.support.v4.app.NotificationCompat;
import android.support.v4.net.ConnectivityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        pref = getSharedPreferences("pragstufenfahrt2016", 0);
        editor = pref.edit();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY);
        String c = df.format(new java.util.Date());
        System.out.println(c);
        if(c.equals("29.08.2016"))
        {
            Notifi(this, "Los gehts!");
        }
        else if(c.equals("02.09.2016"))
        {
            Notifi(this, "Nach Hause!");
        }
    }

    public void buttonOnClick(View v)
    {
        EditText euro = (EditText) findViewById(R.id.euro);
        EditText czk = (EditText) findViewById(R.id.czk);
        if(euro.getText().length() == 0)
        {
            if(czk.getText().length() == 0)
            {
            }
            else
            {
                float out;
                float in;
                out = pref.getFloat("exc", 0);
                in = Float.valueOf(czk.getText().toString());
                float output = in / out;
                float newf = (float) (Math.round(output * 100) / 100.0);
                euro.getText().insert(0, newf + "");
            }
        }
        if(czk.getText().length() == 0)
        {
            if(euro.getText().length() == 0)
            {

            }
            else
            {
                float out;
                float in;
                out = pref.getFloat("exc", 0);
                in = Float.valueOf(euro.getText().toString());
                float output = in * out;
                float newf = (float) (Math.round(output * 100) / 100.0);
                czk.getText().insert(0, newf + "");
            }
        }
    }

    public void buttonRefreshRate(View v)
    {
        Context context = getApplicationContext();
        if(RefreshRate.isOnline())
        {

            editor.putFloat("exc", Float.valueOf(RefreshRate.refreshRate()));
            editor.commit();
            CharSequence text = "Währung abgerufen!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else
        {
            CharSequence text = "Du bist nicht Online!";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        }


    }


    //Die gesamte Funktion:
    public void Notifi(Context context, String Nachricht){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.jas)
                        .setContentTitle("Prag Stufenfahrt 2016")
                        .setContentText(Nachricht);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(666, mBuilder.build());
    }

    public void onClear(View v)
    {
        EditText euro = (EditText) findViewById(R.id.euro);
        EditText czk = (EditText) findViewById(R.id.czk);
        euro.getText().clear();
        czk.getText().clear();

    }

}
