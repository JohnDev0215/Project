package com.byd.dialogservice;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import b.b.a.f.h;
import b.b.d.f.a;
import b.b.f.a;
import b.b.f.c;
import b.b.f.f;
import b.b.g.q.b;
import b.b.g.q.d.m;
import b.b.i.a;
import com.byd.carplay.SystemPanelChangeReceiver;
import com.byd.carsettings.system.BydStorageManager;
import com.byd.systemsettings.BydSettingsApi;
import com.byd.systemsettings.wifi2.BydWifiManager;
import java.lang.ref.WeakReference;
import ts.car.service.androidauto.data.AndroidAutoDevice;
import ts.car.service.carplay.data.CarPlayDevice;

/* JADX INFO: loaded from: classes2.dex */
public class DialogService extends Service {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f3503a = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SharedPreferences f3506d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public SystemPanelChangeReceiver f3508f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile boolean f3504b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f3505c = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Binder f3507e = new Binder();
    public final BroadcastReceiver g = new a();

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            b.b.m.c.a.a("DialogService", ", ---onReceive---action: " + action);
            if ("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG".equals(action)) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=dismiss_all_dialog"));
                Context applicationContext = context.getApplicationContext();
                intent2.setComponent(new ComponentName(applicationContext, (Class<?>) DialogService.class));
                DialogService.i(applicationContext, intent2);
                return;
            }
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                String stringExtra = intent.getStringExtra("reason");
                b.b.m.c.a.a("DialogService", ", ---onReceive---reason: " + stringExtra);
                if (TextUtils.equals(stringExtra, "homekey")) {
                    Context applicationContext2 = context.getApplicationContext();
                    Intent intent3 = new Intent(applicationContext2, (Class<?>) DialogService.class);
                    intent3.setAction("action_home_key_event");
                    DialogService.i(applicationContext2, intent3);
                }
            }
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            f fVarA = f.e.a();
            fVarA.y(DialogService.this.getApplicationContext());
            b.b.m.c.a.a("DialogService", "---onCreate---cdm init completed.");
            String name = b.class.getName();
            DialogService dialogService = DialogService.this;
            a aVar = null;
            fVarA.I(name, new d(dialogService, dialogService, aVar));
            String name2 = b.class.getName();
            DialogService dialogService2 = DialogService.this;
            fVarA.G(name2, new c(dialogService2, dialogService2, aVar));
            b.b.m.c.a.a("DialogService", "---onCreate---SingleConnection init completed.");
        }
    }

    public class c implements a.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<DialogService> f3511a;

        public /* synthetic */ c(DialogService dialogService, DialogService dialogService2, a aVar) {
            this(dialogService2);
        }

        @Override // b.b.f.a.g
        public void c(boolean z) {
            WeakReference<DialogService> weakReference = this.f3511a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f3511a.get().a(z);
        }

        @Override // b.b.f.a.g
        public void e(int i, AndroidAutoDevice androidAutoDevice) {
            WeakReference<DialogService> weakReference = this.f3511a;
            if (weakReference == null || weakReference.get() == null) {
            }
        }

        public c(DialogService dialogService) {
            this.f3511a = new WeakReference<>(dialogService);
        }
    }

    public class d implements c.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<DialogService> f3513a;

        public /* synthetic */ d(DialogService dialogService, DialogService dialogService2, a aVar) {
            this(dialogService2);
        }

        @Override // b.b.f.c.i
        public void c(int i, CarPlayDevice carPlayDevice) {
            WeakReference<DialogService> weakReference = this.f3513a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f3513a.get().e(i, carPlayDevice);
        }

        @Override // b.b.f.c.i
        public void onCarPlayDisplayStatusChanged(boolean z) {
            WeakReference<DialogService> weakReference = this.f3513a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f3513a.get().f(z);
        }

        public d(DialogService dialogService) {
            this.f3513a = new WeakReference<>(dialogService);
        }
    }

    public static void i(Context context, Intent intent) {
        b.b.m.c.a.a("DialogService", "---start---");
        if (context == null || intent == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 26) {
            context.startService(intent);
            return;
        }
        Parcel parcelObtain = Parcel.obtain();
        b.b.m.g.a.c(context, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, new Object[]{intent, UserHandle.readFromParcel(parcelObtain)});
        parcelObtain.recycle();
    }

    public static void j(Context context, String str) {
        b.b.m.c.a.a("DialogService", "---start---action: " + str + ", isRunning: " + f3503a);
        if (context == null || TextUtils.isEmpty(str) || f3503a) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setComponent(new ComponentName(context, (Class<?>) DialogService.class));
        i(context, intent);
    }

    public void a(boolean z) {
        this.f3504b = z;
        b.b.m.c.a.a("DialogService", "aaVideoFocusStatusChanged", ", isShow: " + z);
        b.C0065b.a().k(this, g());
    }

    public final void e(int i, CarPlayDevice carPlayDevice) {
        String[] strArr = new String[2];
        strArr[0] = "cpDeviceStatusChanged";
        StringBuilder sb = new StringBuilder();
        sb.append(", status: ");
        sb.append(i);
        sb.append(", device: ");
        sb.append(carPlayDevice != null ? carPlayDevice.getDeviceName() : "null");
        strArr[1] = sb.toString();
        b.b.m.c.a.a("DialogService", strArr);
        if (i != 2 || carPlayDevice == null) {
            return;
        }
        String btAddr = carPlayDevice.getBtAddr();
        if (TextUtils.isEmpty(btAddr)) {
            btAddr = carPlayDevice.getUsbSerialNumber();
        }
        if (TextUtils.isEmpty(btAddr)) {
            return;
        }
        h(btAddr.hashCode() + "");
    }

    public final void f(boolean z) {
        this.f3505c = z;
        b.b.m.c.a.a("DialogService", "cpDisplayStatusChanged", ", isShow: " + z);
        b.C0065b.a().k(this, g());
    }

    public final boolean g() {
        return this.f3504b || this.f3505c;
    }

    public final synchronized void h(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferences.Editor editorEdit = this.f3506d.edit();
        editorEdit.putLong(str, System.currentTimeMillis());
        editorEdit.commit();
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        b.b.m.c.a.a("DialogService", "---onBind---intent: " + intent);
        return this.f3507e;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b.b.m.c.a.a("DialogService", "onConfigurationChanged", ", newConfig: " + configuration);
        ConfigurationChangedChecker.c(this, configuration);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ConfigurationChangedChecker.b(getApplicationContext());
        f3503a = true;
        this.f3506d = getSharedPreferences("key_cpaa_connected_time", 0);
        b.b.m.c.a.a("DialogService", "---onCreate---");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.byd.ACTION_DISMISS_ALL_SETTINGS_DIALOG");
        registerReceiver(this.g, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter("com.byd.action.panel.state.change");
        SystemPanelChangeReceiver systemPanelChangeReceiver = new SystemPanelChangeReceiver();
        this.f3508f = systemPanelChangeReceiver;
        registerReceiver(systemPanelChangeReceiver, intentFilter2);
        h.a().j(new b());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        b.C0065b.a().l();
        b.b.m.c.a.a("DialogService", "---onDestroy---");
        BroadcastReceiver broadcastReceiver = this.g;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        SystemPanelChangeReceiver systemPanelChangeReceiver = this.f3508f;
        if (systemPanelChangeReceiver != null) {
            unregisterReceiver(systemPanelChangeReceiver);
        }
        a.c.a().q();
        a.e.a().c();
        f fVarA = f.e.a();
        fVarA.Q(getClass().getName());
        fVarA.O(getClass().getName());
        fVarA.J(this);
        BydWifiManager.Singleton.get().release();
        b.b.m.e.i.a.a();
        b.b.i.c.k();
        f3503a = false;
        b.b.m.a.a.b();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        b.b.m.c.a.a("DialogService", "onLowMemory", "onLowMemory");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            onStart(intent, i2);
            return 1;
        }
        boolean zR = b.b.g.p.h.r(this);
        b.b.m.c.a.a("DialogService", "onStartCommand", ", intent: " + intent + ", userSetupCompleted: " + zR);
        String strI = b.C0065b.a().i(intent);
        b.b.m.c.a.g("DialogService", "onStartCommand", ", action: " + intent.getAction() + ", schemeAction: " + strI);
        if (!BydSettingsApi.RESTORE_FACTORY_SETTINGS.equals(strI) && !zR) {
            onStart(intent, i2);
            return 1;
        }
        if (!"action_home_key_event".equals(intent.getAction()) && !BydSettingsApi.DISMISS_ALL_DIALOG.equals(intent.getAction())) {
            b.b.m.c.a.g("DialogService", "onStartCommand", "DialogUtils.dismissAll()");
            m.b();
            m.f1480b.clear();
        }
        BydStorageManager.s();
        b.C0065b.a().d(this, intent);
        onStart(intent, i2);
        return 1;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        b.b.m.c.a.a("DialogService", "onTaskRemoved", ", rootIntent: " + intent);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        b.b.m.c.a.a("DialogService", "onTrimMemory", ", level: " + i);
        b.b.g.p.b.b().c(i);
    }
}
