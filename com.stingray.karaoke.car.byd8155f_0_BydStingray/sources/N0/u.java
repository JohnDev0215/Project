package N0;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f4409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4410b;
    public final long c;
    public final Y0.o d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final w f4411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Y0.g f4412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f4413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f4414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Y0.p f4415i;

    public u(int i7, int i10, long j3, Y0.o oVar, w wVar, Y0.g gVar, int i11, int i12, Y0.p pVar) {
        this.f4409a = i7;
        this.f4410b = i10;
        this.c = j3;
        this.d = oVar;
        this.f4411e = wVar;
        this.f4412f = gVar;
        this.f4413g = i11;
        this.f4414h = i12;
        this.f4415i = pVar;
        if (a1.m.a(j3, a1.m.c) || a1.m.c(j3) >= 0.0f) {
            return;
        }
        throw new IllegalStateException(("lineHeight can't be negative (" + a1.m.c(j3) + ')').toString());
    }

    public final u a(u uVar) {
        if (uVar == null) {
            return this;
        }
        return v.a(this, uVar.f4409a, uVar.f4410b, uVar.c, uVar.d, uVar.f4411e, uVar.f4412f, uVar.f4413g, uVar.f4414h, uVar.f4415i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return Y0.i.a(this.f4409a, uVar.f4409a) && Y0.k.a(this.f4410b, uVar.f4410b) && a1.m.a(this.c, uVar.c) && A7.m.a(this.d, uVar.d) && A7.m.a(this.f4411e, uVar.f4411e) && A7.m.a(this.f4412f, uVar.f4412f) && this.f4413g == uVar.f4413g && Y0.d.a(this.f4414h, uVar.f4414h) && A7.m.a(this.f4415i, uVar.f4415i);
    }

    public final int hashCode() {
        int iB = AbstractC2641j.b(this.f4410b, Integer.hashCode(this.f4409a) * 31, 31);
        a1.n[] nVarArr = a1.m.f11041b;
        int iC = AbstractC1347d.c(iB, 31, this.c);
        Y0.o oVar = this.d;
        int iHashCode = (iC + (oVar != null ? oVar.hashCode() : 0)) * 31;
        w wVar = this.f4411e;
        int iHashCode2 = (iHashCode + (wVar != null ? wVar.hashCode() : 0)) * 31;
        Y0.g gVar = this.f4412f;
        int iB2 = AbstractC2641j.b(this.f4414h, AbstractC2641j.b(this.f4413g, (iHashCode2 + (gVar != null ? gVar.hashCode() : 0)) * 31, 31), 31);
        Y0.p pVar = this.f4415i;
        return iB2 + (pVar != null ? pVar.hashCode() : 0);
    }

    public final String toString() {
        return "ParagraphStyle(textAlign=" + ((Object) Y0.i.b(this.f4409a)) + ", textDirection=" + ((Object) Y0.k.b(this.f4410b)) + ", lineHeight=" + ((Object) a1.m.d(this.c)) + ", textIndent=" + this.d + ", platformStyle=" + this.f4411e + ", lineHeightStyle=" + this.f4412f + ", lineBreak=" + ((Object) Y0.e.a(this.f4413g)) + ", hyphens=" + ((Object) Y0.d.b(this.f4414h)) + ", textMotion=" + this.f4415i + ')';
    }
}
