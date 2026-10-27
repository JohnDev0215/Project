package android.hardware.bydauto;

import android.content.Context;
import b.a;
import b.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager {
    protected a mAutoManager;

    public BYDAutoDeviceManager(Context context) {
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

    public byte[] getBuffer(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onChanged(int i7, int i10, double d, Object obj);

    public abstract /* synthetic */ void onChanged(int i7, int i10, int i11, Object obj);

    public abstract /* synthetic */ void onChanged(int i7, int i10, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i7, String str);

    public abstract void removeDevice(b bVar);

    public int setBuffer(int i7, int i10, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i7, int i10, double d) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i7, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i7, int i10, int i11) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i7, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
