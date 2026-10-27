package p;

/* JADX INFO: loaded from: classes.dex */
public final class bq2 extends f60 {
    public final String h;

    public bq2(String str) {
        v03.j(str, "uri");
        this.h = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof bq2) && v03.b(this.h, ((bq2) obj).h);
    }

    public final int hashCode() {
        return this.h.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("PlayFromUri(uri="), this.h, ')');
    }
}
