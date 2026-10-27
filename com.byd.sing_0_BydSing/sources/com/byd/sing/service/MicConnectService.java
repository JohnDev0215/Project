package com.byd.sing.service;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.app.Service;
import android.app.TaskStackListener;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import com.byd.sing.m;

/* JADX INFO: loaded from: classes.dex */
public class MicConnectService extends Service {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BYDAutoAudioDevice f3259b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BYDAutoBodyworkDevice f3260c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private IActivityManager f3261d;
    private int f;
    private int g;
    private BluetoothCallReceiver h;
    private WarmingReceiver i;
    private boolean j;
    private boolean k;
    private i l;
    private BYDAutoGearboxDevice m;
    private j n;
    private Handler e = new Handler(Looper.getMainLooper());
    IServiceConnectionListener o = new b();
    private final TaskStackListener p = new e();
    private final IProcessObserver q = new f();
    private BroadcastReceiver r = new g();
    private BroadcastReceiver s = new h();
    private int t = -2;

    class a extends AbsBYDAutoPowerListener {
        a() {
        }

        @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
        public void onPowerSTRChanged(int i) {
            super.onPowerSTRChanged(i);
            com.byd.sing.a.a("onPowerSTRChanged state :" + i);
            if (i == 19) {
                com.byd.sing.a.a("STR_SHUTDOWN_PREPARE");
                MicConnectService.this.f3259b.setKtvResource(2);
                MicConnectService.this.f3259b.setKtvMode(0);
                return;
            }
            if (i == 16) {
                com.byd.sing.a.a("STR_SUSPEND_EXIT");
                MicConnectService.this.f3259b.setKtvResource(1);
                MicConnectService.this.f3259b.setKtvMode(1);
                return;
            }
            if (i == 4) {
                MicConnectService.this.f3259b.setKtvResource(2);
                MicConnectService.this.f3259b.setKtvMode(0);
                com.byd.sing.g.e(0);
                m.e(0);
                com.byd.sing.j.f().b();
                return;
            }
            if (i == 10) {
                MicConnectService.this.f3259b.setKtvResource(1);
                MicConnectService.this.f3259b.setKtvMode(1);
                int iK = com.byd.sing.j.f().k();
                com.byd.sing.g.e(iK);
                m.e(iK);
                if (com.byd.sing.k.e()) {
                    com.byd.sing.view.a.h.U();
                }
            }
        }
    }

    class b implements IServiceConnectionListener {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ boolean f3264b;

            a(boolean z) {
                this.f3264b = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.sing.a.a("onServiceConnected connectState = " + this.f3264b);
                MicConnectService.this.j = this.f3264b;
                if (MicConnectService.this.j) {
                    MicConnectService.this.l();
                }
            }
        }

