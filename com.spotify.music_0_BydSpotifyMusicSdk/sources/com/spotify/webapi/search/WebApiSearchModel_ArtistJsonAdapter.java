package com.spotify.webapi.search;

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
public final class WebApiSearchModel_ArtistJsonAdapter extends f<WebApiSearchModel$Artist> {
    public final JsonReader.b a;
    public final f b;

    public WebApiSearchModel_ArtistJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("uri", "name");
        v03.i(bVarA, "of(\"uri\", \"name\")");
        this.a = bVarA;
        f fVarF = oVar.f(String.class, EmptySet.c, "uri");
        v03.i(fVarF, "moshi.adapter(String::cl… emptySet(),\n      \"uri\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchModel$Artist fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    str = (String) fVar.fromJson(jsonReader);
                    if (str == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("uri", "uri", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"uri\", \"uri\", reader)");
                        throw jsonDataExceptionW;
                    }
                } else if (iM0 == 1 && (str2 = (String) fVar.fromJson(jsonReader)) == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("name", "name", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"name\", \"name\",\n            reader)");
                    throw jsonDataExceptionW2;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        if (str == null) {
            JsonDataException jsonDataExceptionO = ds2.o("uri", "uri", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"uri\", \"uri\", reader)");
            throw jsonDataExceptionO;
        }
        if (str2 != null) {
            return new WebApiSearchModel$Artist(str, str2);
        }
        JsonDataException jsonDataExceptionO2 = ds2.o("name", "name", jsonReader);
        v03.i(jsonDataExceptionO2, "missingProperty(\"name\", \"name\", reader)");
        throw jsonDataExceptionO2;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchModel$Artist webApiSearchModel$Artist) {
        WebApiSearchModel$Artist webApiSearchModel$Artist2 = webApiSearchModel$Artist;
        v03.j(mVar, "writer");
        if (webApiSearchModel$Artist2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("uri");
        String strB = webApiSearchModel$Artist2.b();
        f fVar = this.b;
        fVar.toJson(mVar, strB);
        mVar.J0("name");
        fVar.toJson(mVar, webApiSearchModel$Artist2.a());
        mVar.X();
    }

    public final String toString() {
        return u4.b(46, "GeneratedJsonAdapter(WebApiSearchModel.Artist)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
