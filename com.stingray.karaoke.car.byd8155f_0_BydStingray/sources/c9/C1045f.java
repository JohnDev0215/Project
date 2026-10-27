package c9;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;

/* JADX INFO: renamed from: c9.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1045f implements Closeable, Flushable {
    public final e9.g l;

    public C1045f(File file, long j3) {
        this.l = new e9.g(file, j3, f9.c.f14651h);
    }

    public final void b(H0.b bVar) {
        A7.m.f(bVar, "request");
        e9.g gVar = this.l;
        String strV = w0.c.V((w) bVar.f2765m);
        synchronized (gVar) {
            A7.m.f(strV, "key");
            gVar.k();
            gVar.b();
            e9.g.G(strV);
            e9.d dVar = (e9.d) gVar.f14519s.get(strV);
            if (dVar == null) {
                return;
            }
            gVar.C(dVar);
            if (gVar.f14517q <= gVar.f14514m) {
                gVar.f14525y = false;
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.l.close();
    }

    @Override // java.io.Flushable
    public final void flush() {
        this.l.flush();
    }
}