        /* JADX INFO: renamed from: com.byd.sing.service.MicConnectService$b$b, reason: collision with other inner class name */
        class RunnableC0106b implements Runnable {
            RunnableC0106b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.sing.a.a("onServiceDisconnected");
                MicConnectService.this.j = false;
            }
        }

        b() {
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceConnected(boolean z) {
            MicConnectService.this.e.post(new a(z));
        }

        @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
        public void onServiceDisconnected(boolean z) {
            MicConnectService.this.e.post(new RunnableC0106b());
        }
    }

    class c implements com.byd.sing.d {
        c() {
        }

        @Override // com.byd.sing.d
        public void attach() {
            com.byd.sing.a.a("PuremicTuning attach");
            MicConnectService.this.o(true, false);
        }

        @Override // com.byd.sing.d
        public void detach() {
            com.byd.sing.a.a("PuremicTuning detach");
            MicConnectService.this.p();
        }

        @Override // com.byd.sing.d
        public void onMicPowerChanged(int i, boolean z) {
            com.byd.sing.a.a("PuremicTuning onMicPowerChanged isPowerOn = " + z);
        }

        @Override // com.byd.sing.d
        public void onMicPowerOnInit() {
            com.byd.sing.a.a("PuremicTuning onMicPowerOnInit");
            MicConnectService.this.f3259b.setKtvResource(1);
            MicConnectService.this.f3259b.setKtvMode(1);
            Intent intent = new Intent("byd.intent.minikaraoke_micevent");
            intent.putExtra("android.intent.extra.KEY_EVENT", 131);
            MicConnectService.this.sendBroadcast(intent);
        }
    }

    class d implements com.byd.sing.d {
        d() {
        }

        @Override // com.byd.sing.d
        public void attach() {
            com.byd.sing.a.a("TsBydMicManager attach");
            MicConnectService.this.o(true, true);
        }

        @Override // com.byd.sing.d
        public void detach() {
            com.byd.sing.a.a("TsBydMicManager detach");
            MicConnectService.this.p();
        }

        @Override // com.byd.sing.d
        public void onMicPowerChanged(int i, boolean z) {
        }
    }

    class e extends TaskStackListener {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!com.byd.sing.k.e()) {
                    com.byd.sing.j.f().b();
                } else {
                    if (com.byd.sing.j.f().x(com.byd.sing.view.a.j.class.getSimpleName()) || com.byd.sing.j.f().x(com.byd.sing.view.a.k.class.getSimpleName())) {
                        return;
                    }
                    com.byd.sing.j.f().T();
                }
            }
        }

        e() {
        }

        public void onTaskStackChanged() {
            MicConnectService.this.e.post(new a());
        }
    }

    class f extends IProcessObserver.Stub {

        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (!com.byd.sing.k.e()) {
                    com.byd.sing.j.f().b();
                } else {
                    if (com.byd.sing.j.f().x(com.byd.sing.view.a.j.class.getSimpleName()) || com.byd.sing.j.f().x(com.byd.sing.view.a.k.class.getSimpleName())) {
                        return;
                    }
                    com.byd.sing.j.f().T();
                }
            }
        }

        f() {
        }

        public void onForegroundActivitiesChanged(int i, int i2, boolean z) {
            MicConnectService.this.e.post(new a());
        }

        public void onForegroundServicesChanged(int i, int i2, int i3) {
        }

        public void onProcessDied(int i, int i2) {
        }
    }

    class g extends BroadcastReceiver {
        g() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            com.byd.sing.view.a.g gVarE;
            if ("SplitScreen.Swap.Animation".equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("SplitScreenSwapAnimationStatus", false);
                com.byd.sing.a.a("state = " + booleanExtra);
                if (booleanExtra && (gVarE = com.byd.sing.j.f().e(com.byd.sing.view.a.h.class.getSimpleName())) != null && gVarE.k()) {
                    ((com.byd.sing.view.a.h) gVarE).X();
                }
            }
        }
    }

    class h extends BroadcastReceiver {
        h() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.byd.autoservice.ACTION_POWER".equals(intent.getAction())) {
                boolean booleanExtra = intent.getBooleanExtra("power_mode", false);
                com.byd.sing.a.a("state = " + booleanExtra);
                com.byd.sing.j.f().M(booleanExtra);
                if (booleanExtra) {
                    com.byd.sing.j.f().z();
                    com.byd.sing.j.f().b();
                } else {
                    com.byd.sing.j.f().U();
                    if (com.byd.sing.k.e()) {
                        com.byd.sing.view.a.h.U();
                    }
                }
            }
        }
    }

    private class i extends AbsBYDAutoAudioListener {
        private i() {
        }

        @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
        public void onVolumeChanged(int i, int i2) {
            MicConnectService.this.s(i);
        }

        /* synthetic */ i(MicConnectService micConnectService, a aVar) {
            this();
        }
    }

    private class j extends AbsBYDAutoGearboxListener {
        private j() {
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i) {
            com.byd.sing.a.n("value:" + i + " , lastGearValue:" + MicConnectService.this.t);
            MicConnectService.this.k(i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeLevelChanged(int i) {
            com.byd.sing.a.n("value:" + i + " , lastGearValue:" + MicConnectService.this.t);
            MicConnectService.this.k(i);
        }

        /* synthetic */ j(MicConnectService micConnectService, a aVar) {
            this();
        }
    }

    private static class k extends Binder {
        private k() {
        }

        /* synthetic */ k(a aVar) {
            this();
        }
    }

    private class l extends ContentObserver {

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f3278b;

            a(int i) {
                this.f3278b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                com.byd.sing.j.f().N(this.f3278b);
                if (this.f3278b != 1) {
                    com.byd.sing.j.f().b();
                } else if (com.byd.sing.k.e()) {
                    com.byd.sing.view.a.h.U();
                }
            }
        }

        public l(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            if (uri.getLastPathSegment().equals("navibar_mode_on_screensaver")) {
                int i = Settings.Global.getInt(MicConnectService.this.getContentResolver(), "navibar_mode_on_screensaver", 0);
                com.byd.sing.a.a("screenOffType = " + i);
                MicConnectService.this.e.post(new a(i));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k(int i2) {
        if (com.byd.sing.j.f().r()) {
            if (i2 == 0) {
                com.byd.sing.a.f("invalid value:" + i2);
                return;
            }
            int i3 = this.t;
            if (i3 == i2) {
                return;
            }
            if ((i2 == 1 || i2 == 3 || i2 == 4) && (i3 == 1 || i3 == 3 || i3 == 4)) {
                this.t = i2;
                return;
            }
            this.t = i2;
            if (i2 == 2) {
                com.byd.sing.a.a("onGearChanged mute");
                com.byd.sing.j.f().z();
            } else {
                com.byd.sing.a.a("onGearChanged unMute");
                com.byd.sing.j.f().U();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        com.byd.sing.a.a("initSdk isInitialized = " + this.k);
        if (this.k) {
            return;
        }
        this.k = true;
        int autoType = this.f3260c.getAutoType();
        com.byd.sing.a.n("setKtvMode connect -- autoType:" + autoType);
        if (autoType == 0) {
            autoType = 196;
        }
        com.byd.sing.j.f().J(autoType);
        n();
        m();
        boolean zB = com.byd.sing.g.b();
        boolean zB2 = m.b();
        com.byd.sing.a.a("isConnected = " + zB + " , isConnectedTs = " + zB2);
        if (zB) {
            o(false, false);
        } else if (zB2) {
            o(false, true);
        } else {
            this.f3259b.setKtvMode(0);
        }
        int volume = this.f3259b.getVolume();
        com.byd.sing.a.n("initSdk volume:" + volume);
        s(volume);
    }

    private void m() {
        m.a();
        m.d(new d());
    }

    private void n() {
        com.byd.sing.g.a();
        com.byd.sing.g.d(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o(boolean z, boolean z2) {
        com.byd.sing.a.a("onMicConnected fromAttach:" + z + " fromTs:" + z2);
        if (com.byd.sing.k.g()) {
            this.f3259b.setKtvResource(1);
            this.f3259b.setKtvMode(1);
            com.byd.sing.j.f().G(true);
            if (z2) {
                com.byd.sing.j.f().L();
            } else {
                com.byd.sing.j.f().K();
            }
            if (z && com.byd.sing.f.a(this) && com.byd.sing.f.b(this)) {
                com.byd.sing.j.f().S(new com.byd.sing.view.a.i(1));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        com.byd.sing.a.a("onMicDisconnected");
        com.byd.sing.j.f().G(false);
        com.byd.sing.j.f().H(false);
        com.byd.sing.j.f().I(false);
        this.f3259b.setKtvResource(2);
        this.f3259b.setKtvMode(0);
        com.byd.sing.j.f().c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(int i2) {
        com.byd.sing.g.g(i2);
    }

    public static void t(Context context) {
        context.startService(new Intent(context, (Class<?>) MicConnectService.class));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new k(null);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        com.byd.sing.a.e("onConfigurationChanged mOrientation = %s, newOrientation = %s,mUiMode = %s, newUiMode = %s,", Integer.valueOf(this.f), Integer.valueOf(configuration.orientation), Integer.valueOf(this.g), Integer.valueOf(configuration.uiMode));
        if (this.f != configuration.orientation) {
            com.byd.sing.j.f().F(null);
            org.greenrobot.eventbus.c.c().k(new com.byd.sing.service.b());
        }
        this.f = configuration.orientation;
        if (this.g != configuration.uiMode) {
            com.byd.sing.j.f().A();
        }
        this.g = configuration.uiMode;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.byd.sing.a.a("onCreate");
        Configuration configuration = getResources().getConfiguration();
        this.f = configuration.orientation;
        this.g = configuration.uiMode;
        a aVar = null;
        getContentResolver().registerContentObserver(Settings.Global.getUriFor("navibar_mode_on_screensaver"), false, new l(null));
        this.f3261d = ActivityManager.getService();
        this.f3260c = BYDAutoBodyworkDevice.getInstance(this);
        this.m = BYDAutoGearboxDevice.getInstance(this);
        j jVar = new j(this, aVar);
        this.n = jVar;
        this.m.registerListener((AbsBYDAutoGearboxListener) jVar);
        BYDAutoAudioDevice bYDAutoAudioDevice = BYDAutoAudioDevice.getInstance(this);
        this.f3259b = bYDAutoAudioDevice;
        bYDAutoAudioDevice.registerConnectionListener(this.o);
        i iVar = new i(this, aVar);
        this.l = iVar;
        this.f3259b.registerListener((AbsBYDAutoAudioListener) iVar);
        r();
        BYDAutoPowerDevice.getInstance(this).registerListener((AbsBYDAutoPowerListener) new a());
        q();
    }

    @Override // android.app.Service
    public void onDestroy() {
        j jVar;
        super.onDestroy();
        com.byd.sing.a.a("onDestroy");
        u();
        BYDAutoAudioDevice bYDAutoAudioDevice = this.f3259b;
        if (bYDAutoAudioDevice != null) {
            bYDAutoAudioDevice.unregisterConnectionListener(this.o);
            i iVar = this.l;
            if (iVar != null) {
                this.f3259b.unregisterListener((AbsBYDAutoAudioListener) iVar);
                this.l = null;
            }
        }
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.m;
        if (bYDAutoGearboxDevice != null && (jVar = this.n) != null) {
            bYDAutoGearboxDevice.unregisterListener((AbsBYDAutoGearboxListener) jVar);
        }
        BluetoothCallReceiver bluetoothCallReceiver = this.h;
        if (bluetoothCallReceiver != null) {
            unregisterReceiver(bluetoothCallReceiver);
        }
        WarmingReceiver warmingReceiver = this.i;
        if (warmingReceiver != null) {
            unregisterReceiver(warmingReceiver);
        }
        BroadcastReceiver broadcastReceiver = this.r;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        BroadcastReceiver broadcastReceiver2 = this.s;
        if (broadcastReceiver2 != null) {
            unregisterReceiver(broadcastReceiver2);
        }
        startService(new Intent(this, (Class<?>) MicConnectService.class));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return 1;
    }

    public void q() {
        try {
            IActivityManager iActivityManager = this.f3261d;
            if (iActivityManager != null) {
                iActivityManager.registerProcessObserver(this.q);
                this.f3261d.registerTaskStackListener(this.p);
            }
        } catch (RemoteException e2) {
            com.byd.sing.a.c("registerProcessObserver error", e2);
        }
    }

    public void r() {
        this.h = new BluetoothCallReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.headsetclient.profile.action.AG_CALL_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.h, intentFilter);
        this.i = new WarmingReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.byd.system.dialog.ACTION_SHOW");
        registerReceiver(this.i, intentFilter2);
        registerReceiver(this.r, new IntentFilter("SplitScreen.Swap.Animation"));
        registerReceiver(this.s, new IntentFilter("com.byd.autoservice.ACTION_POWER"));
    }

    public void u() {
        try {
            IActivityManager iActivityManager = this.f3261d;
            if (iActivityManager != null) {
                iActivityManager.unregisterProcessObserver(this.q);
                this.f3261d.unregisterTaskStackListener(this.p);
            }
        } catch (RemoteException e2) {
            com.byd.sing.a.c("unregisterProcessObserver error", e2);
        }
    }
}
