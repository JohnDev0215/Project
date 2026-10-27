package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFeaturesTracksJsonAdapter extends f<AudioFeaturesTracks> {
    private final f<List<AudioFeaturesTrack>> nullableListOfAudioFeaturesTrackAdapter;
    private final JsonReader.b options;

    public AudioFeaturesTracksJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("audio_features");
        v03.i(bVarA, "of(\"audio_features\")");
        this.options = bVarA;
        f<List<AudioFeaturesTrack>> fVarF = oVar.f(zo2.j(List.class, AudioFeaturesTrack.class), EmptySet.c, "audio_features");
        v03.i(fVarF, "moshi.adapter(Types.newP…ySet(), \"audio_features\")");
        this.nullableListOfAudioFeaturesTrackAdapter = fVarF;
    }

    public String toString() {
        return u4.b(41, "GeneratedJsonAdapter(AudioFeaturesTracks)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public AudioFeaturesTracks fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<AudioFeaturesTrack> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfAudioFeaturesTrackAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        AudioFeaturesTracks audioFeaturesTracks = new AudioFeaturesTracks();
        if (z) {
            audioFeaturesTracks.audio_features = listFromJson;
        }
        return audioFeaturesTracks;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, AudioFeaturesTracks audioFeaturesTracks) {
        v03.j(mVar, "writer");
        if (audioFeaturesTracks == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("audio_features");
        this.nullableListOfAudioFeaturesTrackAdapter.toJson(mVar, audioFeaturesTracks.audio_features);
        mVar.X();
    }
}
