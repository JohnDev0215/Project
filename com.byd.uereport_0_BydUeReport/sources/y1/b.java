package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: AnimatableTextPropertiesParser.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9178a = JsonReader.a.a("a");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static JsonReader.a f9179b = JsonReader.a.a("fc", "sc", "sw", "t");

    public static u1.k a(JsonReader jsonReader, o1.e eVar) throws IOException {
        jsonReader.i();
        u1.k kVarB = null;
        while (jsonReader.n()) {
            if (jsonReader.C(f9178a) != 0) {
                jsonReader.E();
                jsonReader.F();
            } else {
                kVarB = b(jsonReader, eVar);
            }
        }
        jsonReader.m();
        return kVarB == null ? new u1.k(null, null, null, null) : kVarB;
    }

    public static u1.k b(JsonReader jsonReader, o1.e eVar) throws IOException {
        jsonReader.i();
        u1.a aVarC = null;
        u1.a aVarC2 = null;
        u1.b bVarE = null;
        u1.b bVarE2 = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9179b);
            if (iC == 0) {
                aVarC = d.c(jsonReader, eVar);
            } else if (iC == 1) {
                aVarC2 = d.c(jsonReader, eVar);
            } else if (iC == 2) {
                bVarE = d.e(jsonReader, eVar);
            } else if (iC != 3) {
                jsonReader.E();
                jsonReader.F();
            } else {
                bVarE2 = d.e(jsonReader, eVar);
            }
        }
        jsonReader.m();
        return new u1.k(aVarC, aVarC2, bVarE, bVarE2);
    }
}
