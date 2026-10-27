package p;

/* JADX INFO: loaded from: classes.dex */
public final class y72 extends z72 {
    public final Object a;

    public y72(Object obj) {
        this.a = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof y72) && v03.b(this.a, ((y72) obj).a);
    }

    public final int hashCode() {
        Object obj = this.a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public final String toString() {
        return "Valid(extra=" + this.a + ')';
    }
}
