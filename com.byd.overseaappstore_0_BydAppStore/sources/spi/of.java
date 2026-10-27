package spi;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class of implements vr {
    public int a;
    public boolean b;
    public final l2 c;
    public final Inflater d;

    public of(@NotNull l2 source, @NotNull Inflater inflater) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.c = source;
        this.d = inflater;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public of(@NotNull vr source, @NotNull Inflater inflater) {
        this(b.c(source), inflater);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
    }

    @Override // spi.vr, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.b) {
            return;
        }
        this.d.end();
        this.b = true;
        this.c.close();
    }

    @Override // spi.vr
    public final long read(@NotNull g2 sink, long j) throws IOException {
        long j2;
        Intrinsics.checkNotNullParameter(sink, "sink");
        do {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!(j >= 0)) {
                throw new IllegalArgumentException(d.d("byteCount < 0: ", j).toString());
            }
            if (!(!this.b)) {
                throw new IllegalStateException("closed".toString());
            }
            if (j == 0) {
                j2 = 0;
            } else {
                try {
                    vo voVarO = sink.O(1);
                    int iMin = (int) Math.min(j, 8192 - voVarO.c);
                    if (this.d.needsInput() && !this.c.h()) {
                        vo voVar = this.c.a().a;
                        Intrinsics.checkNotNull(voVar);
                        int i = voVar.c;
                        int i2 = voVar.b;
                        int i3 = i - i2;
                        this.a = i3;
                        this.d.setInput(voVar.a, i2, i3);
                    }
                    int iInflate = this.d.inflate(voVarO.a, voVarO.c, iMin);
                    int i4 = this.a;
                    if (i4 != 0) {
                        int remaining = i4 - this.d.getRemaining();
                        this.a -= remaining;
                        this.c.skip(remaining);
                    }
                    if (iInflate > 0) {
                        voVarO.c += iInflate;
                        j2 = iInflate;
                        sink.b += j2;
                    } else {
                        if (voVarO.b == voVarO.c) {
                            sink.a = voVarO.a();
                            wo.b(voVarO);
                        }
                        j2 = 0;
                    }
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            }
            if (j2 > 0) {
                return j2;
            }
            if (this.d.finished() || this.d.needsDictionary()) {
                return -1L;
            }
        } while (!this.c.h());
        throw new EOFException("source exhausted prematurely");
    }

    @Override // spi.vr
    @NotNull
    public final dt timeout() {
        return this.c.timeout();
    }
}
