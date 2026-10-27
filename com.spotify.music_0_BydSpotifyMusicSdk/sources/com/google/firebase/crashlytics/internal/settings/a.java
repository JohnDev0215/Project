package com.google.firebase.crashlytics.internal.settings;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;
import p.b33;
import p.bj2;
import p.cc2;
import p.ci1;
import p.es2;
import p.hc2;
import p.jo1;
import p.kk0;
import p.ks2;
import p.x50;
import p.yn;
import p.z90;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final Context a;
    public final jo1 b;
    public final hc2 c;
    public final yn d;
    public final hc2 e;
    public final kk0 f;
    public final x50 g;
    public final AtomicReference h;
    public final AtomicReference i;

    public a(Context context, jo1 jo1Var, yn ynVar, hc2 hc2Var, hc2 hc2Var2, kk0 kk0Var, x50 x50Var) {
        AtomicReference atomicReference = new AtomicReference();
        this.h = atomicReference;
        this.i = new AtomicReference(new bj2());
        this.a = context;
        this.b = jo1Var;
        this.d = ynVar;
        this.c = hc2Var;
        this.e = hc2Var2;
        this.f = kk0Var;
        this.g = x50Var;
        atomicReference.set(z90.l(ynVar));
    }

    public final cc2 a(SettingsCacheBehavior settingsCacheBehavior) {
        cc2 cc2Var = null;
        try {
            if (!SettingsCacheBehavior.e.equals(settingsCacheBehavior)) {
                JSONObject jSONObjectB = this.e.b();
                if (jSONObjectB != null) {
                    cc2 cc2VarA = this.c.a(jSONObjectB);
                    if (cc2VarA != null) {
                        jSONObjectB.toString();
                        Log.isLoggable("FirebaseCrashlytics", 3);
                        this.d.getClass();
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        if (SettingsCacheBehavior.k.equals(settingsCacheBehavior) || cc2VarA.c >= jCurrentTimeMillis) {
                            try {
                                Log.isLoggable("FirebaseCrashlytics", 2);
                                cc2Var = cc2VarA;
                            } catch (Exception e) {
                                e = e;
                                cc2Var = cc2VarA;
                                Log.e("FirebaseCrashlytics", "Failed to get cached settings", e);
                            }
                        } else {
                            Log.isLoggable("FirebaseCrashlytics", 2);
                        }
                    } else {
                        Log.e("FirebaseCrashlytics", "Failed to parse cached settings data.", null);
                    }
                } else {
                    Log.isLoggable("FirebaseCrashlytics", 3);
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return cc2Var;
    }

    public final cc2 b() {
        return (cc2) this.h.get();
    }

    public final b33 c(ExecutorService executorService) {
        b33 b33Var;
        cc2 cc2VarA;
        SettingsCacheBehavior settingsCacheBehavior = SettingsCacheBehavior.c;
        boolean z = !this.a.getSharedPreferences("com.google.firebase.crashlytics", 0).getString("existing_instance_identifier", "").equals(this.b.g);
        AtomicReference atomicReference = this.i;
        AtomicReference atomicReference2 = this.h;
        if (!z && (cc2VarA = a(settingsCacheBehavior)) != null) {
            atomicReference2.set(cc2VarA);
            ((bj2) atomicReference.get()).b(cc2VarA);
            return ci1.t(null);
        }
        cc2 cc2VarA2 = a(SettingsCacheBehavior.k);
        if (cc2VarA2 != null) {
            atomicReference2.set(cc2VarA2);
            ((bj2) atomicReference.get()).b(cc2VarA2);
        }
        x50 x50Var = this.g;
        b33 b33Var2 = ((bj2) x50Var.h).a;
        synchronized (x50Var.f) {
            b33Var = ((bj2) x50Var.g).a;
        }
        ExecutorService executorService2 = ks2.a;
        bj2 bj2Var = new bj2();
        es2 es2Var = new es2(bj2Var, 0);
        b33Var2.a(executorService, es2Var);
        b33Var.a(executorService, es2Var);
        return bj2Var.a.e(executorService, new hc2(this));
    }
}
