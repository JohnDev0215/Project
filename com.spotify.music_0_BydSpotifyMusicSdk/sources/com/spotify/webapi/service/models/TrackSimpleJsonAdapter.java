package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class TrackSimpleJsonAdapter extends f<TrackSimple> {
    private final f<Integer> intAdapter;
    private final f<Long> longAdapter;
    private final f<String[]> nullableArrayOfStringAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final f<LinkedTrack> nullableLinkedTrackAdapter;
    private final f<List<ArtistSimple>> nullableListOfArtistSimpleAdapter;
    private final f<List<String>> nullableListOfStringAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public TrackSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("artists", "available_markets", "disc_number", "duration_ms", "explicit", "external_urls", "href", "id", "is_playable", "linked_from", "name", "preview_url", "tags", "track_number", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"artists\", \"available…k_number\", \"uri\", \"type\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, ArtistSimple.class);
        EmptySet emptySet = EmptySet.c;
        f<List<ArtistSimple>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "artists");
        v03.i(fVarF, "moshi.adapter(Types.newP…), emptySet(), \"artists\")");
        this.nullableListOfArtistSimpleAdapter = fVarF;
        f<List<String>> fVarF2 = oVar.f(zo2.j(List.class, String.class), emptySet, "available_markets");
        v03.i(fVarF2, "moshi.adapter(Types.newP…     \"available_markets\")");
        this.nullableListOfStringAdapter = fVarF2;
        f<Integer> fVarF3 = oVar.f(Integer.TYPE, emptySet, "disc_number");
        v03.i(fVarF3, "moshi.adapter(Int::class…t(),\n      \"disc_number\")");
        this.intAdapter = fVarF3;
        f<Long> fVarF4 = oVar.f(Long.TYPE, emptySet, "duration_ms");
        v03.i(fVarF4, "moshi.adapter(Long::clas…t(),\n      \"duration_ms\")");
        this.longAdapter = fVarF4;
        f<Boolean> fVarF5 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF5, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF5;
        f<Map<String, String>> fVarF6 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF6, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF6;
        f<String> fVarF7 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF7, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF7;
        f<LinkedTrack> fVarF8 = oVar.f(LinkedTrack.class, emptySet, "linked_from");
        v03.i(fVarF8, "moshi.adapter(LinkedTrac…mptySet(), \"linked_from\")");
        this.nullableLinkedTrackAdapter = fVarF8;
        f<String[]> fVarF9 = oVar.f(zo2.b(String.class), emptySet, "tags");
        v03.i(fVarF9, "moshi.adapter(Types.arra…ava), emptySet(), \"tags\")");
        this.nullableArrayOfStringAdapter = fVarF9;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(TrackSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public TrackSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<ArtistSimple> listFromJson = null;
        Integer numFromJson = null;
        Long lFromJson = null;
        Boolean boolFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        Boolean boolFromJson2 = null;
        LinkedTrack linkedTrackFromJson = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String[] strArrFromJson = null;
        Integer numFromJson2 = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
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
        List<String> listFromJson2 = null;
        while (jsonReader.hasNext()) {
            Integer num = numFromJson2;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    listFromJson = this.nullableListOfArtistSimpleAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z = true;
                    continue;
                case 1:
                    listFromJson2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z2 = true;
                    continue;
                case 2:
                    numFromJson = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("disc_number", "disc_number", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"disc_num…   \"disc_number\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 3:
                    lFromJson = this.longAdapter.fromJson(jsonReader);
                    if (lFromJson == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("duration_ms", "duration_ms", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"duration…   \"duration_ms\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                case 4:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z3 = true;
                    continue;
                case 5:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z4 = true;
                    continue;
                case 6:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z5 = true;
                    continue;
                case 7:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z6 = true;
                    continue;
                case 8:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z7 = true;
                    continue;
                case 9:
                    linkedTrackFromJson = this.nullableLinkedTrackAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z8 = true;
                    continue;
                case 10:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z9 = true;
                    continue;
                case 11:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z10 = true;
                    continue;
                case 12:
                    strArrFromJson = this.nullableArrayOfStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z11 = true;
                    continue;
                case 13:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("track_number", "track_number", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"track_nu…, \"track_number\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    continue;
                    break;
                case 14:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z12 = true;
                    continue;
                case 15:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    numFromJson2 = num;
                    z13 = true;
                    continue;
            }
            numFromJson2 = num;
        }
        Integer num2 = numFromJson2;
        jsonReader.q();
        TrackSimple trackSimple = new TrackSimple();
        if (z) {
            trackSimple.artists = listFromJson;
        }
        if (z2) {
            trackSimple.available_markets = listFromJson2;
        }
        trackSimple.disc_number = numFromJson != null ? numFromJson.intValue() : trackSimple.disc_number;
        trackSimple.duration_ms = lFromJson != null ? lFromJson.longValue() : trackSimple.duration_ms;
        if (z3) {
            trackSimple.explicit = boolFromJson;
        }
        if (z4) {
            trackSimple.external_urls = mapFromJson;
        }
        if (z5) {
            trackSimple.href = strFromJson;
        }
        if (z6) {
            trackSimple.id = strFromJson2;
        }
        if (z7) {
            trackSimple.is_playable = boolFromJson2;
        }
        if (z8) {
            trackSimple.linked_from = linkedTrackFromJson;
        }
        if (z9) {
            trackSimple.name = strFromJson3;
        }
        if (z10) {
            trackSimple.preview_url = strFromJson4;
        }
        if (z11) {
            trackSimple.tags = strArrFromJson;
        }
        trackSimple.track_number = num2 != null ? num2.intValue() : trackSimple.track_number;
        if (z12) {
            trackSimple.uri = strFromJson5;
        }
        if (z13) {
            trackSimple.type = strFromJson6;
        }
        return trackSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, TrackSimple trackSimple) {
        v03.j(mVar, "writer");
        if (trackSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("artists");
        this.nullableListOfArtistSimpleAdapter.toJson(mVar, trackSimple.artists);
        mVar.J0("available_markets");
        this.nullableListOfStringAdapter.toJson(mVar, trackSimple.available_markets);
        mVar.J0("disc_number");
        u4.s(trackSimple.disc_number, this.intAdapter, mVar, "duration_ms");
        this.longAdapter.toJson(mVar, Long.valueOf(trackSimple.duration_ms));
        mVar.J0("explicit");
        this.nullableBooleanAdapter.toJson(mVar, trackSimple.explicit);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, trackSimple.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, trackSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, trackSimple.id);
        mVar.J0("is_playable");
        this.nullableBooleanAdapter.toJson(mVar, trackSimple.is_playable);
        mVar.J0("linked_from");
        this.nullableLinkedTrackAdapter.toJson(mVar, trackSimple.linked_from);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, trackSimple.name);
        mVar.J0("preview_url");
        this.nullableStringAdapter.toJson(mVar, trackSimple.preview_url);
        mVar.J0("tags");
        this.nullableArrayOfStringAdapter.toJson(mVar, trackSimple.tags);
        mVar.J0("track_number");
        u4.s(trackSimple.track_number, this.intAdapter, mVar, "uri");
        this.nullableStringAdapter.toJson(mVar, trackSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, trackSimple.type);
        mVar.X();
    }
}
