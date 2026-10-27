package com.ts.car.dcm.common.controller;

import com.ts.car.dcm.common.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public interface IControllerCallback {
    void onAbnormalDeviceAttach(String str, int i, int i2);

    void onAbnormalDeviceDetach(String str);

    void onDeviceAttach(LinkDevice linkDevice);

    void onDeviceChanged(LinkDevice linkDevice, int i);

    void onDeviceDetach(String str, int i);

    String onGetConfig(String str);
}
