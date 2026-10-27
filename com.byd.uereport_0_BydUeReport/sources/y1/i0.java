package y1;

import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeTrimPathParser.java */
/* JADX INFO: loaded from: classes.dex */
public class i0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9197a = JsonReader.a.a("s", "e", "o", "nm", "m", "hd");

    public static ShapeTrimPath a(JsonReader jsonReader, o1.e eVar) throws IOException {
        boolean zO = false;
        String strV = null;
        ShapeTrimPath.Type typeForId = null;
        u1.b bVarF = null;
        u1.b bVarF2 = null;
        u1.b bVarF3 = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9197a);
            if (iC == 0) {
                bVarF = d.f(jsonReader, eVar, false);
            } else if (iC == 1) {
                bVarF2 = d.f(jsonReader, eVar, false);
            } else if (iC == 2) {
                bVarF3 = d.f(jsonReader, eVar, false);
            } else if (iC == 3) {
                strV = jsonReader.v();
            } else if (iC == 4) {
                typeForId = ShapeTrimPath.Type.forId(jsonReader.r());
            } else if (iC != 5) {
                jsonReader.F();
            } else {
                zO = jsonReader.o();
            }
        }
        return new ShapeTrimPath(strV, typeForId, bVarF, bVarF2, bVarF3, zO);
    }
}
