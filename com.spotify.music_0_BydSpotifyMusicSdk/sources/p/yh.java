package p;

/* JADX INFO: loaded from: classes.dex */
public final class yh extends e40 {
    public final String a;

    public yh(String str) {
        this.a = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof e40)) {
            return false;
        }
        return this.a.equals(((yh) ((e40) obj)).a);
    }

    public final int hashCode() {
        return this.a.hashCode() ^ 1000003;
    }

    public final String toString() {
        return u4.m(new StringBuilder("User{identifier="), this.a, "}");
    }
}
