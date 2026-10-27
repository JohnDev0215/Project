package p;

/* JADX INFO: loaded from: classes.dex */
public final class wh extends b40 {
    public final String a;

    public wh(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof b40)) {
            return false;
        }
        return this.a.equals(((wh) ((b40) obj)).a);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return u4.m(new StringBuilder("Log{content="), this.a, "}");
    }
}
