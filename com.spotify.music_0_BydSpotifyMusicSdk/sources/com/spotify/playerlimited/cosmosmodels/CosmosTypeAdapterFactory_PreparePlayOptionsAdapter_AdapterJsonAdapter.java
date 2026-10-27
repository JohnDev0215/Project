package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.model.AudioStream;
import com.spotify.player.model.Suppressions;
import com.spotify.player.model.command.options.PlayerOptionOverrides;
import com.spotify.player.model.command.options.PrefetchLevel;
import com.spotify.player.model.command.options.SkipToTrack;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PreparePlayOptionsAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;
    public final f f;
    public final f g;
    public final f h;
    public final f i;
    public final f j;

    public CosmosTypeAdapterFactory_PreparePlayOptionsAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("always_play_something", "audio_stream", "configuration_override", "initially_paused", "license", "playback_id", "player_options_override", "prefetch_level", "seek_to", "session_id", "skip_to", "suppressions", "system_initiated");
        v03.i(bVarA, "of(\"always_play_somethin…ons\", \"system_initiated\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(Boolean.class, emptySet, "alwaysPlaySomething");
        v03.i(fVarF, "moshi.adapter(Boolean::c…), \"alwaysPlaySomething\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(AudioStream.class, emptySet, "audioStream");
        v03.i(fVarF2, "moshi.adapter(AudioStrea…mptySet(), \"audioStream\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(zo2.j(Map.class, String.class, Object.class), emptySet, "configurationOverride");
        v03.i(fVarF3, "moshi.adapter(Types.newP… \"configurationOverride\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(String.class, emptySet, "license");
        v03.i(fVarF4, "moshi.adapter(String::cl…   emptySet(), \"license\")");
        this.e = fVarF4;
        f fVarF5 = oVar.f(PlayerOptionOverrides.class, emptySet, "playerOptionsOverride");
        v03.i(fVarF5, "moshi.adapter(PlayerOpti… \"playerOptionsOverride\")");
        this.f = fVarF5;
        f fVarF6 = oVar.f(PrefetchLevel.class, emptySet, "prefetchLevel");
        v03.i(fVarF6, "moshi.adapter(PrefetchLe…tySet(), \"prefetchLevel\")");
        this.g = fVarF6;
        f fVarF7 = oVar.f(Long.class, emptySet, "seekTo");
        v03.i(fVarF7, "moshi.adapter(Long::clas…    emptySet(), \"seekTo\")");
        this.h = fVarF7;
        f fVarF8 = oVar.f(SkipToTrack.class, emptySet, "skipTo");
        v03.i(fVarF8, "moshi.adapter(SkipToTrac…va, emptySet(), \"skipTo\")");
        this.i = fVarF8;
        f fVarF9 = oVar.f(Suppressions.class, emptySet, "suppressions");
        v03.i(fVarF9, "moshi.adapter(Suppressio…ptySet(), \"suppressions\")");
        this.j = fVarF9;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        Map map = null;
        Boolean bool2 = null;
        String str = null;
        String str2 = null;
        PlayerOptionOverrides playerOptionOverrides = null;
        PrefetchLevel prefetchLevel = null;
        Long l = null;
        String str3 = null;
        SkipToTrack skipToTrack = null;
        Suppressions suppressions = null;
        Boolean bool3 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = false;
        AudioStream audioStream = null;
        while (jsonReader.hasNext()) {
            Boolean bool4 = bool3;
            int iM0 = jsonReader.M0(this.a);
            Suppressions suppressions2 = suppressions;
            f fVar = this.e;
            SkipToTrack skipToTrack2 = skipToTrack;
            f fVar2 = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    bool = (Boolean) fVar2.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z = true;
                    continue;
                case 1:
                    audioStream = (AudioStream) this.c.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z2 = true;
                    continue;
                case 2:
                    map = (Map) this.d.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z3 = true;
                    continue;
                case 3:
                    bool2 = (Boolean) fVar2.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z4 = true;
                    continue;
                case 4:
                    str = (String) fVar.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z5 = true;
                    continue;
                case 5:
                    str2 = (String) fVar.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z6 = true;
                    continue;
                case 6:
                    playerOptionOverrides = (PlayerOptionOverrides) this.f.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z7 = true;
                    continue;
                case 7:
                    prefetchLevel = (PrefetchLevel) this.g.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z8 = true;
                    continue;
                case 8:
                    l = (Long) this.h.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z9 = true;
                    continue;
                case 9:
                    str3 = (String) fVar.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z10 = true;
                    continue;
                case 10:
                    skipToTrack = (SkipToTrack) this.i.fromJson(jsonReader);
                    bool3 = bool4;
                    suppressions = suppressions2;
                    z11 = true;
                    continue;
                case 11:
                    suppressions = (Suppressions) this.j.fromJson(jsonReader);
                    bool3 = bool4;
                    skipToTrack = skipToTrack2;
                    z12 = true;
                    continue;
                case 12:
                    bool3 = (Boolean) fVar2.fromJson(jsonReader);
                    suppressions = suppressions2;
                    skipToTrack = skipToTrack2;
                    z13 = true;
                    continue;
            }
            bool3 = bool4;
            suppressions = suppressions2;
            skipToTrack = skipToTrack2;
        }
        SkipToTrack skipToTrack3 = skipToTrack;
        Suppressions suppressions3 = suppressions;
        Boolean bool5 = bool3;
        jsonReader.q();
        CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.b = bool;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.j = audioStream;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.m = map;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.e = bool2;
        }
        if (z5) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.l = str;
        }
        if (z6) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.a = str2;
        }
        if (z7) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.g = playerOptionOverrides;
        }
        if (z8) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.i = prefetchLevel;
        }
        if (z9) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.d = l;
        }
        if (z10) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.k = str3;
        }
        if (z11) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.c = skipToTrack3;
        }
        if (z12) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.h = suppressions3;
        }
        if (z13) {
            cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter.f = bool5;
        }
        return cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter) {
        CosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2 = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("always_play_something");
        Boolean bool = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.b;
        f fVar = this.b;
        fVar.toJson(mVar, bool);
        mVar.J0("audio_stream");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.j);
        mVar.J0("configuration_override");
        this.d.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.m);
        mVar.J0("initially_paused");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.e);
        mVar.J0("license");
        String str = cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.l;
        f fVar2 = this.e;
        fVar2.toJson(mVar, str);
        mVar.J0("playback_id");
        fVar2.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.a);
        mVar.J0("player_options_override");
        this.f.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.g);
        mVar.J0("prefetch_level");
        this.g.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.i);
        mVar.J0("seek_to");
        this.h.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.d);
        mVar.J0("session_id");
        fVar2.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.k);
        mVar.J0("skip_to");
        this.i.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.c);
        mVar.J0("suppressions");
        this.j.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.h);
        mVar.J0("system_initiated");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PreparePlayOptionsAdapter$Adapter2.f);
        mVar.X();
    }

    public final String toString() {
        return u4.b(80, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PreparePlayOptionsAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
