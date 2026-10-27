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
public final class PlaylistSimpleJsonAdapter extends f<PlaylistSimple> {
    private final f<Boolean> nullableBooleanAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<PlaylistTracksInformation> nullablePlaylistTracksInformationAdapter;
    private final f<String> nullableStringAdapter;
    private final f<UserPublic> nullableUserPublicAdapter;
    private final JsonReader.b options;

    public PlaylistSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks", "collaborative", "description", "external_urls", "href", "id", "images", "public", "name", "owner", "snapshot_id", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"tracks\", \"collaborat…pshot_id\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<PlaylistTracksInformation> fVarF = oVar.f(PlaylistTracksInformation.class, emptySet, "tracks");
        v03.i(fVarF, "moshi.adapter(PlaylistTr…va, emptySet(), \"tracks\")");
        this.nullablePlaylistTracksInformationAdapter = fVarF;
        f<Boolean> fVarF2 = oVar.f(Boolean.class, emptySet, "collaborative");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…tySet(), \"collaborative\")");
        this.nullableBooleanAdapter = fVarF2;
        f<String> fVarF3 = oVar.f(String.class, emptySet, "description");
        v03.i(fVarF3, "moshi.adapter(String::cl…mptySet(), \"description\")");
        this.nullableStringAdapter = fVarF3;
        f<Map<String, String>> fVarF4 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF4, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF4;
        f<List<Image>> fVarF5 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF5, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF5;
        f<UserPublic> fVarF6 = oVar.f(UserPublic.class, emptySet, "owner");
        v03.i(fVarF6, "moshi.adapter(UserPublic…ava, emptySet(), \"owner\")");
        this.nullableUserPublicAdapter = fVarF6;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(PlaylistSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        PlaylistTracksInformation playlistTracksInformationFromJson = null;
        String strFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
        Boolean boolFromJson = null;
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
        Boolean boolFromJson2 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson7;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    playlistTracksInformationFromJson = this.nullablePlaylistTracksInformationAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z = true;
                    continue;
                case 1:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z2 = true;
                    continue;
                case 2:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z3 = true;
                    continue;
                case 3:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z5 = true;
                    continue;
                case 5:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z6 = true;
                    continue;
                case 6:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z7 = true;
                    continue;
                case 7:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z9 = true;
                    continue;
                case 9:
                    userPublicFromJson = this.nullableUserPublicAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z10 = true;
                    continue;
                case 10:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z11 = true;
                    continue;
                case 11:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson7 = str;
                    z12 = true;
                    continue;
                case 12:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    z13 = true;
                    continue;
            }
            strFromJson7 = str;
        }
        String str2 = strFromJson7;
        jsonReader.q();
        PlaylistSimple playlistSimple = new PlaylistSimple();
        if (z) {
            playlistSimple.tracks = playlistTracksInformationFromJson;
        }
        if (z2) {
            playlistSimple.collaborative = boolFromJson2;
        }
        if (z3) {
            playlistSimple.description = strFromJson;
        }
        if (z4) {
            playlistSimple.external_urls = mapFromJson;
        }
        if (z5) {
            playlistSimple.href = strFromJson2;
        }
        if (z6) {
            playlistSimple.id = strFromJson3;
        }
        if (z7) {
            playlistSimple.images = listFromJson;
        }
        if (z8) {
            playlistSimple.is_public = boolFromJson;
        }
        if (z9) {
            playlistSimple.name = strFromJson4;
        }
        if (z10) {
            playlistSimple.owner = userPublicFromJson;
        }
        if (z11) {
            playlistSimple.snapshot_id = strFromJson5;
        }
        if (z12) {
            playlistSimple.uri = strFromJson6;
        }
        if (z13) {
            playlistSimple.type = str2;
        }
        return playlistSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistSimple playlistSimple) {
        v03.j(mVar, "writer");
        if (playlistSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.nullablePlaylistTracksInformationAdapter.toJson(mVar, playlistSimple.tracks);
        mVar.J0("collaborative");
        this.nullableBooleanAdapter.toJson(mVar, playlistSimple.collaborative);
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.description);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, playlistSimple.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, playlistSimple.images);
        mVar.J0("public");
        this.nullableBooleanAdapter.toJson(mVar, playlistSimple.is_public);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.name);
        mVar.J0("owner");
        this.nullableUserPublicAdapter.toJson(mVar, playlistSimple.owner);
        mVar.J0("snapshot_id");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.snapshot_id);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, playlistSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, playlistSimple.type);
        mVar.X();
    }
}
