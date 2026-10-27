package kotlin.q;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final double f4093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final double f4094b;

    public boolean a() {
        return this.f4093a > this.f4094b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof d) {
            if (!a() || !((d) obj).a()) {
                d dVar = (d) obj;
                if (this.f4093a != dVar.f4093a || this.f4094b != dVar.f4094b) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (a()) {
            return -1;
        }
        return (Double.valueOf(this.f4093a).hashCode() * 31) + Double.valueOf(this.f4094b).hashCode();
    }

    public String toString() {
        return this.f4093a + ".." + this.f4094b;
    }
}
