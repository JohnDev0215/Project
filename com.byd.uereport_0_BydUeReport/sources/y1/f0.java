package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: ShapeGroupParser.java */
/* JADX INFO: loaded from: classes.dex */
public class f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9189a = JsonReader.a.a("nm", "hd", "it");

    public static v1.j a(JsonReader jsonReader, o1.e eVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        String strV = null;
        boolean zO = false;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9189a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                zO = jsonReader.o();
            } else if (iC != 2) {
                jsonReader.F();
            } else {
                jsonReader.g();
                while (jsonReader.n()) {
                    v1.b bVarA = g.a(jsonReader, eVar);
                    if (bVarA != null) {
                        arrayList.add(bVarA);
                    }
                }
                jsonReader.j();
            }
        }
        return new v1.j(strV, arrayList, zO);
    }
}
