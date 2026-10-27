package spi;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.hf;
import spi.ke;

/* JADX INFO: loaded from: classes2.dex */
public final class im {
    public x2 a;

    @NotNull
    public final hf b;

    @NotNull
    public final String c;

    @NotNull
    public final ke d;

    @Nullable
    public final mm e;

    @NotNull
    public final Map<Class<?>, Object> f;

    public static class a {

        @Nullable
        public hf a;

        @NotNull
        public String b;

        @NotNull
        public ke.a c;

        @Nullable
        public mm d;

        @NotNull
        public Map<Class<?>, Object> e;

        public a() {
            this.e = new LinkedHashMap();
            this.b = "GET";
            this.c = new ke.a();
        }

        public a(@NotNull im request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.e = new LinkedHashMap();
            this.a = request.b;
            this.b = request.c;
            this.d = request.e;
            this.e = request.f.isEmpty() ? new LinkedHashMap<>() : MapsKt.toMutableMap(request.f);
            this.c = request.d.c();
        }

        @NotNull
        public final im a() {
            Map mapUnmodifiableMap;
            hf hfVar = this.a;
            if (hfVar == null) {
                throw new IllegalStateException("url == null".toString());
            }
            String str = this.b;
            ke keVarD = this.c.d();
            mm mmVar = this.d;
            Map<Class<?>, Object> toImmutableMap = this.e;
            byte[] bArr = uu.a;
            Intrinsics.checkNotNullParameter(toImmutableMap, "$this$toImmutableMap");
            if (toImmutableMap.isEmpty()) {
                mapUnmodifiableMap = MapsKt.emptyMap();
            } else {
                mapUnmodifiableMap = Collections.unmodifiableMap(new LinkedHashMap(toImmutableMap));
                Intrinsics.checkNotNullExpressionValue(mapUnmodifiableMap, "Collections.unmodifiableMap(LinkedHashMap(this))");
            }
            return new im(hfVar, str, keVarD, mmVar, mapUnmodifiableMap);
        }

        @NotNull
        public final a b(@NotNull String name, @NotNull String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.c.g(name, value);
            return this;
        }

        @NotNull
        public final a c(@NotNull ke headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.c = headers.c();
            return this;
        }

        @NotNull
        public final a d(@NotNull String method, @Nullable mm mmVar) {
            Intrinsics.checkNotNullParameter(method, "method");
            if (!(method.length() > 0)) {
                throw new IllegalArgumentException("method.isEmpty() == true".toString());
            }
            if (mmVar == null) {
                gf gfVar = gf.a;
                Intrinsics.checkNotNullParameter(method, "method");
                if (!(!(Intrinsics.areEqual(method, "POST") || Intrinsics.areEqual(method, "PUT") || Intrinsics.areEqual(method, "PATCH") || Intrinsics.areEqual(method, "PROPPATCH") || Intrinsics.areEqual(method, "REPORT")))) {
                    throw new IllegalArgumentException(e.d("method ", method, " must have a request body.").toString());
                }
            } else if (!gf.b(method)) {
                throw new IllegalArgumentException(e.d("method ", method, " must not have a request body.").toString());
            }
            this.b = method;
            this.d = mmVar;
            return this;
        }

        @NotNull
        public final a e(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.c.f(name);
            return this;
        }

        @NotNull
        public final <T> a f(@NotNull Class<? super T> type, @Nullable T t) {
            Intrinsics.checkNotNullParameter(type, "type");
            if (t == null) {
                this.e.remove(type);
            } else {
                if (this.e.isEmpty()) {
                    this.e = new LinkedHashMap();
                }
                Map<Class<?>, Object> map = this.e;
                T tCast = type.cast(t);
                Intrinsics.checkNotNull(tCast);
                map.put(type, tCast);
            }
            return this;
        }

        @NotNull
        public final a g(@NotNull String toHttpUrl) {
            StringBuilder sbK;
            int i;
            Intrinsics.checkNotNullParameter(toHttpUrl, "url");
            if (!StringsKt.u(toHttpUrl, "ws:")) {
                if (StringsKt.u(toHttpUrl, "wss:")) {
                    sbK = e.k("https:");
                    i = 4;
                }
                Objects.requireNonNull(hf.l);
                Intrinsics.checkNotNullParameter(toHttpUrl, "$this$toHttpUrl");
                hf.a aVar = new hf.a();
                aVar.c(null, toHttpUrl);
                hf url = aVar.a();
                Intrinsics.checkNotNullParameter(url, "url");
                this.a = url;
                return this;
            }
            sbK = e.k("http:");
            i = 3;
            String strSubstring = toHttpUrl.substring(i);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
            sbK.append(strSubstring);
            toHttpUrl = sbK.toString();
            Objects.requireNonNull(hf.l);
            Intrinsics.checkNotNullParameter(toHttpUrl, "$this$toHttpUrl");
            hf.a aVar2 = new hf.a();
            aVar2.c(null, toHttpUrl);
            hf url2 = aVar2.a();
            Intrinsics.checkNotNullParameter(url2, "url");
            this.a = url2;
            return this;
        }

        @NotNull
        public final a h(@NotNull hf url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.a = url;
            return this;
        }
    }

    public im(@NotNull hf url, @NotNull String method, @NotNull ke headers, @Nullable mm mmVar, @NotNull Map<Class<?>, ? extends Object> tags) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(tags, "tags");
        this.b = url;
        this.c = method;
        this.d = headers;
        this.e = mmVar;
        this.f = tags;
    }

    @JvmName(name = "cacheControl")
    @NotNull
    public final x2 a() {
        x2 x2Var = this.a;
        if (x2Var != null) {
            return x2Var;
        }
        x2 x2VarB = x2.n.b(this.d);
        this.a = x2VarB;
        return x2VarB;
    }

    @Nullable
    public final String b(@NotNull String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.d.a(name);
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("Request{method=");
        sbK.append(this.c);
        sbK.append(", url=");
        sbK.append(this.b);
        if (this.d.a.length / 2 != 0) {
            sbK.append(", headers=[");
            int i = 0;
            for (Pair<? extends String, ? extends String> pair : this.d) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair<? extends String, ? extends String> pair2 = pair;
                String strComponent1 = pair2.component1();
                String strComponent2 = pair2.component2();
                if (i > 0) {
                    sbK.append(", ");
                }
                sbK.append(strComponent1);
                sbK.append(':');
                sbK.append(strComponent2);
                i = i2;
            }
            sbK.append(']');
        }
        if (!this.f.isEmpty()) {
            sbK.append(", tags=");
            sbK.append(this.f);
        }
        sbK.append('}');
        String string = sbK.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
