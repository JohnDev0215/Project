package androidx.compose.foundation.layout;

import D.J;
import E0.V;
import a1.e;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/OffsetElement;", "LE0/V;", "LD/J;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class OffsetElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11461a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11462b;

    public OffsetElement(float f10, float f11) {
        this.f11461a = f10;
        this.f11462b = f11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        OffsetElement offsetElement = obj instanceof OffsetElement ? (OffsetElement) obj : null;
        if (offsetElement == null) {
            return false;
        }
        return e.a(this.f11461a, offsetElement.f11461a) && e.a(this.f11462b, offsetElement.f11462b);
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + AbstractC1347d.b(Float.hashCode(this.f11461a) * 31, 31, this.f11462b);
    }

    @Override // E0.V
    public final n i() {
        J j3 = new J();
        j3.f1032y = this.f11461a;
        j3.f1033z = this.f11462b;
        j3.f1031A = true;
        return j3;
    }

    @Override // E0.V
    public final void l(n nVar) {
        J j3 = (J) nVar;
        j3.f1032y = this.f11461a;
        j3.f1033z = this.f11462b;
        j3.f1031A = true;
    }

    public final String toString() {
        return "OffsetModifierElement(x=" + ((Object) e.b(this.f11461a)) + ", y=" + ((Object) e.b(this.f11462b)) + ", rtlAware=true)";
    }
}
