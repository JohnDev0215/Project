package n4;

import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class t implements e4.d {
    @Override // e4.d
    public final ImageHeaderParser$ImageType a(ByteBuffer byteBuffer) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    @Override // e4.d
    public final int b(ByteBuffer byteBuffer, F.l lVar) {
        AtomicReference atomicReference = A4.c.f414a;
        return c(new A4.a(byteBuffer), lVar);
    }

    @Override // e4.d
    public final int c(InputStream inputStream, F.l lVar) {
        int iC = new W1.g(inputStream).c();
        if (iC == 0) {
            return -1;
        }
        return iC;
    }

    @Override // e4.d
    public final ImageHeaderParser$ImageType d(InputStream inputStream) {
        return ImageHeaderParser$ImageType.UNKNOWN;
    }
}
