package com.amazon.mp3.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.settings.SettingsActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadDialogActivity extends FragmentActivity {
    public static final String TAG = "DownloadDialogActivity";
    private final DialogInterface.OnDismissListener mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.activity.DownloadDialogActivity.1
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialog) {
            DownloadDialogActivity.this.cancelDownload();
            DownloadDialogActivity.this.finish();
        }
    };
    private final DialogInterface.OnClickListener mOnDownloadMusicSettingsClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DownloadDialogActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            DownloadDialogActivity.this.goToMusicSettings();
            DownloadDialogActivity.this.finish();
        }
    };
    private final DialogInterface.OnClickListener mOnCancelClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DownloadDialogActivity.3
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
            DownloadDialogActivity.this.cancelDownload();
            DownloadDialogActivity.this.finish();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelDownload() {
    }

    public static Intent getStartIntent(Context context, int dialogType) {
        Intent intent = new Intent(context, (Class<?>) DownloadDialogActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("com.amazon.mp3.activity.DOWNLOAD_DIALOG_TYPE", dialogType);
        return intent;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle instanceState) {
        super.onCreate(instanceState);
        int intExtra = getIntent().getIntExtra("com.amazon.mp3.activity.DOWNLOAD_DIALOG_TYPE", 1);
        if (intExtra == 1) {
            handleWifiRequired();
        } else {
            if (intExtra != 2) {
                return;
            }
            handleStorageInitError(getIntent().getStringExtra("com.amazon.mp3.activity.DOWNLOAD_DIALOG_MESSAGE"));
        }
    }

    private void handleStorageInitError(String message) {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_download_failed);
        bauhausDialogBuilder.setMessage(message);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.DownloadDialogActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$handleStorageInitError$0(dialogInterface, i);
            }
        });
        bauhausDialogBuilder.setCancelable(false);
        bauhausDialogBuilder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleStorageInitError$0(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        finish();
    }

    private void handleWifiRequired() {
        Log.verbose(TAG, "DownloadDialogActivity: in handleWifiRequired.");
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_download_music_settings);
        bauhausDialogBuilder.setMessage(R.string.dmusic_download_change_settings_to_allow_download_over_mobile);
        bauhausDialogBuilder.setNegativeButton(R.string.dmusic_download_cancel_dialog_dismiss, this.mOnCancelClickListener);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_download_goto_music_settings, this.mOnDownloadMusicSettingsClickListener);
        Dialog dialogCreate = bauhausDialogBuilder.create();
        dialogCreate.setOnDismissListener(this.mOnDismissListener);
        dialogCreate.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToMusicSettings() {
        Context applicationContext = getApplicationContext();
        Intent startIntent = SettingsActivity.getStartIntent(applicationContext);
        startIntent.setFlags(268435456);
        applicationContext.startActivity(startIntent);
    }
}
