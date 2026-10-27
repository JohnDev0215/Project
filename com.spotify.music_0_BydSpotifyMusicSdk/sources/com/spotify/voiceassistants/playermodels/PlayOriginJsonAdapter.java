package com.spotify.voiceassistants.playermodels;

import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.PlayOrigin;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import p.ly0;
import p.v03;
import p.zl2;

/* JADX INFO: loaded from: classes.dex */
public final class PlayOriginJsonAdapter extends f<PlayOrigin> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    @ly0
    public PlayOrigin fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "jsonReader");
        jsonReader.c();
        String strU = "";
        String strU2 = "";
        while (jsonReader.hasNext()) {
            String strR0 = jsonReader.r0();
            if (v03.b(strR0, "feature_identifier")) {
                strU = jsonReader.u();
                v03.i(strU, "jsonReader.nextString()");
            } else if (v03.b(strR0, "referrer_identifier")) {
                strU2 = jsonReader.u();
                v03.i(strU2, "jsonReader.nextString()");
            } else {
                Logger.g("Unknown JSON property: %s", strR0);
                jsonReader.D();
            }
        }
        jsonReader.q();
        PlayOrigin playOriginA = PlayOrigin.a(strU).g(strU2).a();
        v03.i(playOriginA, "builder(featureId)\n     …rId)\n            .build()");
        return playOriginA;
    }

    @Override // com.squareup.moshi.f
    @zl2
    public void toJson(m mVar, PlayOrigin playOrigin) {
        v03.j(mVar, "writer");
        mVar.i();
        if (playOrigin != null) {
            mVar.J0("feature_identifier").W0(playOrigin.f());
            mVar.J0("referrer_identifier").W0(playOrigin.h());
        }
        mVar.X();
    }
}
