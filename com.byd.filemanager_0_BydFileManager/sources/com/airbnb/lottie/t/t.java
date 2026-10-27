package com.airbnb.lottie.t;

import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: LottieCompositionMoshiParser.java */
/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2795a = JsonReader.a.a("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static JsonReader.a f2796b = JsonReader.a.a("id", "layers", "w", "h", "p", "u");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f2797c = JsonReader.a.a("list");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final JsonReader.a f2798d = JsonReader.a.a("cm", "tm", "dr");

    public static com.airbnb.lottie.d a(JsonReader jsonReader) throws IOException {
        HashMap map;
        ArrayList arrayList;
        JsonReader jsonReader2 = jsonReader;
        float fE = com.airbnb.lottie.u.h.e();
        b.d.d<Layer> dVar = new b.d.d<>();
        ArrayList arrayList2 = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        b.d.h<com.airbnb.lottie.model.c> hVar = new b.d.h<>();
        com.airbnb.lottie.d dVar2 = new com.airbnb.lottie.d();
        jsonReader.l();
        float fR = 0.0f;
        float fR2 = 0.0f;
        float fR3 = 0.0f;
        int iS = 0;
        int iS2 = 0;
        while (jsonReader.p()) {
            switch (jsonReader2.y(f2795a)) {
                case 0:
                    iS = jsonReader.s();
                    break;
                case 1:
                    iS2 = jsonReader.s();
                    break;
                case 2:
                    fR = (float) jsonReader.r();
                    break;
                case 3:
                    map = map4;
                    arrayList = arrayList3;
                    fR2 = ((float) jsonReader.r()) - 0.01f;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 4:
                    map = map4;
                    arrayList = arrayList3;
                    fR3 = (float) jsonReader.r();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 5:
                    String[] strArrSplit = jsonReader.u().split("\\.");
                    if (!com.airbnb.lottie.u.h.j(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), 4, 4, 0)) {
                        dVar2.a("Lottie only supports bodymovin >= 4.4.0");
                    }
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 6:
                    e(jsonReader2, dVar2, arrayList2, dVar);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 7:
                    b(jsonReader2, dVar2, map2, map3);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 8:
                    d(jsonReader2, map4);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 9:
                    c(jsonReader2, dVar2, hVar);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 10:
                    f(jsonReader2, dVar2, arrayList3);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                default:
                    map = map4;
                    arrayList = arrayList3;
                    jsonReader.z();
                    jsonReader.A();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
            }
            jsonReader2 = jsonReader;
        }
        dVar2.r(new Rect(0, 0, (int) (iS * fE), (int) (iS2 * fE)), fR, fR2, fR3, arrayList2, dVar, map2, map3, hVar, map4, arrayList3);
        return dVar2;
    }

    private static void b(JsonReader jsonReader, com.airbnb.lottie.d dVar, Map<String, List<Layer>> map, Map<String, com.airbnb.lottie.g> map2) throws IOException {
        jsonReader.k();
        while (jsonReader.p()) {
            ArrayList arrayList = new ArrayList();
            b.d.d dVar2 = new b.d.d();
            jsonReader.l();
            int iS = 0;
            int iS2 = 0;
            String strU = null;
            String strU2 = null;
            String strU3 = null;
            while (jsonReader.p()) {
                int iY = jsonReader.y(f2796b);
                if (iY == 0) {
                    strU = jsonReader.u();
                } else if (iY == 1) {
                    jsonReader.k();
                    while (jsonReader.p()) {
                        Layer layerB = s.b(jsonReader, dVar);
                        dVar2.j(layerB.b(), layerB);
                        arrayList.add(layerB);
                    }
                    jsonReader.m();
                } else if (iY == 2) {
                    iS = jsonReader.s();
                } else if (iY == 3) {
                    iS2 = jsonReader.s();
                } else if (iY == 4) {
                    strU2 = jsonReader.u();
                } else if (iY != 5) {
                    jsonReader.z();
                    jsonReader.A();
                } else {
                    strU3 = jsonReader.u();
                }
            }
            jsonReader.n();
            if (strU2 != null) {
                com.airbnb.lottie.g gVar = new com.airbnb.lottie.g(iS, iS2, strU, strU2, strU3);
                map2.put(gVar.d(), gVar);
            } else {
                map.put(strU, arrayList);
            }
        }
        jsonReader.m();
    }

    private static void c(JsonReader jsonReader, com.airbnb.lottie.d dVar, b.d.h<com.airbnb.lottie.model.c> hVar) throws IOException {
        jsonReader.k();
        while (jsonReader.p()) {
            com.airbnb.lottie.model.c cVarA = j.a(jsonReader, dVar);
            hVar.j(cVarA.hashCode(), cVarA);
        }
        jsonReader.m();
    }

    private static void d(JsonReader jsonReader, Map<String, com.airbnb.lottie.model.b> map) throws IOException {
        jsonReader.l();
        while (jsonReader.p()) {
            if (jsonReader.y(f2797c) != 0) {
                jsonReader.z();
                jsonReader.A();
            } else {
                jsonReader.k();
                while (jsonReader.p()) {
                    com.airbnb.lottie.model.b bVarA = k.a(jsonReader);
                    map.put(bVarA.b(), bVarA);
                }
                jsonReader.m();
            }
        }
        jsonReader.n();
    }

    private static void e(JsonReader jsonReader, com.airbnb.lottie.d dVar, List<Layer> list, b.d.d<Layer> dVar2) throws IOException {
        jsonReader.k();
        int i = 0;
        while (jsonReader.p()) {
            Layer layerB = s.b(jsonReader, dVar);
            if (layerB.d() == Layer.LayerType.IMAGE) {
                i++;
            }
            list.add(layerB);
            dVar2.j(layerB.b(), layerB);
            if (i > 4) {
                com.airbnb.lottie.u.d.c("You have " + i + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        jsonReader.m();
    }

    private static void f(JsonReader jsonReader, com.airbnb.lottie.d dVar, List<com.airbnb.lottie.model.g> list) throws IOException {
        jsonReader.k();
        while (jsonReader.p()) {
            String strU = null;
            jsonReader.l();
            float fR = 0.0f;
            float fR2 = 0.0f;
            while (jsonReader.p()) {
                int iY = jsonReader.y(f2798d);
                if (iY == 0) {
                    strU = jsonReader.u();
                } else if (iY == 1) {
                    fR = (float) jsonReader.r();
                } else if (iY != 2) {
                    jsonReader.z();
                    jsonReader.A();
                } else {
                    fR2 = (float) jsonReader.r();
                }
            }
            jsonReader.n();
            list.add(new com.airbnb.lottie.model.g(strU, fR, fR2));
        }
        jsonReader.m();
    }
}
