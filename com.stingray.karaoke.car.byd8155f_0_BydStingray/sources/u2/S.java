package u2;

/* JADX INFO: loaded from: classes.dex */
public final class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f22704a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22705b;

    public S(int i7, boolean z6) {
        this.f22704a = i7;
        this.f22705b = z6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || S.class != obj.getClass()) {
            return false;
        }
        S s6 = (S) obj;
        return this.f22704a == s6.f22704a && this.f22705b == s6.f22705b;
    }

    public final int hashCode() {
        return (this.f22704a * 31) + (this.f22705b ? 1 : 0);
    }
}
