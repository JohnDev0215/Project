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
public final class TracksJsonAdapter extends f<Tracks> {
    private final f<List<Track>> nullableListOfTrackAdapter;
    private final JsonReader.b options;

    public TracksJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks");
        v03.i(bVarA, "of(\"tracks\")");
        this.options = bVarA;
        f<List<Track>> fVarF = oVar.f(zo2.j(List.class, Track.class), EmptySet.c, "tracks");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"tracks\")");
        this.nullableListOfTrackAdapter = fVarF;
    }

    public String toString() {
        return u4.b(28, "GeneratedJsonAdapter(Tracks)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Tracks fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<Track> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfTrackAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        Tracks tracks = new Tracks();
        if (z) {
            tracks.tracks = listFromJson;
        }
        return tracks;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Tracks tracks) {
        v03.j(mVar, "writer");
        if (tracks == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.nullableListOfTrackAdapter.toJson(mVar, tracks.tracks);
        mVar.X();
    }
}
