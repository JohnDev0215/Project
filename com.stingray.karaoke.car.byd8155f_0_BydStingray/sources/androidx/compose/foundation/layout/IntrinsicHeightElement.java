package androidx.compose.foundation.layout;

import D.F;
import E0.V;
import g0.n;
import kotlin.Metadata;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/IntrinsicHeightElement;", "LE0/V;", "LD/F;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class IntrinsicHeightElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f11458a;

    public IntrinsicHeightElement(int i7) {
        this.f11458a = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        IntrinsicHeightElement intrinsicHeightElement = obj instanceof IntrinsicHeightElement ? (IntrinsicHeightElement) obj : null;
        return intrinsicHeightElement != null && this.f11458a == intrinsicHeightElement.f11458a;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (AbstractC2641j.c(this.f11458a) * 31);
    }

    @Override // E0.V
    public final n i() {
        F f10 = new F();
        f10.f1025y = this.f11458a;
        f10.f1026z = true;
        return f10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        F f10 = (F) nVar;
        f10.f1025y = this.f11458a;
        f10.f1026z = true;
    }
}
