package h.z.a;

import e.a.i;
import h.e;
import h.t;
import h.u;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: RxJava2CallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final e.a.h f5738a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f5739b;

    private h(@Nullable e.a.h hVar, boolean z) {
        this.f5738a = hVar;
        this.f5739b = z;
    }

    public static h d() {
        return new h(null, false);
    }

    @Override // h.e.a
    @Nullable
    public h.e<?, ?> a(Type type, Annotation[] annotationArr, u uVar) {
        Type typeB;
        boolean z;
        boolean z2;
        Class<?> clsC = e.a.c(type);
        if (clsC == e.a.b.class) {
            return new g(Void.class, this.f5738a, this.f5739b, false, true, false, false, false, true);
        }
        boolean z3 = clsC == e.a.c.class;
        boolean z4 = clsC == i.class;
        boolean z5 = clsC == e.a.d.class;
        if (clsC != e.a.e.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? !z4 ? z5 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type typeB2 = e.a.b(0, (ParameterizedType) type);
        Class<?> clsC2 = e.a.c(typeB2);
        if (clsC2 == t.class) {
            if (!(typeB2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            typeB = e.a.b(0, (ParameterizedType) typeB2);
            z2 = false;
            z = false;
        } else if (clsC2 != e.class) {
            typeB = typeB2;
            z = true;
            z2 = false;
        } else {
            if (!(typeB2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            typeB = e.a.b(0, (ParameterizedType) typeB2);
            z2 = true;
            z = false;
        }
        return new g(typeB, this.f5738a, this.f5739b, z2, z, z3, z4, z5, false);
    }
}
