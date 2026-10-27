package w;

import x.s0;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: w.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2568D extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23053m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2569E f23054n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2568D(C2569E c2569e, int i7) {
        super(1);
        this.f23053m = i7;
        this.f23054n = c2569e;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        N n10;
        switch (this.f23053m) {
            case 0:
                s0 s0Var = (s0) obj;
                w wVar = w.l;
                w wVar2 = w.f23145m;
                boolean zB = s0Var.b(wVar, wVar2);
                Object obj2 = null;
                C2569E c2569e = this.f23054n;
                if (zB) {
                    t tVar = c2569e.f23056C.f23067a.c;
                    if (tVar != null) {
                        obj2 = tVar.c;
                    }
                } else if (s0Var.b(wVar2, w.f23146n)) {
                    t tVar2 = c2569e.f23057D.f23069a.c;
                    if (tVar2 != null) {
                        obj2 = tVar2.c;
                    }
                } else {
                    obj2 = AbstractC2565A.d;
                }
                return obj2 == null ? AbstractC2565A.d : obj2;
            default:
                s0 s0Var2 = (s0) obj;
                w wVar3 = w.l;
                w wVar4 = w.f23145m;
                boolean zB2 = s0Var2.b(wVar3, wVar4);
                C2569E c2569e2 = this.f23054n;
                if (zB2) {
                    N n11 = c2569e2.f23056C.f23067a.f23084b;
                    return n11 != null ? n11.f23081b : AbstractC2565A.c;
                }
                if (s0Var2.b(wVar4, w.f23146n) && (n10 = c2569e2.f23057D.f23069a.f23084b) != null) {
                    return n10.f23081b;
                }
                return AbstractC2565A.c;
        }
    }
}
