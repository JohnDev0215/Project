package Y0;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f10605a;

    public final boolean equals(Object obj) {
        if (obj instanceof a) {
            return Float.compare(this.f10605a, ((a) obj).f10605a) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.f10605a);
    }

    public final String toString() {
        return "BaselineShift(multiplier=" + this.f10605a + ')';
    }
}
