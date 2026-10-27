package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.UserManager;
import android.util.Log;
import com.amazon.identity.auth.device.t9;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i5 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f382a;

    public i5(Context context) {
        this.f382a = context;
    }

    public boolean a() {
        return la.a("com.amazon.canary", this.f382a) && qe.e(this.f382a);
    }

    public boolean b() {
        Context context = this.f382a;
        String str = qe.f519a;
        t9.a aVarB = t9.b(context);
        if (aVarB == null) {
            return true;
        }
        return qe.c.contains(aVarB.f583a);
    }

    public boolean c() {
        return la.a("com.amazon.fv", this.f382a) && qe.e(this.f382a);
    }

    public boolean d() {
        return "com.amazon.imp".equals(this.f382a.getApplicationContext().getPackageName());
    }

    @TargetApi(24)
    public boolean e() {
        if (!(qe.g(this.f382a))) {
            return false;
        }
        UserManager userManager = (UserManager) this.f382a.getSystemService("user");
        if (userManager == null) {
            Log.e(ga.a("PlatformWrapper"), "Cannot get UserManager while the OS supports direct boot!");
            return false;
        }
        boolean z = !userManager.isUserUnlocked();
        ga.a("PlatformWrapper");
        return z;
    }

    public boolean f() {
        return !(t9.a(this.f382a).f261a != null);
    }
}
