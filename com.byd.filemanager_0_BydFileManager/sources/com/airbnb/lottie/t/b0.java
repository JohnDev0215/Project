package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RepeaterParser.java */
/* JADX INFO: loaded from: classes.dex */
class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2758a = JsonReader.a.a("nm", "c", "o", "tr", "hd");

    static com.airbnb.lottie.model.content.g a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zQ = false;
        String strU = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.l lVarG = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2758a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                bVarF = d.f(jsonReader, dVar, false);
            } else if (iY == 2) {
                bVarF2 = d.f(jsonReader, dVar, false);
            } else if (iY == 3) {
                lVarG = c.g(jsonReader, dVar);
            } else if (iY != 4) {
                jsonReader.A();
            } else {
                zQ = jsonReader.q();
            }
        }
        return new com.airbnb.lottie.model.content.g(strU, bVarF, bVarF2, lVarG, zQ);
    }
}
