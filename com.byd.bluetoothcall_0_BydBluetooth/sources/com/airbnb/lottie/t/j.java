package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: FontCharacterParser.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3156a = JsonReader.a.a("ch", "size", "w", "style", "fFamily", "data");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f3157b = JsonReader.a.a("shapes");

    static com.airbnb.lottie.model.c a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.m();
        String strV = null;
        String strV2 = null;
        double dS = 0.0d;
        double dS2 = 0.0d;
        char cCharAt = 0;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3156a);
            if (iZ == 0) {
                cCharAt = jsonReader.v().charAt(0);
            } else if (iZ == 1) {
                dS = jsonReader.s();
            } else if (iZ == 2) {
                dS2 = jsonReader.s();
            } else if (iZ == 3) {
                strV = jsonReader.v();
            } else if (iZ == 4) {
                strV2 = jsonReader.v();
            } else if (iZ != 5) {
                jsonReader.A();
                jsonReader.B();
            } else {
                jsonReader.m();
                while (jsonReader.q()) {
                    if (jsonReader.z(f3157b) != 0) {
                        jsonReader.A();
                        jsonReader.B();
                    } else {
                        jsonReader.l();
                        while (jsonReader.q()) {
                            arrayList.add((com.airbnb.lottie.model.content.j) g.a(jsonReader, dVar));
                        }
                        jsonReader.n();
                    }
                }
                jsonReader.o();
            }
        }
        jsonReader.o();
        return new com.airbnb.lottie.model.c(arrayList, cCharAt, dS, dS2, strV, strV2);
    }
}
