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
public final class ResumePointJsonAdapter extends f<ResumePoint> {
    private final f<Integer> intAdapter;
    private final f<Boolean> nullableBooleanAdapter;
    private final JsonReader.b options;

    public ResumePointJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("fully_played", "resume_position_ms");
        v03.i(bVarA, "of(\"fully_played\",\n      \"resume_position_ms\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<Boolean> fVarF = oVar.f(Boolean.class, emptySet, "fullyPlayed");
        v03.i(fVarF, "moshi.adapter(Boolean::c…mptySet(), \"fullyPlayed\")");
        this.nullableBooleanAdapter = fVarF;
        f<Integer> fVarF2 = oVar.f(Integer.TYPE, emptySet, "resumePositionMs");
        v03.i(fVarF2, "moshi.adapter(Int::class…      \"resumePositionMs\")");
        this.intAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(33, "GeneratedJsonAdapter(ResumePoint)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ResumePoint fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean boolFromJson = null;
        boolean z = false;
        Integer numFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                boolFromJson = this.nullableBooleanAdapter.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1 && (numFromJson = this.intAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("resumePositionMs", "resume_position_ms", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"resumePo…ume_position_ms\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        ResumePoint resumePoint = new ResumePoint();
        if (z) {
            resumePoint.fullyPlayed = boolFromJson;
        }
        resumePoint.resumePositionMs = numFromJson != null ? numFromJson.intValue() : resumePoint.resumePositionMs;
        return resumePoint;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ResumePoint resumePoint) {
        v03.j(mVar, "writer");
        if (resumePoint == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("fully_played");
        this.nullableBooleanAdapter.toJson(mVar, resumePoint.fullyPlayed);
        mVar.J0("resume_position_ms");
        this.intAdapter.toJson(mVar, Integer.valueOf(resumePoint.resumePositionMs));
        mVar.X();
    }
}
