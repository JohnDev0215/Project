package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ContentModelParser.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9190a = JsonReader.a.a("ty", "d");

    public static v1.b a(JsonReader jsonReader, o1.e eVar) throws IOException {
        v1.b bVarA;
        String strV;
        jsonReader.i();
        int iR = 2;
        while (true) {
            bVarA = null;
            if (!jsonReader.n()) {
                strV = null;
                break;
            }
            int iC = jsonReader.C(f9190a);
            if (iC == 0) {
                strV = jsonReader.v();
                break;
            }
            if (iC != 1) {
                jsonReader.E();
                jsonReader.F();
            } else {
                iR = jsonReader.r();
            }
        }
        if (strV == null) {
            return null;
        }
        switch (strV) {
            case "el":
                bVarA = e.a(jsonReader, eVar, iR);
                break;
            case "fl":
                bVarA = e0.a(jsonReader, eVar);
                break;
            case "gf":
                bVarA = m.a(jsonReader, eVar);
                break;
            case "gr":
                bVarA = f0.a(jsonReader, eVar);
                break;
            case "gs":
                bVarA = n.a(jsonReader, eVar);
                break;
            case "mm":
                bVarA = v.a(jsonReader);
                eVar.a("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case "rc":
                bVarA = a0.a(jsonReader, eVar);
                break;
            case "rp":
                bVarA = b0.a(jsonReader, eVar);
                break;
            case "sh":
                bVarA = g0.a(jsonReader, eVar);
                break;
            case "sr":
                bVarA = z.a(jsonReader, eVar);
                break;
            case "st":
                bVarA = h0.a(jsonReader, eVar);
                break;
            case "tm":
                bVarA = i0.a(jsonReader, eVar);
                break;
            case "tr":
                bVarA = c.g(jsonReader, eVar);
                break;
            default:
                a2.d.c("Unknown shape type " + strV);
                break;
        }
        while (jsonReader.n()) {
            jsonReader.F();
        }
        jsonReader.m();
        return bVarA;
    }
}
