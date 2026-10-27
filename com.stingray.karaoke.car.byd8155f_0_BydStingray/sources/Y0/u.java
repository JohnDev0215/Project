package y0;

/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f24100a;

    public final boolean equals(Object obj) {
        if (obj instanceof u) {
            return this.f24100a == ((u) obj).f24100a;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f24100a);
    }

    public final String toString() {
        return "PointerKeyboardModifiers(packedValue=" + this.f24100a + ')';
    }
}
