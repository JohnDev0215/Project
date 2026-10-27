package i0;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f6873a;

    private d(Object obj) {
        this.f6873a = obj;
    }

    static d a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new d(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || d.class != obj.getClass()) {
            return false;
        }
        return h0.c.a(this.f6873a, ((d) obj).f6873a);
    }

    public int hashCode() {
        Object obj = this.f6873a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.f6873a + "}";
    }
}
