package androidx.compose.ui.graphics;

import A7.m;
import E0.AbstractC0156f;
import E0.V;
import E0.d0;
import g0.n;
import kotlin.Metadata;
import n0.C1885n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/BlockGraphicsLayerElement;", "LE0/V;", "Ln0/n;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class BlockGraphicsLayerElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f11574a;

    public BlockGraphicsLayerElement(InterfaceC2782k interfaceC2782k) {
        this.f11574a = interfaceC2782k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BlockGraphicsLayerElement) && m.a(this.f11574a, ((BlockGraphicsLayerElement) obj).f11574a);
    }

    public final int hashCode() {
        return this.f11574a.hashCode();
    }

    @Override // E0.V
    public final n i() {
        return new C1885n(this.f11574a);
    }

    @Override // E0.V
    public final void l(n nVar) {
        C1885n c1885n = (C1885n) nVar;
        c1885n.f19442y = this.f11574a;
        d0 d0Var = AbstractC0156f.r(c1885n, 2).f1649x;
        if (d0Var != null) {
            d0Var.l1(c1885n.f19442y, true);
        }
    }

    public final String toString() {
        return "BlockGraphicsLayerElement(block=" + this.f11574a + ')';
    }
}
