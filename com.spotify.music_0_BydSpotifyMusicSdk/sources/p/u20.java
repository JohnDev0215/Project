package p;

import com.spotify.player.model.command.options.SkipToTrack;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter;
import com.squareup.moshi.JsonReader;

/* JADX INFO: loaded from: classes.dex */
public final class u20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public u20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter = (CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter) objFromJson;
        SkipToTrack.Builder builderA = SkipToTrack.a();
        String str = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.a;
        if (str == null) {
            str = "";
        }
        builderA.c(str);
        Long l = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.b;
        builderA.b(Long.valueOf(l != null ? l.longValue() : 0L));
        String str2 = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.c;
        if (str2 == null) {
            str2 = "";
        }
        builderA.e(str2);
        String str3 = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.d;
        builderA.f(str3 != null ? str3 : "");
        Long l2 = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.e;
        builderA.d(Long.valueOf(l2 != null ? l2.longValue() : 0L));
        SkipToTrack skipToTrackA = builderA.a();
        v03.i(skipToTrackA, "builder()\n              …\n                .build()");
        return skipToTrackA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        SkipToTrack skipToTrack = (SkipToTrack) obj;
        v03.j(mVar, "writer");
        if (skipToTrack == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter = new CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter();
        cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.a = (String) skipToTrack.d().h();
        cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.b = (Long) skipToTrack.c().h();
        cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.c = (String) skipToTrack.f().h();
        cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.d = (String) skipToTrack.g().h();
        cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.e = (Long) skipToTrack.e().h();
        this.a.c(CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter);
    }
}
