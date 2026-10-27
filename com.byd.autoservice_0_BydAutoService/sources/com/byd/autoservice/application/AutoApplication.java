package com.byd.autoservice.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.os.SystemProperties;
import com.byd.autoservice.CoreService;
import com.bydux.api.common.tools.log.BydLogHelper;
import h1.b;
import h1.c;
import i1.e;

/* JADX INFO: loaded from: classes.dex */
public class AutoApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Context f1826b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f1827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1828d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Class<?>[] f1830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final c[] f1831g;

    public AutoApplication() {
        Class<?>[] clsArr = {b.class};
        this.f1830f = clsArr;
        this.f1831g = new c[clsArr.length];
    }

    public static Context a() {
        return f1826b;
    }

    public static int b() {
        return f1827c;
    }

    public static void c(int i3) {
        f1827c = i3;
    }

    public void d() {
        BydLogHelper.logE("CustomServerApplication", "startServicesIfNeeded mServicesStarted is " + this.f1829e);
        if (this.f1829e) {
            return;
        }
        if (!this.f1828d && "1".equals(SystemProperties.get("sys.boot_completed"))) {
            this.f1828d = true;
        }
        int length = this.f1830f.length;
        for (int i3 = 0; i3 < length; i3++) {
            try {
                this.f1831g[i3] = (c) this.f1830f[i3].newInstance();
                c[] cVarArr = this.f1831g;
                cVarArr[i3].f3065a = this;
                cVarArr[i3].c();
                if (this.f1828d) {
                    this.f1831g[i3].a();
                }
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
        this.f1829e = true;
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f1829e) {
            int length = this.f1831g.length;
            for (int i3 = 0; i3 < length; i3++) {
                c[] cVarArr = this.f1831g;
                if (cVarArr[i3] != null) {
                    cVarArr[i3].b(configuration);
                    BydLogHelper.logE("CustomServerApplication", "onCreate newConfig / " + configuration);
                }
            }
        }
        int i4 = configuration.orientation;
        getResources().getConfiguration();
        if (i4 == 1) {
            f1.b.S0().p1();
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logE("CustomServerApplication", "onCreate");
        f1826b = this;
        e.b(getApplicationContext());
        startService(new Intent(getApplicationContext(), (Class<?>) CoreService.class));
        d();
        BYDAutoTestDevice.getInstance(getApplicationContext());
        BYDAutoBodyworkDevice.getInstance(getApplicationContext());
    }
}
