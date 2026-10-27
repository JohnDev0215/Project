package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: CircleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2764a = JsonReader.a.a("nm", "p", "s", "hd", "d");

    static com.airbnb.lottie.model.content.a a(JsonReader jsonReader, com.airbnb.lottie.d dVar, int i) throws IOException {
        boolean z = i == 3;
        boolean zQ = false;
        String strU = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2764a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                mVarB = a.b(jsonReader, dVar);
            } else if (iY == 2) {
                fVarI = d.i(jsonReader, dVar);
            } else if (iY == 3) {
                zQ = jsonReader.q();
            } else if (iY != 4) {
                jsonReader.z();
                jsonReader.A();
            } else {
                z = jsonReader.s() == 3;
            }
        }
        return new com.airbnb.lottie.model.content.a(strU, mVarB, fVarI, z, zQ);
    }
}
