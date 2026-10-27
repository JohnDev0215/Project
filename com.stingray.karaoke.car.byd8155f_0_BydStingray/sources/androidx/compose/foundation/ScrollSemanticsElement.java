package androidx.compose.foundation;

import A.r;
import A7.m;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.v0;
import y.y0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/ScrollSemanticsElement;", "LE0/V;", "Ly/v0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class ScrollSemanticsElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f11424a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11425b;
    public final r c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11426e;

    public ScrollSemanticsElement(y0 y0Var, boolean z6, r rVar, boolean z10, boolean z11) {
        this.f11424a = y0Var;
        this.f11425b = z6;
        this.c = rVar;
        this.d = z10;
        this.f11426e = z11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrollSemanticsElement)) {
            return false;
        }
        ScrollSemanticsElement scrollSemanticsElement = (ScrollSemanticsElement) obj;
        return m.a(this.f11424a, scrollSemanticsElement.f11424a) && this.f11425b == scrollSemanticsElement.f11425b && m.a(this.c, scrollSemanticsElement.c) && this.d == scrollSemanticsElement.d && this.f11426e == scrollSemanticsElement.f11426e;
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d(this.f11424a.hashCode() * 31, 31, this.f11425b);
        r rVar = this.c;
        return Boolean.hashCode(this.f11426e) + AbstractC1347d.d((iD + (rVar == null ? 0 : rVar.hashCode())) * 31, 31, this.d);
    }

    @Override // E0.V
    public final n i() {
        v0 v0Var = new v0();
        v0Var.f24016y = this.f11424a;
        v0Var.f24017z = this.f11425b;
        v0Var.f24015A = this.f11426e;
        return v0Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        v0 v0Var = (v0) nVar;
        v0Var.f24016y = this.f11424a;
        v0Var.f24017z = this.f11425b;
        v0Var.f24015A = this.f11426e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ScrollSemanticsElement(state=");
        sb.append(this.f11424a);
        sb.append(", reverseScrolling=");
        sb.append(this.f11425b);
        sb.append(", flingBehavior=");
        sb.append(this.c);
        sb.append(", isScrollable=");
        sb.append(this.d);
        sb.append(", isVertical=");
        return AbstractC1347d.i(sb, this.f11426e, ')');
    }
}
