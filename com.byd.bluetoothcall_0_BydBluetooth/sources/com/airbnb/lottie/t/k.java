package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: FontParser.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3158a = JsonReader.a.a("fFamily", "fName", "fStyle", "ascent");

    static com.airbnb.lottie.model.b a(JsonReader jsonReader) throws IOException {
        jsonReader.m();
        String strV = null;
        String strV2 = null;
        float fS = 0.0f;
        String strV3 = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3158a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                strV3 = jsonReader.v();
            } else if (iZ == 2) {
                strV2 = jsonReader.v();
            } else if (iZ != 3) {
                jsonReader.A();
                jsonReader.B();
            } else {
                fS = (float) jsonReader.s();
            }
        }
        jsonReader.o();
        return new com.airbnb.lottie.model.b(strV, strV3, strV2, fS);
    }
}
