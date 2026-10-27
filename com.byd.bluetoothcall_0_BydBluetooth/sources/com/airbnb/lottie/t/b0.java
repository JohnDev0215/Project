package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RepeaterParser.java */
/* JADX INFO: loaded from: classes.dex */
class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3138a = JsonReader.a.a("nm", "c", "o", "tr", "hd");

    static com.airbnb.lottie.model.content.g a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zR = false;
        String strV = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.l lVarG = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3138a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                bVarF = d.f(jsonReader, dVar, false);
            } else if (iZ == 2) {
                bVarF2 = d.f(jsonReader, dVar, false);
            } else if (iZ == 3) {
                lVarG = c.g(jsonReader, dVar);
            } else if (iZ != 4) {
                jsonReader.B();
            } else {
                zR = jsonReader.r();
            }
        }
        return new com.airbnb.lottie.model.content.g(strV, bVarF, bVarF2, lVarG, zR);
    }
}
