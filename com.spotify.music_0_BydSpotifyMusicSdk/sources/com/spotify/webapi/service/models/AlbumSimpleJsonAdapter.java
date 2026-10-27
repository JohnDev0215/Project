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
public final class AlbumSimpleJsonAdapter extends f<AlbumSimple> {
    private final f<List<Image>> nullableListOfImageAdapter;
    private final f<List<String>> nullableListOfStringAdapter;
    private final f<Map<String, String>> nullableMapOfStringStringAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public AlbumSimpleJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("album_type", "available_markets", "external_urls", "href", "id", "images", "name", "uri", RxProductState.Keys.KEY_TYPE);
        v03.i(bVarA, "of(\"album_type\", \"availa…\", \"name\", \"uri\", \"type\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "album_type");
        v03.i(fVarF, "moshi.adapter(String::cl…emptySet(), \"album_type\")");
        this.nullableStringAdapter = fVarF;
        f<List<String>> fVarF2 = oVar.f(zo2.j(List.class, String.class), emptySet, "available_markets");
        v03.i(fVarF2, "moshi.adapter(Types.newP…     \"available_markets\")");
        this.nullableListOfStringAdapter = fVarF2;
        f<Map<String, String>> fVarF3 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "external_urls");
        v03.i(fVarF3, "moshi.adapter(Types.newP…tySet(), \"external_urls\")");
        this.nullableMapOfStringStringAdapter = fVarF3;
        f<List<Image>> fVarF4 = oVar.f(zo2.j(List.class, Image.class), emptySet, "images");
        v03.i(fVarF4, "moshi.adapter(Types.newP…ptySet(),\n      \"images\")");
        this.nullableListOfImageAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(AlbumSimple)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public AlbumSimple fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        Map<String, String> mapFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        List<Image> listFromJson = null;
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
        List<String> listFromJson2 = null;
        while (jsonReader.hasNext()) {
            String str = strFromJson6;
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z = true;
                    continue;
                case 1:
                    listFromJson2 = this.nullableListOfStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z2 = true;
                    continue;
                case 2:
                    mapFromJson = this.nullableMapOfStringStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z3 = true;
                    continue;
                case 3:
                    strFromJson2 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z4 = true;
                    continue;
                case 4:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z5 = true;
                    continue;
                case 5:
                    listFromJson = this.nullableListOfImageAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z6 = true;
                    continue;
                case 6:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z7 = true;
                    continue;
                case 7:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson6 = str;
                    z8 = true;
                    continue;
                case 8:
                    strFromJson6 = this.nullableStringAdapter.fromJson(jsonReader);
                    z9 = true;
                    continue;
            }
            strFromJson6 = str;
        }
        String str2 = strFromJson6;
        jsonReader.q();
        AlbumSimple albumSimple = new AlbumSimple();
        if (z) {
            albumSimple.album_type = strFromJson;
        }
        if (z2) {
            albumSimple.available_markets = listFromJson2;
        }
        if (z3) {
            albumSimple.external_urls = mapFromJson;
        }
        if (z4) {
            albumSimple.href = strFromJson2;
        }
        if (z5) {
            albumSimple.id = strFromJson3;
        }
        if (z6) {
            albumSimple.images = listFromJson;
        }
        if (z7) {
            albumSimple.name = strFromJson4;
        }
        if (z8) {
            albumSimple.uri = strFromJson5;
        }
        if (z9) {
            albumSimple.type = str2;
        }
        return albumSimple;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, AlbumSimple albumSimple) {
        v03.j(mVar, "writer");
        if (albumSimple == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("album_type");
        this.nullableStringAdapter.toJson(mVar, albumSimple.album_type);
        mVar.J0("available_markets");
        this.nullableListOfStringAdapter.toJson(mVar, albumSimple.available_markets);
        mVar.J0("external_urls");
        this.nullableMapOfStringStringAdapter.toJson(mVar, albumSimple.external_urls);
        mVar.J0("href");
        this.nullableStringAdapter.toJson(mVar, albumSimple.href);
        mVar.J0("id");
        this.nullableStringAdapter.toJson(mVar, albumSimple.id);
        mVar.J0("images");
        this.nullableListOfImageAdapter.toJson(mVar, albumSimple.images);
        mVar.J0("name");
        this.nullableStringAdapter.toJson(mVar, albumSimple.name);
        mVar.J0("uri");
        this.nullableStringAdapter.toJson(mVar, albumSimple.uri);
        mVar.J0(RxProductState.Keys.KEY_TYPE);
        this.nullableStringAdapter.toJson(mVar, albumSimple.type);
        mVar.X();
    }
}
