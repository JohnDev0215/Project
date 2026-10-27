package com.byd.bluetoothcall.f;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.media.AudioManager;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f3280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f3281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final ZoneId f3282c = ZoneId.systemDefault();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3283d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f3284e = 2;

    private static String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
            Log.d("DANDAN", "info == " + packageInfo);
        } catch (Exception unused) {
        }
        if (packageInfo != null) {
            Log.d("DANDAN", "应用已安装");
            return true;
        }
        Log.d("DANDAN", "应用未安装");
        return false;
    }

    public static void c(Context context, int i) {
        if (context != null && m(context) && t(context) && c.a.b.b.f0().n0() == 2) {
            ((AudioManager) context.getSystemService(AudioManager.class)).playSoundEffect(i);
        }
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        int i = 0;
        while (i < sb.length() && i < sb.length() - 4) {
            int i2 = i + 1;
            sb = sb.replace(i, i2, "*");
            i = i2;
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.text.SpannableStringBuilder e(int r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.String r0 = r7.toLowerCase()
            java.lang.String r1 = r8.toLowerCase()
            boolean r0 = r0.contains(r1)
            r1 = -1
            if (r0 == 0) goto L54
            boolean r0 = android.text.TextUtils.isEmpty(r8)
            if (r0 != 0) goto L54
            int r0 = r7.indexOf(r8)     // Catch: java.lang.Exception -> L34
            if (r0 < 0) goto L25
            int r8 = r8.length()     // Catch: java.lang.Exception -> L21
            int r8 = r8 + r0
            goto L26
        L21:
            r8 = move-exception
            r2 = r0
            r0 = r1
            goto L37
        L25:
            r8 = r1
        L26:
            int r2 = r7.length()     // Catch: java.lang.Exception -> L2e
            if (r8 <= r2) goto L56
            r8 = r1
            goto L56
        L2e:
            r2 = move-exception
            r5 = r0
            r0 = r8
            r8 = r2
            r2 = r5
            goto L37
        L34:
            r8 = move-exception
            r0 = r1
            r2 = r0
        L37:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "e = "
            r3.append(r4)
            java.lang.String r8 = r8.toString()
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            java.lang.String r3 = "DANDAN"
            android.util.Log.d(r3, r8)
            r8 = r0
            r0 = r2
            goto L56
        L54:
            r8 = r1
            r0 = r8
        L56:
            android.text.SpannableStringBuilder r2 = new android.text.SpannableStringBuilder
            r2.<init>(r7)
            if (r0 == r1) goto L9b
            if (r8 == r1) goto L9b
            android.text.style.ForegroundColorSpan r1 = new android.text.style.ForegroundColorSpan
            r1.<init>(r6)
            r6 = 33
            r2.setSpan(r1, r0, r8, r6)
            java.lang.String r6 = com.byd.bluetoothcall.f.e.a(r7)
            java.lang.String r7 = r2.toString()
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L78
            goto L9b
        L78:
            r7 = 0
        L79:
            int r8 = r6.length()
            if (r7 >= r8) goto L9b
            char r8 = r6.charAt(r7)
            boolean r8 = p(r8)
            if (r8 != 0) goto L98
            int r8 = r7 + 1
            int r0 = r6.length()
            if (r8 >= r0) goto L98
            java.lang.String r8 = r6.substring(r7, r8)
            r2.insert(r7, r8)
        L98:
            int r7 = r7 + 1
            goto L79
        L9b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.bluetoothcall.f.f.e(int, java.lang.String, java.lang.String):android.text.SpannableStringBuilder");
    }

    public static String f(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (i <= 0 || str.length() <= i) {
            return str;
        }
        try {
            return str.substring(str.offsetByCodePoints(0, 0), str.offsetByCodePoints(0, i));
        } catch (Exception unused) {
            return "";
        }
    }

    public static int g(Context context) {
        if (!q()) {
            return f3283d;
        }
        if (s(context)) {
            return 0;
        }
        return f3284e;
    }

    public static String h(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null || (runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1)) == null || runningTasks.size() <= 0) {
            return null;
        }
        return runningTasks.get(0).topActivity.getPackageName();
    }

    public static String i(String str, String str2) {
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "unknown");
            } catch (Exception e2) {
                e2.printStackTrace();
                return str2;
            }
        } catch (Throwable unused) {
            return str2;
        }
    }

    public static String j(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            return a(messageDigest.digest());
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return "";
        }
    }

    public static int k() {
        return Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.steering.wheel.position", "0")).intValue();
    }

    public static int l(Context context) {
        return a.a.a.a(context).b();
    }

    public static boolean m(Context context) {
        return (context == null || Settings.System.getInt(context.getContentResolver(), "byd_bluetooth_dial", 1) == 0) ? false : true;
    }

    public static boolean n(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z = Math.abs(jCurrentTimeMillis - f3280a) < ((long) i);
        f3280a = jCurrentTimeMillis;
        return z;
    }

    public static boolean o(int i) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - f3281b) < i) {
            return true;
        }
        f3281b = jCurrentTimeMillis;
        return false;
    }

    public static boolean p(int i) {
        return (i >= 48 && i <= 57) || i == 43;
    }

    public static boolean q() {
        String str = SystemProperties.get("persist.byd.privacy.policy", String.valueOf(0));
        BydLogHelper.logD("PrivacyPolicy status:" + str);
        return TextUtils.equals(str, "1");
    }

    public static boolean r() {
        return b.g.i.f.b(Locale.getDefault()) == 1;
    }

    public static boolean s(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), "sys.byd.safety.warn", 0);
        BydLogHelper.logD("SafetyWarn status:" + i);
        return i == 1;
    }

    public static boolean t(Context context) {
        return (context == null || Settings.System.getInt(context.getContentResolver(), "sound_effects_enabled", 1) == 0) ? false : true;
    }

    public static boolean u(Context context) {
        return TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24));
    }

    public static String v(int i) {
        int i2 = i / 1000;
        int i3 = i2 / 3600;
        int i4 = (i2 % 3600) / 60;
        int i5 = i2 % 60;
        String strValueOf = String.valueOf(i3);
        if (i3 < 10) {
            strValueOf = "0" + strValueOf;
        }
        String strValueOf2 = String.valueOf(i4);
        if (i4 < 10) {
            strValueOf2 = "0" + strValueOf2;
        }
        String strValueOf3 = String.valueOf(i5);
        if (i5 < 10) {
            strValueOf3 = "0" + strValueOf3;
        }
        if (i3 == 0) {
            return strValueOf2 + ":" + strValueOf3;
        }
        return strValueOf + ":" + strValueOf2 + ":" + strValueOf3;
    }
}
