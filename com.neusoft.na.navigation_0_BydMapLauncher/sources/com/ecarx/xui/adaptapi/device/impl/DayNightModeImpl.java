package com.ecarx.xui.adaptapi.device.impl;

import com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode;
import com.ecarx.xui.adaptapi.device.specific.DeviceBean;

/* JADX INFO: loaded from: classes2.dex */
public class DayNightModeImpl implements IDayNightMode {
    private DeviceBean mDeviceBean;

    public DayNightModeImpl(DeviceBean deviceBean) {
        this.mDeviceBean = deviceBean;
    }

    @Override // com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode
    public int getDayNightMode() {
        return this.mDeviceBean.getDayNightModes();
    }

    @Override // com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode
    public int getDayNight() {
        return this.mDeviceBean.getDayNight();
    }

    @Override // com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode
    public boolean registerDayNightModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        return this.mDeviceBean.registerDayNightModeCallBack(iDayNightChangeCallBack);
    }

    @Override // com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode
    public boolean unregisterDayNigntModeCallBack(IDayNightMode.IDayNightChangeCallBack iDayNightChangeCallBack) {
        return this.mDeviceBean.unregisterDayNigntModeCallBack(iDayNightChangeCallBack);
    }
}
