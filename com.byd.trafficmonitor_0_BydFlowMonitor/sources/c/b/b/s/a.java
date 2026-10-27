package c.b.b.s;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<? super T> f4298a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f4299b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4300c;

    public a() {
        Type typeE = e(getClass());
        this.f4299b = typeE;
        this.f4298a = (Class<? super T>) C$Gson$Types.k(typeE);
        this.f4300c = typeE.hashCode();
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
        return this.f4298a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && C$Gson$Types.f(this.f4299b, ((a) obj).f4299b);
    }

    public final Type f() {
        return this.f4299b;
    }

    public final int hashCode() {
        return this.f4300c;
    }

    public final String toString() {
        return C$Gson$Types.t(this.f4299b);
    }

    public a(Type type) {
        Type typeB = C$Gson$Types.b((Type) c.b.b.r.a.b(type));
        this.f4299b = typeB;
        this.f4298a = (Class<? super T>) C$Gson$Types.k(typeB);
        this.f4300c = typeB.hashCode();
    }
}
