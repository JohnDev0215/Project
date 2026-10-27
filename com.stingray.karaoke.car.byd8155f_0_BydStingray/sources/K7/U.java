package K7;

import T.C0746i0;
import W7.AbstractC0839c;
import h8.AbstractC1386c;
import java.util.Collection;
import o8.C2061f;
import p8.C2138m;

/* JADX INFO: loaded from: classes.dex */
public final class U extends E {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Class f3750m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final u0 f3751n;

    public U(Class cls) {
        A7.m.f(cls, "jClass");
        this.f3750m = cls;
        this.f3751n = new u0(new O(this, 1));
    }

    @Override // A7.InterfaceC0057e
    public final Class d() {
        return this.f3750m;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof U) {
            if (A7.m.a(this.f3750m, ((U) obj).f3750m)) {
                return true;
            }
        }
        return false;
    }

    @Override // K7.E
    public final Collection h() {
        return n7.u.l;
    }

    public final int hashCode() {
        return this.f3750m.hashCode();
    }

    @Override // K7.E
    public final Collection i(C2061f c2061f) {
        S s6 = (S) this.f3751n.a();
        s6.getClass();
        H7.w wVar = S.f3746g[1];
        Object objA = s6.d.a();
        A7.m.e(objA, "<get-scope>(...)");
        return ((y8.n) objA).a(c2061f, Y7.b.f10798m);
    }

    @Override // K7.E
    public final Q7.L j(int i7) {
        S s6 = (S) this.f3751n.a();
        s6.getClass();
        H7.w wVar = S.f3746g[3];
        m7.o oVar = (m7.o) s6.f3748f.a();
        if (oVar == null) {
            return null;
        }
        n8.g gVar = (n8.g) oVar.l;
        j8.C c = (j8.C) oVar.f19264m;
        n8.f fVar = (n8.f) oVar.f19265n;
        C2138m c2138m = m8.k.f19337n;
        A7.m.e(c2138m, "packageLocalVariable");
        j8.G g7 = (j8.G) AbstractC1386c.i(c, c2138m, i7);
        if (g7 == null) {
            return null;
        }
        j8.X x8 = c.f17081r;
        A7.m.e(x8, "packageProto.typeTable");
        return (Q7.L) A0.f(this.f3750m, g7, gVar, new C0746i0(x8), fVar, T.f3749t);
    }

    @Override // K7.E
    public final Class l() {
        S s6 = (S) this.f3751n.a();
        s6.getClass();
        H7.w wVar = S.f3746g[2];
        Class cls = (Class) s6.f3747e.a();
        return cls == null ? this.f3750m : cls;
    }

    @Override // K7.E
    public final Collection m(C2061f c2061f) {
        S s6 = (S) this.f3751n.a();
        s6.getClass();
        H7.w wVar = S.f3746g[1];
        Object objA = s6.d.a();
        A7.m.e(objA, "<get-scope>(...)");
        return ((y8.n) objA).b(c2061f, Y7.b.f10798m);
    }

    public final String toString() {
        return "file class " + AbstractC0839c.a(this.f3750m).b();
    }
}
