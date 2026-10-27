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
public final class PlaylistBaseJsonAdapter extends f<PlaylistBase> {
    private final f<Boolean> nullableBooleanAdapter;
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final f<UserPublic> nullableUserPublicAdapter;
    private final JsonReader.b options;

    public PlaylistBaseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("collaborative", "description", "external_urls", "href", "id", "images", "public", "name", "owner", "snapshot_id", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"collaborative\", \"des…id\", \"uri\",\n      \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Boolean> fVarF = oVar.f(Boolean.class, emptySet, "collaborative");
        v03.i(fVarF, "moshi.adapter(Boolean::c…tySet(), \"collaborative\")");
        this.nullableBooleanAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "description");
        v03.i(fVarF2, "moshi.adapter(String::cl…mptySet(), \"description\")");
        this.nullableStringAdapter = fVarF2;
        f<Map<String, String>> fVarF3 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF3, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF3;
        f<List<Image>> fVarF4 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF4;
        f<UserPublic> fVarF5 = oVar.f(UserPublic.class, emptySet, "owner");
        v03.i(fVarF5, "moshi.adapter(UserPublic…ava, emptySet(), \"owner\")");
        this.nullableUserPublicAdapter = fVarF5;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(PlaylistBase)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public PlaylistBase fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson = null;
        String strFromJson2 = null;
        List<Image> listFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson3 = null;
        UserPublic userPublicFromJson = null;
        String strFromJson4 = null;
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
        String strFromJson7 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson6;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z = true;
                    continue;
                case 1:
                    strFromJson7 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z2 = true;
                    continue;
                case 2:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z5 = true;
                    continue;
                case 5:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z6 = true;
                    continue;
                case 6:
                    boolFromJson2 = this.nullableBooleanAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z8 = true;
                    continue;
                case 8:
                    userPublicFromJson = this.nullableUserPublicAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z9 = true;
                    continue;
                case 9:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z10 = true;
                    continue;
                case 10:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z11 = true;
                    continue;
                case 11:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    z12 = true;
                    continue;
            }
            strFromJson6 = str;
        }
        String str2 = strFromJson6;
        jsonReader.q();
        PlaylistBase playlistBase = new PlaylistBase();
        if (z) {
            playlistBase.collaborative = boolFromJson;
        }
        if (z2) {
            playlistBase.description = strFromJson7;
        }
        if (z3) {
            playlistBase.external_urls = mapFromJson;
        }
        if (z4) {
            playlistBase.href = strFromJson;
        }
        if (z5) {
            playlistBase.id = strFromJson2;
        }
        if (z6) {
            playlistBase.images = listFromJson;
        }
        if (z7) {
            playlistBase.is_public = boolFromJson2;
        }
        if (z8) {
            playlistBase.name = strFromJson3;
        }
        if (z9) {
            playlistBase.owner = userPublicFromJson;
        }
        if (z10) {
            playlistBase.snapshot_id = strFromJson4;
        }
        if (z11) {
            playlistBase.uri = strFromJson5;
        }
        if (z12) {
            playlistBase.type = str2;
        }
        return playlistBase;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, PlaylistBase playlistBase) {
        v03.j(mVar, "writer");
        if (playlistBase == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("collaborative");
        this.nullableBooleanAdapter.toJson(mVar, playlistBase.collaborative);
        mVar.J0("description");
        this.nullableStringAdapter.toJson(mVar, playlistBase.description);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, playlistBase.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, playlistBase.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, playlistBase.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, playlistBase.images);
        mVar.J0("public");
        this.nullableBooleanAdapter.toJson(mVar, playlistBase.is_public);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, playlistBase.name);
        mVar.J0("owner");
        this.nullableUserPublicAdapter.toJson(mVar, playlistBase.owner);
        mVar.J0("snapshot_id");
        this.nullableStringAdapter.toJson(mVar, playlistBase.snapshot_id);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, playlistBase.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, playlistBase.type);
        mVar.X();
    }
}
