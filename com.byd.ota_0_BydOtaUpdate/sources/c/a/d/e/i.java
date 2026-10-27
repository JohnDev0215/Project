package c.a.d.e;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import c.a.b.d.l;
import c.a.b.d.n;
import c.a.h.a.b;
import java.util.Objects;

/* JADX INFO: compiled from: OtaSdkCommandStub.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2387a = i.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2389c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.d.a f2390d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.a.b f2391e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.g.c f2392f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.a.a f2393g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RemoteCallbackList<c.a.h.a.a> f2388b = new RemoteCallbackList<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.b.c.a f2394h = c.a.b.c.a.k();

    public i(Context context, c.a.g.c cVar, c.a.a.a aVar, c.a.d.a aVar2, c.a.a.b bVar) {
        this.f2389c = context;
        this.f2390d = aVar2;
        this.f2391e = bVar;
        this.f2392f = cVar;
        this.f2393g = aVar;
    }

    public static /* synthetic */ void B() {
        int callingPid = Binder.getCallingPid();
        c.a.b.d.e.c(f2387a, "linkToDeath->pid = " + callingPid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void D() {
        this.f2390d.w(this.f2394h.b());
    }

    @Override // c.a.h.a.b
    public void A() throws RemoteException {
        c.a.b.d.e.c(f2387a, "setUserClickCheckState false");
        c.a.b.c.a.k().g0(false);
    }

    @Override // c.a.h.a.b
    public void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException {
    }

    @Override // c.a.h.a.b
    public void f() throws RemoteException {
        c.a.b.d.e.c(f2387a, "setCancelDelay->pid = " + Binder.getCallingPid() + ", updateTime = " + n.d("yyyy-MM-dd HH:mm:ss", this.f2394h.b()));
        l.d().b(new Runnable() { // from class: c.a.d.e.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f2380c.D();
            }
        });
    }

    @Override // c.a.h.a.b
    public boolean i() throws RemoteException {
        c.a.b.d.e.c(f2387a, "getUpdateFlag->pid = " + Binder.getCallingPid() + ", updateFlag = " + this.f2394h.w());
        return this.f2394h.w();
    }

    @Override // c.a.h.a.b
    public void j(c.a.h.a.a aVar) throws RemoteException {
        boolean zPingBinder = aVar.asBinder().pingBinder();
        boolean zIsBinderAlive = aVar.asBinder().isBinderAlive();
        c.a.b.d.e.c(f2387a, "registerCallback->pid = " + Binder.getCallingPid() + ", pingBinder = " + zPingBinder + ", isBinderAlive = " + zIsBinderAlive);
        this.f2388b.register(aVar);
        aVar.asBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: c.a.d.e.c
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                i.B();
            }
        }, 0);
    }

    public void k(long j) {
        int iBeginBroadcast = this.f2388b.beginBroadcast();
        for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
            c.a.h.a.a aVar = (c.a.h.a.a) this.f2388b.getBroadcastItem(i2);
            if (aVar != null) {
                try {
                    aVar.k(j);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.f2388b.finishBroadcast();
    }

    @Override // c.a.h.a.b
    public long l() throws RemoteException {
        c.a.b.d.e.c(f2387a, "getUpdateTime->pid = " + Binder.getCallingPid() + ", updateTime = " + n.d("yyyy-MM-dd HH:mm:ss", this.f2394h.b()));
        return this.f2394h.b();
    }

    @Override // c.a.h.a.b
    public String m() throws RemoteException {
        c.a.b.d.e.c(f2387a, "getCurVersion->pid = " + Binder.getCallingPid() + ", curVersion = " + this.f2394h.e());
        return this.f2394h.e();
    }

    @Override // c.a.h.a.b
    public void p() throws Throwable {
        c.a.b.d.e.c(f2387a, "setOtaUpdate->pid = " + Binder.getCallingPid() + ", updateFlag = " + this.f2394h.w());
        if (c.a.g.l.l.g()) {
            if (this.f2392f.n()) {
                return;
            }
            c.a.g.l.l.j(0);
            this.f2393g.h(c.a.g.l.l.f());
            this.f2393g.e(6);
            return;
        }
        if (c.a.g.l.l.e()) {
            if (this.f2392f.n()) {
                return;
            }
            c.a.g.l.l.j(0);
            this.f2393g.h(c.a.g.l.l.f());
            this.f2393g.e(21);
            return;
        }
        if (this.f2394h.w()) {
            l lVarD = l.d();
            c.a.d.a aVar = this.f2390d;
            Objects.requireNonNull(aVar);
            lVarD.b(new a(aVar));
            return;
        }
        l lVarD2 = l.d();
        c.a.d.a aVar2 = this.f2390d;
        Objects.requireNonNull(aVar2);
        lVarD2.b(new h(aVar2));
        if (this.f2392f.n()) {
            return;
        }
        this.f2392f.o(true);
        this.f2392f.i(true);
        this.f2392f.d();
    }

    @Override // c.a.h.a.b
    public String r() throws RemoteException {
        c.a.b.d.e.c(f2387a, "getLatestVersion->pid = " + Binder.getCallingPid() + ", latestVersion = " + this.f2394h.l());
        return this.f2394h.l();
    }

    public void v(boolean z) {
        int iBeginBroadcast = this.f2388b.beginBroadcast();
        for (int i2 = 0; i2 < iBeginBroadcast; i2++) {
            c.a.h.a.a aVar = (c.a.h.a.a) this.f2388b.getBroadcastItem(i2);
            if (aVar != null) {
                try {
                    aVar.v(z);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.f2388b.finishBroadcast();
    }

    @Override // c.a.h.a.b
    public void y(c.a.h.a.a aVar) throws RemoteException {
        boolean zPingBinder = aVar.asBinder().pingBinder();
        boolean zIsBinderAlive = aVar.asBinder().isBinderAlive();
        c.a.b.d.e.c(f2387a, "unregisterCallback->pid = " + Binder.getCallingPid() + ", pingBinder = " + zPingBinder + ", isBinderAlive = " + zIsBinderAlive);
        this.f2388b.unregister(aVar);
    }
}
