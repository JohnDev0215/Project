package com.airbnb.lottie.v;

import com.airbnb.lottie.v.k0.c;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static c.a f953a = c.a.a("k");

    static <T> List<com.airbnb.lottie.x.a<T>> a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar, float f, j0<T> j0Var) {
        ArrayList arrayList = new ArrayList();
        if (cVar.w() == c.b.STRING) {
            dVar.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        cVar.l();
        while (cVar.p()) {
            if (cVar.y(f953a) != 0) {
                cVar.A();
            } else if (cVar.w() == c.b.BEGIN_ARRAY) {
                cVar.k();
                if (cVar.w() == c.b.NUMBER) {
                    arrayList.add(q.b(cVar, dVar, f, j0Var, false));
                } else {
                    while (cVar.p()) {
                        arrayList.add(q.b(cVar, dVar, f, j0Var, true));
                    }
                }
                cVar.m();
            } else {
                arrayList.add(q.b(cVar, dVar, f, j0Var, false));
            }
        }
        cVar.n();
        b(arrayList);
        return arrayList;
    }

    public static <T> void b(List<? extends com.airbnb.lottie.x.a<T>> list) {
        int i;
        T t;
        int size = list.size();
        int i2 = 0;
        while (true) {
            i = size - 1;
            if (i2 >= i) {
                break;
            }
            com.airbnb.lottie.x.a<T> aVar = list.get(i2);
            i2++;
            com.airbnb.lottie.x.a<T> aVar2 = list.get(i2);
            aVar.f = Float.valueOf(aVar2.e);
            if (aVar.f977c == null && (t = aVar2.f976b) != null) {
                aVar.f977c = t;
                if (aVar instanceof com.airbnb.lottie.r.c.h) {
                    ((com.airbnb.lottie.r.c.h) aVar).i();
                }
            }
        }
        com.airbnb.lottie.x.a<T> aVar3 = list.get(i);
        if ((aVar3.f976b == null || aVar3.f977c == null) && list.size() > 1) {
            list.remove(aVar3);
        }
    }
}
