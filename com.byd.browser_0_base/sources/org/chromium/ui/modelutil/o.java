package org.chromium.ui.modelutil;

import defpackage.im2;
import defpackage.q81;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.n;

/* JADX INFO: compiled from: SimpleRecyclerViewMcpBase.java */
/* JADX INFO: loaded from: classes3.dex */
public class o<T, VH, P> extends org.chromium.ui.modelutil.a<P> implements l.a<VH, P> {
    public final q81 f;
    public final a<T> g;
    public final b<T, VH, P> h;

    /* JADX INFO: compiled from: SimpleRecyclerViewMcpBase.java */
    public interface a<T> {
        int f(T t);
    }

    /* JADX INFO: compiled from: SimpleRecyclerViewMcpBase.java */
    public interface b<T, VH, P> {
    }

    public o(a aVar, im2 im2Var, q81 q81Var) {
        this.g = aVar;
        this.h = im2Var;
        this.f = q81Var;
        q81Var.g(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.chromium.ui.modelutil.l.a
    public final void a(VH vh, int i, P p) {
        ((n.a) ((im2) this.h).e).h(vh, this.f.get(i));
    }

    @Override // org.chromium.ui.modelutil.l.a
    public final int d() {
        return this.f.size();
    }

    @Override // org.chromium.ui.modelutil.l.a
    public final int getItemViewType(int i) {
        a<T> aVar = this.g;
        if (aVar == null) {
            return 0;
        }
        return aVar.f((T) this.f.get(i));
    }
}
