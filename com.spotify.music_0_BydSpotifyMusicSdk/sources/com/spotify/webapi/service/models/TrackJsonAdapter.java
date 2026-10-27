package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.spotify.webapi.service.models.Search;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class TrackJsonAdapter extends f<Track> {
    private final f<Integer> intAdapter;
    private final f<Long> longAdapter;
    private final f<AlbumSimple> nullableAlbumSimpleAdapter;
    private final f<String[]> nullableArrayOfStringAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final f<Integer> nullableIntAdapter;
    private final f<LinkedTrack> nullableLinkedTrackAdapter;
    private final f<List<ArtistSimple>> nullableListOfArtistSimpleAdapter;
    private final f<List<String>> nullableListOfStringAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public TrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a(Search.Type.ALBUM, "external_ids", "popularity", "artists", "available_markets", "disc_number", "duration_ms", "explicit", "external_urls", "href", "id", "is_playable", "linked_from", "name", "preview_url", "tags", "track_number", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"album\", \"external_id…k_number\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<AlbumSimple> fVarF = oVar.f(AlbumSimple.class, emptySet, Search.Type.ALBUM);
        v03.i(fVarF, "moshi.adapter(AlbumSimpl…ava, emptySet(), \"album\")");
        this.nullableAlbumSimpleAdapter = fVarF;
        f<Map<String, String>> fVarF2 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_ids");
        v03.i(fVarF2, "moshi.adapter(Types.newP…ptySet(), \"external_ids\")");
        this.nullableMapOfStringStringAdapter = fVarF2;
        f<Integer> fVarF3 = oVar.f(Integer.class, emptySet, "popularity");
        v03.i(fVarF3, "moshi.adapter(Int::class…emptySet(), \"popularity\")");
        this.nullableIntAdapter = fVarF3;
        f<List<ArtistSimple>> fVarF4 = oVar.f(zo2.j(List.class, ArtistSimple.class), emptySet, "artists");
        v03.i(fVarF4, "moshi.adapter(Types.newP…), emptySet(), \"artists\")");
        this.nullableListOfArtistSimpleAdapter = fVarF4;
        f<List<String>> fVarF5 = oVar.f(zo2.j(List.class, String.class), emptySet, "available_markets");
        v03.i(fVarF5, "moshi.adapter(Types.newP…     \"available_markets\")");
        this.nullableListOfStringAdapter = fVarF5;
        f<Integer> fVarF6 = oVar.f(Integer.TYPE, emptySet, "disc_number");
        v03.i(fVarF6, "moshi.adapter(Int::class…t(),\n      \"disc_number\")");
        this.intAdapter = fVarF6;
        f<Long> fVarF7 = oVar.f(Long.TYPE, emptySet, "duration_ms");
        v03.i(fVarF7, "moshi.adapter(Long::clas…t(),\n      \"duration_ms\")");
        this.longAdapter = fVarF7;
        f<Boolean> fVarF8 = oVar.f(Boolean.class, emptySet, "explicit");
        v03.i(fVarF8, "moshi.adapter(Boolean::c…, emptySet(), \"explicit\")");
        this.nullableBooleanAdapter = fVarF8;
        f<String> fVarF9 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF9, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF9;
        f<LinkedTrack> fVarF10 = oVar.f(LinkedTrack.class, emptySet, "linked_from");
        v03.i(fVarF10, "moshi.adapter(LinkedTrac…mptySet(), \"linked_from\")");
        this.nullableLinkedTrackAdapter = fVarF10;
        f<String[]> fVarF11 = oVar.f(zo2.b(String.class), emptySet, "tags");
        v03.i(fVarF11, "moshi.adapter(Types.arra…ava), emptySet(), \"tags\")");
        this.nullableArrayOfStringAdapter = fVarF11;
    }

    public String toString() {
        return u4.b(27, "GeneratedJsonAdapter(Track)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Track fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        AlbumSimple albumSimpleFromJson = null;
        Integer numFromJson = null;
        List<ArtistSimple> listFromJson = null;
        List<String> listFromJson2 = null;
        Integer numFromJson2 = null;
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
        Integer numFromJson3 = null;
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
        boolean z14 = false;
        boolean z15 = false;
        boolean z16 = false;
        Map<String, String> mapFromJson2 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson3;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    albumSimpleFromJson = this.nullableAlbumSimpleAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z = true;
                    continue;
                case 1:
                    mapFromJson2 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z2 = true;
                    continue;
                case 2:
                    numFromJson = this.nullableIntAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z3 = true;
                    continue;
                case 3:
                    listFromJson = this.nullableListOfArtistSimpleAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z4 = true;
                    continue;
                case 4:
                    listFromJson2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z5 = true;
                    continue;
                case 5:
                    numFromJson2 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson2 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("disc_number", "disc_number", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"disc_num…   \"disc_number\", reader)");
                        throw jsonDataExceptionW;
                    }
                    break;
                case 6:
                    lFromJson = this.longAdapter.fromJson(jsonReader);
                    if (lFromJson == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("duration_ms", "duration_ms", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"duration…   \"duration_ms\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    break;
                case 7:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z6 = true;
                    continue;
                case 8:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z7 = true;
                    continue;
                case 9:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z8 = true;
                    continue;
                case 10:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z9 = true;
                    continue;
                case 11:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z10 = true;
                    continue;
                case 12:
                    linkedTrackFromJson = this.nullableLinkedTrackAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z11 = true;
                    continue;
                case 13:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    z12 = true;
                    continue;
                case 14:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z13 = true;
                    continue;
                case 15:
                    strArrFromJson = this.nullableArrayOfStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z14 = true;
                    continue;
                case 16:
                    numFromJson3 = this.intAdapter.fromJson(jsonReader);
                    if (numFromJson3 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("track_number", "track_number", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"track_nu…, \"track_number\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    break;
                case 17:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z15 = true;
                    continue;
                case 18:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str;
                    z16 = true;
                    continue;
            }
            strFromJson3 = str;
        }
        String str2 = strFromJson3;
        jsonReader.q();
        Track track = new Track();
        if (z) {
            track.album = albumSimpleFromJson;
        }
        if (z2) {
            track.external_ids = mapFromJson2;
        }
        if (z3) {
            track.popularity = numFromJson;
        }
        if (z4) {
            track.artists = listFromJson;
        }
        if (z5) {
            track.available_markets = listFromJson2;
        }
        track.disc_number = numFromJson2 != null ? numFromJson2.intValue() : track.disc_number;
        track.duration_ms = lFromJson != null ? lFromJson.longValue() : track.duration_ms;
        if (z6) {
            track.explicit = boolFromJson;
        }
        if (z7) {
            track.external_urls = mapFromJson;
        }
        if (z8) {
            track.href = strFromJson;
        }
        if (z9) {
            track.id = strFromJson2;
        }
        if (z10) {
            track.is_playable = boolFromJson2;
        }
        if (z11) {
            track.linked_from = linkedTrackFromJson;
        }
        if (z12) {
            track.name = str2;
        }
        if (z13) {
            track.preview_url = strFromJson4;
        }
        if (z14) {
            track.tags = strArrFromJson;
        }
        track.track_number = numFromJson3 != null ? numFromJson3.intValue() : track.track_number;
        if (z15) {
            track.uri = strFromJson5;
        }
        if (z16) {
            track.type = strFromJson6;
        }
        return track;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Track track) {
        v03.j(mVar, "writer");
        if (track == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0(Search.Type.ALBUM);
        this.nullableAlbumSimpleAdapter.toJson(mVar, track.album);
        mVar.J0("external_ids");
        this.nullableMapOfStringStringAdapter.toJson(mVar, track.external_ids);
        mVar.J0("popularity");
        this.nullableIntAdapter.toJson(mVar, track.popularity);
        mVar.J0("artists");
        this.nullableListOfArtistSimpleAdapter.toJson(mVar, track.artists);
        mVar.J0("available_markets");
        this.nullableListOfStringAdapter.toJson(mVar, track.available_markets);
        mVar.J0("disc_number");
        u4.s(track.disc_number, this.intAdapter, mVar, "duration_ms");
        this.longAdapter.toJson(mVar, Long.valueOf(track.duration_ms));
        mVar.J0("explicit");
        this.nullableBooleanAdapter.toJson(mVar, track.explicit);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, track.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, track.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, track.id);
        mVar.J0("is_playable");
        this.nullableBooleanAdapter.toJson(mVar, track.is_playable);
        mVar.J0("linked_from");
        this.nullableLinkedTrackAdapter.toJson(mVar, track.linked_from);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, track.name);
        mVar.J0("preview_url");
        this.nullableStringAdapter.toJson(mVar, track.preview_url);
        mVar.J0("tags");
        this.nullableArrayOfStringAdapter.toJson(mVar, track.tags);
        mVar.J0("track_number");
        u4.s(track.track_number, this.intAdapter, mVar, "uri");
        this.nullableStringAdapter.toJson(mVar, track.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, track.type);
        mVar.X();
    }
}
