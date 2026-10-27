package com.amazon.mp3.library.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.activity.LibraryBaseActivity;
import com.amazon.mp3.net.cirrus.BlueMoonExceptionsUtil;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.activity.StreamingNotAuthorizedActivity;

/* JADX INFO: loaded from: classes3.dex */
public class CirrusErrorDialogs {
    private final BaseActivity mActivity;
    private BroadcastReceiver mStreamingNotAuthorizedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.library.dialog.CirrusErrorDialogs.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            StreamingNotAuthorizedActivity.startIfTriggered(CirrusErrorDialogs.this.mActivity);
        }
    };

    public CirrusErrorDialogs(BaseActivity activity) {
        this.mActivity = activity;
    }

    public void checkForErrorsAndShowDialogIfNecessary() {
        BaseActivity baseActivity = this.mActivity;
        if ((baseActivity instanceof LibraryBaseActivity) || (baseActivity instanceof MusicHomeActivity)) {
            if (CirrusExceptions.DeviceRegistrationException.hasBeenDetected()) {
                CirrusExceptions.DeviceRegistrationException.setDetected(false);
                this.mActivity.showFragmentDialog(30031, createDeviceRegistrationDialog());
            } else if (CirrusExceptions.InvalidPaymentMethodException.hasBeenDetected()) {
                CirrusExceptions.InvalidPaymentMethodException.setDetected(false);
                this.mActivity.showFragmentDialog(30030, createInvalidPaymentMethodDialog());
            } else {
                CirrusExceptions.CloudPlayerHomeChangedException.hasBeenDetected();
            }
        }
    }

    public void onResume() {
        BlueMoonExceptionsUtil.displayBlueMoonExceptionDialog(this.mActivity);
        StreamingNotAuthorizedActivity.startIfTriggered(this.mActivity);
        this.mActivity.registerReceiver(this.mStreamingNotAuthorizedReceiver, new IntentFilter("com.amazon.mp3.playback.service.ACTION_STREAMING_NOT_AUTHORIZED"));
        checkForErrorsAndShowDialogIfNecessary();
    }

    public void onPause() {
        BlueMoonExceptionsUtil.dismissBlueMoonExceptionDialog(this.mActivity);
        this.mActivity.unregisterReceiver(this.mStreamingNotAuthorizedReceiver);
    }

    private Dialog createDeviceRegistrationDialog() {
        return new AlertDialog.Builder(this.mActivity).setTitle(R.string.dmusic_cirrus_error_device_registration_title).setMessage(R.string.dmusic_device_registration_exception_message).setPositiveButton(R.string.dmusic_cirrus_error_device_registration_button_label, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.CirrusErrorDialogs.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CirrusErrorDialogs.this.mActivity.removeFragmentDialog(30031);
                CirrusErrorDialogs.this.mActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AmazonApplication.getConfiguration(CirrusErrorDialogs.this.mActivity).getString("device_registration_exception_uri"))));
            }
        }).setNegativeButton(this.mActivity.getString(R.string.dmusic_button_cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.CirrusErrorDialogs.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                CirrusErrorDialogs.this.mActivity.removeFragmentDialog(30031);
            }
        }).create();
    }

    private Dialog createInvalidPaymentMethodDialog() {
        return new AlertDialog.Builder(this.mActivity).setTitle(R.string.dmusic_cirrus_error_invalid_payment_method_title).setMessage(R.string.dmusic_invalid_payment_method_exception_message).setPositiveButton(R.string.dmusic_cirrus_error_invalid_payment_method_button_label, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.CirrusErrorDialogs.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CirrusErrorDialogs.this.mActivity.removeFragmentDialog(30030);
                CirrusErrorDialogs.this.mActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(AmazonApplication.getConfiguration(CirrusErrorDialogs.this.mActivity).getString("invalid_payment_method_exception_uri"))));
            }
        }).setNegativeButton(this.mActivity.getString(R.string.dmusic_button_cancel), new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.CirrusErrorDialogs.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                CirrusErrorDialogs.this.mActivity.removeFragmentDialog(30030);
            }
        }).create();
    }
}
