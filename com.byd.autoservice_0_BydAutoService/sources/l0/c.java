package l0;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3633a;

    public c(Object obj) {
        this.f3633a = obj;
    }

    public static c a(Object obj) {
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
        c cVar = (c) obj;
        Object obj2 = this.f3633a;
        return obj2 == null ? cVar.f3633a == null : obj2.equals(cVar.f3633a);
    }

    public int hashCode() {
        Object obj = this.f3633a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f3633a + "}";
    }
}
