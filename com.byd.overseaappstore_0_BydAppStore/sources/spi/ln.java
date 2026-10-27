package spi;

import java.io.Closeable;
import java.util.Objects;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ke;

/* JADX INFO: loaded from: classes2.dex */
public final class ln implements Closeable {
    public x2 a;

    @NotNull
    public final im b;

    @NotNull
    public final Protocol c;

    @NotNull
    public final String d;
    public final int e;

    @Nullable
    public final ge f;

    @NotNull
    public final ke g;

    @Nullable
    public final mn h;

    @Nullable
    public final ln i;

    @Nullable
    public final ln j;

    @Nullable
    public final ln k;
    public final long l;
    public final long m;

    @Nullable
    public final y8 n;

    public static class a {

        @Nullable
        public im a;

        @Nullable
        public Protocol b;
        public int c;

        @Nullable
        public String d;

        @Nullable
        public ge e;

        @NotNull
        public ke.a f;

        @Nullable
        public mn g;

        @Nullable
        public ln h;

        @Nullable
        public ln i;

        @Nullable
        public ln j;
        public long k;
        public long l;

        @Nullable
        public y8 m;

        public a() {
            this.c = -1;
            this.f = new ke.a();
        }

        public a(@NotNull ln response) {
            Intrinsics.checkNotNullParameter(response, "response");
            this.c = -1;
            this.a = response.b;
            this.b = response.c;
            this.c = response.e;
            this.d = response.d;
            this.e = response.f;
            this.f = response.g.c();
            this.g = response.h;
            this.h = response.i;
            this.i = response.j;
            this.j = response.k;
            this.k = response.l;
            this.l = response.m;
            this.m = response.n;
        }

        @NotNull
        public final ln a() {
            int i = this.c;
            if (!(i >= 0)) {
                StringBuilder sbK = e.k("code < 0: ");
                sbK.append(this.c);
                throw new IllegalStateException(sbK.toString().toString());
            }
            im imVar = this.a;
            if (imVar == null) {
                throw new IllegalStateException("request == null".toString());
            }
            Protocol protocol = this.b;
            if (protocol == null) {
                throw new IllegalStateException("protocol == null".toString());
            }
            String str = this.d;
            if (str != null) {
                return new ln(imVar, protocol, str, i, this.e, this.f.d(), this.g, this.h, this.i, this.j, this.k, this.l, this.m);
            }
            throw new IllegalStateException("message == null".toString());
        }

        @NotNull
        public final a b(@Nullable ln lnVar) {
            c("cacheResponse", lnVar);
            this.i = lnVar;
            return this;
        }

        public final void c(String str, ln lnVar) {
            if (lnVar != null) {
                if (!(lnVar.h == null)) {
                    throw new IllegalArgumentException(d.f(str, ".body != null").toString());
                }
                if (!(lnVar.i == null)) {
                    throw new IllegalArgumentException(d.f(str, ".networkResponse != null").toString());
                }
                if (!(lnVar.j == null)) {
                    throw new IllegalArgumentException(d.f(str, ".cacheResponse != null").toString());
                }
                if (!(lnVar.k == null)) {
                    throw new IllegalArgumentException(d.f(str, ".priorResponse != null").toString());
                }
            }
        }

        @NotNull
        public final a d(@NotNull ke headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.f = headers.c();
            return this;
        }

        @NotNull
        public final a e(@NotNull String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.d = message;
            return this;
        }

        @NotNull
        public final a f(@NotNull Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            this.b = protocol;
            return this;
        }

        @NotNull
        public final a g(@NotNull im request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.a = request;
            return this;
        }
    }

    public ln(@NotNull im request, @NotNull Protocol protocol, @NotNull String message, int i, @Nullable ge geVar, @NotNull ke headers, @Nullable mn mnVar, @Nullable ln lnVar, @Nullable ln lnVar2, @Nullable ln lnVar3, long j, long j2, @Nullable y8 y8Var) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.b = request;
        this.c = protocol;
        this.d = message;
        this.e = i;
        this.f = geVar;
        this.g = headers;
        this.h = mnVar;
        this.i = lnVar;
        this.j = lnVar2;
        this.k = lnVar3;
        this.l = j;
        this.m = j2;
        this.n = y8Var;
    }

    public static String B(ln lnVar, String name) {
        Objects.requireNonNull(lnVar);
        Intrinsics.checkNotNullParameter(name, "name");
        String strA = lnVar.g.a(name);
        if (strA != null) {
            return strA;
        }
        return null;
    }

    public final boolean C() {
        int i = this.e;
        return 200 <= i && 299 >= i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        mn mnVar = this.h;
        if (mnVar == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed".toString());
        }
        mnVar.close();
    }

    @JvmName(name = "cacheControl")
    @NotNull
    public final x2 e() {
        x2 x2Var = this.a;
        if (x2Var != null) {
            return x2Var;
        }
        x2 x2VarB = x2.n.b(this.g);
        this.a = x2VarB;
        return x2VarB;
    }

    @NotNull
    public final String toString() {
        StringBuilder sbK = e.k("Response{protocol=");
        sbK.append(this.c);
        sbK.append(", code=");
        sbK.append(this.e);
        sbK.append(", message=");
        sbK.append(this.d);
        sbK.append(", url=");
        sbK.append(this.b.b);
        sbK.append('}');
        return sbK.toString();
    }
}
