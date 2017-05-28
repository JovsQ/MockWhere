package com.example.avinnovz.whernau.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;

import com.example.avinnovz.whernau.R;
import com.example.avinnovz.whernau.base.BaseActivity;
import com.example.avinnovz.whernau.interfaces.OnConfirmDialogListener;

import butterknife.ButterKnife;

/**
 * Created by avinnovz on 5/28/17.
 */

public class SplashActivity extends BaseActivity {

    private final static int LOCATION_SETTINGS_REQUEST = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        checkGPS();
    }

    private void checkGPS() {
        if (isGpsConnected()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            showConfirmDialog("", "Location", "Enable your GPS to continue", "Ok", null,
                    new OnConfirmDialogListener() {
                @Override
                public void onConfirmed(String action) {
                    openGpsSetting();
                }

                @Override
                public void onCancelled(String action) {

                }
            });
        }
    }

    private void openGpsSetting() {
        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS),
                LOCATION_SETTINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOCATION_SETTINGS_REQUEST) {
            checkGPS();
        }
    }
}
