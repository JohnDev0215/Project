package com.airbnb.lottie.v;

import androidx.annotation.Nullable;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c.a f922a = c.a.a("ty", "d");

    @Nullable
    static com.airbnb.lottie.t.k.b a(com.airbnb.lottie.v.k0.c cVar, com.airbnb.lottie.d dVar) {
        com.airbnb.lottie.t.k.b bVarA;
        String strU;
        cVar.l();
        int iS = 2;
        while (true) {
            bVarA = null;
            if (!cVar.p()) {
                strU = null;
                break;
            }
            int iY = cVar.y(f922a);
            if (iY == 0) {
                strU = cVar.u();
                break;
            }
            if (iY != 1) {
                cVar.z();
                cVar.A();
            } else {
                iS = cVar.s();
            }
        }
        if (strU == null) {
            return null;
        }
        switch (strU) {
            case "el":
                bVarA = e.a(cVar, dVar, iS);
                break;
            case "fl":
                bVarA = e0.a(cVar, dVar);
                break;
            case "gf":
                bVarA = m.a(cVar, dVar);
                break;
            case "gr":
                bVarA = f0.a(cVar, dVar);
                break;
            case "gs":
                bVarA = n.a(cVar, dVar);
                break;
            case "mm":
                bVarA = v.a(cVar);
                dVar.a("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case "rc":
                bVarA = a0.a(cVar, dVar);
                break;
            case "rp":
                bVarA = b0.a(cVar, dVar);
                break;
            case "sh":
                bVarA = g0.a(cVar, dVar);
                break;
            case "sr":
                bVarA = z.a(cVar, dVar);
                break;
            case "st":
                bVarA = h0.a(cVar, dVar);
                break;
            case "tm":
                bVarA = i0.a(cVar, dVar);
                break;
            case "tr":
                bVarA = c.g(cVar, dVar);
                break;
            default:
                com.airbnb.lottie.w.d.b("Unknown shape type " + strU);
                break;
        }
        while (cVar.p()) {
            cVar.A();
        }
        cVar.n();
        return bVarA;
    }
}
