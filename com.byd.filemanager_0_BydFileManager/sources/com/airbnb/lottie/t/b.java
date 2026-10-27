package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: AnimatableTextPropertiesParser.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2756a = JsonReader.a.a("a");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static JsonReader.a f2757b = JsonReader.a.a("fc", "sc", "sw", "t");

    public static com.airbnb.lottie.model.i.k a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        jsonReader.l();
        com.airbnb.lottie.model.i.k kVarB = null;
        while (jsonReader.p()) {
            if (jsonReader.y(f2756a) != 0) {
                jsonReader.z();
                jsonReader.A();
            } else {
                kVarB = b(jsonReader, dVar);
            }
        }
        jsonReader.n();
        return kVarB == null ? new com.airbnb.lottie.model.i.k(null, null, null, null) : kVarB;
    }

    private static com.airbnb.lottie.model.i.k b(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        jsonReader.l();
        com.airbnb.lottie.model.i.a aVarC = null;
        com.airbnb.lottie.model.i.a aVarC2 = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        com.airbnb.lottie.model.i.b bVarE2 = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2757b);
            if (iY == 0) {
                aVarC = d.c(jsonReader, dVar);
            } else if (iY == 1) {
                aVarC2 = d.c(jsonReader, dVar);
            } else if (iY == 2) {
                bVarE = d.e(jsonReader, dVar);
            } else if (iY != 3) {
                jsonReader.z();
                jsonReader.A();
            } else {
                bVarE2 = d.e(jsonReader, dVar);
            }
        }
        jsonReader.n();
        return new com.airbnb.lottie.model.i.k(aVarC, aVarC2, bVarE, bVarE2);
    }
}
