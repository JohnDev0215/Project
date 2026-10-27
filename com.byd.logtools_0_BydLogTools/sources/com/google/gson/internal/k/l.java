package com.google.gson.internal.k;

import com.google.gson.p;
import com.google.gson.q;
import java.io.IOException;

/* JADX INFO: compiled from: TreeTypeAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> extends p<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.gson.n<T> f4096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.gson.h<T> f4097b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final com.google.gson.d f4098c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.google.gson.s.a<T> f4099d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final q f4100e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final l<T>.b f4101f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private p<T> f4102g;

    /* JADX INFO: compiled from: TreeTypeAdapter.java */
    private final class b implements com.google.gson.m, com.google.gson.g {
        private b() {
        }
    }

    public l(com.google.gson.n<T> nVar, com.google.gson.h<T> hVar, com.google.gson.d dVar, com.google.gson.s.a<T> aVar, q qVar) {
        this.f4096a = nVar;
        this.f4097b = hVar;
        this.f4098c = dVar;
        this.f4099d = aVar;
        this.f4100e = qVar;
    }

    private p<T> e() {
        p<T> pVar = this.f4102g;
        if (pVar != null) {
            return pVar;
        }
        p<T> pVarM = this.f4098c.m(this.f4100e, this.f4099d);
        this.f4102g = pVarM;
        return pVarM;
    }

    @Override // com.google.gson.p
    public T b(com.google.gson.stream.a aVar) throws IOException {
        if (this.f4097b == null) {
            return e().b(aVar);
        }
        com.google.gson.i iVarA = com.google.gson.internal.i.a(aVar);
        if (iVarA.e()) {
            return null;
        }
        return this.f4097b.a(iVarA, this.f4099d.e(), this.f4101f);
    }

    @Override // com.google.gson.p
    public void d(com.google.gson.stream.b bVar, T t) throws IOException {
        com.google.gson.n<T> nVar = this.f4096a;
        if (nVar == null) {
            e().d(bVar, t);
        } else if (t == null) {
            bVar.m();
        } else {
            com.google.gson.internal.i.b(nVar.a(t, this.f4099d.e(), this.f4101f), bVar);
        }
    }
}
