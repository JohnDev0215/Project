package s2;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Surface;
import java.nio.ByteBuffer;
import l3.C1764u;
import z2.C2758d;

/* JADX INFO: loaded from: classes.dex */
public interface k {
    void a();

    void b(int i7, m2.c cVar, long j3, int i10);

    void c(int i7, int i10, int i11, long j3);

    void d(Bundle bundle);

    int e(MediaCodec.BufferInfo bufferInfo);

    void f(long j3, int i7);

    void flush();

    void g(int i7);

    void h(C2758d c2758d, Handler handler);

    void i(int i7);

    MediaFormat j();

    void k();

    ByteBuffer l(int i7);

    void m(Surface surface);

    ByteBuffer o(int i7);

    int r();

    default boolean s(C1764u c1764u) {
        return false;
    }
}
