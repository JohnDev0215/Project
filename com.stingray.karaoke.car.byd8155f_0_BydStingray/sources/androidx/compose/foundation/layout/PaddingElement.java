package androidx.compose.foundation.layout;

import D.K;
import E0.V;
import a1.e;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/PaddingElement;", "LE0/V;", "LD/K;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class PaddingElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11463a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11464b;
    public final float c;
    public final float d;

    public PaddingElement(float f10, float f11, float f12, float f13) {
        this.f11463a = f10;
        this.f11464b = f11;
        this.c = f12;
        this.d = f13;
        if ((f10 < 0.0f && !e.a(f10, Float.NaN)) || ((f11 < 0.0f && !e.a(f11, Float.NaN)) || ((f12 < 0.0f && !e.a(f12, Float.NaN)) || (f13 < 0.0f && !e.a(f13, Float.NaN))))) {
            throw new IllegalArgumentException("Padding must be non-negative");
        }
    }

    public final boolean equals(Object obj) {
        PaddingElement paddingElement = obj instanceof PaddingElement ? (PaddingElement) obj : null;
        return paddingElement != null && e.a(this.f11463a, paddingElement.f11463a) && e.a(this.f11464b, paddingElement.f11464b) && e.a(this.c, paddingElement.c) && e.a(this.d, paddingElement.d);
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f11463a) * 31, 31, this.f11464b), 31, this.c), 31, this.d);
    }

    @Override // E0.V
    public final n i() {
        K k10 = new K();
        k10.f1036y = this.f11463a;
        k10.f1037z = this.f11464b;
        k10.f1034A = this.c;
        k10.B = this.d;
        k10.f1035C = true;
        return k10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        K k10 = (K) nVar;
        k10.f1036y = this.f11463a;
        k10.f1037z = this.f11464b;
        k10.f1034A = this.c;
        k10.B = this.d;
        k10.f1035C = true;
    }
}
