package n0;

import android.graphics.BlendModeColorFilter;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: n0.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1883l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BlendModeColorFilter f19439a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f19440b;
    public final int c;

    public C1883l(long j3, int i7, BlendModeColorFilter blendModeColorFilter) {
        this.f19439a = blendModeColorFilter;
        this.f19440b = j3;
        this.c = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1883l)) {
            return false;
        }
        C1883l c1883l = (C1883l) obj;
        return C1891t.c(this.f19440b, c1883l.f19440b) && AbstractC1861K.p(this.c, c1883l.c);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Integer.hashCode(this.c) + (Long.hashCode(this.f19440b) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BlendModeColorFilter(color=");
        AbstractC1347d.n(this.f19440b, ", blendMode=", sb);
        int i7 = this.c;
        sb.append((Object) (AbstractC1861K.p(i7, 0) ? "Clear" : AbstractC1861K.p(i7, 1) ? "Src" : AbstractC1861K.p(i7, 2) ? "Dst" : AbstractC1861K.p(i7, 3) ? "SrcOver" : AbstractC1861K.p(i7, 4) ? "DstOver" : AbstractC1861K.p(i7, 5) ? "SrcIn" : AbstractC1861K.p(i7, 6) ? "DstIn" : AbstractC1861K.p(i7, 7) ? "SrcOut" : AbstractC1861K.p(i7, 8) ? "DstOut" : AbstractC1861K.p(i7, 9) ? "SrcAtop" : AbstractC1861K.p(i7, 10) ? "DstAtop" : AbstractC1861K.p(i7, 11) ? "Xor" : AbstractC1861K.p(i7, 12) ? "Plus" : AbstractC1861K.p(i7, 13) ? "Modulate" : AbstractC1861K.p(i7, 14) ? "Screen" : AbstractC1861K.p(i7, 15) ? "Overlay" : AbstractC1861K.p(i7, 16) ? "Darken" : AbstractC1861K.p(i7, 17) ? "Lighten" : AbstractC1861K.p(i7, 18) ? "ColorDodge" : AbstractC1861K.p(i7, 19) ? "ColorBurn" : AbstractC1861K.p(i7, 20) ? "HardLight" : AbstractC1861K.p(i7, 21) ? "Softlight" : AbstractC1861K.p(i7, 22) ? "Difference" : AbstractC1861K.p(i7, 23) ? "Exclusion" : AbstractC1861K.p(i7, 24) ? "Multiply" : AbstractC1861K.p(i7, 25) ? "Hue" : AbstractC1861K.p(i7, 26) ? "Saturation" : AbstractC1861K.p(i7, 27) ? "Color" : AbstractC1861K.p(i7, 28) ? "Luminosity" : "Unknown"));
        sb.append(')');
        return sb.toString();
    }
}
