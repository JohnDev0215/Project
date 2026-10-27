package b.b.g.k;

import android.os.RemoteException;
import f.a.a.a.d;

/* JADX INFO: compiled from: BydTBoxListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class q extends d.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f867a = "BydTBoxListener";

    @Override // f.a.a.a.d
    public void c(byte b2) throws RemoteException {
    }

    @Override // f.a.a.a.d
    public void d(byte b2) throws RemoteException {
        b.b.m.c.a.a(this.f867a, "onApn3Changed:" + ((int) b2));
    }

    @Override // f.a.a.a.d
    public void g(byte b2) throws RemoteException {
        b.b.m.c.a.a(this.f867a, "onApn2Changed:" + ((int) b2));
    }

    @Override // f.a.a.a.d
    public void h(byte b2) throws RemoteException {
    }

    @Override // f.a.a.a.d
    public void k(byte b2) throws RemoteException {
        b.b.m.c.a.a(this.f867a, "onApn1Changed:" + ((int) b2));
    }

    @Override // f.a.a.a.d
    public void o(f.a.a.a.b bVar) throws RemoteException {
    }
}
