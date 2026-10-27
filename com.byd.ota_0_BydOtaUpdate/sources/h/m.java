package h;

import e.b0;
import e.e;
import e.w;
import e.z;
import h.a;
import h.c;
import h.e;
import h.n;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Retrofit.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<Method, n<?, ?>> f5815a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e.a f5816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HttpUrl f5817c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List<e.a> f5818d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List<c.a> f5819e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    public final Executor f5820f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f5821g;

    /* JADX INFO: compiled from: Retrofit.java */
    public class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j f5822a = j.d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Class f5823b;

        public a(Class cls) {
            this.f5823b = cls;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, @Nullable Object[] objArr) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            if (this.f5822a.f(method)) {
                return this.f5822a.e(method, this.f5823b, obj, objArr);
            }
            n<?, ?> nVarF = m.this.f(method);
            return nVarF.f5835d.b(new h(nVarF, objArr));
        }
    }

    public m(e.a aVar, HttpUrl httpUrl, List<e.a> list, List<c.a> list2, @Nullable Executor executor, boolean z) {
        this.f5816b = aVar;
        this.f5817c = httpUrl;
        this.f5818d = Collections.unmodifiableList(list);
        this.f5819e = Collections.unmodifiableList(list2);
        this.f5820f = executor;
        this.f5821g = z;
    }

    public HttpUrl a() {
        return this.f5817c;
    }

    public c<?, ?> b(Type type, Annotation[] annotationArr) {
        return g(null, type, annotationArr);
    }

    public e.a c() {
        return this.f5816b;
    }

    public <T> T d(Class<T> cls) {
        o.s(cls);
        if (this.f5821g) {
            e(cls);
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new a(cls));
    }

    public final void e(Class<?> cls) {
        j jVarD = j.d();
        for (Method method : cls.getDeclaredMethods()) {
            if (!jVarD.f(method)) {
                f(method);
            }
        }
    }

    public n<?, ?> f(Method method) {
        n nVarA;
        n<?, ?> nVar = this.f5815a.get(method);
        if (nVar != null) {
            return nVar;
        }
        synchronized (this.f5815a) {
            nVarA = this.f5815a.get(method);
            if (nVarA == null) {
                nVarA = new n.a(this, method).a();
                this.f5815a.put(method, nVarA);
            }
        }
        return nVarA;
    }

    public c<?, ?> g(@Nullable c.a aVar, Type type, Annotation[] annotationArr) {
        o.b(type, "returnType == null");
        o.b(annotationArr, "annotations == null");
        int iIndexOf = this.f5819e.indexOf(aVar) + 1;
        int size = this.f5819e.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            c<?, ?> cVarA = this.f5819e.get(i2).a(type, annotationArr, this);
            if (cVarA != null) {
                return cVarA;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate call adapter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5819e.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5819e.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5819e.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<T, z> h(@Nullable e.a aVar, Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        o.b(type, "type == null");
        o.b(annotationArr, "parameterAnnotations == null");
        o.b(annotationArr2, "methodAnnotations == null");
        int iIndexOf = this.f5818d.indexOf(aVar) + 1;
        int size = this.f5818d.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            e<T, z> eVar = (e<T, z>) this.f5818d.get(i2).a(type, annotationArr, annotationArr2, this);
            if (eVar != null) {
                return eVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate RequestBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5818d.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5818d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5818d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<b0, T> i(@Nullable e.a aVar, Type type, Annotation[] annotationArr) {
        o.b(type, "type == null");
        o.b(annotationArr, "annotations == null");
        int iIndexOf = this.f5818d.indexOf(aVar) + 1;
        int size = this.f5818d.size();
        for (int i2 = iIndexOf; i2 < size; i2++) {
            e<b0, T> eVar = (e<b0, T>) this.f5818d.get(i2).b(type, annotationArr, this);
            if (eVar != null) {
                return eVar;
            }
        }
        StringBuilder sb = new StringBuilder("Could not locate ResponseBody converter for ");
        sb.append(type);
        sb.append(".\n");
        if (aVar != null) {
            sb.append("  Skipped:");
            for (int i3 = 0; i3 < iIndexOf; i3++) {
                sb.append("\n   * ");
                sb.append(this.f5818d.get(i3).getClass().getName());
            }
            sb.append('\n');
        }
        sb.append("  Tried:");
        int size2 = this.f5818d.size();
        while (iIndexOf < size2) {
            sb.append("\n   * ");
            sb.append(this.f5818d.get(iIndexOf).getClass().getName());
            iIndexOf++;
        }
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> e<T, z> j(Type type, Annotation[] annotationArr, Annotation[] annotationArr2) {
        return h(null, type, annotationArr, annotationArr2);
    }

    public <T> e<b0, T> k(Type type, Annotation[] annotationArr) {
        return i(null, type, annotationArr);
    }

    public <T> e<T, String> l(Type type, Annotation[] annotationArr) {
        o.b(type, "type == null");
        o.b(annotationArr, "annotations == null");
        int size = this.f5818d.size();
        for (int i2 = 0; i2 < size; i2++) {
            e<T, String> eVar = (e<T, String>) this.f5818d.get(i2).c(type, annotationArr, this);
            if (eVar != null) {
                return eVar;
            }
        }
        return a.d.f5750a;
    }

    /* JADX INFO: compiled from: Retrofit.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final j f5825a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        public e.a f5826b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public HttpUrl f5827c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final List<e.a> f5828d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final List<c.a> f5829e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        @Nullable
        public Executor f5830f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5831g;

        public b(j jVar) {
            ArrayList arrayList = new ArrayList();
            this.f5828d = arrayList;
            this.f5829e = new ArrayList();
            this.f5825a = jVar;
            arrayList.add(new h.a());
        }

        public b a(c.a aVar) {
            this.f5829e.add((c.a) o.b(aVar, "factory == null"));
            return this;
        }

        public b b(e.a aVar) {
            this.f5828d.add((e.a) o.b(aVar, "factory == null"));
            return this;
        }

        public b c(String str) {
            o.b(str, "baseUrl == null");
            HttpUrl httpUrlQ = HttpUrl.q(str);
            if (httpUrlQ != null) {
                return d(httpUrlQ);
            }
            throw new IllegalArgumentException("Illegal URL: " + str);
        }

        public b d(HttpUrl httpUrl) {
            o.b(httpUrl, "baseUrl == null");
            if ("".equals(httpUrl.r().get(r0.size() - 1))) {
                this.f5827c = httpUrl;
                return this;
            }
            throw new IllegalArgumentException("baseUrl must end in /: " + httpUrl);
        }

        public m e() {
            if (this.f5827c == null) {
                throw new IllegalStateException("Base URL required.");
            }
            e.a wVar = this.f5826b;
            if (wVar == null) {
                wVar = new w();
            }
            e.a aVar = wVar;
            Executor executorB = this.f5830f;
            if (executorB == null) {
                executorB = this.f5825a.b();
            }
            Executor executor = executorB;
            ArrayList arrayList = new ArrayList(this.f5829e);
            arrayList.add(this.f5825a.a(executor));
            return new m(aVar, this.f5827c, new ArrayList(this.f5828d), arrayList, executor, this.f5831g);
        }

        public b f(e.a aVar) {
            this.f5826b = (e.a) o.b(aVar, "factory == null");
            return this;
        }

        public b g(w wVar) {
            return f((e.a) o.b(wVar, "client == null"));
        }

        public b() {
            this(j.d());
        }
    }
}
