package P7;

import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class m extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5445m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2061f f5446n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m(C2061f c2061f, int i7) {
        super(1);
        this.f5445m = i7;
        this.f5446n = c2061f;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f5445m) {
            case 0:
                y8.n nVar = (y8.n) obj;
                A7.m.f(nVar, "it");
                return nVar.a(this.f5446n, Y7.b.l);
            default:
                y8.n nVar2 = (y8.n) obj;
                A7.m.f(nVar2, "it");
                return nVar2.b(this.f5446n, Y7.b.f10800p);
        }
    }
}
