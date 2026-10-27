package p;

/* JADX INFO: loaded from: classes.dex */
public final class me {
    public final ke a;

    public me(ke keVar) {
        this.a = keVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof me) && v03.b(this.a, ((me) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "AudiobookWrapper(data=" + this.a + ')';
    }
}
