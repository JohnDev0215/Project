package com.airbnb.lottie.t;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: LayerParser.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2792a = JsonReader.a.a("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2793b = JsonReader.a.a("d", "a");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f2794c = JsonReader.a.a("nm");

    public static Layer a(com.airbnb.lottie.d dVar) {
        Rect rectB = dVar.b();
        return new Layer(Collections.emptyList(), dVar, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new com.airbnb.lottie.model.i.l(), 0, 0, 0, 0.0f, 0.0f, rectB.width(), rectB.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false);
    }

    public static Layer b(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList;
        ArrayList arrayList2;
        float f;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        jsonReader.l();
        Float fValueOf = Float.valueOf(1.0f);
        Float fValueOf2 = Float.valueOf(0.0f);
        Layer.MatteType matteType2 = matteType;
        float fR = 1.0f;
        int iS = 0;
        int iS2 = 0;
        int color = 0;
        int iS3 = 0;
        int iS4 = 0;
        boolean zQ = false;
        Layer.LayerType layerType = null;
        String strU = null;
        com.airbnb.lottie.model.i.l lVarG = null;
        com.airbnb.lottie.model.i.j jVarD = null;
        com.airbnb.lottie.model.i.k kVarA = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        float fR2 = 0.0f;
        float fR3 = 0.0f;
        float fR4 = 0.0f;
        long jS = -1;
        long jS2 = 0;
        String strU2 = null;
        String strU3 = "UNSET";
        while (jsonReader.p()) {
            switch (jsonReader.y(f2792a)) {
                case 0:
                    strU3 = jsonReader.u();
                    break;
                case 1:
                    jS2 = jsonReader.s();
                    break;
                case 2:
                    strU = jsonReader.u();
                    break;
                case 3:
                    int iS5 = jsonReader.s();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (iS5 < layerType.ordinal()) {
                        layerType = Layer.LayerType.values()[iS5];
                    }
                    break;
                case 4:
                    jS = jsonReader.s();
                    break;
                case 5:
                    iS = (int) (jsonReader.s() * com.airbnb.lottie.u.h.e());
                    break;
                case 6:
                    iS2 = (int) (jsonReader.s() * com.airbnb.lottie.u.h.e());
                    break;
                case 7:
                    color = Color.parseColor(jsonReader.u());
                    break;
                case 8:
                    lVarG = c.g(jsonReader, dVar);
                    break;
                case 9:
                    matteType2 = Layer.MatteType.values()[jsonReader.s()];
                    dVar.q(1);
                    break;
                case 10:
                    jsonReader.k();
                    while (jsonReader.p()) {
                        arrayList3.add(u.a(jsonReader, dVar));
                    }
                    dVar.q(arrayList3.size());
                    jsonReader.m();
                    break;
                case 11:
                    jsonReader.k();
                    while (jsonReader.p()) {
                        com.airbnb.lottie.model.content.b bVarA = g.a(jsonReader, dVar);
                        if (bVarA != null) {
                            arrayList4.add(bVarA);
                        }
                    }
                    jsonReader.m();
                    break;
                case 12:
                    jsonReader.l();
                    while (jsonReader.p()) {
                        int iY = jsonReader.y(f2793b);
                        if (iY == 0) {
                            jVarD = d.d(jsonReader, dVar);
                        } else if (iY != 1) {
                            jsonReader.z();
                            jsonReader.A();
                        } else {
                            jsonReader.k();
                            if (jsonReader.p()) {
                                kVarA = b.a(jsonReader, dVar);
                            }
                            while (jsonReader.p()) {
                                jsonReader.A();
                            }
                            jsonReader.m();
                        }
                    }
                    jsonReader.n();
                    break;
                case 13:
                    jsonReader.k();
                    ArrayList arrayList5 = new ArrayList();
                    while (jsonReader.p()) {
                        jsonReader.l();
                        while (jsonReader.p()) {
                            if (jsonReader.y(f2794c) != 0) {
                                jsonReader.z();
                                jsonReader.A();
                            } else {
                                arrayList5.add(jsonReader.u());
                            }
                        }
                        jsonReader.n();
                    }
                    jsonReader.m();
                    dVar.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList5);
                    break;
                case 14:
                    fR = (float) jsonReader.r();
                    break;
                case 15:
                    fR4 = (float) jsonReader.r();
                    break;
                case 16:
                    iS3 = (int) (jsonReader.s() * com.airbnb.lottie.u.h.e());
                    break;
                case 17:
                    iS4 = (int) (jsonReader.s() * com.airbnb.lottie.u.h.e());
                    break;
                case 18:
                    fR2 = (float) jsonReader.r();
                    break;
                case 19:
                    fR3 = (float) jsonReader.r();
                    break;
                case 20:
                    bVarF = d.f(jsonReader, dVar, false);
                    break;
                case 21:
                    strU2 = jsonReader.u();
                    break;
                case 22:
                    zQ = jsonReader.q();
                    break;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    break;
            }
        }
        jsonReader.n();
        float f2 = fR2 / fR;
        float f3 = fR3 / fR;
        ArrayList arrayList6 = new ArrayList();
        if (f2 > 0.0f) {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            arrayList2.add(new com.airbnb.lottie.v.a(dVar, fValueOf2, fValueOf2, null, 0.0f, Float.valueOf(f2)));
            f = 0.0f;
        } else {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            f = 0.0f;
        }
        if (f3 <= f) {
            f3 = dVar.f();
        }
        arrayList2.add(new com.airbnb.lottie.v.a(dVar, fValueOf, fValueOf, null, f2, Float.valueOf(f3)));
        arrayList2.add(new com.airbnb.lottie.v.a(dVar, fValueOf2, fValueOf2, null, f3, Float.valueOf(Float.MAX_VALUE)));
        if (strU3.endsWith(".ai") || "ai".equals(strU2)) {
            dVar.a("Convert your Illustrator layers to shape layers.");
        }
        return new Layer(arrayList4, dVar, strU3, jS2, layerType, jS, strU, arrayList, lVarG, iS, iS2, color, fR, fR4, iS3, iS4, jVarD, kVarA, arrayList2, matteType2, bVarF, zQ);
    }
}
