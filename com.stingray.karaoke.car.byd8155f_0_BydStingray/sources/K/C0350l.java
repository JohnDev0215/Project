package K;

import e0.C1178r;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0350l extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3333m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ J0 f3334n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0350l(J0 j02, InterfaceC2782k interfaceC2782k, int i7) {
        super(1);
        this.f3333m = i7;
        this.f3334n = j02;
        this.o = interfaceC2782k;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3333m) {
            case 0:
                N0.H h10 = (N0.H) obj;
                J0 j02 = this.f3334n;
                if (j02 != null) {
                    j02.f3173b.setValue(h10);
                }
                InterfaceC2782k interfaceC2782k = this.o;
                if (interfaceC2782k != null) {
                    interfaceC2782k.b(h10);
                }
                return m7.q.f19267a;
            default:
                J0 j03 = this.f3334n;
                C1178r c1178r = j03.d;
                InterfaceC2782k interfaceC2782k2 = this.o;
                c1178r.add(interfaceC2782k2);
                return new F0.X(j03, 4, interfaceC2782k2);
        }
    }
}
