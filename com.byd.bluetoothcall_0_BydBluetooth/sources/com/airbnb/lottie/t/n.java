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
    private static JsonReader.a f3162a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f3163b = JsonReader.a.a("p", "k");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f3164c = JsonReader.a.a("n", "v");

    static com.airbnb.lottie.model.content.e a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        com.airbnb.lottie.model.i.c cVar;
        ArrayList arrayList = new ArrayList();
        float fS = 0.0f;
        String strV = null;
        GradientType gradientType = null;
        com.airbnb.lottie.model.i.c cVarG = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.f fVarI2 = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        com.airbnb.lottie.model.i.b bVar = null;
        boolean zR = false;
        while (jsonReader.q()) {
            switch (jsonReader.z(f3162a)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    int iT = -1;
                    jsonReader.m();
                    while (jsonReader.q()) {
                        int iZ = jsonReader.z(f3163b);
                        if (iZ != 0) {
                            cVar = cVarG;
                            if (iZ != 1) {
                                jsonReader.A();
                                jsonReader.B();
                            } else {
                                cVarG = d.g(jsonReader, dVar, iT);
                            }
                        } else {
                            cVar = cVarG;
                            iT = jsonReader.t();
                        }
                        cVarG = cVar;
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
                    bVarE = d.e(jsonReader, dVar);
                    break;
                case 7:
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.t() - 1];
                    break;
                case 8:
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.t() - 1];
                    break;
                case 9:
                    fS = (float) jsonReader.s();
                    break;
                case 10:
                    zR = jsonReader.r();
                    break;
                case 11:
                    jsonReader.l();
                    while (jsonReader.q()) {
                        jsonReader.m();
                        String strV2 = null;
                        com.airbnb.lottie.model.i.b bVarE2 = null;
                        while (jsonReader.q()) {
                            int iZ2 = jsonReader.z(f3164c);
                            if (iZ2 != 0) {
                                com.airbnb.lottie.model.i.b bVar2 = bVar;
                                if (iZ2 != 1) {
                                    jsonReader.A();
                                    jsonReader.B();
                                } else {
                                    bVarE2 = d.e(jsonReader, dVar);
                                }
                                bVar = bVar2;
                            } else {
                                strV2 = jsonReader.v();
                            }
                        }
                        com.airbnb.lottie.model.i.b bVar3 = bVar;
                        jsonReader.o();
                        if (strV2.equals("o")) {
                            bVar = bVarE2;
                        } else {
                            if (strV2.equals("d") || strV2.equals("g")) {
                                dVar.t(true);
                                arrayList.add(bVarE2);
                            }
                            bVar = bVar3;
                        }
                    }
                    com.airbnb.lottie.model.i.b bVar4 = bVar;
                    jsonReader.n();
                    if (arrayList.size() == 1) {
                        arrayList.add(arrayList.get(0));
                    }
                    bVar = bVar4;
                    break;
                default:
                    jsonReader.A();
                    jsonReader.B();
                    break;
            }
        }
        return new com.airbnb.lottie.model.content.e(strV, gradientType, cVarG, dVarH, fVarI, fVarI2, bVarE, lineCapType, lineJoinType, fS, arrayList, bVar, zR);
    }
}
