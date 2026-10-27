package U8;

import S8.AbstractC0727y;
import S8.C0712i;
import S8.v0;
import h8.AbstractC1386c;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class d implements v0 {
    public Object l = n.f9262p;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0712i f9229m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ l f9230n;

    public d(l lVar) {
        this.f9230n = lVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object a(AbstractC2410c abstractC2410c) throws Throwable {
        t tVar;
        Object obj;
        t tVar2;
        boolean z6 = false;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object obj2 = this.l;
        if (obj2 == n.f9262p || obj2 == n.l) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = l.f9244t;
            l lVar = this.f9230n;
            t tVar3 = (t) atomicReferenceFieldUpdater.get(lVar);
            while (!lVar.B()) {
                long andIncrement = l.f9240p.getAndIncrement(lVar);
                long j3 = n.f9252b;
                long j10 = andIncrement / j3;
                int i7 = (int) (andIncrement % j3);
                if (tVar3.f10577n != j10) {
                    t tVarU = lVar.u(j10, tVar3);
                    if (tVarU == null) {
                        continue;
                    } else {
                        tVar = tVarU;
                    }
                } else {
                    tVar = tVar3;
                }
                Object objL = lVar.L(tVar, i7, andIncrement, null);
                B2.q qVar = n.f9260m;
                if (objL == qVar) {
                    throw new IllegalStateException("unreachable");
                }
                B2.q qVar2 = n.o;
                if (objL == qVar2) {
                    if (andIncrement < lVar.y()) {
                        tVar.b();
                    }
                    tVar3 = tVar;
                } else {
                    if (objL == n.f9261n) {
                        l lVar2 = this.f9230n;
                        C0712i c0712iP = AbstractC0727y.p(h6.e.k(abstractC2410c));
                        try {
                            this.f9229m = c0712iP;
                            X8.r rVar = tVar;
                            Object objL2 = lVar2.L(tVar, i7, andIncrement, this);
                            if (objL2 == qVar) {
                                b(rVar, i7);
                            } else {
                                InterfaceC2786o c0811b = null;
                                InterfaceC2782k interfaceC2782k = lVar2.f9248m;
                                if (objL2 == qVar2) {
                                    if (andIncrement < lVar2.y()) {
                                        rVar.b();
                                    }
                                    t tVar4 = (t) l.f9244t.get(lVar2);
                                    while (true) {
                                        if (lVar2.B()) {
                                            C0712i c0712i = this.f9229m;
                                            A7.m.c(c0712i);
                                            this.f9229m = null;
                                            this.l = n.l;
                                            Throwable thV = lVar.v();
                                            if (thV == null) {
                                                c0712i.resumeWith(Boolean.FALSE);
                                            } else {
                                                c0712i.resumeWith(AbstractC1386c.f(thV));
                                            }
                                        } else {
                                            long andIncrement2 = l.f9240p.getAndIncrement(lVar2);
                                            long j11 = n.f9252b;
                                            long j12 = andIncrement2 / j11;
                                            int i10 = (int) (andIncrement2 % j11);
                                            if (tVar4.f10577n != j12) {
                                                t tVarU2 = lVar2.u(j12, tVar4);
                                                if (tVarU2 != null) {
                                                    tVar2 = tVarU2;
                                                }
                                            } else {
                                                tVar2 = tVar4;
                                            }
                                            Object objL3 = lVar2.L(tVar2, i10, andIncrement2, this);
                                            if (objL3 == n.f9260m) {
                                                b(tVar2, i10);
                                                break;
                                            }
                                            if (objL3 == n.o) {
                                                if (andIncrement2 < lVar2.y()) {
                                                    tVar2.b();
                                                }
                                                tVar4 = tVar2;
                                            } else {
                                                if (objL3 == n.f9261n) {
                                                    throw new IllegalStateException("unexpected");
                                                }
                                                tVar2.b();
                                                this.l = objL3;
                                                this.f9229m = null;
                                                obj = Boolean.TRUE;
                                                if (interfaceC2782k != null) {
                                                    c0811b = new C0811b(interfaceC2782k, objArr2 == true ? 1 : 0, objL3);
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    rVar.b();
                                    this.l = objL2;
                                    this.f9229m = null;
                                    obj = Boolean.TRUE;
                                    if (interfaceC2782k != null) {
                                        c0811b = new C0811b(interfaceC2782k, objArr == true ? 1 : 0, objL2);
                                    }
                                }
                                c0712iP.n(obj, c0811b);
                            }
                            Object objQ = c0712iP.q();
                            EnumC2303a enumC2303a = EnumC2303a.l;
                            return objQ;
                        } catch (Throwable th) {
                            c0712iP.A();
                            throw th;
                        }
                    }
                    tVar.b();
                    this.l = objL;
                    z6 = true;
                }
            }
            this.l = n.l;
            Throwable thV2 = lVar.v();
            if (thV2 != null) {
                int i11 = X8.s.f10578a;
                throw thV2;
            }
        } else {
            z6 = true;
        }
        return Boolean.valueOf(z6);
    }

    @Override // S8.v0
    public final void b(X8.r rVar, int i7) {
        C0712i c0712i = this.f9229m;
        if (c0712i != null) {
            c0712i.b(rVar, i7);
        }
    }

    public final Object c() throws Throwable {
        Object obj = this.l;
        B2.q qVar = n.f9262p;
        if (obj == qVar) {
            throw new IllegalStateException("`hasNext()` has not been invoked");
        }
        this.l = qVar;
        if (obj != n.l) {
            return obj;
        }
        Throwable thW = this.f9230n.w();
        int i7 = X8.s.f10578a;
        throw thW;
    }
}
