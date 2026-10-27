package androidx.compose.foundation.layout;

import D.W;
import E0.V;
import g0.f;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/VerticalAlignElement;", "LE0/V;", "LD/W;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VerticalAlignElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f11471a;

    public VerticalAlignElement(f fVar) {
        this.f11471a = fVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        VerticalAlignElement verticalAlignElement = obj instanceof VerticalAlignElement ? (VerticalAlignElement) obj : null;
        if (verticalAlignElement == null) {
            return false;
        }
        return this.f11471a.equals(verticalAlignElement.f11471a);
    }

    public final int hashCode() {
        return Float.hashCode(this.f11471a.f14677a);
    }

    @Override // E0.V
    public final n i() {
        W w10 = new W();
        w10.f1057y = this.f11471a;
        return w10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((W) nVar).f1057y = this.f11471a;
    }
}
