package defpackage;

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
import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import defpackage.hl;
import defpackage.il;
import defpackage.jl;
import defpackage.kl;
import java.util.ArrayList;

/* JADX INFO: compiled from: AvmManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class ml extends AvmManager {
    public static final String a = "ml";
    public static volatile AvmManager b;
    public Context c;
    public e g;
    public HandlerThread h;
    public ServiceConnection i;
    public jl j;
    public boolean d = false;
    public boolean e = false;
    public boolean f = false;
    public int p = 0;
    public int q = 0;
    public int r = -1;
    public final kl.a s = new a();
    public final il.a t = new b();
    public final hl.a u = new c();
    public final ArrayList<ll> k = new ArrayList<>();
    public final ArrayList<AvmManager.c> l = new ArrayList<>();
    public final ArrayList<AvmManager.d> m = new ArrayList<>();
    public final ArrayList<AvmManager.b> n = new ArrayList<>();
    public final ArrayList<AvmManager.a> o = new ArrayList<>();

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class a extends kl.a {
        public a() {
        }

        @Override // defpackage.kl
        public void c(int i, String str) throws RemoteException {
            ml.this.p = i;
            synchronized (ml.this.l) {
                nl.b(ml.a, "mStatusListenerList " + ml.this.l.size());
                for (AvmManager.c cVar : ml.this.l) {
                    if (cVar != null) {
                        cVar.b(i, str);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class b extends il.a {
        public b() {
        }

        @Override // defpackage.il
        public void e(CmdBean cmdBean) throws RemoteException {
            nl.b(ml.a, "onCommandReceived id:" + cmdBean.f());
            if (cmdBean.f() == 229376) {
                ml.this.q = cmdBean.g()[0];
                synchronized (ml.this.m) {
                    for (AvmManager.d dVar : ml.this.m) {
                        if (dVar != null) {
                            dVar.a(ml.this.q);
                        }
                    }
                }
                return;
            }
            if (cmdBean.f() != 67108992) {
                if (cmdBean.f() == 33554690) {
                    ml.this.D();
                    return;
                }
                return;
            }
            synchronized (ml.this.n) {
                for (AvmManager.b bVar : ml.this.n) {
                    if (bVar != null) {
                        bVar.a(cmdBean.g()[0]);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class c extends hl.a {
        public c() {
        }

        @Override // defpackage.hl
        public void i(String str, int i, int i2) throws RemoteException {
            String strSubstring = str != null ? str.length() > 4 ? str.substring(str.length() - 4, str.length()) : str : "";
            nl.b(ml.a, " onAvmCallStatusChanged: phonenum:" + strSubstring + " calltype:" + i + " action:" + i2);
            synchronized (ml.this.o) {
                for (AvmManager.a aVar : ml.this.o) {
                    if (aVar != null) {
                        aVar.a(str, i, i2);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class d implements ServiceConnection {
        public d() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            nl.b(ml.a, "onServiceConnected: " + Thread.currentThread().getName());
            ml.this.j = jl.a.l(iBinder);
            try {
                if (ml.this.j.g(ml.this.s) < 0) {
                    nl.d(ml.a, "registerAvmStatusListener ERROR: ", new Throwable());
                }
                if (ml.this.j.d(ml.this.t) < 0) {
                    nl.d(ml.a, "registerCommandListener ERROR: ", new Throwable());
                }
                if (ml.this.j.k(ml.this.u) < 0) {
                    nl.d(ml.a, "registerCallStatusListener ERROR: ", new Throwable());
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            synchronized (ml.this.k) {
                if (ml.this.j == null) {
                    for (ll llVar : ml.this.k) {
                        if (llVar != null) {
                            llVar.onError(0, "IBinder is null");
                        }
                    }
                    nl.e(ml.a, "onServiceConnected: IBinder is null!");
                    return;
                }
                nl.e(ml.a, "onServiceConnected: Binder is success.");
                for (ll llVar2 : ml.this.k) {
                    if (llVar2 != null) {
                        llVar2.c();
                    }
                }
                ml.this.d = true;
                ml.this.D();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            nl.e(ml.a, "onServiceConnected: Unbinder is success.");
            ml.this.d = false;
            ml.this.e = false;
            synchronized (ml.this.k) {
                if (ml.this.j == null) {
                    for (ll llVar : ml.this.k) {
                        if (llVar != null) {
                            llVar.onError(0, "IBinder is null");
                        }
                    }
                    nl.e(ml.a, "onServiceConnected: IBinder is null!");
                    return;
                }
                nl.e(ml.a, "onServiceConnected: Binder is success.");
                for (ll llVar2 : ml.this.k) {
                    if (llVar2 != null) {
                        llVar2.d();
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class e extends Handler {
        public e(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                nl.b(ml.a, "handleMessage MSG_RETRY_BIND_SERVICE");
                ml.this.C();
            }
        }
    }

    public ml() {
        HandlerThread handlerThread = new HandlerThread("retry_thread_avm");
        this.h = handlerThread;
        handlerThread.start();
        this.g = new e(this.h.getLooper());
    }

    public static synchronized AvmManager c() {
        if (b == null) {
            b = new ml();
        }
        return b;
    }

    public final void A() {
        e eVar;
        Intent intent = new Intent("com.ts.avm.AvmAndroidService");
        intent.setComponent(new ComponentName("com.ts.avm", "com.ts.avm.AvmAndroidService"));
        if (this.c == null) {
            nl.c(a, "ERROR: Should Call setContext first.");
            return;
        }
        String str = a;
        nl.e(str, "BindService Connection:");
        this.e = this.c.bindService(intent, B(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("BindService Result:");
        sb.append(this.e);
        sb.append(" ");
        sb.append(this.g == null);
        nl.e(str, sb.toString());
        if (this.e || (eVar = this.g) == null) {
            return;
        }
        if (eVar.hasMessages(0)) {
            this.g.removeMessages(0);
        }
        this.g.sendEmptyMessageDelayed(0, 1000L);
    }

    public final ServiceConnection B() {
        synchronized (ml.class) {
            if (this.i == null) {
                this.i = new d();
            }
        }
        return this.i;
    }

    public final void C() {
        nl.f(a, "reBindService: " + this.d);
        if (this.d) {
            return;
        }
        A();
    }

    public final void D() {
        int i = this.r;
        if (i == -1) {
            nl.b(a, "reSetProvisionFinishedByCache: ProvisionFinished maybe never be set" + this.r);
            return;
        }
        G(i);
        nl.b(a, "reSetProvisionFinishedByCache:" + this.r);
    }

    public int E(AvmManager.c cVar) {
        synchronized (this.l) {
            if (cVar == null) {
                nl.d(a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.l.contains(cVar)) {
                nl.d(a, "registerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.l.add(cVar);
            return 0;
        }
    }

    public final void F(Context context) {
        this.c = context;
    }

    public int G(int i) {
        String str = a;
        nl.b(str, "setProvisionFinished mode:" + i);
        CmdBean cmdBeanA = new ol().b(33554690).c(9).d(7).e(new int[]{i, 0, 0, 0}).a();
        synchronized (this) {
            if (!this.f) {
                nl.d(str, "setProvisionFinished ERROR: should call init first.", new Throwable());
                return -1;
            }
            jl jlVar = this.j;
            if (jlVar == null) {
                nl.d(str, "setProvisionFinished ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                this.r = i;
                jlVar.b(cmdBeanA);
                nl.b(str, "setProvisionFinished: called success value = " + i);
                return 0;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return -2;
            }
        }
    }

    public final void H() {
        HandlerThread handlerThread = this.h;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.h = null;
            this.g = null;
        }
        if (this.d && this.e) {
            this.c.unbindService(B());
            this.d = false;
            this.e = false;
        }
    }

    public int I(AvmManager.c cVar) {
        synchronized (this.l) {
            if (cVar == null) {
                nl.d(a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.l.contains(cVar)) {
                this.l.remove(cVar);
                return 0;
            }
            nl.d(a, "unregisterStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int a(AvmManager.Calibration calibration) {
        synchronized (this) {
            if (!this.f) {
                nl.d(a, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            jl jlVar = this.j;
            if (jlVar == null) {
                nl.d(a, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                jlVar.b(pl.a(calibration));
                nl.b(a, "calibrate: called success");
                return 0;
            } catch (RemoteException e2) {
                e2.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void b(ll llVar) {
        synchronized (this.k) {
            if (llVar == null) {
                nl.d(a, "deinit: NULL Object input.", new Throwable());
            } else if (this.k.contains(llVar)) {
                this.k.remove(llVar);
            } else {
                nl.d(a, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            H();
            this.f = false;
            this.c = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void d(Context context, ll llVar) {
        synchronized (this.k) {
            if (!this.k.contains(llVar)) {
                this.k.add(llVar);
            }
        }
        synchronized (this) {
            F(context);
            A();
            this.f = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int e(AvmManager.c cVar) {
        return E(cVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int f(AvmManager.d dVar) {
        synchronized (this.m) {
            if (dVar == null) {
                nl.d(a, "registerAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.m.contains(dVar)) {
                nl.d(a, "registerAvmViewStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.m.add(dVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int g(AvmManager.c cVar) {
        return I(cVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int h(AvmManager.d dVar) {
        synchronized (this.m) {
            if (dVar == null) {
                nl.d(a, "unregisterAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.m.contains(dVar)) {
                this.m.remove(dVar);
                return 0;
            }
            nl.d(a, "unregisterAvmViewStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }
}
