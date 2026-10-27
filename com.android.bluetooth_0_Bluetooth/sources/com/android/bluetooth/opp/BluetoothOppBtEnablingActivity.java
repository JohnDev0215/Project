package com.android.bluetooth.opp;

import android.bluetooth.AlertActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.bluetooth.R;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothOppBtEnablingActivity extends AlertActivity {
    private static final int BT_ENABLING_TIMEOUT = 0;
    private static final int BT_ENABLING_TIMEOUT_VALUE = 20000;
    private static final boolean D = false;
    private static final String TAG = "BluetoothOppEnablingActivity";
    private static final boolean V = false;
    private boolean mRegistered = false;
    private final Handler mTimeoutHandler = new Handler() { // from class: com.android.bluetooth.opp.BluetoothOppBtEnablingActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                BluetoothOppBtEnablingActivity.this.cancelSendingProgress();
            }
        }
    };
    private final BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() { // from class: com.android.bluetooth.opp.BluetoothOppBtEnablingActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED") && intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE) == 12) {
                BluetoothOppBtEnablingActivity.this.mTimeoutHandler.removeMessages(0);
                BluetoothOppBtEnablingActivity.this.finish();
            }
        }
    };

    @Override // android.bluetooth.AlertActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addPrivateFlags(524288);
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter.isEnabled()) {
            finish();
            return;
        }
        IntentFilter filter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.mBluetoothReceiver, filter);
        this.mRegistered = true;
        this.mAlertBuilder.setTitle(R.string.enabling_progress_title);
        this.mAlertBuilder.setView(createView());
        setupAlert();
        Handler handler = this.mTimeoutHandler;
        handler.sendMessageDelayed(handler.obtainMessage(0), 20000L);
    }

    private View createView() {
        View view = getLayoutInflater().inflate(R.layout.bt_enabling_progress, (ViewGroup) null);
        TextView contentView = (TextView) view.findViewById(R.id.progress_info);
        contentView.setText(getString(R.string.enabling_progress_content));
        return view;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            this.mTimeoutHandler.removeMessages(0);
            cancelSendingProgress();
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mRegistered) {
            unregisterReceiver(this.mBluetoothReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelSendingProgress() {
        BluetoothOppManager mOppManager = BluetoothOppManager.getInstance(this);
        if (mOppManager.mSendingFlag) {
            mOppManager.mSendingFlag = false;
        }
        finish();
    }
}
