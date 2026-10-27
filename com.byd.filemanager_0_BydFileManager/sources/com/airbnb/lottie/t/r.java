package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: KeyframesParser.java */
/* JADX INFO: loaded from: classes.dex */
class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static JsonReader.a f2791a = JsonReader.a.a("k");

    static <T> List<com.airbnb.lottie.v.a<T>> a(JsonReader jsonReader, com.airbnb.lottie.d dVar, float f, j0<T> j0Var) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.w() == JsonReader.Token.STRING) {
            dVar.a("Lottie doesn't support expressions.");
            return arrayList;
        }
        jsonReader.l();
        while (jsonReader.p()) {
            if (jsonReader.y(f2791a) != 0) {
                jsonReader.A();
            } else if (jsonReader.w() == JsonReader.Token.BEGIN_ARRAY) {
                jsonReader.k();
                if (jsonReader.w() == JsonReader.Token.NUMBER) {
                    arrayList.add(q.b(jsonReader, dVar, f, j0Var, false));
                } else {
                    while (jsonReader.p()) {
                        arrayList.add(q.b(jsonReader, dVar, f, j0Var, true));
                    }
                }
                jsonReader.m();
            } else {
                arrayList.add(q.b(jsonReader, dVar, f, j0Var, false));
            }
        }
        jsonReader.n();
        b(arrayList);
        return arrayList;
    }

    public static <T> void b(List<? extends com.airbnb.lottie.v.a<T>> list) {
        int i;
        T t;
        int size = list.size();
        int i2 = 0;
        while (true) {
            i = size - 1;
            if (i2 >= i) {
                break;
            }
            com.airbnb.lottie.v.a<T> aVar = list.get(i2);
            i2++;
            com.airbnb.lottie.v.a<T> aVar2 = list.get(i2);
            aVar.f = Float.valueOf(aVar2.f2821e);
            if (aVar.f2819c == null && (t = aVar2.f2818b) != null) {
                aVar.f2819c = t;
                if (aVar instanceof com.airbnb.lottie.r.c.h) {
                    ((com.airbnb.lottie.r.c.h) aVar).i();
                }
            }
        }
        com.airbnb.lottie.v.a<T> aVar3 = list.get(i);
        if ((aVar3.f2818b == null || aVar3.f2819c == null) && list.size() > 1) {
            list.remove(aVar3);
        }
    }
}
