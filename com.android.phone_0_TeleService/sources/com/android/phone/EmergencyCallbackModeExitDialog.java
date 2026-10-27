package com.android.phone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.internal.telephony.Phone;
import com.android.phone.EmergencyCallbackModeService;

/* JADX INFO: loaded from: classes.dex */
public class EmergencyCallbackModeExitDialog extends Activity implements DialogInterface.OnCancelListener {
    AlertDialog mAlertDialog = null;
    ProgressDialog mProgressDialog = null;
    CountDownTimer mTimer = null;
    EmergencyCallbackModeService mService = null;
    Handler mHandler = null;
    int mDialogType = 0;
    long mEcmTimeout = 0;
    private boolean mInEmergencyCall = false;
    private Phone mPhone = null;
    private Runnable mTask = new Runnable() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.1
        @Override // java.lang.Runnable
        public void run() {
            Looper.prepare();
            EmergencyCallbackModeExitDialog.this.bindService(new Intent(EmergencyCallbackModeExitDialog.this, (Class<?>) EmergencyCallbackModeService.class), EmergencyCallbackModeExitDialog.this.mConnection, 1);
            synchronized (EmergencyCallbackModeExitDialog.this) {
                try {
                } catch (InterruptedException e) {
                    Log.d("ECM", "EmergencyCallbackModeExitDialog InterruptedException: " + e.getMessage());
                    e.printStackTrace();
                }
                if (EmergencyCallbackModeExitDialog.this.mService == null) {
                    EmergencyCallbackModeExitDialog.this.wait();
                }
            }
            EmergencyCallbackModeExitDialog emergencyCallbackModeExitDialog = EmergencyCallbackModeExitDialog.this;
            EmergencyCallbackModeService emergencyCallbackModeService = emergencyCallbackModeExitDialog.mService;
            if (emergencyCallbackModeService != null) {
                emergencyCallbackModeExitDialog.mEcmTimeout = emergencyCallbackModeService.getEmergencyCallbackModeTimeout();
                EmergencyCallbackModeExitDialog emergencyCallbackModeExitDialog2 = EmergencyCallbackModeExitDialog.this;
                emergencyCallbackModeExitDialog2.mInEmergencyCall = emergencyCallbackModeExitDialog2.mService.getEmergencyCallbackModeCallState();
                try {
                    EmergencyCallbackModeExitDialog.this.unbindService(EmergencyCallbackModeExitDialog.this.mConnection);
                } catch (IllegalArgumentException unused) {
                    Log.w("EmergencyCallbackMode", "Failed to unbind from EmergencyCallbackModeService");
                }
            }
            EmergencyCallbackModeExitDialog.this.mHandler.post(new Runnable() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.1.1
                @Override // java.lang.Runnable
                public void run() {
                    EmergencyCallbackModeExitDialog.this.showEmergencyCallbackModeExitDialog();
                }
            });
        }
    };
    private BroadcastReceiver mEcmExitReceiver = new BroadcastReceiver() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!intent.getAction().equals("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED") || intent.getBooleanExtra("android.telephony.extra.PHONE_IN_ECM_STATE", false)) {
                return;
            }
            AlertDialog alertDialog = EmergencyCallbackModeExitDialog.this.mAlertDialog;
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
            ProgressDialog progressDialog = EmergencyCallbackModeExitDialog.this.mProgressDialog;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            EmergencyCallbackModeExitDialog.this.setResult(-1);
            EmergencyCallbackModeExitDialog.this.finish();
        }
    };
    private ServiceConnection mConnection = new ServiceConnection() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.7
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            EmergencyCallbackModeExitDialog.this.mService = ((EmergencyCallbackModeService.LocalBinder) iBinder).getService();
            synchronized (EmergencyCallbackModeExitDialog.this) {
                EmergencyCallbackModeExitDialog.this.notify();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            EmergencyCallbackModeExitDialog.this.mService = null;
        }
    };
    private Handler mTimerResetHandler = new Handler() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.8
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 1 && !((Boolean) ((AsyncResult) message.obj).result).booleanValue()) {
                EmergencyCallbackModeExitDialog.this.setResult(0);
                EmergencyCallbackModeExitDialog.this.finish();
            }
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addPrivateFlags(524288);
        Phone phoneInEcm = PhoneGlobals.getInstance().getPhoneInEcm();
        this.mPhone = phoneInEcm;
        if (phoneInEcm == null || !phoneInEcm.isInEcm()) {
            Log.i("EmergencyCallbackMode", "ECMModeExitDialog launched - isInEcm: false phone:" + this.mPhone);
            finish();
            return;
        }
        Log.i("EmergencyCallbackMode", "ECMModeExitDialog launched - isInEcm: true phone:" + this.mPhone);
        this.mHandler = new Handler();
        new Thread(null, this.mTask, "EcmExitDialogWaitThread").start();
        this.mPhone.registerForEcmTimerReset(this.mTimerResetHandler, 1, (Object) null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED");
        registerReceiver(this.mEcmExitReceiver, intentFilter);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.mEcmExitReceiver);
        } catch (IllegalArgumentException unused) {
        }
        Phone phone = this.mPhone;
        if (phone != null) {
            phone.unregisterForEcmTimerReset(this.mHandler);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mDialogType = bundle.getInt("DIALOG_TYPE");
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("DIALOG_TYPE", this.mDialogType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmergencyCallbackModeExitDialog() {
        if (isDestroyed()) {
            Log.w("EmergencyCallbackMode", "Tried to show dialog, but activity was already finished");
            return;
        }
        if (this.mInEmergencyCall) {
            this.mDialogType = 4;
            showDialog(4);
            return;
        }
        if (getIntent().getAction().equals("android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS")) {
            this.mDialogType = 1;
            showDialog(1);
        } else if (getIntent().getAction().equals("com.android.phone.action.ACTION_SHOW_ECM_EXIT_DIALOG")) {
            this.mDialogType = 2;
            showDialog(2);
        }
        this.mTimer = new CountDownTimer(this.mEcmTimeout, 1000L) { // from class: com.android.phone.EmergencyCallbackModeExitDialog.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                EmergencyCallbackModeExitDialog.this.mAlertDialog.setMessage(EmergencyCallbackModeExitDialog.this.getDialogText(j));
            }
        }.start();
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i == 1 || i == 2) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this, android.R.style.Theme.DeviceDefault.Dialog.Alert).setIcon(R.drawable.ic_emergency_callback_mode).setTitle(R.string.phone_in_ecm_notification_title).setMessage(getDialogText(this.mEcmTimeout)).setPositiveButton(R.string.alert_dialog_yes, new DialogInterface.OnClickListener() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    EmergencyCallbackModeExitDialog.this.mPhone.exitEmergencyCallbackMode();
                    EmergencyCallbackModeExitDialog.this.showDialog(3);
                    EmergencyCallbackModeExitDialog.this.mTimer.cancel();
                }
            }).setNegativeButton(R.string.alert_dialog_no, new DialogInterface.OnClickListener() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    EmergencyCallbackModeExitDialog.this.setResult(0);
                    EmergencyCallbackModeExitDialog.this.finish();
                }
            }).create();
            this.mAlertDialog = alertDialogCreate;
            alertDialogCreate.setOnCancelListener(this);
            return this.mAlertDialog;
        }
        if (i != 3) {
            if (i != 4) {
                return null;
            }
            AlertDialog alertDialogCreate2 = new AlertDialog.Builder(this, android.R.style.Theme.DeviceDefault.Dialog.Alert).setIcon(R.drawable.ic_emergency_callback_mode).setTitle(R.string.phone_in_ecm_notification_title).setMessage(R.string.alert_dialog_in_ecm_call).setNeutralButton(R.string.alert_dialog_dismiss, new DialogInterface.OnClickListener() { // from class: com.android.phone.EmergencyCallbackModeExitDialog.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    EmergencyCallbackModeExitDialog.this.setResult(0);
                    EmergencyCallbackModeExitDialog.this.finish();
                }
            }).create();
            this.mAlertDialog = alertDialogCreate2;
            alertDialogCreate2.setOnCancelListener(this);
            return this.mAlertDialog;
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.mProgressDialog = progressDialog;
        progressDialog.setMessage(getText(R.string.progress_dialog_exiting_ecm));
        this.mProgressDialog.setIndeterminate(true);
        this.mProgressDialog.setCancelable(false);
        return this.mProgressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence getDialogText(long j) {
        int i = (int) (j / 60000);
        String str = String.format("%d:%02d", Integer.valueOf(i), Long.valueOf((j % 60000) / 1000));
        int i2 = this.mDialogType;
        if (i2 == 1) {
            return String.format(getResources().getQuantityText(R.plurals.alert_dialog_not_avaialble_in_ecm, i).toString(), str);
        }
        if (i2 != 2) {
            return null;
        }
        return String.format(getResources().getQuantityText(this.mPhone.getImsPhone() != null && this.mPhone.getImsPhone().isInImsEcm() ? R.plurals.alert_dialog_exit_ecm_without_data_restriction_hint : R.plurals.alert_dialog_exit_ecm, i).toString(), str);
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        setResult(0);
        finish();
    }
}
