package android.hardware.bydauto;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoManager {
    public static final int BYDAUTO_COMMAND_RESULT_BUSY = -2147482647;
    public static final int BYDAUTO_COMMAND_RESULT_FAILED = -2147482648;
    public static final int BYDAUTO_COMMAND_RESULT_INVALID_VALUE = -2147482645;
    public static final int BYDAUTO_COMMAND_RESULT_SUCCESS = 0;
    public static final int BYDAUTO_COMMAND_RESULT_TIMEOUT = -2147482646;
    public static final int UNKNOWN_ERROR = Integer.MIN_VALUE;
    public static BYDAutoManager mInstance;

    public interface OnBYDAutoListener {
        void onChanged(int i2, int i3, double d2, Object obj);

        void onChanged(int i2, int i3, int i4, Object obj);

        void onChanged(int i2, int i3, byte[] bArr, Object obj);

        void onError(int i2, String str);
    }

    BYDAutoManager() {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int i2, int i3, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(int i2) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int i2) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public double[] getDoubleArray(int i2, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i2, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int i2, int i3, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i2, int i3, double d2) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i2, int[] iArr, double[] dArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i2, int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i2, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }
}
