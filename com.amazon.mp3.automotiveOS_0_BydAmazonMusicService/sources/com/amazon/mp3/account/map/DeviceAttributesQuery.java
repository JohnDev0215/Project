package com.amazon.mp3.account.map;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.extensions.ContextKt;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceAttributesQuery {
    private static final String TAG = "DeviceAttributesQuery";
    private final Context mContext;

    public DeviceAttributesQuery(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public String fetchCentralDeviceType() throws DeviceDataStoreException {
        return DeviceDataStore.getInstance(this.mContext).getValue("DeviceType");
    }

    @Nullable
    public String fetchDeviceSerialNumber() throws RuntimeException {
        if (PlatformUtil.isFireOS()) {
            String deviceSerialNumber = MAPUtil.getDeviceSerialNumber();
            if (deviceSerialNumber != null) {
                return deviceSerialNumber;
            }
            Log.error(TAG, "dsn is null from MAPUtil, fallback to call the deprecated MAP API, permission READ_PHONE_STATE: " + ContextKt.isPermissionGranted(this.mContext, "android.permission.READ_PHONE_STATE") + ", permission READ_PRIVILEGED_PHONE_STATE: " + ContextKt.isPermissionGranted(this.mContext, "android.permission.READ_PRIVILEGED_PHONE_STATE"));
        }
        try {
            return DeviceDataStore.getInstance(this.mContext).getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
        } catch (DeviceDataStoreException e) {
            throw new RuntimeException("FATAL Exception couldn't fetch deviceId from MAP", e);
        }
    }
}
