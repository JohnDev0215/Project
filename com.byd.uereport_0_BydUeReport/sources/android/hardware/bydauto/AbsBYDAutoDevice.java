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

    public int get(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return null;
    }

    public byte[] getBuffer(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public abstract int getDevicetype();

    public double getDouble(int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public float[] getDoubleArray(int i8, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ int[] getFeatureList();

    public abstract String getGetPermission();

    public int[] getIntArray(int i8, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract String getSetPermission();

    public abstract /* synthetic */ int getType();

    public boolean onError(int i8, String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean onPostEvent(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar, int[] iArr) {
    }

    public int set(int i8, int i9, int i10) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int i8, int i9, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int i8, int i9, int i10) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i8, int i9, float f8, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i8, int i9, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i8, int i9, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i8, int i9, double d8) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i8, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public int set(int i8, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}
