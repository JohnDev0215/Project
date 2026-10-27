package p;

/* JADX INFO: loaded from: classes.dex */
public final class f31 implements kw1 {
    public final String a;
    public final boolean b;
    public final String c;

    public f31(String str) {
        v03.j(str, "policyValue");
        this.a = "/client/spotify-id";
        this.b = true;
        this.c = str;
    }

    @Override // p.kw1
    public final boolean a() {
        return this.b;
    }

    @Override // p.kw1
    public final String b() {
        return this.c;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof f31) && v03.b(this.c, ((f31) obj).c);
    }

    @Override // p.kw1
    public final String getPolicyId() {
        return this.a;
    }

    public final int hashCode() {
        return this.c.hashCode();
    }

    public final String toString() {
        return hf2.d(new StringBuilder("SpotifyIdIdentifier(policyValue="), this.c, ')');
    }
}
