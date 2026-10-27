package p;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.Context;
import com.spotify.player.model.Restrictions;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$ContextAdapter$Adapter;
import com.squareup.moshi.JsonReader;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public j20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$ContextAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$ContextAdapter$Adapter cosmosTypeAdapterFactory$ContextAdapter$Adapter = (CosmosTypeAdapterFactory$ContextAdapter$Adapter) objFromJson;
        String str = cosmosTypeAdapterFactory$ContextAdapter$Adapter.a;
        if (str == null) {
            str = "";
        }
        Context.Builder builderA = Context.a(str);
        String str2 = cosmosTypeAdapterFactory$ContextAdapter$Adapter.b;
        Context.Builder builderE = builderA.e(str2 != null ? str2 : "");
        Map mapH0 = cosmosTypeAdapterFactory$ContextAdapter$Adapter.c;
        if (mapH0 == null) {
            mapH0 = kotlin.collections.c.h0();
        }
        builderE.getClass();
        Context.Builder builderB = builderE.b(ImmutableMap.b(mapH0));
        List list = cosmosTypeAdapterFactory$ContextAdapter$Adapter.e;
        if (list != null) {
            builderB.getClass();
            builderB.c(ImmutableList.o(list));
        }
        Restrictions restrictions = cosmosTypeAdapterFactory$ContextAdapter$Adapter.d;
        if (restrictions != null) {
            builderB.d(restrictions);
        }
        Context contextA = builderB.a();
        v03.i(contextA, "builder.build()");
        return contextA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        Context context = (Context) obj;
        v03.j(mVar, "writer");
        if (context == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$ContextAdapter$Adapter cosmosTypeAdapterFactory$ContextAdapter$Adapter = new CosmosTypeAdapterFactory$ContextAdapter$Adapter();
        cosmosTypeAdapterFactory$ContextAdapter$Adapter.a = context.h();
        cosmosTypeAdapterFactory$ContextAdapter$Adapter.b = context.i();
        cosmosTypeAdapterFactory$ContextAdapter$Adapter.c = context.d();
        cosmosTypeAdapterFactory$ContextAdapter$Adapter.d = (Restrictions) context.f().h();
        cosmosTypeAdapterFactory$ContextAdapter$Adapter.e = (List) context.e().h();
        this.a.c(CosmosTypeAdapterFactory$ContextAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$ContextAdapter$Adapter);
    }
}
