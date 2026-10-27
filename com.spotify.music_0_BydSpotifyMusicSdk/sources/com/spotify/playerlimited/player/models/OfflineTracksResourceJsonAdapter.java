package com.spotify.playerlimited.player.models;

import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import com.squareup.moshi.o;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import kotlin.collections.EmptySet;
import p.u4;
import p.v03;
import p.zo2;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineTracksResourceJsonAdapter extends f<OfflineTracksResource> {
    public final JsonReader.b a;
    public final f b;
    public final f c;

    public OfflineTracksResourceJsonAdapter(o oVar) {
        v03.j(oVar, "moshi");
        JsonReader.b bVarA = JsonReader.b.a("tracks", "offline_availability", "uri");
        v03.i(bVarA, "of(\"tracks\", \"offline_availability\",\n      \"uri\")");
        this.a = bVarA;
        ParameterizedType parameterizedTypeJ = zo2.j(List.class, OfflineResource.class);
        EmptySet emptySet = EmptySet.c;
        f fVarF = oVar.f(parameterizedTypeJ, emptySet, "tracks");
        v03.i(fVarF, "moshi.adapter(Types.newP…    emptySet(), \"tracks\")");
        this.b = fVarF;
        f fVarF2 = oVar.f(String.class, emptySet, "availability");
        v03.i(fVarF2, "moshi.adapter(String::cl…ptySet(), \"availability\")");
        this.c = fVarF2;
    }

    @Override // com.squareup.moshi.f
    public final OfflineTracksResource fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "reader");
        jsonReader.c();
        List list = null;
        String str = null;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        String str2 = null;
        while (jsonReader.hasNext()) {
            int iM0 = jsonReader.M0(this.a);
            if (iM0 == -1) {
                jsonReader.Q0();
                jsonReader.D();
            } else if (iM0 != 0) {
                f fVar = this.c;
                if (iM0 == 1) {
                    str2 = (String) fVar.fromJson(jsonReader);
                    z2 = true;
                } else if (iM0 == 2) {
                    str = (String) fVar.fromJson(jsonReader);
                    z3 = true;
                }
            } else {
                list = (List) this.b.fromJson(jsonReader);
                z = true;
            }
        }
        jsonReader.q();
        OfflineTracksResource offlineTracksResource = new OfflineTracksResource();
        if (z) {
            offlineTracksResource.c = list;
        }
        if (z2) {
            offlineTracksResource.b = str2;
        }
        if (z3) {
            offlineTracksResource.a = str;
        }
        return offlineTracksResource;
    }

    @Override // com.squareup.moshi.f
    public final void toJson(m mVar, OfflineTracksResource offlineTracksResource) {
        OfflineTracksResource offlineTracksResource2 = offlineTracksResource;
        v03.j(mVar, "writer");
        if (offlineTracksResource2 == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        mVar.i();
        mVar.J0("tracks");
        this.b.toJson(mVar, offlineTracksResource2.c);
        mVar.J0("offline_availability");
        String str = offlineTracksResource2.b;
        f fVar = this.c;
        fVar.toJson(mVar, str);
        mVar.J0("uri");
        fVar.toJson(mVar, offlineTracksResource2.a);
        mVar.X();
    }

    public final String toString() {
        return u4.b(43, "GeneratedJsonAdapter(OfflineTracksResource)", "StringBuilder(capacity).…builderAction).toString()");
    }
}
