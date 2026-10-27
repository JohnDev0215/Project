package w;

import x.InterfaceC2615D;
import x.s0;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class y extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f23151m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ F f23152n;
    public final /* synthetic */ G o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(F f10, G g7, int i7) {
        super(1);
        this.f23151m = i7;
        this.f23152n = f10;
        this.o = g7;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC2615D interfaceC2615D;
        InterfaceC2615D interfaceC2615D2;
        switch (this.f23151m) {
            case 0:
                s0 s0Var = (s0) obj;
                w wVar = w.l;
                w wVar2 = w.f23145m;
                if (s0Var.b(wVar, wVar2)) {
                    H h10 = this.f23152n.f23067a.f23083a;
                    return (h10 == null || (interfaceC2615D2 = h10.f23071b) == null) ? AbstractC2565A.f23046b : interfaceC2615D2;
                }
                if (!s0Var.b(wVar2, w.f23146n)) {
                    return AbstractC2565A.f23046b;
                }
                H h11 = this.o.f23069a.f23083a;
                return (h11 == null || (interfaceC2615D = h11.f23071b) == null) ? AbstractC2565A.f23046b : interfaceC2615D;
            case 1:
                int iOrdinal = ((w) obj).ordinal();
                float f10 = 1.0f;
                if (iOrdinal == 0) {
                    H h12 = this.f23152n.f23067a.f23083a;
                    if (h12 != null) {
                        f10 = h12.f23070a;
                    }
                } else if (iOrdinal != 1) {
                    if (iOrdinal != 2) {
                        throw new A9.k();
                    }
                    H h13 = this.o.f23069a.f23083a;
                    if (h13 != null) {
                        f10 = h13.f23070a;
                    }
                }
                return Float.valueOf(f10);
            case 2:
                s0 s0Var2 = (s0) obj;
                w wVar3 = w.l;
                w wVar4 = w.f23145m;
                if (!s0Var2.b(wVar3, wVar4) && s0Var2.b(wVar4, w.f23146n)) {
                    P p10 = this.o.f23069a;
                    return AbstractC2565A.f23046b;
                }
                return AbstractC2565A.f23046b;
            default:
                int iOrdinal2 = ((w) obj).ordinal();
                if (iOrdinal2 != 0 && iOrdinal2 != 1) {
                    if (iOrdinal2 != 2) {
                        throw new A9.k();
                    }
                    P p11 = this.o.f23069a;
                }
                return Float.valueOf(1.0f);
        }
    }
}
