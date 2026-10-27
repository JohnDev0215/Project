package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes2.dex */
public final class CallType {
    public static final int BCALL = 2;
    public static final int ECALL = 3;
    public static final int ICALL = 1;
    public static final int OTHER = 0;

    public static String toString(int callType) {
        if (callType == 0) {
            return "OTHER";
        }
        if (callType == 1) {
            return "ICALL";
        }
        if (callType == 2) {
            return "BCALL";
        }
        if (callType == 3) {
            return "ECALL";
        }
        return "0x" + Integer.toHexString(callType);
    }

    public static boolean isValidCallType(int callType) {
        return callType >= 0 && callType <= 3;
    }
}
