package android.hardware.bydauto;

import android.content.Context;
import b.b.b;
import b.b.c;
import b.b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDevice implements b {
    protected AbsBYDAutoDevice(Context context) {
    }

    public String arrayToStr(int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    protected int get(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return null;
    }

    protected byte[] getBuffer(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public abstract int getDevicetype();

    protected double getDouble(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    protected float[] getDoubleArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract /* synthetic */ int[] getFeatureList();

    public abstract String getGetPermission();

    protected int[] getIntArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public abstract String getSetPermission();

    public abstract /* synthetic */ int getType();

    public boolean onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean onPostEvent(c cVar) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(d dVar, int[] iArr) {
    }

    protected int set(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int i, int i2, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(d dVar) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i, int i2, float f, Object obj) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int i2, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int i2, double d2) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }
}
