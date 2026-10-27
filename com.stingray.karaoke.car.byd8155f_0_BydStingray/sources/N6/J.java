package n6;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class J {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f20027a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f20028b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f20029e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f20030f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f20031g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f20032h;

    public J(float f10, float f11, float f12, float f13, float f14, long j3, int i7, int i10) {
        this.f20027a = f10;
        this.f20028b = f11;
        this.c = f12;
        this.d = f13;
        this.f20029e = f14;
        this.f20030f = j3;
        this.f20031g = i7;
        this.f20032h = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof J)) {
            return false;
        }
        J j3 = (J) obj;
        return a1.e.a(this.f20027a, j3.f20027a) && a1.e.a(this.f20028b, j3.f20028b) && a1.e.a(this.c, j3.c) && Float.compare(this.d, j3.d) == 0 && Float.compare(this.f20029e, j3.f20029e) == 0 && a1.m.a(this.f20030f, j3.f20030f) && this.f20031g == j3.f20031g && this.f20032h == j3.f20032h;
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f20027a) * 31, 31, this.f20028b), 31, this.c), 31, this.d), 31, this.f20029e);
        a1.n[] nVarArr = a1.m.f11041b;
        return Integer.hashCode(this.f20032h) + AbstractC2641j.b(this.f20031g, AbstractC1347d.c(iB, 31, this.f20030f), 31);
    }

    public final String toString() {
        String strB = a1.e.b(this.f20027a);
        String strB2 = a1.e.b(this.f20028b);
        String strB3 = a1.e.b(this.c);
        String strD = a1.m.d(this.f20030f);
        StringBuilder sb = new StringBuilder("ScoringValues(noteHeight=");
        sb.append(strB);
        sb.append(", noteStrokeWidth=");
        sb.append(strB2);
        sb.append(", startLineStrokeWidth=");
        sb.append(strB3);
        sb.append(", aimAssist=");
        sb.append(this.d);
        sb.append(", dynamicAimAssist=");
        sb.append(this.f20029e);
        sb.append(", lyricsFontSize=");
        sb.append(strD);
        sb.append(", hitsForMultiplier=");
        sb.append(this.f20031g);
        sb.append(", maxMultiplier=");
        return M5.b.h(sb, this.f20032h, ")");
    }
}
