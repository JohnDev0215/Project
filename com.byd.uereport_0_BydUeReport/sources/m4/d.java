package m4;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import com.byd.systemsettings.system.utils.BydDateUtils;
import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: StringUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static StringBuffer f6376a;

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.length() - Pattern.compile("([一-龥])").matcher(str).replaceAll("").length();
    }

    public static String b(long j8, String str) {
        return j8 == 0 ? "" : k(j8, str);
    }

    @SuppressLint({"MissingPermission"})
    public static String c(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getAddress() == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(f(bluetoothDevice.getName()));
        sb.append(", ");
        return e(sb, bluetoothDevice.getAddress());
    }

    public static String d(String str) {
        return e(new StringBuilder(), str);
    }

    public static String e(StringBuilder sb, String str) {
        if (str == null || sb == null) {
            return "null";
        }
        if (str.length() <= 8) {
            return str;
        }
        int length = str.length() - 8;
        int i8 = 0;
        while (i8 < length) {
            int i9 = i8 + 1;
            String strSubstring = str.substring(i8, i9);
            if (!":".equals(strSubstring)) {
                strSubstring = "*";
            }
            sb.append(strSubstring);
            i8 = i9;
        }
        sb.append(str.substring(length));
        return sb.toString();
    }

    @SuppressLint({"MissingPermission"})
    public static String f(String str) {
        int i8;
        if (str == null) {
            return "";
        }
        String strI = i(str);
        StringBuilder sb = new StringBuilder();
        int length = strI.length();
        int iH = h(strI);
        if (length > iH) {
            int i9 = 0;
            while (true) {
                i8 = length - iH;
                if (i9 >= i8) {
                    break;
                }
                sb.append("*");
                i9++;
            }
            sb.append(strI.substring(i8));
        } else {
            sb.append(strI);
        }
        return sb.toString();
    }

    public static String g(int i8) {
        m();
        String upperCase = Integer.toHexString(i8).toUpperCase(Locale.US);
        f6376a.append("0x");
        if (i8 < 16) {
            f6376a.append("0");
        }
        f6376a.append(upperCase);
        return f6376a.toString();
    }

    public static int h(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        if (length < 2) {
            return 1;
        }
        return length % 2 > 0 ? (length / 2) + 1 : length / 2;
    }

    public static String i(String str) {
        return str == null ? "" : (str.startsWith("\"") || str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    public static String j(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration == null) {
            return " is null";
        }
        BitSet bitSet = wifiConfiguration.allowedKeyManagement;
        return "{ssid: " + f(wifiConfiguration.SSID) + ", bssid: " + d(wifiConfiguration.BSSID) + ", lastConnected: " + a.a(wifiConfiguration, "lastConnected") + ", keyMgmt: " + (bitSet != null ? bitSet.toString() : "null") + "}";
    }

    public static String k(long j8, String str) {
        if (TextUtils.isEmpty(str)) {
            str = BydDateUtils.DEF_FORMAT;
        }
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j8));
    }

    public static int l(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = Pattern.compile("[一-龥]").matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    public static void m() {
        if (f6376a == null) {
            f6376a = new StringBuffer();
        }
        f6376a.setLength(0);
    }

    public static String n(int i8) {
        return ((i8 >> 0) & 255) + "." + ((i8 >> 8) & 255) + "." + ((i8 >> 16) & 255) + "." + ((i8 >> 24) & 255);
    }

    public static boolean o(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals(Pattern.compile("([^a-zA-Z])").matcher(str).replaceAll(""));
    }

    public static boolean p(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals(Pattern.compile("([^0-9])").matcher(str).replaceAll(""));
    }
}
