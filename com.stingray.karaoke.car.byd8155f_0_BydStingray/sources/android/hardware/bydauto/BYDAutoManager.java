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
        void onChanged(int i7, int i10, double d, Object obj);

        void onChanged(int i7, int i10, int i11, Object obj);

        void onChanged(int i7, int i10, byte[] bArr, Object obj);

        void onError(int i7, String str);
    }

    public BYDAutoManager() {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int i7, int i10, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(int i7) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int i7) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public double[] getDoubleArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i7, int i10) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i7, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int i7, int i10, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i7, int i10, double d) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i7, int[] iArr, double[] dArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i7, int i10, int i11) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i7, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }
}
