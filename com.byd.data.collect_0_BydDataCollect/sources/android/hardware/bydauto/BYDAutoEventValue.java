package android.hardware.bydauto;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEventValue {
    public static final int INVAILD_INT = -999999999;
    public static final double INVALID_DOUBLE = -9.99999999E8d;
    public static final float INVALID_FLOAT = -1.0E9f;
    private static final String TAG = "BYDAutoEventValue";
    public byte[] bufferDataValue;
    public float[] floatArrayValue;
    public int[] intArrayValue;
    public double doubleValue = -9.99999999E8d;
    public float floatValue = -1.0E9f;
    public int intValue = INVAILD_INT;

    public BYDAutoEventValue() {
        android.util.Log.d(TAG, TAG);
    }
}
