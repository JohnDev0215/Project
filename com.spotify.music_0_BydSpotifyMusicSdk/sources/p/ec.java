package p;

/* JADX INFO: loaded from: classes.dex */
public final class ec {
    public final String a;

    public ec(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ec) && v03.b(this.a, ((ec) obj).a);
    }

    public final int hashCode() {
        String str = this.a;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("OnError(message="), this.a, ')');
    }
}
