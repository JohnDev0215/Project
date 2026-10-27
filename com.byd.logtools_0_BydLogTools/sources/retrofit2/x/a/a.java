package retrofit2.x.a;

import com.google.gson.d;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.h;
import retrofit2.s;

/* JADX INFO: compiled from: GsonConverterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f5347a;

    private a(d dVar) {
        this.f5347a = dVar;
    }

    public static a f() {
        return g(new d());
    }

    public static a g(d dVar) {
        Objects.requireNonNull(dVar, "gson == null");
        return new a(dVar);
    }

    @Override // retrofit2.h.a
    public h<?, RequestBody> c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, s sVar) {
        return new b(this.f5347a, this.f5347a.k(com.google.gson.s.a.b(type)));
    }

    @Override // retrofit2.h.a
    public h<ResponseBody, ?> d(Type type, Annotation[] annotationArr, s sVar) {
        return new c(this.f5347a, this.f5347a.k(com.google.gson.s.a.b(type)));
    }
}
