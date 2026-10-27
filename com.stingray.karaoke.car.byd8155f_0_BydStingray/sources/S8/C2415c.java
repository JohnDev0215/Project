package s8;

import A7.m;
import F8.AbstractC0287w;
import F8.O;
import G8.i;
import Q7.InterfaceC0654g;
import h8.AbstractC1387d;
import java.util.Collection;
import java.util.List;
import n7.u;

/* JADX INFO: renamed from: s8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2415c implements InterfaceC2414b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final O f22377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f22378b;

    public C2415c(O o) {
        m.f(o, "projection");
        this.f22377a = o;
        o.a();
    }

    @Override // s8.InterfaceC2414b
    public final O a() {
        return this.f22377a;
    }

    @Override // F8.K
    public final N7.i l() {
        N7.i iVarL = this.f22377a.b().B0().l();
        m.e(iVarL, "projection.type.constructor.builtIns");
        return iVarL;
    }

    @Override // F8.K
    public final boolean m() {
        return false;
    }

    @Override // F8.K
    public final /* bridge */ /* synthetic */ InterfaceC0654g n() {
        return null;
    }

    @Override // F8.K
    public final Collection o() {
        O o = this.f22377a;
        AbstractC0287w abstractC0287wB = o.a() == 3 ? o.b() : l().n();
        m.e(abstractC0287wB, "if (projection.projectio… builtIns.nullableAnyType");
        return AbstractC1387d.j(abstractC0287wB);
    }

    @Override // F8.K
    public final List p() {
        return u.l;
    }

    public final String toString() {
        return "CapturedTypeConstructor(" + this.f22377a + ')';
    }
}
