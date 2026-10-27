package K;

import y0.AbstractC2737o;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0343h0 extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3286m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0355n0 f3287n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0343h0(InterfaceC0355n0 interfaceC0355n0, int i7) {
        super(1);
        this.f3286m = i7;
        this.f3287n = interfaceC0355n0;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3286m) {
            case 0:
                this.f3287n.c(((m0.c) obj).f18880a);
                break;
            default:
                y0.q qVar = (y0.q) obj;
                this.f3287n.e(AbstractC2737o.i(qVar, false));
                qVar.a();
                break;
        }
        return m7.q.f19267a;
    }
}
