package com.android.bluetooth.opp;

import android.bluetooth.AlertActivity;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.android.bluetooth.R;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppIncomingFileConfirmActivity extends AlertActivity implements DialogInterface.OnClickListener {
    private static final boolean D = false;
    private static final int DISMISS_TIMEOUT_DIALOG = 0;
    private static final int DISMISS_TIMEOUT_DIALOG_VALUE = 2000;
    private static final String PREFERENCE_USER_TIMEOUT = "user_timeout";
    private static final String TAG = "BluetoothIncomingFileConfirmActivity";
    private static final boolean V = false;
    private BluetoothOppTransferInfo mTransInfo;
    private ContentValues mUpdateValues;
    private Uri mUri;
    private boolean mTimeout = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.opp.BluetoothOppIncomingFileConfirmActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (BluetoothShare.USER_CONFIRMATION_TIMEOUT_ACTION.equals(intent.getAction())) {
                BluetoothOppIncomingFileConfirmActivity.this.onTimeout();
            }
        }
    };
    private final Handler mTimeoutHandler = new Handler() { // from class: com.android.bluetooth.opp.BluetoothOppIncomingFileConfirmActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                BluetoothOppIncomingFileConfirmActivity.this.finish();
            }
        }
    };

    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(2131689482);
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        Intent intent = getIntent();
        this.mUri = intent.getData();
        this.mTransInfo = new BluetoothOppTransferInfo();
        BluetoothOppTransferInfo bluetoothOppTransferInfoQueryRecord = BluetoothOppUtility.queryRecord(this, this.mUri);
        this.mTransInfo = bluetoothOppTransferInfoQueryRecord;
        if (bluetoothOppTransferInfoQueryRecord == null) {
            finish();
            return;
        }
        this.mAlertBuilder.setTitle(getString(R.string.incoming_file_confirm_content));
        this.mAlertBuilder.setView(createView());
        this.mAlertBuilder.setPositiveButton(R.string.incoming_file_confirm_ok, this);
        this.mAlertBuilder.setNegativeButton(R.string.incoming_file_confirm_cancel, this);
        setupAlert();
        if (this.mTimeout) {
            onTimeout();
        }
        registerReceiver(this.mReceiver, new IntentFilter(BluetoothShare.USER_CONFIRMATION_TIMEOUT_ACTION));
    }

    private View createView() {
        View view = getLayoutInflater().inflate(R.layout.incoming_dialog, (ViewGroup) null);
        ((TextView) view.findViewById(R.id.from_content)).setText(this.mTransInfo.mDeviceName);
        ((TextView) view.findViewById(R.id.filename_content)).setText(this.mTransInfo.mFileName);
        ((TextView) view.findViewById(R.id.size_content)).setText(Formatter.formatFileSize(this, this.mTransInfo.mTotalBytes));
        return view;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which != -2) {
            if (which == -1 && !this.mTimeout) {
                ContentValues contentValues = new ContentValues();
                this.mUpdateValues = contentValues;
                contentValues.put(BluetoothShare.USER_CONFIRMATION, (Integer) 1);
                getContentResolver().update(this.mUri, this.mUpdateValues, null, null);
                Toast.makeText(this, getString(R.string.bt_toast_1), 0).show();
                return;
            }
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        this.mUpdateValues = contentValues2;
        contentValues2.put(BluetoothShare.USER_CONFIRMATION, (Integer) 3);
        getContentResolver().update(this.mUri, this.mUpdateValues, null, null);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            finish();
            return true;
        }
        return false;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        boolean z = savedInstanceState.getBoolean(PREFERENCE_USER_TIMEOUT);
        this.mTimeout = z;
        if (z) {
            onTimeout();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(PREFERENCE_USER_TIMEOUT, this.mTimeout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTimeout() {
        this.mTimeout = true;
        changeTitle(getString(R.string.incoming_file_confirm_timeout_content, new Object[]{this.mTransInfo.mDeviceName}));
        changeButtonVisibility(-2, 8);
        changeButtonText(-1, getString(R.string.incoming_file_confirm_timeout_ok));
        Handler handler = this.mTimeoutHandler;
        handler.sendMessageDelayed(handler.obtainMessage(0), 2000L);
    }
}
