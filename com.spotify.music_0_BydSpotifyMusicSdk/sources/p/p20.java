package p;

import com.spotify.player.model.PlayOrigin;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter;
import com.squareup.moshi.JsonReader;
import java.util.Set;
import kotlin.collections.EmptySet;

/* JADX INFO: loaded from: classes.dex */
public final class p20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public p20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter = (CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter) objFromJson;
        String str = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.a;
        if (str == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        PlayOrigin.Builder builderA = PlayOrigin.a(str);
        String str2 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.b;
        if (str2 == null) {
            str2 = "";
        }
        PlayOrigin.Builder builderF = builderA.f(str2);
        String str3 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.c;
        if (str3 == null) {
            str3 = "";
        }
        PlayOrigin.Builder builderI = builderF.i(str3);
        String str4 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.d;
        if (str4 == null) {
            str4 = "";
        }
        PlayOrigin.Builder builderC = builderI.c(str4);
        String str5 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.e;
        if (str5 == null) {
            str5 = "";
        }
        PlayOrigin.Builder builderG = builderC.g(str5);
        String str6 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.f;
        PlayOrigin.Builder builderB = builderG.b(str6 != null ? str6 : "");
        Set set = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.g;
        if (set == null) {
            set = EmptySet.c;
        }
        PlayOrigin playOriginA = builderB.d(set).a();
        v03.i(playOriginA, "builder(checkNotNull(ada…\n                .build()");
        return playOriginA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        PlayOrigin playOrigin = (PlayOrigin) obj;
        v03.j(mVar, "writer");
        if (playOrigin == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.a = playOrigin.f();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.b = playOrigin.g();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.c = playOrigin.k();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.d = playOrigin.d();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.e = playOrigin.h();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.f = playOrigin.c();
        cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.g = playOrigin.e();
        this.a.c(CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter);
    }
}
