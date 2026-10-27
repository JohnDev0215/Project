package X1;

import h.p;
import n3.C1930E;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h extends p {
    public final /* synthetic */ int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f9988e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ h(Object obj, int i7) {
        super(false);
        this.d = i7;
        this.f9988e = obj;
    }

    @Override // h.p
    public final void b() {
        switch (this.d) {
            case 0:
                ((W2.j) this.f9988e).m();
                throw null;
            case 1:
                ((InterfaceC2782k) this.f9988e).b(this);
                return;
            default:
                ((C1930E) this.f9988e).q();
                return;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(InterfaceC2782k interfaceC2782k, boolean z6) {
        super(z6);
        this.d = 1;
        this.f9988e = interfaceC2782k;
    }
}
