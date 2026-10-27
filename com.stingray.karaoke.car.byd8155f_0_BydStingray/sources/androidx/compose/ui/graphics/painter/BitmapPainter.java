package androidx.compose.ui.graphics.painter;

import A7.m;
import H7.H;
import a1.h;
import a1.j;
import h2.AbstractC1347d;
import kotlin.Metadata;
import m0.f;
import n0.AbstractC1861K;
import n0.C1877f;
import n0.C1883l;
import p0.C2071b;
import p0.d;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/compose/ui/graphics/painter/BitmapPainter;", "Lr0/a;", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BitmapPainter extends AbstractC2268a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C1877f f11587q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final long f11588r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final long f11589s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f11590t = 1;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final long f11591u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f11592v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public C1883l f11593w;

    public BitmapPainter(C1877f c1877f, long j3, long j10) {
        int i7;
        int i10;
        this.f11587q = c1877f;
        this.f11588r = j3;
        this.f11589s = j10;
        if (((int) (j3 >> 32)) < 0 || ((int) (j3 & 4294967295L)) < 0 || (i7 = (int) (j10 >> 32)) < 0 || (i10 = (int) (j10 & 4294967295L)) < 0 || i7 > c1877f.f19431a.getWidth() || i10 > c1877f.f19431a.getHeight()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.f11591u = j10;
        this.f11592v = 1.0f;
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f11592v = f10;
        return true;
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f11593w = c1883l;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BitmapPainter)) {
            return false;
        }
        BitmapPainter bitmapPainter = (BitmapPainter) obj;
        return m.a(this.f11587q, bitmapPainter.f11587q) && h.b(this.f11588r, bitmapPainter.f11588r) && j.a(this.f11589s, bitmapPainter.f11589s) && AbstractC1861K.r(this.f11590t, bitmapPainter.f11590t);
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        return H.S(this.f11591u);
    }

    public final int hashCode() {
        return Integer.hashCode(this.f11590t) + AbstractC1347d.c(AbstractC1347d.c(this.f11587q.hashCode() * 31, 31, this.f11588r), 31, this.f11589s);
    }

    @Override // r0.AbstractC2268a
    public final void i(E0.H h10) {
        C2071b c2071b = h10.l;
        long jC = H.c(Math.round(f.d(c2071b.e())), Math.round(f.b(c2071b.e())));
        float f10 = this.f11592v;
        C1883l c1883l = this.f11593w;
        int i7 = this.f11590t;
        d.Z(h10, this.f11587q, this.f11588r, this.f11589s, jC, f10, c1883l, i7, 328);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BitmapPainter(image=");
        sb.append(this.f11587q);
        sb.append(", srcOffset=");
        sb.append((Object) h.e(this.f11588r));
        sb.append(", srcSize=");
        sb.append((Object) j.b(this.f11589s));
        sb.append(", filterQuality=");
        int i7 = this.f11590t;
        sb.append((Object) (AbstractC1861K.r(i7, 0) ? "None" : AbstractC1861K.r(i7, 1) ? "Low" : AbstractC1861K.r(i7, 2) ? "Medium" : AbstractC1861K.r(i7, 3) ? "High" : "Unknown"));
        sb.append(')');
        return sb.toString();
    }
}
