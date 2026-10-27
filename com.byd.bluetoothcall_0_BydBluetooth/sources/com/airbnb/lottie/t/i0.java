package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeTrimPathParser.java */
/* JADX INFO: loaded from: classes.dex */
class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3155a = JsonReader.a.a("s", "e", "o", "nm", "m", "hd");

    static ShapeTrimPath a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zR = false;
        String strV = null;
        ShapeTrimPath.Type typeForId = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.b bVarF3 = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3155a);
            if (iZ == 0) {
                bVarF = d.f(jsonReader, dVar, false);
            } else if (iZ == 1) {
                bVarF2 = d.f(jsonReader, dVar, false);
            } else if (iZ == 2) {
                bVarF3 = d.f(jsonReader, dVar, false);
            } else if (iZ == 3) {
                strV = jsonReader.v();
            } else if (iZ == 4) {
                typeForId = ShapeTrimPath.Type.forId(jsonReader.t());
            } else if (iZ != 5) {
                jsonReader.B();
            } else {
                zR = jsonReader.r();
            }
        }
        return new ShapeTrimPath(strV, typeForId, bVarF, bVarF2, bVarF3, zR);
    }
}
