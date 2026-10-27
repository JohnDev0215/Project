package p;

/* JADX INFO: loaded from: classes.dex */
public final class qm2 {
    public final int a;

    public qm2(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof qm2) && this.a == ((qm2) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }

    public final String toString() {
        return u4.k(new StringBuilder("Duration(totalMilliseconds="), this.a, ')');
    }
}
