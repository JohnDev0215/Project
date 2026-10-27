package com.spotify.voiceassistants.playermodels;

import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.ds2;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class ShowIntentResponseJsonAdapter extends f<ShowIntentResponse> {
    private final f<List<MetadataItem>> listOfMetadataItemAdapter;
    private final JsonReader.b options;
    private final f<String> stringAdapter;

    public ShowIntentResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("req_id", "entities");
        v03.i(bVarA, "of(\"req_id\", \"entities\")");
        this.options = bVarA;
        EmptySet emptySet = EmptySet.c;
        f<String> fVarF = oVar.f(String.class, emptySet, "requestId");
        v03.i(fVarF, "moshi.adapter(String::cl…Set(),\n      \"requestId\")");
        this.stringAdapter = fVarF;
        f<List<MetadataItem>> fVarF2 = oVar.f(zo2.j(List.class, MetadataItem.class), emptySet, "alternativeResults");
        v03.i(fVarF2, "moshi.adapter(Types.newP…(), \"alternativeResults\")");
        this.listOfMetadataItemAdapter = fVarF2;
    }

    public String toString() {
        return u4.b(40, "GeneratedJsonAdapter(ShowIntentResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    public ShowIntentResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String strFromJson = null;
        List<MetadataItem> listFromJson = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.options);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                strFromJson = this.stringAdapter.fromJson(jsonReader);
                if (strFromJson == null) {
                    JsonDataException jsonDataExceptionW = ds2.w("requestId", "req_id", jsonReader);
                    v03.i(jsonDataExceptionW, "unexpectedNull(\"requestI…        \"req_id\", reader)");
                    throw jsonDataExceptionW;
                }
            } else if (iM0 == 1 && (listFromJson = this.listOfMetadataItemAdapter.fromJson(jsonReader)) == null) {
                JsonDataException jsonDataExceptionW2 = ds2.w("alternativeResults", "entities", jsonReader);
                v03.i(jsonDataExceptionW2, "unexpectedNull(\"alternat…lts\", \"entities\", reader)");
                throw jsonDataExceptionW2;
            }
        }
        jsonReader.q();
        if (strFromJson == null) {
            JsonDataException jsonDataExceptionO = ds2.o("requestId", "req_id", jsonReader);
            v03.i(jsonDataExceptionO, "missingProperty(\"requestId\", \"req_id\", reader)");
            throw jsonDataExceptionO;
        }
        if (listFromJson != null) {
            return new ShowIntentResponse(strFromJson, listFromJson);
        }
        JsonDataException jsonDataExceptionO2 = ds2.o("alternativeResults", "entities", jsonReader);
        v03.i(jsonDataExceptionO2, "missingProperty(\"alterna…      \"entities\", reader)");
        throw jsonDataExceptionO2;
    }

    @Override // com.squareup.moshi.f
    public void toJson(m mVar, ShowIntentResponse showIntentResponse) {
        v03.j(mVar, "writer");
        if (showIntentResponse == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("req_id");
        this.stringAdapter.toJson(mVar, showIntentResponse.getRequestId());
        mVar.J0("entities");
        this.listOfMetadataItemAdapter.toJson(mVar, showIntentResponse.getAlternativeResults());
        mVar.X();
    }
}
