package com.byd.logtools.application;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.version.BYDAutoVersionDevice;
import com.byd.logtools.apkinstall.ApkInstallService;
import com.byd.logtools.auth.AuthService;
import com.byd.logtools.broadcast.BydLogReceiver;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BydLogApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static BydLogApplication f3103b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    BYDAutoBodyworkDevice f3104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3105d = "/log";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final AbsBYDAutoBodyworkListener f3106e = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private List<Activity> f3107f = new LinkedList();

    class a extends AbsBYDAutoBodyworkListener {
        a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            BydLogHelper.logI("BydLogApplication", "AbsBYDAutoBodyworkListener onPowerLevelChanged: level = " + i);
            if (i == 0) {
                BydLogApplication.this.b();
            }
        }
    }

    public static BydLogApplication d() {
        return f3103b;
    }

    public void a(Activity activity) {
        if (activity != null && activity.getClass() != null) {
            BydLogHelper.logI("BydLogApplication", "addActivity " + activity.getClass().getName() + ", mList.size() = " + this.f3107f.size());
        }
        this.f3107f.add(activity);
    }

    public void b() {
        try {
            try {
                for (Activity activity : this.f3107f) {
                    if (activity != null && activity.getClass() != null) {
                        BydLogHelper.logI("BydLogApplication", "exit Activity " + activity.getClass().getName());
                    }
                    if (activity != null) {
                        activity.finish();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            this.f3107f.clear();
        }
    }

    public String c() {
        return "/data/data/com.byd.logtools/logs/";
    }

    public String e() {
        return "/data/data/com.byd.logtools/upLogs/";
    }

    public String f() {
        return "/data/data/com.byd.logtools/tempLogs/";
    }

    public void g(Activity activity) {
        if (activity != null && activity.getClass() != null) {
            BydLogHelper.logI("BydLogApplication", "removeActivity " + activity.getClass().getName() + ", mList.size() = " + this.f3107f.size());
        }
        this.f3107f.remove(activity);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f3103b = this;
        BydLogHelper.init(this);
        IntentFilter intentFilter = new IntentFilter();
        BydLogReceiver bydLogReceiver = new BydLogReceiver();
        intentFilter.addAction("android.intent.action.DROPBOX_ENTRY_ADDED");
        registerReceiver(bydLogReceiver, intentFilter);
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        this.f3104c = bYDAutoBodyworkDevice;
        bYDAutoBodyworkDevice.registerListener(this.f3106e);
        startService(new Intent(this, (Class<?>) AuthService.class));
        startService(new Intent(this, (Class<?>) ApkInstallService.class));
        BYDAutoVersionDevice.getInstance(d());
    }
}
