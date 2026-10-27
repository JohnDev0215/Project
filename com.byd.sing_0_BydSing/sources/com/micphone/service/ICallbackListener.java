package com.micphone.service;

import android.micphone.IMicphoneNotificationCallback;

/* JADX INFO: loaded from: classes.dex */
public interface ICallbackListener {
    void registerCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback);

    void unregisterCallback(IMicphoneNotificationCallback iMicphoneNotificationCallback);
}
