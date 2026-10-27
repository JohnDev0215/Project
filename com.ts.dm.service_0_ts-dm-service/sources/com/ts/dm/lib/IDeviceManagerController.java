package com.ts.dm.lib;

import android.content.Context;
import com.ts.car.sdk.dm.aidl.ICheckResultCallback;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;
import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import com.ts.dm.lib.config.Config;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDeviceManagerController {
    boolean cancelWirelessCarPlay(String str);

    void checkCarplayWirelessCapability(String str, ICheckResultCallback iCheckResultCallback);

    int connect(ConnectRequest connectRequest);

    int connect(String str, int i);

    boolean deleteDevice(String str);

    int disconnect(String str, int i);

    List<DmDevice> getDeviceList();

    List<DmDevice> getOnlineDeviceList();

    void registerListener(IDeviceManagementListener iDeviceManagementListener);

    void unregisterListener(IDeviceManagementListener iDeviceManagementListener);

    static IDeviceManagerController getDefaultInstance(Context context, int dbVersion, Config config) {
        return new DeviceManagerController(context, dbVersion, config);
    }
}
