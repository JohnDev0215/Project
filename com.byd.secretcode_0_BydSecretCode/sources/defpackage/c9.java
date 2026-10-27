package defpackage;

/* JADX INFO: compiled from: DisplayCutoutCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c9 {
    public final Object a;

    public c9(Object obj) {
        this.a = obj;
    }

    public static c9 a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new c9(obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || c9.class != obj.getClass()) {
            return false;
        }
        c9 c9Var = (c9) obj;
        Object obj2 = this.a;
        return obj2 == null ? c9Var.a == null : obj2.equals(c9Var.a);
    }

    public int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.a + "}";
    }
}
