package N;

import A7.m;
import A7.o;
import E0.AbstractC0156f;
import N0.C0411f;
import N0.G;
import N0.H;
import N0.L;
import S0.InterfaceC0702n;
import java.util.List;
import m7.q;
import n0.C1891t;
import n0.InterfaceC1892u;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4254m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f4255n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(h hVar, int i7) {
        super(1);
        this.f4254m = i7;
        this.f4255n = hVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        H h10;
        switch (this.f4254m) {
            case 0:
                List list = (List) obj;
                h hVar = this.f4255n;
                H h11 = hVar.H0().f4236n;
                if (h11 != null) {
                    G g7 = h11.f4348a;
                    C0411f c0411f = g7.f4340a;
                    L l = hVar.f4270z;
                    InterfaceC1892u interfaceC1892u = hVar.f4263I;
                    h10 = new H(new G(c0411f, L.f(l, interfaceC1892u != null ? interfaceC1892u.a() : C1891t.f19452k, 0L, null, null, null, 0L, null, 0, 0L, 16777214), g7.c, g7.d, g7.f4342e, g7.f4343f, g7.f4344g, g7.f4345h, g7.f4346i, g7.f4347j), h11.f4349b, h11.c);
                    list.add(h10);
                } else {
                    h10 = null;
                }
                break;
            case 1:
                C0411f c0411f2 = (C0411f) obj;
                h hVar2 = this.f4255n;
                f fVar = hVar2.f4268N;
                if (fVar == null) {
                    f fVar2 = new f(hVar2.f4269y, c0411f2);
                    d dVar = new d(c0411f2, hVar2.f4270z, hVar2.f4256A, hVar2.f4257C, hVar2.f4258D, hVar2.f4259E, hVar2.f4260F, hVar2.f4261G);
                    dVar.c(hVar2.H0().f4234k);
                    fVar2.d = dVar;
                    hVar2.f4268N = fVar2;
                } else if (!m.a(c0411f2, fVar.f4253b)) {
                    fVar.f4253b = c0411f2;
                    d dVar2 = fVar.d;
                    if (dVar2 != null) {
                        L l10 = hVar2.f4270z;
                        InterfaceC0702n interfaceC0702n = hVar2.f4256A;
                        int i7 = hVar2.f4257C;
                        boolean z6 = hVar2.f4258D;
                        int i10 = hVar2.f4259E;
                        int i11 = hVar2.f4260F;
                        List list2 = hVar2.f4261G;
                        dVar2.f4226a = c0411f2;
                        dVar2.f4227b = l10;
                        dVar2.c = interfaceC0702n;
                        dVar2.d = i7;
                        dVar2.f4228e = z6;
                        dVar2.f4229f = i10;
                        dVar2.f4230g = i11;
                        dVar2.f4231h = list2;
                        dVar2.l = null;
                        dVar2.f4236n = null;
                        dVar2.f4237p = -1;
                        dVar2.o = -1;
                        q qVar = q.f19267a;
                    }
                }
                AbstractC0156f.o(hVar2);
                AbstractC0156f.n(hVar2);
                AbstractC0156f.m(hVar2);
                break;
            default:
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                h hVar3 = this.f4255n;
                f fVar3 = hVar3.f4268N;
                if (fVar3 != null) {
                    InterfaceC2782k interfaceC2782k = hVar3.f4264J;
                    if (interfaceC2782k != null) {
                        interfaceC2782k.b(fVar3);
                    }
                    f fVar4 = hVar3.f4268N;
                    if (fVar4 != null) {
                        fVar4.c = zBooleanValue;
                    }
                    AbstractC0156f.o(hVar3);
                    AbstractC0156f.n(hVar3);
                    AbstractC0156f.m(hVar3);
                }
                break;
        }
        return Boolean.TRUE;
    }
}
