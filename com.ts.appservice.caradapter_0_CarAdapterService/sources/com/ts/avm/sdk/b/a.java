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
import b.a.a.a;
import b.a.a.b;
import b.a.a.c;
import b.a.a.d;
import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import java.util.ArrayList;

/* JADX INFO: compiled from: AvmManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AvmManager {
    private static final String t = "a";
    private static volatile AvmManager u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f92a;
    private e e;
    private HandlerThread f;
    private ServiceConnection g;
    private b.a.a.c h;
    private int n;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f93b = false;
    private boolean c = false;
    private boolean d = false;
    private int o = 0;
    private int p = -1;
    private final d.a q = new BinderC0004a();
    private final b.a r = new b();
    private final a.AbstractBinderC0001a s = new c();
    private final ArrayList<com.ts.avm.sdk.a> i = new ArrayList<>();
    private final ArrayList<AvmManager.c> j = new ArrayList<>();
    private final ArrayList<AvmManager.d> k = new ArrayList<>();
    private final ArrayList<AvmManager.b> l = new ArrayList<>();
    private final ArrayList<AvmManager.a> m = new ArrayList<>();

    /* JADX INFO: renamed from: com.ts.avm.sdk.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class BinderC0004a extends d.a {
        BinderC0004a() {
        }

        @Override // b.a.a.d
        public void g(int i, String str) {
            a.this.n = i;
            synchronized (a.this.j) {
                com.ts.avm.sdk.c.a.b(a.t, "mStatusListenerList " + a.this.j.size());
                for (AvmManager.c cVar : a.this.j) {
                    if (cVar != null) {
                        cVar.a(i, str);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class b extends b.a {
        b() {
        }

        @Override // b.a.a.b
        public void k(CmdBean cmdBean) {
            com.ts.avm.sdk.c.a.b(a.t, "onCommandReceived id:" + cmdBean.a());
            if (cmdBean.a() == 229376) {
                a.this.o = cmdBean.b()[0];
                synchronized (a.this.k) {
                    for (AvmManager.d dVar : a.this.k) {
                        if (dVar != null) {
                            dVar.a(a.this.o);
                        }
                    }
                }
                return;
            }
            if (cmdBean.a() != 67108992) {
                if (cmdBean.a() == 33554690) {
                    a.this.z();
                    return;
                }
                return;
            }
            synchronized (a.this.l) {
                for (AvmManager.b bVar : a.this.l) {
                    if (bVar != null) {
                        bVar.a(cmdBean.b()[0]);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class c extends a.AbstractBinderC0001a {
        c() {
        }

        @Override // b.a.a.a
        public void m(String str, int i, int i2) {
            String strSubstring = str != null ? str.length() > 4 ? str.substring(str.length() - 4, str.length()) : str : "";
            com.ts.avm.sdk.c.a.b(a.t, " onAvmCallStatusChanged: phonenum:" + strSubstring + " calltype:" + i + " action:" + i2);
            synchronized (a.this.m) {
                for (AvmManager.a aVar : a.this.m) {
                    if (aVar != null) {
                        aVar.a(str, i, i2);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class d implements ServiceConnection {
        d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.ts.avm.sdk.c.a.b(a.t, "onServiceConnected: " + Thread.currentThread().getName());
            a.this.h = c.a.a(iBinder);
            try {
                if (a.this.h.c(a.this.q) < 0) {
                    com.ts.avm.sdk.c.a.d(a.t, "registerAvmStatusListener ERROR: ", new Throwable());
                }
                if (a.this.h.j(a.this.r) < 0) {
                    com.ts.avm.sdk.c.a.d(a.t, "registerCommandListener ERROR: ", new Throwable());
                }
                if (a.this.h.h(a.this.s) < 0) {
                    com.ts.avm.sdk.c.a.d(a.t, "registerCallStatusListener ERROR: ", new Throwable());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            synchronized (a.this.i) {
                if (a.this.h == null) {
                    for (com.ts.avm.sdk.a aVar : a.this.i) {
                        if (aVar != null) {
                            aVar.a(0, "IBinder is null");
                        }
                    }
                    com.ts.avm.sdk.c.a.e(a.t, "onServiceConnected: IBinder is null!");
                    return;
                }
                com.ts.avm.sdk.c.a.e(a.t, "onServiceConnected: Binder is success.");
                for (com.ts.avm.sdk.a aVar2 : a.this.i) {
                    if (aVar2 != null) {
                        aVar2.b();
                    }
                }
                a.this.f93b = true;
                a.this.z();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            com.ts.avm.sdk.c.a.e(a.t, "onServiceConnected: Unbinder is success.");
            a.this.f93b = false;
            a.this.c = false;
            synchronized (a.this.i) {
                if (a.this.h == null) {
                    for (com.ts.avm.sdk.a aVar : a.this.i) {
                        if (aVar != null) {
                            aVar.a(0, "IBinder is null");
                        }
                    }
                    com.ts.avm.sdk.c.a.e(a.t, "onServiceConnected: IBinder is null!");
                    return;
                }
                com.ts.avm.sdk.c.a.e(a.t, "onServiceConnected: Binder is success.");
                for (com.ts.avm.sdk.a aVar2 : a.this.i) {
                    if (aVar2 != null) {
                        aVar2.c();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                com.ts.avm.sdk.c.a.b(a.t, "handleMessage MSG_RETRY_BIND_SERVICE");
                a.this.y();
            }
        }
    }

    private a() {
        HandlerThread handlerThread = new HandlerThread("retry_thread_avm");
        this.f = handlerThread;
        handlerThread.start();
        this.e = new e(this.f.getLooper());
    }

    private void A(Context context) {
        this.f92a = context;
    }

    private void C() {
        HandlerThread handlerThread = this.f;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f = null;
            this.e = null;
        }
        if (this.f93b && this.c) {
            this.f92a.unbindService(x());
            this.f93b = false;
            this.c = false;
        }
    }

    public static synchronized AvmManager b() {
        if (u == null) {
            u = new a();
        }
        return u;
    }

    private void w() {
        e eVar;
        Intent intent = new Intent("com.ts.avm.AvmAndroidService");
        intent.setComponent(new ComponentName("com.ts.avm", "com.ts.avm.AvmAndroidService"));
        if (this.f92a == null) {
            com.ts.avm.sdk.c.a.c(t, "ERROR: Should Call setContext first.");
            return;
        }
        com.ts.avm.sdk.c.a.e(t, "BindService Connection:");
        this.c = this.f92a.bindService(intent, x(), 1);
        String str = t;
        StringBuilder sb = new StringBuilder();
        sb.append("BindService Result:");
        sb.append(this.c);
        sb.append(" ");
        sb.append(this.e == null);
        com.ts.avm.sdk.c.a.e(str, sb.toString());
        if (this.c || (eVar = this.e) == null) {
            return;
        }
        if (eVar.hasMessages(0)) {
            this.e.removeMessages(0);
        }
        this.e.sendEmptyMessageDelayed(0, 1000L);
    }

    private ServiceConnection x() {
        synchronized (a.class) {
            if (this.g == null) {
                this.g = new d();
            }
        }
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        com.ts.avm.sdk.c.a.f(t, "reBindService: " + this.f93b);
        if (this.f93b) {
            return;
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        int i = this.p;
        if (i == -1) {
            com.ts.avm.sdk.c.a.b(t, "reSetProvisionFinishedByCache: ProvisionFinished maybe never be set" + this.p);
            return;
        }
        B(i);
        com.ts.avm.sdk.c.a.b(t, "reSetProvisionFinishedByCache:" + this.p);
    }

    public int B(int i) {
        com.ts.avm.sdk.c.a.b(t, "setProvisionFinished mode:" + i);
        com.ts.avm.sdk.d.a aVar = new com.ts.avm.sdk.d.a();
        aVar.b(33554690);
        aVar.c(9);
        aVar.d(7);
        aVar.e(new int[]{i, 0, 0, 0});
        CmdBean cmdBeanA = aVar.a();
        synchronized (this) {
            if (!this.d) {
                com.ts.avm.sdk.c.a.d(t, "setProvisionFinished ERROR: should call init first.", new Throwable());
                return -1;
            }
            if (this.h == null) {
                com.ts.avm.sdk.c.a.d(t, "setProvisionFinished ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                this.p = i;
                this.h.e(cmdBeanA);
                com.ts.avm.sdk.c.a.b(t, "setProvisionFinished: called success value = " + i);
                return 0;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void a(com.ts.avm.sdk.a aVar) {
        synchronized (this.i) {
            if (aVar == null) {
                com.ts.avm.sdk.c.a.d(t, "deinit: NULL Object input.", new Throwable());
            } else if (this.i.contains(aVar)) {
                this.i.remove(aVar);
            } else {
                com.ts.avm.sdk.c.a.d(t, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            C();
            this.d = false;
            this.f92a = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void c(Context context, com.ts.avm.sdk.a aVar) {
        synchronized (this.i) {
            if (!this.i.contains(aVar)) {
                this.i.add(aVar);
            }
        }
        synchronized (this) {
            A(context);
            w();
            this.d = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int d(boolean z) {
        com.ts.avm.sdk.c.a.b(t, "switchRadarAlarmToneStatus : isOn? :" + z);
        com.ts.avm.sdk.d.a aVar = new com.ts.avm.sdk.d.a();
        aVar.b(129);
        aVar.c(9);
        aVar.d(7);
        aVar.e(new int[]{z ? 1 : 0, 0, 0, 0});
        CmdBean cmdBeanA = aVar.a();
        synchronized (this) {
            if (!this.d) {
                com.ts.avm.sdk.c.a.d(t, "switchRadarAlarmToneStatus ERROR: should call init first", new Throwable());
                return -1;
            }
            if (this.h == null) {
                com.ts.avm.sdk.c.a.d(t, "switchRadarAlarmToneStatus ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                this.h.e(cmdBeanA);
                com.ts.avm.sdk.c.a.b(t, "switchRadarAlarmToneStatus: called success value = " + (z ? 1 : 0));
                return 0;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return -2;
            }
        }
    }
}
