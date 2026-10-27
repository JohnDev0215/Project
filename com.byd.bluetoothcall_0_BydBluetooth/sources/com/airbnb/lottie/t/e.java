package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: CircleShapeParser.java */
/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3144a = JsonReader.a.a("nm", "p", "s", "hd", "d");

    static com.airbnb.lottie.model.content.a a(JsonReader jsonReader, com.airbnb.lottie.d dVar, int i) throws IOException {
        boolean z = i == 3;
        boolean zR = false;
        String strV = null;
        com.airbnb.lottie.model.i.m<PointF, PointF> mVarB = null;
        com.airbnb.lottie.model.i.f fVarI = null;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3144a);
            if (iZ == 0) {
                strV = jsonReader.v();
            } else if (iZ == 1) {
                mVarB = a.b(jsonReader, dVar);
            } else if (iZ == 2) {
                fVarI = d.i(jsonReader, dVar);
            } else if (iZ == 3) {
                zR = jsonReader.r();
            } else if (iZ != 4) {
                jsonReader.A();
                jsonReader.B();
            } else {
                z = jsonReader.t() == 3;
            }
        }
        return new com.airbnb.lottie.model.content.a(strV, mVarB, fVarI, z, zR);
    }
}
