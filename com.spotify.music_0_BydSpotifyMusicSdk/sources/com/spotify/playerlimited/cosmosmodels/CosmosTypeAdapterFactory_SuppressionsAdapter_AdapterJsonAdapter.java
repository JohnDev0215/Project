package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.Set;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_SuppressionsAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;

    public CosmosTypeAdapterFactory_SuppressionsAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("providers");
        v03.i(bVarA, "of(\"providers\")");
        this.a = bVarA;
        f fVarF = oVar.f(zo2.j(Set.class, String.class), EmptySet.c, "providers");
        v03.i(fVarF, "moshi.adapter(Types.newP…Set(),\n      \"providers\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Set set = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                set = (Set) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter = new CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter.a = set;
        }
        return cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter) {
        CosmosTypeAdapterFactory$SuppressionsAdapter$Adapter cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter2 = cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("providers");
        this.b.toJson(mVar, cosmosTypeAdapterFactory$SuppressionsAdapter$Adapter2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(74, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.SuppressionsAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
