package com.android.server.telecom.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telecom.Log;
import com.android.server.telecom.R;

/* JADX INFO: loaded from: classes.dex */
public class CallRedirectionTimeoutDialogActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(this, "CallRedirectionTimeoutDialogActivity onCreate.", new Object[0]);
        showDialog(getIntent().getStringExtra("android.telecom.extra.REDIRECTION_APP_NAME"));
    }

    private void showDialog(CharSequence charSequence) {
        Log.i(this, "showDialog: timeout redirection with %s", new Object[]{charSequence});
        new AlertDialog.Builder(this).setMessage(getString(R.string.alert_redirect_outgoing_call_timeout, new Object[]{charSequence})).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.ui.CallRedirectionTimeoutDialogActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                CallRedirectionTimeoutDialogActivity.this.finish();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.server.telecom.ui.CallRedirectionTimeoutDialogActivity.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                CallRedirectionTimeoutDialogActivity.this.finish();
            }
        }).create().show();
    }
}
