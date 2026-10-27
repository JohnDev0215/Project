package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.MetricsFactory;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.ie;
import com.amazon.identity.auth.device.je;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.oe;
import com.amazon.identity.platform.metric.minerva.MinervaOAuthProvider;
import com.amazon.minerva.client.api.AmazonMinervaClientBuilder;
import com.amazon.minerva.client.thirdparty.api.AmazonMinervaAndroidClientBuilder;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.lang.reflect.GenericDeclaration;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class yd {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f685a = null;
    public static ce b = null;
    public static final String c = "yd";
    public static volatile Boolean d;
    public static volatile Boolean e;
    public static volatile Boolean f;
    public static volatile Boolean g;
    public static volatile Boolean h;
    public static volatile Boolean i;
    public static final boolean j = "yes".equals(new v5().a("com.amazon.map.verbose.metrics"));

    public static me.b<?> a() {
        boolean zBooleanValue;
        boolean zBooleanValue2;
        Context context = f685a;
        if (context == null) {
            return new oe.a();
        }
        if (c(context)) {
            return new je.a();
        }
        Boolean bool = i;
        if (bool != null) {
            zBooleanValue2 = bool.booleanValue();
        } else {
            Boolean bool2 = Boolean.FALSE;
            Context context2 = f685a;
            Boolean bool3 = qe.k;
            if (bool3 != null) {
                zBooleanValue = bool3.booleanValue();
            } else {
                PackageManager packageManager = context2.getPackageManager();
                Boolean bool4 = (packageManager != null && packageManager.hasSystemFeature("com.fireos.sdk.minerva") && packageManager.hasSystemFeature("com.fireos.sdk.minerva", 2)) ? Boolean.TRUE : bool2;
                qe.k = bool4;
                zBooleanValue = bool4.booleanValue();
            }
            if (zBooleanValue) {
                try {
                    Class.forName("com.amazon.minerva.client.api.AmazonMinerva");
                    bool2 = Boolean.TRUE;
                    Log.i(ga.a(c), "FireOS Minerva metrics is supported");
                } catch (ClassNotFoundException unused) {
                    Log.w(ga.a(c), "FireOS Minerva metrics not supported");
                }
            } else {
                Log.w(ga.a(c), "FireOS Minerva metrics not supported on this device.");
            }
            i = bool2;
            if (!i.booleanValue() && !xa.a() && qe.f(f685a)) {
                Log.w(ga.a(c), "FireOS Metrics component is not integrated. Please integrate with latest metrics component.");
            }
            zBooleanValue2 = bool2.booleanValue();
        }
        return zBooleanValue2 ? new ie.a() : new oe.a();
    }

    public static void b() {
        GenericDeclaration genericDeclaration;
        Context context = f685a;
        if (context != null) {
            if (c(context)) {
                Context context2 = f685a;
                genericDeclaration = pe.class;
                synchronized (genericDeclaration) {
                    if (pe.f498a == null) {
                        pe.f498a = AmazonMinervaAndroidClientBuilder.standard(context2, "arn:aws:remote-config:us-west-2:188547096058:appConfig:a6odrhg7").withRegion("us-east-1").withDeviceType(z9.b(context2)).withOAuthProvider(new MinervaOAuthProvider()).withChildProfileVerifier(new le()).withUserControlVerifier(new ne()).build();
                    }
                }
            } else {
                if (!c()) {
                    return;
                }
                Context context3 = f685a;
                genericDeclaration = he.class;
                synchronized (genericDeclaration) {
                    if (he.f373a == null) {
                        he.f373a = AmazonMinervaClientBuilder.standard(context3).withRegion("us-east-1").build();
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0023 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean c(android.content.Context r4) {
        /*
            java.lang.Boolean r0 = com.amazon.identity.auth.device.yd.h
            if (r0 == 0) goto L9
            boolean r4 = r0.booleanValue()
            return r4
        L9:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r1 = com.amazon.identity.auth.device.qe.a()     // Catch: java.lang.ClassNotFoundException -> L31
            if (r1 == 0) goto L23
            java.lang.String r1 = r4.getPackageName()     // Catch: java.lang.ClassNotFoundException -> L31
            java.lang.String r2 = "MinervaForceThirdParty"
            r3 = 0
            java.lang.Boolean r1 = com.amazon.identity.auth.device.ia.a(r4, r1, r2, r3)     // Catch: java.lang.ClassNotFoundException -> L31
            boolean r1 = r1.booleanValue()     // Catch: java.lang.ClassNotFoundException -> L31
            if (r1 != 0) goto L23
            goto L3c
        L23:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch: java.lang.ClassNotFoundException -> L3c
            java.lang.String r1 = com.amazon.identity.auth.device.yd.c     // Catch: java.lang.ClassNotFoundException -> L3c
            java.lang.String r2 = "ThirdParty Minerva metrics is supported"
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)     // Catch: java.lang.ClassNotFoundException -> L3c
            android.util.Log.i(r1, r2)     // Catch: java.lang.ClassNotFoundException -> L3c
            goto L3c
        L31:
            java.lang.String r1 = com.amazon.identity.auth.device.yd.c
            java.lang.String r1 = com.amazon.identity.auth.device.ga.a(r1)
            java.lang.String r2 = "ThirdParty DCP metrics not supported"
            android.util.Log.w(r1, r2)
        L3c:
            com.amazon.identity.auth.device.yd.h = r0
            java.lang.Boolean r1 = com.amazon.identity.auth.device.yd.h
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L5d
            boolean r1 = com.amazon.identity.auth.device.xa.a()
            if (r1 != 0) goto L5d
            boolean r4 = com.amazon.identity.auth.device.qe.f(r4)
            if (r4 != 0) goto L5d
            java.lang.String r4 = com.amazon.identity.auth.device.yd.c
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)
            java.lang.String r1 = "ThirdParty Metrics component is not integrated. Please integrate with latest metrics component."
            android.util.Log.w(r4, r1)
        L5d:
            boolean r4 = r0.booleanValue()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.yd.c(android.content.Context):boolean");
    }

    public static boolean d(Context context) {
        Boolean bool = f;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        if (b(context)) {
            try {
                PeriodicMetricReporter.class.getMethod("startRecordingPeriodically", Long.TYPE, TimeUnit.class);
                PeriodicMetricReporter.class.getMethod("getMetricEvent", new Class[0]);
                bool2 = Boolean.TRUE;
                Log.i(ga.a(c), "ThirdParty Periodic metrics is supported");
            } catch (ClassNotFoundException unused) {
                Log.w(ga.a(c), "ThirdParty Periodic metrics not supported");
            } catch (NoSuchMethodException unused2) {
                Log.w(ga.a(c), "ThirdParty Periodic metrics library is too old");
            }
        }
        f = bool2;
        return bool2.booleanValue();
    }

    public static de b(String str, String str2) {
        if (j) {
            return a(str, str2);
        }
        return new de.b();
    }

    public static boolean d() {
        Boolean bool = g;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        if (c()) {
            try {
                Class<?> cls = Class.forName("com.amazon.client.metrics.PeriodicMetricReporter");
                Class.forName("com.amazon.client.metrics.PeriodicMetricReporterImpl");
                cls.getMethod("startRecordingPeriodically", Long.TYPE, TimeUnit.class);
                cls.getMethod("getMetricEvent", new Class[0]);
                Boolean bool3 = Boolean.TRUE;
                Log.i(ga.a(c), "FireOS Periodic metrics is supported");
                bool2 = bool3;
            } catch (ClassNotFoundException unused) {
                Log.w(ga.a(c), "FireOS Periodic metrics not supported");
                return false;
            } catch (NoSuchMethodException unused2) {
                Log.w(ga.a(c), "FireOS Periodic metrics library is too old");
                return false;
            }
        }
        g = bool2;
        return g.booleanValue();
    }

    public static boolean c() {
        Boolean bool = e;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            Class.forName("com.amazon.client.metrics.AndroidMetricsFactoryImpl");
            Class.forName("com.amazon.client.metrics.MetricsFactory").getMethod("createConcurrentMetricEvent", String.class, String.class);
            bool2 = Boolean.TRUE;
            Log.i(ga.a(c), "FireOS DCP metrics is supported");
        } catch (ClassNotFoundException unused) {
            Log.w(ga.a(c), "FireOS DCP metrics not supported");
        } catch (NoSuchMethodException unused2) {
            Log.w(ga.a(c), "FireOS DCP metrics is too old");
        }
        e = bool2;
        if (!e.booleanValue() && !xa.a() && qe.f(f685a)) {
            Log.w(ga.a(c), "FireOS Metrics component is not integrated. Please integrate with latest metrics component.");
        }
        return bool2.booleanValue();
    }

    public static void b(String str, String... strArr) {
        ce ceVarA = a(f685a);
        ceVarA.a(str, strArr);
        ceVarA.a();
    }

    public static boolean b(Context context) {
        Boolean bool = d;
        if (bool != null) {
            return bool.booleanValue();
        }
        Boolean bool2 = Boolean.FALSE;
        try {
            MetricsFactory.class.getMethod("createConcurrentMetricEvent", String.class, String.class);
            if (!qe.a() || ia.a(context, context.getPackageName(), "DCMForceThirdParty", false).booleanValue()) {
                bool2 = Boolean.TRUE;
                Log.i(ga.a(c), "ThirdParty DCP metrics is supported");
            }
        } catch (ClassNotFoundException unused) {
            Log.w(ga.a(c), "ThirdParty DCP metrics not supported");
        } catch (NoSuchMethodException unused2) {
            Log.w(ga.a(c), "ThirdParty DCP metrics is too old");
        }
        d = bool2;
        if (!d.booleanValue() && !xa.a() && !qe.f(context)) {
            Log.w(ga.a(c), "ThirdParty Metrics component is not integrated. Please integrate with latest metrics component.");
        }
        return bool2.booleanValue();
    }

    public static synchronized ce a(Context context) {
        ce ceVar;
        ce ceVarA;
        if (context == null) {
            Log.e(ga.a(c), "MetricsHelper has not been init yet. MAP will not be able to emit metrics");
            return new be(null);
        }
        String packageName = context.getPackageName();
        synchronized (yd.class) {
            ceVar = b;
            if (!(ceVar instanceof fe) && !(ceVar instanceof ud)) {
                if (xa.a()) {
                    Log.i(ga.a(c), "Running in unit test, creating logging metrics collector");
                    ceVarA = new be("UnitTest");
                } else {
                    ceVarA = a(context, packageName, "MAPClientLib");
                }
                ceVar = ceVarA;
                b = ceVar;
            }
        }
        return ceVar;
    }

    public static de a(String str, String str2) {
        de bVar;
        String str3 = str + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str2;
        ce ceVarA = a(f685a);
        if (ceVarA != null) {
            bVar = ceVarA.b(str3);
        } else {
            bVar = new de.b();
        }
        bVar.b();
        return bVar;
    }

    @Deprecated
    public static void a(String str, String... strArr) {
        a(f685a).a(str, strArr);
    }

    public static Callback a(y5 y5Var, de deVar, Callback callback) {
        return a(y5Var, deVar, callback, null, false);
    }

    public static Callback a(y5 y5Var, de deVar, Callback callback, t5 t5Var, boolean z) {
        return new xd(deVar, y5Var, z, callback, null, t5Var, "com.amazon.dcp.sso.ErrorCode", MAPAccountManager.RegistrationError.UNRECOGNIZED);
    }

    public static synchronized ce a(String str) {
        Context context = f685a;
        if (context == null) {
            Log.e(ga.a(c), "MetricsHelper has not been init yet. MAP will not be able to emit metrics");
            return new be(null);
        }
        return a(f685a, context.getPackageName(), str);
    }

    public static synchronized ce a(Context context, String str, String str2) {
        if (context != null) {
            if (b(context)) {
                try {
                    Log.i(ga.a(c), "Using the ThirdPartyPlatformDCPMetricsCollector");
                    return new fe(context, str, str2);
                } catch (Throwable th) {
                    Log.e(ga.a(c), "Error initializing ThirdPartyPlatformDCPMetricsCollector", th);
                }
            } else if (c()) {
                try {
                    Log.i(ga.a(c), "Using the FireOSPlatformDCPMetricsCollector");
                    return new ud(context, str, str2);
                } catch (Throwable th2) {
                    Log.e(ga.a(c), "Error initializing FireOSPlatformDCPMetricsCollector", th2);
                }
            }
        }
        Log.i(ga.a(c), "Using the PlatformLoggingMetricsCollector");
        return new be(null);
    }

    public static Callback a(Callback callback, y5 y5Var, String str, MAPAccountManager.RegistrationError registrationError) {
        return new xd(null, y5Var, true, callback, null, null, str, registrationError);
    }
}
