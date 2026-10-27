package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public final class LogEncrypt {
    private static final int IP_LENGTH_MIN = 7;
    private static final int IP_START_INDEX = 7;
    private static final int MAC_LENGTH_MAX = 17;
    private static final int MAC_START_INDEX = 6;

    public static String IpAddressEncrypt(String str) {
        if (str == null || str.length() < 7 || str.length() == 7) {
            return "*******";
        }
        return "*******" + str.substring(7);
    }

    public static String MacAddressEncrypt(String str) {
        if (str == null || str.length() < 17) {
            return "**************";
        }
        return "******" + str.substring(6);
    }
}
