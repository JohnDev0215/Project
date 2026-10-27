package p;

/* JADX INFO: loaded from: classes.dex */
public final class q81 implements Comparable {
    public static final q81 e = new q81();
    public final int c;

    public q81() {
        if (!new i51(0, 255, 1).a(1) || !new i51(0, 255, 1).a(8) || !new i51(0, 255, 1).a(22)) {
            throw new IllegalArgumentException("Version components are out of range: 1.8.22".toString());
        }
        this.c = 67606;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        q81 q81Var = (q81) obj;
        v03.j(q81Var, "other");
        return this.c - q81Var.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        q81 q81Var = obj instanceof q81 ? (q81) obj : null;
        return q81Var != null && this.c == q81Var.c;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return "1.8.22";
    }
}
