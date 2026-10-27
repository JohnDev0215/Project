package com.amazon.mp3.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.task.ConnectStrategy;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class NetConnectStrategy implements ConnectStrategy {
    private String mPreviousNetworkLog;
    String mTAG = getClass().getSimpleName();
    boolean mReceiverIsRegistered = false;
    final Object mLockObject = new Object();
    private BroadcastReceiver mOnNetStateChangedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.net.NetConnectStrategy.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            NetConnectStrategy.this.logNetworkStateChange(intent);
            if (ConnectivityUtil.hasAnyInternetConnection(context) && ConnectivityUtil.hasValidInternetConnection(context)) {
                synchronized (NetConnectStrategy.this.mLockObject) {
                    NetConnectStrategy.this.mLockObject.notifyAll();
                }
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void logNetworkStateChange(final Intent intent) {
        if (intent == null || intent.getExtras() == null || intent.getExtras().size() == 0) {
            return;
        }
        Bundle extras = intent.getExtras();
        StringBuilder sb = new StringBuilder();
        for (String str : extras.keySet()) {
            sb.append(String.format("%s: %s; ", str, extras.get(str)));
        }
        String string = sb.toString();
        if (string.equals(this.mPreviousNetworkLog)) {
            return;
        }
        Log.info(this.mTAG, string);
        this.mPreviousNetworkLog = string;
    }

    public void registerReceiver() {
        if (this.mReceiverIsRegistered) {
            return;
        }
        AmazonApplication.getContext().registerReceiver(this.mOnNetStateChangedReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.mReceiverIsRegistered = true;
    }

    public void unregisterReceiver() {
        if (this.mReceiverIsRegistered) {
            this.mReceiverIsRegistered = false;
            AmazonApplication.getContext().unregisterReceiver(this.mOnNetStateChangedReceiver);
        }
    }

    @Override // com.amazon.mp3.task.ConnectStrategy
    public Object getConnection() {
        registerReceiver();
        synchronized (this.mLockObject) {
            while (true) {
                if (ConnectivityUtil.hasAnyInternetConnection() && ConnectivityUtil.hasValidInternetConnection()) {
                }
                Log.info(this.mTAG, "No valid Net connection - Task will wait until Net is available");
                try {
                    this.mLockObject.wait();
                    Log.info(this.mTAG, "Net State Changed - Blocked Net Tasks may be released");
                } catch (InterruptedException unused) {
                }
            }
        }
        unregisterReceiver();
        return null;
    }
}
