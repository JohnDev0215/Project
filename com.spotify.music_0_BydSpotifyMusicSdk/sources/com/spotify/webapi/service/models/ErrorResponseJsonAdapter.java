package com.spotify.webapi.service.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ErrorResponseJsonAdapter extends f<ErrorResponse> {
    private final f<ErrorDetails> nullableErrorDetailsAdapter;
    private final JsonReader.b options;

    public ErrorResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("error");
        v03.i(bVarA, "of(\"error\")");
        this.options = bVarA;
        f<ErrorDetails> fVarF = oVar.f(ErrorDetails.class, EmptySet.c, "error");
        v03.i(fVarF, "moshi.adapter(ErrorDetai…ava, emptySet(), \"error\")");
        this.nullableErrorDetailsAdapter = fVarF;
    }

    public String toString() {
        return u4.b(35, "GeneratedJsonAdapter(ErrorResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ErrorResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        ErrorDetails errorDetailsFromJson = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                errorDetailsFromJson = this.nullableErrorDetailsAdapter.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        ErrorResponse errorResponse = new ErrorResponse();
        if (z) {
            errorResponse.error = errorDetailsFromJson;
        }
        return errorResponse;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ErrorResponse errorResponse) {
        v03.j(mVar, "writer");
        if (errorResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("error");
        this.nullableErrorDetailsAdapter.toJson(mVar, errorResponse.error);
        mVar.X();
    }
}
