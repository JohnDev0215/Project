package com.spotify.voiceassistants.playermodels;

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
public final class ContextRequestJsonAdapter extends f<ContextRequest> {
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;
    private final f<ParsedQuery> parsedQueryAdapter;
    private final f<SourceDevice> sourceDeviceAdapter;
    private final f<String> stringAdapter;

    public ContextRequestJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("text_query", "text_query_language", "source_device", "parsed_query", "voice_feature_name", "interactionId");
        v03.i(bVarA, "of(\"text_query\",\n      \"…e_name\", \"interactionId\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "textQuery");
        v03.i(fVarF, "moshi.adapter(String::cl…Set(),\n      \"textQuery\")");
        this.stringAdapter = fVarF;
        f<SourceDevice> fVarF2 = oVar.f(SourceDevice.class, emptySet, "sourceDevice");
        v03.i(fVarF2, "moshi.adapter(SourceDevi…ptySet(), \"sourceDevice\")");
        this.sourceDeviceAdapter = fVarF2;
        f<ParsedQuery> fVarF3 = oVar.f(ParsedQuery.class, emptySet, "parsedQuery");
        v03.i(fVarF3, "moshi.adapter(ParsedQuer…mptySet(), \"parsedQuery\")");
        this.parsedQueryAdapter = fVarF3;
        f<String> fVarF4 = oVar.f(String.class, emptySet, "interactionId");
        v03.i(fVarF4, "moshi.adapter(String::cl…tySet(), \"interactionId\")");
        this.nullableStringAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(36, "GeneratedJsonAdapter(ContextRequest)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ContextRequest fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        String strFromJson2 = null;
        SourceDevice sourceDeviceFromJson = null;
        ParsedQuery parsedQueryFromJson = null;
        String str = null;
        String strFromJson3 = null;
        while (true) {
            String str2 = strFromJson3;
            String str3 = str;
            ParsedQuery parsedQuery = parsedQueryFromJson;
            if (!jsonReader.hasNext()) {
                jsonReader.q();
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
                if (sourceDeviceFromJson == null) {
                    JsonDataException jsonDataExceptionO3 = ds2.o("sourceDevice", "source_device", jsonReader);
                    v03.i(jsonDataExceptionO3, "missingProperty(\"sourceD…ice\",\n            reader)");
                    throw jsonDataExceptionO3;
                }
                if (parsedQuery == null) {
                    JsonDataException jsonDataExceptionO4 = ds2.o("parsedQuery", "parsed_query", jsonReader);
                    v03.i(jsonDataExceptionO4, "missingProperty(\"parsedQ…ery\",\n            reader)");
                    throw jsonDataExceptionO4;
                }
                if (str3 != null) {
                    return new ContextRequest(strFromJson, strFromJson2, sourceDeviceFromJson, parsedQuery, str3, str2);
                }
                JsonDataException jsonDataExceptionO5 = ds2.o("voiceFeatureName", "voice_feature_name", jsonReader);
                v03.i(jsonDataExceptionO5, "missingProperty(\"voiceFe…ce_feature_name\", reader)");
                throw jsonDataExceptionO5;
            }
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    strFromJson3 = str2;
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
                case 0:
                    strFromJson = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("textQuery", "text_query", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"textQuer…    \"text_query\", reader)");
                        throw jsonDataExceptionW;
                    }
                    strFromJson3 = str2;
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
                case 1:
                    strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("textQueryLanguage", "text_query_language", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"textQuer…_query_language\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    strFromJson3 = str2;
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
                case 2:
                    sourceDeviceFromJson = this.sourceDeviceAdapter.fromJson(jsonReader);
                    if (sourceDeviceFromJson == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("sourceDevice", "source_device", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"sourceDe… \"source_device\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    strFromJson3 = str2;
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
                case 3:
                    parsedQueryFromJson = this.parsedQueryAdapter.fromJson(jsonReader);
                    if (parsedQueryFromJson == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("parsedQuery", "parsed_query", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"parsedQu…, \"parsed_query\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    strFromJson3 = str2;
                    str = str3;
                    break;
                    break;
                case 4:
                    String strFromJson4 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson4 == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("voiceFeatureName", "voice_feature_name", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"voiceFea…ce_feature_name\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    str = strFromJson4;
                    strFromJson3 = str2;
                    parsedQueryFromJson = parsedQuery;
                    break;
                    break;
                case 5:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
                default:
                    strFromJson3 = str2;
                    str = str3;
                    parsedQueryFromJson = parsedQuery;
                    break;
            }
        }
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ContextRequest contextRequest) {
        v03.j(mVar, "writer");
        if (contextRequest == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("text_query");
        this.stringAdapter.toJson(mVar, contextRequest.getTextQuery());
        mVar.J0("text_query_language");
        this.stringAdapter.toJson(mVar, contextRequest.getTextQueryLanguage());
        mVar.J0("source_device");
        this.sourceDeviceAdapter.toJson(mVar, contextRequest.getSourceDevice());
        mVar.J0("parsed_query");
        this.parsedQueryAdapter.toJson(mVar, contextRequest.getParsedQuery());
        mVar.J0("voice_feature_name");
        this.stringAdapter.toJson(mVar, contextRequest.getVoiceFeatureName());
        mVar.J0("interactionId");
        this.nullableStringAdapter.toJson(mVar, contextRequest.getInteractionId());
        mVar.X();
    }
}
