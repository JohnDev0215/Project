package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: AnimatableTransformParser.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2759a = JsonReader.a.a("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static JsonReader.a f2760b = JsonReader.a.a("k");

    private static boolean a(com.airbnb.lottie.model.i.e eVar) {
        return eVar == null || (eVar.c() && eVar.b().get(0).f2818b.equals(0.0f, 0.0f));
    }

    private static boolean b(com.airbnb.lottie.model.i.m<PointF, PointF> mVar) {
        return mVar == null || (!(mVar instanceof com.airbnb.lottie.model.i.i) && mVar.c() && mVar.b().get(0).f2818b.equals(0.0f, 0.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean c(com.airbnb.lottie.model.i.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.v.a) bVar.b().get(0)).f2818b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean d(com.airbnb.lottie.model.i.g gVar) {
        return gVar == null || (gVar.c() && ((com.airbnb.lottie.v.d) ((com.airbnb.lottie.v.a) gVar.b().get(0)).f2818b).a(1.0f, 1.0f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean e(com.airbnb.lottie.model.i.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.v.a) bVar.b().get(0)).f2818b).floatValue() == 0.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean f(com.airbnb.lottie.model.i.b bVar) {
        return bVar == null || (bVar.c() && ((Float) ((com.airbnb.lottie.v.a) bVar.b().get(0)).f2818b).floatValue() == 0.0f);
    }

    public static com.airbnb.lottie.model.i.l g(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean z;
        boolean z2 = false;
        boolean z3 = jsonReader.w() == JsonReader.Token.BEGIN_OBJECT;
        if (z3) {
            jsonReader.l();
        }
        com.airbnb.lottie.model.i.b bVar = null;
        com.airbnb.lottie.model.i.e eVarA = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.g gVarJ = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.d dVarH = null;
        com.airbnb.lottie.model.i.b bVarF3 = null;
        com.airbnb.lottie.model.i.b bVarF4 = null;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2759a)) {
                case 0:
                    boolean z4 = z2;
                    jsonReader.l();
                    while (jsonReader.p()) {
                        if (jsonReader.y(f2760b) != 0) {
                            jsonReader.z();
                            jsonReader.A();
                        } else {
                            eVarA = a.a(jsonReader, dVar);
                        }
                    }
                    jsonReader.n();
                    z2 = z4;
                    continue;
                case 1:
                    mVarB = a.b(jsonReader, dVar);
                    continue;
                case 2:
                    gVarJ = d.j(jsonReader, dVar);
                    continue;
                case 3:
                    dVar.a("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    dVarH = d.h(jsonReader, dVar);
                    continue;
                case 6:
                    bVarF3 = d.f(jsonReader, dVar, z2);
                    continue;
                case 7:
                    bVarF4 = d.f(jsonReader, dVar, z2);
                    continue;
                case 8:
                    bVarF = d.f(jsonReader, dVar, z2);
                    continue;
                case 9:
                    bVarF2 = d.f(jsonReader, dVar, z2);
                    continue;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    continue;
            }
            com.airbnb.lottie.model.i.b bVarF5 = d.f(jsonReader, dVar, z2);
            if (bVarF5.b().isEmpty()) {
                bVarF5.b().add(new com.airbnb.lottie.v.a(dVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(dVar.f())));
            } else {
                if (((com.airbnb.lottie.v.a) bVarF5.b().get(0)).f2818b == 0) {
                    z = false;
                    bVarF5.b().set(0, new com.airbnb.lottie.v.a(dVar, Float.valueOf(0.0f), Float.valueOf(0.0f), null, 0.0f, Float.valueOf(dVar.f())));
                }
                z2 = z;
                bVar = bVarF5;
            }
            z = false;
            z2 = z;
            bVar = bVarF5;
        }
        if (z3) {
            jsonReader.n();
        }
        com.airbnb.lottie.model.i.e eVar = a(eVarA) ? null : eVarA;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVar = b(mVarB) ? null : mVarB;
        com.airbnb.lottie.model.i.b bVar2 = c(bVar) ? null : bVar;
        if (d(gVarJ)) {
            gVarJ = null;
        }
        return new com.airbnb.lottie.model.i.l(eVar, mVar, gVarJ, bVar2, dVarH, bVarF3, bVarF4, f(bVarF) ? null : bVarF, e(bVarF2) ? null : bVarF2);
    }
}
