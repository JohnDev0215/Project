package com.vivid.carcontroller.byd.dx;

import android.app.Application;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BYDDXDriveModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends l3.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f5134e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f5135f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f5136g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f5137h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f5138i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private boolean f5139j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private Handler f5140k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f5141l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f5142m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private BYDAutoGearboxDevice f5143n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f5144o;

    /* JADX INFO: compiled from: BYDDXDriveModeManager.java */
    class a extends AbsBYDAutoGearboxListener {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void d(boolean z6, int i7) {
            g.this.f5141l = z6;
            if (g.this.f5141l && g.this.f5138i) {
                g.this.b(i7 != 1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(int i7) {
            h3.i.b("BYDDXDriveModeManager").b("onGearChanged:%s", Integer.valueOf(i7));
            boolean z6 = i7 != 1;
            if (g.this.f5138i) {
                g.this.b(z6);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(int i7) {
            h3.i.b("BYDDXDriveModeManager").b("onGearboxAutoModeLevelChanged:%s", Integer.valueOf(i7));
            boolean z6 = i7 != 1;
            if (g.this.f5138i) {
                g.this.b(z6);
            }
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener, a.d
        public void isConnected(final boolean z6) {
            super.isConnected(z6);
            h3.i.b("BYDDXDriveModeManager").b("sdk isBinding:" + z6, new Object[0]);
            final int gearboxAutoModeLevel = g.this.f5142m ? g.this.f5143n.getGearboxAutoModeLevel() : g.this.f5143n.getGear();
            h3.i.b("BYDDXDriveModeManager").b("isConnected autoModeType:%s", Integer.valueOf(gearboxAutoModeLevel));
            g.this.f5140k.post(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.f
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5131e.d(z6, gearboxAutoModeLevel);
                }
            });
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(final int i7) {
            super.onGearChanged(i7);
            if (g.this.f5142m) {
                return;
            }
            g.this.f5140k.post(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5129e.e(i7);
                }
            });
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeLevelChanged(final int i7) {
            super.onGearboxAutoModeLevelChanged(i7);
            if (g.this.f5142m) {
                g.this.f5140k.post(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f5127e.f(i7);
                    }
                });
            }
        }
    }

    public g(Application application) {
        super(application);
        this.f5134e = BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON;
        this.f5135f = "0";
        this.f5136g = "1";
        this.f5137h = "2";
        this.f5138i = false;
        this.f5139j = false;
        this.f5140k = new Handler(Looper.getMainLooper());
        this.f5141l = false;
        this.f5142m = false;
        this.f5144o = false;
        String strN = n();
        h3.i.b("BYDDXDriveModeManager").b("energy type is :%s", strN);
        Log.i("BYDDXDriveModeManager", "energy type is :" + strN);
        this.f5142m = "2".equals(strN);
    }

    private String n() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Method method = cls.getMethod("get", String.class, String.class);
            method.setAccessible(true);
            Object objInvoke = method.invoke(cls, "persist.sys.energytype", "");
            return (objInvoke == null || !(objInvoke instanceof String)) ? "" : (String) objInvoke;
        } catch (Exception e7) {
            h3.i.b("BYDDXDriveModeManager").e(e7, "getEnergyType exception", new Object[0]);
            Log.e("BYDDXDriveModeManager", "getEnergyType exception", e7);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q() {
        this.f5143n = BYDAutoGearboxDevice.getInstance(this.f7958d);
        boolean zA = a();
        h3.i.b("BYDDXDriveModeManager").b("registerDriveModeListener isEnableDriveMode:%s", Boolean.valueOf(zA));
        if (zA) {
            this.f5138i = true;
            if (this.f5141l) {
                int gearboxAutoModeLevel = this.f5142m ? this.f5143n.getGearboxAutoModeLevel() : this.f5143n.getGear();
                h3.i.b("BYDDXDriveModeManager").b("startListenDriveMode autoModeType:%s", Integer.valueOf(gearboxAutoModeLevel));
                b(gearboxAutoModeLevel != 1);
            }
            if (this.f5139j) {
                return;
            }
            r();
        }
    }

    private void r() {
        try {
            this.f5143n.registerListener((AbsBYDAutoGearboxListener) new a());
            this.f5139j = true;
        } catch (Throwable th) {
            h3.i.b("BYDDXDriveModeManager").e(th, "registerListener failed", new Object[0]);
        }
    }

    public List<String> o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        return arrayList;
    }

    public boolean p() {
        return x.a.a(this.f7958d, BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON) == 0;
    }

    public boolean s() {
        synchronized (this) {
            if (this.f5144o) {
                return false;
            }
            this.f5144o = true;
            this.f5140k.post(new Runnable() { // from class: com.vivid.carcontroller.byd.dx.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f5126e.q();
                }
            });
            return true;
        }
    }
}
