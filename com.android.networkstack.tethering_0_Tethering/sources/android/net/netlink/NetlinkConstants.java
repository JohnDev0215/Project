package android.net.netlink;

import android.system.OsConstants;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class NetlinkConstants {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static final int alignedLengthOf(short s) {
        return alignedLengthOf(s & 65535);
    }

    public static final int alignedLengthOf(int i) {
        if (i <= 0) {
            return 0;
        }
        return (((i + 4) - 1) / 4) * 4;
    }

    public static String stringForAddressFamily(int i) {
        return i == OsConstants.AF_INET ? "AF_INET" : i == OsConstants.AF_INET6 ? "AF_INET6" : i == OsConstants.AF_NETLINK ? "AF_NETLINK" : String.valueOf(i);
    }

    public static String hexify(byte[] bArr) {
        return bArr == null ? "(null)" : toHexString(bArr, 0, bArr.length);
    }

    public static String hexify(ByteBuffer byteBuffer) {
        return byteBuffer == null ? "(null)" : toHexString(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining());
    }

    public static String stringForNlMsgType(short s) {
        if (s == 1) {
            return "NLMSG_NOOP";
        }
        if (s == 2) {
            return "NLMSG_ERROR";
        }
        if (s == 3) {
            return "NLMSG_DONE";
        }
        if (s == 4) {
            return "NLMSG_OVERRUN";
        }
        if (s == 68) {
            return "RTM_NEWNDUSEROPT";
        }
        switch (s) {
            case 16:
                return "RTM_NEWLINK";
            case 17:
                return "RTM_DELLINK";
            case 18:
                return "RTM_GETLINK";
            case 19:
                return "RTM_SETLINK";
            case 20:
                return "RTM_NEWADDR";
            case 21:
                return "RTM_DELADDR";
            case 22:
                return "RTM_GETADDR";
            default:
                switch (s) {
                    case 24:
                        return "RTM_NEWROUTE";
                    case 25:
                        return "RTM_DELROUTE";
                    case 26:
                        return "RTM_GETROUTE";
                    default:
                        switch (s) {
                            case 28:
                                return "RTM_NEWNEIGH";
                            case 29:
                                return "RTM_DELNEIGH";
                            case 30:
                                return "RTM_GETNEIGH";
                            default:
                                switch (s) {
                                    case 32:
                                        return "RTM_NEWRULE";
                                    case 33:
                                        return "RTM_DELRULE";
                                    case 34:
                                        return "RTM_GETRULE";
                                    default:
                                        return "unknown RTM type: " + String.valueOf((int) s);
                                }
                        }
                }
        }
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        char[] cArr = new char[i2 * 2];
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            byte b = bArr[i4];
            int i5 = i3 + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i3] = cArr2[(b >>> 4) & 15];
            i3 = i5 + 1;
            cArr[i5] = cArr2[b & 15];
        }
        return new String(cArr);
    }
}
