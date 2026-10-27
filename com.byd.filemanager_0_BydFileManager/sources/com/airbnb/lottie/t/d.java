package com.airbnb.lottie.t;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;
import java.util.List;

/* JADX INFO: compiled from: AnimatableValueParser.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    private static <T> List<com.airbnb.lottie.v.a<T>> a(JsonReader jsonReader, float f, com.airbnb.lottie.d dVar, j0<T> j0Var) throws IOException {
        return r.a(jsonReader, dVar, f, j0Var);
    }

    private static <T> List<com.airbnb.lottie.v.a<T>> b(JsonReader jsonReader, com.airbnb.lottie.d dVar, j0<T> j0Var) throws IOException {
        return r.a(jsonReader, dVar, 1.0f, j0Var);
    }

    static com.airbnb.lottie.model.i.a c(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.a(b(jsonReader, dVar, f.f2766a));
    }

    static com.airbnb.lottie.model.i.j d(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.j(b(jsonReader, dVar, h.f2770a));
    }

    public static com.airbnb.lottie.model.i.b e(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return f(jsonReader, dVar, true);
    }

    public static com.airbnb.lottie.model.i.b f(JsonReader jsonReader, com.airbnb.lottie.d dVar, boolean z) throws IOException {
        return new com.airbnb.lottie.model.i.b(a(jsonReader, z ? com.airbnb.lottie.u.h.e() : 1.0f, dVar, i.f2774a));
    }

    static com.airbnb.lottie.model.i.c g(JsonReader jsonReader, com.airbnb.lottie.d dVar, int i) throws IOException {
        return new com.airbnb.lottie.model.i.c(b(jsonReader, dVar, new l(i)));
    }

    static com.airbnb.lottie.model.i.d h(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.d(b(jsonReader, dVar, o.f2785a));
    }

    static com.airbnb.lottie.model.i.f i(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.f(a(jsonReader, com.airbnb.lottie.u.h.e(), dVar, y.f2801a));
    }

    static com.airbnb.lottie.model.i.g j(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.g(b(jsonReader, dVar, c0.f2761a));
    }

    static com.airbnb.lottie.model.i.h k(JsonReader jsonReader, com.airbnb.lottie.d dVar) throws IOException {
        return new com.airbnb.lottie.model.i.h(a(jsonReader, com.airbnb.lottie.u.h.e(), dVar, d0.f2762a));
    }
}
