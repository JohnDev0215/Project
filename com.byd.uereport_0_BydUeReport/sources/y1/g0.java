package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapePathParser.java */
/* JADX INFO: loaded from: classes.dex */
public class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9191a = JsonReader.a.a("nm", "ind", "ks", "hd");

    public static v1.k a(JsonReader jsonReader, o1.e eVar) throws IOException {
        int iR = 0;
        String strV = null;
        u1.h hVarK = null;
        boolean zO = false;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9191a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                iR = jsonReader.r();
            } else if (iC == 2) {
                hVarK = d.k(jsonReader, eVar);
            } else if (iC != 3) {
                jsonReader.F();
            } else {
                zO = jsonReader.o();
            }
        }
        return new v1.k(strV, iR, hVarK, zO);
    }
}
