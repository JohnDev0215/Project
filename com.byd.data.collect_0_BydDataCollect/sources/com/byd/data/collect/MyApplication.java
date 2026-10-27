package com.byd.data.collect;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.collectdata.BYDAutoCollectDataDevice;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.vehiclehealth.BYDAutoVehicleHealthDevice;
import com.byd.data.collect.server.i;
import com.byd.data.common.sqlite.o;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MyApplication extends Application {
    private void a() {
        BYDAutoStudy.getInstance(this);
        BYDAutoBodyworkDevice.getInstance(this);
        BYDAutoCollectDataDevice.getInstance(this);
        BYDAutoVehicleHealthDevice.getInstance(this);
        BYDAutoPowerDevice.getInstance(this);
        BYDAutoEnergyDevice.getInstance(this);
        BYDAutoInstrumentDevice.getInstance(this);
        BYDAutoStatisticDevice.getInstance(this);
    }

    private void b(String str) {
        Context baseContext = getBaseContext();
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            ApplicationInfo applicationInfo = getApplicationInfo();
            applicationInfo.dataDir = str;
            Field declaredField = applicationInfo.getClass().getDeclaredField("credentialProtectedDataDir");
            declaredField.setAccessible(true);
            declaredField.set(applicationInfo, str);
            baseContext.getClass();
            Field declaredField2 = Class.forName("android.app.ContextImpl").getDeclaredField("mPackageInfo");
            declaredField2.setAccessible(true);
            Method declaredMethod = declaredField2.getType().getDeclaredMethod("setApplicationInfo", ApplicationInfo.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(declaredField2.get(baseContext), applicationInfo);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        com.byd.data.common.server.b.h(context);
        o.f().i(context);
        b("data_acquisition/com.byd.data.collect" + File.separator);
        com.byd.data.common.g.a.i().n(i.o(context), com.byd.data.collect.k.d.S(context));
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        a();
        com.byd.data.common.server.b.h(this).l(true, 0);
    }
}
