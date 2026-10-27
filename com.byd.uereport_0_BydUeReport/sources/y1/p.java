package y1;

import android.graphics.Color;
import android.graphics.PointF;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: JsonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final JsonReader.a f9208a = JsonReader.a.a("x", "y");

    /* JADX INFO: compiled from: JsonUtils.java */
    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f9209a;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            f9209a = iArr;
            try {
                iArr[JsonReader.Token.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9209a[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9209a[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static PointF a(JsonReader jsonReader, float f8) throws IOException {
        jsonReader.g();
        float fQ = (float) jsonReader.q();
        float fQ2 = (float) jsonReader.q();
        while (jsonReader.x() != JsonReader.Token.END_ARRAY) {
            jsonReader.F();
        }
        jsonReader.j();
        return new PointF(fQ * f8, fQ2 * f8);
    }

    public static PointF b(JsonReader jsonReader, float f8) throws IOException {
        float fQ = (float) jsonReader.q();
        float fQ2 = (float) jsonReader.q();
        while (jsonReader.n()) {
            jsonReader.F();
        }
        return new PointF(fQ * f8, fQ2 * f8);
    }

    public static PointF c(JsonReader jsonReader, float f8) throws IOException {
        jsonReader.i();
        float fG = 0.0f;
        float fG2 = 0.0f;
        while (jsonReader.n()) {
            int iC = jsonReader.C(f9208a);
            if (iC == 0) {
                fG = g(jsonReader);
            } else if (iC != 1) {
                jsonReader.E();
                jsonReader.F();
            } else {
                fG2 = g(jsonReader);
            }
        }
        jsonReader.m();
        return new PointF(fG * f8, fG2 * f8);
    }

    public static int d(JsonReader jsonReader) throws IOException {
        jsonReader.g();
        int iQ = (int) (jsonReader.q() * 255.0d);
        int iQ2 = (int) (jsonReader.q() * 255.0d);
        int iQ3 = (int) (jsonReader.q() * 255.0d);
        while (jsonReader.n()) {
            jsonReader.F();
        }
        jsonReader.j();
        return Color.argb(255, iQ, iQ2, iQ3);
    }

    public static PointF e(JsonReader jsonReader, float f8) throws IOException {
        int i8 = a.f9209a[jsonReader.x().ordinal()];
        if (i8 == 1) {
            return b(jsonReader, f8);
        }
        if (i8 == 2) {
            return a(jsonReader, f8);
        }
        if (i8 == 3) {
            return c(jsonReader, f8);
        }
        throw new IllegalArgumentException("Unknown point starts with " + jsonReader.x());
    }

    public static List<PointF> f(JsonReader jsonReader, float f8) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.g();
        while (jsonReader.x() == JsonReader.Token.BEGIN_ARRAY) {
            jsonReader.g();
            arrayList.add(e(jsonReader, f8));
            jsonReader.j();
        }
        jsonReader.j();
        return arrayList;
    }

    public static float g(JsonReader jsonReader) throws IOException {
        JsonReader.Token tokenX = jsonReader.x();
        int i8 = a.f9209a[tokenX.ordinal()];
        if (i8 == 1) {
            return (float) jsonReader.q();
        }
        if (i8 != 2) {
            throw new IllegalArgumentException("Unknown value for token of type " + tokenX);
        }
        jsonReader.g();
        float fQ = (float) jsonReader.q();
        while (jsonReader.n()) {
            jsonReader.F();
        }
        jsonReader.j();
        return fQ;
    }
}
