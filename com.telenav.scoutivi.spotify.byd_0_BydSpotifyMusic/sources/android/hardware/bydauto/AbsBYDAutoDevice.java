package android.hardware.bydauto;

import a.b;
import a.c;
import a.d;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDevice implements b {
    protected AbsBYDAutoDevice(Context context) {
    }

    public String arrayToStr(int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    protected int get(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return null;
    }

    protected byte[] getBuffer(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    public abstract int getDevicetype();

    protected double getDouble(int i7, int i8) {
        throw new RuntimeException("Stub!");
    }

    protected float[] getDoubleArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ int[] getFeatureList();

    public abstract String getGetPermission();

    protected int[] getIntArray(int i7, int[] iArr) {
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

    public boolean postEvent(int i7, int i8, int i9, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar, int[] iArr) {
    }

    protected int set(int i7, int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int i7, int i8, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int i7, int i8, int i9) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i7, int i8, float f7, Object obj) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i7, int i8, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i7, int i8, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i7, int i8, double d7) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i7, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i7, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}
