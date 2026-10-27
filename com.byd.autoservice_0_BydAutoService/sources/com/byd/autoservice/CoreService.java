package com.byd.autoservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import com.byd.autoservice.application.AutoApplication;
import com.bydux.api.common.tools.log.BydLogHelper;
import f1.b;

/* JADX INFO: loaded from: classes.dex */
public class CoreService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1810b = "CoreService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b f1811c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f1812d = "com.byd.card";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f1813e = "com.byd.card.floatcard.FloatCardService";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1814f = "com.byd.bluetoothcall";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f1815g = "com.byd.bluetoothcall.service.BYD_BlueToothService";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g1.a f1816h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1817i = 1001;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f1818j = 1002;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f1819k = 1003;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f1820l = 1004;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1821m = 1005;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Context f1822n = null;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    @SuppressLint({"HandlerLeak"})
    public final Handler f1823o = new a();

    public class a extends Handler {
        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1002:
                    CoreService.this.e();
                    break;
                case 1003:
                    CoreService.this.f();
                    break;
                case 1004:
                    CoreService.this.f1811c = b.S0();
                    CoreService.this.f1811c.K0();
                    CoreService.this.f1811c.o1();
                    break;
                case 1005:
                    CoreService.this.f1811c.K0();
                    break;
            }
        }
    }

    public final void e() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.bluetoothcall", "com.byd.bluetoothcall.service.BYD_BlueToothService"));
        intent.putExtra("command", "BOOT_COMPLETED");
        startService(intent);
        l1.a.o().p(getApplicationContext(), "com.byd.bluetoothcall");
    }

    public final void f() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.byd.card", "com.byd.card.floatcard.FloatCardService"));
        startService(intent);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f1811c == null) {
            this.f1811c = b.S0();
        }
        this.f1811c.J0();
        this.f1823o.sendEmptyMessageDelayed(1005, 400L);
        String string = Settings.Global.getString(AutoApplication.a().getContentResolver(), "persist.sys.language.switch");
        BydLogHelper.logI("CoreService", "onConfigurationChanged from secretcode sLanguageSwitch =" + string);
        if ("true".equals(string)) {
            this.f1811c.M0();
        } else {
            this.f1811c.L0();
        }
        this.f1811c.G0();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f1816h = new g1.a(this);
        this.f1823o.sendEmptyMessageDelayed(1002, 100L);
        this.f1823o.sendEmptyMessageDelayed(1004, 1000L);
        this.f1816h.h();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.f1811c.k1();
    }
}
