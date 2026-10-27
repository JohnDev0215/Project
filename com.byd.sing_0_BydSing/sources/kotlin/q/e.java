package kotlin.q;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4096b;

    public boolean a() {
        return this.f4095a > this.f4096b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof e) {
            if (!a() || !((e) obj).a()) {
                e eVar = (e) obj;
                if (this.f4095a != eVar.f4095a || this.f4096b != eVar.f4096b) {
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
        return (Float.valueOf(this.f4095a).hashCode() * 31) + Float.valueOf(this.f4096b).hashCode();
    }

    public String toString() {
        return this.f4095a + ".." + this.f4096b;
    }
}
