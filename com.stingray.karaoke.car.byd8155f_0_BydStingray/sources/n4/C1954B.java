package n4;

import android.media.MediaDataSource;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: n4.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1954B extends MediaDataSource {
    public final /* synthetic */ ByteBuffer l;

    public C1954B(ByteBuffer byteBuffer) {
        this.l = byteBuffer;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return this.l.limit();
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j3, byte[] bArr, int i7, int i10) {
        ByteBuffer byteBuffer = this.l;
        if (j3 >= byteBuffer.limit()) {
            return -1;
        }
        byteBuffer.position((int) j3);
        int iMin = Math.min(i10, byteBuffer.remaining());
        byteBuffer.get(bArr, i7, iMin);
        return iMin;
    }
}
