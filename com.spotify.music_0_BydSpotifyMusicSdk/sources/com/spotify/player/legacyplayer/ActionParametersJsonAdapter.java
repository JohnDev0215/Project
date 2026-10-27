package com.spotify.player.legacyplayer;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Type;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ActionParametersJsonAdapter<T> extends f<ActionParameters<T>> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public ActionParametersJsonAdapter(o oVar, Type[] typeArr) {
        v03.j(oVar, "moshi");
        v03.j(typeArr, "types");
        if (typeArr.length != 1) {
            String str = "TypeVariable mismatch: Expecting 1 type for generic type variables [T], but received " + typeArr.length;
            v03.i(str, "StringBuilder().apply(builderAction).toString()");
            throw new IllegalArgumentException(str.toString());
        }
        JsonReader.b bVarA = JsonReader.b.a("value", "logging_params");
        v03.i(bVarA, "of(\"value\", \"logging_params\")");
        this.a = bVarA;
        Type type = typeArr[0];
        EmptySet emptySet = EmptySet.c;
        f<T> fVarF = oVar.f(type, emptySet, "value");
        v03.i(fVarF, "moshi.adapter(types[0], emptySet(),\n      \"value\")");
        this.b = fVarF;
        f<T> fVarF2 = oVar.f(LoggingParameters.class, emptySet, "loggingParams");
        v03.i(fVarF2, "moshi.adapter(LoggingPar…tySet(), \"loggingParams\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final Object fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Object objFromJson = null;
        LoggingParameters loggingParameters = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                objFromJson = this.b.fromJson(jsonReader);
            } else if (iM0 == 1 && (loggingParameters = (LoggingParameters) this.c.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW = ds2.w("loggingParams", "logging_params", jsonReader);
                v03.i(jsonDataExceptionW, "unexpectedNull(\"loggingP…\"logging_params\", reader)");
                throw jsonDataExceptionW;
            }
        }
        jsonReader.q();
        if (loggingParameters != null) {
            return new ActionParameters(objFromJson, loggingParameters);
        }
        JsonDataException jsonDataExceptionO = ds2.o("loggingParams", "logging_params", jsonReader);
        v03.i(jsonDataExceptionO, "missingProperty(\"logging…\"logging_params\", reader)");
        throw jsonDataExceptionO;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, Object obj) {
        ActionParameters actionParameters = (ActionParameters) obj;
        v03.j(mVar, "writer");
        if (actionParameters == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("value");
        this.b.toJson(mVar, actionParameters.a);
        mVar.J0("logging_params");
        this.c.toJson(mVar, actionParameters.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(38, "GeneratedJsonAdapter(ActionParameters)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
