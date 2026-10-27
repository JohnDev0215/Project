package com.airbnb.lottie.t;

import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: GradientStrokeParser.java */
/* JADX INFO: loaded from: classes.dex */
class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2782a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2783b = JsonReader.a.a("p", "k");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f2784c = JsonReader.a.a("n", "v");

    static com.airbnb.lottie.model.content.e a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        com.airbnb.lottie.model.i.c cVar;
        ArrayList arrayList = new ArrayList();
        float fR = 0.0f;
        String strU = null;
        GradientType gradientType = null;
        com.airbnb.lottie.model.i.c cVarG = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.f fVarI2 = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        com.airbnb.lottie.model.i.b bVar = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2782a)) {
                case 0:
                    strU = jsonReader.u();
                    break;
                case 1:
                    int iS = -1;
                    jsonReader.l();
                    while (jsonReader.p()) {
                        int iY = jsonReader.y(f2783b);
                        if (iY != 0) {
                            cVar = cVarG;
                            if (iY != 1) {
                                jsonReader.z();
                                jsonReader.A();
                            } else {
                                cVarG = d.g(jsonReader, dVar, iS);
                            }
                        } else {
                            cVar = cVarG;
                            iS = jsonReader.s();
                        }
                        cVarG = cVar;
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
                    bVarE = d.e(jsonReader, dVar);
                    break;
                case 7:
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.s() - 1];
                    break;
                case 8:
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.s() - 1];
                    break;
                case 9:
                    fR = (float) jsonReader.r();
                    break;
                case 10:
                    zQ = jsonReader.q();
                    break;
                case 11:
                    jsonReader.k();
                    while (jsonReader.p()) {
                        jsonReader.l();
                        String strU2 = null;
                        com.airbnb.lottie.model.i.b bVarE2 = null;
                        while (jsonReader.p()) {
                            int iY2 = jsonReader.y(f2784c);
                            if (iY2 != 0) {
                                com.airbnb.lottie.model.i.b bVar2 = bVar;
                                if (iY2 != 1) {
                                    jsonReader.z();
                                    jsonReader.A();
                                } else {
                                    bVarE2 = d.e(jsonReader, dVar);
                                }
                                bVar = bVar2;
                            } else {
                                strU2 = jsonReader.u();
                            }
                        }
                        com.airbnb.lottie.model.i.b bVar3 = bVar;
                        jsonReader.n();
                        if (strU2.equals("o")) {
                            bVar = bVarE2;
                        } else {
                            if (strU2.equals("d") || strU2.equals("g")) {
                                dVar.t(true);
                                arrayList.add(bVarE2);
                            }
                            bVar = bVar3;
                        }
                    }
                    com.airbnb.lottie.model.i.b bVar4 = bVar;
                    jsonReader.m();
                    if (arrayList.size() == 1) {
                        arrayList.add(arrayList.get(0));
                    }
                    bVar = bVar4;
                    break;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    break;
            }
        }
        return new com.airbnb.lottie.model.content.e(strU, gradientType, cVarG, dVarH, fVarI, fVarI2, bVarE, lineCapType, lineJoinType, fR, arrayList, bVar, zQ);
    }
}
