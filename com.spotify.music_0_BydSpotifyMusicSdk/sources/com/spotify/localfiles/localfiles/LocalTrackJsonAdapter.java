package com.spotify.localfiles.localfiles;

import com.spotify.webapi.service.models.Search;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class LocalTrackJsonAdapter extends f<LocalTrack> {
    private final f<Boolean> booleanAdapter;
    private final f<List<LocalArtist>> nullableListOfLocalArtistAdapter;
    private final f<LocalAlbum> nullableLocalAlbumAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public LocalTrackJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a(Search.Type.LINK, "rowId", "name", Search.Type.ALBUM, "artists", "isExplicit", "contentUri", "is19PlusOnly", "isCurated");
        v03.i(bVarA, "of(\"link\", \"rowId\", \"nam…19PlusOnly\", \"isCurated\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.stringAdapter = fVarF;
        f<LocalAlbum> fVarF2 = oVar.f(LocalAlbum.class, emptySet, Search.Type.ALBUM);
        v03.i(fVarF2, "moshi.adapter(LocalAlbum…ava, emptySet(), \"album\")");
        this.nullableLocalAlbumAdapter = fVarF2;
        f<List<LocalArtist>> fVarF3 = oVar.f(zo2.j(List.class, LocalArtist.class), emptySet, "artists");
        v03.i(fVarF3, "moshi.adapter(Types.newP…   emptySet(), \"artists\")");
        this.nullableListOfLocalArtistAdapter = fVarF3;
        f<Boolean> fVarF4 = oVar.f(Boolean.TYPE, emptySet, "isExplicit");
        v03.i(fVarF4, "moshi.adapter(Boolean::c…et(),\n      \"isExplicit\")");
        this.booleanAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(LocalTrack)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalTrack fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson = null;
        Boolean boolFromJson3 = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        LocalAlbum localAlbumFromJson = null;
        List<LocalArtist> listFromJson = null;
        String strFromJson4 = null;
        while (true) {
            List<LocalArtist> list = listFromJson;
            LocalAlbum localAlbum = localAlbumFromJson;
            Boolean bool = boolFromJson3;
            Boolean bool2 = boolFromJson2;
            if (!jsonReader.hasNext()) {
                jsonReader.q();
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionO = ds2.o("uri", Search.Type.LINK, jsonReader);
                    v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"link\", reader)");
                    throw jsonDataExceptionO;
                }
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionO2 = ds2.o("rowId", "rowId", jsonReader);
                    v03.i(jsonDataExceptionO2, "missingProperty(\"rowId\", \"rowId\", reader)");
                    throw jsonDataExceptionO2;
                }
                if (strFromJson3 == null) {
                    JsonDataException jsonDataExceptionO3 = ds2.o("name", "name", jsonReader);
                    v03.i(jsonDataExceptionO3, "missingProperty(\"name\", \"name\", reader)");
                    throw jsonDataExceptionO3;
                }
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionO4 = ds2.o("isExplicit", "isExplicit", jsonReader);
                    v03.i(jsonDataExceptionO4, "missingProperty(\"isExpli…t\", \"isExplicit\", reader)");
                    throw jsonDataExceptionO4;
                }
                boolean zBooleanValue = boolFromJson.booleanValue();
                if (strFromJson4 == null) {
                    JsonDataException jsonDataExceptionO5 = ds2.o("contentUri", "contentUri", jsonReader);
                    v03.i(jsonDataExceptionO5, "missingProperty(\"content…i\", \"contentUri\", reader)");
                    throw jsonDataExceptionO5;
                }
                if (bool2 == null) {
                    JsonDataException jsonDataExceptionO6 = ds2.o("is19PlusOnly", "is19PlusOnly", jsonReader);
                    v03.i(jsonDataExceptionO6, "missingProperty(\"is19Plu…nly\",\n            reader)");
                    throw jsonDataExceptionO6;
                }
                boolean zBooleanValue2 = bool2.booleanValue();
                if (bool != null) {
                    return new LocalTrack(strFromJson, strFromJson2, strFromJson3, localAlbum, list, zBooleanValue, strFromJson4, zBooleanValue2, bool.booleanValue());
                }
                JsonDataException jsonDataExceptionO7 = ds2.o("isCurated", "isCurated", jsonReader);
                v03.i(jsonDataExceptionO7, "missingProperty(\"isCurated\", \"isCurated\", reader)");
                throw jsonDataExceptionO7;
            }
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 0:
                    strFromJson = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("uri", Search.Type.LINK, jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"link\",\n            reader)");
                        throw jsonDataExceptionW;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 1:
                    strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("rowId", "rowId", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"rowId\", …wId\",\n            reader)");
                        throw jsonDataExceptionW2;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 2:
                    strFromJson3 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson3 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("name", "name", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"name\", \"name\",\n            reader)");
                        throw jsonDataExceptionW3;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 3:
                    localAlbumFromJson = this.nullableLocalAlbumAdapter.fromJson(jsonReader);
                    listFromJson = list;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 4:
                    listFromJson = this.nullableListOfLocalArtistAdapter.fromJson(jsonReader);
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 5:
                    boolFromJson = this.booleanAdapter.fromJson(jsonReader);
                    if (boolFromJson == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("isExplicit", "isExplicit", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"isExplic…    \"isExplicit\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 6:
                    strFromJson4 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson4 == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("contentUri", "contentUri", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"contentU…    \"contentUri\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
                case 7:
                    boolFromJson2 = this.booleanAdapter.fromJson(jsonReader);
                    if (boolFromJson2 == null) {
                        JsonDataException jsonDataExceptionW6 = ds2.w("is19PlusOnly", "is19PlusOnly", jsonReader);
                        v03.i(jsonDataExceptionW6, "unexpectedNull(\"is19Plus…, \"is19PlusOnly\", reader)");
                        throw jsonDataExceptionW6;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    break;
                    break;
                case 8:
                    boolFromJson3 = this.booleanAdapter.fromJson(jsonReader);
                    if (boolFromJson3 == null) {
                        JsonDataException jsonDataExceptionW7 = ds2.w("isCurated", "isCurated", jsonReader);
                        v03.i(jsonDataExceptionW7, "unexpectedNull(\"isCurate…     \"isCurated\", reader)");
                        throw jsonDataExceptionW7;
                    }
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson2 = bool2;
                    break;
                    break;
                default:
                    listFromJson = list;
                    localAlbumFromJson = localAlbum;
                    boolFromJson3 = bool;
                    boolFromJson2 = bool2;
                    break;
            }
        }
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalTrack localTrack) {
        v03.j(mVar, "writer");
        if (localTrack == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0(Search.Type.LINK);
        this.stringAdapter.toJson(mVar, localTrack.getUri());
        mVar.J0("rowId");
        this.stringAdapter.toJson(mVar, localTrack.getRowId());
        mVar.J0("name");
        this.stringAdapter.toJson(mVar, localTrack.getName());
        mVar.J0(Search.Type.ALBUM);
        this.nullableLocalAlbumAdapter.toJson(mVar, localTrack.getAlbum());
        mVar.J0("artists");
        this.nullableListOfLocalArtistAdapter.toJson(mVar, localTrack.getArtists());
        mVar.J0("isExplicit");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(localTrack.isExplicit()));
        mVar.J0("contentUri");
        this.stringAdapter.toJson(mVar, localTrack.getContentUri());
        mVar.J0("is19PlusOnly");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(localTrack.is19PlusOnly()));
        mVar.J0("isCurated");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(localTrack.isCurated()));
        mVar.X();
    }
}
