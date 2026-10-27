package a.g.k;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f390a;

    private c(Object obj) {
        this.f390a = obj;
    }

    static c a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new c(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c.class != obj.getClass()) {
            return false;
        }
        Object obj2 = this.f390a;
        Object obj3 = ((c) obj).f390a;
        return obj2 == null ? obj3 == null : obj2.equals(obj3);
    }

    public int hashCode() {
        Object obj = this.f390a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f390a + "}";
    }
}
