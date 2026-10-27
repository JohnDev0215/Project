package spi;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class mn implements Closeable {
    public static final b Companion = new b(null);
    private Reader reader;

    public static final class a extends Reader {
        public boolean a;
        public InputStreamReader b;
        public final l2 c;
        public final Charset d;

        public a(@NotNull l2 source, @NotNull Charset charset) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(charset, "charset");
            this.c = source;
            this.d = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            this.a = true;
            InputStreamReader inputStreamReader = this.b;
            if (inputStreamReader != null) {
                inputStreamReader.close();
            } else {
                this.c.close();
            }
        }

        @Override // java.io.Reader
        public final int read(@NotNull char[] cbuf, int i, int i2) throws IOException {
            Intrinsics.checkNotNullParameter(cbuf, "cbuf");
            if (this.a) {
                throw new IOException("Stream closed");
            }
            InputStreamReader inputStreamReader = this.b;
            if (inputStreamReader == null) {
                inputStreamReader = new InputStreamReader(this.c.A(), uu.r(this.c, this.d));
                this.b = inputStreamReader;
            }
            return inputStreamReader.read(cbuf, i, i2);
        }
    }

    public static final class b {

        public static final class a extends mn {
            public final /* synthetic */ l2 a;
            public final /* synthetic */ mi b;
            public final /* synthetic */ long c;

            public a(l2 l2Var, mi miVar, long j) {
                this.a = l2Var;
                this.b = miVar;
                this.c = j;
            }

            @Override // spi.mn
            public final long contentLength() {
                return this.c;
            }

            @Override // spi.mn
            @Nullable
            public final mi contentType() {
                return this.b;
            }

            @Override // spi.mn
            @NotNull
            public final l2 source() {
                return this.a;
            }
        }

        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final mn a(@NotNull String string, @Nullable mi miVar) {
            Intrinsics.checkNotNullParameter(string, "$this$toResponseBody");
            Charset charset = Charsets.UTF_8;
            if (miVar != null) {
                Pattern pattern = mi.d;
                Charset charsetA = miVar.a(null);
                if (charsetA == null) {
                    miVar = mi.f.b(miVar + "; charset=utf-8");
                } else {
                    charset = charsetA;
                }
            }
            g2 g2Var = new g2();
            Intrinsics.checkNotNullParameter(string, "string");
            Intrinsics.checkNotNullParameter(charset, "charset");
            g2 g2VarX = g2Var.X(string, 0, string.length(), charset);
            return c(g2VarX, miVar, g2VarX.b);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final mn b(@NotNull ByteString toResponseBody, @Nullable mi miVar) {
            Intrinsics.checkNotNullParameter(toResponseBody, "$this$toResponseBody");
            g2 g2Var = new g2();
            g2Var.P(toResponseBody);
            return c(g2Var, miVar, toResponseBody.size());
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final mn c(@NotNull l2 asResponseBody, @Nullable mi miVar, long j) {
            Intrinsics.checkNotNullParameter(asResponseBody, "$this$asResponseBody");
            return new a(asResponseBody, miVar, j);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final mn d(@NotNull byte[] toResponseBody, @Nullable mi miVar) {
            Intrinsics.checkNotNullParameter(toResponseBody, "$this$toResponseBody");
            g2 g2Var = new g2();
            g2Var.Q(toResponseBody);
            return c(g2Var, miVar, toResponseBody.length);
        }
    }

    private final Charset charset() {
        Charset charsetA;
        mi miVarContentType = contentType();
        return (miVarContentType == null || (charsetA = miVarContentType.a(Charsets.UTF_8)) == null) ? Charsets.UTF_8 : charsetA;
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [T, java.lang.Object] */
    private final <T> T consumeSource(Function1<? super l2, ? extends T> function1, Function1<? super T, Integer> function12) throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > Integer.MAX_VALUE) {
            throw new IOException(d.d("Cannot buffer entire body for content length: ", jContentLength));
        }
        l2 l2VarSource = source();
        try {
            T tInvoke = function1.invoke(l2VarSource);
            InlineMarker.finallyStart(1);
            CloseableKt.closeFinally(l2VarSource, null);
            InlineMarker.finallyEnd(1);
            int iIntValue = function12.invoke(tInvoke).intValue();
            if (jContentLength == -1 || jContentLength == iIntValue) {
                return tInvoke;
            }
            throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + iIntValue + ") disagree");
        } finally {
        }
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mn create(@NotNull String str, @Nullable mi miVar) {
        return Companion.a(str, miVar);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mn create(@NotNull ByteString byteString, @Nullable mi miVar) {
        return Companion.b(byteString, miVar);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mn create(@NotNull l2 l2Var, @Nullable mi miVar, long j) {
        return Companion.c(l2Var, miVar, j);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.asResponseBody(contentType, contentLength)", imports = {"okhttp3.ResponseBody.Companion.asResponseBody"}))
    @JvmStatic
    @NotNull
    public static final mn create(@Nullable mi miVar, long j, @NotNull l2 content) {
        b bVar = Companion;
        Objects.requireNonNull(bVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return bVar.c(content, miVar, j);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mn create(@NotNull byte[] bArr, @Nullable mi miVar) {
        return Companion.d(bArr, miVar);
    }

    @NotNull
    public final InputStream byteStream() {
        return source().A();
    }

    @NotNull
    public final ByteString byteString() throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > Integer.MAX_VALUE) {
            throw new IOException(d.d("Cannot buffer entire body for content length: ", jContentLength));
        }
        l2 l2VarSource = source();
        try {
            ByteString byteStringQ = l2VarSource.q();
            CloseableKt.closeFinally(l2VarSource, null);
            int size = byteStringQ.size();
            if (jContentLength == -1 || jContentLength == size) {
                return byteStringQ;
            }
            throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + size + ") disagree");
        } finally {
        }
    }

    @NotNull
    public final byte[] bytes() throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > Integer.MAX_VALUE) {
            throw new IOException(d.d("Cannot buffer entire body for content length: ", jContentLength));
        }
        l2 l2VarSource = source();
        try {
            byte[] bArrF = l2VarSource.f();
            CloseableKt.closeFinally(l2VarSource, null);
            int length = bArrF.length;
            if (jContentLength == -1 || jContentLength == length) {
                return bArrF;
            }
            throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + length + ") disagree");
        } finally {
        }
    }

    @NotNull
    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        a aVar = new a(source(), charset());
        this.reader = aVar;
        return aVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        uu.c(source());
    }

    public abstract long contentLength();

    @Nullable
    public abstract mi contentType();

    @NotNull
    public abstract l2 source();

    @NotNull
    public final String string() throws IOException {
        l2 l2VarSource = source();
        try {
            String strM = l2VarSource.m(uu.r(l2VarSource, charset()));
            CloseableKt.closeFinally(l2VarSource, null);
            return strM;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    @NotNull
    public static final mn create(@Nullable mi miVar, @NotNull String content) {
        b bVar = Companion;
        Objects.requireNonNull(bVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return bVar.a(content, miVar);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    @NotNull
    public static final mn create(@Nullable mi miVar, @NotNull ByteString content) {
        b bVar = Companion;
        Objects.requireNonNull(bVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return bVar.b(content, miVar);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toResponseBody(contentType)", imports = {"okhttp3.ResponseBody.Companion.toResponseBody"}))
    @JvmStatic
    @NotNull
    public static final mn create(@Nullable mi miVar, @NotNull byte[] content) {
        b bVar = Companion;
        Objects.requireNonNull(bVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return bVar.d(content, miVar);
    }
}
