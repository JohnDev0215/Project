package y1;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: AnimatableValueParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static <T> List<b2.a<T>> a(JsonReader jsonReader, float f8, o1.e eVar, j0<T> j0Var) throws IOException {
        return r.a(jsonReader, eVar, f8, j0Var);
    }

    public static <T> List<b2.a<T>> b(JsonReader jsonReader, o1.e eVar, j0<T> j0Var) throws IOException {
        return r.a(jsonReader, eVar, 1.0f, j0Var);
    }

    public static u1.a c(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.a(b(jsonReader, eVar, f.f9188a));
    }

    public static u1.j d(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.j(b(jsonReader, eVar, h.f9192a));
    }

    public static u1.b e(JsonReader jsonReader, o1.e eVar) throws IOException {
        return f(jsonReader, eVar, true);
    }

    public static u1.b f(JsonReader jsonReader, o1.e eVar, boolean z7) throws IOException {
        return new u1.b(a(jsonReader, z7 ? a2.h.e() : 1.0f, eVar, i.f9196a));
    }

    public static u1.c g(JsonReader jsonReader, o1.e eVar, int i8) throws IOException {
        return new u1.c(b(jsonReader, eVar, new l(i8)));
    }

    public static u1.d h(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.d(b(jsonReader, eVar, o.f9207a));
    }

    public static u1.f i(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.f(a(jsonReader, a2.h.e(), eVar, y.f9223a));
    }

    public static u1.g j(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.g(b(jsonReader, eVar, c0.f9183a));
    }

    public static u1.h k(JsonReader jsonReader, o1.e eVar) throws IOException {
        return new u1.h(a(jsonReader, a2.h.e(), eVar, d0.f9184a));
    }
}
