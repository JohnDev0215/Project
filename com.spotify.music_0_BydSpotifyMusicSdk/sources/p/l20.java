package p;

import com.spotify.player.model.ContextTrack;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter;
import com.squareup.moshi.JsonReader;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class l20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public l20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        ContextTrack.Builder builderA = ContextTrack.a("");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        jsonReader.c();
        while (jsonReader.hasNext()) {
            String strR0 = jsonReader.r0();
            if (strR0.equals("uri")) {
                builderA.e(jsonReader.u());
            } else if (strR0.equals("uid")) {
                builderA.d(jsonReader.u());
            } else if (strR0.equals("provider")) {
                builderA.c(jsonReader.u());
            } else if (strR0.equals("album_uri")) {
                String strU = jsonReader.u();
                v03.i(strU, "reader.nextString()");
                linkedHashMap.put("album_uri", strU);
            } else if (strR0.equals("artist_uri")) {
                String strU2 = jsonReader.u();
                v03.i(strU2, "reader.nextString()");
                linkedHashMap.put("artist_uri", strU2);
            } else if (strR0.equals("metadata")) {
                Map map = (Map) this.a.c(Map.class).fromJson(jsonReader);
                if (map == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                linkedHashMap.putAll(map);
            } else {
                jsonReader.D();
            }
        }
        jsonReader.q();
        builderA.b(linkedHashMap);
        ContextTrack contextTrackA = builderA.a();
        v03.i(contextTrackA, "track.build()");
        return contextTrackA;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        ContextTrack contextTrack = (ContextTrack) obj;
        v03.j(mVar, "writer");
        if (contextTrack == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter = new CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter();
        cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.a = contextTrack.f();
        cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.b = contextTrack.e();
        cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.c = contextTrack.b();
        cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.d = contextTrack.c();
        this.a.c(CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter);
    }
}
