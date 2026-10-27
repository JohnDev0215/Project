package S8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public class Y extends d0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f8100n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(W w10) {
        super(true);
        boolean z6 = true;
        Q(w10);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d0.f8111m;
        InterfaceC0715l interfaceC0715l = (InterfaceC0715l) atomicReferenceFieldUpdater.get(this);
        C0716m c0716m = interfaceC0715l instanceof C0716m ? (C0716m) interfaceC0715l : null;
        if (c0716m == null) {
            z6 = false;
            break;
        }
        d0 d0VarJ = c0716m.j();
        while (!d0VarJ.L()) {
            InterfaceC0715l interfaceC0715l2 = (InterfaceC0715l) atomicReferenceFieldUpdater.get(d0VarJ);
            C0716m c0716m2 = interfaceC0715l2 instanceof C0716m ? (C0716m) interfaceC0715l2 : null;
            if (c0716m2 == null) {
                z6 = false;
                break;
            }
            d0VarJ = c0716m2.j();
        }
        this.f8100n = z6;
    }

    @Override // S8.d0
    public final boolean L() {
        return this.f8100n;
    }

    @Override // S8.d0
    public final boolean M() {
        return true;
    }
}
