package com.spotify.playerlimited.cosmosmodels;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTypeAdapterFactory_SkipToTrackAdapter_AdapterJsonAdapter extends f<CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public CosmosTypeAdapterFactory_SkipToTrackAdapter_AdapterJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("page_index", "page_url", "track_index", "track_uid", "track_uri");
        v03.i(bVarA, "of(\"page_index\", \"page_u…\"track_uid\", \"track_uri\")");
        this.a = bVarA;
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(Long.class, emptySet, "pageIndex");
        v03.i(fVarF, "moshi.adapter(Long::clas… emptySet(), \"pageIndex\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "pageUrl");
        v03.i(fVarF2, "moshi.adapter(String::cl…   emptySet(), \"pageUrl\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        Long l = null;
        Long l2 = null;
        String str = null;
        String str2 = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        String str3 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 != 0) {
                    f fVar2 = this.c;
                    if (iM0 == 1) {
                        str3 = (String) fVar2.fromJson(jsonReader);
                        z2 = true;
                    } else if (iM0 == 2) {
                        l2 = (Long) fVar.fromJson(jsonReader);
                        z3 = true;
                    } else if (iM0 == 3) {
                        str = (String) fVar2.fromJson(jsonReader);
                        z4 = true;
                    } else if (iM0 == 4) {
                        str2 = (String) fVar2.fromJson(jsonReader);
                        z5 = true;
                    }
                } else {
                    l = (Long) fVar.fromJson(jsonReader);
                    z = true;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter = new CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter();
        if (z) {
            cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.b = l;
        }
        if (z2) {
            cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.a = str3;
        }
        if (z3) {
            cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.e = l2;
        }
        if (z4) {
            cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.c = str;
        }
        if (z5) {
            cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter.d = str2;
        }
        return cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter) {
        CosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2 = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter;
        v03.j(mVar, "writer");
        if (cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("page_index");
        Long l = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2.b;
        f fVar = this.b;
        fVar.toJson(mVar, l);
        mVar.J0("page_url");
        String str = cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2.a;
        f fVar2 = this.c;
        fVar2.toJson(mVar, str);
        mVar.J0("track_index");
        fVar.toJson(mVar, cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2.e);
        mVar.J0("track_uid");
        fVar2.toJson(mVar, cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2.c);
        mVar.J0("track_uri");
        fVar2.toJson(mVar, cosmosTypeAdapterFactory$SkipToTrackAdapter$Adapter2.d);
        mVar.X();
    }

    public final String toString() {
        return u4.b(73, "GeneratedJsonAdapter(CosmosTypeAdapterFactory.SkipToTrackAdapter.Adapter)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
