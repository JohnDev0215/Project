package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.legacyplayer.PlayOptions$AudioStream;
import com.spotify.player.legacyplayer.PlayOptions$Operation;
import com.spotify.player.legacyplayer.PlayOptions$Trigger;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.b;
import p.ds2;
import p.nu1;
import p.rt1;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayOptionsAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter> {
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

    public CosmosTypeAdapterFactory_PlayOptionsAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("allow_seeking", "always_play_something", "audio_stream", "initially_paused", "operation", "override_restrictions", "playback_id", "player_options_override", "seek_to", "skip_to", "suppressions", "system_initiated", "trigger");
        v03.i(bVarA, "of(\"allow_seeking\",\n    …em_initiated\", \"trigger\")");
        this.a = bVarA;
        Class cls = Boolean.TYPE;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(cls, emptySet, "allowSeeking");
        v03.i(fVarF, "moshi.adapter(Boolean::c…(),\n      \"allowSeeking\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(PlayOptions$AudioStream.class, emptySet, "audioStream");
        v03.i(fVarF2, "moshi.adapter(PlayOption…mptySet(), \"audioStream\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(PlayOptions$Operation.class, emptySet, "operation");
        v03.i(fVarF3, "moshi.adapter(PlayOption… emptySet(), \"operation\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(String.class, emptySet, "playbackId");
        v03.i(fVarF4, "moshi.adapter(String::cl…emptySet(), \"playbackId\")");
        this.e = fVarF4;
        f fVarF5 = oVar.f(b.class, emptySet, "playerOptionsOverride");
        v03.i(fVarF5, "moshi.adapter(PlayerOpti… \"playerOptionsOverride\")");
        this.f = fVarF5;
        f fVarF6 = oVar.f(Long.class, emptySet, "seekTo");
        v03.i(fVarF6, "moshi.adapter(Long::clas…    emptySet(), \"seekTo\")");
        this.g = fVarF6;
        f fVarF7 = oVar.f(rt1.class, emptySet, "skipTo");
        v03.i(fVarF7, "moshi.adapter(PlayOption…va, emptySet(), \"skipTo\")");
        this.h = fVarF7;
        f fVarF8 = oVar.f(nu1.class, emptySet, "suppressions");
        v03.i(fVarF8, "moshi.adapter(PlayerSupp…ptySet(), \"suppressions\")");
        this.i = fVarF8;
        f fVarF9 = oVar.f(PlayOptions$Trigger.class, emptySet, "trigger");
        v03.i(fVarF9, "moshi.adapter(PlayOption…a, emptySet(), \"trigger\")");
        this.j = fVarF9;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        PlayOptions$AudioStream playOptions$AudioStream = null;
        Boolean bool2 = null;
        PlayOptions$Operation playOptions$Operation = null;
        Boolean bool3 = null;
        String str = null;
        b bVar = null;
        Long l = null;
        rt1 rt1Var = null;
        nu1 nu1Var = null;
        Boolean bool4 = null;
        PlayOptions$Trigger playOptions$Trigger = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        Boolean bool5 = null;
        while (jsonReader.hasNext()) {
            PlayOptions$Trigger playOptions$Trigger2 = playOptions$Trigger;
            int iM0 = jsonReader.M0(this.a);
            Boolean bool6 = bool4;
            f fVar = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    bool = (Boolean) fVar.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("allowSeeking", "allow_seeking", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"allowSee… \"allow_seeking\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 1:
                    bool5 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool5 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("alwaysPlaySomething", "always_play_something", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"alwaysPl…_play_something\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                case 2:
                    playOptions$AudioStream = (PlayOptions$AudioStream) this.c.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z = true;
                    continue;
                case 3:
                    bool2 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool2 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("initiallyPaused", "initially_paused", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"initiall…nitially_paused\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    break;
                case 4:
                    playOptions$Operation = (PlayOptions$Operation) this.d.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z2 = true;
                    continue;
                case 5:
                    bool3 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool3 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("overrideRestrictions", "override_restrictions", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"override…de_restrictions\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    break;
                case 6:
                    str = (String) this.e.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z3 = true;
                    continue;
                case 7:
                    bVar = (b) this.f.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z4 = true;
                    continue;
                case 8:
                    l = (Long) this.g.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z5 = true;
                    continue;
                case 9:
                    rt1Var = (rt1) this.h.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z6 = true;
                    continue;
                case 10:
                    nu1Var = (nu1) this.i.fromJson(jsonReader);
                    playOptions$Trigger = playOptions$Trigger2;
                    bool4 = bool6;
                    z7 = true;
                    continue;
                case 11:
                    bool4 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool4 == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("systemInitiated", "system_initiated", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"systemIn…ystem_initiated\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    playOptions$Trigger = playOptions$Trigger2;
                    continue;
                    break;
                case 12:
                    playOptions$Trigger = (PlayOptions$Trigger) this.j.fromJson(jsonReader);
                    bool4 = bool6;
                    z8 = true;
                    continue;
            }
            playOptions$Trigger = playOptions$Trigger2;
            bool4 = bool6;
        }
        Boolean bool7 = bool4;
        PlayOptions$Trigger playOptions$Trigger3 = playOptions$Trigger;
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter();
        cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.f = bool != null ? bool.booleanValue() : cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.f;
        cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.m = bool5 != null ? bool5.booleanValue() : cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.m;
        if (z) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.k = playOptions$AudioStream;
        }
        cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.c = bool2 != null ? bool2.booleanValue() : cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.c;
        if (z2) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.g = playOptions$Operation;
        }
        cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.l = bool3 != null ? bool3.booleanValue() : cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.l;
        if (z3) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.i = str;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.d = bVar;
        }
        if (z5) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.b = l;
        }
        if (z6) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.a = rt1Var;
        }
        if (z7) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.e = nu1Var;
        }
        cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.j = bool7 != null ? bool7.booleanValue() : cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.j;
        if (z8) {
            cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter.h = playOptions$Trigger3;
        }
        return cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("allow_seeking");
        Boolean boolValueOf = Boolean.valueOf(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.f);
        f fVar = this.b;
        fVar.toJson(mVar, boolValueOf);
        mVar.J0("always_play_something");
        u4.w(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.m, fVar, mVar, "audio_stream");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.k);
        mVar.J0("initially_paused");
        u4.w(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.c, fVar, mVar, "operation");
        this.d.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.g);
        mVar.J0("override_restrictions");
        u4.w(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.l, fVar, mVar, "playback_id");
        this.e.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.i);
        mVar.J0("player_options_override");
        this.f.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.d);
        mVar.J0("seek_to");
        this.g.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.b);
        mVar.J0("skip_to");
        this.h.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.a);
        mVar.J0("suppressions");
        this.i.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.e);
        mVar.J0("system_initiated");
        u4.w(cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.j, fVar, mVar, "trigger");
        this.j.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsAdapter$Adapter2.h);
        mVar.X();
    }

    public final String toString() {
        return u4.b(73, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayOptionsAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
