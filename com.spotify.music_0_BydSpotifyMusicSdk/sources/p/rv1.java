package p;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class rv1 {
    public final int a;
    public final int b;
    public final boolean c;
    public final Date d;
    public final boolean e;
    public final boolean f;
    public final boolean g;
    public final boolean h;
    public final String i;
    public final int j;

    public rv1(int i, int i2, boolean z, Date date, boolean z2, boolean z3, boolean z4, int i3) {
        int i4 = 0;
        z2 = (i3 & 32) != 0 ? false : z2;
        z3 = (i3 & 64) != 0 ? false : z3;
        z4 = (i3 & 128) != 0 ? false : z4;
        this.a = i;
        this.b = i2;
        this.c = z;
        this.d = date;
        this.e = false;
        this.f = z2;
        this.g = z3;
        this.h = z4;
        this.i = null;
        if (z || i2 == 0) {
            i4 = 2;
        } else {
            int i5 = i - i2;
            if (i2 >= 0 && i5 > 0) {
                i4 = 1;
            }
        }
        this.j = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof rv1)) {
            return false;
        }
        rv1 rv1Var = (rv1) obj;
        return this.a == rv1Var.a && this.b == rv1Var.b && this.c == rv1Var.c && v03.b(this.d, rv1Var.d) && this.e == rv1Var.e && this.f == rv1Var.f && this.g == rv1Var.g && this.h == rv1Var.h && v03.b(this.i, rv1Var.i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [int] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v7, types: [int] */
    /* JADX WARN: Type inference failed for: r0v9, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    public final int hashCode() {
        int iA = hf2.a(this.b, Integer.hashCode(this.a) * 31, 31);
        boolean z = this.c;
        ?? r3 = z;
        if (z) {
            r3 = 1;
        }
        int iHashCode = (this.d.hashCode() + ((iA + r3) * 31)) * 31;
        boolean z2 = this.e;
        ?? r0 = z2;
        if (z2) {
            r0 = 1;
        }
        int i = (iHashCode + r0) * 31;
        boolean z3 = this.f;
        ?? r02 = z3;
        if (z3) {
            r02 = 1;
        }
        int i2 = (i + r02) * 31;
        boolean z4 = this.g;
        ?? r03 = z4;
        if (z4) {
            r03 = 1;
        }
        int i3 = (i2 + r03) * 31;
        boolean z5 = this.h;
        int i4 = (i3 + (z5 ? 1 : z5)) * 31;
        String str = this.i;
        return i4 + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PodcastEpisodeSubtitleInformation(lengthInSeconds=");
        sb.append(this.a);
        sb.append(", timeLeftInSeconds=");
        sb.append(this.b);
        sb.append(", fullyPlayed=");
        sb.append(this.c);
        sb.append(", publicationDate=");
        sb.append(this.d);
        sb.append(", currentlyPlaying=");
        sb.append(this.e);
        sb.append(", isBookChapter=");
        sb.append(this.f);
        sb.append(", isSample=");
        sb.append(this.g);
        sb.append(", isLocked=");
        sb.append(this.h);
        sb.append(", showName=");
        return hf2.d(sb, this.i, ')');
    }
}
