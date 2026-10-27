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
        void onChanged(int i3, int i4, double d3, Object obj);

        void onChanged(int i3, int i4, int i5, Object obj);

        void onChanged(int i3, int i4, byte[] bArr, Object obj);

        void onError(int i3, String str);
    }

    public BYDAutoManager() {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int i3, int i4, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(int i3) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int i3) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public double[] getDoubleArray(int i3, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int i3, int i4) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int i3, int[] iArr) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int i3, int i4, byte[] bArr) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int i3, int i4, double d3) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int i3, int[] iArr, double[] dArr) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int i3, int i4, int i5) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int i3, int[] iArr, int[] iArr2) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAutoListener onBYDAutoListener) {
        throw new RuntimeException("Stub!");
    }
}
