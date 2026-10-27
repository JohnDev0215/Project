package c.a.b.d;

import android.content.Context;
import android.os.RemoteException;
import i.a.a.a.d;

/* JADX INFO: compiled from: TboxUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2345a = "m";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static m f2346b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f2347c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i.a.a.a.c f2348d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public i.a.a.a.d f2349e;

    /* JADX INFO: compiled from: TboxUtil.java */
    public class b extends d.a {
        public b() {
        }

        @Override // i.a.a.a.d
        public void d(byte b2) throws RemoteException {
            if (b2 == 0) {
                e.c(m.f2345a, "onRealApn2Changed->OPEN");
                return;
            }
            if (b2 == 1) {
                e.c(m.f2345a, "onRealApn2Changed->CLOSE");
                return;
            }
            e.c(m.f2345a, "onRealApn2Changed->b = " + ((int) b2));
        }

        @Override // i.a.a.a.d
        public void f(byte b2) throws RemoteException {
            if (b2 == 0) {
                e.c(m.f2345a, "onApn1Changed->OPEN");
                return;
            }
            if (b2 == 1) {
                e.c(m.f2345a, "onApn1Changed->CLOSE");
                return;
            }
            e.c(m.f2345a, "onApn1Changed->b = " + ((int) b2));
        }

        @Override // i.a.a.a.d
        public void g(byte b2) throws RemoteException {
            if (b2 == 0) {
                e.c(m.f2345a, "onApn3Changed->OPEN");
                return;
            }
            if (b2 == 1) {
                e.c(m.f2345a, "onApn3Changed->CLOSE");
                return;
            }
            e.c(m.f2345a, "onApn3Changed->b = " + ((int) b2));
        }

        @Override // i.a.a.a.d
        public void i(i.a.a.a.b bVar) throws RemoteException {
        }

        @Override // i.a.a.a.d
        public void k(byte b2) throws RemoteException {
            if (b2 == 0) {
                e.c(m.f2345a, "onApn2Changed->OPEN");
                return;
            }
            if (b2 == 1) {
                e.c(m.f2345a, "onApn2Changed->CLOSE");
                return;
            }
            e.c(m.f2345a, "onApn2Changed->b = " + ((int) b2));
        }

        @Override // i.a.a.a.d
        public void l(byte b2) throws RemoteException {
            if (b2 == 0) {
                e.c(m.f2345a, "onRilConnectChanged->tbox DISCONNECT");
                return;
            }
            if (b2 == 1) {
                e.c(m.f2345a, "onRilConnectChanged->tbox CONNECTED");
                return;
            }
            e.c(m.f2345a, "onRilConnectChanged->b = " + ((int) b2));
        }
    }

    public m() {
        throw new UnsupportedOperationException("TboxUtil: U can't new me");
    }

    public static m c(Context context) {
        if (f2346b == null) {
            synchronized (m.class) {
                if (f2346b == null) {
                    f2346b = new m(context);
                }
            }
        }
        return f2346b;
    }

    public String b() {
        try {
            i.a.a.a.c cVar = this.f2348d;
            if (cVar != null) {
                return cVar.h();
            }
            i.a.a.a.c cVarM = i.a.a.a.c.m();
            this.f2348d = cVarM;
            return cVarM.h();
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void d() {
        try {
            b bVar = new b();
            this.f2349e = bVar;
            this.f2348d.b(bVar);
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public void e() {
        try {
            this.f2348d.c(this.f2349e);
            this.f2349e = null;
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    public m(Context context) {
        this.f2347c = context;
        try {
            this.f2348d = i.a.a.a.c.m();
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }
}
