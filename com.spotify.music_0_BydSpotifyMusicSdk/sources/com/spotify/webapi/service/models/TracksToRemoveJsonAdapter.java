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
public final class TracksToRemoveJsonAdapter extends f<TracksToRemove> {
    private final f<List<TrackToRemove>> nullableListOfTrackToRemoveAdapter;
    private final JsonReader.b options;

    public TracksToRemoveJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks");
        v03.i(bVarA, "of(\"tracks\")");
        this.options = bVarA;
        f<List<TrackToRemove>> fVarF = oVar.f(zo2.j(List.class, TrackToRemove.class), EmptySet.c, "tracks");
        v03.i(fVarF, "moshi.adapter(Types.newP…    emptySet(), \"tracks\")");
        this.nullableListOfTrackToRemoveAdapter = fVarF;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(TracksToRemove)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TracksToRemove fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<TrackToRemove> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfTrackToRemoveAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        TracksToRemove tracksToRemove = new TracksToRemove();
        if (z) {
            tracksToRemove.tracks = listFromJson;
        }
        return tracksToRemove;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TracksToRemove tracksToRemove) {
        v03.j(mVar, "writer");
        if (tracksToRemove == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.nullableListOfTrackToRemoveAdapter.toJson(mVar, tracksToRemove.tracks);
        mVar.X();
    }
}
