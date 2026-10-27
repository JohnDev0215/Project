package com.android.bluetooth.hfpclient;

import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
public class HeadsetClientStateMachineFactory {
    public HeadsetClientStateMachine make(HeadsetClientService context, HandlerThread t, NativeInterface nativeInterface) {
        return HeadsetClientStateMachine.make(context, t.getLooper(), nativeInterface);
    }
}
