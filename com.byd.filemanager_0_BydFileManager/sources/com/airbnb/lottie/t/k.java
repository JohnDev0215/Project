package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: FontParser.java */
/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2778a = JsonReader.a.a("fFamily", "fName", "fStyle", "ascent");

    static com.airbnb.lottie.model.b a(JsonReader jsonReader) throws IOException {
        jsonReader.l();
        String strU = null;
        String strU2 = null;
        float fR = 0.0f;
        String strU3 = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2778a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                strU3 = jsonReader.u();
            } else if (iY == 2) {
                strU2 = jsonReader.u();
            } else if (iY != 3) {
                jsonReader.z();
                jsonReader.A();
            } else {
                fR = (float) jsonReader.r();
            }
        }
        jsonReader.n();
        return new com.airbnb.lottie.model.b(strU, strU3, strU2, fR);
    }
}
