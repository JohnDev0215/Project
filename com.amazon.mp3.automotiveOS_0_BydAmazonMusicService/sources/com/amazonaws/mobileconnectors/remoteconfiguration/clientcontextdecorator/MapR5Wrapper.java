package com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;

/* JADX INFO: loaded from: classes5.dex */
public class MapR5Wrapper {
    private String deviceType;
    private String dsn;

    public boolean isMapR5Present() {
        return true;
    }

    public MapR5Wrapper(Context context) {
        if (isMapR5Present()) {
            DeviceDataStore deviceDataStore = DeviceDataStore.getInstance(context);
            try {
                this.deviceType = deviceDataStore.getValue("DeviceType");
            } catch (Exception unused) {
                this.deviceType = null;
            }
            try {
                this.dsn = deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
            } catch (Exception unused2) {
                this.dsn = null;
            }
        }
    }

    public String getDeviceSerialNumber() {
        return this.dsn;
    }

    public String getDeviceType() {
        return this.deviceType;
    }
}
