package com.example.avinnovz.whernau.base;

import android.content.Context;
import android.content.DialogInterface;
import android.location.LocationManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialog;

import com.example.avinnovz.whernau.R;
import com.example.avinnovz.whernau.interfaces.OnConfirmDialogListener;

/**
 * Created by avinnovz on 5/28/17.
 */

public class BaseActivity extends AppCompatActivity {

    /** check gps availability */
    public boolean isGpsConnected() {
        final LocationManager locationManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /** dynamic confirm dialog */
    public void showConfirmDialog(final String action, final String title, final String message,
                                  final String positiveText, final String negativeText,
                                  final OnConfirmDialogListener onConfirmDialogListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title == null ? getString(R.string.app_name) : title);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (onConfirmDialogListener != null) {
                    dialogInterface.dismiss();
                    onConfirmDialogListener.onConfirmed(action);
                }
            }
        });
        if (negativeText != null && !negativeText.isEmpty()) {
            builder.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (onConfirmDialogListener != null) {
                        onConfirmDialogListener.onCancelled(action);
                    }
                    dialogInterface.dismiss();
                }
            });
        }
        final AppCompatDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
