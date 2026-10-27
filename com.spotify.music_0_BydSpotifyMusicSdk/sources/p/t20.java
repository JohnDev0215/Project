package p;

import com.spotify.player.model.Restrictions;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class t20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public t20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter = (CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter) objFromJson;
        Restrictions.Builder builderA = Restrictions.a();
        builderA.f(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.e);
        builderA.n(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.f);
        builderA.o(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.j);
        builderA.h(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.a);
        builderA.g(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.b);
        builderA.s(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.c);
        builderA.r(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.d);
        builderA.t(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.g);
        builderA.u(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.h);
        builderA.v(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.i);
        builderA.p(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.t);
        builderA.b(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.u);
        builderA.e(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.v);
        builderA.w(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.k);
        builderA.i(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.l);
        builderA.d(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.m);
        builderA.c(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.n);
        builderA.m(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.o);
        builderA.l(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.f63p);
        builderA.k(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.q);
        builderA.x(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.s);
        builderA.j(cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.r);
        Restrictions restrictionsA = builderA.a();
        v03.i(restrictionsA, "builder()\n              …                 .build()");
        return restrictionsA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        Restrictions restrictions = (Restrictions) obj;
        v03.j(mVar, "writer");
        if (restrictions == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter = new CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.e = restrictions.f();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.f = restrictions.n();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.j = restrictions.o();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.a = restrictions.h();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.b = restrictions.g();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.c = restrictions.s();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.d = restrictions.r();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.g = restrictions.t();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.h = restrictions.u();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.i = restrictions.v();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.t = restrictions.p();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.u = restrictions.b();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.v = restrictions.e();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.k = restrictions.w();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.l = restrictions.i();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.m = restrictions.d();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.n = restrictions.c();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.o = restrictions.m();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.f63p = restrictions.l();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.q = restrictions.k();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.s = restrictions.x();
        cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.r = restrictions.j();
        this.a.c(CosmosTypeAdapterFactory$RestrictionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$RestrictionsAdapter$Adapter);
    }
}
