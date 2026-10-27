package com.airbnb.lottie.t;

import android.graphics.Path;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeFillParser.java */
/* JADX INFO: loaded from: classes.dex */
class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2765a = JsonReader.a.a("nm", "c", "o", "fillEnabled", "r", "hd");

    static com.airbnb.lottie.model.content.i a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zQ = false;
        boolean zQ2 = false;
        int iS = 1;
        String strU = null;
        com.airbnb.lottie.model.i.a aVarC = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2765a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                aVarC = d.c(jsonReader, dVar);
            } else if (iY == 2) {
                dVarH = d.h(jsonReader, dVar);
            } else if (iY == 3) {
                zQ = jsonReader.q();
            } else if (iY == 4) {
                iS = jsonReader.s();
            } else if (iY != 5) {
                jsonReader.z();
                jsonReader.A();
            } else {
                zQ2 = jsonReader.q();
            }
        }
        return new com.airbnb.lottie.model.content.i(strU, zQ, iS == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, aVarC, dVarH, zQ2);
    }
}
