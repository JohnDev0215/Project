package androidx.compose.foundation.gestures;

import A.C0014g;
import A.C0030o;
import A.EnumC0017h0;
import A.InterfaceC0012f;
import A.J0;
import A.K0;
import A.S0;
import A.r;
import A7.m;
import C.k;
import E0.AbstractC0156f;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.r0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollableElement;", "LE0/V;", "LA/J0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ScrollableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K0 f11443a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final EnumC0017h0 f11444b;
    public final r0 c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11445e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r f11446f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k f11447g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final InterfaceC0012f f11448h;

    public ScrollableElement(InterfaceC0012f interfaceC0012f, r rVar, EnumC0017h0 enumC0017h0, K0 k02, k kVar, r0 r0Var, boolean z6, boolean z10) {
        this.f11443a = k02;
        this.f11444b = enumC0017h0;
        this.c = r0Var;
        this.d = z6;
        this.f11445e = z10;
        this.f11446f = rVar;
        this.f11447g = kVar;
        this.f11448h = interfaceC0012f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrollableElement)) {
            return false;
        }
        ScrollableElement scrollableElement = (ScrollableElement) obj;
        return m.a(this.f11443a, scrollableElement.f11443a) && this.f11444b == scrollableElement.f11444b && m.a(this.c, scrollableElement.c) && this.d == scrollableElement.d && this.f11445e == scrollableElement.f11445e && m.a(this.f11446f, scrollableElement.f11446f) && m.a(this.f11447g, scrollableElement.f11447g) && m.a(this.f11448h, scrollableElement.f11448h);
    }

    public final int hashCode() {
        int iHashCode = (this.f11444b.hashCode() + (this.f11443a.hashCode() * 31)) * 31;
        r0 r0Var = this.c;
        int iD = AbstractC1347d.d(AbstractC1347d.d((iHashCode + (r0Var != null ? r0Var.hashCode() : 0)) * 31, 31, this.d), 31, this.f11445e);
        r rVar = this.f11446f;
        int iHashCode2 = (iD + (rVar != null ? rVar.hashCode() : 0)) * 31;
        k kVar = this.f11447g;
        int iHashCode3 = (iHashCode2 + (kVar != null ? kVar.hashCode() : 0)) * 31;
        InterfaceC0012f interfaceC0012f = this.f11448h;
        return iHashCode3 + (interfaceC0012f != null ? interfaceC0012f.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        k kVar = this.f11447g;
        return new J0(this.f11448h, this.f11446f, this.f11444b, this.f11443a, kVar, this.c, this.d, this.f11445e);
    }

    @Override // E0.V
    public final void l(n nVar) {
        boolean z6;
        boolean z10;
        J0 j02 = (J0) nVar;
        boolean z11 = j02.f121C;
        boolean z12 = this.d;
        boolean z13 = false;
        if (z11 != z12) {
            j02.f54O.f300m = z12;
            j02.f51L.f258y = z12;
            z6 = true;
        } else {
            z6 = false;
        }
        r rVar = this.f11446f;
        r rVar2 = rVar == null ? j02.f52M : rVar;
        S0 s02 = j02.f53N;
        K0 k02 = s02.f100a;
        K0 k03 = this.f11443a;
        if (!m.a(k02, k03)) {
            s02.f100a = k03;
            z13 = true;
        }
        r0 r0Var = this.c;
        s02.f101b = r0Var;
        EnumC0017h0 enumC0017h0 = s02.d;
        EnumC0017h0 enumC0017h02 = this.f11444b;
        if (enumC0017h0 != enumC0017h02) {
            s02.d = enumC0017h02;
            z13 = true;
        }
        boolean z14 = s02.f102e;
        boolean z15 = this.f11445e;
        if (z14 != z15) {
            s02.f102e = z15;
            z10 = true;
        } else {
            z10 = z13;
        }
        s02.c = rVar2;
        s02.f103f = j02.f50K;
        C0030o c0030o = j02.f55P;
        c0030o.f229y = enumC0017h02;
        c0030o.f222A = z15;
        c0030o.B = this.f11448h;
        j02.f48I = r0Var;
        j02.f49J = rVar;
        C0014g c0014g = C0014g.f175p;
        EnumC0017h0 enumC0017h03 = s02.d;
        EnumC0017h0 enumC0017h04 = EnumC0017h0.l;
        j02.S0(c0014g, z12, this.f11447g, enumC0017h03 == enumC0017h04 ? enumC0017h04 : EnumC0017h0.f181m, z10);
        if (z6) {
            j02.f57R = null;
            j02.f58S = null;
            AbstractC0156f.o(j02);
        }
    }
}
