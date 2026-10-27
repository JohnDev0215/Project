package p;

import com.spotify.player.model.ContextPage;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$ContextPageAdapter$Adapter;
import com.squareup.moshi.JsonReader;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class k20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public k20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$ContextPageAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$ContextPageAdapter$Adapter cosmosTypeAdapterFactory$ContextPageAdapter$Adapter = (CosmosTypeAdapterFactory$ContextPageAdapter$Adapter) objFromJson;
        ContextPage.Builder builderA = ContextPage.a();
        String str = cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.a;
        if (str == null) {
            str = "";
        }
        builderA.d(str);
        String str2 = cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.b;
        builderA.c(str2 != null ? str2 : "");
        List list = cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.c;
        if (list == null) {
            list = EmptyList.c;
        }
        ContextPage.Builder builderE = builderA.e(list);
        Map mapH0 = cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.d;
        if (mapH0 == null) {
            mapH0 = kotlin.collections.c.h0();
        }
        builderE.b(mapH0);
        ContextPage contextPageA = builderE.a();
        v03.i(contextPageA, "builder()\n              …\n                .build()");
        return contextPageA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        ContextPage contextPage = (ContextPage) obj;
        v03.j(mVar, "writer");
        if (contextPage == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$ContextPageAdapter$Adapter cosmosTypeAdapterFactory$ContextPageAdapter$Adapter = new CosmosTypeAdapterFactory$ContextPageAdapter$Adapter();
        cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.a = contextPage.d();
        cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.b = contextPage.c();
        cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.c = (List) contextPage.e().h();
        cosmosTypeAdapterFactory$ContextPageAdapter$Adapter.d = contextPage.b();
        this.a.c(CosmosTypeAdapterFactory$ContextPageAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$ContextPageAdapter$Adapter);
    }
}
