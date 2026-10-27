package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: AnimatablePathValueParser.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9176a = JsonReader.a.a("k", "x", "y");

    public static u1.e a(JsonReader jsonReader, o1.e eVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.g();
            while (jsonReader.n()) {
                arrayList.add(w.a(jsonReader, eVar));
            }
            jsonReader.j();
            r.b(arrayList);
        } else {
            arrayList.add(new b2.a(p.e(jsonReader, a2.h.e())));
        }
        return new u1.e(arrayList);
    }

    public static u1.m<PointF, PointF> b(JsonReader jsonReader, o1.e eVar) throws IOException {
        jsonReader.i();
        u1.e eVarA = null;
        u1.b bVarE = null;
        boolean z7 = false;
        u1.b bVarE2 = null;
        while (jsonReader.x() != JsonReader.Token.END_OBJECT) {
            int iC = jsonReader.C(f9176a);
            if (iC == 0) {
                eVarA = a(jsonReader, eVar);
            } else if (iC != 1) {
                if (iC != 2) {
                    jsonReader.E();
                    jsonReader.F();
                } else if (jsonReader.x() == JsonReader.Token.STRING) {
                    jsonReader.F();
                    z7 = true;
                } else {
                    bVarE = d.e(jsonReader, eVar);
                }
            } else if (jsonReader.x() == JsonReader.Token.STRING) {
                jsonReader.F();
                z7 = true;
            } else {
                bVarE2 = d.e(jsonReader, eVar);
            }
        }
        jsonReader.m();
        if (z7) {
            eVar.a("Lottie doesn't support expressions.");
        }
        return eVarA != null ? eVarA : new u1.i(bVarE2, bVarE);
    }
}
