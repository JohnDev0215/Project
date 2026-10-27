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
public final class CosmosTypeAdapterFactory_PlayOriginAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public CosmosTypeAdapterFactory_PlayOriginAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("device_identifier", "external_referrer", "feature_classes", "feature_identifier", "feature_version", "referrer_identifier", "view_uri");
        v03.i(bVarA, "of(\"device_identifier\",\n…_identifier\", \"view_uri\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(String.class, emptySet, "deviceIdentifier");
        v03.i(fVarF, "moshi.adapter(String::cl…et(), \"deviceIdentifier\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(zo2.j(Set.class, String.class), emptySet, "featureClasses");
        v03.i(fVarF2, "moshi.adapter(Types.newP…,\n      \"featureClasses\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        Set set = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        String str6 = null;
        while (jsonReader.hasNext()) {
            String str7 = str5;
            int iM0 = jsonReader.M0(this.a);
            boolean z8 = z7;
            f fVar = this.b;
            switch (iM0) {
                case -1:
                    jsonReader.Q0();
                    jsonReader.D();
                    break;
                case 0:
                    str = (String) fVar.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z = true;
                    continue;
                case 1:
                    str6 = (String) fVar.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z2 = true;
                    continue;
                case 2:
                    set = (Set) this.c.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z3 = true;
                    continue;
                case 3:
                    str2 = (String) fVar.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z4 = true;
                    continue;
                case 4:
                    str3 = (String) fVar.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z5 = true;
                    continue;
                case 5:
                    str4 = (String) fVar.fromJson(jsonReader);
                    str5 = str7;
                    z7 = z8;
                    z6 = true;
                    continue;
                case 6:
                    str5 = (String) fVar.fromJson(jsonReader);
                    z7 = true;
                    continue;
            }
            str5 = str7;
            z7 = z8;
        }
        String str8 = str5;
        boolean z9 = z7;
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.f = str;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.d = str6;
        }
        if (z3) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.g = set;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.a = str2;
        }
        if (z5) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.b = str3;
        }
        if (z6) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.e = str4;
        }
        if (z9) {
            cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter.c = str8;
        }
        return cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayOriginAdapter$Adapter cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("device_identifier");
        String str = cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.f;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("external_referrer");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.d);
        mVar.J0("feature_classes");
        this.c.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.g);
        mVar.J0("feature_identifier");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.a);
        mVar.J0("feature_version");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.b);
        mVar.J0("referrer_identifier");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.e);
        mVar.J0("view_uri");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOriginAdapter$Adapter2.c);
        mVar.X();
    }

    public final String toString() {
        return u4.b(72, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayOriginAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
