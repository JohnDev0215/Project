package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.GenericArrayType;
import java.util.Map;
import kotlin.collections.EmptySet;
import p.iu1;
import p.u4;
import p.v03;
import p.xt1;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_PlayerContextAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;
    public final f e;

    public CosmosTypeAdapterFactory_PlayerContextAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("fallback_pages", "metadata", "pages", "restrictions", "uri", "url");
        v03.i(bVarA, "of(\"fallback_pages\", \"me…trictions\", \"uri\", \"url\")");
        this.a = bVarA;
        GenericArrayType genericArrayTypeB = zo2.b(xt1.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(genericArrayTypeB, emptySet, "fallbackPages");
        v03.i(fVarF, "moshi.adapter(Types.arra…tySet(), \"fallbackPages\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(zo2.j(Map.class, String.class, String.class), emptySet, "metadata");
        v03.i(fVarF2, "moshi.adapter(Types.newP…, emptySet(), \"metadata\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(iu1.class, emptySet, "restrictions");
        v03.i(fVarF3, "moshi.adapter(PlayerRest…ptySet(), \"restrictions\")");
        this.d = fVarF3;
        f fVarF4 = oVar.f(String.class, emptySet, "uri");
        v03.i(fVarF4, "moshi.adapter(String::cl…\n      emptySet(), \"uri\")");
        this.e = fVarF4;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        xt1[] xt1VarArr = null;
        xt1[] xt1VarArr2 = null;
        iu1 iu1Var = null;
        String str = null;
        String str2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        Map map = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            f fVar = this.b;
            String str3 = str2;
            f fVar2 = this.e;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    xt1VarArr = (xt1[]) fVar.fromJson(jsonReader);
                    str2 = str3;
                    z = true;
                    continue;
                case 1:
                    map = (Map) this.c.fromJson(jsonReader);
                    str2 = str3;
                    z2 = true;
                    continue;
                case 2:
                    xt1VarArr2 = (xt1[]) fVar.fromJson(jsonReader);
                    str2 = str3;
                    z3 = true;
                    continue;
                case 3:
                    iu1Var = (iu1) this.d.fromJson(jsonReader);
                    str2 = str3;
                    z4 = true;
                    continue;
                case 4:
                    str = (String) fVar2.fromJson(jsonReader);
                    str2 = str3;
                    z5 = true;
                    continue;
                case 5:
                    str2 = (String) fVar2.fromJson(jsonReader);
                    z6 = true;
                    continue;
            }
            str2 = str3;
        }
        String str4 = str2;
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter = new CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.e = xt1VarArr;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.b = map;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.d = xt1VarArr2;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.c = iu1Var;
        }
        if (z5) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.a = str;
        }
        if (z6) {
            cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter.f = str4;
        }
        return cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayerContextAdapter$Adapter cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("fallback_pages");
        xt1[] xt1VarArr = cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.e;
        f fVar = this.b;
        fVar.toJson(mVar, xt1VarArr);
        mVar.J0("metadata");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.b);
        mVar.J0("pages");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.d);
        mVar.J0("restrictions");
        this.d.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.c);
        mVar.J0("uri");
        String str = cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.a;
        f fVar2 = this.e;
        fVar2.toJson(mVar, str);
        mVar.J0("url");
        fVar2.toJson(mVar, cosmosTypeAdapterFactory$PlayerContextAdapter$Adapter2.f);
        mVar.X();
    }

    public final String toString() {
        return u4.b(75, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayerContextAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
