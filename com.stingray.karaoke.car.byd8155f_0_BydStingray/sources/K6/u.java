package k6;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18299b;

    public u(int i7, int i10) {
        this.f18298a = i7;
        this.f18299b = i10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        return this.f18298a == uVar.f18298a && this.f18299b == uVar.f18299b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f18299b) + (Integer.hashCode(this.f18298a) * 31);
    }

    public final String toString() {
        return "ScreenSize(widthDp=" + this.f18298a + ", heightDp=" + this.f18299b + ")";
    }
}
