package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class p {
    public static final p c = new p(2, false);
    public static final p d = new p(1, true);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f10627a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f10628b;

    public p(int i7, boolean z6) {
        this.f10627a = i7;
        this.f10628b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f10627a == pVar.f10627a && this.f10628b == pVar.f10628b;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.f10628b) + (Integer.hashCode(this.f10627a) * 31);
    }

    public final String toString() {
        return equals(c) ? "TextMotion.Static" : equals(d) ? "TextMotion.Animated" : "Invalid";
    }
}
