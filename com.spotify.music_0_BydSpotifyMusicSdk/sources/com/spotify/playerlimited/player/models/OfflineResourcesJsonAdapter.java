package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.util.List;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineResourcesJsonAdapter extends f<OfflineResources> {
    public final JsonReader.b a;
    public final f b;

    public OfflineResourcesJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("resources");
        v03.i(bVarA, "of(\"resources\")");
        this.a = bVarA;
        f fVarF = oVar.f(zo2.j(List.class, OfflineResource.class), EmptySet.c, "resources");
        v03.i(fVarF, "moshi.adapter(Types.newP… emptySet(), \"resources\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final OfflineResources fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List list = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                list = (List) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        OfflineResources offlineResources = new OfflineResources();
        if (z) {
            offlineResources.a = list;
        }
        return offlineResources;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineResources offlineResources) {
        OfflineResources offlineResources2 = offlineResources;
        v03.j(mVar, "writer");
        if (offlineResources2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("resources");
        this.b.toJson(mVar, offlineResources2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(38, "GeneratedJsonAdapter(OfflineResources)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
