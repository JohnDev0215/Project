package com.airbnb.lottie.t;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ColorParser.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements j0<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f2766a = new f();

    private f() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f) throws IOException {
        boolean z = jsonReader.w() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.k();
        }
        double dR = jsonReader.r();
        double dR2 = jsonReader.r();
        double dR3 = jsonReader.r();
        double dR4 = jsonReader.r();
        if (z) {
            jsonReader.m();
        }
        if (dR <= 1.0d && dR2 <= 1.0d && dR3 <= 1.0d) {
            dR *= 255.0d;
            dR2 *= 255.0d;
            dR3 *= 255.0d;
            if (dR4 <= 1.0d) {
                dR4 *= 255.0d;
            }
        }
        return Integer.valueOf(Color.argb((int) dR4, (int) dR, (int) dR2, (int) dR3));
    }
}
