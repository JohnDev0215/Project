package com.amazon.mp3.account.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class SignOutConfirmationDialog extends AlertDialog {
    private final Activity mActivity;

    public SignOutConfirmationDialog(Activity parent) {
        super(parent);
        this.mActivity = parent;
    }

    @Override // android.app.Dialog
    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        builder.setTitle(R.string.dmusic_sso_signout_warning_title);
        builder.setMessage(getSSOWarningMessage());
        builder.setNegativeButton(R.string.dmusic_button_cancel, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.account.activity.SignOutConfirmationDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                SignOutConfirmationDialog.this.dismiss();
            }
        });
        builder.setPositiveButton(R.string.dmusic_sign_out, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.account.activity.SignOutConfirmationDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                AccountDeregistrationDialogActivity.start(SignOutConfirmationDialog.this.mActivity);
            }
        });
        builder.create().show();
    }

    private String getSSOWarningMessage() {
        boolean zIsAppStoreInstalled = isAppStoreInstalled();
        return this.mActivity.getResources().getString(!AmazonApplication.getCapabilities().isStoreSupported() ? zIsAppStoreInstalled ? R.string.dmusic_sso_signout_warning_message_with_appstore : R.string.dmusic_sso_signout_warning_message : zIsAppStoreInstalled ? R.string.dmusic_sso_signout_with_store_warning_message_with_appstore : R.string.dmusic_sso_signout_with_store_warning_message);
    }

    private boolean isAppStoreInstalled() {
        try {
            this.mActivity.getPackageManager().getPackageInfo("com.amazon.venezia", 1);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
