package androidx.compose.foundation.layout;

import D.E;
import E0.V;
import g0.e;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/HorizontalAlignElement;", "LE0/V;", "LD/E;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class HorizontalAlignElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f11457a;

    public HorizontalAlignElement(e eVar) {
        this.f11457a = eVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        HorizontalAlignElement horizontalAlignElement = obj instanceof HorizontalAlignElement ? (HorizontalAlignElement) obj : null;
        if (horizontalAlignElement == null) {
            return false;
        }
        return this.f11457a.equals(horizontalAlignElement.f11457a);
    }

    public final int hashCode() {
        return Float.hashCode(this.f11457a.f14676a);
    }

    @Override // E0.V
    public final n i() {
        E e10 = new E();
        e10.f1024y = this.f11457a;
        return e10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        ((E) nVar).f1024y = this.f11457a;
    }
}
