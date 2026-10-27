package spi;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class mm {
    public static final a Companion = new a(null);

    public static final class a {

        /* JADX INFO: renamed from: spi.mm$a$a, reason: collision with other inner class name */
        public static final class C0134a extends mm {
            public final /* synthetic */ byte[] a;
            public final /* synthetic */ mi b;
            public final /* synthetic */ int c;
            public final /* synthetic */ int d;

            public C0134a(byte[] bArr, mi miVar, int i, int i2) {
                this.a = bArr;
                this.b = miVar;
                this.c = i;
                this.d = i2;
            }

            @Override // spi.mm
            public final long contentLength() {
                return this.c;
            }

            @Override // spi.mm
            @Nullable
            public final mi contentType() {
                return this.b;
            }

            @Override // spi.mm
            public final void writeTo(@NotNull k2 sink) throws IOException {
                Intrinsics.checkNotNullParameter(sink, "sink");
                sink.n(this.a, this.d, this.c);
            }
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static mm c(a aVar, mi miVar, byte[] content, int i, int i2) {
            if ((i2 & 4) != 0) {
                i = 0;
            }
            int length = (i2 & 8) != 0 ? content.length : 0;
            Objects.requireNonNull(aVar);
            Intrinsics.checkNotNullParameter(content, "content");
            return aVar.b(content, miVar, i, length);
        }

        public static /* synthetic */ mm d(a aVar, byte[] bArr, mi miVar, int i, int i2) {
            if ((i2 & 1) != 0) {
                miVar = null;
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return aVar.b(bArr, miVar, i, (i2 & 4) != 0 ? bArr.length : 0);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        public final mm a(@NotNull String toRequestBody, @Nullable mi miVar) {
            Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
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
            byte[] bytes = toRequestBody.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
            return b(bytes, miVar, 0, bytes.length);
        }

        @JvmStatic
        @JvmName(name = "create")
        @NotNull
        @JvmOverloads
        public final mm b(@NotNull byte[] toRequestBody, @Nullable mi miVar, int i, int i2) {
            Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
            uu.b(toRequestBody.length, i, i2);
            return new C0134a(toRequestBody, miVar, i2, i);
        }
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mm create(@NotNull File asRequestBody, @Nullable mi miVar) {
        Objects.requireNonNull(Companion);
        Intrinsics.checkNotNullParameter(asRequestBody, "$this$asRequestBody");
        return new km(asRequestBody, miVar);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mm create(@NotNull String str, @Nullable mi miVar) {
        return Companion.a(str, miVar);
    }

    @JvmStatic
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmOverloads
    public static final mm create(@Nullable mi miVar, @NotNull byte[] bArr) {
        return a.c(Companion, miVar, bArr, 0, 12);
    }

    @JvmStatic
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmOverloads
    public static final mm create(@Nullable mi miVar, @NotNull byte[] bArr, int i) {
        return a.c(Companion, miVar, bArr, i, 8);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final mm create(@NotNull byte[] bArr) {
        return a.d(Companion, bArr, null, 0, 7);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final mm create(@NotNull byte[] bArr, @Nullable mi miVar) {
        return a.d(Companion, bArr, miVar, 0, 6);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final mm create(@NotNull byte[] bArr, @Nullable mi miVar, int i) {
        return a.d(Companion, bArr, miVar, i, 4);
    }

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    @JvmOverloads
    public static final mm create(@NotNull byte[] bArr, @Nullable mi miVar, int i, int i2) {
        return Companion.b(bArr, miVar, i, i2);
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    @Nullable
    public abstract mi contentType();

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public abstract void writeTo(@NotNull k2 k2Var) throws IOException;

    @JvmStatic
    @JvmName(name = "create")
    @NotNull
    public static final mm create(@NotNull ByteString toRequestBody, @Nullable mi miVar) {
        Objects.requireNonNull(Companion);
        Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
        return new lm(toRequestBody, miVar);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'file' argument first to fix Java", replaceWith = @ReplaceWith(expression = "file.asRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.asRequestBody"}))
    @JvmStatic
    @NotNull
    public static final mm create(@Nullable mi miVar, @NotNull File asRequestBody) {
        Objects.requireNonNull(Companion);
        Intrinsics.checkNotNullParameter(asRequestBody, "file");
        Intrinsics.checkNotNullParameter(asRequestBody, "$this$asRequestBody");
        return new km(asRequestBody, miVar);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    @NotNull
    public static final mm create(@Nullable mi miVar, @NotNull String content) {
        a aVar = Companion;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return aVar.a(content, miVar);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmStatic
    @NotNull
    public static final mm create(@Nullable mi miVar, @NotNull ByteString toRequestBody) {
        Objects.requireNonNull(Companion);
        Intrinsics.checkNotNullParameter(toRequestBody, "content");
        Intrinsics.checkNotNullParameter(toRequestBody, "$this$toRequestBody");
        return new lm(toRequestBody, miVar);
    }

    @JvmStatic
    @NotNull
    @Deprecated(level = DeprecationLevel.WARNING, message = "Moved to extension function. Put the 'content' argument first to fix Java", replaceWith = @ReplaceWith(expression = "content.toRequestBody(contentType, offset, byteCount)", imports = {"okhttp3.RequestBody.Companion.toRequestBody"}))
    @JvmOverloads
    public static final mm create(@Nullable mi miVar, @NotNull byte[] content, int i, int i2) {
        a aVar = Companion;
        Objects.requireNonNull(aVar);
        Intrinsics.checkNotNullParameter(content, "content");
        return aVar.b(content, miVar, i, i2);
    }
}
