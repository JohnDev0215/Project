package p;

/* JADX INFO: loaded from: classes.dex */
public final class lm0 implements fz1 {
    public final String a;

    public lm0(String str) {
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
        nm0 nm0Var = nm0.c;
        w1 w1Var = c5.a;
        return new wo1(nm0Var, false);
    }

    @Override // p.tq1
    public final String c() {
        return "59012899fdb7ced1d56fc6eb077a7eef2fd8c230f7f3568a12370eb60b019f82";
    }

    @Override // p.tq1
    public final String d() {
        return "query fetchAlbum($uri: ID!) { albumUnion(uri: $uri) { __typename ... on Album { __typename ...album } } }  fragment image on Image { sources { url width height } }  fragment album on Album { uri name coverArt { __typename ...image } artists { items { profile { name } } } date { year } name uri }";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof lm0) && v03.b(this.a, ((lm0) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    @Override // p.tq1
    public final String name() {
        return "fetchAlbum";
    }

    public final String toString() {
        return hf2.d(new StringBuilder("FetchAlbumQuery(uri="), this.a, ')');
    }
}
