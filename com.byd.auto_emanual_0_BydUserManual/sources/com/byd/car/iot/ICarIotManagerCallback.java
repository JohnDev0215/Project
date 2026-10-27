package com.byd.car.iot;

import com.byd.car.iot.converter.AutoiotSetting;

/* JADX INFO: loaded from: classes.dex */
public interface ICarIotManagerCallback {
    void controlIotDeviceCallback(AutoiotSetting autoiotSetting, int i);

    void getIotDeviceInfoCallback(AutoiotSetting autoiotSetting, int i);
}
