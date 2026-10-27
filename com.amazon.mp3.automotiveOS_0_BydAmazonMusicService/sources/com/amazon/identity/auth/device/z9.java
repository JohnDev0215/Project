package com.amazon.identity.auth.device;

import amazon.os.Build;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.framework.RemoteMAPException;
import com.amazon.identity.platform.setting.PlatformSettings;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class z9 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f705a = "com.amazon.identity.auth.device.z9";
    public static volatile String b;

    public static boolean a(Context context, String str) {
        return TextUtils.equals(oa.a(context, DeviceAttribute.CentralDeviceType), str);
    }

    public static String b(Context context) {
        if (!IsolatedModeSwitcher.isAppInStaticIsolatedMode(context)) {
            return c(context);
        }
        String strA = ia.a(context, context.getPackageName(), "MAPDeviceType");
        if (TextUtils.isEmpty(strA)) {
            return "A1MPSLFC7L5AFK";
        }
        ga.a(f705a);
        return strA;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:47:0x009a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c1 A[PHI: r4
  0x00c1: PHI (r4v1 java.lang.String) = (r4v0 java.lang.String), (r4v6 java.lang.String) binds: [B:38:0x0084, B:63:0x00c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String c(android.content.Context r7) {
        /*
            Method dump skipped, instruction units count: 344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.z9.c(android.content.Context):java.lang.String");
    }

    public static boolean d(Context context, String str) {
        String strC = c(context, str);
        if (strC == null) {
            return false;
        }
        return a(context, strC);
    }

    public static String e(Context context) {
        PlatformSettings platformSettingsA = PlatformSettings.a(context);
        platformSettingsA.getClass();
        synchronized (qe.class) {
            String str = qe.f519a;
        }
        v5 v5Var = platformSettingsA.f714a;
        Context applicationContext = platformSettingsA.b.getApplicationContext();
        v5Var.getClass();
        synchronized (qe.class) {
        }
        ga.a(v5.b);
        String string = Settings.Global.getString(applicationContext.getContentResolver(), "ro.product.package_name");
        if (TextUtils.isEmpty(string)) {
            string = null;
        }
        if (string == null && qe.f(context)) {
            Log.e(ga.a(f705a), "Central Software Component Id is null.  DCP setting -device-/os_package_name not set for this device.");
        }
        return string;
    }

    public static Map<String, jd> a(Context context) {
        Long l;
        t5 t5VarA = t5.a(context);
        Collection<j5> collectionUnmodifiableCollection = qe.m(((i5) t5VarA.getSystemService("sso_platform")).f382a) ? Collections.unmodifiableCollection(Collections.EMPTY_SET) : MAPApplicationInformationQueryer.a(t5VarA).a();
        HashMap map = new HashMap();
        o5 o5Var = (o5) t5VarA.getSystemService("dcp_device_info");
        String strA = oa.a(t5VarA, DeviceAttribute.CentralDeviceType);
        Long lValueOf = Long.valueOf(o5Var.b());
        String strE = e(context);
        Log.i(ga.a(f705a), String.format("Using the central device type: %s, software version: %s, and software component id: %s", strA, lValueOf, strE));
        map.put(strA, new jd(lValueOf, strE));
        for (j5 j5Var : collectionUnmodifiableCollection) {
            synchronized (j5Var) {
                if (j5Var.i == null) {
                    j5Var.i = la.a(j5Var.f392a, j5Var.b);
                }
                l = j5Var.i;
            }
            String str = j5Var.b;
            try {
                String strC = j5Var.c();
                String strD = j5Var.d();
                String str2 = f705a;
                String.format("The RemoteMapInfo returns softwareComponentId: %s, deviceType: %s, softwareVersion: %s, overrideDSN: %s", str, strC, l, strD);
                ga.a(str2);
                if (TextUtils.isEmpty(strD)) {
                    if (l == null) {
                        String.format("%s is using null software version. Replacing the null with 0.", j5Var.b);
                        ga.a(str2);
                        l = 0L;
                    }
                    if (TextUtils.isEmpty(strC)) {
                        String.format("%s is using null or empty device type. This should be an integration error.", j5Var.b);
                        ga.a(str2);
                    } else if (TextUtils.equals(strC, strA)) {
                        String.format("%s is using central device type.", j5Var.b);
                        ga.a(str2);
                    } else if (map.containsKey(strC)) {
                        Log.i(ga.a(str2), String.format("Multiple apps are using the device type %s. Choosing the app with the greatest software version.", strC));
                        if (((jd) map.get(strC)).f400a.longValue() < l.longValue()) {
                            map.put(strC, new jd(l, str));
                        }
                    } else {
                        map.put(strC, new jd(l, str));
                    }
                } else {
                    String.format("%s is using override DSN. Skipping it.", j5Var.b);
                    ga.a(str2);
                }
            } catch (RemoteMAPException e) {
                Log.w(ga.a(f705a), String.format("Failed to query device type/override DSN for remoteMAPApp Package.Skipping it.Error Message : %s", e.getMessage()));
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            Log.i(ga.a(f705a), String.format("Using the deviceType-softwareVersion,softwareComponentId: %s - %s, %s", entry.getKey(), ((jd) entry.getValue()).f400a, ((jd) entry.getValue()).b));
        }
        return map;
    }

    public static String d(Context context) {
        if (!qe.f(context)) {
            return null;
        }
        if (qe.b()) {
            return Build.VERSION.DEVICE_TYPE_ID;
        }
        String strA = new v5().a("ro.product.config.type");
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        return strA;
    }

    public static String b(Context context, String str) {
        String strC = c(context);
        String strA = ia.a(context, str, "App_Device_Type_For_Central_Device_Type_" + strC);
        if (strA != null) {
            ga.a(f705a, "Package: %s device type: %s picked from metadata (manifest)", str, strA);
            return strA;
        }
        String strA2 = ia.a(context, str, "MAPDeviceType");
        if (strA2 != null) {
            ga.a(f705a, "Package: %s device type: %s picked from metadata (manifest)", str, strA2);
            return strA2;
        }
        ga.a(f705a, "No device type override found for the app %s. Will use the central device type %s", str, strC);
        return strC;
    }

    public static String c(Context context, String str) {
        String str2 = f705a;
        String.format("%s is trying to get device type", str);
        ga.a(str2);
        if (str == null) {
            Log.i(ga.a(str2), "Not specify package name, get central device type.");
            return oa.a(context, DeviceAttribute.CentralDeviceType);
        }
        j5 j5VarA = MAPApplicationInformationQueryer.a(context).a(str);
        if (j5VarA == null) {
            Log.e(ga.a(str2), "Cannot get remote map information even including the calling app itself! This could happen only the calling app is IMP!");
            return oa.a(context, DeviceAttribute.CentralDeviceType);
        }
        try {
            return j5VarA.c();
        } catch (RemoteMAPException e) {
            Log.e(ga.a(f705a), String.format("Failed to get device type for the Package. Error Message: %s", e.getMessage()));
            return null;
        }
    }

    public static String a(Context context, String str, String str2) {
        return a(context, str) ? e(context) : str2;
    }
}
