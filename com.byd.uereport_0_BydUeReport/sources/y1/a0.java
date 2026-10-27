package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RectangleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9177a = JsonReader.a.a("nm", "p", "s", "r", "hd");

    public static v1.f a(JsonReader jsonReader, o1.e eVar) throws IOException {
        String strV = null;
        u1.m<PointF, PointF> mVarB = null;
        u1.f fVarI = null;
        u1.b bVarE = null;
        boolean zO = false;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9177a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                mVarB = a.b(jsonReader, eVar);
            } else if (iC == 2) {
                fVarI = d.i(jsonReader, eVar);
            } else if (iC == 3) {
                bVarE = d.e(jsonReader, eVar);
            } else if (iC != 4) {
                jsonReader.F();
            } else {
                zO = jsonReader.o();
            }
        }
        return new v1.f(strV, mVarB, fVarI, bVarE, zO);
    }
}
