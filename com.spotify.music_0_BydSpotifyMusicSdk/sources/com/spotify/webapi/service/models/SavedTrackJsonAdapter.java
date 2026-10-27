package com.spotify.webapi.service.models;

import com.spotify.webapi.service.models.Search;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class SavedTrackJsonAdapter extends f<SavedTrack> {
    private final f<String> nullableStringAdapter;
    private final f<Track> nullableTrackAdapter;
    private final JsonReader.b options;

    public SavedTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("added_at", Search.Type.TRACK);
        v03.i(bVarA, "of(\"added_at\", \"track\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "added_at");
        v03.i(fVarF, "moshi.adapter(String::cl…  emptySet(), \"added_at\")");
        this.nullableStringAdapter = fVarF;
        f<Track> fVarF2 = oVar.f(Track.class, emptySet, Search.Type.TRACK);
        v03.i(fVarF2, "moshi.adapter(Track::cla…     emptySet(), \"track\")");
        this.nullableTrackAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(SavedTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SavedTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        boolean z2 = false;
        Track trackFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                trackFromJson = this.nullableTrackAdapter.fromJson(jsonReader);
                z2 = true;
            }
        }
        jsonReader.q();
        SavedTrack savedTrack = new SavedTrack();
        if (z) {
            savedTrack.added_at = strFromJson;
        }
        if (z2) {
            savedTrack.track = trackFromJson;
        }
        return savedTrack;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SavedTrack savedTrack) {
        v03.j(mVar, "writer");
        if (savedTrack == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("added_at");
        this.nullableStringAdapter.toJson(mVar, savedTrack.added_at);
        mVar.J0(Search.Type.TRACK);
        this.nullableTrackAdapter.toJson(mVar, savedTrack.track);
        mVar.X();
    }
}
