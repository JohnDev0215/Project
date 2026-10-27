package OooO00o.OooO00o.OooO0Oo.OooO0OO.d;

import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.d;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.micphone.utils.SysUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class e {
    public static String a() {
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address").getInputStream()));
            String line = BuildConfig.FLAVOR;
            while (line != null) {
                line = lineNumberReader.readLine();
                if (line != null) {
                    return line.trim();
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(Context context) {
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String strC = b.c("sys/class/net/eth0/address");
        if (TextUtils.isEmpty(strC)) {
            strC = b.c("sys/class/net/eth0/wlan0");
        }
        if (TextUtils.isEmpty(strC) && (wifiManager = (WifiManager) context.getSystemService("wifi")) != null && (connectionInfo = wifiManager.getConnectionInfo()) != null) {
            strC = connectionInfo.getMacAddress();
        }
        if (TextUtils.isEmpty(strC)) {
            strC = c();
        }
        return strC.trim();
    }

    public static String c() {
        d.a aVarA = d.a("busybox ifconfig", false);
        if (aVarA.f91a != 0) {
            return BuildConfig.FLAVOR;
        }
        String str = aVarA.f92b;
        if (str.length() <= 0 || !str.contains("HWaddr")) {
            return BuildConfig.FLAVOR;
        }
        try {
            return str.substring(str.indexOf("HWaddr") + 7, str.indexOf("HWaddr") + 24);
        } catch (Exception unused) {
            return BuildConfig.FLAVOR;
        }
    }

    public static String d(Context context) {
        int i = Build.VERSION.SDK_INT;
        return i < 23 ? f(context) : i < 24 ? a() : e();
    }

    public static String e() {
        ArrayList list;
        int i;
        try {
            list = Collections.list(NetworkInterface.getNetworkInterfaces());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (i = 0; i < list.size(); i++) {
            NetworkInterface networkInterface = (NetworkInterface) list.get(i);
            if (networkInterface.getName().toLowerCase(Locale.ROOT).equals(SysUtils.INTERFACE_NAME_WLAN)) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress == null) {
                    return BuildConfig.FLAVOR;
                }
                StringBuilder sb = new StringBuilder();
                for (byte b2 : hardwareAddress) {
                    sb.append(String.format("%02X:", Byte.valueOf(b2)));
                }
                if (!TextUtils.isEmpty(sb)) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                return sb.toString();
            }
            return BuildConfig.FLAVOR;
        }
        return BuildConfig.FLAVOR;
    }

    public static String f(Context context) {
        WifiInfo connectionInfo;
        if (context == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            connectionInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
        } catch (Exception e) {
            e.printStackTrace();
            connectionInfo = null;
        }
        if (connectionInfo == null) {
            return null;
        }
        String macAddress = connectionInfo.getMacAddress();
        return !TextUtils.isEmpty(macAddress) ? macAddress.toUpperCase(Locale.ENGLISH) : macAddress;
    }
}
