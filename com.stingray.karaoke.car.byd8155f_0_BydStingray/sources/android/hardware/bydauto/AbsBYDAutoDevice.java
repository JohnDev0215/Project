package android.hardware.bydauto;

import android.content.Context;
import b.b;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDevice implements b {
    public AbsBYDAutoDevice(Context context) {
    }

    public String arrayToStr(int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return null;
    }

    public byte[] getBuffer(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public abstract int getDevicetype();

    public double getDouble(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ int[] getFeatureList();

    public abstract String getGetPermission();

    public int[] getIntArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract String getSetPermission();

    public abstract /* synthetic */ int getType();

    public boolean onError(int i7, String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean onPostEvent(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i7, int i10, int i11) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int i7, int i10, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int i7, int i10, int i11) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int get(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i7, int i10, float f10, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar, int[] iArr) {
    }

    public int set(int i7, int i10, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i7, int i10, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i7, int i10, double d) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i7, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i7, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}
