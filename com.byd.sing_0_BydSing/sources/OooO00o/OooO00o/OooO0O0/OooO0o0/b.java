package OooO00o.OooO00o.OooO0O0.OooO0o0;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.IBinder;
import android.os.Process;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.t;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        Pattern patternCompile = Pattern.compile(" +(\\d) +(?=\\[\\w+\\s*]:)(?=.*" + str + ")");
        t.a aVarA = t.a("cat /proc/asound/cards", false, true);
        if (aVarA.f2619a != 0 || TextUtils.isEmpty(aVarA.f2620b)) {
            return -1;
        }
        for (String str2 : aVarA.f2620b.trim().split("\r")) {
            Matcher matcher = patternCompile.matcher(str2);
            if (matcher.find()) {
                try {
                    return Integer.parseInt(matcher.group().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public static void b(UsbDevice usbDevice, Context context) {
        try {
            Class<?> cls = Class.forName("android.os.ServiceManager");
            IBinder iBinder = (IBinder) cls.getDeclaredMethod("getService", String.class).invoke(cls, "usb");
            Class<?>[] declaredClasses = Class.forName("android.hardware.usb.IUsbManager").getDeclaredClasses();
            Class<?> cls2 = null;
            int length = declaredClasses.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Class<?> cls3 = declaredClasses[i];
                if ("Stub".equals(cls3.getSimpleName())) {
                    cls2 = cls3;
                    break;
                }
                i++;
            }
            Object objInvoke = cls2.getDeclaredMethod("asInterface", IBinder.class).invoke(cls2, iBinder);
            Class<?> cls4 = objInvoke.getClass();
            Class<?> cls5 = Integer.TYPE;
            cls4.getDeclaredMethod("grantDevicePermission", UsbDevice.class, cls5).invoke(objInvoke, usbDevice, Integer.valueOf(Process.myUid()));
            Class<?> cls6 = Class.forName("android.os.UserHandle");
            objInvoke.getClass().getDeclaredMethod("setDevicePackage", UsbDevice.class, String.class, cls5).invoke(objInvoke, context.getPackageName(), Integer.valueOf(((Integer) cls6.getDeclaredMethod("getUserId", cls5).invoke(cls6, Integer.valueOf(Process.myUid()))).intValue()));
        } catch (Exception unused) {
            OooO00o.OooO00o.OooO0O0.b.f41a.d("OldCommonUtil - old", "ServiceManager Class except");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x004d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(java.lang.String r5) throws java.lang.Throwable {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            if (r2 == 0) goto L40
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L44
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L39 java.io.IOException -> L3b
        L20:
            java.lang.String r1 = r5.readLine()     // Catch: java.io.IOException -> L37 java.lang.Throwable -> L64
            boolean r3 = android.text.TextUtils.isEmpty(r1)     // Catch: java.io.IOException -> L37 java.lang.Throwable -> L64
            if (r3 != 0) goto L2e
            r0.append(r1)     // Catch: java.io.IOException -> L37 java.lang.Throwable -> L64
            goto L20
        L2e:
            r5.close()     // Catch: java.io.IOException -> L32
            goto L57
        L32:
            r5 = move-exception
            r5.printStackTrace()
            goto L57
        L37:
            r1 = move-exception
            goto L48
        L39:
            r0 = move-exception
            goto L66
        L3b:
            r5 = move-exception
            r4 = r1
            r1 = r5
            r5 = r4
            goto L48
        L40:
            return r1
        L41:
            r0 = move-exception
            r2 = r1
            goto L66
        L44:
            r5 = move-exception
            r2 = r1
            r1 = r5
            r5 = r2
        L48:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L64
            if (r5 == 0) goto L55
            r5.close()     // Catch: java.io.IOException -> L51
            goto L55
        L51:
            r5 = move-exception
            r5.printStackTrace()
        L55:
            if (r2 == 0) goto L5f
        L57:
            r2.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r5 = move-exception
            r5.printStackTrace()
        L5f:
            java.lang.String r5 = r0.toString()
            return r5
        L64:
            r0 = move-exception
            r1 = r5
        L66:
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.io.IOException -> L6c
            goto L70
        L6c:
            r5 = move-exception
            r5.printStackTrace()
        L70:
            if (r2 == 0) goto L7a
            r2.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L76:
            r5 = move-exception
            r5.printStackTrace()
        L7a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: OooO00o.OooO00o.OooO0O0.OooO0o0.b.c(java.lang.String):java.lang.String");
    }

    public static String d(String str) {
        return str == null ? BuildConfig.FLAVOR : str.trim();
    }
}
