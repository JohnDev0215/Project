package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: CircleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9186a = JsonReader.a.a("nm", "p", "s", "hd", "d");

    public static v1.a a(JsonReader jsonReader, o1.e eVar, int i8) throws IOException {
        boolean z7 = i8 == 3;
        boolean zO = false;
        String strV = null;
        u1.m<PointF, PointF> mVarB = null;
        u1.f fVarI = null;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9186a);
            if (iC == 0) {
                strV = jsonReader.v();
            } else if (iC == 1) {
                mVarB = a.b(jsonReader, eVar);
            } else if (iC == 2) {
                fVarI = d.i(jsonReader, eVar);
            } else if (iC == 3) {
                zO = jsonReader.o();
            } else if (iC != 4) {
                jsonReader.E();
                jsonReader.F();
            } else {
                z7 = jsonReader.r() == 3;
            }
        }
        return new v1.a(strV, mVarB, fVarI, z7, zO);
    }
}
