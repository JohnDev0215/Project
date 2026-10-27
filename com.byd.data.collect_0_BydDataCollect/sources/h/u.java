package h;

import f.b0;
import f.f0;
import f.h0;
import f.i;
import h.c;
import h.e;
import h.h;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Retrofit.java */
/* JADX INFO: loaded from: classes.dex */
public final class u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Method, v<?>> f5688a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final i.a f5689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final f.x f5690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final List<h.a> f5691d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final List<e.a> f5692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    final Executor f5693f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final boolean f5694g;

    /* JADX INFO: compiled from: Retrofit.java */
    class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final q f5695a = q.f();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Object[] f5696b = new Object[0];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f5697c;

        a(Class cls) {
            this.f5697c = cls;
        }

        @Override // java.lang.reflect.InvocationHandler
        @Nullable
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            if (this.f5695a.h(method)) {
                return this.f5695a.g(method, this.f5697c, obj, objArr);
            }
            v<?> vVarC = u.this.c(method);
            if (objArr == null) {
                objArr = this.f5696b;
            }
            return vVarC.a(objArr);
        }
    }

    u(i.a aVar, f.x xVar, List<h.a> list, List<e.a> list2, @Nullable Executor executor, boolean z) {
        this.f5689b = aVar;
        this.f5690c = xVar;
        this.f5691d = list;
        this.f5692e = list2;
        this.f5693f = executor;
        this.f5694g = z;
    }

    private void j(Class<?> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        ArrayDeque arrayDeque = new ArrayDeque(1);
        arrayDeque.add(cls);
        while (!arrayDeque.isEmpty()) {
            Class<?> cls2 = (Class) arrayDeque.removeFirst();
            if (cls2.getTypeParameters().length != 0) {
                StringBuilder sb = new StringBuilder("Type parameters are unsupported on ");
                sb.append(cls2.getName());
                if (cls2 != cls) {
                    sb.append(" which is an interface of ");
                    sb.append(cls.getName());
                }
                throw new IllegalArgumentException(sb.toString());
            }
            Collections.addAll(arrayDeque, cls2.getInterfaces());
        }
        if (this.f5694g) {
            q qVarF = q.f();
            for (Method method : cls.getDeclaredMethods()) {
                if (!qVarF.h(method) && !Modifier.isStatic(method.getModifiers())) {
                    c(method);
                }
            }
        }
    }

    public e<?, ?> a(Type type, Annotation[] annotationArr) {
        return d(null, type, annotationArr);
    }

    public <T> T b(Class<T> cls) {
        j(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a(cls));
    }

    v<?> c(Method method) {
        v<?> vVarB;
        v<?> vVar = this.f5688a.get(method);
        if (vVar != null) {
            return vVar;
        }
        synchronized (this.f5688a) {
            vVarB = this.f5688a.get(method);
            if (vVarB == null) {
                vVarB = v.b(this, method);
                this.f5688a.put(method, vVarB);
            }
        }
        return vVarB;
    }

    public e<?, ?> d(@Nullable e.a aVar, Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "returnType == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int iIndexOf = this.f5692e.indexOf(aVar) + 1;
        int size = this.f5692e.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            e<?, ?> eVarA = this.f5692e.get(i2).a(type, annotationArr, this);
            if (eVarA != null) {
                return eVarA;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5692e.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5692e.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5692e.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<T, f0> e(@Nullable h.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "parameterAnnotations == null");
        Objects.requireNonNull(annotationArr2, "methodAnnotations == null");
        int iIndexOf = this.f5691d.indexOf(aVar) + 1;
        int size = this.f5691d.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            h<T, f0> hVar = (h<T, f0>) this.f5691d.get(i2).c(type, annotationArr, annotationArr2, this);
            if (hVar != null) {
                return hVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5691d.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5691d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5691d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<h0, T> f(@Nullable h.a aVar, Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int iIndexOf = this.f5691d.indexOf(aVar) + 1;
        int size = this.f5691d.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            h<h0, T> hVar = (h<h0, T>) this.f5691d.get(i2).d(type, annotationArr, this);
            if (hVar != null) {
                return hVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5691d.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5691d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5691d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<T, f0> g(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return e(null, type, annotationArr, annotationArr2);
    }

    public <T> h<h0, T> h(Type type, Annotation[] annotationArr) {
        return f(null, type, annotationArr);
    }

    public <T> h<T, String> i(Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int size = this.f5691d.size();
        for (int i2 = 0; i2 < size; i2++) {
            h<T, String> hVar = (h<T, String>) this.f5691d.get(i2).e(type, annotationArr, this);
            if (hVar != null) {
                return hVar;
            }
        }
        return c.d.f5547a;
    }

    /* JADX INFO: compiled from: Retrofit.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final q f5699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private i.a f5700b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        private f.x f5701c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<h.a> f5702d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final List<e.a> f5703e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Nullable
        private Executor f5704f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f5705g;

        b(q qVar) {
            this.f5702d = new ArrayList();
            this.f5703e = new ArrayList();
            this.f5699a = qVar;
        }

        public b a(e.a aVar) {
            List<e.a> list = this.f5703e;
            Objects.requireNonNull(aVar, "factory == null");
            list.add(aVar);
            return this;
        }

        public b b(h.a aVar) {
            List<h.a> list = this.f5702d;
            Objects.requireNonNull(aVar, "factory == null");
            list.add(aVar);
            return this;
        }

        public b c(String str) {
            Objects.requireNonNull(str, "baseUrl == null");
            return d(f.x.l(str));
        }

        public b d(f.x xVar) {
            Objects.requireNonNull(xVar, "baseUrl == null");
            if ("".equals(xVar.r().get(r0.size() - 1))) {
                this.f5701c = xVar;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + xVar);
        }

        public u e() {
            if (this.f5701c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            i.a b0Var = this.f5700b;
            if (b0Var == null) {
                b0Var = new b0();
            }
            i.a aVar = b0Var;
            Executor executorB = this.f5704f;
            if (executorB == null) {
                executorB = this.f5699a.b();
            }
            Executor executor = executorB;
            ArrayList arrayList = new ArrayList(this.f5703e);
            arrayList.addAll(this.f5699a.a(executor));
            ArrayList arrayList2 = new ArrayList(this.f5702d.size() + 1 + this.f5699a.d());
            arrayList2.add(new c());
            arrayList2.addAll(this.f5702d);
            arrayList2.addAll(this.f5699a.c());
            return new u(aVar, this.f5701c, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor, this.f5705g);
        }

        public b f(i.a aVar) {
            Objects.requireNonNull(aVar, "factory == null");
            this.f5700b = aVar;
            return this;
        }

        public b g(b0 b0Var) {
            Objects.requireNonNull(b0Var, "client == null");
            return f(b0Var);
        }

        public b() {
            this(q.f());
        }
    }
}
