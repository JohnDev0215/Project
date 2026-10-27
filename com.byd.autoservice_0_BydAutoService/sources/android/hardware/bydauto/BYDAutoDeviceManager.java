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

    public byte[] getBuffer(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i3, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i3, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ void onChanged(int i3, int i4, double d3, Object obj);

    public abstract /* synthetic */ void onChanged(int i3, int i4, int i5, Object obj);

    public abstract /* synthetic */ void onChanged(int i3, int i4, byte[] bArr, Object obj);

    public abstract /* synthetic */ void onError(int i3, String str);

    public abstract void removeDevice(b bVar);

    public int setBuffer(int i3, int i4, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i3, int i4, double d3) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i3, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i3, int i4, int i5) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i3, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(b bVar, int[] iArr) {
        throw new RuntimeException("Stub!");
    }
}
