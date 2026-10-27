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
        void onChanged(int i, int i2, double d, Object obj);

        void onChanged(int i, int i2, int i3, Object obj);

        void onChanged(int i, int i2, byte[] bArr, Object obj);

        void onError(int i, String str);
    }

    BYDAutoManager() {
        throw new RuntimeException("Stub!");
    }

    public int getInt(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setInt(int deviceType, int eventType, int value) {
        throw new RuntimeException("Stub!");
    }

    public int setBuffer(int deviceType, int eventType, byte[] value) {
        throw new RuntimeException("Stub!");
    }

    public double getDouble(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setDouble(int deviceType, int eventType, double value) {
        throw new RuntimeException("Stub!");
    }

    public int[] getIntArray(int deviceType, int[] eventType) {
        throw new RuntimeException("Stub!");
    }

    public byte[] getBuffer(int deviceType, int eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setIntArray(int deviceType, int[] eventType, int[] value) {
        throw new RuntimeException("Stub!");
    }

    public double[] getDoubleArray(int deviceType, int[] eventType) {
        throw new RuntimeException("Stub!");
    }

    public int setDoubleArray(int deviceType, int[] eventType, double[] value) {
        throw new RuntimeException("Stub!");
    }

    public int enableDevice(int deviceType) {
        throw new RuntimeException("Stub!");
    }

    public int disableDevice(int deviceType) {
        throw new RuntimeException("Stub!");
    }

    public void registerListener(OnBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public void unregisterListener(OnBYDAutoListener l) {
        throw new RuntimeException("Stub!");
    }

    public static void dispatchNativeEvent(int device_type, int event_type, byte[] value) {
        throw new RuntimeException("Stub!");
    }
}
