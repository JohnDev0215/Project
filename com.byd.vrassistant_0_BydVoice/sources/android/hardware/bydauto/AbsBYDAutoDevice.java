package android.hardware.bydauto;

import android.content.Context;
import android.hardware.IBYDAutoDevice;
import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoDevice implements IBYDAutoDevice {
    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        return null;
    }

    public abstract int getDevicetype();

    public abstract String getGetPermission();

    public abstract String getSetPermission();

    public void registerListener(IBYDAutoListener iBYDAutoListener, int[] iArr) {
    }

    protected AbsBYDAutoDevice(Context context) {
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, int i3, Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, float f, Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean postEvent(int i, int i2, byte[] bArr, Object obj) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onPostEvent(IBYDAutoEvent iBYDAutoEvent) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public boolean onError(int i, String str) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public void registerListener(IBYDAutoListener iBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }

    @Override // android.hardware.IBYDAutoDevice
    public void unregisterListener(IBYDAutoListener iBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int i2, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int i2, double d) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    protected int set(int i, int[] iArr, float[] fArr) {
        throw new RuntimeException("Stub!");
    }

    protected int get(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    protected int[] getIntArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    protected byte[] getBuffer(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    protected double getDouble(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    protected float[] getDoubleArray(int i, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaState(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public int setMediaInfo(int i, int i2, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public String arrayToStr(float[] fArr) {
        throw new RuntimeException("Stub!");
    }
}
