package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeTrimPathParser.java */
/* JADX INFO: loaded from: classes.dex */
class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2775a = JsonReader.a.a("s", "e", "o", "nm", "m", "hd");

    static ShapeTrimPath a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zQ = false;
        String strU = null;
        ShapeTrimPath.Type typeForId = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.b bVarF3 = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2775a);
            if (iY == 0) {
                bVarF = d.f(jsonReader, dVar, false);
            } else if (iY == 1) {
                bVarF2 = d.f(jsonReader, dVar, false);
            } else if (iY == 2) {
                bVarF3 = d.f(jsonReader, dVar, false);
            } else if (iY == 3) {
                strU = jsonReader.u();
            } else if (iY == 4) {
                typeForId = ShapeTrimPath.Type.forId(jsonReader.s());
            } else if (iY != 5) {
                jsonReader.A();
            } else {
                zQ = jsonReader.q();
            }
        }
        return new ShapeTrimPath(strU, typeForId, bVarF, bVarF2, bVarF3, zQ);
    }
}
