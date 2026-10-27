package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoDevice;
import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDevice implements IBYDAutoDevice {
    public abstract int getDevicetype();

    public abstract String getGetPermission();

    public abstract String getSetPermission();

    protected AbsBYDAutoDevice(Context con) {
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, float value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int device_type, int event_type, byte[] value, Object data) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onPostEvent(IBYDAutoEvent event) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onError(int errCode, String errMessage) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public void registerListener(IBYDAutoListener listener) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(IBYDAutoListener listener, int[] featureIDs) {
    }

    @Override // android.hardware.IBYDAutoDevice
    public void unregisterListener(IBYDAutoListener listener) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int event, double value) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int[] event, int[] params) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int device, int[] event, float[] params) {
        throw new RuntimeException("Stub!");
    }

    protected int get(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    protected int[] getIntArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    protected byte[] getBuffer(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    protected double getDouble(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    protected float[] getDoubleArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] eventTypes, BYDAutoEventValue eventValue) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        return null;
    }

    public String arrayToStr(int[] intArray) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] floatArray) {
        throw new RuntimeException("Stub!");
    }
}
