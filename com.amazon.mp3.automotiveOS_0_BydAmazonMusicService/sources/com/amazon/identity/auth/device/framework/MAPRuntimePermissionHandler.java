package com.amazon.identity.auth.device.framework;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.la;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.y5;
import com.google.android.gms.common.GooglePlayServicesUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPRuntimePermissionHandler {
    public static final ConcurrentHashMap<Integer, MAPRuntimePermissionHandler> e = new ConcurrentHashMap<>();
    public static final Map<String, PermissionAction> f;
    public static final String g;
    public static Integer h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Integer f324a;
    public final String b;
    public final String c;
    public final String[] d;

    /* JADX INFO: compiled from: DCP */
    public enum PermissionAction {
        ACTION_READ_MOBILE_NUMBER(1, new String[]{"android.permission.READ_PHONE_STATE"});

        public final String[] mPermissions;
        public final int mRequestId;

        PermissionAction(int i, String[] strArr) {
            this.mRequestId = i;
            this.mPermissions = strArr;
        }
    }

    static {
        HashMap map = new HashMap();
        f = map;
        map.put("read_mobile_number", PermissionAction.ACTION_READ_MOBILE_NUMBER);
        g = MAPRuntimePermissionHandler.class.getName();
    }

    public MAPRuntimePermissionHandler(String str, String str2) {
        PermissionAction permissionAction = f.get(str);
        this.f324a = Integer.valueOf(permissionAction.mRequestId);
        this.d = permissionAction.mPermissions;
        this.c = str2;
        this.b = str;
    }

    public static boolean a(Context context) {
        try {
            if (!qe.m(context)) {
                return false;
            }
            Class.forName("com.google.android.gms.auth.api.credentials.HintRequest");
            int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            ga.a(g);
            return iIsGooglePlayServicesAvailable == 0 || iIsGooglePlayServicesAvailable == 2;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            Log.w(ga.a(g), "error on play service check", th);
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            if (!qe.m(context)) {
                return false;
            }
            Class.forName("com.google.android.gms.auth.api.phone.SmsRetriever");
            int iIsGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
            String str = g;
            ga.a(str);
            if (iIsGooglePlayServicesAvailable != 0 && iIsGooglePlayServicesAvailable != 2) {
                return false;
            }
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            int i = packageInfo.versionCode;
            ga.a(str);
            return ((long) packageInfo.versionCode) >= 10200000;
        } catch (ClassNotFoundException unused) {
            return false;
        } catch (Throwable th) {
            Log.w(ga.a(g), "error on play service check", th);
            return false;
        }
    }

    public static boolean a(Context context, y5 y5Var) {
        ApplicationInfo applicationInfo;
        if (h == null) {
            try {
                applicationInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo;
            } catch (PackageManager.NameNotFoundException e2) {
                Log.e(ga.a(la.f421a), String.format("Could not find package. Error Message : %s", e2.getMessage()));
            }
            Integer numValueOf = applicationInfo != null ? Integer.valueOf(applicationInfo.targetSdkVersion) : null;
            h = numValueOf;
        }
        Integer num = h;
        if (num == null) {
            Log.w(ga.a(g), "Unable to determine target SDK version. Will not show permission dialog.");
            y5Var.b("MAPRuntimePermissionError:CannotGetBuildTargetVersion");
        } else {
            String str = g;
            ga.a(str);
            if (num.intValue() >= 23) {
                return true;
            }
            Log.w(ga.a(str), "The app build target sdk version is below 23. Runtime permission is not needed.");
        }
        return false;
    }
}
