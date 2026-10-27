package y1;

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
    public static final JsonReader.a f9217a = JsonReader.a.a("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static JsonReader.a f9218b = JsonReader.a.a("id", "layers", "w", "h", "p", "u");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final JsonReader.a f9219c = JsonReader.a.a("list");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final JsonReader.a f9220d = JsonReader.a.a("cm", "tm", "dr");

    public static o1.e a(JsonReader jsonReader) throws IOException {
        HashMap map;
        ArrayList arrayList;
        JsonReader jsonReader2 = jsonReader;
        float fE = a2.h.e();
        v.d<Layer> dVar = new v.d<>();
        ArrayList arrayList2 = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        v.h<t1.c> hVar = new v.h<>();
        o1.e eVar = new o1.e();
        jsonReader.i();
        float fQ = 0.0f;
        float fQ2 = 0.0f;
        float fQ3 = 0.0f;
        int iR = 0;
        int iR2 = 0;
        while (jsonReader.n()) {
            switch (jsonReader2.C(f9217a)) {
                case 0:
                    iR = jsonReader.r();
                    break;
                case 1:
                    iR2 = jsonReader.r();
                    break;
                case 2:
                    fQ = (float) jsonReader.q();
                    break;
                case 3:
                    map = map4;
                    arrayList = arrayList3;
                    fQ2 = ((float) jsonReader.q()) - 0.01f;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 4:
                    map = map4;
                    arrayList = arrayList3;
                    fQ3 = (float) jsonReader.q();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 5:
                    String[] strArrSplit = jsonReader.v().split("\\.");
                    if (!a2.h.j(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), 4, 4, 0)) {
                        eVar.a("Lottie only supports bodymovin >= 4.4.0");
                    }
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 6:
                    e(jsonReader2, eVar, arrayList2, dVar);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 7:
                    b(jsonReader2, eVar, map2, map3);
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
                    c(jsonReader2, eVar, hVar);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 10:
                    f(jsonReader2, eVar, arrayList3);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                default:
                    map = map4;
                    arrayList = arrayList3;
                    jsonReader.E();
                    jsonReader.F();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
            }
            jsonReader2 = jsonReader;
        }
        eVar.r(new Rect(0, 0, (int) (iR * fE), (int) (iR2 * fE)), fQ, fQ2, fQ3, arrayList2, dVar, map2, map3, hVar, map4, arrayList3);
        return eVar;
    }

    public static void b(JsonReader jsonReader, o1.e eVar, Map<String, List<Layer>> map, Map<String, o1.h> map2) throws IOException {
        jsonReader.g();
        while (jsonReader.n()) {
            ArrayList arrayList = new ArrayList();
            v.d dVar = new v.d();
            jsonReader.i();
            int iR = 0;
            int iR2 = 0;
            String strV = null;
            String strV2 = null;
            String strV3 = null;
            while (jsonReader.n()) {
                int iC = jsonReader.C(f9218b);
                if (iC == 0) {
                    strV = jsonReader.v();
                } else if (iC == 1) {
                    jsonReader.g();
                    while (jsonReader.n()) {
                        Layer layerB = s.b(jsonReader, eVar);
                        dVar.k(layerB.b(), layerB);
                        arrayList.add(layerB);
                    }
                    jsonReader.j();
                } else if (iC == 2) {
                    iR = jsonReader.r();
                } else if (iC == 3) {
                    iR2 = jsonReader.r();
                } else if (iC == 4) {
                    strV2 = jsonReader.v();
                } else if (iC != 5) {
                    jsonReader.E();
                    jsonReader.F();
                } else {
                    strV3 = jsonReader.v();
                }
            }
            jsonReader.m();
            if (strV2 != null) {
                o1.h hVar = new o1.h(iR, iR2, strV, strV2, strV3);
                map2.put(hVar.d(), hVar);
            } else {
                map.put(strV, arrayList);
            }
        }
        jsonReader.j();
    }

    public static void c(JsonReader jsonReader, o1.e eVar, v.h<t1.c> hVar) throws IOException {
        jsonReader.g();
        while (jsonReader.n()) {
            t1.c cVarA = j.a(jsonReader, eVar);
            hVar.j(cVarA.hashCode(), cVarA);
        }
        jsonReader.j();
    }

    public static void d(JsonReader jsonReader, Map<String, t1.b> map) throws IOException {
        jsonReader.i();
        while (jsonReader.n()) {
            if (jsonReader.C(f9219c) != 0) {
                jsonReader.E();
                jsonReader.F();
            } else {
                jsonReader.g();
                while (jsonReader.n()) {
                    t1.b bVarA = k.a(jsonReader);
                    map.put(bVarA.b(), bVarA);
                }
                jsonReader.j();
            }
        }
        jsonReader.m();
    }

    public static void e(JsonReader jsonReader, o1.e eVar, List<Layer> list, v.d<Layer> dVar) throws IOException {
        jsonReader.g();
        int i8 = 0;
        while (jsonReader.n()) {
            Layer layerB = s.b(jsonReader, eVar);
            if (layerB.d() == Layer.LayerType.IMAGE) {
                i8++;
            }
            list.add(layerB);
            dVar.k(layerB.b(), layerB);
            if (i8 > 4) {
                a2.d.c("You have " + i8 + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        jsonReader.j();
    }

    public static void f(JsonReader jsonReader, o1.e eVar, List<t1.g> list) throws IOException {
        jsonReader.g();
        while (jsonReader.n()) {
            String strV = null;
            jsonReader.i();
            float fQ = 0.0f;
            float fQ2 = 0.0f;
            while (jsonReader.n()) {
                int iC = jsonReader.C(f9220d);
                if (iC == 0) {
                    strV = jsonReader.v();
                } else if (iC == 1) {
                    fQ = (float) jsonReader.q();
                } else if (iC != 2) {
                    jsonReader.E();
                    jsonReader.F();
                } else {
                    fQ2 = (float) jsonReader.q();
                }
            }
            jsonReader.m();
            list.add(new t1.g(strV, fQ, fQ2));
        }
        jsonReader.j();
    }
}
