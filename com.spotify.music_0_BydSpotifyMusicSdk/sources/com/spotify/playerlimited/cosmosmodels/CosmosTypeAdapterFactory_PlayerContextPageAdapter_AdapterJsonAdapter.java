package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.legacyplayer.PlayerTrack;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayerContextPageAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public CosmosTypeAdapterFactory_PlayerContextPageAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("metadata", "next_page_url", "page_url", "tracks");
        v03.i(bVarA, "of(\"metadata\", \"next_pag…    \"page_url\", \"tracks\")");
        this.a = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Map.class, String.class, String.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(parameterizedTypeJ, emptySet, "metadata");
        v03.i(fVarF, "moshi.adapter(Types.newP…, emptySet(), \"metadata\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "nextPageUrl");
        v03.i(fVarF2, "moshi.adapter(String::cl…mptySet(), \"nextPageUrl\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.b(PlayerTrack.class), emptySet, "tracks");
        v03.i(fVarF3, "moshi.adapter(Types.arra…a), emptySet(), \"tracks\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Map map = null;
        String str = null;
        PlayerTrack[] playerTrackArr = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 != 0) {
                f fVar = this.c;
                if (iM0 == 1) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    str = (String) fVar.fromJson(jsonReader);
                    z3 = true;
                } else if (iM0 == 3) {
                    playerTrackArr = (PlayerTrack[]) this.d.fromJson(jsonReader);
                    z4 = true;
                }
            } else {
                map = (Map) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.d = map;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.b = str2;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.a = str;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter.c = playerTrackArr;
        }
        return cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("metadata");
        this.b.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2.d);
        mVar.J0("next_page_url");
        String str = cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2.b;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("page_url");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2.a);
        mVar.J0("tracks");
        this.d.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextPageAdapter$Adapter2.c);
        mVar.X();
    }

    public final String toString() {
        return u4.b(79, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerContextPageAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
