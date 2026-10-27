package p;

/* JADX INFO: loaded from: classes.dex */
public final class zu1 {
    public final ru1 a;

    public zu1(ru1 ru1Var) {
        this.a = ru1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zu1) && v03.b(this.a, ((zu1) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "OwnerV2(data=" + this.a + ')';
    }
}
