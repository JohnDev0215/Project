package com.ts.avm.sdk.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import c.b.a.a;
import c.b.a.b;
import c.b.a.c;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import java.util.ArrayList;

/* JADX INFO: compiled from: AvmManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AvmManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2619a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile AvmManager f2620b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2621c;
    private d g;
    private HandlerThread h;
    private ServiceConnection i;
    private c.b.a.b j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2622d = false;
    private boolean e = false;
    private boolean f = false;
    private int o = 0;
    private int p = 0;
    private final c.a q = new BinderC0068a();
    private final a.AbstractBinderC0055a r = new b();
    private final ArrayList<com.ts.avm.sdk.a> k = new ArrayList<>();
    private final ArrayList<AvmManager.b> l = new ArrayList<>();
    private final ArrayList<AvmManager.c> m = new ArrayList<>();
    private final ArrayList<AvmManager.a> n = new ArrayList<>();

    /* JADX INFO: renamed from: com.ts.avm.sdk.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class BinderC0068a extends c.a {
        BinderC0068a() {
        }

        @Override // c.b.a.c
        public void c(int i, String str) throws RemoteException {
            a.this.o = i;
            synchronized (a.this.l) {
                com.ts.avm.sdk.c.a.b(a.f2619a, "mStatusListenerList " + a.this.l.size());
                for (AvmManager.b bVar : a.this.l) {
                    if (bVar != null) {
                        bVar.a(i, str);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class b extends a.AbstractBinderC0055a {
        b() {
        }

        @Override // c.b.a.a
        public void e(CmdBean cmdBean) throws RemoteException {
            com.ts.avm.sdk.c.a.b(a.f2619a, "onCommandReceived id:" + cmdBean.f());
            if (cmdBean.f() == 229376) {
                a.this.p = cmdBean.g()[0];
                synchronized (a.this.m) {
                    for (AvmManager.c cVar : a.this.m) {
                        if (cVar != null) {
                            cVar.a(cmdBean.g()[0]);
                        }
                    }
                }
                return;
            }
            if (cmdBean.f() == 67108992) {
                synchronized (a.this.n) {
                    for (AvmManager.a aVar : a.this.n) {
                        if (aVar != null) {
                            aVar.a(cmdBean.g()[0]);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class c implements ServiceConnection {
        c() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.ts.avm.sdk.c.a.b(a.f2619a, "onServiceConnected: " + Thread.currentThread().getName());
            a.this.j = b.a.k(iBinder);
            try {
                if (a.this.j.g(a.this.q) < 0) {
                    com.ts.avm.sdk.c.a.d(a.f2619a, "registerAvmStatusListener ERROR: ", new Throwable());
                }
                if (a.this.j.d(a.this.r) < 0) {
                    com.ts.avm.sdk.c.a.d(a.f2619a, "registerCommandListener ERROR: ", new Throwable());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            synchronized (a.this.k) {
                if (a.this.j == null) {
                    for (com.ts.avm.sdk.a aVar : a.this.k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    com.ts.avm.sdk.c.a.e(a.f2619a, "onServiceConnected: IBinder is null!");
                    return;
                }
                com.ts.avm.sdk.c.a.e(a.f2619a, "onServiceConnected: Binder is success.");
                for (com.ts.avm.sdk.a aVar2 : a.this.k) {
                    if (aVar2 != null) {
                        aVar2.a();
                    }
                }
                a.this.f2622d = true;
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.ts.avm.sdk.c.a.e(a.f2619a, "onServiceConnected: Unbinder is success.");
            a.this.f2622d = false;
            a.this.e = false;
            synchronized (a.this.k) {
                if (a.this.j == null) {
                    for (com.ts.avm.sdk.a aVar : a.this.k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    com.ts.avm.sdk.c.a.e(a.f2619a, "onServiceConnected: IBinder is null!");
                    return;
                }
                com.ts.avm.sdk.c.a.e(a.f2619a, "onServiceConnected: Binder is success.");
                for (com.ts.avm.sdk.a aVar2 : a.this.k) {
                    if (aVar2 != null) {
                        aVar2.b();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class d extends Handler {
        public d(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                com.ts.avm.sdk.c.a.b(a.f2619a, "handleMessage MSG_RETRY_BIND_SERVICE");
                a.this.A();
            }
        }
    }

    private a() {
        HandlerThread handlerThread = new HandlerThread("retry_thread_avm");
        this.h = handlerThread;
        handlerThread.start();
        this.g = new d(this.h.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A() {
        com.ts.avm.sdk.c.a.f(f2619a, "reBindService: " + this.f2622d);
        if (this.f2622d) {
            return;
        }
        y();
    }

    private void C(Context context) {
        this.f2621c = context;
    }

    private void D() {
        HandlerThread handlerThread = this.h;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.h = null;
            this.g = null;
        }
        if (this.f2622d && this.e) {
            this.f2621c.unbindService(z());
            this.f2622d = false;
            this.e = false;
        }
    }

    public static synchronized AvmManager c() {
        if (f2620b == null) {
            f2620b = new a();
        }
        return f2620b;
    }

    private void y() {
        d dVar;
        Intent intent = new Intent("com.ts.avm.AvmAndroidService");
        intent.setComponent(new ComponentName("com.ts.avm", "com.ts.avm.AvmAndroidService"));
        if (this.f2621c == null) {
            com.ts.avm.sdk.c.a.c(f2619a, "ERROR: Should Call setContext first.");
            return;
        }
        String str = f2619a;
        com.ts.avm.sdk.c.a.e(str, "BindService Connection:");
        this.e = this.f2621c.bindService(intent, z(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("BindService Result:");
        sb.append(this.e);
        sb.append(" ");
        sb.append(this.g == null);
        com.ts.avm.sdk.c.a.e(str, sb.toString());
        if (this.e || (dVar = this.g) == null) {
            return;
        }
        if (dVar.hasMessages(0)) {
            this.g.removeMessages(0);
        }
        this.g.sendEmptyMessageDelayed(0, 1000L);
    }

    private ServiceConnection z() {
        synchronized (a.class) {
            if (this.i == null) {
                this.i = new c();
            }
        }
        return this.i;
    }

    public int B(AvmManager.b bVar) {
        synchronized (this.l) {
            if (bVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.l.contains(bVar)) {
                com.ts.avm.sdk.c.a.d(f2619a, "registerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.l.add(bVar);
            return 0;
        }
    }

    public int E(AvmManager.b bVar) {
        synchronized (this.l) {
            if (bVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.l.contains(bVar)) {
                this.l.remove(bVar);
                return 0;
            }
            com.ts.avm.sdk.c.a.d(f2619a, "unregisterStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void a(com.ts.avm.sdk.a aVar) {
        synchronized (this.k) {
            if (aVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "deinit: NULL Object input.", new Throwable());
            } else if (this.k.contains(aVar)) {
                this.k.remove(aVar);
            } else {
                com.ts.avm.sdk.c.a.d(f2619a, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            D();
            this.f = false;
            this.f2621c = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int b() {
        synchronized (this) {
            if (!this.f) {
                com.ts.avm.sdk.c.a.d(f2619a, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            c.b.a.b bVar = this.j;
            if (bVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                return bVar.i();
            } catch (RemoteException e) {
                e.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void d(Context context, com.ts.avm.sdk.a aVar) {
        synchronized (this.k) {
            if (!this.k.contains(aVar)) {
                this.k.add(aVar);
            }
        }
        synchronized (this) {
            C(context);
            y();
            this.f = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int e(AvmManager.a aVar) {
        synchronized (this.n) {
            if (aVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "registerAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.n.contains(aVar)) {
                com.ts.avm.sdk.c.a.d(f2619a, "registerAvmDockerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.n.add(aVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int f(AvmManager.b bVar) {
        return B(bVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int g() {
        synchronized (this) {
            if (!this.f) {
                com.ts.avm.sdk.c.a.d(f2619a, "show ERROR: should call init first.", new Throwable());
                return -1;
            }
            c.b.a.b bVar = this.j;
            if (bVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "show ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                bVar.b(com.ts.avm.sdk.d.b.a(AvmManager.State.START));
                com.ts.avm.sdk.c.a.b(f2619a, "show: called success");
                return 0;
            } catch (RemoteException e) {
                e.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int h(AvmManager.a aVar) {
        synchronized (this.n) {
            if (aVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "unregisterAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.n.contains(aVar)) {
                this.n.remove(aVar);
                return 0;
            }
            com.ts.avm.sdk.c.a.d(f2619a, "unregisterAvmDockerStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int i(AvmManager.b bVar) {
        return E(bVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int j(AvmManager.VoiceEvent voiceEvent) {
        synchronized (this) {
            if (!this.f) {
                com.ts.avm.sdk.c.a.d(f2619a, "voiceRecognitionControl ERROR: should call init first.", new Throwable());
                return -1;
            }
            c.b.a.b bVar = this.j;
            if (bVar == null) {
                com.ts.avm.sdk.c.a.d(f2619a, "voiceRecognitionControl ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                bVar.b(com.ts.avm.sdk.d.b.a(voiceEvent));
                com.ts.avm.sdk.c.a.b(f2619a, "voiceRecognitionControl: called success");
                return 0;
            } catch (RemoteException e) {
                e.printStackTrace();
                return -2;
            }
        }
    }
}
