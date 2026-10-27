package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.compose.runtime.CompositionImpl$$ExternalSyntheticBackportWithForwarding0;
import com.amazon.device.information.contract.DeviceInformationContract;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.t9;
import com.google.android.gms.fido.Fido;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class qe {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f519a = "com.amazon.identity.auth.device.qe";
    public static final Set<String> b;
    public static final Set<String> c;
    public static final AtomicReference<Boolean> d;
    public static ConcurrentHashMap<Uri, Boolean> e;
    public static ConcurrentHashMap<String, Boolean> f;
    public static volatile Boolean g;
    public static volatile Boolean h;
    public static volatile Boolean i;
    public static volatile Boolean j;
    public static volatile Boolean k;
    public static volatile Boolean l;
    public static volatile Boolean m;
    public static final Object n;

    static {
        HashSet hashSet = new HashSet();
        b = hashSet;
        hashSet.addAll(Arrays.asList("com.amazon.dcp", "com.amazon.sso", "com.amazon.canary", "com.amazon.fv"));
        HashSet hashSet2 = new HashSet();
        c = hashSet2;
        hashSet2.addAll(Arrays.asList("com.amazon.imp"));
        d = new AtomicReference<>(null);
        e = new ConcurrentHashMap<>();
        f = new ConcurrentHashMap<>();
        g = null;
        h = null;
        i = null;
        j = null;
        k = null;
        l = null;
        m = null;
        n = new Object();
    }

    public static boolean a() {
        if (!Build.MANUFACTURER.equalsIgnoreCase("Amazon")) {
            try {
                Class.forName("com.amazon.acos.util.AmazonBuild");
                ga.a(f519a);
            } catch (ClassNotFoundException unused) {
                ga.a(f519a);
                return false;
            }
        }
        return true;
    }

    public static boolean b(Context context) {
        return f(context);
    }

    public static String c(Context context) {
        String strE = ((o5) t5.a(context).getSystemService("dcp_device_info")).e();
        ga.a(f519a);
        return strE;
    }

    public static boolean d(Context context) {
        return a(context, DeviceInformationContract.AUTHORITY_URI);
    }

    public static boolean e(Context context) {
        return t9.a(context, false) != null;
    }

    public static boolean f(Context context) {
        AtomicReference<Boolean> atomicReference = d;
        if (atomicReference.get() == null) {
            CompositionImpl$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, Boolean.valueOf(a(t5.a(context)) ? false : a()));
        }
        return atomicReference.get().booleanValue();
    }

    public static boolean g(Context context) {
        return f(context) && j(context);
    }

    public static boolean h(Context context) {
        return !f(context) && t9.c(context);
    }

    @Deprecated
    public static boolean i(Context context) {
        String strA = ra.a(context);
        return strA != null && strA.startsWith("D01E") && Build.MODEL.toLowerCase(Locale.US).equals("kindle fire");
    }

    public static boolean j(Context context) {
        if (t9.b(context) == null) {
            return false;
        }
        return !b.contains(r1.f583a);
    }

    public static boolean k(Context context) {
        t9.a aVarB = t9.b(context);
        if (aVarB == null) {
            return false;
        }
        return b.contains(aVarB.f583a);
    }

    public static boolean l(Context context) {
        if (t9.a(context).f261a == null) {
            return false;
        }
        return !b.contains(r1.f583a);
    }

    public static boolean m(Context context) {
        return !t9.c(context);
    }

    @SuppressLint({"NewApi"})
    public static boolean n(Context context) {
        Boolean bool = j;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service") && packageManager.hasSystemFeature("com.fireos.sdk.amazon_profile_service", 4)) {
            bool2 = Boolean.TRUE;
        }
        j = bool2;
        return bool2.booleanValue();
    }

    public static boolean b() {
        Boolean bool = i;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class.forName("amazon.os.Build.VERSION");
            bool2 = Boolean.TRUE;
            ga.a(f519a);
        } catch (ClassNotFoundException unused) {
            ga.a(f519a);
        }
        i = bool2;
        return bool2.booleanValue();
    }

    public static boolean c() {
        Boolean bool = h;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class<?> cls = Class.forName("com.amazon.android.os.MultipleProfileHelper");
            cls.getMethod("myProfileId", new Class[0]);
            cls.getMethod("getCallingProfileId", new Class[0]);
            cls.getMethod("getForegroundProfileId", new Class[0]);
            Class<?> cls2 = Integer.TYPE;
            cls.getMethod("isSameApp", cls2, cls2);
            bool2 = Boolean.TRUE;
            ga.a(f519a);
        } catch (ClassNotFoundException unused) {
            ga.a(f519a);
        } catch (NoSuchMethodException unused2) {
            ga.a(f519a);
        }
        h = bool2;
        return bool2.booleanValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(android.content.Context r4, android.net.Uri r5) {
        /*
            java.util.concurrent.ConcurrentHashMap<android.net.Uri, java.lang.Boolean> r0 = com.amazon.identity.auth.device.qe.e
            java.lang.Object r0 = r0.get(r5)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            if (r0 != 0) goto L4f
            r0 = 0
            if (r5 != 0) goto Le
            goto L3b
        Le:
            com.amazon.identity.auth.device.z5 r1 = new com.amazon.identity.auth.device.z5
            r1.<init>(r4, r0)
            android.content.pm.PackageManager r4 = r1.b
            android.content.pm.ProviderInfo r4 = com.amazon.identity.auth.device.z5.a(r5, r0, r4)
            r2 = 0
            if (r4 != 0) goto L1e
        L1c:
            r4 = r2
            goto L38
        L1e:
            java.lang.String r3 = r4.packageName
            boolean r1 = r1.b(r3)
            if (r1 != 0) goto L38
            java.lang.String r4 = com.amazon.identity.auth.device.z5.f
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r3 = "Package does not qualify as a trusted package."
            java.lang.String r1 = java.lang.String.format(r3, r1)
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)
            android.util.Log.e(r4, r1)
            goto L1c
        L38:
            if (r4 == 0) goto L3b
            r0 = 1
        L3b:
            java.util.concurrent.ConcurrentHashMap<android.net.Uri, java.lang.Boolean> r4 = com.amazon.identity.auth.device.qe.e
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
            java.lang.Object r4 = r4.putIfAbsent(r5, r1)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            if (r4 != 0) goto L4e
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L4f
        L4e:
            r0 = r4
        L4f:
            boolean r4 = r0.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.qe.a(android.content.Context, android.net.Uri):boolean");
    }

    public static boolean a(t5 t5Var) {
        return t5Var.b().a(Feature.IsolateApplication);
    }

    public static synchronized boolean a(Context context) {
        Boolean bool;
        Boolean bool2 = m;
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        try {
            Class.forName("com.google.android.gms.fido.fido2.Fido2ApiClient");
            if (Fido.getFido2ApiClient(context) == null) {
                Log.i(ga.a(f519a), "Could not initialize FidoAuthenticatorJSBridge because Fido client could not be initialized");
                yd.b("FidoClientInitialization:Error", new String[0]);
                bool = Boolean.FALSE;
            } else {
                yd.b("FidoClientInitialization:Success", new String[0]);
                bool = Boolean.TRUE;
            }
        } catch (Exception unused) {
            Log.i(ga.a(f519a), "Could not initialize FidoAuthenticatorJSBridge because Fido client could not be initialized");
            yd.b("FidoClientInitialization:Error", new String[0]);
            bool = Boolean.FALSE;
        }
        m = bool;
        return bool.booleanValue();
    }
}
