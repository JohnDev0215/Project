package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public final class LogEncryption {
    private static final int IP_LENGTH_MIN = 7;
    private static final int IP_START_INDEX = 7;
    private static final int MAC_LENGTH_MAX = 17;
    private static final int MAC_START_INDEX = 6;

    public static String MacAddressEncrypt(String str) {
        if (str == null || str.length() < 17) {
            return "**************";
        }
        return "******" + str.substring(6);
    }

    public static String IpAddressEncrypt(String str) {
        if (str == null || str.length() < 7 || str.length() == 7) {
            return "*******";
        }
        return "*******" + str.substring(7);
    }

    public static String maskString(String str, int frontNum, int subsequentNum) {
        if (str == null) {
            return null;
        }
        if (str.length() <= frontNum + subsequentNum) {
            return "***";
        }
        int maskLength = (str.length() - frontNum) - subsequentNum;
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, frontNum));
        for (int i = 0; i < maskLength; i++) {
            sb.append('*');
        }
        int i2 = frontNum + maskLength;
        sb.append(str.substring(i2));
        return sb.toString();
    }

    public static String maskLong(long value) {
        String strValue = String.valueOf(value);
        if (strValue.length() <= 2) {
            return strValue;
        }
        StringBuilder sb = new StringBuilder(strValue.length());
        sb.append(strValue.substring(0, 1));
        for (int i = 0; i < strValue.length() - 2; i++) {
            sb.append('*');
        }
        sb.append(strValue.substring(strValue.length() - 1));
        return sb.toString();
    }

    public static String maskInt(int value) {
        String strValue = String.valueOf(value);
        if (strValue.length() <= 2) {
            return strValue;
        }
        StringBuilder sb = new StringBuilder(strValue.length());
        sb.append(strValue.substring(0, 1));
        for (int i = 0; i < strValue.length() - 2; i++) {
            sb.append('*');
        }
        sb.append(strValue.substring(strValue.length() - 1));
        return sb.toString();
    }
}
