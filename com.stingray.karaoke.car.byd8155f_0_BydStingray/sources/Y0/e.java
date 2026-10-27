package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f10610b = 66305;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10611a;

    public static String a(int i7) {
        StringBuilder sb = new StringBuilder("LineBreak(strategy=");
        int i10 = i7 & 255;
        String str = "Invalid";
        sb.append((Object) (l9.l.K(i10, 1) ? "Strategy.Simple" : l9.l.K(i10, 2) ? "Strategy.HighQuality" : l9.l.K(i10, 3) ? "Strategy.Balanced" : l9.l.K(i10, 0) ? "Strategy.Unspecified" : "Invalid"));
        sb.append(", strictness=");
        int i11 = (i7 >> 8) & 255;
        sb.append((Object) (w0.c.K(i11, 1) ? "Strictness.None" : w0.c.K(i11, 2) ? "Strictness.Loose" : w0.c.K(i11, 3) ? "Strictness.Normal" : w0.c.K(i11, 4) ? "Strictness.Strict" : w0.c.K(i11, 0) ? "Strictness.Unspecified" : "Invalid"));
        sb.append(", wordBreak=");
        int i12 = (i7 >> 16) & 255;
        if (i12 == 1) {
            str = "WordBreak.None";
        } else if (i12 == 2) {
            str = "WordBreak.Phrase";
        } else if (i12 == 0) {
            str = "WordBreak.Unspecified";
        }
        sb.append((Object) str);
        sb.append(')');
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.f10611a == ((e) obj).f10611a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f10611a);
    }

    public final String toString() {
        return a(this.f10611a);
    }
}
