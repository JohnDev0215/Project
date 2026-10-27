package com.byd.smarttravel;

import android.app.Application;
import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import c.a.d.i;
import c.a.d.k;
import java.util.Calendar;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class BydApp extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Context f1950b;

    public static Context a() {
        return f1950b;
    }

    public final void b() {
        if (!i.a(this, "KEY_MODE")) {
            i.g(this, "KEY_MODE", true);
        }
        if (!i.a(this, "KEY_CHARGE_FULL")) {
            i.g(this, "KEY_CHARGE_FULL", true);
        }
        if (!i.a(this, "KEY_CHARGE_SWITCH")) {
            i.g(this, "KEY_CHARGE_SWITCH", false);
        }
        if (!i.a(this, "KEY_CHARGE_CYCLE")) {
            i.i(this, "KEY_CHARGE_CYCLE", "1|1|1|1|1|1|1");
        }
        if (!i.a(this, "KEY_CHARGE_START_TIME")) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 0, 0);
            calendar.set(14, 0);
            i.h(this, "KEY_CHARGE_START_TIME", calendar.getTimeInMillis());
        }
        if (!i.a(this, "KEY_CHARGE_END_TIME")) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(calendar2.get(1), calendar2.get(2), calendar2.get(5), 0, 0, 0);
            calendar2.set(14, 0);
            i.h(this, "KEY_CHARGE_END_TIME", calendar2.getTimeInMillis());
        }
        if (!i.a(this, "KEY_PRICE_FULL")) {
            i.g(this, "KEY_PRICE_FULL", false);
        }
        if (!i.a(this, "KEY_PRICE_START_TIME")) {
            i.h(this, "KEY_PRICE_START_TIME", 0L);
            i.h(this, "KEY_PRICE_END_TIME", 420L);
        }
        if (!i.a(this, "KEY_USECAR_SWITCH")) {
            i.g(this, "KEY_USECAR_SWITCH", false);
        }
        if (!i.a(this, "KEY_USECAR_CYCLE")) {
            i.i(this, "KEY_USECAR_CYCLE", "1|1|1|1|1|1|1");
        }
        if (!i.a(this, "KEY_USECAR_START_TIME")) {
            Calendar calendar3 = Calendar.getInstance();
            calendar3.set(calendar3.get(1), calendar3.get(2), calendar3.get(5), 8, 0, 0);
            calendar3.set(14, 0);
            i.h(this, "KEY_USECAR_START_TIME", calendar3.getTimeInMillis());
        }
        if (!i.a(this, "acc_off_tips")) {
            i.g(this, "acc_off_tips", true);
        }
        if (!i.a(this, "gun_state_tips")) {
            i.g(this, "gun_state_tips", true);
        }
        if (i.a(this, "KEY_TIME_ZONE")) {
            return;
        }
        i.i(this, "KEY_TIME_ZONE", TimeZone.getDefault().getID());
    }

    @Override // android.app.Application
    public void onCreate() throws Throwable {
        super.onCreate();
        f1950b = this;
        k.a();
        k.b("[智慧充电]----[APP启动]:" + hashCode());
        BYDAutoStudy.getInstance(this);
        BYDAutoBodyworkDevice.getInstance(this);
        b();
    }
}
