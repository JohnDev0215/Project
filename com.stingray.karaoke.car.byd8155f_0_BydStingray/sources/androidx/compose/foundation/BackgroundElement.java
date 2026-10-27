package androidx.compose.foundation;

import A7.m;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import n0.AbstractC1887p;
import n0.C1891t;
import n0.InterfaceC1866P;
import y.C2711p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/BackgroundElement;", "LE0/V;", "Ly/p;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BackgroundElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AbstractC1887p f11399b;
    public final float c;
    public final InterfaceC1866P d;

    public BackgroundElement(long j3, AbstractC1887p abstractC1887p, float f10, InterfaceC1866P interfaceC1866P, int i7) {
        j3 = (i7 & 1) != 0 ? C1891t.f19452k : j3;
        abstractC1887p = (i7 & 2) != 0 ? null : abstractC1887p;
        this.f11398a = j3;
        this.f11399b = abstractC1887p;
        this.c = f10;
        this.d = interfaceC1866P;
    }

    public final boolean equals(Object obj) {
        BackgroundElement backgroundElement = obj instanceof BackgroundElement ? (BackgroundElement) obj : null;
        return backgroundElement != null && C1891t.c(this.f11398a, backgroundElement.f11398a) && m.a(this.f11399b, backgroundElement.f11399b) && this.c == backgroundElement.c && m.a(this.d, backgroundElement.d);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        int iHashCode = Long.hashCode(this.f11398a) * 31;
        AbstractC1887p abstractC1887p = this.f11399b;
        return this.d.hashCode() + AbstractC1347d.b((iHashCode + (abstractC1887p != null ? abstractC1887p.hashCode() : 0)) * 31, 31, this.c);
    }

    @Override // E0.V
    public final n i() {
        C2711p c2711p = new C2711p();
        c2711p.f23983y = this.f11398a;
        c2711p.f23984z = this.f11399b;
        c2711p.f23978A = this.c;
        c2711p.B = this.d;
        c2711p.f23979C = 9205357640488583168L;
        return c2711p;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C2711p c2711p = (C2711p) nVar;
        c2711p.f23983y = this.f11398a;
        c2711p.f23984z = this.f11399b;
        c2711p.f23978A = this.c;
        c2711p.B = this.d;
    }
}
