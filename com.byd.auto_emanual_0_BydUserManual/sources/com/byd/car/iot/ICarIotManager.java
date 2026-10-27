package com.byd.car.iot;

import android.os.RemoteException;
import com.byd.car.iot.converter.AutoiotSetting;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarIotManager {
    boolean controlIotDevice(AutoiotSetting autoiotSetting);

    List<String> getDeviceList() throws RemoteException;

    boolean getIotDeviceInfo(AutoiotSetting autoiotSetting);

    void registerListener(ICarIotManagerCallback iCarIotManagerCallback);

    void unregisterListener();
}
