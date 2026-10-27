package h.p.a;

import d.a.j;
import d.a.k;
import h.c;
import h.l;
import h.m;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: RxJava2CallAdapterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends c.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f5877a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5878b;

    public g(j jVar, boolean z) {
        this.f5877a = jVar;
        this.f5878b = z;
    }

    public static g d() {
        return new g(null, false);
    }

    @Override // h.c.a
    public h.c<?, ?> a(Type type, Annotation[] annotationArr, m mVar) {
        Type typeB;
        boolean z;
        boolean z2;
        Class<?> clsC = c.a.c(type);
        if (clsC == d.a.a.class) {
            return new f(Void.class, this.f5877a, this.f5878b, false, true, false, false, false, true);
        }
        boolean z3 = clsC == d.a.c.class;
        boolean z4 = clsC == k.class;
        boolean z5 = clsC == d.a.d.class;
        if (clsC != d.a.g.class && !z3 && !z4 && !z5) {
            return null;
        }
        if (!(type instanceof ParameterizedType)) {
            String str = !z3 ? !z4 ? z5 ? "Maybe" : "Observable" : "Single" : "Flowable";
            throw new IllegalStateException(str + " return type must be parameterized as " + str + "<Foo> or " + str + "<? extends Foo>");
        }
        Type typeB2 = c.a.b(0, (ParameterizedType) type);
        Class<?> clsC2 = c.a.c(typeB2);
        if (clsC2 == l.class) {
            if (!(typeB2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
            }
            typeB = c.a.b(0, (ParameterizedType) typeB2);
            z2 = false;
            z = false;
        } else if (clsC2 != d.class) {
            typeB = typeB2;
            z = true;
            z2 = false;
        } else {
            if (!(typeB2 instanceof ParameterizedType)) {
                throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
            }
            typeB = c.a.b(0, (ParameterizedType) typeB2);
            z2 = true;
            z = false;
        }
        return new f(typeB, this.f5877a, this.f5878b, z2, z, z3, z4, z5, false);
    }
}
