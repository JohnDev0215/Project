package org.chromium.ui.modelutil;

import org.chromium.ui.modelutil.d;

/* JADX INFO: compiled from: ListModelChangeProcessor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c<M extends d<P>, V, P> implements d.a<P> {
    public final V e;
    public final M f;
    public final a<M, V, P> g;

    /* JADX INFO: compiled from: ListModelChangeProcessor.java */
    public interface a<M, V, P> {
        void a(d dVar, int i, int i2, Object obj);

        void e(d dVar, int i, Object obj, Object obj2, int i2);

        void f(d dVar, int i, int i2, Object obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public c(h hVar, Object obj, a aVar) {
        this.f = hVar;
        this.e = obj;
        this.g = aVar;
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void b(int i, int i2) {
        this.g.a(this.f, i, i2, this.e);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void i(int i, int i2, Object obj) {
        this.g.e(this.f, i, this.e, obj, i2);
    }

    @Override // org.chromium.ui.modelutil.d.a
    public final void j(int i, int i2) {
        this.g.f(this.f, i, i2, this.e);
    }
}
