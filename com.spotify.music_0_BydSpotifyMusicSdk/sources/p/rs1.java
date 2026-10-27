package p;

import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.ArrayList;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class rs1 implements ps1 {
    public final i7 a;

    public rs1(OkHttpClient okHttpClient) {
        v03.j(okHttpClient, "okHttpClient");
        z40 z40Var = new z40();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        td0 td0Var = td0.b;
        a70 a70Var = new a70(okHttpClient);
        com.apollographql.apollo3.network.ws.a aVar = new com.apollographql.apollo3.network.ws.a(okHttpClient);
        arrayList.add(new com.apollographql.apollo3.interceptor.a(HttpMethod.c, HttpMethod.e));
        Boolean bool = Boolean.TRUE;
        ArrayList arrayList3 = new ArrayList();
        arrayList3.clear();
        arrayList3.addAll(arrayList2);
        this.a = new i7(new com.apollographql.apollo3.network.http.c(new d70("https://api-partner.spotify.com/pathfinder/v1/query"), a70Var, arrayList3, false), new com.apollographql.apollo3.api.c(z40Var.a, z40Var.b), new com.apollographql.apollo3.network.ws.f("https://api-partner.spotify.com/pathfinder/v1/query", new ArrayList(), aVar, 60000L, new com.apollographql.apollo3.network.ws.b(), null), arrayList, td0Var, bool);
    }
}
