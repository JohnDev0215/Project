package com.byd.cleardata.h;

import android.annotation.SuppressLint;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public abstract class d extends c.a.c<m> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected c.a.g<? super m> f2676a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected AtomicBoolean f2677b = new AtomicBoolean(false);

    @Override // c.a.c
    protected void j(c.a.g<? super m> gVar) {
        this.f2676a = gVar;
        try {
            m(gVar);
        } catch (Throwable th) {
            gVar.b(th);
        }
        gVar.a();
    }

    public void l() {
        this.f2677b.set(true);
    }

    public abstract void m(c.a.g<? super m> gVar);

    @SuppressLint({"NewApi"})
    protected long n(List<com.byd.cleardata.d.d> list) {
        if (list == null) {
            return 0L;
        }
        long j = 0;
        for (com.byd.cleardata.d.d dVar : list) {
            if (o()) {
                return 0L;
            }
            long jB = dVar.B();
            if (jB < 0) {
                return 0L;
            }
            if (jB == 0) {
                jB = 1;
            }
            j += jB;
        }
        return j;
    }

    public boolean o() {
        if (this.f2677b.get()) {
            com.byd.cleardata.i.c.b("cancelTask");
        }
        return this.f2677b.get();
    }
}
