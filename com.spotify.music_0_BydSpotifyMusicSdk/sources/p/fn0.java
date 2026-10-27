package p;

/* JADX INFO: loaded from: classes.dex */
public final class fn0 implements fz1 {
    public final String a;

    public fn0(String str) {
        this.a = str;
    }

    @Override // p.tq1
    public final void a(a81 a81Var, com.apollographql.apollo3.api.c cVar) {
        v03.j(cVar, "customScalarAdapters");
        a81Var.l0("uri");
        c5.a.b(a81Var, cVar, this.a);
    }

    @Override // p.tq1
    public final wo1 b() {
        hn0 hn0Var = hn0.c;
        w1 w1Var = c5.a;
        return new wo1(hn0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "aa263032074b52ce2639f62f8216850daccc77fea35a5e8d556233191bd3c90e";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchArtistInfo($uri: ID!) { artistUnion(uri: $uri) { __typename ... on Artist { __typename ...artist } } }  fragment image on Image { sources { url width height } }  fragment artist on Artist { uri profile { name } visuals { avatarImage { __typename ...image } } }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof fn0) && v03.b(this.a, ((fn0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // p.tq1
    public final String name() {
        return "fetchArtistInfo";
    }

    public final String toString() {
        return hf2.d(new StringBuilder("FetchArtistInfoQuery(uri="), this.a, ')');
    }
}
