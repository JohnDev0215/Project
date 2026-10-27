package com.ecarx.xui.adaptapi.device;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.device.ads.IAdvertise;
import com.ecarx.xui.adaptapi.device.daynigntmode.IDayNightMode;
import com.ecarx.xui.adaptapi.device.log.DeviceLog;
import com.ecarx.xui.adaptapi.device.storage.IStorage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Device extends AdaptAPI {
    public static final int OPERATOR_DEFAULT = 0;
    public static final int OPERATOR_GEELY = 1;
    public static final int OPERATOR_LYNKCO = 2;

    @Retention(RetentionPolicy.SOURCE)
    @interface OperatorCode {
    }

    public abstract IAdvertise getAdvertise();

    public abstract IDayNightMode getDayNightMode();

    public abstract DeviceLog getDeviceLog();

    public abstract String getIHUHardwareVersion();

    public abstract String getIhuId();

    public abstract int getMaxScreenBrightness();

    public abstract String getMcuHardwareVersion();

    public abstract String getMcuSoftwareVersion();

    public abstract int getMcuSoftwareVersionInt();

    public abstract int getMinScreenBrightness();

    public abstract String getMpuHardwareVersion();

    public abstract String getMpuSoftwareVersion();

    public abstract int getOperatorCode();

    public abstract String getOperatorName();

    public abstract String getPartNo();

    public abstract String getProjectCode();

    public abstract String getRolloffConfigCode();

    public abstract String getSerialNo();

    public abstract IStorage getStorage();

    public abstract String getSupplierCode();

    public abstract List<ComponentName> getSupportedComponents();

    public abstract String getVehicleCountryCode();

    public abstract String getVehicleType();

    public abstract String getVin();

    public abstract String getXdsn();

    public abstract boolean isDVRCameraConfigured();

    public abstract boolean isMobileNetworkConfigured();

    public abstract boolean isRearViewCameraConfigured();

    public abstract boolean isTboxConfigured();

    public abstract boolean setDayNightMode(int i);

    public static Device create(Context context) {
        try {
            return (Device) Class.forName("com.ecarx.xui.adaptapi.device.impl.DeviceImpl").getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            Log.e("Device", "new Device ex:", e);
            return null;
        }
    }
}
