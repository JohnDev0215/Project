package com.byd.systemsettings.callback;

import android.os.IBinder;

/* JADX INFO: loaded from: classes2.dex */
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

    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public OnServiceDeathCallback getCallback() {
        return this.mCallback;
    }

    public void setCallback(OnServiceDeathCallback mCallback) {
        this.mCallback = mCallback;
    }
}
