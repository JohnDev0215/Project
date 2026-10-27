package android.hardware.bydauto;

import a.a;
import a.b;
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

    public byte[] getBuffer(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onChanged(int i7, int i8, double d7, Object obj);

    public abstract /* synthetic */ void onChanged(int i7, int i8, int i9, Object obj);

    public abstract /* synthetic */ void onChanged(int i7, int i8, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i7, String str);

    public abstract void removeDevice(b bVar);

    public int setBuffer(int i7, int i8, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i7, int i8, double d7) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i7, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i7, int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i7, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
