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
public final class LocalContentSummaryJsonAdapter extends f<LocalContentSummary> {
    private final f<Integer> intAdapter;
    private final f<Long> longAdapter;
    private final JsonReader.b options;

    public LocalContentSummaryJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("numberOfTracks", "totalDurationSeconds");
        v03.i(bVarA, "of(\"numberOfTracks\",\n      \"totalDurationSeconds\")");
        this.options = bVarA;
        Class cls = Integer.TYPE;
        EmptySet emptySet = EmptySet.c;
        f<Integer> fVarF = oVar.f(cls, emptySet, "numberOfTracks");
        v03.i(fVarF, "moshi.adapter(Int::class…,\n      \"numberOfTracks\")");
        this.intAdapter = fVarF;
        f<Long> fVarF2 = oVar.f(Long.TYPE, emptySet, "totalDurationSeconds");
        v03.i(fVarF2, "moshi.adapter(Long::clas…  \"totalDurationSeconds\")");
        this.longAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(41, "GeneratedJsonAdapter(LocalContentSummary)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public LocalContentSummary fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer numFromJson = null;
        Long lFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                numFromJson = this.intAdapter.fromJson(jsonReader);
                if (numFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("numberOfTracks", "numberOfTracks", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"numberOf…\"numberOfTracks\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1 && (lFromJson = this.longAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW2 = ds2.w("totalDurationSeconds", "totalDurationSeconds", jsonReader);
                v03.i(jsonDataExceptionW2, "unexpectedNull(\"totalDur…DurationSeconds\", reader)");
                throw jsonDataExceptionW2;
            }
        }
        jsonReader.q();
        if (numFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("numberOfTracks", "numberOfTracks", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"numberO…\"numberOfTracks\", reader)");
            throw jsonDataExceptionO;
        }
        int iIntValue = numFromJson.intValue();
        if (lFromJson != null) {
            return new LocalContentSummary(iIntValue, lFromJson.longValue());
        }
        JsonDataException jsonDataExceptionO2 = ds2.o("totalDurationSeconds", "totalDurationSeconds", jsonReader);
        v03.i(jsonDataExceptionO2, "missingProperty(\"totalDu…DurationSeconds\", reader)");
        throw jsonDataExceptionO2;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, LocalContentSummary localContentSummary) {
        v03.j(mVar, "writer");
        if (localContentSummary == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("numberOfTracks");
        this.intAdapter.toJson(mVar, Integer.valueOf(localContentSummary.getNumberOfTracks()));
        mVar.J0("totalDurationSeconds");
        this.longAdapter.toJson(mVar, Long.valueOf(localContentSummary.getTotalDurationSeconds()));
        mVar.X();
    }
}
