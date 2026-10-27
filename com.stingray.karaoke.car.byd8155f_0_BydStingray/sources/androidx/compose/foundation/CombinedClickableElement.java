package androidx.compose.foundation;

import A7.m;
import C.k;
import E0.AbstractC0156f;
import E0.V;
import L0.g;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.C2682C;
import y0.C2722B;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/CombinedClickableElement;", "LE0/V;", "Ly/C;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class CombinedClickableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f11406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11407b;
    public final String c;
    public final g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2772a f11408e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f11409f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final InterfaceC2772a f11410g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final InterfaceC2772a f11411h;

    public CombinedClickableElement(k kVar, boolean z6, String str, g gVar, InterfaceC2772a interfaceC2772a, String str2, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3) {
        this.f11406a = kVar;
        this.f11407b = z6;
        this.c = str;
        this.d = gVar;
        this.f11408e = interfaceC2772a;
        this.f11409f = str2;
        this.f11410g = interfaceC2772a2;
        this.f11411h = interfaceC2772a3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CombinedClickableElement.class != obj.getClass()) {
            return false;
        }
        CombinedClickableElement combinedClickableElement = (CombinedClickableElement) obj;
        return m.a(this.f11406a, combinedClickableElement.f11406a) && m.a(null, null) && this.f11407b == combinedClickableElement.f11407b && m.a(this.c, combinedClickableElement.c) && m.a(this.d, combinedClickableElement.d) && this.f11408e == combinedClickableElement.f11408e && m.a(this.f11409f, combinedClickableElement.f11409f) && this.f11410g == combinedClickableElement.f11410g && this.f11411h == combinedClickableElement.f11411h;
    }

    public final int hashCode() {
        k kVar = this.f11406a;
        int iD = AbstractC1347d.d((kVar != null ? kVar.hashCode() : 0) * 961, 31, this.f11407b);
        String str = this.c;
        int iHashCode = (iD + (str != null ? str.hashCode() : 0)) * 31;
        g gVar = this.d;
        int iHashCode2 = (this.f11408e.hashCode() + ((iHashCode + (gVar != null ? Integer.hashCode(gVar.f3883a) : 0)) * 31)) * 31;
        String str2 = this.f11409f;
        int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        InterfaceC2772a interfaceC2772a = this.f11410g;
        int iHashCode4 = (iHashCode3 + (interfaceC2772a != null ? interfaceC2772a.hashCode() : 0)) * 31;
        InterfaceC2772a interfaceC2772a2 = this.f11411h;
        return iHashCode4 + (interfaceC2772a2 != null ? interfaceC2772a2.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        C2682C c2682c = new C2682C(this.f11406a, null, this.f11407b, this.c, this.d, this.f11408e);
        c2682c.f23828S = this.f11409f;
        c2682c.f23829T = this.f11410g;
        c2682c.f23830U = this.f11411h;
        return c2682c;
    }

    @Override // E0.V
    public final void l(n nVar) {
        boolean z6;
        C2722B c2722b;
        C2682C c2682c = (C2682C) nVar;
        String str = c2682c.f23828S;
        String str2 = this.f11409f;
        if (!m.a(str, str2)) {
            c2682c.f23828S = str2;
            AbstractC0156f.o(c2682c);
        }
        boolean z10 = c2682c.f23829T == null;
        InterfaceC2772a interfaceC2772a = this.f11410g;
        if (z10 != (interfaceC2772a == null)) {
            c2682c.M0();
            AbstractC0156f.o(c2682c);
            z6 = true;
        } else {
            z6 = false;
        }
        c2682c.f23829T = interfaceC2772a;
        boolean z11 = c2682c.f23830U == null;
        InterfaceC2772a interfaceC2772a2 = this.f11411h;
        if (z11 != (interfaceC2772a2 == null)) {
            z6 = true;
        }
        c2682c.f23830U = interfaceC2772a2;
        boolean z12 = c2682c.f23929E;
        boolean z13 = this.f11407b;
        boolean z14 = z12 != z13 ? true : z6;
        c2682c.O0(this.f11406a, null, z13, this.c, this.d, this.f11408e);
        if (!z14 || (c2722b = c2682c.f23933I) == null) {
            return;
        }
        c2722b.J0();
    }
}
