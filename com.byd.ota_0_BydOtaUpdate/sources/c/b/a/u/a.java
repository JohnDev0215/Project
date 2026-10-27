package c.b.a.u;

import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: TypeToken.java */
/* JADX INFO: loaded from: classes.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class<? super T> f3033a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Type f3034b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3035c;

    public a() {
        Type typeD = d(getClass());
        this.f3034b = typeD;
        this.f3033a = (Class<? super T>) C$Gson$Types.k(typeD);
        this.f3035c = typeD.hashCode();
    }

    public static <T> a<T> a(Class<T> cls) {
        return new a<>(cls);
    }

    public static a<?> b(Type type) {
        return new a<>(type);
    }

    public static Type d(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        return C$Gson$Types.b(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
    }

    public final Class<? super T> c() {
        return this.f3033a;
    }

    public final Type e() {
        return this.f3034b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof a) && C$Gson$Types.f(this.f3034b, ((a) obj).f3034b);
    }

    public final int hashCode() {
        return this.f3035c;
    }

    public final String toString() {
        return C$Gson$Types.t(this.f3034b);
    }

    public a(Type type) {
        Type typeB = C$Gson$Types.b((Type) c.b.a.t.a.b(type));
        this.f3034b = typeB;
        this.f3033a = (Class<? super T>) C$Gson$Types.k(typeB);
        this.f3035c = typeB.hashCode();
    }
}
