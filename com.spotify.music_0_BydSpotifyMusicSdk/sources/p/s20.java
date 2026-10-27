package p;

import com.google.common.collect.ImmutableMap;
import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.player.model.command.options.PreparePlayOptions;
import com.spotify.player.model.command.options.SkipToTrack;
import com.spotify.playerlimited.cosmosmodels.CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter;
import com.squareup.moshi.JsonReader;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class s20 extends com.squareup.moshi.f {
    public final com.squareup.moshi.o a;

    public s20(com.squareup.moshi.o oVar) {
        v03.j(oVar, "moshi");
        this.a = oVar;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        Object objFromJson = this.a.c(CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.class).fromJson(jsonReader);
        if (objFromJson == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter = (CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter) objFromJson;
        PreparePlayOptions.Builder builderC = PreparePlayOptions.c();
        String str = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.a;
        if (str == null) {
            str = "";
        }
        PreparePlayOptions.Builder builderG = builderC.g(str);
        Boolean bool = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.b;
        PreparePlayOptions.Builder builderA = builderG.a(bool != null ? bool.booleanValue() : false);
        SkipToTrack skipToTrack = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.c;
        if (skipToTrack == null) {
            skipToTrack = SkipToTrack.a;
        }
        PreparePlayOptions.Builder builderL = builderA.l(skipToTrack);
        Long l = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.d;
        PreparePlayOptions.Builder builderJ = builderL.j(Long.valueOf(l != null ? l.longValue() : 0L));
        Boolean bool2 = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.e;
        PreparePlayOptions.Builder builderE = builderJ.e(bool2 != null ? bool2.booleanValue() : false);
        Boolean bool3 = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.f;
        PreparePlayOptions.Builder builderN = builderE.n(bool3 != null ? bool3.booleanValue() : false);
        PlayerOptionOverrides playerOptionOverrides = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.g;
        if (playerOptionOverrides == null) {
            playerOptionOverrides = PlayerOptionOverrides.a;
        }
        PreparePlayOptions.Builder builderH = builderN.h(playerOptionOverrides);
        Suppressions suppressions = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.h;
        if (suppressions == null) {
            suppressions = Suppressions.a;
        }
        PreparePlayOptions.Builder builderM = builderH.m(suppressions);
        PrefetchLevel prefetchLevel = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.i;
        if (prefetchLevel == null) {
            prefetchLevel = PrefetchLevel.c;
        }
        PreparePlayOptions.Builder builderI = builderM.i(prefetchLevel);
        AudioStream audioStream = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.j;
        if (audioStream == null) {
            audioStream = AudioStream.c;
        }
        PreparePlayOptions.Builder builderB = builderI.b(audioStream);
        String str2 = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.k;
        if (str2 == null) {
            str2 = "";
        }
        PreparePlayOptions.Builder builderK = builderB.k(str2);
        String str3 = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.l;
        builderK.f(str3 != null ? str3 : "");
        if (cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.m != null) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Map map = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.m;
            if (map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    String str4 = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof Double) {
                        try {
                            linkedHashMap.put(str4, String.valueOf((int) ((Number) value).doubleValue()));
                        } catch (NumberFormatException unused) {
                            linkedHashMap.put(str4, value.toString());
                        }
                    } else {
                        linkedHashMap.put(str4, value.toString());
                    }
                }
            }
            builderC.d(ImmutableMap.b(linkedHashMap));
        }
        PreparePlayOptions preparePlayOptionsC = builderC.c();
        v03.i(preparePlayOptionsC, "builder.build()");
        return preparePlayOptionsC;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(com.squareup.moshi.m mVar, Object obj) {
        PreparePlayOptions preparePlayOptions = (PreparePlayOptions) obj;
        v03.j(mVar, "writer");
        if (preparePlayOptions == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.a = (String) preparePlayOptions.g().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.b = (Boolean) preparePlayOptions.a().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.c = (SkipToTrack) preparePlayOptions.l().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.d = (Long) preparePlayOptions.j().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.e = (Boolean) preparePlayOptions.e().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.f = (Boolean) preparePlayOptions.n().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.g = (PlayerOptionOverrides) preparePlayOptions.h().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.h = (Suppressions) preparePlayOptions.m().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.i = (PrefetchLevel) preparePlayOptions.i().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.j = (AudioStream) preparePlayOptions.b().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.k = (String) preparePlayOptions.k().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.l = (String) preparePlayOptions.f().h();
        cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.m = preparePlayOptions.d();
        this.a.c(CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.class).toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter);
    }
}
