package com.byd.systemsettings.callback;

import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public class DeathRecipientCallback implements IBinder.DeathRecipient {
    private OnServiceDeathCallback mCallback;
    private String serviceName;

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        OnServiceDeathCallback onServiceDeathCallback = this.mCallback;
        if (onServiceDeathCallback != null) {
            onServiceDeathCallback.onServiceDied(this.serviceName);
        }
    }

    public OnServiceDeathCallback getCallback() {
        return this.mCallback;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setCallback(OnServiceDeathCallback onServiceDeathCallback) {
        this.mCallback = onServiceDeathCallback;
    }

    public void setServiceName(String str) {
        this.serviceName = str;
    }
}
