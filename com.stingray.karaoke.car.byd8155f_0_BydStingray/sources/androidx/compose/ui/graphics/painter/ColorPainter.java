package androidx.compose.ui.graphics.painter;

import E0.H;
import kotlin.Metadata;
import n0.C1883l;
import n0.C1891t;
import p0.d;
import p0.g;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/compose/ui/graphics/painter/ColorPainter;", "Lr0/a;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ColorPainter extends AbstractC2268a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final long f11594q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f11595r = 1.0f;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C1883l f11596s;

    public ColorPainter(long j3) {
        this.f11594q = j3;
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f11595r = f10;
        return true;
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f11596s = c1883l;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ColorPainter) {
            return C1891t.c(this.f11594q, ((ColorPainter) obj).f11594q);
        }
        return false;
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        return 9205357640488583168L;
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f11594q);
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) {
        h10.c0(this.f11594q, 0L, (58 & 4) != 0 ? d.s0(h10.e(), 0L) : 0L, (58 & 8) != 0 ? 1.0f : this.f11595r, g.f20641a, (58 & 32) != 0 ? null : this.f11596s, (58 & 64) != 0 ? 3 : 0);
    }

    public final String toString() {
        return "ColorPainter(color=" + ((Object) C1891t.i(this.f11594q)) + ')';
    }
}
