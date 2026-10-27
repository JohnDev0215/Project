package okio;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.EOFException;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: Okio.kt */
/* JADX INFO: loaded from: classes.dex */
final class BlackholeSink implements Sink {
    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() {
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return Timeout.NONE;
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws EOFException {
        i.c(buffer, BYDAutoMultimediaDevice.PARAM_SOURCE);
        buffer.skip(j);
    }
}
