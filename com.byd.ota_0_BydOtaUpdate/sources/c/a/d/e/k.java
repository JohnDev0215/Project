package c.a.d.e;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import c.a.h.c.b;

/* JADX INFO: compiled from: OtaToolCommandStub.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2415a = k.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2416b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.a.d.a f2417c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c.a.g.d f2418d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.a.g.c f2419e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c.a.a.a f2420f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c.a.b.c.a f2421g = c.a.b.c.a.k();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c.a.h.c.a f2422h;

    public k(Context context, c.a.g.c cVar, c.a.a.a aVar, c.a.d.a aVar2, c.a.g.d dVar) {
        this.f2416b = context;
        this.f2417c = aVar2;
        this.f2418d = dVar;
        this.f2419e = cVar;
        this.f2420f = aVar;
    }

    @Override // c.a.h.c.b
    public void a(int i2, long j, boolean z, float f2, double d2, String str) throws RemoteException {
    }

    @Override // c.a.h.c.b
    public void c(Bundle bundle) throws RemoteException {
    }

    @Override // c.a.h.c.b
    public void q(c.a.h.c.a aVar) throws RemoteException {
        this.f2422h = aVar;
    }

    @Override // c.a.h.c.b
    public void s(c.a.h.c.a aVar) throws RemoteException {
        this.f2422h = null;
    }
}
