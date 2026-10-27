package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class TracksPagerJsonAdapter extends f<TracksPager> {
    private final f<Pager<Track>> nullablePagerOfTrackAdapter;
    private final JsonReader.b options;

    public TracksPagerJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks");
        v03.i(bVarA, "of(\"tracks\")");
        this.options = bVarA;
        f<Pager<Track>> fVarF = oVar.f(zo2.j(Pager.class, Track.class), EmptySet.c, "tracks");
        v03.i(fVarF, "moshi.adapter(Types.newP…ptySet(),\n      \"tracks\")");
        this.nullablePagerOfTrackAdapter = fVarF;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(TracksPager)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TracksPager fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Pager<Track> pagerFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                pagerFromJson = this.nullablePagerOfTrackAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        TracksPager tracksPager = new TracksPager();
        if (z) {
            tracksPager.tracks = pagerFromJson;
        }
        return tracksPager;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TracksPager tracksPager) {
        v03.j(mVar, "writer");
        if (tracksPager == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.nullablePagerOfTrackAdapter.toJson(mVar, tracksPager.tracks);
        mVar.X();
    }
}
