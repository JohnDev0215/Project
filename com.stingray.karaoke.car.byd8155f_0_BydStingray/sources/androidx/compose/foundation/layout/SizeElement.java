package androidx.compose.foundation.layout;

import D.U;
import E0.V;
import a1.e;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/SizeElement;", "LE0/V;", "LD/U;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class SizeElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11466a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11467b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f11468e;

    public SizeElement(float f10, float f11, float f12, float f13, boolean z6) {
        this.f11466a = f10;
        this.f11467b = f11;
        this.c = f12;
        this.d = f13;
        this.f11468e = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SizeElement)) {
            return false;
        }
        SizeElement sizeElement = (SizeElement) obj;
        return e.a(this.f11466a, sizeElement.f11466a) && e.a(this.f11467b, sizeElement.f11467b) && e.a(this.c, sizeElement.c) && e.a(this.d, sizeElement.d) && this.f11468e == sizeElement.f11468e;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f11468e) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f11466a) * 31, 31, this.f11467b), 31, this.c), 31, this.d);
    }

    @Override // E0.V
    public final n i() {
        U u4 = new U();
        u4.f1053y = this.f11466a;
        u4.f1054z = this.f11467b;
        u4.f1051A = this.c;
        u4.B = this.d;
        u4.f1052C = this.f11468e;
        return u4;
    }

    @Override // E0.V
    public final void l(n nVar) {
        U u4 = (U) nVar;
        u4.f1053y = this.f11466a;
        u4.f1054z = this.f11467b;
        u4.f1051A = this.c;
        u4.B = this.d;
        u4.f1052C = this.f11468e;
    }

    public /* synthetic */ SizeElement(float f10, float f11, float f12, float f13, boolean z6, int i7) {
        this((i7 & 1) != 0 ? Float.NaN : f10, (i7 & 2) != 0 ? Float.NaN : f11, (i7 & 4) != 0 ? Float.NaN : f12, (i7 & 8) != 0 ? Float.NaN : f13, z6);
    }
}
