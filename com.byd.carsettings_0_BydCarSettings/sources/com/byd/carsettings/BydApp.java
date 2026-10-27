package com.byd.carsettings;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import androidx.annotation.RequiresApi;
import b.b.a.f.h;
import b.b.g.j.a;
import b.b.g.n.q.m;
import b.b.g.n.q.t;
import b.b.g.n.q.u;
import b.b.g.p.c;
import b.b.g.r.d;
import b.b.g.r.e;
import b.b.g.r.g;
import b.b.g.r.i;
import b.b.g.r.k;
import b.b.g.r.q;
import com.byd.base.hilt.Router;
import com.byd.dialogservice.DialogService;
import com.byd.mvp.ui.widget.BydBridgeWebView;
import com.byd.mvp.utils.AppConfig;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.application.BydApplication;

/* JADX INFO: loaded from: classes2.dex */
public class BydApp extends BydApplication {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Context f2577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static BydApp f2578b;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.c.a.d("BydApp", "initTaskAsync", "---start---");
            BydBridgeWebView.k();
            e.d(BydApp.this);
            a.b.a().p(BydApp.this.getApplicationContext());
            b.b.m.c.a.d("BydApp", "initTaskAsync", "---end---");
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.g.g.b.n(BydApp.this);
        }
    }

    public static BydApp a() {
        return f2578b;
    }

    public static Context getContext() {
        return f2577a;
    }

    public final void b(Context context) {
        b.b.a.a.a.b().c(context);
    }

    public final void c() {
        h.a().e(new a(), 500L);
        h.a().e(new b(), 2000L);
    }

    @RequiresApi(api = 28)
    public final boolean d() {
        b.b.m.c.a.a("BydApp", "isMainProcess", ", pid = " + Process.myPid() + ", getProcessName = " + Application.getProcessName());
        return true;
    }

    public final void e() {
        b.b.m.c.a.a("BydApp", "registerCrashCallback", "---registerCrashCallback------");
        d.c.a().a(getApplicationContext());
    }

    @Override // com.bydux.api.common.views.application.BydApplication, android.app.Application
    @RequiresApi(api = 28)
    public void onCreate() {
        i.g();
        super.onCreate();
        f2578b = this;
        b.b.m.c.a.e(new q(getApplicationContext(), 1));
        b.b.m.c.a.d("BydApp", "onCreate", "---START---");
        g.j(getApplicationContext());
        BydLogHelper.logD("car setting auto study init device start");
        u.I().s0(this);
        m.p0().I1(this);
        b.b.g.n.q.b.c().f(this);
        t.b().c(this);
        b.b.g.n.q.i.k().m(this);
        BydLogHelper.logD("car setting auto study init device start");
        String strA = c.a("ro.build.type", null);
        b.b.m.c.a.d("BydApp", "onCreate", "---versionType: " + strA);
        if ("userdebug".equals(strA)) {
            try {
                Class<?> cls = Class.forName("com.github.moduth.blockcanary.BlockCanaryWrapper");
                cls.getMethod("init", Context.class).invoke(cls.newInstance(), this);
                b.b.m.c.a.d("BydApp", "onCreate", "---BlockCanary installed.");
            } catch (Exception e2) {
                b.b.m.c.a.c("BydApp", "onCreate", "---BlockCanary install failed. e: " + e2);
            }
        }
        b.b.g.p.m.a.g(getApplicationContext());
        e();
        DialogService.j(getApplicationContext(), "action_just_start");
        f2577a = this;
        b(this);
        Router.b();
        AppConfig.c(AppConfig.AppMode.PRODUCT, this, null);
        b.b.m.c.a.d("BydApp", "onCreate", "---readVehicleConfig---");
        k.c(getApplicationContext());
        if (!b.b.m.g.h.f()) {
            b.b.m.g.h.g(new b.b.g.r.h());
            b.b.m.g.h.i(getApplicationContext());
        }
        b.b.m.c.a.d("BydApp", "onCreate", "---cacheVehicleConfig completed---");
        i.f(getApplicationContext());
        c();
        b.b.i.d.s(getApplicationContext());
        if (d()) {
            b.b.g.r.u.c(getContext(), "massage_dialog", 2);
            b.b.d.d.a.e("pairing_request_service", DialogService.class.getName());
            b.b.m.c.a.d("BydApp", "onCreate", "---END---");
            b.b.a.e.c.b().e(new b.b.g.b());
            b.b.a.e.c.b().d(new b.b.g.a());
            b.b.g.r.u.c(getApplicationContext(), "wade_protect_checkbox_state", 2);
        }
    }

    @Override // com.bydux.api.common.views.application.BydApplication
    public boolean onSkinEnable() {
        return false;
    }
}
