package com.spotify.playerlimited.cosmosmodels;

import com.spotify.player.model.ContextPage;
import com.spotify.player.model.Restrictions;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_ContextAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$ContextAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;

    public CosmosTypeAdapterFactory_ContextAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("metadata", "pages", "restrictions", "uri", "url");
        v03.i(bVarA, "of(\"metadata\", \"pages\",\n…trictions\", \"uri\", \"url\")");
        this.a = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(Map.class, String.class, String.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(parameterizedTypeJ, emptySet, "metadata");
        v03.i(fVarF, "moshi.adapter(Types.newP…, emptySet(), \"metadata\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(zo2.j(List.class, ContextPage.class), emptySet, "pages");
        v03.i(fVarF2, "moshi.adapter(Types.newP…     emptySet(), \"pages\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(Restrictions.class, emptySet, "restrictions");
        v03.i(fVarF3, "moshi.adapter(Restrictio…ptySet(), \"restrictions\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF4, "moshi.adapter(String::cl…\n      emptySet(), \"uri\")");
        this.e = fVarF4;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$ContextAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Map map = null;
        Restrictions restrictions = null;
        String str = null;
        String str2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        List list = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                map = (Map) this.b.fromJson(jsonReader);
                z = true;
            } else if (iM0 == 1) {
                list = (List) this.c.fromJson(jsonReader);
                z2 = true;
            } else if (iM0 != 2) {
                f fVar = this.e;
                if (iM0 == 3) {
                    str = (String) fVar.fromJson(jsonReader);
                    z4 = true;
                } else if (iM0 == 4) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z5 = true;
                }
            } else {
                restrictions = (Restrictions) this.d.fromJson(jsonReader);
                z3 = true;
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$ContextAdapter$Adapter cosmosTypeAdapterFactory$ContextAdapter$Adapter = new CosmosTypeAdapterFactory$ContextAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$ContextAdapter$Adapter.c = map;
        }
        if (z2) {
            cosmosTypeAdapterFactory$ContextAdapter$Adapter.e = list;
        }
        if (z3) {
            cosmosTypeAdapterFactory$ContextAdapter$Adapter.d = restrictions;
        }
        if (z4) {
            cosmosTypeAdapterFactory$ContextAdapter$Adapter.a = str;
        }
        if (z5) {
            cosmosTypeAdapterFactory$ContextAdapter$Adapter.b = str2;
        }
        return cosmosTypeAdapterFactory$ContextAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$ContextAdapter$Adapter cosmosTypeAdapterFactory$ContextAdapter$Adapter) {
        CosmosTypeAdapterFactory$ContextAdapter$Adapter cosmosTypeAdapterFactory$ContextAdapter$Adapter2 = cosmosTypeAdapterFactory$ContextAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$ContextAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("metadata");
        this.b.toJson(mVar, cosmosTypeAdapterFactory$ContextAdapter$Adapter2.c);
        mVar.J0("pages");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$ContextAdapter$Adapter2.e);
        mVar.J0("restrictions");
        this.d.toJson(mVar, cosmosTypeAdapterFactory$ContextAdapter$Adapter2.d);
        mVar.J0("uri");
        String str = cosmosTypeAdapterFactory$ContextAdapter$Adapter2.a;
        f fVar = this.e;
        fVar.toJson(mVar, str);
        mVar.J0("url");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$ContextAdapter$Adapter2.b);
        mVar.X();
    }

    public final String toString() {
        return u4.b(69, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.ContextAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
