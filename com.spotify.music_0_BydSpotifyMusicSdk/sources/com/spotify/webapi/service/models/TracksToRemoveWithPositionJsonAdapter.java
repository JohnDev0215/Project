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
public final class TracksToRemoveWithPositionJsonAdapter extends f<TracksToRemoveWithPosition> {
    private final f<List<TrackToRemoveWithPosition>> nullableListOfTrackToRemoveWithPositionAdapter;
    private final JsonReader.b options;

    public TracksToRemoveWithPositionJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks");
        v03.i(bVarA, "of(\"tracks\")");
        this.options = bVarA;
        f<List<TrackToRemoveWithPosition>> fVarF = oVar.f(zo2.j(List.class, TrackToRemoveWithPosition.class), EmptySet.c, "tracks");
        v03.i(fVarF, "moshi.adapter(Types.newP…a), emptySet(), \"tracks\")");
        this.nullableListOfTrackToRemoveWithPositionAdapter = fVarF;
    }

    public String toString() {
        return u4.b(48, "GeneratedJsonAdapter(TracksToRemoveWithPosition)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TracksToRemoveWithPosition fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<TrackToRemoveWithPosition> listFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                listFromJson = this.nullableListOfTrackToRemoveWithPositionAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        TracksToRemoveWithPosition tracksToRemoveWithPosition = new TracksToRemoveWithPosition();
        if (z) {
            tracksToRemoveWithPosition.tracks = listFromJson;
        }
        return tracksToRemoveWithPosition;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TracksToRemoveWithPosition tracksToRemoveWithPosition) {
        v03.j(mVar, "writer");
        if (tracksToRemoveWithPosition == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.nullableListOfTrackToRemoveWithPositionAdapter.toJson(mVar, tracksToRemoveWithPosition.tracks);
        mVar.X();
    }
}
