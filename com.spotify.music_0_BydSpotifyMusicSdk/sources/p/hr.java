package p;

/* JADX INFO: loaded from: classes.dex */
public final class hr {
    public final String a;
    public final i31 b;

    public hr(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hr)) {
            return false;
        }
        hr hrVar = (hr) obj;
        return v03.b(this.a, hrVar.a) && v03.b(this.b, hrVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "CoverArt(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
