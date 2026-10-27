package com.ts.car.dcm.common.controller;

import com.ts.car.dcm.common.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IUsbControllerCallback extends IControllerCallback {
    void onResetUsbBusStarting(String str);

    void onResetUsbFinished(String str, boolean z);

    void onRoleSwitchFinished(LinkDevice linkDevice, int i, boolean z);

    void onRoleSwitchStarting(String str, int i);

    void onUsbAoAFinished(LinkDevice linkDevice, int i, boolean z);

    void onUsbAoAStarting(String str, int i);
}
