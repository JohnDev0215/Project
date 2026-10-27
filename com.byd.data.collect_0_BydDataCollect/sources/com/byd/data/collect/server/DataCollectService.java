package com.byd.data.collect.server;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import com.byd.data.common.server.CloudCanService;
import com.byd.data.common.sqlite.o;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class DataCollectService extends Service {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f3220g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private com.byd.data.collect.h.a f3221h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private com.byd.data.collect.h.b f3222i;
    private h j;
    private com.byd.data.common.server.d.a k;
    private BroadcastReceiver l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3215b = "DataCollectService";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f3216c = 1000;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final byte f3217d = 33;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final byte f3218e = 30;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final byte f3219f = 66;
    private final String m = "com.byd.data.collect.server.cleardata";
    private final String n = "com.byd.data.CollectService";
    private final String o = "com.byd.data.EnergyService";

    class a extends BroadcastReceiver {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void b(String str) {
            com.byd.data.common.i.c.a(str);
            com.byd.data.common.i.f.g("DataCollectService", "clear db is end");
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.byd.data.collect.server.cleardata".equals(intent.getAction())) {
                final String strH = com.byd.data.common.g.a.i().h();
                com.byd.data.common.i.f.g("DataCollectService", "clear db path = " + strH);
                if (new File(strH).exists()) {
                    new Thread(new Runnable() { // from class: com.byd.data.collect.server.a
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f3224b.b(strH);
                        }
                    }).start();
                    com.bydux.api.common.views.window.a.a(context, "清除数据库成功");
                }
            }
        }
    }

    private void b(com.byd.data.collect.k.d dVar) {
        g.g(this).e();
        Handler handler = this.f3220g;
        handler.sendMessageDelayed(handler.obtainMessage(33, 0, dVar.Y() ? 0 : 30), 1000L);
        com.byd.data.common.i.f.b("DataCollectService", "delayCollectData data collect after delayed 1 second");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Message message) {
        int i2 = message.what;
        com.byd.data.common.server.b bVarH = com.byd.data.common.server.b.h(this);
        if (i2 == 33) {
            com.byd.data.common.server.e.a aVarO = i.o(com.byd.data.common.server.b.h(this).f());
            boolean z = bVarH.g().isEncrypt;
            com.byd.data.collect.k.d dVarS = com.byd.data.collect.k.d.S(this);
            boolean z2 = message.arg1 == 1;
            aVarO.h(dVarS.T(this, true, z, z2), z2);
            aVarO.h(dVarS.T(this, false, z, z2), z2);
            int i3 = message.arg2;
            int i4 = i3 > 0 ? i3 - 1 : 0;
            Message messageObtainMessage = this.f3220g.obtainMessage(33, 1 == i4 ? 1 : 0, i4);
            if (z2 && message.arg2 == message.arg1 && !dVarS.Y()) {
                messageObtainMessage.arg1 = 0;
                return true;
            }
            this.f3220g.sendMessageDelayed(messageObtainMessage, 1000L);
        } else if (i2 == 66) {
            if (bVarH.n(this)) {
                b(com.byd.data.collect.k.d.S(this));
            } else {
                Handler handler = this.f3220g;
                handler.sendMessageDelayed(handler.obtainMessage(66), 4000L);
            }
        }
        return true;
    }

    public void a(boolean z) {
        Handler handler = this.f3220g;
        if (handler == null) {
            return;
        }
        if (z && handler.hasMessages(33)) {
            return;
        }
        this.f3220g.removeMessages(33);
        this.f3220g.sendMessage(this.f3220g.obtainMessage(33, 0, z ? 0 : 30));
    }

    public void c() {
        Handler handler = this.f3220g;
        if (handler == null) {
            return;
        }
        handler.removeMessages(33);
        Handler handler2 = this.f3220g;
        handler2.sendMessage(handler2.obtainMessage(33, 1, 0));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        com.byd.data.common.i.f.g("DataCollectService", "onBind getAction:" + action);
        if ("com.byd.data.CollectService".equals(action)) {
            return this.f3221h;
        }
        if ("com.byd.data.EnergyService".equals(action)) {
            return this.f3222i;
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3221h = new com.byd.data.collect.h.a(this);
        this.f3222i = new com.byd.data.collect.h.b(this);
        h hVarH = h.h(this);
        this.j = hVarH;
        hVarH.j();
        HandlerThread handlerThread = new HandlerThread("DataCollectService");
        handlerThread.start();
        Intent intent = new Intent(getApplicationContext(), (Class<?>) CloudCanService.class);
        com.byd.data.common.server.d.a aVar = new com.byd.data.common.server.d.a();
        this.k = aVar;
        bindService(intent, aVar, 1);
        Handler handler = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.byd.data.collect.server.c
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3227b.d(message);
            }
        });
        this.f3220g = handler;
        handler.sendEmptyMessageDelayed(66, 2000L);
        this.f3220g.post(new Runnable() { // from class: com.byd.data.collect.server.b
            @Override // java.lang.Runnable
            public final void run() {
                o.f().e().a();
            }
        });
        com.byd.data.common.i.f.g("DataCollectService", "onCreate is end version =1.17.1-202507021749sop");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.data.collect.server.cleardata");
        BroadcastReceiver aVar2 = new a();
        this.l = aVar2;
        registerReceiver(aVar2, intentFilter);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.byd.data.common.server.b.e().w();
        com.byd.data.collect.server.k.a aVarG = g.g(this);
        if (aVarG != null && aVarG.c()) {
            aVarG.d();
        }
        h hVar = this.j;
        if (hVar != null) {
            hVar.k();
        }
        Handler handler = this.f3220g;
        if (handler != null) {
            handler.getLooper().quit();
            this.f3220g = null;
        }
        com.byd.data.common.server.d.a aVar = this.k;
        if (aVar != null) {
            if (aVar.b()) {
                unbindService(this.k);
            }
            this.k = null;
        }
        com.byd.data.common.g.a.i().l();
        BroadcastReceiver broadcastReceiver = this.l;
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        com.byd.data.common.i.f.g("DataCollectService", "onDestroy is end");
    }
}
