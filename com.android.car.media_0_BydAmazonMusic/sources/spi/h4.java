package spi;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: Gson.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h4 extends nb<AtomicLong> {
    public final /* synthetic */ nb a;

    public h4(nb nbVar) {
        this.a = nbVar;
    }

    @Override // spi.nb
    public final AtomicLong a(o5 o5Var) {
        return new AtomicLong(((Number) this.a.a(o5Var)).longValue());
    }

    @Override // spi.nb
    public final void b(v5 v5Var, AtomicLong atomicLong) {
        this.a.b(v5Var, Long.valueOf(atomicLong.get()));
    }
}
