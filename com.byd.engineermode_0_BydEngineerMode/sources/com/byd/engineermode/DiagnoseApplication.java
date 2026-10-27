package com.byd.engineermode;

import android.app.Activity;
import android.app.Application;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.hardware.bydauto.pki.PkiManager;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DiagnoseApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static DiagnoseApplication f2238b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    BYDAutoBodyworkDevice f2239c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final AbsBYDAutoBodyworkListener f2240d = new b();
    private List<Activity> e = new LinkedList();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            BYDAutoVersionDevice.getInstance(DiagnoseApplication.e());
            BYDAutoOtaDevice.getInstance(DiagnoseApplication.e());
            OtaUpgradeManager.getInstance(DiagnoseApplication.e());
            BYDAutoAudioDevice.getInstance(DiagnoseApplication.e());
            BYDAutoTestDevice.getInstance(DiagnoseApplication.e());
            PkiManager.getInstance(DiagnoseApplication.e());
            BYDAutoTBoxDevice.getInstance(DiagnoseApplication.e());
            DiagnoseApplication.this.f2239c = BYDAutoBodyworkDevice.getInstance(DiagnoseApplication.f2238b);
            DiagnoseApplication diagnoseApplication = DiagnoseApplication.this;
            diagnoseApplication.f2239c.registerListener(diagnoseApplication.f2240d);
        }
    }

    class b extends AbsBYDAutoBodyworkListener {
        b() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            com.byd.engineermode.utils.a.i("DiagnoseApplication AbsBYDAutoBodyworkListener onPowerLevelChanged: level = " + i);
            if (i == 0) {
                DiagnoseApplication.this.d();
            }
        }
    }

    public static DiagnoseApplication e() {
        return f2238b;
    }

    public void c(Activity activity) {
        if (activity != null && activity.getClass() != null) {
            com.byd.engineermode.utils.a.i("DiagnoseApplication addActivity " + activity.getClass().getName() + ", mList.size() = " + this.e.size());
        }
        this.e.add(activity);
    }

    public void d() {
        try {
            for (Activity activity : this.e) {
                if (activity != null && activity.getClass() != null) {
                    com.byd.engineermode.utils.a.i("DiagnoseApplication exit Activity " + activity.getClass().getName());
                }
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void f(Activity activity) {
        if (activity != null && activity.getClass() != null) {
            com.byd.engineermode.utils.a.i("DiagnoseApplication removeActivity " + activity.getClass().getName() + ", mList.size() = " + this.e.size());
        }
        this.e.remove(activity);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f2238b = this;
        new Thread(new a()).start();
    }
}
