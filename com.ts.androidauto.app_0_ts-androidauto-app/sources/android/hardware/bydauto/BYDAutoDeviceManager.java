package android.hardware.bydauto;

import android.content.Context;
import android.hardware.BYDAutoManager;
import android.hardware.IBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager implements BYDAutoManager.OnBYDAutoListener {
    protected android.hardware.BYDAutoManager mAutoManager;

    public abstract void addDevice(IBYDAutoDevice iBYDAutoDevice);

    public abstract void removeDevice(IBYDAutoDevice iBYDAutoDevice);

    protected BYDAutoDeviceManager(Context con) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDeviceManager getInstance(Context con) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice device) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice device, int[] featureIDs) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(IBYDAutoDevice device) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int device, int event, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int device, int event, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int device, int event, double value) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int device, int[] event, int[] value) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int device, int event) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int device, int[] event, float[] value) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int device, int[] event) {
        throw new RuntimeException("Stub!");
    }
}
