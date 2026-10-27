package com.spotify.localfiles.localfiles;

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
public final class LocalSourceJsonAdapter extends f<LocalSource> {
    private final f<Boolean> booleanAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public LocalSourceJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("id", "path", "name", "enabled", "found");
        v03.i(bVarA, "of(\"id\", \"path\", \"name\", \"enabled\",\n      \"found\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "id");
        v03.i(fVarF, "moshi.adapter(String::cl…, emptySet(),\n      \"id\")");
        this.stringAdapter = fVarF;
        f<Boolean> fVarF2 = oVar.f(Boolean.TYPE, emptySet, "enabled");
        v03.i(fVarF2, "moshi.adapter(Boolean::c…tySet(),\n      \"enabled\")");
        this.booleanAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(LocalSource)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalSource fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        Boolean boolFromJson2 = null;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("id", "id", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"id\", \"id\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("path", "path", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"path\", \"path\",\n            reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                strFromJson3 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson3 == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("name", "name", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"name\", \"name\",\n            reader)");
                    throw jsonDataExceptionW3;
                }
            } else if (iM0 == 3) {
                boolFromJson = this.booleanAdapter.fromJson(jsonReader);
                if (boolFromJson == null) {
                    JsonDataException jsonDataExceptionW4 = ds2.w("enabled", "enabled", jsonReader);
                    v03.i(jsonDataExceptionW4, "unexpectedNull(\"enabled\"…       \"enabled\", reader)");
                    throw jsonDataExceptionW4;
                }
            } else if (iM0 == 4 && (boolFromJson2 = this.booleanAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW5 = ds2.w("found", "found", jsonReader);
                v03.i(jsonDataExceptionW5, "unexpectedNull(\"found\", …und\",\n            reader)");
                throw jsonDataExceptionW5;
            }
        }
        jsonReader.q();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("id", "id", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"id\", \"id\", reader)");
            throw jsonDataExceptionO;
        }
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO2 = ds2.o("path", "path", jsonReader);
            v03.i(jsonDataExceptionO2, "missingProperty(\"path\", \"path\", reader)");
            throw jsonDataExceptionO2;
        }
        if (strFromJson3 == null) {
            JsonDataException jsonDataExceptionO3 = ds2.o("name", "name", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"name\", \"name\", reader)");
            throw jsonDataExceptionO3;
        }
        if (boolFromJson == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("enabled", "enabled", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"enabled\", \"enabled\", reader)");
            throw jsonDataExceptionO4;
        }
        boolean zBooleanValue = boolFromJson.booleanValue();
        if (boolFromJson2 != null) {
            return new LocalSource(strFromJson, strFromJson2, strFromJson3, zBooleanValue, boolFromJson2.booleanValue());
        }
        JsonDataException jsonDataExceptionO5 = ds2.o("found", "found", jsonReader);
        v03.i(jsonDataExceptionO5, "missingProperty(\"found\", \"found\", reader)");
        throw jsonDataExceptionO5;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalSource localSource) {
        v03.j(mVar, "writer");
        if (localSource == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("id");
        this.stringAdapter.toJson(mVar, localSource.getId());
        mVar.J0("path");
        this.stringAdapter.toJson(mVar, localSource.getPath());
        mVar.J0("name");
        this.stringAdapter.toJson(mVar, localSource.getName());
        mVar.J0("enabled");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(localSource.getEnabled()));
        mVar.J0("found");
        this.booleanAdapter.toJson(mVar, Boolean.valueOf(localSource.getFound()));
        mVar.X();
    }
}
