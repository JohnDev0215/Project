package C0;

import java.util.List;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D extends E0.D {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ G f845b;
    public final /* synthetic */ InterfaceC2785n c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(G g7, InterfaceC2785n interfaceC2785n, String str) {
        super(str);
        this.f845b = g7;
        this.c = interfaceC2785n;
    }

    @Override // C0.K
    public final L h(M m10, List list, long j3) {
        G g7 = this.f845b;
        a1.k layoutDirection = m10.getLayoutDirection();
        B b10 = g7.f854s;
        b10.l = layoutDirection;
        b10.f840m = m10.getDensity();
        b10.f841n = m10.p();
        boolean zV = m10.v();
        InterfaceC2785n interfaceC2785n = this.c;
        if (zV || g7.l.f1482n == null) {
            g7.o = 0;
            L l = (L) interfaceC2785n.invoke(b10, new a1.a(j3));
            return new C(l, g7, g7.o, l, 1);
        }
        g7.f851p = 0;
        L l10 = (L) interfaceC2785n.invoke(g7.f855t, new a1.a(j3));
        return new C(l10, g7, g7.f851p, l10, 0);
    }
}
