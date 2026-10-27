package com.airbnb.lottie.t;

import android.graphics.Path;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeFillParser.java */
/* JADX INFO: loaded from: classes.dex */
class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3145a = JsonReader.a.a("nm", "c", "o", "fillEnabled", "r", "hd");

    static com.airbnb.lottie.model.content.i a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zR = false;
        boolean zR2 = false;
        int iT = 1;
        String strV = null;
        com.airbnb.lottie.model.i.a aVarC = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3145a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                aVarC = d.c(jsonReader, dVar);
            } else if (iZ == 2) {
                dVarH = d.h(jsonReader, dVar);
            } else if (iZ == 3) {
                zR = jsonReader.r();
            } else if (iZ == 4) {
                iT = jsonReader.t();
            } else if (iZ != 5) {
                jsonReader.A();
                jsonReader.B();
            } else {
                zR2 = jsonReader.r();
            }
        }
        return new com.airbnb.lottie.model.content.i(strV, zR, iT == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, aVarC, dVarH, zR2);
    }
}
