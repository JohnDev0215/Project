package android.hardware.bydauto.mqtt;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoMqttDevice extends AbsBYDAutoDevice {
    public static final int MQTT_COMMAND_BUSY = -2147482647;
    public static final int MQTT_COMMAND_FAILED = -2147482648;
    public static final int MQTT_COMMAND_INVALID_VALUE = -2147482645;
    public static final int MQTT_COMMAND_SUCCESS = 0;
    public static final int MQTT_COMMAND_TIMEOUT = -2147482646;
    public static final String TAG = "BYDAutoMqttDevice";

    public BYDAutoMqttDevice(Context context) {
        super(null);
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoMqttDevice getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice, android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoMqttListener absBYDAutoMqttListener) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(AbsBYDAutoMqttListener absBYDAutoMqttListener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(AbsBYDAutoMqttListener absBYDAutoMqttListener, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
