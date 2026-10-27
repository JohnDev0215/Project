package com.airbnb.lottie.t;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: compiled from: LayerParser.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3172a = JsonReader.a.a("nm", "ind", "refId", "ty", "parent", "sw", BydAdbHelper.COMMAND_SH, "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f3173b = JsonReader.a.a("d", "a");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f3174c = JsonReader.a.a("nm");

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
        jsonReader.m();
        Float fValueOf = Float.valueOf(1.0f);
        Float fValueOf2 = Float.valueOf(0.0f);
        Layer.MatteType matteType2 = matteType;
        float fS = 1.0f;
        int iT = 0;
        int iT2 = 0;
        int color = 0;
        int iT3 = 0;
        int iT4 = 0;
        boolean zR = false;
        Layer.LayerType layerType = null;
        String strV = null;
        com.airbnb.lottie.model.i.l lVarG = null;
        com.airbnb.lottie.model.i.j jVarD = null;
        com.airbnb.lottie.model.i.k kVarA = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        float fS2 = 0.0f;
        float fS3 = 0.0f;
        float fS4 = 0.0f;
        long jT = -1;
        long jT2 = 0;
        String strV2 = null;
        String strV3 = "UNSET";
        while (jsonReader.q()) {
            switch (jsonReader.z(f3172a)) {
                case 0:
                    strV3 = jsonReader.v();
                    break;
                case 1:
                    jT2 = jsonReader.t();
                    break;
                case 2:
                    strV = jsonReader.v();
                    break;
                case 3:
                    int iT5 = jsonReader.t();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (iT5 < layerType.ordinal()) {
                        layerType = Layer.LayerType.values()[iT5];
                    }
                    break;
                case 4:
                    jT = jsonReader.t();
                    break;
                case 5:
                    iT = (int) (jsonReader.t() * com.airbnb.lottie.u.h.e());
                    break;
                case 6:
                    iT2 = (int) (jsonReader.t() * com.airbnb.lottie.u.h.e());
                    break;
                case 7:
                    color = Color.parseColor(jsonReader.v());
                    break;
                case 8:
                    lVarG = c.g(jsonReader, dVar);
                    break;
                case 9:
                    matteType2 = Layer.MatteType.values()[jsonReader.t()];
                    dVar.q(1);
                    break;
                case 10:
                    jsonReader.l();
                    while (jsonReader.q()) {
                        arrayList3.add(u.a(jsonReader, dVar));
                    }
                    dVar.q(arrayList3.size());
                    jsonReader.n();
                    break;
                case 11:
                    jsonReader.l();
                    while (jsonReader.q()) {
                        com.airbnb.lottie.model.content.b bVarA = g.a(jsonReader, dVar);
                        if (bVarA != null) {
                            arrayList4.add(bVarA);
                        }
                    }
                    jsonReader.n();
                    break;
                case 12:
                    jsonReader.m();
                    while (jsonReader.q()) {
                        int iZ = jsonReader.z(f3173b);
                        if (iZ == 0) {
                            jVarD = d.d(jsonReader, dVar);
                        } else if (iZ != 1) {
                            jsonReader.A();
                            jsonReader.B();
                        } else {
                            jsonReader.l();
                            if (jsonReader.q()) {
                                kVarA = b.a(jsonReader, dVar);
                            }
                            while (jsonReader.q()) {
                                jsonReader.B();
                            }
                            jsonReader.n();
                        }
                    }
                    jsonReader.o();
                    break;
                case 13:
                    jsonReader.l();
                    ArrayList arrayList5 = new ArrayList();
                    while (jsonReader.q()) {
                        jsonReader.m();
                        while (jsonReader.q()) {
                            if (jsonReader.z(f3174c) != 0) {
                                jsonReader.A();
                                jsonReader.B();
                            } else {
                                arrayList5.add(jsonReader.v());
                            }
                        }
                        jsonReader.o();
                    }
                    jsonReader.n();
                    dVar.a("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: " + arrayList5);
                    break;
                case 14:
                    fS = (float) jsonReader.s();
                    break;
                case 15:
                    fS4 = (float) jsonReader.s();
                    break;
                case 16:
                    iT3 = (int) (jsonReader.t() * com.airbnb.lottie.u.h.e());
                    break;
                case 17:
                    iT4 = (int) (jsonReader.t() * com.airbnb.lottie.u.h.e());
                    break;
                case 18:
                    fS2 = (float) jsonReader.s();
                    break;
                case 19:
                    fS3 = (float) jsonReader.s();
                    break;
                case 20:
                    bVarF = d.f(jsonReader, dVar, false);
                    break;
                case 21:
                    strV2 = jsonReader.v();
                    break;
                case 22:
                    zR = jsonReader.r();
                    break;
                default:
                    jsonReader.A();
                    jsonReader.B();
                    break;
            }
        }
        jsonReader.o();
        float f2 = fS2 / fS;
        float f3 = fS3 / fS;
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
        if (strV3.endsWith(".ai") || "ai".equals(strV2)) {
            dVar.a("Convert your Illustrator layers to shape layers.");
        }
        return new Layer(arrayList4, dVar, strV3, jT2, layerType, jT, strV, arrayList, lVarG, iT, iT2, color, fS, fS4, iT3, iT4, jVarD, kVarA, arrayList2, matteType2, bVarF, zR);
    }
}
