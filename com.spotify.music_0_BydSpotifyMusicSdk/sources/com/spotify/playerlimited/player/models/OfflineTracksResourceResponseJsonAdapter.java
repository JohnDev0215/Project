package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineTracksResourceResponseJsonAdapter extends f<OfflineTracksResourceResponse> {
    public final JsonReader.b a;
    public final f b;

    public OfflineTracksResourceResponseJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("resources");
        v03.i(bVarA, "of(\"resources\")");
        this.a = bVarA;
        f fVarF = oVar.f(OfflineTracksResource.class, EmptySet.c, "resource");
        v03.i(fVarF, "moshi.adapter(OfflineTra…, emptySet(), \"resource\")");
        this.b = fVarF;
    }

    @Override // com.squareup.moshi.f
    public final OfflineTracksResourceResponse fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        OfflineTracksResource offlineTracksResource = null;
        boolean z = false;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 == 0) {
                offlineTracksResource = (OfflineTracksResource) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        OfflineTracksResourceResponse offlineTracksResourceResponse = new OfflineTracksResourceResponse();
        if (z) {
            offlineTracksResourceResponse.a = offlineTracksResource;
        }
        return offlineTracksResourceResponse;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineTracksResourceResponse offlineTracksResourceResponse) {
        OfflineTracksResourceResponse offlineTracksResourceResponse2 = offlineTracksResourceResponse;
        v03.j(mVar, "writer");
        if (offlineTracksResourceResponse2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("resources");
        this.b.toJson(mVar, offlineTracksResourceResponse2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(51, "GeneratedJsonAdapter(OfflineTracksResourceResponse)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
