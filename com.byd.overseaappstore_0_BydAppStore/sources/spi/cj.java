package spi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.mi;

/* JADX INFO: loaded from: classes2.dex */
public final class cj extends mm {

    @JvmField
    @NotNull
    public static final mi e;

    @JvmField
    @NotNull
    public static final mi f;
    public static final byte[] g;
    public static final byte[] h;
    public static final byte[] i;
    public final mi a;
    public long b;
    public final ByteString c;

    @NotNull
    public final List<c> d;

    public static final class a {
        public final ByteString a;
        public mi b;
        public final List<c> c;

        @JvmOverloads
        public a() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        @JvmOverloads
        public a(@NotNull String boundary) {
            Intrinsics.checkNotNullParameter(boundary, "boundary");
            this.a = ByteString.INSTANCE.d(boundary);
            this.b = cj.e;
            this.c = new ArrayList();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        public /* synthetic */ a(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(str, "UUID.randomUUID().toString()");
            }
            this(str);
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public static final class c {
        public static final a c = new a(null);

        @Nullable
        public final ke a;

        @NotNull
        public final mm b;

        public static final class a {
            private a() {
            }

            public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        private c(ke keVar, mm mmVar) {
            this.a = keVar;
            this.b = mmVar;
        }

        public /* synthetic */ c(ke keVar, mm mmVar, DefaultConstructorMarker defaultConstructorMarker) {
            this(keVar, mmVar);
        }
    }

    static {
        new b(null);
        mi.a aVar = mi.f;
        e = aVar.a("multipart/mixed");
        aVar.a("multipart/alternative");
        aVar.a("multipart/digest");
        aVar.a("multipart/parallel");
        f = aVar.a("multipart/form-data");
        g = new byte[]{(byte) 58, (byte) 32};
        h = new byte[]{(byte) 13, (byte) 10};
        byte b2 = (byte) 45;
        i = new byte[]{b2, b2};
    }

    public cj(@NotNull ByteString boundaryByteString, @NotNull mi type, @NotNull List<c> parts) {
        Intrinsics.checkNotNullParameter(boundaryByteString, "boundaryByteString");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(parts, "parts");
        this.c = boundaryByteString;
        this.d = parts;
        this.a = mi.f.a(type + "; boundary=" + boundaryByteString.utf8());
        this.b = -1L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long a(k2 k2Var, boolean z) throws IOException {
        g2 g2Var;
        if (z) {
            k2Var = new g2();
            g2Var = k2Var;
        } else {
            g2Var = 0;
        }
        int size = this.d.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            c cVar = this.d.get(i2);
            ke keVar = cVar.a;
            mm mmVar = cVar.b;
            Intrinsics.checkNotNull(k2Var);
            k2Var.t(i);
            k2Var.u(this.c);
            k2Var.t(h);
            if (keVar != null) {
                int length = keVar.a.length / 2;
                for (int i3 = 0; i3 < length; i3++) {
                    k2Var.l(keVar.b(i3)).t(g).l(keVar.d(i3)).t(h);
                }
            }
            mi miVarContentType = mmVar.contentType();
            if (miVarContentType != null) {
                k2Var.l("Content-Type: ").l(miVarContentType.a).t(h);
            }
            long jContentLength = mmVar.contentLength();
            if (jContentLength != -1) {
                k2Var.l("Content-Length: ").y(jContentLength).t(h);
            } else if (z) {
                Intrinsics.checkNotNull(g2Var);
                g2Var.B();
                return -1L;
            }
            byte[] bArr = h;
            k2Var.t(bArr);
            if (z) {
                j += jContentLength;
            } else {
                mmVar.writeTo(k2Var);
            }
            k2Var.t(bArr);
        }
        Intrinsics.checkNotNull(k2Var);
        byte[] bArr2 = i;
        k2Var.t(bArr2);
        k2Var.u(this.c);
        k2Var.t(bArr2);
        k2Var.t(h);
        if (!z) {
            return j;
        }
        Intrinsics.checkNotNull(g2Var);
        long j2 = j + g2Var.b;
        g2Var.B();
        return j2;
    }

    @Override // spi.mm
    public final long contentLength() throws IOException {
        long j = this.b;
        if (j != -1) {
            return j;
        }
        long jA = a(null, true);
        this.b = jA;
        return jA;
    }

    @Override // spi.mm
    @NotNull
    public final mi contentType() {
        return this.a;
    }

    @Override // spi.mm
    public final void writeTo(@NotNull k2 sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        a(sink, false);
    }
}
