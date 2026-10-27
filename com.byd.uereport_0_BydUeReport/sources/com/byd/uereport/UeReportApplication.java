package com.byd.uereport;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Handler;
import com.byd.bt.model.BTDevice;
import com.byd.systemsettings.DxJumpService;
import g5.h;
import g5.k;
import h3.c;
import java.util.Calendar;
import java.util.TimeZone;
import r4.e;
import r4.f;

/* JADX INFO: loaded from: classes2.dex */
public class UeReportApplication extends Application {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Context f3064d;

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UeReportApplication.this.d();
        }
    }

    public final String b() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e8) {
            e8.printStackTrace();
            return null;
        }
    }

    public final void c() {
        if (!e.a(this, "KEY_MODE")) {
            e.g(this, "KEY_MODE", true);
        }
        if (!e.a(this, "KEY_CHARGE_FULL")) {
            e.g(this, "KEY_CHARGE_FULL", true);
        }
        if (!e.a(this, "KEY_CHARGE_SWITCH")) {
            e.g(this, "KEY_CHARGE_SWITCH", false);
        }
        if (!e.a(this, "KEY_CHARGE_CYCLE")) {
            e.i(this, "KEY_CHARGE_CYCLE", "1|1|1|1|1|1|1");
        }
        if (!e.a(this, "KEY_CHARGE_START_TIME")) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 0, 0);
            calendar.set(14, 0);
            e.h(this, "KEY_CHARGE_START_TIME", calendar.getTimeInMillis());
        }
        if (!e.a(this, "KEY_CHARGE_END_TIME")) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(calendar2.get(1), calendar2.get(2), calendar2.get(5), 0, 0, 0);
            calendar2.set(14, 0);
            e.h(this, "KEY_CHARGE_END_TIME", calendar2.getTimeInMillis());
        }
        if (!e.a(this, "KEY_PRICE_FULL")) {
            e.g(this, "KEY_PRICE_FULL", false);
        }
        if (!e.a(this, "KEY_PRICE_START_TIME")) {
            e.h(this, "KEY_PRICE_START_TIME", 0L);
            e.h(this, "KEY_PRICE_END_TIME", 420L);
        }
        if (!e.a(this, "KEY_USECAR_SWITCH")) {
            e.g(this, "KEY_USECAR_SWITCH", false);
        }
        if (!e.a(this, "KEY_USECAR_CYCLE")) {
            e.i(this, "KEY_USECAR_CYCLE", "1|1|1|1|1|1|1");
        }
        if (!e.a(this, "KEY_USECAR_START_TIME")) {
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(calendar3.get(1), calendar3.get(2), calendar3.get(5), 8, 0, 0);
            calendar3.set(14, 0);
            e.h(this, "KEY_USECAR_START_TIME", calendar3.getTimeInMillis());
        }
        if (!e.a(this, "KEY_CHARGE_GUN_TAG")) {
            e.g(this, "KEY_CHARGE_GUN_TAG", false);
        }
        if (!e.a(this, "acc_off_tips")) {
            e.g(this, "acc_off_tips", true);
        }
        if (!e.a(this, "gun_state_tips")) {
            e.g(this, "gun_state_tips", true);
        }
        if (!e.a(this, "KEY_TIME_ZONE")) {
            e.i(this, "KEY_TIME_ZONE", TimeZone.getDefault().getID());
        }
        if (!k.a(this, "KEY_MAINTAIN_MILE_STATE")) {
            k.g(this, "KEY_MAINTAIN_MILE_STATE", false);
        }
        if (k.a(this, "KEY_MAINTAIN_TIME_STATE")) {
            return;
        }
        k.g(this, "KEY_MAINTAIN_TIME_STATE", false);
    }

    public final void d() {
        g5.a.i("-------------------initService--------------- ");
        startService(new Intent(getApplicationContext(), (Class<?>) VehicleService.class));
        startService(new Intent(getApplicationContext(), (Class<?>) CpaaService.class));
        startService(new Intent(getApplicationContext(), (Class<?>) MultiModuleService.class));
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        f5.a.h(this).e();
        super.onConfigurationChanged(configuration);
    }

    @Override // android.app.Application
    public void onCreate() {
        r4.a.d(this);
        super.onCreate();
        g5.a.i("-------------------Application onCreate--------------- ");
        g5.a.i("UeReport version : " + b());
        c.a.a().f(getApplicationContext());
        f.a();
        c();
        r4.a.c(this);
        f3064d = getApplicationContext();
        x3.a.d(new h(getApplicationContext(), 1));
        f5.a.h(getApplicationContext());
        f3.c.i().j(this);
        new Handler().postDelayed(new a(), BTDevice.CONNECTED_TIME_STANDARD);
        o2.c.b().e(new DxJumpService());
    }
}
