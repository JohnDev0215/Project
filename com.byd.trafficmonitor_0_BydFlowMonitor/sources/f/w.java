package f;

import f.q;
import java.util.Objects;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpUrl f5419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5420b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final q f5421c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final x f5422d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f5423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile d f5424f;

    public w(a aVar) {
        this.f5419a = aVar.f5425a;
        this.f5420b = aVar.f5426b;
        this.f5421c = aVar.f5427c.d();
        this.f5422d = aVar.f5428d;
        Object obj = aVar.f5429e;
        this.f5423e = obj == null ? this : obj;
    }

    public x a() {
        return this.f5422d;
    }

    public d b() {
        d dVar = this.f5424f;
        if (dVar != null) {
            return dVar;
        }
        d dVarK = d.k(this.f5421c);
        this.f5424f = dVarK;
        return dVarK;
    }

    public String c(String str) {
        return this.f5421c.a(str);
    }

    public q d() {
        return this.f5421c;
    }

    public boolean e() {
        return this.f5419a.m();
    }

    public String f() {
        return this.f5420b;
    }

    public a g() {
        return new a(this);
    }

    public HttpUrl h() {
        return this.f5419a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f5420b);
        sb.append(", url=");
        sb.append(this.f5419a);
        sb.append(", tag=");
        Object obj = this.f5423e;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    /* JADX INFO: compiled from: Request.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public HttpUrl f5425a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f5426b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public q.a f5427c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public x f5428d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f5429e;

        public a() {
            this.f5426b = "GET";
            this.f5427c = new q.a();
        }

        public a a(String str, String str2) {
            this.f5427c.a(str, str2);
            return this;
        }

        public w b() {
            if (this.f5425a != null) {
                return new w(this);
            }
            throw new IllegalStateException("url == null");
        }

        public a c(d dVar) {
            String string = dVar.toString();
            return string.isEmpty() ? h("Cache-Control") : d("Cache-Control", string);
        }

        public a d(String str, String str2) {
            this.f5427c.g(str, str2);
            return this;
        }

        public a e(q qVar) {
            this.f5427c = qVar.d();
            return this;
        }

        public a f(String str, x xVar) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (xVar != null && !f.b0.g.f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (xVar != null || !f.b0.g.f.e(str)) {
                this.f5426b = str;
                this.f5428d = xVar;
                return this;
            }
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }

        public a g(x xVar) {
            return f("POST", xVar);
        }

        public a h(String str) {
            this.f5427c.f(str);
            return this;
        }

        public a i(String str) {
            Objects.requireNonNull(str, "url == null");
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            HttpUrl httpUrlQ = HttpUrl.q(str);
            if (httpUrlQ != null) {
                return j(httpUrlQ);
            }
            throw new IllegalArgumentException("unexpected url: " + str);
        }

        public a j(HttpUrl httpUrl) {
            Objects.requireNonNull(httpUrl, "url == null");
            this.f5425a = httpUrl;
            return this;
        }

        public a(w wVar) {
            this.f5425a = wVar.f5419a;
            this.f5426b = wVar.f5420b;
            this.f5428d = wVar.f5422d;
            this.f5429e = wVar.f5423e;
            this.f5427c = wVar.f5421c.d();
        }
    }
}
