package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFeaturesTrackJsonAdapter extends f<AudioFeaturesTrack> {
    private final f<Float> floatAdapter;
    private final f<Integer> intAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public AudioFeaturesTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("acousticness", "analysis_url", "danceability", "duration_ms", "energy", "id", "instrumentalness", "key", "liveness", "loudness", "mode", "speechiness", "tempo", "time_signature", "track_href", RxProductState.Keys.KEY_TYPE, "uri", "valence");
        v03.i(bVarA, "of(\"acousticness\", \"anal…, \"uri\",\n      \"valence\")");
        this.options = bVarA;
        Class cls = Float.TYPE;
        EmptySet emptySet = EmptySet.c;
        f<Float> fVarF = oVar.f(cls, emptySet, "acousticness");
        v03.i(fVarF, "moshi.adapter(Float::cla…(),\n      \"acousticness\")");
        this.floatAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "analysis_url");
        v03.i(fVarF2, "moshi.adapter(String::cl…ptySet(), \"analysis_url\")");
        this.nullableStringAdapter = fVarF2;
        f<Integer> fVarF3 = oVar.f(Integer.TYPE, emptySet, "duration_ms");
        v03.i(fVarF3, "moshi.adapter(Int::class…t(),\n      \"duration_ms\")");
        this.intAdapter = fVarF3;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(AudioFeaturesTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public AudioFeaturesTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Float fFromJson = null;
        Float fFromJson2 = null;
        Integer numFromJson = null;
        Float fFromJson3 = null;
        String strFromJson = null;
        Float fFromJson4 = null;
        Integer numFromJson2 = null;
        Float fFromJson5 = null;
        Float fFromJson6 = null;
        Integer numFromJson3 = null;
        Float fFromJson7 = null;
        Float fFromJson8 = null;
        Integer numFromJson4 = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        Float fFromJson9 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        String strFromJson5 = null;
        while (jsonReader.hasNext()) {
            Integer num = numFromJson4;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    fFromJson = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("acousticness", "acousticness", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"acoustic…, \"acousticness\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 1:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson4 = num;
                    z = true;
                    continue;
                case 2:
                    fFromJson2 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("danceability", "danceability", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"danceabi…, \"danceability\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                case 3:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("duration_ms", "duration_ms", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"duration…   \"duration_ms\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    break;
                case 4:
                    fFromJson3 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson3 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("energy", "energy", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"energy\",…rgy\",\n            reader)");
                        throw jsonDataExceptionW4;
                    }
                    break;
                case 5:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson4 = num;
                    z2 = true;
                    continue;
                case 6:
                    fFromJson4 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson4 == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("instrumentalness", "instrumentalness", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"instrume…nstrumentalness\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    break;
                case 7:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW6 = ds2.w("key", "key", jsonReader);
                        v03.i(jsonDataExceptionW6, "unexpectedNull(\"key\", \"key\", reader)");
                        throw jsonDataExceptionW6;
                    }
                    break;
                case 8:
                    fFromJson5 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson5 == null) {
                        JsonDataException jsonDataExceptionW7 = ds2.w("liveness", "liveness", jsonReader);
                        v03.i(jsonDataExceptionW7, "unexpectedNull(\"liveness…      \"liveness\", reader)");
                        throw jsonDataExceptionW7;
                    }
                    break;
                case 9:
                    fFromJson6 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson6 == null) {
                        JsonDataException jsonDataExceptionW8 = ds2.w("loudness", "loudness", jsonReader);
                        v03.i(jsonDataExceptionW8, "unexpectedNull(\"loudness…      \"loudness\", reader)");
                        throw jsonDataExceptionW8;
                    }
                    break;
                case 10:
                    numFromJson3 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionW9 = ds2.w("mode", "mode", jsonReader);
                        v03.i(jsonDataExceptionW9, "unexpectedNull(\"mode\", \"mode\",\n            reader)");
                        throw jsonDataExceptionW9;
                    }
                    break;
                case 11:
                    fFromJson7 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson7 == null) {
                        JsonDataException jsonDataExceptionW10 = ds2.w("speechiness", "speechiness", jsonReader);
                        v03.i(jsonDataExceptionW10, "unexpectedNull(\"speechin…\", \"speechiness\", reader)");
                        throw jsonDataExceptionW10;
                    }
                    break;
                case 12:
                    fFromJson8 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson8 == null) {
                        JsonDataException jsonDataExceptionW11 = ds2.w("tempo", "tempo", jsonReader);
                        v03.i(jsonDataExceptionW11, "unexpectedNull(\"tempo\", …mpo\",\n            reader)");
                        throw jsonDataExceptionW11;
                    }
                    break;
                case 13:
                    numFromJson4 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson4 == null) {
                        JsonDataException jsonDataExceptionW12 = ds2.w("time_signature", "time_signature", jsonReader);
                        v03.i(jsonDataExceptionW12, "unexpectedNull(\"time_sig…\"time_signature\", reader)");
                        throw jsonDataExceptionW12;
                    }
                    continue;
                    break;
                case 14:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson4 = num;
                    z3 = true;
                    continue;
                case 15:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson4 = num;
                    z4 = true;
                    continue;
                case 16:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson4 = num;
                    z5 = true;
                    continue;
                case 17:
                    fFromJson9 = this.floatAdapter.fromJson(jsonReader);
                    if (fFromJson9 == null) {
                        JsonDataException jsonDataExceptionW13 = ds2.w("valence", "valence", jsonReader);
                        v03.i(jsonDataExceptionW13, "unexpectedNull(\"valence\"…       \"valence\", reader)");
                        throw jsonDataExceptionW13;
                    }
                    break;
            }
            numFromJson4 = num;
        }
        Integer num2 = numFromJson4;
        jsonReader.q();
        AudioFeaturesTrack audioFeaturesTrack = new AudioFeaturesTrack();
        audioFeaturesTrack.acousticness = fFromJson != null ? fFromJson.floatValue() : audioFeaturesTrack.acousticness;
        if (z) {
            audioFeaturesTrack.analysis_url = strFromJson5;
        }
        audioFeaturesTrack.danceability = fFromJson2 != null ? fFromJson2.floatValue() : audioFeaturesTrack.danceability;
        audioFeaturesTrack.duration_ms = numFromJson != null ? numFromJson.intValue() : audioFeaturesTrack.duration_ms;
        audioFeaturesTrack.energy = fFromJson3 != null ? fFromJson3.floatValue() : audioFeaturesTrack.energy;
        if (z2) {
            audioFeaturesTrack.id = strFromJson;
        }
        audioFeaturesTrack.instrumentalness = fFromJson4 != null ? fFromJson4.floatValue() : audioFeaturesTrack.instrumentalness;
        audioFeaturesTrack.key = numFromJson2 != null ? numFromJson2.intValue() : audioFeaturesTrack.key;
        audioFeaturesTrack.liveness = fFromJson5 != null ? fFromJson5.floatValue() : audioFeaturesTrack.liveness;
        audioFeaturesTrack.loudness = fFromJson6 != null ? fFromJson6.floatValue() : audioFeaturesTrack.loudness;
        audioFeaturesTrack.mode = numFromJson3 != null ? numFromJson3.intValue() : audioFeaturesTrack.mode;
        audioFeaturesTrack.speechiness = fFromJson7 != null ? fFromJson7.floatValue() : audioFeaturesTrack.speechiness;
        audioFeaturesTrack.tempo = fFromJson8 != null ? fFromJson8.floatValue() : audioFeaturesTrack.tempo;
        audioFeaturesTrack.time_signature = num2 != null ? num2.intValue() : audioFeaturesTrack.time_signature;
        if (z3) {
            audioFeaturesTrack.track_href = strFromJson2;
        }
        if (z4) {
            audioFeaturesTrack.type = strFromJson3;
        }
        if (z5) {
            audioFeaturesTrack.uri = strFromJson4;
        }
        audioFeaturesTrack.valence = fFromJson9 != null ? fFromJson9.floatValue() : audioFeaturesTrack.valence;
        return audioFeaturesTrack;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, AudioFeaturesTrack audioFeaturesTrack) {
        v03.j(mVar, "writer");
        if (audioFeaturesTrack == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("acousticness");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.acousticness));
        mVar.J0("analysis_url");
        this.nullableStringAdapter.toJson(mVar, audioFeaturesTrack.analysis_url);
        mVar.J0("danceability");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.danceability));
        mVar.J0("duration_ms");
        u4.s(audioFeaturesTrack.duration_ms, this.intAdapter, mVar, "energy");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.energy));
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, audioFeaturesTrack.id);
        mVar.J0("instrumentalness");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.instrumentalness));
        mVar.J0("key");
        u4.s(audioFeaturesTrack.key, this.intAdapter, mVar, "liveness");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.liveness));
        mVar.J0("loudness");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.loudness));
        mVar.J0("mode");
        u4.s(audioFeaturesTrack.mode, this.intAdapter, mVar, "speechiness");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.speechiness));
        mVar.J0("tempo");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.tempo));
        mVar.J0("time_signature");
        u4.s(audioFeaturesTrack.time_signature, this.intAdapter, mVar, "track_href");
        this.nullableStringAdapter.toJson(mVar, audioFeaturesTrack.track_href);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, audioFeaturesTrack.type);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, audioFeaturesTrack.uri);
        mVar.J0("valence");
        this.floatAdapter.toJson(mVar, Float.valueOf(audioFeaturesTrack.valence));
        mVar.X();
    }
}
