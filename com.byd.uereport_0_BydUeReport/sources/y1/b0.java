package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RepeaterParser.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9180a = JsonReader.a.a("nm", "c", "o", "tr", "hd");

    public static v1.g a(JsonReader jsonReader, o1.e eVar) throws IOException {
        boolean zO = false;
        String strV = null;
        u1.b bVarF = null;
        u1.b bVarF2 = null;
        u1.l lVarG = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9180a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                bVarF = d.f(jsonReader, eVar, false);
            } else if (iC == 2) {
                bVarF2 = d.f(jsonReader, eVar, false);
            } else if (iC == 3) {
                lVarG = c.g(jsonReader, eVar);
            } else if (iC != 4) {
                jsonReader.F();
            } else {
                zO = jsonReader.o();
            }
        }
        return new v1.g(strV, bVarF, bVarF2, lVarG, zO);
    }
}
