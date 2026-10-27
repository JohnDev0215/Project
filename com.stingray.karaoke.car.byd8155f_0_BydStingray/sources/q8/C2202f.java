package q8;

import F8.AbstractC0287w;
import F8.O;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: q8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2202f extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f21278m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C2203g f21279n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2202f(C2203g c2203g, int i7) {
        super(1);
        this.f21278m = i7;
        this.f21279n = c2203g;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f21278m) {
            case 0:
                O o = (O) obj;
                A7.m.f(o, "it");
                if (o.c()) {
                    return "*";
                }
                AbstractC0287w abstractC0287wB = o.b();
                A7.m.e(abstractC0287wB, "it.type");
                String strY = this.f21279n.Y(abstractC0287wB);
                if (o.a() == 1) {
                    return strY;
                }
                return A7.k.x(o.a()) + ' ' + strY;
            case 1:
                t8.g gVar = (t8.g) obj;
                A7.m.f(gVar, "it");
                return this.f21279n.B(gVar);
            default:
                AbstractC0287w abstractC0287w = (AbstractC0287w) obj;
                A7.m.e(abstractC0287w, "it");
                return this.f21279n.Y(abstractC0287w);
        }
    }
}
