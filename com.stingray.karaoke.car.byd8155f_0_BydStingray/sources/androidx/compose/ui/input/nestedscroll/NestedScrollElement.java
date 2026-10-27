package androidx.compose.ui.input.nestedscroll;

import A7.m;
import E0.V;
import e0.C1183w;
import g0.n;
import kotlin.Metadata;
import x0.InterfaceC2660a;
import x0.d;
import x0.g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollElement;", "LE0/V;", "Lx0/g;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class NestedScrollElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2660a f11606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f11607b;

    public NestedScrollElement(InterfaceC2660a interfaceC2660a, d dVar) {
        this.f11606a = interfaceC2660a;
        this.f11607b = dVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof NestedScrollElement)) {
            return false;
        }
        NestedScrollElement nestedScrollElement = (NestedScrollElement) obj;
        return m.a(nestedScrollElement.f11606a, this.f11606a) && m.a(nestedScrollElement.f11607b, this.f11607b);
    }

    public final int hashCode() {
        int iHashCode = this.f11606a.hashCode() * 31;
        d dVar = this.f11607b;
        return iHashCode + (dVar != null ? dVar.hashCode() : 0);
    }

    @Override // E0.V
    public final n i() {
        return new g(this.f11606a, this.f11607b);
    }

    @Override // E0.V
    public final void l(n nVar) {
        g gVar = (g) nVar;
        gVar.f23688y = this.f11606a;
        d dVar = gVar.f23689z;
        if (dVar.f23678a == gVar) {
            dVar.f23678a = null;
        }
        d dVar2 = this.f11607b;
        if (dVar2 == null) {
            gVar.f23689z = new d();
        } else if (!dVar2.equals(dVar)) {
            gVar.f23689z = dVar2;
        }
        if (gVar.f14695x) {
            d dVar3 = gVar.f23689z;
            dVar3.f23678a = gVar;
            dVar3.f23679b = new C1183w(gVar, 15);
            dVar3.c = gVar.v0();
        }
    }
}
