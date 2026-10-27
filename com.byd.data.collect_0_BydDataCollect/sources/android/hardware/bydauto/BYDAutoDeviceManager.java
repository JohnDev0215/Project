package android.hardware.bydauto;

import a.a.a;
import a.a.b;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager {
    protected a mAutoManager;

    protected BYDAutoDeviceManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDeviceManager getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public abstract void addDevice(b bVar);

    public int disableDevice(b bVar) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i2, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i2, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onChanged(int i2, int i3, double d2, Object obj);

    public abstract /* synthetic */ void onChanged(int i2, int i3, int i4, Object obj);

    public abstract /* synthetic */ void onChanged(int i2, int i3, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i2, String str);

    public abstract void removeDevice(b bVar);

    public int setBuffer(int i2, int i3, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i2, int i3, double d2) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i2, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i2, int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i2, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
