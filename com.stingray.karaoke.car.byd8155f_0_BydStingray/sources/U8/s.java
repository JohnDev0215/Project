package U8;

/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f9270b = new r();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9271a;

    public static final Object a(Object obj) {
        if (obj instanceof r) {
            return null;
        }
        return obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof s) {
            return A7.m.a(this.f9271a, ((s) obj).f9271a);
        }
        return false;
    }

    public final int hashCode() {
        Object obj = this.f9271a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        Object obj = this.f9271a;
        if (obj instanceof q) {
            return ((q) obj).toString();
        }
        return "Value(" + obj + ')';
    }
}
