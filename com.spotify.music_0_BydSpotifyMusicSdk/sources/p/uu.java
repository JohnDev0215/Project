package p;

/* JADX INFO: loaded from: classes.dex */
public final class uu extends wu {
    public final String a;

    public uu(String str) {
        str.getClass();
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof uu) {
            return ((uu) obj).a.equals(this.a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Failure{reasons="), this.a, '}');
    }
}
