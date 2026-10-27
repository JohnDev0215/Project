package p;

/* JADX INFO: loaded from: classes.dex */
public final class j71 {
    public static final j71 c = new j71(0, 0);
    public final int a;
    public final int b;

    public j71(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != j71.class) {
            return false;
        }
        j71 j71Var = (j71) obj;
        return j71Var.a == this.a && j71Var.b == this.b;
    }

    public final int hashCode() {
        return this.b + this.a;
    }

    public final String toString() {
        return this == c ? "EMPTY" : String.format("(enabled=0x%x,disabled=0x%x)", Integer.valueOf(this.a), Integer.valueOf(this.b));
    }
}
