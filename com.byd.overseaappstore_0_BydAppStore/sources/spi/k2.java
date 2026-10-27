package spi;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public interface k2 extends rp, WritableByteChannel {
    @NotNull
    g2 a();

    @NotNull
    k2 c(int i) throws IOException;

    @NotNull
    k2 d(int i) throws IOException;

    @Override // spi.rp, java.io.Flushable
    void flush() throws IOException;

    @NotNull
    k2 i(int i) throws IOException;

    @NotNull
    k2 j() throws IOException;

    @NotNull
    k2 l(@NotNull String str) throws IOException;

    @NotNull
    k2 n(@NotNull byte[] bArr, int i, int i2) throws IOException;

    @NotNull
    k2 o(long j) throws IOException;

    @NotNull
    k2 t(@NotNull byte[] bArr) throws IOException;

    @NotNull
    k2 u(@NotNull ByteString byteString) throws IOException;

    long x(@NotNull vr vrVar) throws IOException;

    @NotNull
    k2 y(long j) throws IOException;
}
