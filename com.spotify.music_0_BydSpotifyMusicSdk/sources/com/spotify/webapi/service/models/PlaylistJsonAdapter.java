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
public final class PlaylistJsonAdapter extends f<Playlist> {
    private final f<Boolean> nullableBooleanAdapter;
    private final f<Followers> nullableFollowersAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<Pager<PlaylistTrack>> nullablePagerOfPlaylistTrackAdapter;
    private final f<String> nullableStringAdapter;
    private final f<UserPublic> nullableUserPublicAdapter;
    private final JsonReader.b options;

    public PlaylistJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("followers", "tracks", "collaborative", "description", "external_urls", "href", "id", "images", "public", "name", "owner", "snapshot_id", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"followers\", \"tracks\"…pshot_id\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Followers> fVarF = oVar.f(Followers.class, emptySet, "followers");
        v03.i(fVarF, "moshi.adapter(Followers:… emptySet(), \"followers\")");
        this.nullableFollowersAdapter = fVarF;
        f<Pager<PlaylistTrack>> fVarF2 = oVar.f(zo2.j(Pager.class, PlaylistTrack.class), emptySet, "tracks");
        v03.i(fVarF2, "moshi.adapter(Types.newP…    emptySet(), \"tracks\")");
        this.nullablePagerOfPlaylistTrackAdapter = fVarF2;
        f<Boolean> fVarF3 = oVar.f(Boolean.class, emptySet, "collaborative");
        v03.i(fVarF3, "moshi.adapter(Boolean::c…tySet(), \"collaborative\")");
        this.nullableBooleanAdapter = fVarF3;
        f<String> fVarF4 = oVar.f(String.class, emptySet, "description");
        v03.i(fVarF4, "moshi.adapter(String::cl…mptySet(), \"description\")");
        this.nullableStringAdapter = fVarF4;
        f<Map<String, String>> fVarF5 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF5, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF5;
        f<List<Image>> fVarF6 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF6, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF6;
        f<UserPublic> fVarF7 = oVar.f(UserPublic.class, emptySet, "owner");
        v03.i(fVarF7, "moshi.adapter(UserPublic…ava, emptySet(), \"owner\")");
        this.nullableUserPublicAdapter = fVarF7;
    }

    public String toString() {
        return u4.b(30, "GeneratedJsonAdapter(Playlist)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public Playlist fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Followers followersFromJson = null;
        Boolean boolFromJson = null;
        String strFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson4 = null;
        UserPublic userPublicFromJson = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        String strFromJson7 = null;
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
        Pager<PlaylistTrack> pagerFromJson = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson7;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    followersFromJson = this.nullableFollowersAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z = true;
                    continue;
                case 1:
                    pagerFromJson = this.nullablePagerOfPlaylistTrackAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z2 = true;
                    continue;
                case 2:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z4 = true;
                    continue;
                case 4:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z5 = true;
                    continue;
                case 5:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z7 = true;
                    continue;
                case 7:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z8 = true;
                    continue;
                case 8:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z9 = true;
                    continue;
                case 9:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z10 = true;
                    continue;
                case 10:
                    userPublicFromJson = this.nullableUserPublicAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z11 = true;
                    continue;
                case 11:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z12 = true;
                    continue;
                case 12:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z13 = true;
                    continue;
                case 13:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    z14 = true;
                    continue;
            }
            strFromJson7 = str;
        }
        String str2 = strFromJson7;
        jsonReader.q();
        Playlist playlist = new Playlist();
        if (z) {
            playlist.followers = followersFromJson;
        }
        if (z2) {
            playlist.tracks = pagerFromJson;
        }
        if (z3) {
            playlist.collaborative = boolFromJson;
        }
        if (z4) {
            playlist.description = strFromJson;
        }
        if (z5) {
            playlist.external_urls = mapFromJson;
        }
        if (z6) {
            playlist.href = strFromJson2;
        }
        if (z7) {
            playlist.id = strFromJson3;
        }
        if (z8) {
            playlist.images = listFromJson;
        }
        if (z9) {
            playlist.is_public = boolFromJson2;
        }
        if (z10) {
            playlist.name = strFromJson4;
        }
        if (z11) {
            playlist.owner = userPublicFromJson;
        }
        if (z12) {
            playlist.snapshot_id = strFromJson5;
        }
        if (z13) {
            playlist.uri = strFromJson6;
        }
        if (z14) {
            playlist.type = str2;
        }
        return playlist;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, Playlist playlist) {
        v03.j(mVar, "writer");
        if (playlist == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("followers");
        this.nullableFollowersAdapter.toJson(mVar, playlist.followers);
        mVar.J0("tracks");
        this.nullablePagerOfPlaylistTrackAdapter.toJson(mVar, playlist.tracks);
        mVar.J0("collaborative");
        this.nullableBooleanAdapter.toJson(mVar, playlist.collaborative);
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, playlist.description);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, playlist.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, playlist.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, playlist.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, playlist.images);
        mVar.J0("public");
        this.nullableBooleanAdapter.toJson(mVar, playlist.is_public);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, playlist.name);
        mVar.J0("owner");
        this.nullableUserPublicAdapter.toJson(mVar, playlist.owner);
        mVar.J0("snapshot_id");
        this.nullableStringAdapter.toJson(mVar, playlist.snapshot_id);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, playlist.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, playlist.type);
        mVar.X();
    }
}
