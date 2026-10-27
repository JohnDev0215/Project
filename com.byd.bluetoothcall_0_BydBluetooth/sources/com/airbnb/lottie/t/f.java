package com.airbnb.lottie.t;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

/* JADX INFO: compiled from: ColorParser.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements j0<Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f3146a = new f();

    private f() {
    }

    @Override // com.airbnb.lottie.t.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public Integer a(JsonReader jsonReader, float f) throws IOException {
        boolean z = jsonReader.x() == JsonReader.Token.BEGIN_ARRAY;
        if (z) {
            jsonReader.l();
        }
        double dS = jsonReader.s();
        double dS2 = jsonReader.s();
        double dS3 = jsonReader.s();
        double dS4 = jsonReader.s();
        if (z) {
            jsonReader.n();
        }
        if (dS <= 1.0d && dS2 <= 1.0d && dS3 <= 1.0d && dS4 <= 1.0d) {
            dS *= 255.0d;
            dS2 *= 255.0d;
            dS3 *= 255.0d;
            dS4 *= 255.0d;
        }
        return Integer.valueOf(Color.argb((int) dS4, (int) dS, (int) dS2, (int) dS3));
    }
}
