package p;

import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class q20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public q20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter = (CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter) objFromJson;
        PlayerOptionOverrides.Builder builderA = PlayerOptionOverrides.a();
        Boolean bool = cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.a;
        builderA.d(Boolean.valueOf(bool != null ? bool.booleanValue() : false));
        Boolean bool2 = cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.b;
        builderA.b(Boolean.valueOf(bool2 != null ? bool2.booleanValue() : false));
        Boolean bool3 = cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.c;
        builderA.c(Boolean.valueOf(bool3 != null ? bool3.booleanValue() : false));
        PlayerOptionOverrides playerOptionOverridesA = builderA.a();
        v03.i(playerOptionOverridesA, "builder()\n              …\n                .build()");
        return playerOptionOverridesA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        PlayerOptionOverrides playerOptionOverrides = (PlayerOptionOverrides) obj;
        v03.j(mVar, "writer");
        if (playerOptionOverrides == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter();
        cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.a = (Boolean) playerOptionOverrides.d().h();
        cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.b = (Boolean) playerOptionOverrides.b().h();
        cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.c = (Boolean) playerOptionOverrides.c().h();
        this.a.c(CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter);
    }
}
