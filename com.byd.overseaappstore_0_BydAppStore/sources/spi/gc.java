package spi;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class gc extends mm {
    public static final mi c;
    public final List<String> a;
    public final List<String> b;

    public static final class a {
        public final List<String> a;
        public final List<String> b;
        public final Charset c;

        @JvmOverloads
        public a() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        @JvmOverloads
        public a(@Nullable Charset charset) {
            this.c = charset;
            this.a = new ArrayList();
            this.b = new ArrayList();
        }

        public /* synthetic */ a(Charset charset, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : charset);
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new b(null);
        c = mi.f.a("application/x-www-form-urlencoded");
    }

    public gc(@NotNull List<String> encodedNames, @NotNull List<String> encodedValues) {
        Intrinsics.checkNotNullParameter(encodedNames, "encodedNames");
        Intrinsics.checkNotNullParameter(encodedValues, "encodedValues");
        this.a = uu.x(encodedNames);
        this.b = uu.x(encodedValues);
    }

    public final long a(k2 k2Var, boolean z) throws EOFException {
        g2 g2VarA;
        if (z) {
            g2VarA = new g2();
        } else {
            Intrinsics.checkNotNull(k2Var);
            g2VarA = k2Var.a();
        }
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                g2VarA.S(38);
            }
            g2VarA.Y(this.a.get(i));
            g2VarA.S(61);
            g2VarA.Y(this.b.get(i));
        }
        if (!z) {
            return 0L;
        }
        long j = g2VarA.b;
        g2VarA.B();
        return j;
    }

    @Override // spi.mm
    public final long contentLength() {
        return a(null, true);
    }

    @Override // spi.mm
    @NotNull
    public final mi contentType() {
        return c;
    }

    @Override // spi.mm
    public final void writeTo(@NotNull k2 sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        a(sink, false);
    }
}
