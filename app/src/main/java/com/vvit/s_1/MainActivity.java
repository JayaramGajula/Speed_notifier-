package com.vvit.s_1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.SEND_SMS,Manifest.permission.READ_SMS,Manifest.permission.CALL_PHONE}, 1000);
        } else {
            dostuff();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dostuff();
            } else {
                finish();
            }
        }
    }

    public void dostuff() {
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (lm != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        int  count=0,n=0;
        TextView txt = (TextView) this.findViewById(R.id.txt_speed);
        TextView t1=(TextView) this.findViewById(R.id._txt2);
        if (location == null) {
            txt.setText("_._ km/hr");

        } else {
            float current_speed = location.getSpeed();
            txt.setText((current_speed * 3.6) + " km/hr");
           //int  count=0,n=0;

           double c=location.getSpeed();
           c=c*3.6;

            if(c>45)
            {

                if(count%100==0) {
                    String mobile = "8309736079";
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + mobile));
                    startActivity(i);
                    String sms = "you are driving more than ur limit";
                    String mbno = "8309736079";
                    SmsManager mySsmManager = SmsManager.getDefault();
                    mySsmManager.sendTextMessage(mbno, null, sms, null, null);
                    count++;
                    //t1.setText("hello passed");

                }
            }
        }

    }
}
