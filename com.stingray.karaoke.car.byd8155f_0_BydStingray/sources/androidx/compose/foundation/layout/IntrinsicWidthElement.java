package androidx.compose.foundation.layout;

import D.H;
import E0.V;
import g0.n;
import kotlin.Metadata;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/IntrinsicWidthElement;", "LE0/V;", "LD/H;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class IntrinsicWidthElement extends V {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof IntrinsicWidthElement ? (IntrinsicWidthElement) obj : null) != null;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (AbstractC2641j.c(2) * 31);
    }

    @Override // E0.V
    public final n i() {
        H h10 = new H();
        h10.f1027y = 2;
        h10.f1028z = true;
        return h10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        H h10 = (H) nVar;
        h10.f1027y = 2;
        h10.f1028z = true;
    }
}
