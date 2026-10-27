package x6;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f23821a;

    public f(float f10) {
        this.f23821a = f10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        fVar.getClass();
        return Float.compare(0.3f, 0.3f) == 0 && Float.compare(0.6f, 0.6f) == 0 && Float.compare(this.f23821a, fVar.f23821a) == 0 && Float.compare(0.03f, 0.03f) == 0 && Float.compare(0.7f, 0.7f) == 0 && Float.compare(0.1f, 0.1f) == 0 && Float.compare(0.6f, 0.6f) == 0 && Float.compare(0.6f, 0.6f) == 0 && Float.compare(0.4f, 0.4f) == 0 && Float.compare(20.0f, 20.0f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(20.0f) + AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(0.3f) * 31, 31, 0.6f), 31, this.f23821a), 31, 0.03f), 31, 0.7f), 31, 0.1f), 31, 0.6f), 31, 0.6f), 31, 0.4f);
    }

    public final String toString() {
        return "LyricsUiConfig(singerBackgroundAlphaMin=0.3, singerBackgroundAlphaMax=0.6, singerPaintTextSize=" + this.f23821a + ", singerPaintLetterSpacing=0.03, cueBarHeightRatio=0.7, cueBarWidthRatio=0.1, breakBarWidthRatioSolo=0.6, breakBarWidthRatioDuet=0.6, breakBarBackgroundAlpha=0.4, duetBackgroundCornerRadius=20.0)";
    }
}
