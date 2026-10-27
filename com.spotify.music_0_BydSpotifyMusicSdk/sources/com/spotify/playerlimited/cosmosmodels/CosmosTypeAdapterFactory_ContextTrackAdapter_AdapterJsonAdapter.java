package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_ContextTrackAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public CosmosTypeAdapterFactory_ContextTrackAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("metadata", "provider", "uid", "uri");
        v03.i(bVarA, "of(\"metadata\", \"provider\", \"uid\",\n      \"uri\")");
        this.a = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Map.class, String.class, String.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(parameterizedTypeJ, emptySet, "metadata");
        v03.i(fVarF, "moshi.adapter(Types.newP…, emptySet(), \"metadata\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "provider");
        v03.i(fVarF2, "moshi.adapter(String::cl…  emptySet(), \"provider\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Map map = null;
        String str = null;
        String str2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        String str3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 != 0) {
                f fVar = this.c;
                if (iM0 == 1) {
                    str3 = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    str = (String) fVar.fromJson(jsonReader);
                    z3 = true;
                } else if (iM0 == 3) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z4 = true;
                }
            } else {
                map = (Map) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter = new CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.c = map;
        }
        if (z2) {
            cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.d = str3;
        }
        if (z3) {
            cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.b = str;
        }
        if (z4) {
            cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter.a = str2;
        }
        return cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter) {
        CosmosTypeAdapterFactory$ContextTrackAdapter$Adapter cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2 = cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("metadata");
        this.b.toJson(mVar, cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2.c);
        mVar.J0("provider");
        String str = cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2.d;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("uid");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2.b);
        mVar.J0("uri");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$ContextTrackAdapter$Adapter2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(74, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.ContextTrackAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
