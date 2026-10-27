package N0;

import a.AbstractC0904a;
import h2.AbstractC1347d;
import x.AbstractC2641j;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0406a f4402a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4403b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f4405f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f4406g;

    public r(C0406a c0406a, int i7, int i10, int i11, int i12, float f10, float f11) {
        this.f4402a = c0406a;
        this.f4403b = i7;
        this.c = i10;
        this.d = i11;
        this.f4404e = i12;
        this.f4405f = f10;
        this.f4406g = f11;
    }

    public final long a(long j3, boolean z6) {
        if (z6) {
            int i7 = K.c;
            long j10 = K.f4356b;
            if (K.a(j3, j10)) {
                return j10;
            }
        }
        int i10 = K.c;
        int i11 = this.f4403b;
        return AbstractC0904a.q(((int) (j3 >> 32)) + i11, ((int) (j3 & 4294967295L)) + i11);
    }

    public final int b(int i7) {
        int i10 = this.c;
        int i11 = this.f4403b;
        return AbstractC2673a.v(i7, i11, i10) - i11;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f4402a.equals(rVar.f4402a) && this.f4403b == rVar.f4403b && this.c == rVar.c && this.d == rVar.d && this.f4404e == rVar.f4404e && Float.compare(this.f4405f, rVar.f4405f) == 0 && Float.compare(this.f4406g, rVar.f4406g) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.f4406g) + AbstractC1347d.b(AbstractC2641j.b(this.f4404e, AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, AbstractC2641j.b(this.f4403b, this.f4402a.hashCode() * 31, 31), 31), 31), 31), 31, this.f4405f);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ParagraphInfo(paragraph=");
        sb.append(this.f4402a);
        sb.append(", startIndex=");
        sb.append(this.f4403b);
        sb.append(", endIndex=");
        sb.append(this.c);
        sb.append(", startLineIndex=");
        sb.append(this.d);
        sb.append(", endLineIndex=");
        sb.append(this.f4404e);
        sb.append(", top=");
        sb.append(this.f4405f);
        sb.append(", bottom=");
        return AbstractC1347d.h(sb, this.f4406g, ')');
    }
}
