package p;

/* JADX INFO: loaded from: classes.dex */
public final class ob1 extends pb1 {
    public final w50 a;

    public ob1(w50 w50Var) {
        this.a = w50Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ob1.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((ob1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (ob1.class.getName().hashCode() * 31);
    }

    public final String toString() {
        return "Success {mOutputData=" + this.a + '}';
    }
}
