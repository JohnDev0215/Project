package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapePathParser.java */
/* JADX INFO: loaded from: classes.dex */
class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static JsonReader.a f2769a = JsonReader.a.a("nm", "ind", "ks", "hd");

    static com.airbnb.lottie.model.content.k a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        int iS = 0;
        String strU = null;
        com.airbnb.lottie.model.i.h hVarK = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2769a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                iS = jsonReader.s();
            } else if (iY == 2) {
                hVarK = d.k(jsonReader, dVar);
            } else if (iY != 3) {
                jsonReader.A();
            } else {
                zQ = jsonReader.q();
            }
        }
        return new com.airbnb.lottie.model.content.k(strU, iS, hVarK, zQ);
    }
}
