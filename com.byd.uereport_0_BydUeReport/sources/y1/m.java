package y1;

import android.graphics.Path;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: GradientFillParser.java */
/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9202a = JsonReader.a.a("nm", "g", "o", "t", "s", "e", "r", "hd");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final JsonReader.a f9203b = JsonReader.a.a("p", "k");

    public static v1.d a(JsonReader jsonReader, o1.e eVar) throws IOException {
        Path.FillType fillType = Path.FillType.WINDING;
        String strV = null;
        GradientType gradientType = null;
        u1.c cVarG = null;
        u1.d dVarH = null;
        u1.f fVarI = null;
        u1.f fVarI2 = null;
        boolean zO = false;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9202a)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    int iR = -1;
                    jsonReader.i();
                    while (jsonReader.n()) {
                        int iC = jsonReader.C(f9203b);
                        if (iC == 0) {
                            iR = jsonReader.r();
                        } else if (iC != 1) {
                            jsonReader.E();
                            jsonReader.F();
                        } else {
                            cVarG = d.g(jsonReader, eVar, iR);
                        }
                    }
                    jsonReader.m();
                    break;
                case 2:
                    dVarH = d.h(jsonReader, eVar);
                    break;
                case 3:
                    gradientType = jsonReader.r() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    fVarI = d.i(jsonReader, eVar);
                    break;
                case 5:
                    fVarI2 = d.i(jsonReader, eVar);
                    break;
                case 6:
                    fillType = jsonReader.r() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    zO = jsonReader.o();
                    break;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    break;
            }
        }
        return new v1.d(strV, gradientType, fillType, cVarG, dVarH, fVarI, fVarI2, null, null, zO);
    }
}
