package y1;

import android.graphics.Path;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ShapeFillParser.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9187a = JsonReader.a.a("nm", "c", "o", "fillEnabled", "r", "hd");

    public static v1.i a(JsonReader jsonReader, o1.e eVar) throws IOException {
        boolean zO = false;
        boolean zO2 = false;
        int iR = 1;
        String strV = null;
        u1.a aVarC = null;
        u1.d dVarH = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9187a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                aVarC = d.c(jsonReader, eVar);
            } else if (iC == 2) {
                dVarH = d.h(jsonReader, eVar);
            } else if (iC == 3) {
                zO = jsonReader.o();
            } else if (iC == 4) {
                iR = jsonReader.r();
            } else if (iC != 5) {
                jsonReader.E();
                jsonReader.F();
            } else {
                zO2 = jsonReader.o();
            }
        }
        return new v1.i(strV, zO, iR == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD, aVarC, dVarH, zO2);
    }
}
