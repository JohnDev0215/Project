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
public final class CosmosTypeAdapterFactory_PlayOptionsSkipToAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;
    public final f d;

    public CosmosTypeAdapterFactory_PlayOptionsSkipToAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("page_index", "page_url", "track_index", "track_uid", "track_uri");
        v03.i(bVarA, "of(\"page_index\", \"page_u…\"track_uid\", \"track_uri\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(Integer.class, emptySet, "pageIndex");
        v03.i(fVarF, "moshi.adapter(Int::class… emptySet(), \"pageIndex\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "pageUrl");
        v03.i(fVarF2, "moshi.adapter(String::cl…   emptySet(), \"pageUrl\")");
        this.c = fVarF2;
        f fVarF3 = oVar.f(Integer.TYPE, emptySet, "trackIndex");
        v03.i(fVarF3, "moshi.adapter(Int::class…et(),\n      \"trackIndex\")");
        this.d = fVarF3;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Integer num = null;
        Integer num2 = null;
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
                    num2 = (Integer) this.d.fromJson(jsonReader);
                    if (num2 == null) {
                        JsonDataException jsonDataExceptionW = ds2.w("trackIndex", "track_index", jsonReader);
                        v03.i(jsonDataExceptionW, "unexpectedNull(\"trackInd…   \"track_index\", reader)");
                        throw jsonDataExceptionW;
                    }
                } else if (iM0 == 3) {
                    str = (String) fVar.fromJson(jsonReader);
                    z3 = true;
                } else if (iM0 == 4) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z4 = true;
                }
            } else {
                num = (Integer) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter = new CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.b = num;
        }
        if (z2) {
            cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.a = str3;
        }
        cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.e = num2 != null ? num2.intValue() : cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.e;
        if (z3) {
            cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.c = str;
        }
        if (z4) {
            cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter.d = str2;
        }
        return cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter) {
        CosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2 = cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("page_index");
        this.b.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2.b);
        mVar.J0("page_url");
        String str = cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2.a;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("track_index");
        this.d.toJson(mVar, Integer.valueOf(cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2.e));
        mVar.J0("track_uid");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2.c);
        mVar.J0("track_uri");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$PlayOptionsSkipToAdapter$Adapter2.d);
        mVar.X();
    }

    public final String toString() {
        return u4.b(79, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.PlayOptionsSkipToAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
