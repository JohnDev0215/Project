package c6;

import c6.t;
import h5.h0;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: compiled from: Request.kt */
/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private d f3923a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final u f3924b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3925c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final t f3926d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final a0 f3927e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Map<Class<?>, Object> f3928f;

    public z(u url, String method, t headers, a0 a0Var, Map<Class<?>, ? extends Object> tags) {
        kotlin.jvm.internal.l.e(url, "url");
        kotlin.jvm.internal.l.e(method, "method");
        kotlin.jvm.internal.l.e(headers, "headers");
        kotlin.jvm.internal.l.e(tags, "tags");
        this.f3924b = url;
        this.f3925c = method;
        this.f3926d = headers;
        this.f3927e = a0Var;
        this.f3928f = tags;
    }

    public final a0 a() {
        return this.f3927e;
    }

    public final d b() {
        d dVar = this.f3923a;
        if (dVar != null) {
            return dVar;
        }
        d dVarB = d.f3681p.b(this.f3926d);
        this.f3923a = dVarB;
        return dVarB;
    }

    public final Map<Class<?>, Object> c() {
        return this.f3928f;
    }

    public final String d(String name) {
        kotlin.jvm.internal.l.e(name, "name");
        return this.f3926d.c(name);
    }

    public final t e() {
        return this.f3926d;
    }

    public final boolean f() {
        return this.f3924b.i();
    }

    public final String g() {
        return this.f3925c;
    }

    public final a h() {
        return new a(this);
    }

    public final u i() {
        return this.f3924b;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f3925c);
        sb.append(", url=");
        sb.append(this.f3924b);
        if (this.f3926d.size() != 0) {
            sb.append(", headers=[");
            int i7 = 0;
            for (g5.k<? extends String, ? extends String> kVar : this.f3926d) {
                int i8 = i7 + 1;
                if (i7 < 0) {
                    h5.p.o();
                }
                g5.k<? extends String, ? extends String> kVar2 = kVar;
                String strA = kVar2.a();
                String strB = kVar2.b();
                if (i7 > 0) {
                    sb.append(", ");
                }
                sb.append(strA);
                sb.append(':');
                sb.append(strB);
                i7 = i8;
            }
            sb.append(']');
        }
        if (!this.f3928f.isEmpty()) {
            sb.append(", tags=");
            sb.append(this.f3928f);
        }
        sb.append('}');
        String string = sb.toString();
        kotlin.jvm.internal.l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    /* JADX INFO: compiled from: Request.kt */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private u f3929a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3930b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private t.a f3931c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private a0 f3932d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Map<Class<?>, Object> f3933e;

        public a() {
            this.f3933e = new LinkedHashMap();
            this.f3930b = "GET";
            this.f3931c = new t.a();
        }

        public a a(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            this.f3931c.a(name, value);
            return this;
        }

        public z b() {
            u uVar = this.f3929a;
            if (uVar != null) {
                return new z(uVar, this.f3930b, this.f3931c.d(), this.f3932d, d6.b.N(this.f3933e));
            }
            throw new IllegalStateException("url == null".toString());
        }

        public a c(String name, String value) {
            kotlin.jvm.internal.l.e(name, "name");
            kotlin.jvm.internal.l.e(value, "value");
            this.f3931c.g(name, value);
            return this;
        }

        public a d(t headers) {
            kotlin.jvm.internal.l.e(headers, "headers");
            this.f3931c = headers.e();
            return this;
        }

        public a e(String method, a0 a0Var) {
            kotlin.jvm.internal.l.e(method, "method");
            if (!(method.length() > 0)) {
                throw new IllegalArgumentException("method.isEmpty() == true".toString());
            }
            if (a0Var == null) {
                if (!(true ^ i6.f.d(method))) {
                    throw new IllegalArgumentException(("method " + method + " must have a request body.").toString());
                }
            } else if (!i6.f.a(method)) {
                throw new IllegalArgumentException(("method " + method + " must not have a request body.").toString());
            }
            this.f3930b = method;
            this.f3932d = a0Var;
            return this;
        }

        public a f(String name) {
            kotlin.jvm.internal.l.e(name, "name");
            this.f3931c.f(name);
            return this;
        }

        public a g(u url) {
            kotlin.jvm.internal.l.e(url, "url");
            this.f3929a = url;
            return this;
        }

        public a h(String url) {
            kotlin.jvm.internal.l.e(url, "url");
            if (y5.p.y(url, "ws:", true)) {
                StringBuilder sb = new StringBuilder();
                sb.append("http:");
                String strSubstring = url.substring(3);
                kotlin.jvm.internal.l.d(strSubstring, "(this as java.lang.String).substring(startIndex)");
                sb.append(strSubstring);
                url = sb.toString();
            } else if (y5.p.y(url, "wss:", true)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https:");
                String strSubstring2 = url.substring(4);
                kotlin.jvm.internal.l.d(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                sb2.append(strSubstring2);
                url = sb2.toString();
            }
            return g(u.f3847l.d(url));
        }

        public a(z request) {
            Map<Class<?>, Object> mapL;
            kotlin.jvm.internal.l.e(request, "request");
            this.f3933e = new LinkedHashMap();
            this.f3929a = request.i();
            this.f3930b = request.g();
            this.f3932d = request.a();
            if (request.c().isEmpty()) {
                mapL = new LinkedHashMap<>();
            } else {
                mapL = h0.l(request.c());
            }
            this.f3933e = mapL;
            this.f3931c = request.e().e();
        }
    }
}
