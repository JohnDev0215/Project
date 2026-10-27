package com.android.phone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import com.android.internal.telephony.CommandException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TimeConsumingPreferenceActivity extends PreferenceActivity implements TimeConsumingPreferenceListener, DialogInterface.OnCancelListener {
    private final DialogInterface.OnClickListener mDismiss;
    private final DialogInterface.OnClickListener mDismissAndFinish;
    private final ArrayList<String> mBusyList = new ArrayList<>();
    protected boolean mIsForeground = false;

    public TimeConsumingPreferenceActivity() {
        this.mDismiss = new DismissOnClickListener();
        this.mDismissAndFinish = new DismissAndFinishOnClickListener();
    }

    private class DismissOnClickListener implements DialogInterface.OnClickListener {
        private DismissOnClickListener(TimeConsumingPreferenceActivity timeConsumingPreferenceActivity) {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    private class DismissAndFinishOnClickListener implements DialogInterface.OnClickListener {
        private DismissAndFinishOnClickListener() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            TimeConsumingPreferenceActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        int i2;
        if (i == 100 || i == 200) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getText(R.string.updating_title));
            progressDialog.setIndeterminate(true);
            if (i == 100) {
                progressDialog.setCancelable(true);
                progressDialog.setOnCancelListener(this);
                progressDialog.setMessage(getText(R.string.reading_settings));
                return progressDialog;
            }
            if (i != 200) {
                return null;
            }
            progressDialog.setCancelable(false);
            progressDialog.setMessage(getText(R.string.updating_settings));
            return progressDialog;
        }
        if (i != 400 && i != 500 && i != 300 && i != 600 && i != 700 && i != 800 && i != 900 && i != 1000) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (i == 400) {
            i2 = R.string.response_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else if (i == 500) {
            i2 = R.string.radio_off_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismissAndFinish);
        } else if (i == 600) {
            i2 = R.string.fdn_check_failure;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else if (i == 700) {
            i2 = R.string.stk_cc_ss_to_dial_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else if (i == 800) {
            i2 = R.string.stk_cc_ss_to_ussd_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else if (i == 900) {
            i2 = R.string.stk_cc_ss_to_ss_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else if (i == 1000) {
            i2 = R.string.stk_cc_ss_to_dial_video_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        } else {
            i2 = R.string.exception_error;
            builder.setPositiveButton(R.string.close_dialog, this.mDismiss);
        }
        builder.setTitle(getText(R.string.error_updating_title));
        builder.setMessage(getText(i2));
        builder.setCancelable(false);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.getWindow().addFlags(4);
        return alertDialogCreate;
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mIsForeground = true;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mIsForeground = false;
    }

    @Override // com.android.phone.TimeConsumingPreferenceListener
    public void onStarted(Preference preference, boolean z) {
        Log.i("TimeConsumingPrefActivity", "onStarted, preference=" + preference.getKey() + ", reading=" + z);
        this.mBusyList.add(preference.getKey());
        if (this.mIsForeground) {
            if (z) {
                showDialog(100);
            } else {
                showDialog(200);
            }
        }
    }

    public void onFinished(Preference preference, boolean z) {
        Log.i("TimeConsumingPrefActivity", "onFinished, preference=" + preference.getKey() + ", reading=" + z);
        this.mBusyList.remove(preference.getKey());
        if (this.mBusyList.isEmpty()) {
            if (z) {
                dismissDialogSafely(100);
            } else {
                dismissDialogSafely(200);
            }
        }
        preference.setEnabled(true);
    }

    @Override // com.android.phone.TimeConsumingPreferenceListener
    public void onError(Preference preference, int i) {
        if (!preference.isEnabled()) {
            Log.i("TimeConsumingPrefActivity", "onError, skipped duplicated error popup");
            return;
        }
        Log.i("TimeConsumingPrefActivity", "onError, preference=" + preference.getKey() + ", error=" + i);
        if (this.mIsForeground) {
            showDialog(i);
        }
        preference.setEnabled(false);
    }

    @Override // com.android.phone.TimeConsumingPreferenceListener
    public void onException(Preference preference, CommandException commandException) {
        if (commandException.getCommandError() == CommandException.Error.FDN_CHECK_FAILURE) {
            onError(preference, 600);
            return;
        }
        if (commandException.getCommandError() == CommandException.Error.RADIO_NOT_AVAILABLE) {
            onError(preference, 500);
            return;
        }
        if (commandException.getCommandError() == CommandException.Error.SS_MODIFIED_TO_DIAL) {
            onError(preference, 700);
            return;
        }
        if (commandException.getCommandError() == CommandException.Error.SS_MODIFIED_TO_DIAL_VIDEO) {
            onError(preference, 1000);
            return;
        }
        if (commandException.getCommandError() == CommandException.Error.SS_MODIFIED_TO_USSD) {
            onError(preference, 800);
        } else if (commandException.getCommandError() == CommandException.Error.SS_MODIFIED_TO_SS) {
            onError(preference, 900);
        } else {
            preference.setEnabled(false);
            onError(preference, 300);
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        finish();
    }

    protected void dismissDialogSafely(int i) {
        try {
            dismissDialog(i);
        } catch (IllegalArgumentException unused) {
        }
    }
}
