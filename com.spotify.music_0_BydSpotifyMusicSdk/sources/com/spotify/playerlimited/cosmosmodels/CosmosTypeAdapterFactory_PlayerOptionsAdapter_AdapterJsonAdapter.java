package com.spotify.playerlimited.cosmosmodels;

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
public final class CosmosTypeAdapterFactory_PlayerOptionsAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;

    public CosmosTypeAdapterFactory_PlayerOptionsAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("repeating_context", "repeating_track", "shuffling_context");
        v03.i(bVarA, "of(\"repeating_context\",\n…ck\", \"shuffling_context\")");
        this.a = bVarA;
        f fVarF = oVar.f(Boolean.TYPE, EmptySet.c, "repeatingContext");
        v03.i(fVarF, "moshi.adapter(Boolean::c…      \"repeatingContext\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        Boolean bool2 = null;
        Boolean bool3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    bool = (Boolean) fVar.fromJson(jsonReader);
                    if (bool == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("repeatingContext", "repeating_context", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"repeatin…peating_context\", reader)");
                        throw jsonDataExceptionW;
                    }
                } else if (iM0 == 1) {
                    bool2 = (Boolean) fVar.fromJson(jsonReader);
                    if (bool2 == null) {
                        JsonDataException jsonDataExceptionW2 = ds2.w("repeatingTrack", "repeating_track", jsonReader);
                        v03.i(jsonDataExceptionW2, "unexpectedNull(\"repeatin…repeating_track\", reader)");
                        throw jsonDataExceptionW2;
                    }
                } else if (iM0 == 2 && (bool3 = (Boolean) fVar.fromJson(jsonReader)) == null) {
                    JsonDataException jsonDataExceptionW3 = ds2.w("shufflingContext", "shuffling_context", jsonReader);
                    v03.i(jsonDataExceptionW3, "unexpectedNull(\"shufflin…uffling_context\", reader)");
                    throw jsonDataExceptionW3;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter();
        cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.b = bool != null ? bool.booleanValue() : cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.b;
        cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.c = bool2 != null ? bool2.booleanValue() : cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.c;
        cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.a = bool3 != null ? bool3.booleanValue() : cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter.a;
        return cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("repeating_context");
        Boolean boolValueOf = Boolean.valueOf(cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter2.b);
        f fVar = this.b;
        fVar.toJson(mVar, boolValueOf);
        mVar.J0("repeating_track");
        u4.w(cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter2.c, fVar, mVar, "shuffling_context");
        fVar.toJson(mVar, Boolean.valueOf(cosmosTypeAdapterFactory$PlayerOptionsAdapter$Adapter2.a));
        mVar.X();
    }

    public final String toString() {
        return u4.b(75, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerOptionsAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
