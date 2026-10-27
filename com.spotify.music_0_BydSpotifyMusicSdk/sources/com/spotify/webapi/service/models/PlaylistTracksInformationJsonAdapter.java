package com.spotify.webapi.service.models;

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
public final class PlaylistTracksInformationJsonAdapter extends f<PlaylistTracksInformation> {
    private final f<Integer> intAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public PlaylistTracksInformationJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("href", "total");
        v03.i(bVarA, "of(\"href\", \"total\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "total");
        v03.i(fVarF2, "moshi.adapter(Int::class…ava, emptySet(), \"total\")");
        this.intAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(47, "GeneratedJsonAdapter(PlaylistTracksInformation)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistTracksInformation fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        Integer numFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1 && (numFromJson = this.intAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("total", "total", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"total\", …tal\",\n            reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        PlaylistTracksInformation playlistTracksInformation = new PlaylistTracksInformation();
        if (z) {
            playlistTracksInformation.href = strFromJson;
        }
        playlistTracksInformation.total = numFromJson != null ? numFromJson.intValue() : playlistTracksInformation.total;
        return playlistTracksInformation;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistTracksInformation playlistTracksInformation) {
        v03.j(mVar, "writer");
        if (playlistTracksInformation == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, playlistTracksInformation.href);
        mVar.J0("total");
        this.intAdapter.toJson(mVar, Integer.valueOf(playlistTracksInformation.total));
        mVar.X();
    }
}
