package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapePathParser.java */
/* JADX INFO: loaded from: classes.dex */
class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static JsonReader.a f3149a = JsonReader.a.a("nm", "ind", "ks", "hd");

    static com.airbnb.lottie.model.content.k a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        int iT = 0;
        String strV = null;
        com.airbnb.lottie.model.i.h hVarK = null;
        boolean zR = false;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3149a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                iT = jsonReader.t();
            } else if (iZ == 2) {
                hVarK = d.k(jsonReader, dVar);
            } else if (iZ != 3) {
                jsonReader.B();
            } else {
                zR = jsonReader.r();
            }
        }
        return new com.airbnb.lottie.model.content.k(strV, iT, hVarK, zR);
    }
}
