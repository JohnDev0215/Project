package b.c.a.s;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes3.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<? super T> f2319a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f2320b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f2321c;

    public a() {
        Type typeE = e(getClass());
        this.f2320b = typeE;
        this.f2319a = (Class<? super T>) C$Gson$Types.k(typeE);
        this.f2321c = typeE.hashCode();
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    public static a<?> b(Type type) {
        return new a<>(type);
    }

    public static a<?> c(Type type, Type... typeArr) {
        return new a<>(C$Gson$Types.o(null, type, typeArr));
    }

    public static Type e(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> d() {
        return this.f2319a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && C$Gson$Types.f(this.f2320b, ((a) obj).f2320b);
    }

    public final Type f() {
        return this.f2320b;
    }

    public final int hashCode() {
        return this.f2321c;
    }

    public final String toString() {
        return C$Gson$Types.t(this.f2320b);
    }

    public a(Type type) {
        Type typeB = C$Gson$Types.b((Type) b.c.a.r.a.b(type));
        this.f2320b = typeB;
        this.f2319a = (Class<? super T>) C$Gson$Types.k(typeB);
        this.f2321c = typeB.hashCode();
    }
}
