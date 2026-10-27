package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: FontCharacterParser.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9198a = JsonReader.a.a("ch", "size", "w", "style", "fFamily", "data");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9199b = JsonReader.a.a("shapes");

    public static t1.c a(JsonReader jsonReader, o1.e eVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.i();
        String strV = null;
        String strV2 = null;
        double dQ = 0.0d;
        double dQ2 = 0.0d;
        char cCharAt = 0;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9198a);
            if (iC == 0) {
                cCharAt = jsonReader.v().charAt(0);
            } else if (iC == 1) {
                dQ = jsonReader.q();
            } else if (iC == 2) {
                dQ2 = jsonReader.q();
            } else if (iC == 3) {
                strV = jsonReader.v();
            } else if (iC == 4) {
                strV2 = jsonReader.v();
            } else if (iC != 5) {
                jsonReader.E();
                jsonReader.F();
            } else {
                jsonReader.i();
                while (jsonReader.n()) {
                    if (jsonReader.C(f9199b) != 0) {
                        jsonReader.E();
                        jsonReader.F();
                    } else {
                        jsonReader.g();
                        while (jsonReader.n()) {
                            arrayList.add((v1.j) g.a(jsonReader, eVar));
                        }
                        jsonReader.j();
                    }
                }
                jsonReader.m();
            }
        }
        jsonReader.m();
        return new t1.c(arrayList, cCharAt, dQ, dQ2, strV, strV2);
    }
}
