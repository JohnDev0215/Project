package com.amazon.mp3.library.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import com.amazon.mp3.activity.DialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.BauhausDialogBuilder;

/* JADX INFO: loaded from: classes3.dex */
public class NetworkFailureWithSettingsDialog extends DialogActivity {
    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        return new BauhausDialogBuilder(this).setTitle(R.string.dmusic_no_connection_title).setMessage(R.string.dmusic_no_connection_desc).setPositiveButton(R.string.dmusic_setting_title, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkFailureWithSettingsDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                NetworkFailureWithSettingsDialog.this.launchNetworkSettings();
                NetworkFailureWithSettingsDialog.this.finish();
            }
        }).setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.NetworkFailureWithSettingsDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(final DialogInterface dialog, final int which) {
                NetworkFailureWithSettingsDialog.this.finish();
            }
        }).create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchNetworkSettings() {
        Intent intent = new Intent("android.settings.SETTINGS");
        intent.setFlags(268435456);
        startActivity(intent);
    }
}
