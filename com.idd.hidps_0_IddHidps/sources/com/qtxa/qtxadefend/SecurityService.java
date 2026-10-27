package com.qtxa.qtxadefend;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.StrictMode;
import com.qtxa.qtcarsecsvc.ISecurityService;
import com.qtxa.qtcarsecsvc.ISystemCallBack;
import com.qtxa.qtcarsecsvc.OnEventListener;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SecurityService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private u f103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final ISecurityService.a f104b = new a();

    class a extends ISecurityService.a {
        a() {
        }

        @Override // com.qtxa.qtcarsecsvc.ISecurityService
        public void addEventListener(OnEventListener onEventListener) {
            if (SecurityService.this.c()) {
                SecurityService.this.f103a.g().a(onEventListener);
            }
        }

        @Override // com.qtxa.qtcarsecsvc.ISecurityService
        public void addSystemCallBack(ISystemCallBack iSystemCallBack) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
            SecurityService.this.f103a.h().a(iSystemCallBack);
            ActivityManager activityManager = (ActivityManager) SecurityService.this.f103a.c().getSystemService("activity");
            if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (!runningAppProcessInfo.processName.equals("system") && !runningAppProcessInfo.processName.equals("com.idd.hidps")) {
                    SecurityService.this.f103a.a().o(runningAppProcessInfo.processName);
                }
            }
        }

        @Override // com.qtxa.qtcarsecsvc.ISecurityService
        public String call(String str) {
            if (!SecurityService.this.c()) {
                return null;
            }
            w.d("on_call : " + str);
            String strF = SecurityService.this.f103a.a().f(str);
            w.d("on_result : " + strF);
            return strF;
        }

        @Override // com.qtxa.qtcarsecsvc.ISecurityService
        public void removeEventListener(OnEventListener onEventListener) {
            if (SecurityService.this.c()) {
                SecurityService.this.f103a.g().j(onEventListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c() {
        if (Binder.getCallingUid() == 1000) {
            return true;
        }
        w.d("calling " + this.f103a.c().getPackageManager().getNameForUid(Binder.getCallingUid()) + ", " + Binder.getCallingPid());
        return false;
    }

    void d() {
        w.d("Service APK version name is: " + b.b(getApplicationContext()));
    }

    void e() {
        if (b.f110a) {
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f104b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        e();
        w.c(getApplicationContext());
        u uVar = new u(getApplicationContext());
        this.f103a = uVar;
        uVar.i();
        PackageEventReceiver.a(this.f103a);
        d();
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f103a.j();
        w.g();
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
