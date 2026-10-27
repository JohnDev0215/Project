package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: FontParser.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9200a = JsonReader.a.a("fFamily", "fName", "fStyle", "ascent");

    public static t1.b a(JsonReader jsonReader) throws IOException {
        jsonReader.i();
        String strV = null;
        String strV2 = null;
        float fQ = 0.0f;
        String strV3 = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9200a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                strV3 = jsonReader.v();
            } else if (iC == 2) {
                strV2 = jsonReader.v();
            } else if (iC != 3) {
                jsonReader.E();
                jsonReader.F();
            } else {
                fQ = (float) jsonReader.q();
            }
        }
        jsonReader.m();
        return new t1.b(strV, strV3, strV2, fQ);
    }
}
