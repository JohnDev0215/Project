package h;

import f.f0;
import f.h0;
import h.h;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.Unit;

/* JADX INFO: compiled from: BuiltInConverters.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f5543a = true;

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class a implements h<h0, h0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f5544a = new a();

        a() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h0 a(h0 h0Var) {
            try {
                return y.a(h0Var);
            } finally {
                h0Var.close();
            }
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class b implements h<f0, f0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f5545a = new b();

        b() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public f0 a(f0 f0Var) {
            return f0Var;
        }
    }

    /* JADX INFO: renamed from: h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class C0104c implements h<h0, h0> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final C0104c f5546a = new C0104c();

        C0104c() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h0 a(h0 h0Var) {
            return h0Var;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class d implements h<Object, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final d f5547a = new d();

        d() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String a(Object obj) {
            return obj.toString();
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class e implements h<h0, Unit> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final e f5548a = new e();

        e() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Unit a(h0 h0Var) {
            h0Var.close();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class f implements h<h0, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final f f5549a = new f();

        f() {
        }

        @Override // h.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void a(h0 h0Var) {
            h0Var.close();
            return null;
        }
    }

    c() {
    }

    @Override // h.h.a
    @Nullable
    public h<?, f0> c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, u uVar) {
        if (f0.class.isAssignableFrom(y.h(type))) {
            return b.f5545a;
        }
        return null;
    }

    @Override // h.h.a
    @Nullable
    public h<h0, ?> d(Type type, Annotation[] annotationArr, u uVar) {
        if (type == h0.class) {
            return y.l(annotationArr, h.a0.w.class) ? C0104c.f5546a : a.f5544a;
        }
        if (type == Void.class) {
            return f.f5549a;
        }
        if (!this.f5543a || type != Unit.class) {
            return null;
        }
        try {
            return e.f5548a;
        } catch (NoClassDefFoundError unused) {
            this.f5543a = false;
            return null;
        }
    }
}
