package androidx.compose.foundation.layout;

import A7.m;
import D.C0129k;
import E0.V;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/BoxChildDataElement;", "LE0/V;", "LD/k;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class BoxChildDataElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g0.c f11453a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11454b;

    public BoxChildDataElement(g0.c cVar, boolean z6) {
        this.f11453a = cVar;
        this.f11454b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        BoxChildDataElement boxChildDataElement = obj instanceof BoxChildDataElement ? (BoxChildDataElement) obj : null;
        if (boxChildDataElement == null) {
            return false;
        }
        return m.a(this.f11453a, boxChildDataElement.f11453a) && this.f11454b == boxChildDataElement.f11454b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11454b) + (this.f11453a.hashCode() * 31);
    }

    @Override // E0.V
    public final n i() {
        C0129k c0129k = new C0129k();
        c0129k.f1082y = this.f11453a;
        c0129k.f1083z = this.f11454b;
        return c0129k;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C0129k c0129k = (C0129k) nVar;
        c0129k.f1082y = this.f11453a;
        c0129k.f1083z = this.f11454b;
    }
}
