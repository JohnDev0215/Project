package com.spotify.voiceassistants.playermodels;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ShowIntentRequestJsonAdapter extends f<ShowIntentRequest> {
    private volatile Constructor<ShowIntentRequest> constructorRef;
    private final f<ShowIntentQuery> nullableShowIntentQueryAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public ShowIntentRequestJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("text_query", "text_query_language", "parsed_query", "voice_feature_name");
        v03.i(bVarA, "of(\"text_query\",\n      \"…y\", \"voice_feature_name\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "textQuery");
        v03.i(fVarF, "moshi.adapter(String::cl…Set(),\n      \"textQuery\")");
        this.stringAdapter = fVarF;
        f<ShowIntentQuery> fVarF2 = oVar.f(ShowIntentQuery.class, emptySet, "showIntentQuery");
        v03.i(fVarF2, "moshi.adapter(ShowIntent…Set(), \"showIntentQuery\")");
        this.nullableShowIntentQueryAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(39, "GeneratedJsonAdapter(ShowIntentRequest)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ShowIntentRequest fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String strFromJson = null;
        String strFromJson2 = null;
        ShowIntentQuery showIntentQueryFromJson = null;
        String strFromJson3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("textQuery", "text_query", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"textQuer…    \"text_query\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1) {
                strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionW2 = ds2.w("textQueryLanguage", "text_query_language", jsonReader);
                    v03.i(jsonDataExceptionW2, "unexpectedNull(\"textQuer…_query_language\", reader)");
                    throw jsonDataExceptionW2;
                }
            } else if (iM0 == 2) {
                showIntentQueryFromJson = this.nullableShowIntentQueryAdapter.fromJson(jsonReader);
                i &= -5;
            } else if (iM0 == 3 && (strFromJson3 = this.stringAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW3 = ds2.w("voiceFeatureName", "voice_feature_name", jsonReader);
                v03.i(jsonDataExceptionW3, "unexpectedNull(\"voiceFea…ce_feature_name\", reader)");
                throw jsonDataExceptionW3;
            }
        }
        jsonReader.q();
        if (i == -5) {
            if (strFromJson == null) {
                JsonDataException jsonDataExceptionO = ds2.o("textQuery", "text_query", jsonReader);
                v03.i(jsonDataExceptionO, "missingProperty(\"textQuery\", \"text_query\", reader)");
                throw jsonDataExceptionO;
            }
            if (strFromJson2 == null) {
                JsonDataException jsonDataExceptionO2 = ds2.o("textQueryLanguage", "text_query_language", jsonReader);
                v03.i(jsonDataExceptionO2, "missingProperty(\"textQue…_query_language\", reader)");
                throw jsonDataExceptionO2;
            }
            if (strFromJson3 != null) {
                return new ShowIntentRequest(strFromJson, strFromJson2, showIntentQueryFromJson, strFromJson3);
            }
            JsonDataException jsonDataExceptionO3 = ds2.o("voiceFeatureName", "voice_feature_name", jsonReader);
            v03.i(jsonDataExceptionO3, "missingProperty(\"voiceFe…ce_feature_name\", reader)");
            throw jsonDataExceptionO3;
        }
        Constructor<ShowIntentRequest> declaredConstructor = this.constructorRef;
        int i2 = 6;
        if (declaredConstructor == null) {
            declaredConstructor = ShowIntentRequest.class.getDeclaredConstructor(String.class, String.class, ShowIntentQuery.class, String.class, Integer.TYPE, ds2.c);
            this.constructorRef = declaredConstructor;
            v03.i(declaredConstructor, "ShowIntentRequest::class…his.constructorRef = it }");
            i2 = 6;
        }
        Object[] objArr = new Object[i2];
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO4 = ds2.o("textQuery", "text_query", jsonReader);
            v03.i(jsonDataExceptionO4, "missingProperty(\"textQuery\", \"text_query\", reader)");
            throw jsonDataExceptionO4;
        }
        objArr[0] = strFromJson;
        if (strFromJson2 == null) {
            JsonDataException jsonDataExceptionO5 = ds2.o("textQueryLanguage", "text_query_language", jsonReader);
            v03.i(jsonDataExceptionO5, "missingProperty(\"textQue…_query_language\", reader)");
            throw jsonDataExceptionO5;
        }
        objArr[1] = strFromJson2;
        objArr[2] = showIntentQueryFromJson;
        if (strFromJson3 == null) {
            JsonDataException jsonDataExceptionO6 = ds2.o("voiceFeatureName", "voice_feature_name", jsonReader);
            v03.i(jsonDataExceptionO6, "missingProperty(\"voiceFe…e\",\n              reader)");
            throw jsonDataExceptionO6;
        }
        objArr[3] = strFromJson3;
        objArr[4] = Integer.valueOf(i);
        objArr[5] = null;
        ShowIntentRequest showIntentRequestNewInstance = declaredConstructor.newInstance(objArr);
        v03.i(showIntentRequestNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
        return showIntentRequestNewInstance;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ShowIntentRequest showIntentRequest) {
        v03.j(mVar, "writer");
        if (showIntentRequest == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("text_query");
        this.stringAdapter.toJson(mVar, showIntentRequest.getTextQuery());
        mVar.J0("text_query_language");
        this.stringAdapter.toJson(mVar, showIntentRequest.getTextQueryLanguage());
        mVar.J0("parsed_query");
        this.nullableShowIntentQueryAdapter.toJson(mVar, showIntentRequest.getShowIntentQuery());
        mVar.J0("voice_feature_name");
        this.stringAdapter.toJson(mVar, showIntentRequest.getVoiceFeatureName());
        mVar.X();
    }
}
