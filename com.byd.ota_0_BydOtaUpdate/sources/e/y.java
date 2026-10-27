package e;

import e.s;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpUrl f3670a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3671b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s f3672c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    public final z f3673d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f3674e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile d f3675f;

    public y(a aVar) {
        this.f3670a = aVar.f3676a;
        this.f3671b = aVar.f3677b;
        this.f3672c = aVar.f3678c.d();
        this.f3673d = aVar.f3679d;
        Object obj = aVar.f3680e;
        this.f3674e = obj == null ? this : obj;
    }

    @Nullable
    public z a() {
        return this.f3673d;
    }

    public d b() {
        d dVar = this.f3675f;
        if (dVar != null) {
            return dVar;
        }
        d dVarK = d.k(this.f3672c);
        this.f3675f = dVarK;
        return dVarK;
    }

    public String c(String str) {
        return this.f3672c.a(str);
    }

    public s d() {
        return this.f3672c;
    }

    public boolean e() {
        return this.f3670a.m();
    }

    public String f() {
        return this.f3671b;
    }

    public a g() {
        return new a(this);
    }

    public HttpUrl h() {
        return this.f3670a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request{method=");
        sb.append(this.f3671b);
        sb.append(", url=");
        sb.append(this.f3670a);
        sb.append(", tag=");
        Object obj = this.f3674e;
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
        public HttpUrl f3676a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f3677b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public s.a f3678c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public z f3679d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public Object f3680e;

        public a() {
            this.f3677b = "GET";
            this.f3678c = new s.a();
        }

        public a a(String str, String str2) {
            this.f3678c.a(str, str2);
            return this;
        }

        public y b() {
            if (this.f3676a != null) {
                return new y(this);
            }
            throw new IllegalStateException("url == null");
        }

        public a c(d dVar) {
            String string = dVar.toString();
            return string.isEmpty() ? g("Cache-Control") : d("Cache-Control", string);
        }

        public a d(String str, String str2) {
            this.f3678c.g(str, str2);
            return this;
        }

        public a e(s sVar) {
            this.f3678c = sVar.d();
            return this;
        }

        public a f(String str, @Nullable z zVar) {
            Objects.requireNonNull(str, "method == null");
            if (str.length() == 0) {
                throw new IllegalArgumentException("method.length() == 0");
            }
            if (zVar != null && !e.d0.g.f.b(str)) {
                throw new IllegalArgumentException("method " + str + " must not have a request body.");
            }
            if (zVar != null || !e.d0.g.f.e(str)) {
                this.f3677b = str;
                this.f3679d = zVar;
                return this;
            }
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }

        public a g(String str) {
            this.f3678c.f(str);
            return this;
        }

        public a h(HttpUrl httpUrl) {
            Objects.requireNonNull(httpUrl, "url == null");
            this.f3676a = httpUrl;
            return this;
        }

        public a(y yVar) {
            this.f3676a = yVar.f3670a;
            this.f3677b = yVar.f3671b;
            this.f3679d = yVar.f3673d;
            this.f3680e = yVar.f3674e;
            this.f3678c = yVar.f3672c.d();
        }
    }
}
