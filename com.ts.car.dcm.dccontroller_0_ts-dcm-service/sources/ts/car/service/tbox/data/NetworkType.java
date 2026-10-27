package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public final class NetworkType {
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_ERROR = 20;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_IWLAN = 18;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_LTE_CA = 19;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_XRTT = 7;
    private static String[] sNetworkStrings = {"NETWORK_TYPE_UNKNOWN", "NETWORK_TYPE_GPRS", "NETWORK_TYPE_EDGE", "NETWORK_TYPE_UMTS", "NETWORK_TYPE_CDMA", "NETWORK_TYPE_EVDO_0", "NETWORK_TYPE_EVDO_A", "NETWORK_TYPE_1xRTT", "NETWORK_TYPE_HSDPA", "NETWORK_TYPE_HSUPA", "NETWORK_TYPE_HSPA", "NETWORK_TYPE_IDEN", "NETWORK_TYPE_EVDO_B", "NETWORK_TYPE_LTE", "NETWORK_TYPE_EHRPD", "NETWORK_TYPE_HSPAP", "NETWORK_TYPE_GSM", "NETWORK_TYPE_TD_SCDMA", "NETWORK_TYPE_IWLAN", "NETWORK_TYPE_LTE_CA", "NETWORK_TYPE_ERROR"};

    public static String toString(int networkType) {
        if (networkType >= 0 && networkType <= 20) {
            return sNetworkStrings[networkType];
        }
        return "0x" + Integer.toHexString(networkType);
    }
}
