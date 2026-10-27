package G6;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2690a = 0.35f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f2691b = 0.85f;
    public final float c = 0.4f;
    public final float d = 0.25f;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return Float.compare(this.f2690a, bVar.f2690a) == 0 && Float.compare(this.f2691b, bVar.f2691b) == 0 && Float.compare(this.c, bVar.c) == 0 && Float.compare(this.d, bVar.d) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f2690a) * 31, 31, this.f2691b), 31, this.c);
    }

    public final String toString() {
        return "PaletteBackgroundConfig(circleOffset=" + this.f2690a + ", circleSizeMultiplier=" + this.f2691b + ", circleAlpha=" + this.c + ", backAlpha=" + this.d + ")";
    }
}
