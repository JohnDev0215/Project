package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class AlbumJsonAdapter extends f<Album> {
    private final f<Integer> nullableIntAdapter;
    private final f<List<AlbumCopyright>> nullableListOfAlbumCopyrightAdapter;
    private final f<List<ArtistSimple>> nullableListOfArtistSimpleAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<List<String>> nullableListOfStringAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<Pager<TrackSimple>> nullablePagerOfTrackSimpleAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public AlbumJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("artists", "copyrights", "external_ids", "genres", "popularity", "release_date", "release_date_precision", "tracks", "album_type", "available_markets", "external_urls", "href", "id", "images", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"artists\", \"copyright…me\", \"uri\",\n      \"type\")");
        this.options = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, ArtistSimple.class);
        EmptySet emptySet = EmptySet.c;
        f<List<ArtistSimple>> fVarF = oVar.f(parameterizedTypeJ, emptySet, "artists");
        v03.i(fVarF, "moshi.adapter(Types.newP…), emptySet(), \"artists\")");
        this.nullableListOfArtistSimpleAdapter = fVarF;
        f<List<AlbumCopyright>> fVarF2 = oVar.f(zo2.j(List.class, AlbumCopyright.class), emptySet, "copyrights");
        v03.i(fVarF2, "moshi.adapter(Types.newP…emptySet(), \"copyrights\")");
        this.nullableListOfAlbumCopyrightAdapter = fVarF2;
        f<Map<String, String>> fVarF3 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_ids");
        v03.i(fVarF3, "moshi.adapter(Types.newP…ptySet(), \"external_ids\")");
        this.nullableMapOfStringStringAdapter = fVarF3;
        f<List<String>> fVarF4 = oVar.f(zo2.j(List.class, String.class), emptySet, "genres");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"genres\")");
        this.nullableListOfStringAdapter = fVarF4;
        f<Integer> fVarF5 = oVar.f(Integer.class, emptySet, "popularity");
        v03.i(fVarF5, "moshi.adapter(Int::class…emptySet(), \"popularity\")");
        this.nullableIntAdapter = fVarF5;
        f<String> fVarF6 = oVar.f(String.class, emptySet, "release_date");
        v03.i(fVarF6, "moshi.adapter(String::cl…ptySet(), \"release_date\")");
        this.nullableStringAdapter = fVarF6;
        f<Pager<TrackSimple>> fVarF7 = oVar.f(zo2.j(Pager.class, TrackSimple.class), emptySet, "tracks");
        v03.i(fVarF7, "moshi.adapter(Types.newP…    emptySet(), \"tracks\")");
        this.nullablePagerOfTrackSimpleAdapter = fVarF7;
        f<List<Image>> fVarF8 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF8, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF8;
    }

    public String toString() {
        return u4.b(27, "GeneratedJsonAdapter(Album)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Album fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List<ArtistSimple> listFromJson = null;
        Map<String, String> mapFromJson = null;
        List<String> listFromJson2 = null;
        Integer numFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        Pager<TrackSimple> pagerFromJson = null;
        String strFromJson3 = null;
        List<String> listFromJson3 = null;
        Map<String, String> mapFromJson2 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
        List<Image> listFromJson4 = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
        String strFromJson8 = null;
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
        boolean z17 = false;
        List<AlbumCopyright> listFromJson5 = null;
        while (jsonReader.hasNext()) {
            List<Image> list = listFromJson4;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    listFromJson = this.nullableListOfArtistSimpleAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z = true;
                    continue;
                case 1:
                    listFromJson5 = this.nullableListOfAlbumCopyrightAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z2 = true;
                    continue;
                case 2:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z3 = true;
                    continue;
                case 3:
                    listFromJson2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z4 = true;
                    continue;
                case 4:
                    numFromJson = this.nullableIntAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z5 = true;
                    continue;
                case 5:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z7 = true;
                    continue;
                case 7:
                    pagerFromJson = this.nullablePagerOfTrackSimpleAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z9 = true;
                    continue;
                case 9:
                    listFromJson3 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z10 = true;
                    continue;
                case 10:
                    mapFromJson2 = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z11 = true;
                    continue;
                case 11:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z12 = true;
                    continue;
                case 12:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z13 = true;
                    continue;
                case 13:
                    listFromJson4 = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    z14 = true;
                    continue;
                case 14:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z15 = true;
                    continue;
                case 15:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z16 = true;
                    continue;
                case 16:
                    strFromJson8 = this.nullableStringAdapter.fromJson(jsonReader);
                    listFromJson4 = list;
                    z17 = true;
                    continue;
            }
            listFromJson4 = list;
        }
        List<Image> list2 = listFromJson4;
        jsonReader.q();
        Album album = new Album();
        if (z) {
            album.artists = listFromJson;
        }
        if (z2) {
            album.copyrights = listFromJson5;
        }
        if (z3) {
            album.external_ids = mapFromJson;
        }
        if (z4) {
            album.genres = listFromJson2;
        }
        if (z5) {
            album.popularity = numFromJson;
        }
        if (z6) {
            album.release_date = strFromJson;
        }
        if (z7) {
            album.release_date_precision = strFromJson2;
        }
        if (z8) {
            album.tracks = pagerFromJson;
        }
        if (z9) {
            album.album_type = strFromJson3;
        }
        if (z10) {
            album.available_markets = listFromJson3;
        }
        if (z11) {
            album.external_urls = mapFromJson2;
        }
        if (z12) {
            album.href = strFromJson4;
        }
        if (z13) {
            album.id = strFromJson5;
        }
        if (z14) {
            album.images = list2;
        }
        if (z15) {
            album.name = strFromJson6;
        }
        if (z16) {
            album.uri = strFromJson7;
        }
        if (z17) {
            album.type = strFromJson8;
        }
        return album;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Album album) {
        v03.j(mVar, "writer");
        if (album == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("artists");
        this.nullableListOfArtistSimpleAdapter.toJson(mVar, album.artists);
        mVar.J0("copyrights");
        this.nullableListOfAlbumCopyrightAdapter.toJson(mVar, album.copyrights);
        mVar.J0("external_ids");
        this.nullableMapOfStringStringAdapter.toJson(mVar, album.external_ids);
        mVar.J0("genres");
        this.nullableListOfStringAdapter.toJson(mVar, album.genres);
        mVar.J0("popularity");
        this.nullableIntAdapter.toJson(mVar, album.popularity);
        mVar.J0("release_date");
        this.nullableStringAdapter.toJson(mVar, album.release_date);
        mVar.J0("release_date_precision");
        this.nullableStringAdapter.toJson(mVar, album.release_date_precision);
        mVar.J0("tracks");
        this.nullablePagerOfTrackSimpleAdapter.toJson(mVar, album.tracks);
        mVar.J0("album_type");
        this.nullableStringAdapter.toJson(mVar, album.album_type);
        mVar.J0("available_markets");
        this.nullableListOfStringAdapter.toJson(mVar, album.available_markets);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, album.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, album.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, album.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, album.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, album.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, album.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, album.type);
        mVar.X();
    }
}
