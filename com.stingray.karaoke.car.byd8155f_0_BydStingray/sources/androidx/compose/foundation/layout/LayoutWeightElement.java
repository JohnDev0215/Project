package androidx.compose.foundation.layout;

import D.I;
import E0.V;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/LayoutWeightElement;", "LE0/V;", "LD/I;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LayoutWeightElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11460b;

    public LayoutWeightElement(float f10, boolean z6) {
        this.f11459a = f10;
        this.f11460b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        LayoutWeightElement layoutWeightElement = obj instanceof LayoutWeightElement ? (LayoutWeightElement) obj : null;
        if (layoutWeightElement == null) {
            return false;
        }
        return this.f11459a == layoutWeightElement.f11459a && this.f11460b == layoutWeightElement.f11460b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11460b) + (Float.hashCode(this.f11459a) * 31);
    }

    @Override // E0.V
    public final n i() {
        I i7 = new I();
        i7.f1029y = this.f11459a;
        i7.f1030z = this.f11460b;
        return i7;
    }

    @Override // E0.V
    public final void l(n nVar) {
        I i7 = (I) nVar;
        i7.f1029y = this.f11459a;
        i7.f1030z = this.f11460b;
    }
}
