package com.ts.car.dcm.common.controller;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConnectionController {
    private static final String TAG = "ConnectionController";

    public abstract void acceptDevice(String str, boolean z);

    public abstract void addControllerListener(IControllerCallback iControllerCallback);

    public abstract int getControllerType();

    public abstract void init(Context context, int i);

    public abstract void removeControllerListener(IControllerCallback iControllerCallback);

    public abstract void setCapability(int i);

    public abstract void setDeviceCapability(String str, int i);
}
