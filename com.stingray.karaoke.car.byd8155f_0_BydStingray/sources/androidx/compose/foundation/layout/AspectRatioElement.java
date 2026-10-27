package androidx.compose.foundation.layout;

import D.C0128j;
import E0.V;
import g0.n;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/AspectRatioElement;", "LE0/V;", "LD/j;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AspectRatioElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11451a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f11452b;

    public AspectRatioElement(float f10, boolean z6) {
        this.f11451a = f10;
        this.f11452b = z6;
        if (f10 > 0.0f) {
            return;
        }
        throw new IllegalArgumentException(("aspectRatio " + f10 + " must be > 0").toString());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        AspectRatioElement aspectRatioElement = obj instanceof AspectRatioElement ? (AspectRatioElement) obj : null;
        if (aspectRatioElement == null) {
            return false;
        }
        if (this.f11451a == aspectRatioElement.f11451a) {
            if (this.f11452b == ((AspectRatioElement) obj).f11452b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11452b) + (Float.hashCode(this.f11451a) * 31);
    }

    @Override // E0.V
    public final n i() {
        C0128j c0128j = new C0128j();
        c0128j.f1080y = this.f11451a;
        c0128j.f1081z = this.f11452b;
        return c0128j;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C0128j c0128j = (C0128j) nVar;
        c0128j.f1080y = this.f11451a;
        c0128j.f1081z = this.f11452b;
    }
}
