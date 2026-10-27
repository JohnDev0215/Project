package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: PolystarShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f2802a = JsonReader.a.a("nm", "sy", "pt", "p", "r", "or", "os", "ir", "is", "hd");

    static PolystarShape a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        boolean zQ = false;
        String strU = null;
        PolystarShape.Type typeForValue = null;
        com.airbnb.lottie.model.i.b bVarF = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.b bVarF2 = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        com.airbnb.lottie.model.i.b bVarE2 = null;
        com.airbnb.lottie.model.i.b bVarF3 = null;
        com.airbnb.lottie.model.i.b bVarF4 = null;
        while (jsonReader.p()) {
            switch (jsonReader.y(f2802a)) {
                case 0:
                    strU = jsonReader.u();
                    break;
                case 1:
                    typeForValue = PolystarShape.Type.forValue(jsonReader.s());
                    break;
                case 2:
                    bVarF = d.f(jsonReader, dVar, false);
                    break;
                case 3:
                    mVarB = a.b(jsonReader, dVar);
                    break;
                case 4:
                    bVarF2 = d.f(jsonReader, dVar, false);
                    break;
                case 5:
                    bVarE2 = d.e(jsonReader, dVar);
                    break;
                case 6:
                    bVarF4 = d.f(jsonReader, dVar, false);
                    break;
                case 7:
                    bVarE = d.e(jsonReader, dVar);
                    break;
                case 8:
                    bVarF3 = d.f(jsonReader, dVar, false);
                    break;
                case 9:
                    zQ = jsonReader.q();
                    break;
                default:
                    jsonReader.z();
                    jsonReader.A();
                    break;
            }
        }
        return new PolystarShape(strU, typeForValue, bVarF, mVarB, bVarF2, bVarE, bVarE2, bVarF3, bVarF4, zQ);
    }
}
