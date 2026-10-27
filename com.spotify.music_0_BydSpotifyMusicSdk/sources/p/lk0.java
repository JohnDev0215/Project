package p;

/* JADX INFO: loaded from: classes.dex */
public final class lk0 extends v03 {
    public final String r;

    public lk0(String str) {
        v03.j(str, "errorMsg");
        this.r = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof lk0) && v03.b(this.r, ((lk0) obj).r);
    }

    public final int hashCode() {
        return this.r.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("Error(errorMsg="), this.r, ')');
    }
}
