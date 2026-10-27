package p;

/* JADX INFO: loaded from: classes.dex */
public final class tb {
    public final String a;
    public final i31 b;

    public tb(String str, i31 i31Var) {
        this.a = str;
        this.b = i31Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof tb)) {
            return false;
        }
        tb tbVar = (tb) obj;
        return v03.b(this.a, tbVar.a) && v03.b(this.b, tbVar.b);
    }

    public final int hashCode() {
        return this.b.a.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "AvatarImage(__typename=" + this.a + ", image=" + this.b + ')';
    }
}
