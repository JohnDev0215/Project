package android.hardware.bydauto;

import android.content.Context;
import android.hardware.BYDAutoManager;
import android.hardware.IBYDAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager implements BYDAutoManager.OnBYDAutoListener {
    protected android.hardware.BYDAutoManager mAutoManager;

    public BYDAutoDeviceManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDeviceManager getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public abstract void addDevice(IBYDAutoDevice iBYDAutoDevice);

    public int disableDevice(IBYDAutoDevice iBYDAutoDevice) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice iBYDAutoDevice) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract void removeDevice(IBYDAutoDevice iBYDAutoDevice);

    public int setBuffer(int i, int i2, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i, int i2, double d) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(IBYDAutoDevice iBYDAutoDevice, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
