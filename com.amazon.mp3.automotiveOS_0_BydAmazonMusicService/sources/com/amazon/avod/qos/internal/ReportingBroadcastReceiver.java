package com.amazon.avod.qos.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ReportingBroadcastReceiver<T> extends BroadcastReceiver {
    protected final Context mContext;
    private final String mIntentFilter;
    protected boolean mIsRegistered = false;
    protected T mReporter;

    protected abstract void onReceive(Intent intent);

    public ReportingBroadcastReceiver(Context context, String str) {
        this.mContext = context;
        this.mIntentFilter = str;
    }

    public void register(T t) {
        this.mReporter = t;
        this.mContext.registerReceiver(this, new IntentFilter(this.mIntentFilter));
        this.mIsRegistered = true;
    }

    public void unregister() {
        if (this.mIsRegistered) {
            this.mContext.unregisterReceiver(this);
            this.mIsRegistered = false;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        onReceive(intent);
    }
}
