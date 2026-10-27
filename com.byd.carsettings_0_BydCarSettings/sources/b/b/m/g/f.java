package b.b.m.g;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: StringUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static StringBuffer f2179a;

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return str.length() - Pattern.compile("([一-龥])").matcher(str).replaceAll("").length();
    }

    public static String b(long j, String str) {
        return j == 0 ? "" : j(j, str);
    }

    public static String c(String str) {
        return j(System.currentTimeMillis(), str);
    }

    @SuppressLint({"MissingPermission"})
    public static String d(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || bluetoothDevice.getAddress() == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(g(bluetoothDevice.getName()));
        sb.append(", ");
        return f(sb, bluetoothDevice.getAddress());
    }

    public static String e(String str) {
        return f(new StringBuilder(), str);
    }

    public static String f(StringBuilder sb, String str) {
        if (str == null || sb == null) {
            return "null";
        }
        if (str.length() <= 8) {
            return str;
        }
        int length = str.length() - 8;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            String strSubstring = str.substring(i, i2);
            if (!":".equals(strSubstring)) {
                strSubstring = "*";
            }
            sb.append(strSubstring);
            i = i2;
        }
        sb.append(str.substring(length));
        return sb.toString();
    }

    @SuppressLint({"MissingPermission"})
    public static String g(String str) {
        int i;
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = i(str);
        if (length > i2) {
            int i3 = 0;
            while (true) {
                i = length - i2;
                if (i3 >= i) {
                    break;
                }
                sb.append("*");
                i3++;
            }
            sb.append(str.substring(i));
        } else {
            sb.append(str);
        }
        return sb.toString();
    }

    public static String h(int i) {
        l();
        String upperCase = Integer.toHexString(i).toUpperCase(Locale.US);
        f2179a.append("0x");
        if (i < 16) {
            f2179a.append("0");
        }
        f2179a.append(upperCase);
        return f2179a.toString();
    }

    public static int i(String str) {
        if (TextUtils.isEmpty(str) || str.length() <= 0) {
            return 0;
        }
        int length = str.length();
        if (length < 2) {
            return 1;
        }
        return length % 2 > 0 ? (length / 2) + 1 : length / 2;
    }

    public static String j(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j));
    }

    public static int k(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Matcher matcher = Pattern.compile("[一-龥]").matcher(str);
        if (matcher.find()) {
            return matcher.start();
        }
        return -1;
    }

    public static void l() {
        if (f2179a == null) {
            f2179a = new StringBuffer();
        }
        f2179a.setLength(0);
    }

    public static String m(int i) {
        return ((i >> 0) & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static boolean n(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals(Pattern.compile("([^a-zA-Z])").matcher(str).replaceAll(""));
    }

    public static boolean o(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.equals(Pattern.compile("([^0-9])").matcher(str).replaceAll(""));
    }
}
