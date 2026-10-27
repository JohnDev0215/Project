package G8;

import A.I0;
import F8.AbstractC0287w;
import F8.O;
import Q7.InterfaceC0654g;
import Q7.Q;
import g1.AbstractC1274a;
import java.util.Collection;
import java.util.List;
import n7.u;
import s8.InterfaceC2414b;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InterfaceC2414b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O f2731a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC2772a f2732b;
    public final i c;
    public final Q d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f2733e;

    public /* synthetic */ i(O o, D8.d dVar, i iVar, Q q4, int i7) {
        this(o, (i7 & 2) != 0 ? null : dVar, (i7 & 4) != 0 ? null : iVar, (i7 & 8) != 0 ? null : q4);
    }

    @Override // s8.InterfaceC2414b
    public final O a() {
        return this.f2731a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!i.class.equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        A7.m.d(obj, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedTypeConstructor");
        i iVar = (i) obj;
        i iVar2 = this.c;
        if (iVar2 != null) {
            this = iVar2;
        }
        i iVar3 = iVar.c;
        if (iVar3 != null) {
            iVar = iVar3;
        }
        return this == iVar;
    }

    public final int hashCode() {
        i iVar = this.c;
        return iVar != null ? iVar.hashCode() : super.hashCode();
    }

    @Override // F8.K
    public final N7.i l() {
        AbstractC0287w abstractC0287wB = this.f2731a.b();
        A7.m.e(abstractC0287wB, "projection.type");
        return AbstractC1274a.L(abstractC0287wB);
    }

    @Override // F8.K
    public final boolean m() {
        return false;
    }

    @Override // F8.K
    public final InterfaceC0654g n() {
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, m7.f] */
    @Override // F8.K
    public final Collection o() {
        List list = (List) this.f2733e.getValue();
        return list == null ? u.l : list;
    }

    @Override // F8.K
    public final List p() {
        return u.l;
    }

    public final String toString() {
        return "CapturedType(" + this.f2731a + ')';
    }

    public i(O o, InterfaceC2772a interfaceC2772a, i iVar, Q q4) {
        A7.m.f(o, "projection");
        this.f2731a = o;
        this.f2732b = interfaceC2772a;
        this.c = iVar;
        this.d = q4;
        this.f2733e = h6.r.n(m7.g.l, new I0(this, 20));
    }
}
