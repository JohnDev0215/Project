package com.airbnb.lottie.v;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.t.l.d;
import com.airbnb.lottie.v.k0.c;
import com.byd.dilink.provider.CarStatusSettingConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c.a f957a = c.a.a("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static c.a f958b = c.a.a(CarStatusSettingConstants.DiCareRecord.DICARE_ID, "layers", "w", "h", "p", "u");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c.a f959c = c.a.a("list");
    private static final c.a d = c.a.a("cm", "tm", "dr");

    public static com.airbnb.lottie.d a(com.airbnb.lottie.v.k0.c cVar) {
        HashMap map;
        ArrayList arrayList;
        com.airbnb.lottie.v.k0.c cVar2 = cVar;
        float fE = com.airbnb.lottie.w.h.e();
        LongSparseArray<com.airbnb.lottie.t.l.d> longSparseArray = new LongSparseArray<>();
        ArrayList arrayList2 = new ArrayList();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        ArrayList arrayList3 = new ArrayList();
        SparseArrayCompat<com.airbnb.lottie.t.d> sparseArrayCompat = new SparseArrayCompat<>();
        com.airbnb.lottie.d dVar = new com.airbnb.lottie.d();
        cVar.l();
        float fR = 0.0f;
        float fR2 = 0.0f;
        float fR3 = 0.0f;
        int iS = 0;
        int iS2 = 0;
        while (cVar.p()) {
            switch (cVar2.y(f957a)) {
                case 0:
                    iS = cVar.s();
                    break;
                case 1:
                    iS2 = cVar.s();
                    break;
                case 2:
                    fR = (float) cVar.r();
                    break;
                case 3:
                    map = map4;
                    arrayList = arrayList3;
                    fR2 = ((float) cVar.r()) - 0.01f;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 4:
                    map = map4;
                    arrayList = arrayList3;
                    fR3 = (float) cVar.r();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 5:
                    String[] strArrSplit = cVar.u().split("\\.");
                    if (!com.airbnb.lottie.w.h.j(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]), Integer.parseInt(strArrSplit[2]), 4, 4, 0)) {
                        dVar.a("Lottie only supports bodymovin >= 4.4.0");
                    }
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 6:
                    e(cVar2, dVar, arrayList2, longSparseArray);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 7:
                    b(cVar2, dVar, map2, map3);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 8:
                    d(cVar2, map4);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 9:
                    c(cVar2, dVar, sparseArrayCompat);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                case 10:
                    f(cVar2, dVar, arrayList3);
                    map = map4;
                    arrayList = arrayList3;
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
                default:
                    map = map4;
                    arrayList = arrayList3;
                    cVar.z();
                    cVar.A();
                    map4 = map;
                    arrayList3 = arrayList;
                    break;
            }
            cVar2 = cVar;
        }
        dVar.r(new Rect(0, 0, (int) (iS * fE), (int) (iS2 * fE)), fR, fR2, fR3, arrayList2, longSparseArray, map2, map3, sparseArrayCompat, map4, arrayList3);
        return dVar;
    }

    private static void b(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, Map<String, List<com.airbnb.lottie.t.l.d>> map, Map<String, com.airbnb.lottie.g> map2) {
        cVar.k();
        while (cVar.p()) {
            ArrayList arrayList = new ArrayList();
            LongSparseArray longSparseArray = new LongSparseArray();
            cVar.l();
            int iS = 0;
            int iS2 = 0;
            String strU = null;
            String strU2 = null;
            String strU3 = null;
            while (cVar.p()) {
                int iY = cVar.y(f958b);
                if (iY == 0) {
                    strU = cVar.u();
                } else if (iY == 1) {
                    cVar.k();
                    while (cVar.p()) {
                        com.airbnb.lottie.t.l.d dVarB = s.b(cVar, dVar);
                        longSparseArray.put(dVarB.b(), dVarB);
                        arrayList.add(dVarB);
                    }
                    cVar.m();
                } else if (iY == 2) {
                    iS = cVar.s();
                } else if (iY == 3) {
                    iS2 = cVar.s();
                } else if (iY == 4) {
                    strU2 = cVar.u();
                } else if (iY != 5) {
                    cVar.z();
                    cVar.A();
                } else {
                    strU3 = cVar.u();
                }
            }
            cVar.n();
            if (strU2 != null) {
                com.airbnb.lottie.g gVar = new com.airbnb.lottie.g(iS, iS2, strU, strU2, strU3);
                map2.put(gVar.d(), gVar);
            } else {
                map.put(strU, arrayList);
            }
        }
        cVar.m();
    }

    private static void c(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, SparseArrayCompat<com.airbnb.lottie.t.d> sparseArrayCompat) {
        cVar.k();
        while (cVar.p()) {
            com.airbnb.lottie.t.d dVarA = j.a(cVar, dVar);
            sparseArrayCompat.put(dVarA.hashCode(), dVarA);
        }
        cVar.m();
    }

    private static void d(com.airbnb.lottie.v.k0.c cVar, Map<String, com.airbnb.lottie.t.c> map) {
        cVar.l();
        while (cVar.p()) {
            if (cVar.y(f959c) != 0) {
                cVar.z();
                cVar.A();
            } else {
                cVar.k();
                while (cVar.p()) {
                    com.airbnb.lottie.t.c cVarA = k.a(cVar);
                    map.put(cVarA.b(), cVarA);
                }
                cVar.m();
            }
        }
        cVar.n();
    }

    private static void e(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, List<com.airbnb.lottie.t.l.d> list, LongSparseArray<com.airbnb.lottie.t.l.d> longSparseArray) {
        cVar.k();
        int i = 0;
        while (cVar.p()) {
            com.airbnb.lottie.t.l.d dVarB = s.b(cVar, dVar);
            if (dVarB.d() == d.a.IMAGE) {
                i++;
            }
            list.add(dVarB);
            longSparseArray.put(dVarB.b(), dVarB);
            if (i > 4) {
                com.airbnb.lottie.w.d.b("You have " + i + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        cVar.m();
    }

    private static void f(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, List<com.airbnb.lottie.t.h> list) {
        cVar.k();
        while (cVar.p()) {
            String strU = null;
            cVar.l();
            float fR = 0.0f;
            float fR2 = 0.0f;
            while (cVar.p()) {
                int iY = cVar.y(d);
                if (iY == 0) {
                    strU = cVar.u();
                } else if (iY == 1) {
                    fR = (float) cVar.r();
                } else if (iY != 2) {
                    cVar.z();
                    cVar.A();
                } else {
                    fR2 = (float) cVar.r();
                }
            }
            cVar.n();
            list.add(new com.airbnb.lottie.t.h(strU, fR, fR2));
        }
        cVar.m();
    }
}
