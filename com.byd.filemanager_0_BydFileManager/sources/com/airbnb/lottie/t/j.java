package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: FontCharacterParser.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2776a = JsonReader.a.a("ch", "size", "w", "style", "fFamily", "data");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final JsonReader.a f2777b = JsonReader.a.a("shapes");

    static com.airbnb.lottie.model.c a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.l();
        String strU = null;
        String strU2 = null;
        double dR = 0.0d;
        double dR2 = 0.0d;
        char cCharAt = 0;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2776a);
            if (iY == 0) {
                cCharAt = jsonReader.u().charAt(0);
            } else if (iY == 1) {
                dR = jsonReader.r();
            } else if (iY == 2) {
                dR2 = jsonReader.r();
            } else if (iY == 3) {
                strU = jsonReader.u();
            } else if (iY == 4) {
                strU2 = jsonReader.u();
            } else if (iY != 5) {
                jsonReader.z();
                jsonReader.A();
            } else {
                jsonReader.l();
                while (jsonReader.p()) {
                    if (jsonReader.y(f2777b) != 0) {
                        jsonReader.z();
                        jsonReader.A();
                    } else {
                        jsonReader.k();
                        while (jsonReader.p()) {
                            arrayList.add((com.airbnb.lottie.model.content.j) g.a(jsonReader, dVar));
                        }
                        jsonReader.m();
                    }
                }
                jsonReader.n();
            }
        }
        jsonReader.n();
        return new com.airbnb.lottie.model.c(arrayList, cCharAt, dR, dR2, strU, strU2);
    }
}
