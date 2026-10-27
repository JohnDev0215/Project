package f;

import f.w;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class e0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final x f5054a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f5055b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final w f5056c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    final f0 f5057d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Map<Class<?>, Object> f5058e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private volatile h f5059f;

    e0(a aVar) {
        this.f5054a = aVar.f5060a;
        this.f5055b = aVar.f5061b;
        this.f5056c = aVar.f5062c.f();
        this.f5057d = aVar.f5063d;
        this.f5058e = f.k0.e.u(aVar.f5064e);
    }

    @Nullable
    public f0 a() {
        return this.f5057d;
    }

    public h b() {
        h hVar = this.f5059f;
        if (hVar != null) {
            return hVar;
        }
        h hVarK = h.k(this.f5056c);
        this.f5059f = hVarK;
        return hVarK;
    }

    @Nullable
    public String c(String str) {
        return this.f5056c.c(str);
    }

    public w d() {
        return this.f5056c;
    }

    public boolean e() {
        return this.f5054a.n();
    }

    public String f() {
        return this.f5055b;
    }

    public a g() {
        return new a(this);
    }

    @Nullable
    public <T> T h(Class<? extends T> cls) {
        return cls.cast(this.f5058e.get(cls));
    }

    public x i() {
        return this.f5054a;
    }

    public String toString() {
        return "Request{method=" + this.f5055b + ", url=" + this.f5054a + ", tags=" + this.f5058e + '}';
    }

    /* JADX INFO: compiled from: Request.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        x f5060a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f5061b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        w.a f5062c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        @Nullable
        f0 f5063d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Map<Class<?>, Object> f5064e;

        public a() {
            this.f5064e = Collections.emptyMap();
            this.f5061b = "GET";
            this.f5062c = new w.a();
        }

        public e0 a() {
            if (this.f5060a != null) {
                return new e0(this);
            }
            throw new IllegalStateException("url == null");
        }

        public a b(String str, String str2) {
            this.f5062c.h(str, str2);
            return this;
        }

        public a c(w wVar) {
            this.f5062c = wVar.f();
            return this;
        }

        public a d(String str, @Nullable f0 f0Var) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (f0Var != null && !f.k0.i.f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (f0Var != null || !f.k0.i.f.e(str)) {
                this.f5061b = str;
                this.f5063d = f0Var;
                return this;
            }
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }

        public a e(f0 f0Var) {
            return d("PATCH", f0Var);
        }

        public a f(f0 f0Var) {
            return d("POST", f0Var);
        }

        public a g(f0 f0Var) {
            return d("PUT", f0Var);
        }

        public a h(String str) {
            this.f5062c.g(str);
            return this;
        }

        public <T> a i(Class<? super T> cls, @Nullable T t) {
            Objects.requireNonNull(cls, "type == null");
            if (t == null) {
                this.f5064e.remove(cls);
            } else {
                if (this.f5064e.isEmpty()) {
                    this.f5064e = new LinkedHashMap();
                }
                this.f5064e.put(cls, cls.cast(t));
            }
            return this;
        }

        public a j(x xVar) {
            Objects.requireNonNull(xVar, "url == null");
            this.f5060a = xVar;
            return this;
        }

        a(e0 e0Var) {
            Map<Class<?>, Object> linkedHashMap;
            this.f5064e = Collections.emptyMap();
            this.f5060a = e0Var.f5054a;
            this.f5061b = e0Var.f5055b;
            this.f5063d = e0Var.f5057d;
            if (e0Var.f5058e.isEmpty()) {
                linkedHashMap = Collections.emptyMap();
            } else {
                linkedHashMap = new LinkedHashMap<>(e0Var.f5058e);
            }
            this.f5064e = linkedHashMap;
            this.f5062c = e0Var.f5056c.f();
        }
    }
}
