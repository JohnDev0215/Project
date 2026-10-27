package spi;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.oe;

/* JADX INFO: loaded from: classes2.dex */
public final class df implements Closeable {
    public static final Logger g;
    public final g2 a;
    public int b;
    public boolean c;

    @NotNull
    public final oe.b d;
    public final k2 e;
    public final boolean f;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        new a(null);
        g = Logger.getLogger(qe.class.getName());
    }

    public df(@NotNull k2 sink, boolean z) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.e = sink;
        this.f = z;
        g2 g2Var = new g2();
        this.a = g2Var;
        this.b = 16384;
        this.d = new oe.b(0, false, g2Var, 3, null);
    }

    public final synchronized void B(boolean z, int i, @Nullable g2 g2Var, int i2) throws IOException {
        if (this.c) {
            throw new IOException("closed");
        }
        C(i, i2, 0, z ? 1 : 0);
        if (i2 > 0) {
            k2 k2Var = this.e;
            Intrinsics.checkNotNull(g2Var);
            k2Var.p(g2Var, i2);
        }
    }

    public final void C(int i, int i2, int i3, int i4) throws IOException {
        Logger logger = g;
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(qe.e.b(false, i, i2, i3, i4));
        }
        if (!(i2 <= this.b)) {
            throw new IllegalArgumentException(e.f(e.k("FRAME_SIZE_ERROR length > "), this.b, ": ", i2).toString());
        }
        if (!((((int) 2147483648L) & i) == 0)) {
            throw new IllegalArgumentException(e.c("reserved bit set: ", i).toString());
        }
        k2 writeMedium = this.e;
        byte[] bArr = uu.a;
        Intrinsics.checkNotNullParameter(writeMedium, "$this$writeMedium");
        writeMedium.i((i2 >>> 16) & 255);
        writeMedium.i((i2 >>> 8) & 255);
        writeMedium.i(i2 & 255);
        this.e.i(i3 & 255);
        this.e.i(i4 & 255);
        this.e.d(i & Integer.MAX_VALUE);
    }

    public final synchronized void D(int i, @NotNull ErrorCode errorCode, @NotNull byte[] debugData) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        Intrinsics.checkNotNullParameter(debugData, "debugData");
        if (this.c) {
            throw new IOException("closed");
        }
        if (!(errorCode.getHttpCode() != -1)) {
            throw new IllegalArgumentException("errorCode.httpCode == -1".toString());
        }
        C(0, debugData.length + 8, 7, 0);
        this.e.d(i);
        this.e.d(errorCode.getHttpCode());
        if (!(debugData.length == 0)) {
            this.e.t(debugData);
        }
        this.e.flush();
    }

    public final synchronized void E(boolean z, int i, @NotNull List<ie> headerBlock) throws IOException {
        Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
        if (this.c) {
            throw new IOException("closed");
        }
        this.d.e(headerBlock);
        long j = this.a.b;
        long jMin = Math.min(this.b, j);
        int i2 = j == jMin ? 4 : 0;
        if (z) {
            i2 |= 1;
        }
        C(i, (int) jMin, 1, i2);
        this.e.p(this.a, jMin);
        if (j > jMin) {
            I(i, j - jMin);
        }
    }

    public final synchronized void F(boolean z, int i, int i2) throws IOException {
        if (this.c) {
            throw new IOException("closed");
        }
        C(0, 8, 6, z ? 1 : 0);
        this.e.d(i);
        this.e.d(i2);
        this.e.flush();
    }

    public final synchronized void G(int i, @NotNull ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (this.c) {
            throw new IOException("closed");
        }
        if (!(errorCode.getHttpCode() != -1)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        C(i, 4, 3, 0);
        this.e.d(errorCode.getHttpCode());
        this.e.flush();
    }

    public final synchronized void H(int i, long j) throws IOException {
        if (this.c) {
            throw new IOException("closed");
        }
        if (!(j != 0 && j <= 2147483647L)) {
            throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j).toString());
        }
        C(i, 4, 8, 0);
        this.e.d((int) j);
        this.e.flush();
    }

    public final void I(int i, long j) throws IOException {
        while (j > 0) {
            long jMin = Math.min(this.b, j);
            j -= jMin;
            C(i, (int) jMin, 9, j == 0 ? 4 : 0);
            this.e.p(this.a, jMin);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        this.c = true;
        this.e.close();
    }

    public final synchronized void e(@NotNull jp peerSettings) throws IOException {
        Intrinsics.checkNotNullParameter(peerSettings, "peerSettings");
        if (this.c) {
            throw new IOException("closed");
        }
        int i = this.b;
        int i2 = peerSettings.a;
        if ((i2 & 32) != 0) {
            i = peerSettings.b[5];
        }
        this.b = i;
        int i3 = i2 & 2;
        if ((i3 != 0 ? peerSettings.b[1] : -1) != -1) {
            oe.b bVar = this.d;
            int i4 = i3 != 0 ? peerSettings.b[1] : -1;
            Objects.requireNonNull(bVar);
            int iMin = Math.min(i4, 16384);
            int i5 = bVar.c;
            if (i5 != iMin) {
                if (iMin < i5) {
                    bVar.a = Math.min(bVar.a, iMin);
                }
                bVar.b = true;
                bVar.c = iMin;
                int i6 = bVar.g;
                if (iMin < i6) {
                    if (iMin == 0) {
                        bVar.a();
                    } else {
                        bVar.b(i6 - iMin);
                    }
                }
            }
        }
        C(0, 0, 4, 1);
        this.e.flush();
    }

    public final synchronized void flush() throws IOException {
        if (this.c) {
            throw new IOException("closed");
        }
        this.e.flush();
    }
}
