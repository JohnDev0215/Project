package s3;

/* JADX INFO: renamed from: s3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2381a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f22244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22245b;
    public int c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2381a)) {
            return false;
        }
        C2381a c2381a = (C2381a) obj;
        int i7 = this.f22244a;
        if (i7 != c2381a.f22244a) {
            return false;
        }
        if (i7 == 8 && Math.abs(this.c - this.f22245b) == 1 && this.c == c2381a.f22245b && this.f22245b == c2381a.c) {
            return true;
        }
        return this.c == c2381a.c && this.f22245b == c2381a.f22245b;
    }

    public final int hashCode() {
        return (((this.f22244a * 31) + this.f22245b) * 31) + this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i7 = this.f22244a;
        sb.append(i7 != 1 ? i7 != 2 ? i7 != 4 ? i7 != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.f22245b);
        sb.append("c:");
        return M5.b.h(sb, this.c, ",p:null]");
    }
}
