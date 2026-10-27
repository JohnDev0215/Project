package o0;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6681a;

    public c(Object obj) {
        this.f6681a = obj;
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
        Object obj2 = this.f6681a;
        return obj2 == null ? cVar.f6681a == null : obj2.equals(cVar.f6681a);
    }

    public int hashCode() {
        Object obj = this.f6681a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f6681a + "}";
    }
}
