package y1;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: AnimatableTransformParser.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static JsonReader.a f9181a = JsonReader.a.a("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static JsonReader.a f9182b = JsonReader.a.a("k");

    public static boolean a(u1.e eVar) {
        return eVar == null || (eVar.c() && eVar.b().get(0).f2045b.equals(0.0f, 0.0f));
    }

    public static boolean b(u1.m<PointF, PointF> mVar) {
        return mVar == null || (!(mVar instanceof u1.i) && mVar.c() && mVar.b().get(0).f2045b.equals(0.0f, 0.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean c(u1.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((b2.a) bVar.b().get(0)).f2045b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean d(u1.g gVar) {
        return gVar == null || (gVar.c() && ((b2.d) ((b2.a) gVar.b().get(0)).f2045b).a(1.0f, 1.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean e(u1.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((b2.a) bVar.b().get(0)).f2045b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean f(u1.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((b2.a) bVar.b().get(0)).f2045b).floatValue() == 0.0f);
    }

    public static u1.l g(JsonReader jsonReader, o1.e eVar) throws IOException {
        boolean z7;
        boolean z8 = false;
        boolean z9 = jsonReader.x() == JsonReader.Token.BEGIN_OBJECT;
        if (z9) {
            jsonReader.i();
        }
        u1.b bVar = null;
        u1.e eVarA = null;
        u1.m<PointF, PointF> mVarB = null;
        u1.g gVarJ = null;
        u1.b bVarF = null;
        u1.b bVarF2 = null;
        u1.d dVarH = null;
        u1.b bVarF3 = null;
        u1.b bVarF4 = null;
        while (jsonReader.n()) {
            switch (jsonReader.C(f9181a)) {
                case 0:
                    boolean z10 = z8;
                    jsonReader.i();
                    while (jsonReader.n()) {
                        if (jsonReader.C(f9182b) != 0) {
                            jsonReader.E();
                            jsonReader.F();
                        } else {
                            eVarA = a.a(jsonReader, eVar);
                        }
                    }
                    jsonReader.m();
                    z8 = z10;
                    continue;
                case 1:
                    mVarB = a.b(jsonReader, eVar);
                    continue;
                case 2:
                    gVarJ = d.j(jsonReader, eVar);
                    continue;
                case 3:
                    eVar.a("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    dVarH = d.h(jsonReader, eVar);
                    continue;
                case 6:
                    bVarF3 = d.f(jsonReader, eVar, z8);
                    continue;
                case 7:
                    bVarF4 = d.f(jsonReader, eVar, z8);
                    continue;
                case 8:
                    bVarF = d.f(jsonReader, eVar, z8);
                    continue;
                case 9:
                    bVarF2 = d.f(jsonReader, eVar, z8);
                    continue;
                default:
                    jsonReader.E();
                    jsonReader.F();
                    continue;
            }
            u1.b bVarF5 = d.f(jsonReader, eVar, z8);
            if (bVarF5.b().isEmpty()) {
                bVarF5.b().add(new b2.a(eVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(eVar.f())));
            } else {
                if (((b2.a) bVarF5.b().get(0)).f2045b == 0) {
                    z7 = false;
                    bVarF5.b().set(0, new b2.a(eVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(eVar.f())));
                }
                z8 = z7;
                bVar = bVarF5;
            }
            z7 = false;
            z8 = z7;
            bVar = bVarF5;
        }
        if (z9) {
            jsonReader.m();
        }
        u1.e eVar2 = a(eVarA) ? null : eVarA;
        u1.m<PointF, PointF> mVar = b(mVarB) ? null : mVarB;
        u1.b bVar2 = c(bVar) ? null : bVar;
        if (d(gVarJ)) {
            gVarJ = null;
        }
        return new u1.l(eVar2, mVar, gVarJ, bVar2, dVarH, bVarF3, bVarF4, f(bVarF) ? null : bVarF, e(bVarF2) ? null : bVarF2);
    }
}
