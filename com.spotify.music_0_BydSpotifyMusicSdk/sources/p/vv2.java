package p;

import com.google.common.collect.ImmutableSet;
import com.spotify.webapi.service.models.Entity;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class vv2 {
    public final ps1 a;
    public final yv2 b;

    public vv2(zv2 zv2Var, OkHttpClient okHttpClient, ps1 ps1Var) {
        v03.j(zv2Var, "factory");
        v03.j(okHttpClient, "authenticatedClient");
        v03.j(ps1Var, "pathfinderApi");
        this.a = ps1Var;
        HttpUrl httpUrl = aw2.a;
        g00[] g00VarArr = {new fl1(el1.a(ImmutableSet.r(Entity.getPolymorphicAdapter()))), new dl1(el1.a(ImmutableSet.r(Entity.getPolymorphicAdapter())))};
        d52 d52Var = new d52();
        d52Var.b = okHttpClient;
        d52Var.d(aw2.a);
        for (int i = 0; i < 2; i++) {
            d52Var.b(g00VarArr[i]);
        }
        d52Var.a(new a62(true));
        this.b = (yv2) d52Var.e().b(yv2.class);
    }
}
