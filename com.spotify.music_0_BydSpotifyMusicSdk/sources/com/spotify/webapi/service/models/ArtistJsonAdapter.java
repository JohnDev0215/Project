package com.spotify.webapi.service.models;

import com.spotify.connectivity.productstate.RxProductState;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class ArtistJsonAdapter extends f<Artist> {
    private final f<Followers> nullableFollowersAdapter;
    private final f<Integer> nullableIntAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<List<String>> nullableListOfStringAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ArtistJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("followers", "genres", "images", "popularity", "external_urls", "href", "id", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"followers\", \"genres\"…\", \"name\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Followers> fVarF = oVar.f(Followers.class, emptySet, "followers");
        v03.i(fVarF, "moshi.adapter(Followers:… emptySet(), \"followers\")");
        this.nullableFollowersAdapter = fVarF;
        f<List<String>> fVarF2 = oVar.f(zo2.j(List.class, String.class), emptySet, "genres");
        v03.i(fVarF2, "moshi.adapter(Types.newP…ptySet(),\n      \"genres\")");
        this.nullableListOfStringAdapter = fVarF2;
        f<List<Image>> fVarF3 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF3, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF3;
        f<Integer> fVarF4 = oVar.f(Integer.class, emptySet, "popularity");
        v03.i(fVarF4, "moshi.adapter(Int::class…emptySet(), \"popularity\")");
        this.nullableIntAdapter = fVarF4;
        f<Map<String, String>> fVarF5 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF5, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF5;
        f<String> fVarF6 = oVar.f(String.class, emptySet, "href");
        v03.i(fVarF6, "moshi.adapter(String::cl…      emptySet(), \"href\")");
        this.nullableStringAdapter = fVarF6;
    }

    public String toString() {
        return u4.b(28, "GeneratedJsonAdapter(Artist)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Artist fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Followers followersFromJson = null;
        List<Image> listFromJson = null;
        Integer numFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        String strFromJson5 = null;
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
        List<String> listFromJson2 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson5;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    followersFromJson = this.nullableFollowersAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z = true;
                    continue;
                case 1:
                    listFromJson2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z2 = true;
                    continue;
                case 2:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z3 = true;
                    continue;
                case 3:
                    numFromJson = this.nullableIntAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z4 = true;
                    continue;
                case 4:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z5 = true;
                    continue;
                case 5:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson5 = str;
                    z9 = true;
                    continue;
                case 9:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    z10 = true;
                    continue;
            }
            strFromJson5 = str;
        }
        String str2 = strFromJson5;
        jsonReader.q();
        Artist artist = new Artist();
        if (z) {
            artist.followers = followersFromJson;
        }
        if (z2) {
            artist.genres = listFromJson2;
        }
        if (z3) {
            artist.images = listFromJson;
        }
        if (z4) {
            artist.popularity = numFromJson;
        }
        if (z5) {
            artist.external_urls = mapFromJson;
        }
        if (z6) {
            artist.href = strFromJson;
        }
        if (z7) {
            artist.id = strFromJson2;
        }
        if (z8) {
            artist.name = strFromJson3;
        }
        if (z9) {
            artist.uri = strFromJson4;
        }
        if (z10) {
            artist.type = str2;
        }
        return artist;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Artist artist) {
        v03.j(mVar, "writer");
        if (artist == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("followers");
        this.nullableFollowersAdapter.toJson(mVar, artist.followers);
        mVar.J0("genres");
        this.nullableListOfStringAdapter.toJson(mVar, artist.genres);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, artist.images);
        mVar.J0("popularity");
        this.nullableIntAdapter.toJson(mVar, artist.popularity);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, artist.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, artist.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, artist.id);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, artist.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, artist.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, artist.type);
        mVar.X();
    }
}
