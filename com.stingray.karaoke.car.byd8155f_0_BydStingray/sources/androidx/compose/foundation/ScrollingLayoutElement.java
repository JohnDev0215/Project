package androidx.compose.foundation;

import A7.m;
import E0.V;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import y.y0;
import y.z0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/ScrollingLayoutElement;", "LE0/V;", "Ly/z0;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScrollingLayoutElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y0 f11427a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11428b;
    public final boolean c;

    public ScrollingLayoutElement(y0 y0Var, boolean z6, boolean z10) {
        this.f11427a = y0Var;
        this.f11428b = z6;
        this.c = z10;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ScrollingLayoutElement)) {
            return false;
        }
        ScrollingLayoutElement scrollingLayoutElement = (ScrollingLayoutElement) obj;
        return m.a(this.f11427a, scrollingLayoutElement.f11427a) && this.f11428b == scrollingLayoutElement.f11428b && this.c == scrollingLayoutElement.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.c) + AbstractC1347d.d(this.f11427a.hashCode() * 31, 31, this.f11428b);
    }

    @Override // E0.V
    public final n i() {
        z0 z0Var = new z0();
        z0Var.f24037y = this.f11427a;
        z0Var.f24038z = this.f11428b;
        z0Var.f24036A = this.c;
        return z0Var;
    }

    @Override // E0.V
    public final void l(n nVar) {
        z0 z0Var = (z0) nVar;
        z0Var.f24037y = this.f11427a;
        z0Var.f24038z = this.f11428b;
        z0Var.f24036A = this.c;
    }
}
