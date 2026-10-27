package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: AnimatablePathValueParser.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f2754a = JsonReader.a.a("k", "x", "y");

    public static com.airbnb.lottie.model.i.e a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.w() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.k();
            while (jsonReader.p()) {
                arrayList.add(w.a(jsonReader, dVar));
            }
            jsonReader.m();
            r.b(arrayList);
        } else {
            arrayList.add(new com.airbnb.lottie.v.a(p.e(jsonReader, com.airbnb.lottie.u.h.e())));
        }
        return new com.airbnb.lottie.model.i.e(arrayList);
    }

    static com.airbnb.lottie.model.i.m<PointF, PointF> b(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        jsonReader.l();
        com.airbnb.lottie.model.i.e eVarA = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        boolean z = false;
        com.airbnb.lottie.model.i.b bVarE2 = null;
        while (jsonReader.w() != JsonReader.Token.END_OBJECT) {
            int iY = jsonReader.y(f2754a);
            if (iY == 0) {
                eVarA = a(jsonReader, dVar);
            } else if (iY != 1) {
                if (iY != 2) {
                    jsonReader.z();
                    jsonReader.A();
                } else if (jsonReader.w() == JsonReader.Token.STRING) {
                    jsonReader.A();
                    z = true;
                } else {
                    bVarE = d.e(jsonReader, dVar);
                }
            } else if (jsonReader.w() == JsonReader.Token.STRING) {
                jsonReader.A();
                z = true;
            } else {
                bVarE2 = d.e(jsonReader, dVar);
            }
        }
        jsonReader.n();
        if (z) {
            dVar.a("Lottie doesn't support expressions.");
        }
        return eVarA != null ? eVarA : new com.airbnb.lottie.model.i.i(bVarE2, bVarE);
    }
}
