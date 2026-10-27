package okhttp3;

import com.spotify.connectivity.productstate.RxProductState;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.c;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpMethod;
import p.ng2;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Request {
    public final HttpUrl a;
    public final String b;
    public final Headers c;
    public final RequestBody d;
    public final Map e;
    public CacheControl f;

    public static class Builder {
        public HttpUrl a;
        public RequestBody d;
        public LinkedHashMap e = new LinkedHashMap();
        public String b = com.spotify.cosmos.cosmos.Request.GET;
        public Headers.Builder c = new Headers.Builder();

        public final void a(String str, String str2) {
            v03.j(str2, "value");
            this.c.a(str, str2);
        }

        public final Request b() {
            HttpUrl httpUrl = this.a;
            if (httpUrl != null) {
                return new Request(httpUrl, this.b, this.c.e(), this.d, Util.z(this.e));
            }
            throw new IllegalStateException("url == null".toString());
        }

        public final void c(CacheControl cacheControl) {
            v03.j(cacheControl, "cacheControl");
            String string = cacheControl.toString();
            if (string.length() == 0) {
                this.c.g("Cache-Control");
            } else {
                d("Cache-Control", string);
            }
        }

        public final void d(String str, String str2) {
            v03.j(str, "name");
            v03.j(str2, "value");
            Headers.Builder builder = this.c;
            builder.getClass();
            Headers.e.getClass();
            Headers.Companion.a(str);
            Headers.Companion.b(str2, str);
            builder.g(str);
            builder.c(str, str2);
        }

        public final void e(Headers headers) {
            v03.j(headers, "headers");
            this.c = headers.c();
        }

        public final void f(String str, RequestBody requestBody) {
            v03.j(str, "method");
            if (str.length() <= 0) {
                throw new IllegalArgumentException("method.isEmpty() == true".toString());
            }
            if (requestBody == null) {
                if (!(!HttpMethod.c(str))) {
                    throw new IllegalArgumentException(u4.j("method ", str, " must have a request body.").toString());
                }
            } else if (!HttpMethod.b(str)) {
                throw new IllegalArgumentException(u4.j("method ", str, " must not have a request body.").toString());
            }
            this.b = str;
            this.d = requestBody;
        }

        public final void g(Class cls, Object obj) {
            v03.j(cls, RxProductState.Keys.KEY_TYPE);
            if (obj == null) {
                this.e.remove(cls);
                return;
            }
            if (this.e.isEmpty()) {
                this.e = new LinkedHashMap();
            }
            LinkedHashMap linkedHashMap = this.e;
            Object objCast = cls.cast(obj);
            v03.h(objCast);
            linkedHashMap.put(cls, objCast);
        }

        public final void h(String str) {
            v03.j(str, "url");
            if (ng2.g0(str, "ws:", true)) {
                String strSubstring = str.substring(3);
                v03.i(strSubstring, "this as java.lang.String).substring(startIndex)");
                str = "http:".concat(strSubstring);
            } else if (ng2.g0(str, "wss:", true)) {
                String strSubstring2 = str.substring(4);
                v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
                str = "https:".concat(strSubstring2);
            }
            HttpUrl.k.getClass();
            this.a = HttpUrl.Companion.c(str);
        }
    }

    public Request(HttpUrl httpUrl, String str, Headers headers, RequestBody requestBody, Map map) {
        v03.j(str, "method");
        this.a = httpUrl;
        this.b = str;
        this.c = headers;
        this.d = requestBody;
        this.e = map;
    }

    public final CacheControl a() {
        CacheControl cacheControl = this.f;
        if (cacheControl != null) {
            return cacheControl;
        }
        CacheControl.n.getClass();
        CacheControl cacheControlA = CacheControl.Companion.a(this.c);
        this.f = cacheControlA;
        return cacheControlA;
    }

    public final Builder b() {
        Builder builder = new Builder();
        builder.e = new LinkedHashMap();
        builder.a = this.a;
        builder.b = this.b;
        builder.d = this.d;
        Map map = this.e;
        builder.e = map.isEmpty() ? new LinkedHashMap() : c.n0(map);
        builder.c = this.c.c();
        return builder;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.b);
        sb.append(", url=");
        sb.append(this.a);
        Headers headers = this.c;
        if (headers.size() != 0) {
            sb.append(", headers=[");
            int i = 0;
            for (Pair<? extends String, ? extends String> pair : headers) {
                int i2 = i + 1;
                if (i < 0) {
                    v03.P();
                    throw null;
                }
                Pair<? extends String, ? extends String> pair2 = pair;
                String str = (String) pair2.a();
                String str2 = (String) pair2.b();
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(str);
                sb.append(':');
                sb.append(str2);
                i = i2;
            }
            sb.append(']');
        }
        Map map = this.e;
        if (!map.isEmpty()) {
            sb.append(", tags=");
            sb.append(map);
        }
        sb.append('}');
        String string = sb.toString();
        v03.i(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
