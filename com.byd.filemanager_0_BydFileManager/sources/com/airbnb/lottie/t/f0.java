package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ShapeGroupParser.java */
/* JADX INFO: loaded from: classes.dex */
class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2767a = JsonReader.a.a("nm", "hd", "it");

    static com.airbnb.lottie.model.content.j a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        String strU = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2767a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                zQ = jsonReader.q();
            } else if (iY != 2) {
                jsonReader.A();
            } else {
                jsonReader.k();
                while (jsonReader.p()) {
                    com.airbnb.lottie.model.content.b bVarA = g.a(jsonReader, dVar);
                    if (bVarA != null) {
                        arrayList.add(bVarA);
                    }
                }
                jsonReader.m();
            }
        }
        return new com.airbnb.lottie.model.content.j(strU, arrayList, zQ);
    }
}
