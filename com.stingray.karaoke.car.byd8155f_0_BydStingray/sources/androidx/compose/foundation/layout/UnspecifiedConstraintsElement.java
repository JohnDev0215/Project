package androidx.compose.foundation.layout;

import E0.V;
import a1.e;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/UnspecifiedConstraintsElement;", "LE0/V;", "LD/V;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class UnspecifiedConstraintsElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11470b;

    public UnspecifiedConstraintsElement(float f10, float f11) {
        this.f11469a = f10;
        this.f11470b = f11;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof UnspecifiedConstraintsElement)) {
            return false;
        }
        UnspecifiedConstraintsElement unspecifiedConstraintsElement = (UnspecifiedConstraintsElement) obj;
        return e.a(this.f11469a, unspecifiedConstraintsElement.f11469a) && e.a(this.f11470b, unspecifiedConstraintsElement.f11470b);
    }

    public final int hashCode() {
        return Float.hashCode(this.f11470b) + (Float.hashCode(this.f11469a) * 31);
    }

    @Override // E0.V
    public final n i() {
        D.V v10 = new D.V();
        v10.f1055y = this.f11469a;
        v10.f1056z = this.f11470b;
        return v10;
    }

    @Override // E0.V
    public final void l(n nVar) {
        D.V v10 = (D.V) nVar;
        v10.f1055y = this.f11469a;
        v10.f1056z = this.f11470b;
    }
}
