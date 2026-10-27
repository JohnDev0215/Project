package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: RectangleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3135a = JsonReader.a.a("nm", "p", "s", "r", "hd");

    static com.airbnb.lottie.model.content.f a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        String strV = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        boolean zR = false;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3135a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                mVarB = a.b(jsonReader, dVar);
            } else if (iZ == 2) {
                fVarI = d.i(jsonReader, dVar);
            } else if (iZ == 3) {
                bVarE = d.e(jsonReader, dVar);
            } else if (iZ != 4) {
                jsonReader.B();
            } else {
                zR = jsonReader.r();
            }
        }
        return new com.airbnb.lottie.model.content.f(strV, mVarB, fVarI, bVarE, zR);
    }
}
