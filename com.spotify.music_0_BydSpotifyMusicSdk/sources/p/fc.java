package p;

/* JADX INFO: loaded from: classes.dex */
public final class fc {
    public final cc a;

    public fc(cc ccVar) {
        this.a = ccVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof fc) && v03.b(this.a, ((fc) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "ArtistWrapper(data=" + this.a + ')';
    }
}
