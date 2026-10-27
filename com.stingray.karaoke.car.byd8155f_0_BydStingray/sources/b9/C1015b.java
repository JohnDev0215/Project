package b9;

import B2.q;
import Q5.O;
import S8.C0711h;
import S8.C0712i;
import S8.InterfaceC0710g;
import S8.v0;
import U8.C0811b;
import X8.r;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.InterfaceC2194h;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: b9.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1015b implements InterfaceC0710g, v0 {
    public final C0712i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f12333m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c f12334n;

    public C1015b(c cVar, C0712i c0712i, Object obj) {
        this.f12334n = cVar;
        this.l = c0712i;
        this.f12333m = obj;
    }

    @Override // S8.v0
    public final void b(r rVar, int i7) {
        this.l.b(rVar, i7);
    }

    @Override // q7.InterfaceC2189c
    public final InterfaceC2194h getContext() {
        return this.l.f8118p;
    }

    @Override // S8.InterfaceC0710g
    public final q i(Object obj, InterfaceC2786o interfaceC2786o) {
        c cVar = this.f12334n;
        C0811b c0811b = new C0811b(cVar, 1, this);
        q qVarI = this.l.i((m7.q) obj, c0811b);
        if (qVarI != null) {
            c.f12335h.set(cVar, this.f12333m);
        }
        return qVarI;
    }

    @Override // S8.InterfaceC0710g
    public final boolean l(Throwable th) {
        return this.l.l(th);
    }

    @Override // S8.InterfaceC0710g
    public final void n(Object obj, InterfaceC2786o interfaceC2786o) throws IllegalAccessException, InvocationTargetException {
        m7.q qVar = m7.q.f19267a;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c.f12335h;
        c cVar = this.f12334n;
        atomicReferenceFieldUpdater.set(cVar, this.f12333m);
        O o = new O(cVar, 11, this);
        C0712i c0712i = this.l;
        c0712i.B(qVar, c0712i.f8083n, new C0711h(o, 0));
    }

    @Override // S8.InterfaceC0710g
    public final void r(Object obj) {
        this.l.r(obj);
    }

    @Override // q7.InterfaceC2189c
    public final void resumeWith(Object obj) {
        this.l.resumeWith(obj);
    }
}
