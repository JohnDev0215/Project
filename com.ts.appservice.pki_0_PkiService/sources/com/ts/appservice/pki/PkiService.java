package com.ts.appservice.pki;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import com.ts.appservice.pki.b.b;
import com.ts.appservice.pki.c.c;
import com.ts.appservice.pki.c.d;
import com.ts.appservice.pki.test.PkiInstallTestManager;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class PkiService extends Service implements IHwBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    b f9a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    com.ts.appservice.pki.b.a f10b;
    private b.a.a.a.b c;
    private d d;
    private com.ts.appservice.pki.c.b e;
    private c f;
    private a g;
    private HandlerThread h;
    private boolean i = false;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 108) {
                return;
            }
            PkiService.this.b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        try {
            this.c = b.a.a.a.b.t();
            com.ts.appservice.pki.d.b.f("PkiService", " tryConnectService mTeeService " + this.c);
        } catch (Exception e) {
            com.ts.appservice.pki.d.b.d("PkiService", "tryConnectService failed, msg:" + e.getMessage());
        }
        b.a.a.a.b bVar = this.c;
        if (bVar == null) {
            this.g.sendEmptyMessageDelayed(CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256, 1000L);
        } else {
            bVar.asBinder().linkToDeath(this, 0L);
            c();
        }
    }

    private void c() {
        com.ts.appservice.pki.d.b.f("PkiService", "updateTeeService, hasCreated : " + this.i);
        if (this.i) {
            this.f.I(this.c);
            this.d.I(this.c);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        String action = intent.getAction();
        com.ts.appservice.pki.d.b.f("PkiService", " onBind" + action);
        if ("com.ts.appservice.pki.PkiSdkBinder".equals(action)) {
            return this.f9a;
        }
        if ("com.ts.appservice.pki.PkiInstallBinder".equals(action)) {
            return this.f10b;
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.ts.appservice.pki.d.b.f("PkiService", "PkiService onCreate");
        HandlerThread handlerThread = new HandlerThread("PkiService");
        this.h = handlerThread;
        handlerThread.start();
        this.g = new a(this.h.getLooper());
        b();
        com.ts.appservice.pki.c.b bVar = new com.ts.appservice.pki.c.b();
        this.e = bVar;
        bVar.c(this);
        d dVar = new d();
        this.d = dVar;
        dVar.w(this.c, this.e);
        c cVar = new c();
        this.f = cVar;
        cVar.w(this.c, this.e);
        b bVar2 = new b(this);
        this.f9a = bVar2;
        bVar2.a(this.d, this.f);
        com.ts.appservice.pki.b.a aVar = new com.ts.appservice.pki.b.a();
        this.f10b = aVar;
        aVar.h(this.d, this.f);
        PkiInstallTestManager.getInstance().init(this, this.d, this.f);
        this.i = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        com.ts.appservice.pki.b.a aVar = this.f10b;
        if (aVar != null) {
            aVar.l();
        }
        PkiInstallTestManager.getInstance().onDestroy();
        this.i = false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        com.ts.appservice.pki.d.b.f("PkiService", "PkiService onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    public void serviceDied(long j) {
        com.ts.appservice.pki.d.b.d("PkiService", "binderDied: tryConnectService!!!");
        try {
            if (this.c != null && this.c.asBinder() != null) {
                this.c.asBinder().unlinkToDeath(this);
            }
        } catch (Exception e) {
            com.ts.appservice.pki.d.b.d("PkiService", "unlinkToDeath failed, msg:" + e.getMessage());
        }
        this.c = null;
        if (this.i) {
            this.f.I(null);
            this.d.I(null);
        }
        b();
    }
}
