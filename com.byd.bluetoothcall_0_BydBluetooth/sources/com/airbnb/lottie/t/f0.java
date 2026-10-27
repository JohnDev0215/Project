package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ShapeGroupParser.java */
/* JADX INFO: loaded from: classes.dex */
class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3147a = JsonReader.a.a("nm", "hd", "it");

    static com.airbnb.lottie.model.content.j a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        String strV = null;
        boolean zR = false;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3147a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                zR = jsonReader.r();
            } else if (iZ != 2) {
                jsonReader.B();
            } else {
                jsonReader.l();
                while (jsonReader.q()) {
                    com.airbnb.lottie.model.content.b bVarA = g.a(jsonReader, dVar);
                    if (bVarA != null) {
                        arrayList.add(bVarA);
                    }
                }
                jsonReader.n();
            }
        }
        return new com.airbnb.lottie.model.content.j(strV, arrayList, zR);
    }
}
