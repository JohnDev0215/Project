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
public final class WebApiSearchResultsJsonAdapter extends f<WebApiSearchResults> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public WebApiSearchResultsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("search_term", "results");
        v03.i(bVarA, "of(\"search_term\", \"results\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "searchTerm");
        v03.i(fVarF, "moshi.adapter(String::cl…et(),\n      \"searchTerm\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(WebApiSearchModel$Response.class, emptySet, "results");
        v03.i(fVarF2, "moshi.adapter(WebApiSear…a, emptySet(), \"results\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final WebApiSearchResults fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        WebApiSearchModel$Response webApiSearchModel$Response = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                str = (String) this.b.fromJson(jsonReader);
                if (str == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("searchTerm", "search_term", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"searchTe…   \"search_term\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1 && (webApiSearchModel$Response = (WebApiSearchModel$Response) this.c.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW2 = ds2.w("results", "results", jsonReader);
                v03.i(jsonDataExceptionW2, "unexpectedNull(\"results\"…       \"results\", reader)");
                throw jsonDataExceptionW2;
            }
        }
        jsonReader.q();
        if (str == null) {
            JsonDataException jsonDataExceptionO = ds2.o("searchTerm", "search_term", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"searchT…\", \"search_term\", reader)");
            throw jsonDataExceptionO;
        }
        if (webApiSearchModel$Response != null) {
            return new WebApiSearchResults(str, webApiSearchModel$Response);
        }
        JsonDataException jsonDataExceptionO2 = ds2.o("results", "results", jsonReader);
        v03.i(jsonDataExceptionO2, "missingProperty(\"results\", \"results\", reader)");
        throw jsonDataExceptionO2;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, WebApiSearchResults webApiSearchResults) {
        WebApiSearchResults webApiSearchResults2 = webApiSearchResults;
        v03.j(mVar, "writer");
        if (webApiSearchResults2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("search_term");
        this.b.toJson(mVar, webApiSearchResults2.a);
        mVar.J0("results");
        this.c.toJson(mVar, webApiSearchResults2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(41, "GeneratedJsonAdapter(WebApiSearchResults)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
