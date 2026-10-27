package p;

/* JADX INFO: loaded from: classes.dex */
public final class dz1 {
    public final Class a;
    public final Class b;

    public dz1(Class cls, Class cls2) {
        this.a = cls;
        this.b = cls2;
    }

    public static dz1 a(Class cls) {
        return new dz1(cz1.class, cls);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || dz1.class != obj.getClass()) {
            return false;
        }
        dz1 dz1Var = (dz1) obj;
        if (this.b.equals(dz1Var.b)) {
            return this.a.equals(dz1Var.a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        Class cls = this.b;
        Class cls2 = this.a;
        if (cls2 == cz1.class) {
            return cls.getName();
        }
        return "@" + cls2.getName() + " " + cls.getName();
    }
}
