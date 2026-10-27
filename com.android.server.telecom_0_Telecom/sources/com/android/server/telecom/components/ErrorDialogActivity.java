package com.android.server.telecom.components;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public class ErrorDialogActivity extends Activity {
    private static final String TAG = ErrorDialogActivity.class.getSimpleName();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getBooleanExtra("show_missing_voicemail", false)) {
            showMissingVoicemailErrorDialog();
            return;
        }
        if (getIntent().getCharSequenceExtra("error_message_string") != null) {
            showGenericErrorDialog(getIntent().getCharSequenceExtra("error_message_string"));
            return;
        }
        int intExtra = getIntent().getIntExtra("error_message_id", -1);
        if (intExtra == -1) {
            Log.w(TAG, "ErrorDialogActivity called with no error type extra.", new Object[0]);
            finish();
        } else {
            showGenericErrorDialog(intExtra);
        }
    }

    private void showGenericErrorDialog(CharSequence charSequence) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.components.ErrorDialogActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ErrorDialogActivity.this.finish();
            }
        };
        new AlertDialog.Builder(this).setMessage(charSequence).setPositiveButton(R.string.ok, onClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.server.telecom.components.ErrorDialogActivity.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ErrorDialogActivity.this.finish();
            }
        }).create().show();
    }

    private void showGenericErrorDialog(int i) {
        showGenericErrorDialog(getResources().getText(i));
    }

    private void showMissingVoicemailErrorDialog() {
        new AlertDialog.Builder(this).setTitle(com.android.server.telecom.R.string.no_vm_number).setMessage(com.android.server.telecom.R.string.no_vm_number_msg).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.components.ErrorDialogActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ErrorDialogActivity.this.finish();
            }
        }).setNegativeButton(com.android.server.telecom.R.string.add_vm_number_str, new DialogInterface.OnClickListener() { // from class: com.android.server.telecom.components.ErrorDialogActivity.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ErrorDialogActivity.this.addVoiceMailNumberPanel(dialogInterface);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.server.telecom.components.ErrorDialogActivity.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                ErrorDialogActivity.this.finish();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addVoiceMailNumberPanel(DialogInterface dialogInterface) {
        if (dialogInterface != null) {
            dialogInterface.dismiss();
        }
        startActivity(new Intent("com.android.phone.CallFeaturesSetting.ADD_VOICEMAIL"));
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
