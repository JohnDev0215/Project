package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineResourceJsonAdapter extends f<OfflineResource> {
    public final JsonReader.b a;
    public final f b;

    public OfflineResourceJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("offline_availability", "uri");
        v03.i(bVarA, "of(\"offline_availability\", \"uri\")");
        this.a = bVarA;
        f fVarF = oVar.f(String.class, EmptySet.c, "availability");
        v03.i(fVarF, "moshi.adapter(String::cl…ptySet(), \"availability\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final OfflineResource fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        String str = null;
        boolean z = false;
        boolean z2 = false;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 != -1) {
                f fVar = this.b;
                if (iM0 == 0) {
                    str = (String) fVar.fromJson(jsonReader);
                    z = true;
                } else if (iM0 == 1) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                }
            } else {
                jsonReader.Q0();
                jsonReader.D();
            }
        }
        jsonReader.q();
        OfflineResource offlineResource = new OfflineResource();
        if (z) {
            offlineResource.b = str;
        }
        if (z2) {
            offlineResource.a = str2;
        }
        return offlineResource;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineResource offlineResource) {
        OfflineResource offlineResource2 = offlineResource;
        v03.j(mVar, "writer");
        if (offlineResource2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("offline_availability");
        String str = offlineResource2.b;
        f fVar = this.b;
        fVar.toJson(mVar, str);
        mVar.J0("uri");
        fVar.toJson(mVar, offlineResource2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(37, "GeneratedJsonAdapter(OfflineResource)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
