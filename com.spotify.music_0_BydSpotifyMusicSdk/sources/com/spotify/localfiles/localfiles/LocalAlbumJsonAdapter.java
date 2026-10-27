package com.spotify.localfiles.localfiles;

import com.spotify.webapi.service.models.Search;
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
public final class LocalAlbumJsonAdapter extends f<LocalAlbum> {
    private final f<LocalCovers> nullableLocalCoversAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public LocalAlbumJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a(Search.Type.LINK, "name", "covers");
        v03.i(bVarA, "of(\"link\", \"name\", \"covers\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.stringAdapter = fVarF;
        f<LocalCovers> fVarF2 = oVar.f(LocalCovers.class, emptySet, "covers");
        v03.i(fVarF2, "moshi.adapter(LocalCover…va, emptySet(), \"covers\")");
        this.nullableLocalCoversAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(32, "GeneratedJsonAdapter(LocalAlbum)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalAlbum fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        LocalCovers localCoversFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("uri", Search.Type.LINK, jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"link\",\n            reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("name", "name", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"name\", \"name\",\n            reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                localCoversFromJson = this.nullableLocalCoversAdapter.fromJson(jsonReader);
            }
        }
        jsonReader.q();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("uri", Search.Type.LINK, jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"link\", reader)");
            throw jsonDataExceptionO;
        }
        if (strFromJson2 != null) {
            return new LocalAlbum(strFromJson, strFromJson2, localCoversFromJson);
        }
        JsonDataException jsonDataExceptionO2 = ds2.o("name", "name", jsonReader);
        v03.i(jsonDataExceptionO2, "missingProperty(\"name\", \"name\", reader)");
        throw jsonDataExceptionO2;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalAlbum localAlbum) {
        v03.j(mVar, "writer");
        if (localAlbum == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0(Search.Type.LINK);
        this.stringAdapter.toJson(mVar, localAlbum.getUri());
        mVar.J0("name");
        this.stringAdapter.toJson(mVar, localAlbum.getName());
        mVar.J0("covers");
        this.nullableLocalCoversAdapter.toJson(mVar, localAlbum.getCovers());
        mVar.X();
    }
}
