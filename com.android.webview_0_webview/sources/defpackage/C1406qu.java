package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: qu, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1406qu {
    public static final Object A = new Object();
    public static final int[] B = new int[4];
    public final Handler a = new Handler();
    public final Executor b = new Executor(this) { // from class: Yt
        public final C1406qu a;

        {
            this.a = this;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.a.a.post(runnable);
        }
    };
    public final Bundle c;
    public final boolean d;
    public InterfaceC1351pu e;
    public C1296ou f;
    public InterfaceC1241nu g;
    public Gu h;
    public boolean i;
    public boolean j;
    public boolean k;
    public int l;
    public final InterfaceC1075ku m;
    public final InterfaceC1075ku n;
    public final InterfaceC1075ku o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public int u;
    public int v;
    public boolean w;
    public int[] x;
    public InterfaceC1789xt y;
    public boolean z;

    public C1406qu(Context context, ComponentName componentName, boolean z, boolean z2, Bundle bundle, String str) {
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        this.c = bundle2;
        bundle2.putBoolean("org.chromium.base.process_launcher.extra.bind_to_caller", z);
        this.d = z;
        C0852gu c0852gu = new C0852gu(this);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        int i = (z2 ? Integer.MIN_VALUE : 0) | 1;
        this.n = new ServiceConnectionC1186mu(context, intent, i, this.a, this.b, c0852gu, str, null);
        this.m = new ServiceConnectionC1186mu(context, intent, i | 64, this.a, this.b, c0852gu, str, null);
        this.o = new ServiceConnectionC1186mu(context, intent, i | 32, this.a, this.b, c0852gu, str, null);
    }

    public static String c() {
        ClassLoader classLoader = C1406qu.class.getClassLoader();
        return classLoader.toString() + classLoader.hashCode();
    }

    public static boolean j() {
        return Mt.c();
    }

    public final boolean a(boolean z) {
        boolean zA;
        if (z) {
            zA = ((ServiceConnectionC1186mu) this.m).a();
        } else {
            this.q++;
            zA = ((ServiceConnectionC1186mu) this.n).a();
        }
        if (!zA) {
            return false;
        }
        ((ServiceConnectionC1186mu) this.o).a();
        l();
        return true;
    }

    public final void b() {
        try {
            TraceEvent.x("ChildProcessConnection.doConnectionSetup", null);
            try {
                this.h.h(this.f.a, new BinderC1019ju(this), this.f.b);
            } catch (RemoteException e) {
                Ts.a("ChildProcessConn", "Failed to setup connection.", e);
            }
            this.f = null;
        } finally {
            TraceEvent.A("ChildProcessConnection.doConnectionSetup");
        }
    }

    public boolean d() {
        boolean z;
        synchronized (A) {
            z = this.z;
        }
        return z;
    }

    public boolean e() {
        return this.h != null;
    }

    public final void f() {
        InterfaceC1351pu interfaceC1351pu = this.e;
        if (interfaceC1351pu != null) {
            this.e = null;
            interfaceC1351pu.a(this);
        }
    }

    public void g(IBinder iBinder) {
        if (this.i) {
            return;
        }
        try {
            TraceEvent.x("ChildProcessConnection.ChildServiceConnection.onServiceConnected", null);
            this.i = true;
            Gu guC = Fu.c(iBinder);
            this.h = guC;
            if (this.d) {
                if (!guC.o(c())) {
                    if (this.e != null) {
                        this.e.c(this);
                    }
                    k();
                    return;
                }
            }
            if (this.e != null) {
                this.e.b();
            }
            this.j = true;
            if (this.y == null) {
                final InterfaceC1789xt interfaceC1789xt = new InterfaceC1789xt(this) { // from class: Zt
                    public final C1406qu a;

                    {
                        this.a = this;
                    }

                    @Override // defpackage.InterfaceC1789xt
                    public void a(final int i) {
                        final C1406qu c1406qu = this.a;
                        c1406qu.a.post(new Runnable(c1406qu, i) { // from class: cu
                            public final C1406qu s;
                            public final int t;

                            {
                                this.s = c1406qu;
                                this.t = i;
                            }

                            @Override // java.lang.Runnable
                            public void run() {
                                C1406qu c1406qu2 = this.s;
                                int i2 = this.t;
                                Gu gu = c1406qu2.h;
                                if (gu == null) {
                                    return;
                                }
                                try {
                                    gu.l(i2);
                                } catch (RemoteException unused) {
                                }
                            }
                        });
                    }
                };
                ThreadUtils.e(new Runnable(interfaceC1789xt) { // from class: au
                    public final InterfaceC1789xt s;

                    {
                        this.s = interfaceC1789xt;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        MemoryPressureListener.a.f(this.s);
                    }
                });
                this.y = interfaceC1789xt;
            }
            if (this.f != null) {
                b();
            }
        } catch (RemoteException e) {
            Ts.a("ChildProcessConn", "Failed to bind service to connection.", e);
        } finally {
            TraceEvent.A("ChildProcessConnection.ChildServiceConnection.onServiceConnected");
        }
    }

    public void h() {
        if (this.k) {
            return;
        }
        this.k = true;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.l);
        StringBuilder sbF = Z7.f("bindings:");
        sbF.append(((ServiceConnectionC1186mu) this.o).h ? "W" : " ");
        sbF.append(((ServiceConnectionC1186mu) this.n).h ? "M" : " ");
        sbF.append(((ServiceConnectionC1186mu) this.m).h ? "S" : " ");
        synchronized (A) {
            sbF.append(" state:");
            sbF.append(this.u);
            sbF.append(" counts:");
            for (int i = 0; i < 4; i++) {
                sbF.append(B[i]);
                sbF.append(",");
            }
        }
        objArr[1] = sbF.toString();
        Ts.f("ChildProcessConn", "onServiceDisconnected (crash or killed by oom): pid=%d %s", objArr);
        k();
        f();
        InterfaceC1241nu interfaceC1241nu = this.g;
        if (interfaceC1241nu != null) {
            ((C1625uu) interfaceC1241nu).a(null);
            this.g = null;
        }
    }

    public void i(boolean z, InterfaceC1351pu interfaceC1351pu) {
        try {
            TraceEvent.x("ChildProcessConnection.start", null);
            this.e = interfaceC1351pu;
            if (!a(z)) {
                Ts.a("ChildProcessConn", "Failed to establish the service connection.", new Object[0]);
                f();
            }
        } finally {
            TraceEvent.A("ChildProcessConnection.start");
        }
    }

    public void k() {
        this.h = null;
        this.f = null;
        this.t = true;
        ((ServiceConnectionC1186mu) this.m).b();
        ((ServiceConnectionC1186mu) this.o).b();
        ((ServiceConnectionC1186mu) this.n).b();
        l();
        synchronized (A) {
            this.x = Arrays.copyOf(B, 4);
        }
        final InterfaceC1789xt interfaceC1789xt = this.y;
        if (interfaceC1789xt != null) {
            ThreadUtils.e(new Runnable(interfaceC1789xt) { // from class: bu
                public final InterfaceC1789xt s;

                {
                    this.s = interfaceC1789xt;
                }

                @Override // java.lang.Runnable
                public void run() {
                    MemoryPressureListener.a.g(this.s);
                }
            });
            this.y = null;
        }
    }

    public final void l() {
        int[] iArr = B;
        int i = this.t ? 0 : ((ServiceConnectionC1186mu) this.m).h ? 3 : ((ServiceConnectionC1186mu) this.n).h ? 2 : 1;
        synchronized (A) {
            if (i != this.u) {
                if (this.u != 0) {
                    int i2 = this.u;
                    iArr[i2] = iArr[i2] - 1;
                }
                if (i != 0) {
                    iArr[i] = iArr[i] + 1;
                }
            }
            this.u = i;
            if (!this.t) {
                this.v = i;
            }
        }
    }

    public void m(int i, int i2) {
        if (e()) {
            if (this.r == i && this.s == i2) {
                return;
            }
            this.r = i;
            this.s = i2;
            ServiceConnectionC1186mu serviceConnectionC1186mu = (ServiceConnectionC1186mu) this.o;
            Objects.requireNonNull(serviceConnectionC1186mu);
            if (Mt.c()) {
                C1514st.c(serviceConnectionC1186mu.a, serviceConnectionC1186mu, i, i2);
                Mt.b(serviceConnectionC1186mu.a, serviceConnectionC1186mu.b, serviceConnectionC1186mu, serviceConnectionC1186mu.c, serviceConnectionC1186mu.d, serviceConnectionC1186mu.e, serviceConnectionC1186mu.g);
            }
        }
    }
}
