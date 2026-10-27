package p;

/* JADX INFO: loaded from: classes.dex */
public final class nt {
    public final String a;
    public final String b;
    public final String c;
    public final lw1 d;

    public nt(lw1 lw1Var) {
        v03.j(lw1Var, "policyInputs");
        this.a = "com.spotify.auto";
        this.b = "4.4.1.0";
        this.c = "a296f6a0c8d02612625a2cce0a87cd4903b3a030b8d046935f1922a441cd30df";
        this.d = lw1Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof nt)) {
            return false;
        }
        nt ntVar = (nt) obj;
        return v03.b(this.a, ntVar.a) && v03.b(this.b, ntVar.b) && v03.b(this.c, ntVar.c) && v03.b(this.d, ntVar.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + hf2.b(this.c, hf2.b(this.b, this.a.hashCode() * 31, 31), 31);
    }

    public final String toString() {
        return "ClientData(clientId=" + this.a + ", clientVersion=" + this.b + ", propertySetId=" + this.c + ", policyInputs=" + this.d + ')';
    }
}
