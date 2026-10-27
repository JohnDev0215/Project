package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ContentModelParser.java */
/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2768a = JsonReader.a.a("ty", "d");

    static com.airbnb.lottie.model.content.b a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        com.airbnb.lottie.model.content.b bVarA;
        String strU;
        jsonReader.l();
        int iS = 2;
        while (true) {
            bVarA = null;
            if (!jsonReader.p()) {
                strU = null;
                break;
            }
            int iY = jsonReader.y(f2768a);
            if (iY == 0) {
                strU = jsonReader.u();
                break;
            }
            if (iY != 1) {
                jsonReader.z();
                jsonReader.A();
            } else {
                iS = jsonReader.s();
            }
        }
        if (strU == null) {
            return null;
        }
        switch (strU) {
            case "el":
                bVarA = e.a(jsonReader, dVar, iS);
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
                com.airbnb.lottie.u.d.c("Unknown shape type " + strU);
                break;
        }
        while (jsonReader.p()) {
            jsonReader.A();
        }
        jsonReader.n();
        return bVarA;
    }
}
