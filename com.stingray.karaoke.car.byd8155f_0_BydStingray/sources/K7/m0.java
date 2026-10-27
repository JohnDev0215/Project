package K7;

import A7.C0055c;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import l8.InterfaceC1788f;
import o8.C2057b;
import q8.C2203g;

/* JADX INFO: loaded from: classes.dex */
public abstract class m0 extends r implements H7.w {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Object f3811s = new Object();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final E f3812m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f3813n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Object f3814p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f3815q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final t0 f3816r;

    public m0(E e10, String str, String str2, T7.H h10, Object obj) {
        this.f3812m = e10;
        this.f3813n = str;
        this.o = str2;
        this.f3814p = obj;
        this.f3815q = h6.r.n(m7.g.l, new l0(this, 1));
        this.f3816r = v0.n(h10, new l0(this, 0));
    }

    public final boolean equals(Object obj) {
        m0 m0VarC = A0.c(obj);
        return m0VarC != null && A7.m.a(this.f3812m, m0VarC.f3812m) && A7.m.a(this.f3813n, m0VarC.f3813n) && A7.m.a(this.o, m0VarC.o) && A7.m.a(this.f3814p, m0VarC.f3814p);
    }

    @Override // H7.InterfaceC0317b
    public final String getName() {
        return this.f3813n;
    }

    public final int hashCode() {
        return this.o.hashCode() + M5.b.c(this.f3813n, this.f3812m.hashCode() * 31, 31);
    }

    @Override // K7.r
    public final L7.e i() {
        return u().i();
    }

    @Override // K7.r
    public final E j() {
        return this.f3812m;
    }

    @Override // K7.r
    public final L7.e l() {
        u().getClass();
        return null;
    }

    @Override // H7.InterfaceC0317b
    public final boolean m() {
        return false;
    }

    @Override // K7.r
    public final boolean r() {
        return !A7.m.a(this.f3814p, C0055c.l);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object, m7.f] */
    public final Member s() {
        if (!n().f0()) {
            return null;
        }
        C2057b c2057b = y0.f3854a;
        v0 v0VarB = y0.b(n());
        if (v0VarB instanceof C0389m) {
            C0389m c0389m = (C0389m) v0VarB;
            m8.e eVar = c0389m.o;
            if ((eVar.f19291m & 16) == 16) {
                m8.c cVar = eVar.f19295r;
                int i7 = cVar.f19280m;
                if ((i7 & 1) != 1 || (i7 & 2) != 2) {
                    return null;
                }
                int i10 = cVar.f19281n;
                InterfaceC1788f interfaceC1788f = c0389m.f3808p;
                return this.f3812m.g(interfaceC1788f.n(i10), interfaceC1788f.n(cVar.o));
            }
        }
        return (Field) this.f3815q.getValue();
    }

    @Override // K7.r
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final Q7.L n() {
        Object objA = this.f3816r.a();
        A7.m.e(objA, "_descriptor()");
        return (Q7.L) objA;
    }

    public final String toString() {
        C2203g c2203g = x0.f3852a;
        return x0.c(n());
    }

    public abstract i0 u();

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public m0(E e10, String str, String str2, Object obj) {
        this(e10, str, str2, null, obj);
        A7.m.f(e10, "container");
        A7.m.f(str, "name");
        A7.m.f(str2, "signature");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public m0(E e10, T7.H h10) {
        A7.m.f(e10, "container");
        A7.m.f(h10, "descriptor");
        String strB = h10.getName().b();
        A7.m.e(strB, "descriptor.name.asString()");
        this(e10, strB, y0.b(h10).i(), h10, C0055c.l);
    }
}
