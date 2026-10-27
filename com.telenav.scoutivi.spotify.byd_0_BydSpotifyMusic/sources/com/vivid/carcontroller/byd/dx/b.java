package com.vivid.carcontroller.byd.dx;

import android.app.Application;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: BYDDXDeviceManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends l3.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f5123d = "com.vivid.carcontroller.byd.dx.b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5124b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f5125c;

    public b(Application application) {
        super(application);
        this.f5124b = 0;
        this.f5125c = 1;
    }

    private int a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("getInt", String.class, Integer.TYPE);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
            if (objInvoke == null || !(objInvoke instanceof Integer)) {
                return 0;
            }
            return ((Integer) objInvoke).intValue();
        } catch (Exception e7) {
            h3.i.b(f5123d).e(e7, "getSteeringWheelPosition exception", new Object[0]);
            return 0;
        }
    }

    public boolean b() {
        return a() == 1;
    }
}
