package h5;

/* JADX INFO: compiled from: IndexedValue.kt */
/* JADX INFO: loaded from: classes.dex */
public final class c0<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f6648a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f6649b;

    public c0(int i7, T t6) {
        this.f6648a = i7;
        this.f6649b = t6;
    }

    public final int a() {
        return this.f6648a;
    }

    public final T b() {
        return this.f6649b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c0)) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return this.f6648a == c0Var.f6648a && kotlin.jvm.internal.l.a(this.f6649b, c0Var.f6649b);
    }

    public int hashCode() {
        int i7 = this.f6648a * 31;
        T t6 = this.f6649b;
        return i7 + (t6 == null ? 0 : t6.hashCode());
    }

    public String toString() {
        return "IndexedValue(index=" + this.f6648a + ", value=" + this.f6649b + ')';
    }
}
