package D;

import F0.C0225r0;
import G.C0297g;
import K.J0;
import T.C0735d;
import T.C0761q;
import o3.C2042o;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: D.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0130l extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1084m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f1085n;
    public final /* synthetic */ Object o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0130l(int i7, int i10, Object obj) {
        super(2);
        this.f1084m = i10;
        this.o = obj;
        this.f1085n = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f1084m) {
            case 0:
                ((Number) obj2).intValue();
                int iZ = C0735d.Z(this.f1085n | 1);
                AbstractC0133o.a((g0.o) this.o, (C0761q) obj, iZ);
                break;
            case 1:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    E.l lVar = (E.l) this.o;
                    B5.u uVar = lVar.f1362b.f1360b;
                    int i7 = this.f1085n;
                    C0297g c0297gM = uVar.m(i7);
                    int i10 = i7 - c0297gM.f2560a;
                    ((E.h) c0297gM.c).c.o(lVar.c, Integer.valueOf(i10), c0761q, 0);
                }
                break;
            case 2:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    B5.u uVar2 = ((F.h) this.o).f1882b.c;
                    int i11 = this.f1085n;
                    C0297g c0297gM2 = uVar2.m(i11);
                    ((F.f) c0297gM2.c).c.o(F.i.f1883a, Integer.valueOf(i11 - c0297gM2.f2560a), c0761q2, 6);
                }
                break;
            case 3:
                ((Number) obj2).intValue();
                int iZ2 = C0735d.Z(this.f1085n | 1);
                ((C0225r0) this.o).a(iZ2, (C0761q) obj);
                break;
            case 4:
                ((Number) obj2).intValue();
                int iZ3 = C0735d.Z(this.f1085n | 1);
                G.E.c((b0.b) this.o, (C0761q) obj, iZ3);
                break;
            case 5:
                ((Number) obj2).intValue();
                int iZ4 = C0735d.Z(this.f1085n | 1);
                K.X.g((O.K) this.o, (C0761q) obj, iZ4);
                break;
            case 6:
                ((Number) obj2).intValue();
                int iZ5 = C0735d.Z(this.f1085n | 1);
                ((J0) this.o).a(iZ5, (C0761q) obj);
                break;
            case 7:
                ((Number) obj2).intValue();
                int iZ6 = C0735d.Z(this.f1085n | 1);
                ((e1.s) this.o).a(iZ6, (C0761q) obj);
                break;
            case 8:
                ((Number) obj2).intValue();
                int iZ7 = C0735d.Z(this.f1085n | 1);
                ((e1.x) this.o).a(iZ7, (C0761q) obj);
                break;
            case 9:
                ((Number) obj2).intValue();
                int iZ8 = C0735d.Z(this.f1085n | 1);
                h6.i.a((C2042o) this.o, (C0761q) obj, iZ8);
                break;
            default:
                ((Number) obj2).intValue();
                int iZ9 = C0735d.Z(this.f1085n | 1);
                ((x.L) this.o).a(iZ9, (C0761q) obj);
                break;
        }
        return m7.q.f19267a;
    }
}
