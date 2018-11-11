package com.example.cybersecurity.hackaton.sentinela2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cybersecurity.hackaton.sentinela2.Models.RegistroIntentService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class FormActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean enviado = sharedPreferences.getBoolean("enviado", false);
            }
        };

        if (verificarPlayServices()) {
            Intent intent = new Intent(this, RegistroIntentService.class);
            startService(intent);
        }
    }


    private boolean verificarPlayServices() {
        int codigo = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (codigo != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(codigo)) {
                GooglePlayServicesUtil.getErrorDialog(codigo, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "Este  dispositivonãopermiteusaro  recurso.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("registrationComplete"));
    }

    @Override protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
        super.onPause();
    }


}
