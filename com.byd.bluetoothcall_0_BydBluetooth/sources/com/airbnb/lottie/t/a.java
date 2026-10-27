package com.airbnb.lottie.t;

import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: compiled from: AnimatablePathValueParser.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static JsonReader.a f3134a = JsonReader.a.a("k", "x", "y");

    public static com.airbnb.lottie.model.i.e a(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.l();
            while (jsonReader.q()) {
                arrayList.add(w.a(jsonReader, dVar));
            }
            jsonReader.n();
            r.b(arrayList);
        } else {
            arrayList.add(new com.airbnb.lottie.v.a(p.e(jsonReader, com.airbnb.lottie.u.h.e())));
        }
        return new com.airbnb.lottie.model.i.e(arrayList);
    }

    static com.airbnb.lottie.model.i.m<PointF, PointF> b(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        jsonReader.m();
        com.airbnb.lottie.model.i.e eVarA = null;
        com.airbnb.lottie.model.i.b bVarE = null;
        boolean z = false;
        com.airbnb.lottie.model.i.b bVarE2 = null;
        while (jsonReader.x() != JsonReader.Token.END_OBJECT) {
            int iZ = jsonReader.z(f3134a);
            if (iZ == 0) {
                eVarA = a(jsonReader, dVar);
            } else if (iZ != 1) {
                if (iZ != 2) {
                    jsonReader.A();
                    jsonReader.B();
                } else if (jsonReader.x() == JsonReader.Token.STRING) {
                    jsonReader.B();
                    z = true;
                } else {
                    bVarE = d.e(jsonReader, dVar);
                }
            } else if (jsonReader.x() == JsonReader.Token.STRING) {
                jsonReader.B();
                z = true;
            } else {
                bVarE2 = d.e(jsonReader, dVar);
            }
        }
        jsonReader.o();
        if (z) {
            dVar.a("Lottie doesn't support expressions.");
        }
        return eVarA != null ? eVarA : new com.airbnb.lottie.model.i.i(bVarE2, bVarE);
    }
}
