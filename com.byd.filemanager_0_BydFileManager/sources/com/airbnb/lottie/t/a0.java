package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RectangleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2755a = JsonReader.a.a("nm", "p", "s", "r", "hd");

    static com.airbnb.lottie.model.content.f a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        String strU = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        boolean zQ = false;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2755a);
            if (iY == 0) {
                strU = jsonReader.u();
            } else if (iY == 1) {
                mVarB = a.b(jsonReader, dVar);
            } else if (iY == 2) {
                fVarI = d.i(jsonReader, dVar);
            } else if (iY == 3) {
                bVarE = d.e(jsonReader, dVar);
            } else if (iY != 4) {
                jsonReader.A();
            } else {
                zQ = jsonReader.q();
            }
        }
        return new com.airbnb.lottie.model.content.f(strU, mVarB, fVarI, bVarE, zQ);
    }
}
