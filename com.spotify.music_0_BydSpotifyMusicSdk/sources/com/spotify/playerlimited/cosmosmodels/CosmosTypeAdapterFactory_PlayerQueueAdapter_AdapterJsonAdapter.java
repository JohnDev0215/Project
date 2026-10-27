package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.legacyplayer.PlayerTrack;
import com.spotify.webapi.service.models.Search;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.GenericArrayType;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayerQueueAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public CosmosTypeAdapterFactory_PlayerQueueAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("next_tracks", "prev_tracks", "revision", Search.Type.TRACK);
        v03.i(bVarA, "of(\"next_tracks\", \"prev_…     \"revision\", \"track\")");
        this.a = bVarA;
        GenericArrayType genericArrayTypeB = zo2.b(PlayerTrack.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(genericArrayTypeB, emptySet, "nextTracks");
        v03.i(fVarF, "moshi.adapter(Types.arra…emptySet(), \"nextTracks\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "revision");
        v03.i(fVarF2, "moshi.adapter(String::cl…  emptySet(), \"revision\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(PlayerTrack.class, emptySet, Search.Type.TRACK);
        v03.i(fVarF3, "moshi.adapter(PlayerTrac…ava, emptySet(), \"track\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        PlayerTrack[] playerTrackArr = null;
        String str = null;
        PlayerTrack playerTrack = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        PlayerTrack[] playerTrackArr2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    playerTrackArr = (PlayerTrack[]) fVar.fromJson(jsonReader);
                    z = true;
                } else if (iM0 == 1) {
                    playerTrackArr2 = (PlayerTrack[]) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    str = (String) this.c.fromJson(jsonReader);
                    z3 = true;
                } else if (iM0 == 3) {
                    playerTrack = (PlayerTrack) this.d.fromJson(jsonReader);
                    z4 = true;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.c = playerTrackArr;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.d = playerTrackArr2;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.a = str;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter.b = playerTrack;
        }
        return cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("next_tracks");
        PlayerTrack[] playerTrackArr = cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2.c;
        f fVar = this.b;
        fVar.toJson(mVar, playerTrackArr);
        mVar.J0("prev_tracks");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2.d);
        mVar.J0("revision");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2.a);
        mVar.J0(Search.Type.TRACK);
        this.d.toJson(mVar, cosmosTypeAdapterFactory$PlayerQueueAdapter$Adapter2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(73, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerQueueAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
