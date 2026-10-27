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
    private static final JsonReader.a f2786a = JsonReader.a.a("x", "y");

    /* JADX INFO: compiled from: JsonUtils.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2787a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            f2787a = iArr;
            try {
                iArr[JsonReader.Token.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2787a[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2787a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static PointF a(JsonReader jsonReader, float f) throws IOException {
        jsonReader.k();
        float fR = (float) jsonReader.r();
        float fR2 = (float) jsonReader.r();
        while (jsonReader.w() != JsonReader.Token.END_ARRAY) {
            jsonReader.A();
        }
        jsonReader.m();
        return new PointF(fR * f, fR2 * f);
    }

    private static PointF b(JsonReader jsonReader, float f) throws IOException {
        float fR = (float) jsonReader.r();
        float fR2 = (float) jsonReader.r();
        while (jsonReader.p()) {
            jsonReader.A();
        }
        return new PointF(fR * f, fR2 * f);
    }

    private static PointF c(JsonReader jsonReader, float f) throws IOException {
        jsonReader.l();
        float fG = 0.0f;
        float fG2 = 0.0f;
        while (jsonReader.p()) {
            int iY = jsonReader.y(f2786a);
            if (iY == 0) {
                fG = g(jsonReader);
            } else if (iY != 1) {
                jsonReader.z();
                jsonReader.A();
            } else {
                fG2 = g(jsonReader);
            }
        }
        jsonReader.n();
        return new PointF(fG * f, fG2 * f);
    }

    static int d(JsonReader jsonReader) throws IOException {
        jsonReader.k();
        int iR = (int) (jsonReader.r() * 255.0d);
        int iR2 = (int) (jsonReader.r() * 255.0d);
        int iR3 = (int) (jsonReader.r() * 255.0d);
        while (jsonReader.p()) {
            jsonReader.A();
        }
        jsonReader.m();
        return Color.argb(255, iR, iR2, iR3);
    }

    static PointF e(JsonReader jsonReader, float f) throws IOException {
        int i = a.f2787a[jsonReader.w().ordinal()];
        if (i == 1) {
            return b(jsonReader, f);
        }
        if (i == 2) {
            return a(jsonReader, f);
        }
        if (i == 3) {
            return c(jsonReader, f);
        }
        throw new IllegalArgumentException("Unknown point starts with " + jsonReader.w());
    }

    static List<PointF> f(JsonReader jsonReader, float f) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.k();
        while (jsonReader.w() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.k();
            arrayList.add(e(jsonReader, f));
            jsonReader.m();
        }
        jsonReader.m();
        return arrayList;
    }

    static float g(JsonReader jsonReader) throws IOException {
        JsonReader.Token tokenW = jsonReader.w();
        int i = a.f2787a[tokenW.ordinal()];
        if (i == 1) {
            return (float) jsonReader.r();
        }
        if (i != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + tokenW);
        }
        jsonReader.k();
        float fR = (float) jsonReader.r();
        while (jsonReader.p()) {
            jsonReader.A();
        }
        jsonReader.m();
        return fR;
    }
}
