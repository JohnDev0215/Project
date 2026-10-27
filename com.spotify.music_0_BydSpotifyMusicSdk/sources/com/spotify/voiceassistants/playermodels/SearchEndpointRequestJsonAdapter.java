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
public final class SearchEndpointRequestJsonAdapter extends f<SearchEndpointRequest> {
    private volatile Constructor<SearchEndpointRequest> constructorRef;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;
    private final f<ParsedQuery> parsedQueryAdapter;
    private final f<SourceDevice> sourceDeviceAdapter;
    private final f<String> stringAdapter;

    public SearchEndpointRequestJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("text_query", "text_query_language", "source_device", "target_device", "parsed_query", "voice_feature_name", "interactionId");
        v03.i(bVarA, "of(\"text_query\",\n      \"…\",\n      \"interactionId\")");
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
        return u4.b(43, "GeneratedJsonAdapter(SearchEndpointRequest)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SearchEndpointRequest fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String strFromJson = null;
        String strFromJson2 = null;
        SourceDevice sourceDeviceFromJson = null;
        SourceDevice sourceDeviceFromJson2 = null;
        ParsedQuery parsedQuery = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        while (true) {
            String str = strFromJson4;
            String str2 = strFromJson3;
            ParsedQuery parsedQuery2 = parsedQuery;
            SourceDevice sourceDevice = sourceDeviceFromJson2;
            SourceDevice sourceDevice2 = sourceDeviceFromJson;
            if (!jsonReader.hasNext()) {
                jsonReader.q();
                if (i == -9) {
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
                    if (sourceDevice2 == null) {
                        JsonDataException jsonDataExceptionO3 = ds2.o("sourceDevice", "source_device", jsonReader);
                        v03.i(jsonDataExceptionO3, "missingProperty(\"sourceD…e\",\n              reader)");
                        throw jsonDataExceptionO3;
                    }
                    v03.g(sourceDevice, "null cannot be cast to non-null type com.spotify.voiceassistants.playermodels.SourceDevice");
                    if (parsedQuery2 == null) {
                        JsonDataException jsonDataExceptionO4 = ds2.o("parsedQuery", "parsed_query", jsonReader);
                        v03.i(jsonDataExceptionO4, "missingProperty(\"parsedQ…y\",\n              reader)");
                        throw jsonDataExceptionO4;
                    }
                    if (str2 != null) {
                        return new SearchEndpointRequest(strFromJson, strFromJson2, sourceDevice2, sourceDevice, parsedQuery2, str2, str);
                    }
                    JsonDataException jsonDataExceptionO5 = ds2.o("voiceFeatureName", "voice_feature_name", jsonReader);
                    v03.i(jsonDataExceptionO5, "missingProperty(\"voiceFe…ce_feature_name\", reader)");
                    throw jsonDataExceptionO5;
                }
                Constructor<SearchEndpointRequest> declaredConstructor = this.constructorRef;
                int i2 = 9;
                if (declaredConstructor == null) {
                    declaredConstructor = SearchEndpointRequest.class.getDeclaredConstructor(String.class, String.class, SourceDevice.class, SourceDevice.class, ParsedQuery.class, String.class, String.class, Integer.TYPE, ds2.c);
                    this.constructorRef = declaredConstructor;
                    v03.i(declaredConstructor, "SearchEndpointRequest::c…his.constructorRef = it }");
                    i2 = 9;
                }
                Object[] objArr = new Object[i2];
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionO6 = ds2.o("textQuery", "text_query", jsonReader);
                    v03.i(jsonDataExceptionO6, "missingProperty(\"textQuery\", \"text_query\", reader)");
                    throw jsonDataExceptionO6;
                }
                objArr[0] = strFromJson;
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionO7 = ds2.o("textQueryLanguage", "text_query_language", jsonReader);
                    v03.i(jsonDataExceptionO7, "missingProperty(\"textQue…_query_language\", reader)");
                    throw jsonDataExceptionO7;
                }
                objArr[1] = strFromJson2;
                if (sourceDevice2 == null) {
                    JsonDataException jsonDataExceptionO8 = ds2.o("sourceDevice", "source_device", jsonReader);
                    v03.i(jsonDataExceptionO8, "missingProperty(\"sourceD… \"source_device\", reader)");
                    throw jsonDataExceptionO8;
                }
                objArr[2] = sourceDevice2;
                objArr[3] = sourceDevice;
                if (parsedQuery2 == null) {
                    JsonDataException jsonDataExceptionO9 = ds2.o("parsedQuery", "parsed_query", jsonReader);
                    v03.i(jsonDataExceptionO9, "missingProperty(\"parsedQ…, \"parsed_query\", reader)");
                    throw jsonDataExceptionO9;
                }
                objArr[4] = parsedQuery2;
                if (str2 == null) {
                    JsonDataException jsonDataExceptionO10 = ds2.o("voiceFeatureName", "voice_feature_name", jsonReader);
                    v03.i(jsonDataExceptionO10, "missingProperty(\"voiceFe…e\",\n              reader)");
                    throw jsonDataExceptionO10;
                }
                objArr[5] = str2;
                objArr[6] = str;
                objArr[7] = Integer.valueOf(i);
                objArr[8] = null;
                SearchEndpointRequest searchEndpointRequestNewInstance = declaredConstructor.newInstance(objArr);
                v03.i(searchEndpointRequestNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
                return searchEndpointRequestNewInstance;
            }
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                case 0:
                    strFromJson = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("textQuery", "text_query", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"textQuer…    \"text_query\", reader)");
                        throw jsonDataExceptionW;
                    }
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                case 1:
                    strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("textQueryLanguage", "text_query_language", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"textQuer…_query_language\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                case 2:
                    sourceDeviceFromJson = this.sourceDeviceAdapter.fromJson(jsonReader);
                    if (sourceDeviceFromJson == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("sourceDevice", "source_device", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"sourceDe… \"source_device\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    break;
                    break;
                case 3:
                    sourceDeviceFromJson2 = this.sourceDeviceAdapter.fromJson(jsonReader);
                    if (sourceDeviceFromJson2 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("targetDevice", "target_device", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"targetDe… \"target_device\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    i &= -9;
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                    break;
                case 4:
                    ParsedQuery parsedQueryFromJson = this.parsedQueryAdapter.fromJson(jsonReader);
                    if (parsedQueryFromJson == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("parsedQuery", "parsed_query", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"parsedQu…, \"parsed_query\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    parsedQuery = parsedQueryFromJson;
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                    break;
                case 5:
                    strFromJson3 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson3 == null) {
                        JsonDataException jsonDataExceptionW6 = ds2.w("voiceFeatureName", "voice_feature_name", jsonReader);
                        v03.i(jsonDataExceptionW6, "unexpectedNull(\"voiceFea…ce_feature_name\", reader)");
                        throw jsonDataExceptionW6;
                    }
                    strFromJson4 = str;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                    break;
                case 6:
                    strFromJson4 = this.nullableStringAdapter.fromJson(jsonReader);
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
                default:
                    strFromJson4 = str;
                    strFromJson3 = str2;
                    parsedQuery = parsedQuery2;
                    sourceDeviceFromJson2 = sourceDevice;
                    sourceDeviceFromJson = sourceDevice2;
                    break;
            }
        }
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SearchEndpointRequest searchEndpointRequest) {
        v03.j(mVar, "writer");
        if (searchEndpointRequest == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("text_query");
        this.stringAdapter.toJson(mVar, searchEndpointRequest.getTextQuery());
        mVar.J0("text_query_language");
        this.stringAdapter.toJson(mVar, searchEndpointRequest.getTextQueryLanguage());
        mVar.J0("source_device");
        this.sourceDeviceAdapter.toJson(mVar, searchEndpointRequest.getSourceDevice());
        mVar.J0("target_device");
        this.sourceDeviceAdapter.toJson(mVar, searchEndpointRequest.getTargetDevice());
        mVar.J0("parsed_query");
        this.parsedQueryAdapter.toJson(mVar, searchEndpointRequest.getParsedQuery());
        mVar.J0("voice_feature_name");
        this.stringAdapter.toJson(mVar, searchEndpointRequest.getVoiceFeatureName());
        mVar.J0("interactionId");
        this.nullableStringAdapter.toJson(mVar, searchEndpointRequest.getInteractionId());
        mVar.X();
    }
}
