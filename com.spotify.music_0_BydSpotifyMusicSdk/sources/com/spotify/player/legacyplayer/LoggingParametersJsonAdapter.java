package com.spotify.player.legacyplayer;

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
public final class LoggingParametersJsonAdapter extends f<LoggingParameters> {
    public final JsonReader.b a;
    public final f b;

    public LoggingParametersJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("command_initiated_time");
        v03.i(bVarA, "of(\"command_initiated_time\")");
        this.a = bVarA;
        f fVarF = oVar.f(Long.TYPE, EmptySet.c, "commandInitiatedTime");
        v03.i(fVarF, "moshi.adapter(Long::clas…  \"commandInitiatedTime\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final LoggingParameters fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Long l = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0 && (l = (Long) this.b.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("commandInitiatedTime", "command_initiated_time", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"commandI…_initiated_time\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        LoggingParameters loggingParameters = new LoggingParameters();
        loggingParameters.a = l != null ? l.longValue() : loggingParameters.a;
        return loggingParameters;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, LoggingParameters loggingParameters) {
        LoggingParameters loggingParameters2 = loggingParameters;
        v03.j(mVar, "writer");
        if (loggingParameters2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("command_initiated_time");
        this.b.toJson(mVar, Long.valueOf(loggingParameters2.a));
        mVar.X();
    }

    public final String toString() {
        return u4.b(39, "GeneratedJsonAdapter(LoggingParameters)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
