package t1;

/* JADX INFO: compiled from: MutablePair.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public T f8016a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public T f8017b;

    public static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public void b(T t7, T t8) {
        this.f8016a = t7;
        this.f8017b = t8;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof n0.d)) {
            return false;
        }
        n0.d dVar = (n0.d) obj;
        return a(dVar.f6506a, this.f8016a) && a(dVar.f6507b, this.f8017b);
    }

    public int hashCode() {
        T t7 = this.f8016a;
        int iHashCode = t7 == null ? 0 : t7.hashCode();
        T t8 = this.f8017b;
        return iHashCode ^ (t8 != null ? t8.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.f8016a) + " " + String.valueOf(this.f8017b) + "}";
    }
}
