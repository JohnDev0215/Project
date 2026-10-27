package com.spotify.webapi.service.models;

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
public final class ErrorDetailsJsonAdapter extends f<ErrorDetails> {
    private final f<Integer> intAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;

    public ErrorDetailsJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("message", "status");
        v03.i(bVarA, "of(\"message\", \"status\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "message");
        v03.i(fVarF, "moshi.adapter(String::cl…   emptySet(), \"message\")");
        this.nullableStringAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "status");
        v03.i(fVarF2, "moshi.adapter(Int::class…va, emptySet(), \"status\")");
        this.intAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(34, "GeneratedJsonAdapter(ErrorDetails)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ErrorDetails fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        boolean z = false;
        Integer numFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.nullableStringAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1 && (numFromJson = this.intAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("status", "status", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"status\",…tus\",\n            reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        ErrorDetails errorDetails = new ErrorDetails();
        if (z) {
            errorDetails.message = strFromJson;
        }
        errorDetails.status = numFromJson != null ? numFromJson.intValue() : errorDetails.status;
        return errorDetails;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ErrorDetails errorDetails) {
        v03.j(mVar, "writer");
        if (errorDetails == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("message");
        this.nullableStringAdapter.toJson(mVar, errorDetails.message);
        mVar.J0("status");
        this.intAdapter.toJson(mVar, Integer.valueOf(errorDetails.status));
        mVar.X();
    }
}
