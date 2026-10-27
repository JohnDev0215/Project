package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final g c = new g(f.f10613b, 17);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f10614a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f10615b;

    public g(float f10, int i7) {
        this.f10614a = f10;
        this.f10615b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        float f10 = gVar.f10614a;
        float f11 = f.f10612a;
        return Float.compare(this.f10614a, f10) == 0 && this.f10615b == gVar.f10615b;
    }

    public final int hashCode() {
        float f10 = f.f10612a;
        return Integer.hashCode(this.f10615b) + (Float.hashCode(this.f10614a) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("LineHeightStyle(alignment=");
        float f10 = this.f10614a;
        if (f10 == 0.0f) {
            float f11 = f.f10612a;
            str = "LineHeightStyle.Alignment.Top";
        } else if (f10 == f.f10612a) {
            str = "LineHeightStyle.Alignment.Center";
        } else if (f10 == f.f10613b) {
            str = "LineHeightStyle.Alignment.Proportional";
        } else if (f10 == f.c) {
            str = "LineHeightStyle.Alignment.Bottom";
        } else {
            str = "LineHeightStyle.Alignment(topPercentage = " + f10 + ')';
        }
        sb.append((Object) str);
        sb.append(", trim=");
        int i7 = this.f10615b;
        sb.append((Object) (i7 == 1 ? "LineHeightStyle.Trim.FirstLineTop" : i7 == 16 ? "LineHeightStyle.Trim.LastLineBottom" : i7 == 17 ? "LineHeightStyle.Trim.Both" : i7 == 0 ? "LineHeightStyle.Trim.None" : "Invalid"));
        sb.append(')');
        return sb.toString();
    }
}
