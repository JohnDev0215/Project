package com.airbnb.lottie.t;

import android.graphics.Color;
import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonUtils.java */
/* JADX INFO: loaded from: classes.dex */
class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final JsonReader.a f3166a = JsonReader.a.a("x", "y");

    /* JADX INFO: compiled from: JsonUtils.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3167a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            f3167a = iArr;
            try {
                iArr[JsonReader.Token.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3167a[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3167a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static PointF a(JsonReader jsonReader, float f) throws IOException {
        jsonReader.l();
        float fS = (float) jsonReader.s();
        float fS2 = (float) jsonReader.s();
        while (jsonReader.x() != JsonReader.Token.END_ARRAY) {
            jsonReader.B();
        }
        jsonReader.n();
        return new PointF(fS * f, fS2 * f);
    }

    private static PointF b(JsonReader jsonReader, float f) throws IOException {
        float fS = (float) jsonReader.s();
        float fS2 = (float) jsonReader.s();
        while (jsonReader.q()) {
            jsonReader.B();
        }
        return new PointF(fS * f, fS2 * f);
    }

    private static PointF c(JsonReader jsonReader, float f) throws IOException {
        jsonReader.m();
        float fG = 0.0f;
        float fG2 = 0.0f;
        while (jsonReader.q()) {
            int iZ = jsonReader.z(f3166a);
            if (iZ == 0) {
                fG = g(jsonReader);
            } else if (iZ != 1) {
                jsonReader.A();
                jsonReader.B();
            } else {
                fG2 = g(jsonReader);
            }
        }
        jsonReader.o();
        return new PointF(fG * f, fG2 * f);
    }

    static int d(JsonReader jsonReader) throws IOException {
        jsonReader.l();
        int iS = (int) (jsonReader.s() * 255.0d);
        int iS2 = (int) (jsonReader.s() * 255.0d);
        int iS3 = (int) (jsonReader.s() * 255.0d);
        while (jsonReader.q()) {
            jsonReader.B();
        }
        jsonReader.n();
        return Color.argb(255, iS, iS2, iS3);
    }

    static PointF e(JsonReader jsonReader, float f) throws IOException {
        int i = a.f3167a[jsonReader.x().ordinal()];
        if (i == 1) {
            return b(jsonReader, f);
        }
        if (i == 2) {
            return a(jsonReader, f);
        }
        if (i == 3) {
            return c(jsonReader, f);
        }
        throw new IllegalArgumentException("Unknown point starts with " + jsonReader.x());
    }

    static List<PointF> f(JsonReader jsonReader, float f) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.l();
        while (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.l();
            arrayList.add(e(jsonReader, f));
            jsonReader.n();
        }
        jsonReader.n();
        return arrayList;
    }

    static float g(JsonReader jsonReader) throws IOException {
        JsonReader.Token tokenX = jsonReader.x();
        int i = a.f3167a[tokenX.ordinal()];
        if (i == 1) {
            return (float) jsonReader.s();
        }
        if (i != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + tokenX);
        }
        jsonReader.l();
        float fS = (float) jsonReader.s();
        while (jsonReader.q()) {
            jsonReader.B();
        }
        jsonReader.n();
        return fS;
    }
}
