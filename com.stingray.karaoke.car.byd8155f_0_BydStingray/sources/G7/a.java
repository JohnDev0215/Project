package G7;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2710a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f2711b;

    public a(float f10, float f11) {
        this.f2710a = f10;
        this.f2711b = f11;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean b(Comparable comparable, Comparable comparable2) {
        return ((Number) comparable).floatValue() <= ((Number) comparable2).floatValue();
    }

    public final boolean a() {
        return this.f2710a > this.f2711b;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            if (!a() || !((a) obj).a()) {
                a aVar = (a) obj;
                if (this.f2710a != aVar.f2710a || this.f2711b != aVar.f2711b) {
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (a()) {
            return -1;
        }
        return Float.hashCode(this.f2711b) + (Float.hashCode(this.f2710a) * 31);
    }

    public final String toString() {
        return this.f2710a + ".." + this.f2711b;
    }
}
