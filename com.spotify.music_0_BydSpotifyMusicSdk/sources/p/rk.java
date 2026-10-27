package p;

/* JADX INFO: loaded from: classes.dex */
public final class rk extends qk {
    public final String a = "includeTracks";

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof rk) && v03.b(this.a, ((rk) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("BVariable(name="), this.a, ')');
    }
}
