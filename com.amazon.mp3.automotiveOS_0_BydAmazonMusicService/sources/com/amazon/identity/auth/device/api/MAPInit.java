package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ae;
import com.amazon.identity.auth.device.b7;
import com.amazon.identity.auth.device.c5;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.ha;
import com.amazon.identity.auth.device.i4;
import com.amazon.identity.auth.device.ke;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.s9;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.u4;
import com.amazon.identity.auth.device.v5;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.y3;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.y9;
import com.amazon.identity.auth.device.yd;
import com.amazon.identity.platform.setting.PlatformSettings;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class MAPInit {
    public static boolean c = false;
    public static MAPInit d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f224a;
    public boolean b;

    public MAPInit(Context context) {
        this.f224a = context;
    }

    @FireOsSdk
    public static synchronized MAPInit getInstance(Context context) {
        e.a((Object) context, "context");
        MAPInit mAPInit = d;
        if (mAPInit != null) {
            return mAPInit;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            return new MAPInit(null);
        }
        MAPInit mAPInit2 = new MAPInit(applicationContext);
        d = mAPInit2;
        return mAPInit2;
    }

    public static boolean isRunningInFunctionalTest() {
        return c;
    }

    public static void setIsRunningInFunctionalTest(boolean z) {
        c = z;
    }

    public final void a() {
        Map<String, String> map = EnvironmentUtils.f306a;
        EnvironmentUtils.Environment environment = EnvironmentUtils.Environment.prod;
        EnvironmentUtils.c = new y3();
        Context context = this.f224a;
        ConcurrentHashMap<String, Boolean> concurrentHashMap = y9.f680a;
        y9.b = PlatformSettings.a(context).a("enable.debugging.logs", false).booleanValue();
    }

    @FireOsSdk
    public synchronized void initialize() {
        Context context = this.f224a;
        if (context == null) {
            return;
        }
        if (this.b) {
            return;
        }
        this.b = true;
        ga.f351a = context.getPackageName();
        Locale locale = Locale.ENGLISH;
        boolean z = ha.f;
        Log.i(ga.a("com.amazon.identity.auth.device.api.MAPInit"), String.format(locale, "Initializing MAP (%s) for app %s. MAP release version: %s", s9.a(), this.f224a.getPackageName(), "20220722N"));
        b7.f240a = this.f224a.getApplicationContext();
        SSOMetrics.b = this.f224a.getApplicationContext();
        Context context2 = this.f224a;
        Context context3 = yd.f685a;
        yd.f685a = context2.getApplicationContext();
        y5 y5Var = new y5("MAPInit:initialize");
        final ke keVar = new ke();
        final ke keVar2 = new ke();
        keVar.f411a = System.currentTimeMillis();
        keVar2.f411a = System.currentTimeMillis();
        final de deVarA = y5Var.a(this.f224a, "NecessaryTime");
        final de deVarA2 = y5Var.a(this.f224a, "TotalTime");
        ae aeVarA = ae.a(this.f224a);
        boolean zA = va.a();
        aeVarA.b("MAPInitOnMainThread:" + zA);
        Log.i(ga.a("com.amazon.identity.auth.device.api.MAPInit"), "Running MAPInit on main thread: " + zA);
        va.a(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.1
            @Override // java.lang.Runnable
            public void run() {
                boolean z2;
                PlatformSettings.a(MAPInit.this.f224a);
                qe.a(MAPInit.this.f224a);
                i4.a(MAPInit.this.f224a).a();
                Context context4 = MAPInit.this.f224a;
                String str = u4.e;
                if (!t9.c(context4) || t9.f(context4)) {
                    z2 = true;
                } else {
                    ga.a(u4.e);
                    z2 = false;
                }
                if (z2) {
                    u4.a(MAPInit.this.f224a).b();
                }
                final MAPInit mAPInit = MAPInit.this;
                PlatformSettings platformSettingsA = PlatformSettings.a(mAPInit.f224a);
                Runnable runnable = new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ga.a("com.amazon.identity.auth.device.api.MAPInit");
                        MAPInit.this.a();
                    }
                };
                v5 v5Var = platformSettingsA.f714a;
                v5Var.getClass();
                v5Var.a("addChangeCallback", new Class[]{Runnable.class}, runnable);
                mAPInit.a();
                final MAPInit mAPInit2 = MAPInit.this;
                final de deVar = deVarA2;
                final ke keVar3 = keVar2;
                mAPInit2.getClass();
                va.f637a.execute(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPInit.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            new c5(MAPInit.this.f224a).a();
                        } finally {
                            deVar.c();
                            keVar3.b();
                            me.a aVar = (me.a) yd.a();
                            aVar.f438a = "MAPInit:initialize:TotalTime";
                            aVar.f = Double.valueOf(keVar3.a());
                            aVar.a().a();
                        }
                    }
                });
                deVarA.c();
                keVar.b();
                me.a aVar = (me.a) yd.a();
                aVar.f438a = "MAPInit:initialize:NecessaryTime";
                aVar.f = Double.valueOf(keVar.a());
                aVar.a().a();
            }
        });
    }
}
