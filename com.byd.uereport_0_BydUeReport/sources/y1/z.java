package y1;

import android.graphics.PointF;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: PolystarShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9224a = JsonReader.a.a("nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd");

    public static PolystarShape a(JsonReader jsonReader, o1.e eVar) throws IOException {
        boolean zO = false;
        String strV = null;
        PolystarShape.Type typeForValue = null;
        u1.b bVarF = null;
        u1.m<PointF, PointF> mVarB = null;
        u1.b bVarF2 = null;
        u1.b bVarE = null;
        u1.b bVarE2 = null;
        u1.b bVarF3 = null;
        u1.b bVarF4 = null;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9224a)) {
                case 0:
                    strV = jsonReader.v();
                    break;
                case 1:
                    typeForValue = PolystarShape.Type.forValue(jsonReader.r());
                    break;
                case 2:
                    bVarF = d.f(jsonReader, eVar, false);
                    break;
                case 3:
                    mVarB = a.b(jsonReader, eVar);
                    break;
                case 4:
                    bVarF2 = d.f(jsonReader, eVar, false);
                    break;
                case 5:
                    bVarE2 = d.e(jsonReader, eVar);
                    break;
                case 6:
                    bVarF4 = d.f(jsonReader, eVar, false);
                    break;
                case 7:
                    bVarE = d.e(jsonReader, eVar);
                    break;
                case 8:
                    bVarF3 = d.f(jsonReader, eVar, false);
                    break;
                case 9:
                    zO = jsonReader.o();
                    break;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    break;
            }
        }
        return new PolystarShape(strV, typeForValue, bVarF, mVarB, bVarF2, bVarE, bVarE2, bVarF3, bVarF4, zO);
    }
}
