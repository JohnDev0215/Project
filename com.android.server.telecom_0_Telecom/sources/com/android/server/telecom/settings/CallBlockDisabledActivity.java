package com.android.server.telecom.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.android.server.telecom.R;

/* JADX INFO: loaded from: classes.dex */
public class CallBlockDisabledActivity extends Activity {
    private AlertDialog mDialog;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        showCallBlockingOffDialog();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        dismissCallBlockingOffDialog();
    }

    private void showCallBlockingOffDialog() {
        if (isShowingCallBlockingOffDialog()) {
            return;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setTitle(R.string.phone_strings_emergency_call_made_dialog_title_txt).setMessage(R.string.phone_strings_emergency_call_made_dialog_call_blocking_text_txt).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.settings.CallBlockDisabledActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BlockedNumbersUtil.setEnhancedBlockSetting(CallBlockDisabledActivity.this, "show_emergency_call_notification", false);
                BlockedNumbersUtil.updateEmergencyCallNotification(CallBlockDisabledActivity.this, false);
                CallBlockDisabledActivity.this.finish();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.server.telecom.settings.CallBlockDisabledActivity.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                CallBlockDisabledActivity.this.finish();
            }
        }).create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.setCanceledOnTouchOutside(false);
        this.mDialog.show();
    }

    private void dismissCallBlockingOffDialog() {
        if (isShowingCallBlockingOffDialog()) {
            this.mDialog.dismiss();
        }
        this.mDialog = null;
    }

    private boolean isShowingCallBlockingOffDialog() {
        AlertDialog alertDialog = this.mDialog;
        return alertDialog != null && alertDialog.isShowing();
    }
}
