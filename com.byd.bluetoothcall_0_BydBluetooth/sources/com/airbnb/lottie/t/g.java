package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ContentModelParser.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3148a = JsonReader.a.a("ty", "d");

    static com.airbnb.lottie.model.content.b a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        com.airbnb.lottie.model.content.b bVarA;
        String strV;
        jsonReader.m();
        int iT = 2;
        while (true) {
            bVarA = null;
            if (!jsonReader.q()) {
                strV = null;
                break;
            }
            int iZ = jsonReader.z(f3148a);
            if (iZ == 0) {
                strV = jsonReader.v();
                break;
            }
            if (iZ != 1) {
                jsonReader.A();
                jsonReader.B();
            } else {
                iT = jsonReader.t();
            }
        }
        if (strV == null) {
            return null;
        }
        switch (strV) {
            case "el":
                bVarA = e.a(jsonReader, dVar, iT);
                break;
            case "fl":
                bVarA = e0.a(jsonReader, dVar);
                break;
            case "gf":
                bVarA = m.a(jsonReader, dVar);
                break;
            case "gr":
                bVarA = f0.a(jsonReader, dVar);
                break;
            case "gs":
                bVarA = n.a(jsonReader, dVar);
                break;
            case "mm":
                bVarA = v.a(jsonReader);
                dVar.a("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case "rc":
                bVarA = a0.a(jsonReader, dVar);
                break;
            case "rp":
                bVarA = b0.a(jsonReader, dVar);
                break;
            case "sh":
                bVarA = g0.a(jsonReader, dVar);
                break;
            case "sr":
                bVarA = z.a(jsonReader, dVar);
                break;
            case "st":
                bVarA = h0.a(jsonReader, dVar);
                break;
            case "tm":
                bVarA = i0.a(jsonReader, dVar);
                break;
            case "tr":
                bVarA = c.g(jsonReader, dVar);
                break;
            default:
                com.airbnb.lottie.u.d.b("Unknown shape type " + strV);
                break;
        }
        while (jsonReader.q()) {
            jsonReader.B();
        }
        jsonReader.o();
        return bVarA;
    }
}
