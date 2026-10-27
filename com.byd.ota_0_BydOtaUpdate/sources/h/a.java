package h;

import e.b0;
import e.z;
import h.e;
import h.r.w;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: compiled from: BuiltInConverters.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends e.a {

    /* JADX INFO: renamed from: h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BuiltInConverters.java */
    public static final class C0098a implements h.e<b0, b0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final C0098a f5747a = new C0098a();

        @Override // h.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b0 a(b0 b0Var) throws IOException {
            try {
                return o.a(b0Var);
            } finally {
                b0Var.close();
            }
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    public static final class b implements h.e<z, z> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5748a = new b();

        @Override // h.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public z a(z zVar) throws IOException {
            return zVar;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    public static final class c implements h.e<b0, b0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f5749a = new c();

        @Override // h.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b0 a(b0 b0Var) throws IOException {
            return b0Var;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    public static final class d implements h.e<Object, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final d f5750a = new d();

        @Override // h.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String a(Object obj) {
            return obj.toString();
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    public static final class e implements h.e<b0, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final e f5751a = new e();

        @Override // h.e
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void a(b0 b0Var) throws IOException {
            b0Var.close();
            return null;
        }
    }

    @Override // h.e.a
    public h.e<?, z> a(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, m mVar) {
        if (z.class.isAssignableFrom(o.j(type))) {
            return b.f5748a;
        }
        return null;
    }

    @Override // h.e.a
    public h.e<b0, ?> b(Type type, Annotation[] annotationArr, m mVar) {
        if (type == b0.class) {
            return o.o(annotationArr, w.class) ? c.f5749a : C0098a.f5747a;
        }
        if (type == Void.class) {
            return e.f5751a;
        }
        return null;
    }
}
