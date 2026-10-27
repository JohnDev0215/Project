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
    private static final JsonReader.a f3175a = JsonReader.a.a("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static JsonReader.a f3176b = JsonReader.a.a("id", "layers", "w", "h", "p", "u");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final JsonReader.a f3177c = JsonReader.a.a("list");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final JsonReader.a f3178d = JsonReader.a.a("cm", "tm", "dr");

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
        jsonReader.m();
        float fS = 0.0f;
        float fS2 = 0.0f;
        float fS3 = 0.0f;
        int iT = 0;
        int iT2 = 0;
        while (jsonReader.q()) {
            switch (jsonReader2.z(f3175a)) {
                case 0:
                    iT = jsonReader.t();
                    break;
                case 1:
                    iT2 = jsonReader.t();
                    break;
                case 2:
                    fS = (float) jsonReader.s();
                    break;
                case 3:
                    map = map4;
                    arrayList = arrayList3;
                    fS2 = ((float) jsonReader.s()) - 0.01f;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 4:
                    map = map4;
                    arrayList = arrayList3;
                    fS3 = (float) jsonReader.s();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 5:
                    String[] strArrSplit = jsonReader.v().split("\\.");
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
                    jsonReader.A();
                    jsonReader.B();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
            }
            jsonReader2 = jsonReader;
        }
        dVar2.r(new Rect(0, 0, (int) (iT * fE), (int) (iT2 * fE)), fS, fS2, fS3, arrayList2, dVar, map2, map3, hVar, map4, arrayList3);
        return dVar2;
    }

    private static void b(JsonReader jsonReader, com.airbnb.lottie.d dVar, Map<String, List<Layer>> map, Map<String, com.airbnb.lottie.g> map2) throws IOException {
        jsonReader.l();
        while (jsonReader.q()) {
            ArrayList arrayList = new ArrayList();
            b.d.d dVar2 = new b.d.d();
            jsonReader.m();
            int iT = 0;
            int iT2 = 0;
            String strV = null;
            String strV2 = null;
            String strV3 = null;
            while (jsonReader.q()) {
                int iZ = jsonReader.z(f3176b);
                if (iZ == 0) {
                    strV = jsonReader.v();
                } else if (iZ == 1) {
                    jsonReader.l();
                    while (jsonReader.q()) {
                        Layer layerB = s.b(jsonReader, dVar);
                        dVar2.j(layerB.b(), layerB);
                        arrayList.add(layerB);
                    }
                    jsonReader.n();
                } else if (iZ == 2) {
                    iT = jsonReader.t();
                } else if (iZ == 3) {
                    iT2 = jsonReader.t();
                } else if (iZ == 4) {
                    strV2 = jsonReader.v();
                } else if (iZ != 5) {
                    jsonReader.A();
                    jsonReader.B();
                } else {
                    strV3 = jsonReader.v();
                }
            }
            jsonReader.o();
            if (strV2 != null) {
                com.airbnb.lottie.g gVar = new com.airbnb.lottie.g(iT, iT2, strV, strV2, strV3);
                map2.put(gVar.d(), gVar);
            } else {
                map.put(strV, arrayList);
            }
        }
        jsonReader.n();
    }

    private static void c(JsonReader jsonReader, com.airbnb.lottie.d dVar, b.d.h<com.airbnb.lottie.model.c> hVar) throws IOException {
        jsonReader.l();
        while (jsonReader.q()) {
            com.airbnb.lottie.model.c cVarA = j.a(jsonReader, dVar);
            hVar.j(cVarA.hashCode(), cVarA);
        }
        jsonReader.n();
    }

    private static void d(JsonReader jsonReader, Map<String, com.airbnb.lottie.model.b> map) throws IOException {
        jsonReader.m();
        while (jsonReader.q()) {
            if (jsonReader.z(f3177c) != 0) {
                jsonReader.A();
                jsonReader.B();
            } else {
                jsonReader.l();
                while (jsonReader.q()) {
                    com.airbnb.lottie.model.b bVarA = k.a(jsonReader);
                    map.put(bVarA.b(), bVarA);
                }
                jsonReader.n();
            }
        }
        jsonReader.o();
    }

    private static void e(JsonReader jsonReader, com.airbnb.lottie.d dVar, List<Layer> list, b.d.d<Layer> dVar2) throws IOException {
        jsonReader.l();
        int i = 0;
        while (jsonReader.q()) {
            Layer layerB = s.b(jsonReader, dVar);
            if (layerB.d() == Layer.LayerType.IMAGE) {
                i++;
            }
            list.add(layerB);
            dVar2.j(layerB.b(), layerB);
            if (i > 4) {
                com.airbnb.lottie.u.d.b("You have " + i + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        jsonReader.n();
    }

    private static void f(JsonReader jsonReader, com.airbnb.lottie.d dVar, List<com.airbnb.lottie.model.g> list) throws IOException {
        jsonReader.l();
        while (jsonReader.q()) {
            String strV = null;
            jsonReader.m();
            float fS = 0.0f;
            float fS2 = 0.0f;
            while (jsonReader.q()) {
                int iZ = jsonReader.z(f3178d);
                if (iZ == 0) {
                    strV = jsonReader.v();
                } else if (iZ == 1) {
                    fS = (float) jsonReader.s();
                } else if (iZ != 2) {
                    jsonReader.A();
                    jsonReader.B();
                } else {
                    fS2 = (float) jsonReader.s();
                }
            }
            jsonReader.o();
            list.add(new com.airbnb.lottie.model.g(strV, fS, fS2));
        }
        jsonReader.n();
    }
}
