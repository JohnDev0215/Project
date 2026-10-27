package p6;

import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: BufferedSink.kt */
/* JADX INFO: loaded from: classes.dex */
public interface f extends y, WritableByteChannel {
    f H(String str);

    f M(int i7);

    f Q(h hVar);

    e b();

    f d(byte[] bArr);

    f e(byte[] bArr, int i7, int i8);

    @Override // p6.y, java.io.Flushable
    void flush();

    f j(long j7);

    f t(int i7);

    f y(int i7);
}
