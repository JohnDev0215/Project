package h.q.a;

import e.b0;
import e.z;
import h.e;
import h.m;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: compiled from: GsonConverterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends e.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c.b.a.e f5879a;

    public a(c.b.a.e eVar) {
        this.f5879a = eVar;
    }

    public static a d(c.b.a.e eVar) {
        Objects.requireNonNull(eVar, "gson == null");
        return new a(eVar);
    }

    @Override // h.e.a
    public e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        return new b(this.f5879a, this.f5879a.m(c.b.a.u.a.b(type)));
    }

    @Override // h.e.a
    public e<b0, ?> b(Type type, Annotation[] annotationArr, m mVar) {
        return new c(this.f5879a, this.f5879a.m(c.b.a.u.a.b(type)));
    }
}
