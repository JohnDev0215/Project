package p;

/* JADX INFO: loaded from: classes.dex */
public final class mb1 extends pb1 {
    public final w50 a = w50.c;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || mb1.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((mb1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (mb1.class.getName().hashCode() * 31);
    }

    public final String toString() {
        return "Failure {mOutputData=" + this.a + '}';
    }
}
