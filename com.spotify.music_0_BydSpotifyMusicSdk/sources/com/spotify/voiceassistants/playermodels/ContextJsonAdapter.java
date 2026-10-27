package com.spotify.voiceassistants.playermodels;

import com.google.common.collect.ImmutableMap;
import com.spotify.base.java.logging.Logger;
import com.spotify.player.model.Context;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.f;
import com.squareup.moshi.m;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.c;
import p.ly0;
import p.v03;
import p.zl2;

/* JADX INFO: loaded from: classes.dex */
public final class ContextJsonAdapter extends f<Context> {
    private final void readMetadata(JsonReader jsonReader, Map<String, String> map) {
        jsonReader.c();
        while (jsonReader.hasNext()) {
            String strR0 = jsonReader.r0();
            v03.i(strR0, "jsonReader.nextName()");
            String strU = jsonReader.u();
            v03.i(strU, "jsonReader.nextString()");
            map.put(strR0, strU);
        }
        jsonReader.q();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.squareup.moshi.f
    @ly0
    public Context fromJson(JsonReader jsonReader) {
        v03.j(jsonReader, "jsonReader");
        jsonReader.c();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String strU = "";
        String strU2 = "";
        while (jsonReader.hasNext()) {
            String strR0 = jsonReader.r0();
            if (strR0 != null) {
                int iHashCode = strR0.hashCode();
                if (iHashCode != -450004177) {
                    if (iHashCode != 116076) {
                        if (iHashCode == 116079 && strR0.equals("url")) {
                            strU2 = jsonReader.u();
                            v03.i(strU2, "jsonReader.nextString()");
                        }
                    } else if (strR0.equals("uri")) {
                        strU = jsonReader.u();
                        v03.i(strU, "jsonReader.nextString()");
                    }
                } else if (strR0.equals("metadata")) {
                    readMetadata(jsonReader, linkedHashMap);
                }
            }
            Logger.g("Unknown JSON property: %s", strR0);
            jsonReader.D();
        }
        jsonReader.q();
        Context.Builder builderE = Context.a(strU).e(strU2);
        Map mapL0 = c.l0(linkedHashMap);
        builderE.getClass();
        Context contextA = builderE.b(ImmutableMap.b(mapL0)).a();
        v03.i(contextA, "builder(uri)\n           …p())\n            .build()");
        return contextA;
    }

    @Override // com.squareup.moshi.f
    @zl2
    public void toJson(m mVar, Context context) {
        v03.j(mVar, "writer");
        mVar.i();
        if (context != null) {
            mVar.J0("uri").W0(context.h());
            mVar.J0("url").W0(context.i());
            v03.i(context.d(), "nonNullContext.metadata()");
            if (!r0.isEmpty()) {
                mVar.J0("metadata");
                mVar.i();
                ImmutableMap<String, String> immutableMapD = context.d();
                v03.i(immutableMapD, "nonNullContext.metadata()");
                for (Map.Entry<String, String> entry : immutableMapD.entrySet()) {
                    mVar.J0(entry.getKey()).W0(entry.getValue());
                }
                mVar.X();
            }
        }
        mVar.X();
    }
}
