package android.hardware.bydauto;

import android.content.Context;
import defpackage.b;
import defpackage.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class BYDAutoDeviceManager {
    public b mAutoManager;

    public BYDAutoDeviceManager(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static synchronized BYDAutoDeviceManager getInstance(Context context) {
        throw new RuntimeException("Stub!");
    }

    public abstract void addDevice(c cVar);

    public int disableDevice(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(c cVar) {
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

    public abstract /* synthetic */ void onChanged(int i, int i2, double d, Object obj);

    public abstract /* synthetic */ void onChanged(int i, int i2, int i3, Object obj);

    public abstract /* synthetic */ void onChanged(int i, int i2, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i, String str);

    public abstract void removeDevice(c cVar);

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

    public int enableDevice(c cVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
