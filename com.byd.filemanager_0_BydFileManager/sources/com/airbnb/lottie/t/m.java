package com.airbnb.lottie.t;

import android.graphics.Path;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: GradientFillParser.java */
/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2780a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "r", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2781b = JsonReader.a.a("p", "k");

    static com.airbnb.lottie.model.content.d a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        Path.FillType fillType = Path.FillType.WINDING;
        String strU = null;
        GradientType gradientType = null;
        com.airbnb.lottie.model.i.c cVarG = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.f fVarI2 = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2780a)) {
                case 0:
                    strU = jsonReader.u();
                    break;
                case 1:
                    int iS = -1;
                    jsonReader.l();
                    while (jsonReader.p()) {
                        int iY = jsonReader.y(f2781b);
                        if (iY == 0) {
                            iS = jsonReader.s();
                        } else if (iY != 1) {
                            jsonReader.z();
                            jsonReader.A();
                        } else {
                            cVarG = d.g(jsonReader, dVar, iS);
                        }
                    }
                    jsonReader.n();
                    break;
                case 2:
                    dVarH = d.h(jsonReader, dVar);
                    break;
                case 3:
                    gradientType = jsonReader.s() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    fVarI = d.i(jsonReader, dVar);
                    break;
                case 5:
                    fVarI2 = d.i(jsonReader, dVar);
                    break;
                case 6:
                    fillType = jsonReader.s() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    zQ = jsonReader.q();
                    break;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    break;
            }
        }
        return new com.airbnb.lottie.model.content.d(strU, gradientType, fillType, cVarG, dVarH, fVarI, fVarI2, null, null, zQ);
    }
}
