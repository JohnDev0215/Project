package T;

import a0.C0906b;
import q7.InterfaceC2193g;

/* JADX INFO: loaded from: classes.dex */
public final class S implements InterfaceC2193g, G0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ S f8221m = new S(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final S f8222n = new S(1);
    public static final S o = new S(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final S f8223p = new S(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final S f8224q = new S(4);
    public final /* synthetic */ int l;

    public /* synthetic */ S(int i7) {
        this.l = i7;
    }

    public static final void b(S s6) {
        V8.f0 f0Var;
        W.e eVar;
        Z.b bVar;
        V8.f0 f0Var2 = C0767t0.f8349v;
        do {
            f0Var = C0767t0.f8349v;
            eVar = (W.e) f0Var.getValue();
            bVar = (Z.b) eVar;
            Y.c cVarC = bVar.f10808n;
            Z.a aVar = (Z.a) cVarC.get(s6);
            if (aVar != null) {
                int iHashCode = s6 != null ? s6.hashCode() : 0;
                Y.m mVar = cVarC.l;
                Y.m mVarV = mVar.v(iHashCode, s6, 0);
                if (mVar != mVarV) {
                    cVarC = mVarV == null ? Y.c.f10586n : new Y.c(mVarV, cVarC.f10587m - 1);
                }
                C0906b c0906b = C0906b.f11026a;
                Object obj = aVar.f10805a;
                boolean z6 = obj != c0906b;
                Object obj2 = aVar.f10806b;
                if (z6) {
                    Object obj3 = cVarC.get(obj);
                    A7.m.c(obj3);
                    cVarC = cVarC.c(obj, new Z.a(((Z.a) obj3).f10805a, obj2));
                }
                if (obj2 != c0906b) {
                    Object obj4 = cVarC.get(obj2);
                    A7.m.c(obj4);
                    cVarC = cVarC.c(obj2, new Z.a(obj, ((Z.a) obj4).f10806b));
                }
                Object obj5 = obj != c0906b ? bVar.l : obj2;
                if (obj2 != c0906b) {
                    obj = bVar.f10807m;
                }
                bVar = new Z.b(obj5, obj, cVarC);
            }
            if (eVar == bVar) {
                return;
            }
        } while (!f0Var.k(eVar, bVar));
    }

    @Override // T.G0
    public boolean a(Object obj, Object obj2) {
        switch (this.l) {
            case 1:
                return false;
            case 2:
                return obj == obj2;
            default:
                return A7.m.a(obj, obj2);
        }
    }

    public String toString() {
        switch (this.l) {
            case 1:
                return "NeverEqualPolicy";
            case 2:
                return "ReferentialEqualityPolicy";
            case 3:
            default:
                return super.toString();
            case 4:
                return "StructuralEqualityPolicy";
            case 5:
                return "Empty";
        }
    }
}
