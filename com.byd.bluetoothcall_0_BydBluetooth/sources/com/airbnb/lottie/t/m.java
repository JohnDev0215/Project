package com.airbnb.lottie.t;

import android.graphics.Path;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: GradientFillParser.java */
/* JADX INFO: loaded from: classes.dex */
class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3160a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "r", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f3161b = JsonReader.a.a("p", "k");

    static com.airbnb.lottie.model.content.d a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        Path.FillType fillType = Path.FillType.WINDING;
        String strV = null;
        GradientType gradientType = null;
        com.airbnb.lottie.model.i.c cVarG = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.f fVarI2 = null;
        boolean zR = false;
        while (jsonReader.q()) {
            switch (jsonReader.z(f3160a)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    int iT = -1;
                    jsonReader.m();
                    while (jsonReader.q()) {
                        int iZ = jsonReader.z(f3161b);
                        if (iZ == 0) {
                            iT = jsonReader.t();
                        } else if (iZ != 1) {
                            jsonReader.A();
                            jsonReader.B();
                        } else {
                            cVarG = d.g(jsonReader, dVar, iT);
                        }
                    }
                    jsonReader.o();
                    break;
                case 2:
                    dVarH = d.h(jsonReader, dVar);
                    break;
                case 3:
                    gradientType = jsonReader.t() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    fVarI = d.i(jsonReader, dVar);
                    break;
                case 5:
                    fVarI2 = d.i(jsonReader, dVar);
                    break;
                case 6:
                    fillType = jsonReader.t() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    zR = jsonReader.r();
                    break;
                default:
                    jsonReader.A();
                    jsonReader.B();
                    break;
            }
        }
        return new com.airbnb.lottie.model.content.d(strV, gradientType, fillType, cVarG, dVarH, fVarI, fVarI2, null, null, zR);
    }
}
