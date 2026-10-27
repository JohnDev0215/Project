package com.spotify.voiceassistants.playermodels;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class SearchEndpointResponseJsonAdapter extends f<SearchEndpointResponse> {
    private volatile Constructor<SearchEndpointResponse> constructorRef;
    private final f<List<MetadataItem>> listOfMetadataItemAdapter;
    private final f<MetadataItem> nullableMetadataItemAdapter;
    private final f<String> nullableStringAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public SearchEndpointResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("req_id", "intent", "error", "result", "feedback_details", "action", "feedback_id", "alternative_results");
        v03.i(bVarA, "of(\"req_id\", \"intent\", \"…\", \"alternative_results\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "requestId");
        v03.i(fVarF, "moshi.adapter(String::cl…Set(),\n      \"requestId\")");
        this.stringAdapter = fVarF;
        f<String> fVarF2 = oVar.f(String.class, emptySet, "error");
        v03.i(fVarF2, "moshi.adapter(String::cl…     emptySet(), \"error\")");
        this.nullableStringAdapter = fVarF2;
        f<MetadataItem> fVarF3 = oVar.f(MetadataItem.class, emptySet, "feedbackDetails");
        v03.i(fVarF3, "moshi.adapter(MetadataIt…Set(), \"feedbackDetails\")");
        this.nullableMetadataItemAdapter = fVarF3;
        f<List<MetadataItem>> fVarF4 = oVar.f(zo2.j(List.class, MetadataItem.class), emptySet, "alternativeResults");
        v03.i(fVarF4, "moshi.adapter(Types.newP…(), \"alternativeResults\")");
        this.listOfMetadataItemAdapter = fVarF4;
    }

    public String toString() {
        return u4.b(44, "GeneratedJsonAdapter(SearchEndpointResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public SearchEndpointResponse fromJson(JsonReader jsonReader) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        int i = -1;
        String strFromJson = null;
        String strFromJson2 = null;
        String strFromJson3 = null;
        String strFromJson4 = null;
        MetadataItem metadataItemFromJson = null;
        String strFromJson5 = null;
        String strFromJson6 = null;
        List<MetadataItem> listFromJson = null;
        while (true) {
            String str = strFromJson5;
            MetadataItem metadataItem = metadataItemFromJson;
            if (!jsonReader.hasNext()) {
                jsonReader.q();
                if (i == -149) {
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionO = ds2.o("requestId", "req_id", jsonReader);
                        v03.i(jsonDataExceptionO, "missingProperty(\"requestId\", \"req_id\", reader)");
                        throw jsonDataExceptionO;
                    }
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionO2 = ds2.o("intent", "intent", jsonReader);
                        v03.i(jsonDataExceptionO2, "missingProperty(\"intent\", \"intent\", reader)");
                        throw jsonDataExceptionO2;
                    }
                    if (strFromJson4 == null) {
                        JsonDataException jsonDataExceptionO3 = ds2.o("result", "result", jsonReader);
                        v03.i(jsonDataExceptionO3, "missingProperty(\"result\", \"result\", reader)");
                        throw jsonDataExceptionO3;
                    }
                    if (strFromJson6 != null) {
                        v03.g(listFromJson, "null cannot be cast to non-null type kotlin.collections.List<com.spotify.voiceassistants.playermodels.MetadataItem>");
                        return new SearchEndpointResponse(strFromJson, strFromJson2, strFromJson3, strFromJson4, metadataItem, str, strFromJson6, listFromJson);
                    }
                    JsonDataException jsonDataExceptionO4 = ds2.o("feedbackId", "feedback_id", jsonReader);
                    v03.i(jsonDataExceptionO4, "missingProperty(\"feedbac…d\",\n              reader)");
                    throw jsonDataExceptionO4;
                }
                Constructor<SearchEndpointResponse> declaredConstructor = this.constructorRef;
                int i2 = 10;
                if (declaredConstructor == null) {
                    declaredConstructor = SearchEndpointResponse.class.getDeclaredConstructor(String.class, String.class, String.class, String.class, MetadataItem.class, String.class, String.class, List.class, Integer.TYPE, ds2.c);
                    this.constructorRef = declaredConstructor;
                    v03.i(declaredConstructor, "SearchEndpointResponse::…his.constructorRef = it }");
                    i2 = 10;
                }
                Object[] objArr = new Object[i2];
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionO5 = ds2.o("requestId", "req_id", jsonReader);
                    v03.i(jsonDataExceptionO5, "missingProperty(\"requestId\", \"req_id\", reader)");
                    throw jsonDataExceptionO5;
                }
                objArr[0] = strFromJson;
                if (strFromJson2 == null) {
                    JsonDataException jsonDataExceptionO6 = ds2.o("intent", "intent", jsonReader);
                    v03.i(jsonDataExceptionO6, "missingProperty(\"intent\", \"intent\", reader)");
                    throw jsonDataExceptionO6;
                }
                objArr[1] = strFromJson2;
                objArr[2] = strFromJson3;
                if (strFromJson4 == null) {
                    JsonDataException jsonDataExceptionO7 = ds2.o("result", "result", jsonReader);
                    v03.i(jsonDataExceptionO7, "missingProperty(\"result\", \"result\", reader)");
                    throw jsonDataExceptionO7;
                }
                objArr[3] = strFromJson4;
                objArr[4] = metadataItem;
                objArr[5] = str;
                if (strFromJson6 == null) {
                    JsonDataException jsonDataExceptionO8 = ds2.o("feedbackId", "feedback_id", jsonReader);
                    v03.i(jsonDataExceptionO8, "missingProperty(\"feedbac…\", \"feedback_id\", reader)");
                    throw jsonDataExceptionO8;
                }
                objArr[6] = strFromJson6;
                objArr[7] = listFromJson;
                objArr[8] = Integer.valueOf(i);
                objArr[9] = null;
                SearchEndpointResponse searchEndpointResponseNewInstance = declaredConstructor.newInstance(objArr);
                v03.i(searchEndpointResponseNewInstance, "localConstructor.newInst…torMarker */ null\n      )");
                return searchEndpointResponseNewInstance;
            }
            switch (jsonReader.M0(this.options)) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 0:
                    strFromJson = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("requestId", "req_id", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"requestI…        \"req_id\", reader)");
                        throw jsonDataExceptionW;
                    }
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 1:
                    strFromJson2 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("intent", "intent", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"intent\",…        \"intent\", reader)");
                        throw jsonDataExceptionW2;
                    }
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 2:
                    strFromJson3 = this.nullableStringAdapter.fromJson(jsonReader);
                    i &= -5;
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 3:
                    strFromJson4 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson4 == null) {
                        JsonDataException jsonDataExceptionW3 = ds2.w("result", "result", jsonReader);
                        v03.i(jsonDataExceptionW3, "unexpectedNull(\"result\",…        \"result\", reader)");
                        throw jsonDataExceptionW3;
                    }
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 4:
                    metadataItemFromJson = this.nullableMetadataItemAdapter.fromJson(jsonReader);
                    i &= -17;
                    strFromJson5 = str;
                    break;
                case 5:
                    strFromJson5 = this.nullableStringAdapter.fromJson(jsonReader);
                    metadataItemFromJson = metadataItem;
                    break;
                case 6:
                    strFromJson6 = this.stringAdapter.fromJson(jsonReader);
                    if (strFromJson6 == null) {
                        JsonDataException jsonDataExceptionW4 = ds2.w("feedbackId", "feedback_id", jsonReader);
                        v03.i(jsonDataExceptionW4, "unexpectedNull(\"feedback…   \"feedback_id\", reader)");
                        throw jsonDataExceptionW4;
                    }
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                case 7:
                    listFromJson = this.listOfMetadataItemAdapter.fromJson(jsonReader);
                    if (listFromJson == null) {
                        JsonDataException jsonDataExceptionW5 = ds2.w("alternativeResults", "alternative_results", jsonReader);
                        v03.i(jsonDataExceptionW5, "unexpectedNull(\"alternat…rnative_results\", reader)");
                        throw jsonDataExceptionW5;
                    }
                    i &= -129;
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
                    break;
                default:
                    strFromJson5 = str;
                    metadataItemFromJson = metadataItem;
                    break;
            }
        }
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, SearchEndpointResponse searchEndpointResponse) {
        v03.j(mVar, "writer");
        if (searchEndpointResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("req_id");
        this.stringAdapter.toJson(mVar, searchEndpointResponse.getRequestId());
        mVar.J0("intent");
        this.stringAdapter.toJson(mVar, searchEndpointResponse.getIntent());
        mVar.J0("error");
        this.nullableStringAdapter.toJson(mVar, searchEndpointResponse.getError());
        mVar.J0("result");
        this.stringAdapter.toJson(mVar, searchEndpointResponse.getResult());
        mVar.J0("feedback_details");
        this.nullableMetadataItemAdapter.toJson(mVar, searchEndpointResponse.getFeedbackDetails());
        mVar.J0("action");
        this.nullableStringAdapter.toJson(mVar, searchEndpointResponse.getAction());
        mVar.J0("feedback_id");
        this.stringAdapter.toJson(mVar, searchEndpointResponse.getFeedbackId());
        mVar.J0("alternative_results");
        this.listOfMetadataItemAdapter.toJson(mVar, searchEndpointResponse.getAlternativeResults());
        mVar.X();
    }
}
