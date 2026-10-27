package y6;

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
import java.util.ArrayList;
import w6.a;
import w6.b;
import w6.c;
import w6.d;

/* JADX INFO: compiled from: AvmManagerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends AvmManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f9391a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile AvmManager f9392b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f9393c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f9397g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public HandlerThread f9398h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ServiceConnection f9399i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public w6.c f9400j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9394d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9395e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9396f = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9406p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9407q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9408r = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final d.a f9409s = new BinderC0150a();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b.a f9410t = new b();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a.AbstractBinderC0138a f9411u = new c();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList<x6.a> f9401k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList<AvmManager.c> f9402l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList<AvmManager.d> f9403m = new ArrayList<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList<AvmManager.b> f9404n = new ArrayList<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList<AvmManager.a> f9405o = new ArrayList<>();

    /* JADX INFO: renamed from: y6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class BinderC0150a extends d.a {
        public BinderC0150a() {
        }

        @Override // w6.d
        public void o(int i8, String str) throws RemoteException {
            a.this.f9406p = i8;
            synchronized (a.this.f9402l) {
                z6.a.b(a.f9391a, "mStatusListenerList " + a.this.f9402l.size());
                for (AvmManager.c cVar : a.this.f9402l) {
                    if (cVar != null) {
                        cVar.a(i8, str);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class b extends b.a {
        public b() {
        }

        @Override // w6.b
        public void R(CmdBean cmdBean) throws RemoteException {
            z6.a.b(a.f9391a, "onCommandReceived id:" + cmdBean.f());
            if (cmdBean.f() == 229376) {
                a.this.f9407q = cmdBean.g()[0];
                synchronized (a.this.f9403m) {
                    for (AvmManager.d dVar : a.this.f9403m) {
                        if (dVar != null) {
                            dVar.a(a.this.f9407q);
                        }
                    }
                }
                return;
            }
            if (cmdBean.f() != 67108992) {
                if (cmdBean.f() == 33554690) {
                    a.this.K();
                    return;
                }
                return;
            }
            synchronized (a.this.f9404n) {
                for (AvmManager.b bVar : a.this.f9404n) {
                    if (bVar != null) {
                        bVar.onAvmDockerStatusChanged(cmdBean.g()[0]);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class c extends a.AbstractBinderC0138a {
        public c() {
        }

        @Override // w6.a
        public void v0(String str, int i8, int i9) throws RemoteException {
            String strSubstring = str != null ? str.length() > 4 ? str.substring(str.length() - 4, str.length()) : str : "";
            z6.a.b(a.f9391a, " onAvmCallStatusChanged: phonenum:" + strSubstring + " calltype:" + i8 + " action:" + i9);
            synchronized (a.this.f9405o) {
                for (AvmManager.a aVar : a.this.f9405o) {
                    if (aVar != null) {
                        aVar.a(str, i8, i9);
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
            z6.a.b(a.f9391a, "onServiceConnected: " + Thread.currentThread().getName());
            a.this.f9400j = c.a.A0(iBinder);
            try {
                if (a.this.f9400j.u(a.this.f9409s) < 0) {
                    z6.a.d(a.f9391a, "registerAvmStatusListener ERROR: ", new Throwable());
                }
                if (a.this.f9400j.P(a.this.f9410t) < 0) {
                    z6.a.d(a.f9391a, "registerCommandListener ERROR: ", new Throwable());
                }
                if (a.this.f9400j.H(a.this.f9411u) < 0) {
                    z6.a.d(a.f9391a, "registerCallStatusListener ERROR: ", new Throwable());
                }
            } catch (RemoteException e8) {
                e8.printStackTrace();
            }
            synchronized (a.this.f9401k) {
                if (a.this.f9400j == null) {
                    for (x6.a aVar : a.this.f9401k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    z6.a.e(a.f9391a, "onServiceConnected: IBinder is null!");
                    return;
                }
                z6.a.e(a.f9391a, "onServiceConnected: Binder is success.");
                for (x6.a aVar2 : a.this.f9401k) {
                    if (aVar2 != null) {
                        aVar2.onServiceConnected();
                    }
                }
                a.this.f9394d = true;
                a.this.K();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            z6.a.e(a.f9391a, "onServiceConnected: Unbinder is success.");
            a.this.f9394d = false;
            a.this.f9395e = false;
            synchronized (a.this.f9401k) {
                if (a.this.f9400j == null) {
                    for (x6.a aVar : a.this.f9401k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    z6.a.e(a.f9391a, "onServiceConnected: IBinder is null!");
                    return;
                }
                z6.a.e(a.f9391a, "onServiceConnected: Binder is success.");
                for (x6.a aVar2 : a.this.f9401k) {
                    if (aVar2 != null) {
                        aVar2.onServiceDisConnected();
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
                z6.a.b(a.f9391a, "handleMessage MSG_RETRY_BIND_SERVICE");
                a.this.J();
            }
        }
    }

    public a() {
        HandlerThread handlerThread = new HandlerThread("retry_thread_avm");
        this.f9398h = handlerThread;
        handlerThread.start();
        this.f9397g = new e(this.f9398h.getLooper());
    }

    public static synchronized AvmManager c() {
        if (f9392b == null) {
            f9392b = new a();
        }
        return f9392b;
    }

    public final void H() {
        e eVar;
        Intent intent = new Intent("com.ts.avm.AvmAndroidService");
        intent.setComponent(new ComponentName("com.ts.avm", "com.ts.avm.AvmAndroidService"));
        if (this.f9393c == null) {
            z6.a.c(f9391a, "ERROR: Should Call setContext first.");
            return;
        }
        String str = f9391a;
        z6.a.e(str, "BindService Connection:");
        this.f9395e = this.f9393c.bindService(intent, I(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("BindService Result:");
        sb.append(this.f9395e);
        sb.append(" ");
        sb.append(this.f9397g == null);
        z6.a.e(str, sb.toString());
        if (this.f9395e || (eVar = this.f9397g) == null) {
            return;
        }
        if (eVar.hasMessages(0)) {
            this.f9397g.removeMessages(0);
        }
        this.f9397g.sendEmptyMessageDelayed(0, 1000L);
    }

    public final ServiceConnection I() {
        synchronized (a.class) {
            if (this.f9399i == null) {
                this.f9399i = new d();
            }
        }
        return this.f9399i;
    }

    public final void J() {
        z6.a.f(f9391a, "reBindService: " + this.f9394d);
        if (this.f9394d) {
            return;
        }
        H();
    }

    public final void K() {
        int i8 = this.f9408r;
        if (i8 == -1) {
            z6.a.b(f9391a, "reSetProvisionFinishedByCache: ProvisionFinished maybe never be set" + this.f9408r);
            return;
        }
        N(i8);
        z6.a.b(f9391a, "reSetProvisionFinishedByCache:" + this.f9408r);
    }

    public int L(AvmManager.c cVar) {
        synchronized (this.f9402l) {
            if (cVar == null) {
                z6.a.d(f9391a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9402l.contains(cVar)) {
                z6.a.d(f9391a, "registerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f9402l.add(cVar);
            return 0;
        }
    }

    public final void M(Context context) {
        this.f9393c = context;
    }

    public int N(int i8) {
        String str = f9391a;
        z6.a.b(str, "setProvisionFinished mode:" + i8);
        CmdBean cmdBeanA = new a7.a().b(33554690).c(9).d(7).e(new int[]{i8, 0, 0, 0}).a();
        synchronized (this) {
            if (!this.f9396f) {
                z6.a.d(str, "setProvisionFinished ERROR: should call init first.", new Throwable());
                return -1;
            }
            w6.c cVar = this.f9400j;
            if (cVar == null) {
                z6.a.d(str, "setProvisionFinished ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                this.f9408r = i8;
                cVar.L(cmdBeanA);
                z6.a.b(str, "setProvisionFinished: called success value = " + i8);
                return 0;
            } catch (RemoteException e8) {
                e8.printStackTrace();
                return -2;
            }
        }
    }

    public final void O() {
        HandlerThread handlerThread = this.f9398h;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f9398h = null;
            this.f9397g = null;
        }
        if (this.f9394d && this.f9395e) {
            this.f9393c.unbindService(I());
            this.f9394d = false;
            this.f9395e = false;
        }
    }

    public int P(AvmManager.c cVar) {
        synchronized (this.f9402l) {
            if (cVar == null) {
                z6.a.d(f9391a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9402l.contains(cVar)) {
                this.f9402l.remove(cVar);
                return 0;
            }
            z6.a.d(f9391a, "unregisterStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void a(x6.a aVar) {
        synchronized (this.f9401k) {
            if (aVar == null) {
                z6.a.d(f9391a, "deinit: NULL Object input.", new Throwable());
            } else if (this.f9401k.contains(aVar)) {
                this.f9401k.remove(aVar);
            } else {
                z6.a.d(f9391a, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            O();
            this.f9396f = false;
            this.f9393c = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int b() {
        return this.f9407q;
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int d() {
        synchronized (this) {
            if (!this.f9396f) {
                z6.a.d(f9391a, "hide ERROR: should call init first.", new Throwable());
                return -1;
            }
            w6.c cVar = this.f9400j;
            if (cVar == null) {
                z6.a.d(f9391a, "hide ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.L(a7.b.a(AvmManager.State.STOP));
                z6.a.b(f9391a, "hide: called success");
                return 0;
            } catch (RemoteException e8) {
                e8.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void e(Context context, x6.a aVar) {
        synchronized (this.f9401k) {
            if (!this.f9401k.contains(aVar)) {
                this.f9401k.add(aVar);
            }
        }
        synchronized (this) {
            M(context);
            H();
            this.f9396f = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int f(AvmManager.b bVar) {
        synchronized (this.f9404n) {
            if (bVar == null) {
                z6.a.d(f9391a, "registerAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9404n.contains(bVar)) {
                z6.a.d(f9391a, "registerAvmDockerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f9404n.add(bVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int g(AvmManager.c cVar) {
        return L(cVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int h(AvmManager.d dVar) {
        synchronized (this.f9403m) {
            if (dVar == null) {
                z6.a.d(f9391a, "registerAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9403m.contains(dVar)) {
                z6.a.d(f9391a, "registerAvmViewStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f9403m.add(dVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int i(AvmManager.a aVar) {
        synchronized (this.f9405o) {
            if (aVar == null) {
                z6.a.d(f9391a, "registerCallStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9405o.contains(aVar)) {
                z6.a.d(f9391a, "registerCallStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f9405o.add(aVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int j(String str, String str2, String str3, int i8, int i9, int i10, boolean z7) {
        synchronized (this) {
            if (!this.f9396f) {
                z6.a.d(f9391a, "sendCallInfo ERROR: should call init first.", new Throwable());
                return -1;
            }
            w6.c cVar = this.f9400j;
            if (cVar == null) {
                z6.a.d(f9391a, "sendCallInfo ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.z(str, str2, str3, i8, i9, i10, z7);
                if (str == null) {
                    str = "";
                } else if (str.length() > 4) {
                    str = str.substring(str.length() - 4, str.length());
                }
                z6.a.b(f9391a, "sendCallInfo: called success phoneNum:" + str + " callTime:" + i8 + " callType:" + i9 + " callState:" + i10 + " isMute:" + z7);
                return 0;
            } catch (RemoteException e8) {
                e8.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int k() {
        synchronized (this) {
            if (!this.f9396f) {
                z6.a.d(f9391a, "show ERROR: should call init first.", new Throwable());
                return -1;
            }
            w6.c cVar = this.f9400j;
            if (cVar == null) {
                z6.a.d(f9391a, "show ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.L(a7.b.a(AvmManager.State.START));
                z6.a.b(f9391a, "show: called success");
                return 0;
            } catch (RemoteException e8) {
                e8.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int l(AvmManager.b bVar) {
        synchronized (this.f9404n) {
            if (bVar == null) {
                z6.a.d(f9391a, "unregisterAvmDockerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9404n.contains(bVar)) {
                this.f9404n.remove(bVar);
                return 0;
            }
            z6.a.d(f9391a, "unregisterAvmDockerStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int m(AvmManager.c cVar) {
        return P(cVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int n(AvmManager.d dVar) {
        synchronized (this.f9403m) {
            if (dVar == null) {
                z6.a.d(f9391a, "unregisterAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9403m.contains(dVar)) {
                this.f9403m.remove(dVar);
                return 0;
            }
            z6.a.d(f9391a, "unregisterAvmViewStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int o(AvmManager.a aVar) {
        synchronized (this.f9405o) {
            if (aVar == null) {
                z6.a.d(f9391a, "unregisterCallStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f9405o.contains(aVar)) {
                this.f9405o.remove(aVar);
                return 0;
            }
            z6.a.d(f9391a, "unregisterCallStatusListener: this listener has not been registered.", new Throwable());
            return -5;
        }
    }
}
