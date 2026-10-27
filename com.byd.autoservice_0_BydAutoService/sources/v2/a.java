package v2;

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
import t2.a;
import t2.b;
import t2.c;
import t2.d;

/* JADX INFO: compiled from: AvmManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AvmManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f5424a = "a";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile AvmManager f5425b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f5426c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f5430g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public HandlerThread f5431h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ServiceConnection f5432i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public t2.c f5433j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5427d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5428e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5429f = false;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f5439p = 0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f5440q = 0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f5441r = -1;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final d.a f5442s = new BinderC0067a();

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final b.a f5443t = new b();

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a.AbstractBinderC0063a f5444u = new c();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList<u2.a> f5434k = new ArrayList<>();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ArrayList<AvmManager.c> f5435l = new ArrayList<>();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList<AvmManager.d> f5436m = new ArrayList<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList<AvmManager.b> f5437n = new ArrayList<>();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ArrayList<AvmManager.a> f5438o = new ArrayList<>();

    /* JADX INFO: renamed from: v2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class BinderC0067a extends d.a {
        public BinderC0067a() {
        }

        @Override // t2.d
        public void m(int i3, String str) throws RemoteException {
            a.this.f5439p = i3;
            synchronized (a.this.f5435l) {
                w2.a.b(a.f5424a, "mStatusListenerList " + a.this.f5435l.size());
                for (AvmManager.c cVar : a.this.f5435l) {
                    if (cVar != null) {
                        cVar.b(i3, str);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class b extends b.a {
        public b() {
        }

        @Override // t2.b
        public void o(CmdBean cmdBean) throws RemoteException {
            w2.a.b(a.f5424a, "onCommandReceived id:" + cmdBean.f());
            if (cmdBean.f() == 229376) {
                a.this.f5440q = cmdBean.g()[0];
                synchronized (a.this.f5436m) {
                    for (AvmManager.d dVar : a.this.f5436m) {
                        if (dVar != null) {
                            dVar.a(a.this.f5440q);
                        }
                    }
                }
                return;
            }
            if (cmdBean.f() != 67108992) {
                if (cmdBean.f() == 33554690) {
                    a.this.H();
                    return;
                }
                return;
            }
            synchronized (a.this.f5437n) {
                for (AvmManager.b bVar : a.this.f5437n) {
                    if (bVar != null) {
                        bVar.a(cmdBean.g()[0]);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: AvmManagerImpl.java */
    public class c extends a.AbstractBinderC0063a {
        public c() {
        }

        @Override // t2.a
        public void C(String str, int i3, int i4) throws RemoteException {
            String strSubstring = str != null ? str.length() > 4 ? str.substring(str.length() - 4, str.length()) : str : "";
            w2.a.b(a.f5424a, " onAvmCallStatusChanged: phonenum:" + strSubstring + " calltype:" + i3 + " action:" + i4);
            synchronized (a.this.f5438o) {
                for (AvmManager.a aVar : a.this.f5438o) {
                    if (aVar != null) {
                        aVar.a(str, i3, i4);
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
            w2.a.b(a.f5424a, "onServiceConnected: " + Thread.currentThread().getName());
            a.this.f5433j = c.a.F(iBinder);
            try {
                if (a.this.f5433j.u(a.this.f5442s) < 0) {
                    w2.a.d(a.f5424a, "registerAvmStatusListener ERROR: ", new Throwable());
                }
                if (a.this.f5433j.n(a.this.f5443t) < 0) {
                    w2.a.d(a.f5424a, "registerCommandListener ERROR: ", new Throwable());
                }
                if (a.this.f5433j.E(a.this.f5444u) < 0) {
                    w2.a.d(a.f5424a, "registerCallStatusListener ERROR: ", new Throwable());
                }
            } catch (RemoteException e3) {
                e3.printStackTrace();
            }
            synchronized (a.this.f5434k) {
                if (a.this.f5433j == null) {
                    for (u2.a aVar : a.this.f5434k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    w2.a.e(a.f5424a, "onServiceConnected: IBinder is null!");
                    return;
                }
                w2.a.e(a.f5424a, "onServiceConnected: Binder is success.");
                for (u2.a aVar2 : a.this.f5434k) {
                    if (aVar2 != null) {
                        aVar2.a();
                    }
                }
                a.this.f5427d = true;
                a.this.H();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            w2.a.e(a.f5424a, "onServiceConnected: Unbinder is success.");
            a.this.f5427d = false;
            a.this.f5428e = false;
            synchronized (a.this.f5434k) {
                if (a.this.f5433j == null) {
                    for (u2.a aVar : a.this.f5434k) {
                        if (aVar != null) {
                            aVar.onError(0, "IBinder is null");
                        }
                    }
                    w2.a.e(a.f5424a, "onServiceConnected: IBinder is null!");
                    return;
                }
                w2.a.e(a.f5424a, "onServiceConnected: Binder is success.");
                for (u2.a aVar2 : a.this.f5434k) {
                    if (aVar2 != null) {
                        aVar2.b();
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
                w2.a.b(a.f5424a, "handleMessage MSG_RETRY_BIND_SERVICE");
                a.this.G();
            }
        }
    }

    public a() {
        HandlerThread handlerThread = new HandlerThread("retry_thread_avm");
        this.f5431h = handlerThread;
        handlerThread.start();
        this.f5430g = new e(this.f5431h.getLooper());
    }

    public static synchronized AvmManager d() {
        if (f5425b == null) {
            f5425b = new a();
        }
        return f5425b;
    }

    public final void E() {
        e eVar;
        Intent intent = new Intent("com.ts.avm.AvmAndroidService");
        intent.setComponent(new ComponentName("com.ts.avm", "com.ts.avm.AvmAndroidService"));
        if (this.f5426c == null) {
            w2.a.c(f5424a, "ERROR: Should Call setContext first.");
            return;
        }
        String str = f5424a;
        w2.a.e(str, "BindService Connection:");
        this.f5428e = this.f5426c.bindService(intent, F(), 1);
        StringBuilder sb = new StringBuilder();
        sb.append("BindService Result:");
        sb.append(this.f5428e);
        sb.append(" ");
        sb.append(this.f5430g == null);
        w2.a.e(str, sb.toString());
        if (this.f5428e || (eVar = this.f5430g) == null) {
            return;
        }
        if (eVar.hasMessages(0)) {
            this.f5430g.removeMessages(0);
        }
        this.f5430g.sendEmptyMessageDelayed(0, 1000L);
    }

    public final ServiceConnection F() {
        synchronized (a.class) {
            if (this.f5432i == null) {
                this.f5432i = new d();
            }
        }
        return this.f5432i;
    }

    public final void G() {
        w2.a.f(f5424a, "reBindService: " + this.f5427d);
        if (this.f5427d) {
            return;
        }
        E();
    }

    public final void H() {
        int i3 = this.f5441r;
        if (i3 == -1) {
            w2.a.b(f5424a, "reSetProvisionFinishedByCache: ProvisionFinished maybe never be set" + this.f5441r);
            return;
        }
        h(i3);
        w2.a.b(f5424a, "reSetProvisionFinishedByCache:" + this.f5441r);
    }

    public int I(AvmManager.c cVar) {
        synchronized (this.f5435l) {
            if (cVar == null) {
                w2.a.d(f5424a, "registerStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f5435l.contains(cVar)) {
                w2.a.d(f5424a, "registerStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f5435l.add(cVar);
            return 0;
        }
    }

    public final void J(Context context) {
        this.f5426c = context;
    }

    public final void K() {
        HandlerThread handlerThread = this.f5431h;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.f5431h = null;
            this.f5430g = null;
        }
        if (this.f5427d && this.f5428e) {
            this.f5426c.unbindService(F());
            this.f5427d = false;
            this.f5428e = false;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void a(u2.a aVar) {
        synchronized (this.f5434k) {
            if (aVar == null) {
                w2.a.d(f5424a, "deinit: NULL Object input.", new Throwable());
            } else if (this.f5434k.contains(aVar)) {
                this.f5434k.remove(aVar);
            } else {
                w2.a.d(f5424a, "deinit: this listener has not been registered.", new Throwable());
            }
        }
        synchronized (this) {
            K();
            this.f5429f = false;
            this.f5426c = null;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int b() {
        return this.f5440q;
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int c() {
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(f5424a, "calibrate ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(f5424a, "calibrate ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                return cVar.z();
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public void e(Context context, u2.a aVar) {
        synchronized (this.f5434k) {
            if (!this.f5434k.contains(aVar)) {
                this.f5434k.add(aVar);
            }
        }
        synchronized (this) {
            J(context);
            E();
            this.f5429f = true;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int f(AvmManager.c cVar) {
        return I(cVar);
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int g(AvmManager.d dVar) {
        synchronized (this.f5436m) {
            if (dVar == null) {
                w2.a.d(f5424a, "registerAvmViewStatusListener: NULL Object input.", new Throwable());
                return -4;
            }
            if (this.f5436m.contains(dVar)) {
                w2.a.d(f5424a, "registerAvmViewStatusListener: this listener has already been registered.", new Throwable());
                return -5;
            }
            this.f5436m.add(dVar);
            return 0;
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int h(int i3) {
        String str = f5424a;
        w2.a.b(str, "setProvisionFinished mode:" + i3);
        CmdBean cmdBeanA = new x2.a().b(33554690).c(9).d(7).e(new int[]{i3, 0, 0, 0}).a();
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(str, "setProvisionFinished ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(str, "setProvisionFinished ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                this.f5441r = i3;
                cVar.l(cmdBeanA);
                w2.a.b(str, "setProvisionFinished: called success value = " + i3);
                return 0;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int i() {
        String str = f5424a;
        w2.a.b(str, "startPip");
        CmdBean cmdBeanA = new x2.a().b(33554689).c(9).d(7).e(new int[]{1, 0, 0, 0}).a();
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(str, "startPip ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(str, "startPip ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.l(cmdBeanA);
                w2.a.b(str, "startPip: called success");
                return 0;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int j() {
        String str = f5424a;
        w2.a.b(str, "stopPip");
        CmdBean cmdBeanA = new x2.a().b(33554689).c(9).d(7).e(new int[]{0, 0, 0, 0}).a();
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(str, "stopPip ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(str, "stopPip ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.l(cmdBeanA);
                w2.a.b(str, "stopPip: called success");
                return 0;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int k(AvmManager.Language language) {
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(f5424a, "switchLanguage ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(f5424a, "switchLanguage ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.l(x2.b.a(language));
                w2.a.b(f5424a, "switchLanguage: called success");
                return 0;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }

    @Override // com.ts.avm.sdk.AvmManager
    public int l(AvmManager.Theme theme) {
        synchronized (this) {
            if (!this.f5429f) {
                w2.a.d(f5424a, "switchTheme ERROR: should call init first.", new Throwable());
                return -1;
            }
            t2.c cVar = this.f5433j;
            if (cVar == null) {
                w2.a.d(f5424a, "switchTheme ERROR: this function should be called after getting service connection callback.", new Throwable());
                return -3;
            }
            try {
                cVar.l(x2.b.a(theme));
                w2.a.b(f5424a, "switchTheme: called success");
                return 0;
            } catch (RemoteException e3) {
                e3.printStackTrace();
                return -2;
            }
        }
    }
}
