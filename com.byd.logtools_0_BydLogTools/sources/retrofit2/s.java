package retrofit2;

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
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.c;
import retrofit2.e;
import retrofit2.h;

/* JADX INFO: compiled from: Retrofit.java */
/* JADX INFO: loaded from: classes.dex */
public final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<Method, t<?>> f5321a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Call.Factory f5322b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final HttpUrl f5323c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final List<h.a> f5324d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final List<e.a> f5325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    final Executor f5326f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final boolean f5327g;

    /* JADX INFO: compiled from: Retrofit.java */
    class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final o f5328a = o.f();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Object[] f5329b = new Object[0];

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Class f5330c;

        a(Class cls) {
            this.f5330c = cls;
        }

        @Override // java.lang.reflect.InvocationHandler
        @Nullable
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            if (objArr == null) {
                objArr = this.f5329b;
            }
            return this.f5328a.h(method) ? this.f5328a.g(method, this.f5330c, obj, objArr) : s.this.c(method).a(objArr);
        }
    }

    s(Call.Factory factory, HttpUrl httpUrl, List<h.a> list, List<e.a> list2, @Nullable Executor executor, boolean z) {
        this.f5322b = factory;
        this.f5323c = httpUrl;
        this.f5324d = list;
        this.f5325e = list2;
        this.f5326f = executor;
        this.f5327g = z;
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
        if (this.f5327g) {
            o oVarF = o.f();
            for (Method method : cls.getDeclaredMethods()) {
                if (!oVarF.h(method) && !Modifier.isStatic(method.getModifiers())) {
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

    t<?> c(Method method) {
        t<?> tVarB;
        t<?> tVar = this.f5321a.get(method);
        if (tVar != null) {
            return tVar;
        }
        synchronized (this.f5321a) {
            tVarB = this.f5321a.get(method);
            if (tVarB == null) {
                tVarB = t.b(this, method);
                this.f5321a.put(method, tVarB);
            }
        }
        return tVarB;
    }

    public e<?, ?> d(@Nullable e.a aVar, Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "returnType == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int iIndexOf = this.f5325e.indexOf(aVar) + 1;
        int size = this.f5325e.size();
        for (int i = iIndexOf; i < size; i++) {
            e<?, ?> eVarA = this.f5325e.get(i).a(type, annotationArr, this);
            if (eVarA != null) {
                return eVarA;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.f5325e.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5325e.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5325e.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<T, RequestBody> e(@Nullable h.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "parameterAnnotations == null");
        Objects.requireNonNull(annotationArr2, "methodAnnotations == null");
        int iIndexOf = this.f5324d.indexOf(aVar) + 1;
        int size = this.f5324d.size();
        for (int i = iIndexOf; i < size; i++) {
            h<T, RequestBody> hVar = (h<T, RequestBody>) this.f5324d.get(i).c(type, annotationArr, annotationArr2, this);
            if (hVar != null) {
                return hVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.f5324d.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5324d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5324d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<ResponseBody, T> f(@Nullable h.a aVar, Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int iIndexOf = this.f5324d.indexOf(aVar) + 1;
        int size = this.f5324d.size();
        for (int i = iIndexOf; i < size; i++) {
            h<ResponseBody, T> hVar = (h<ResponseBody, T>) this.f5324d.get(i).d(type, annotationArr, this);
            if (hVar != null) {
                return hVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i2 = 0; i2 < iIndexOf; i2++) {
                sb.append("\n   * ");
                sb.append(this.f5324d.get(i2).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5324d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5324d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> h<T, RequestBody> g(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return e(null, type, annotationArr, annotationArr2);
    }

    public <T> h<ResponseBody, T> h(Type type, Annotation[] annotationArr) {
        return f(null, type, annotationArr);
    }

    public <T> h<T, String> i(Type type, Annotation[] annotationArr) {
        Objects.requireNonNull(type, "type == null");
        Objects.requireNonNull(annotationArr, "annotations == null");
        int size = this.f5324d.size();
        for (int i = 0; i < size; i++) {
            h<T, String> hVar = (h<T, String>) this.f5324d.get(i).e(type, annotationArr, this);
            if (hVar != null) {
                return hVar;
            }
        }
        return c.d.f5198a;
    }

    /* JADX INFO: compiled from: Retrofit.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final o f5332a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private Call.Factory f5333b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @Nullable
        private HttpUrl f5334c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final List<h.a> f5335d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final List<e.a> f5336e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Nullable
        private Executor f5337f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f5338g;

        b(o oVar) {
            this.f5335d = new ArrayList();
            this.f5336e = new ArrayList();
            this.f5332a = oVar;
        }

        public b a(h.a aVar) {
            List<h.a> list = this.f5335d;
            Objects.requireNonNull(aVar, "factory == null");
            list.add(aVar);
            return this;
        }

        public b b(String str) {
            Objects.requireNonNull(str, "baseUrl == null");
            return c(HttpUrl.get(str));
        }

        public b c(HttpUrl httpUrl) {
            Objects.requireNonNull(httpUrl, "baseUrl == null");
            if (HttpUrl.FRAGMENT_ENCODE_SET.equals(httpUrl.pathSegments().get(r0.size() - 1))) {
                this.f5334c = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }

        public s d() {
            if (this.f5334c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            Call.Factory okHttpClient = this.f5333b;
            if (okHttpClient == null) {
                okHttpClient = new OkHttpClient();
            }
            Call.Factory factory = okHttpClient;
            Executor executorB = this.f5337f;
            if (executorB == null) {
                executorB = this.f5332a.b();
            }
            Executor executor = executorB;
            ArrayList arrayList = new ArrayList(this.f5336e);
            arrayList.addAll(this.f5332a.a(executor));
            ArrayList arrayList2 = new ArrayList(this.f5335d.size() + 1 + this.f5332a.d());
            arrayList2.add(new c());
            arrayList2.addAll(this.f5335d);
            arrayList2.addAll(this.f5332a.c());
            return new s(factory, this.f5334c, Collections.unmodifiableList(arrayList2), Collections.unmodifiableList(arrayList), executor, this.f5338g);
        }

        public b e(Call.Factory factory) {
            Objects.requireNonNull(factory, "factory == null");
            this.f5333b = factory;
            return this;
        }

        public b f(OkHttpClient okHttpClient) {
            Objects.requireNonNull(okHttpClient, "client == null");
            return e(okHttpClient);
        }

        public b() {
            this(o.f());
        }
    }
}
