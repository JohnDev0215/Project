package android.hardware.bydauto;

import android.content.Context;
import b.a;
import b.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager {
    public a mAutoManager;

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

    public byte[] getBuffer(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i8, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i8, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onChanged(int i8, int i9, double d8, Object obj);

    public abstract /* synthetic */ void onChanged(int i8, int i9, int i10, Object obj);

    public abstract /* synthetic */ void onChanged(int i8, int i9, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i8, String str);

    public abstract void removeDevice(b bVar);

    public int setBuffer(int i8, int i9, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i8, int i9, double d8) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i8, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i8, int i9, int i10) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i8, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
