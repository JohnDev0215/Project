package p;

/* JADX INFO: loaded from: classes.dex */
public final class de {
    public final String a;

    public de(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof de) && v03.b(this.a, ((de) obj).a);
    }

    public final int hashCode() {
        String str = this.a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Author(name="), this.a, ')');
    }
}
