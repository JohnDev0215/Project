package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayerOptionOverridesAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;

    public CosmosTypeAdapterFactory_PlayerOptionOverridesAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("repeating_context", "repeating_track", "shuffling_context");
        v03.i(bVarA, "of(\"repeating_context\",\n…ck\", \"shuffling_context\")");
        this.a = bVarA;
        f fVarF = oVar.f(Boolean.class, EmptySet.c, "repeatingContext");
        v03.i(fVarF, "moshi.adapter(Boolean::c…et(), \"repeatingContext\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Boolean bool = null;
        Boolean bool2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        Boolean bool3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    bool = (Boolean) fVar.fromJson(jsonReader);
                    z = true;
                } else if (iM0 == 1) {
                    bool3 = (Boolean) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    bool2 = (Boolean) fVar.fromJson(jsonReader);
                    z3 = true;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.b = bool;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.c = bool3;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter.a = bool2;
        }
        return cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("repeating_context");
        Boolean bool = cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter2.b;
        f fVar = this.b;
        fVar.toJson(mVar, bool);
        mVar.J0("repeating_track");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter2.c);
        mVar.J0("shuffling_context");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerOptionOverridesAdapter$Adapter2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(83, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerOptionOverridesAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
