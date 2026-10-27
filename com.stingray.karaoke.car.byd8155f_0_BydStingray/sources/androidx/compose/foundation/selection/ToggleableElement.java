package androidx.compose.foundation.selection;

import A7.m;
import C.k;
import E0.AbstractC0156f;
import E0.V;
import I.c;
import L0.g;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import x.AbstractC2641j;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/selection/ToggleableElement;", "LE0/V;", "LI/c;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ToggleableElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f11501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f11502b;
    public final boolean c;
    public final g d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2782k f11503e;

    public ToggleableElement(boolean z6, k kVar, boolean z10, g gVar, InterfaceC2782k interfaceC2782k) {
        this.f11501a = z6;
        this.f11502b = kVar;
        this.c = z10;
        this.d = gVar;
        this.f11503e = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ToggleableElement.class != obj.getClass()) {
            return false;
        }
        ToggleableElement toggleableElement = (ToggleableElement) obj;
        return this.f11501a == toggleableElement.f11501a && m.a(this.f11502b, toggleableElement.f11502b) && m.a(null, null) && this.c == toggleableElement.c && this.d.equals(toggleableElement.d) && this.f11503e == toggleableElement.f11503e;
    }

    public final int hashCode() {
        int iHashCode = Boolean.hashCode(this.f11501a) * 31;
        k kVar = this.f11502b;
        return this.f11503e.hashCode() + AbstractC2641j.b(this.d.f3883a, AbstractC1347d.d((iHashCode + (kVar != null ? kVar.hashCode() : 0)) * 961, 31, this.c), 31);
    }

    @Override // E0.V
    public final n i() {
        g gVar = this.d;
        return new c(this.f11501a, this.f11502b, this.c, gVar, this.f11503e);
    }

    @Override // E0.V
    public final void l(n nVar) {
        c cVar = (c) nVar;
        boolean z6 = cVar.f2902S;
        boolean z10 = this.f11501a;
        if (z6 != z10) {
            cVar.f2902S = z10;
            AbstractC0156f.o(cVar);
        }
        cVar.f2903T = this.f11503e;
        cVar.O0(this.f11502b, null, this.c, null, this.d, cVar.f2904U);
    }
}
