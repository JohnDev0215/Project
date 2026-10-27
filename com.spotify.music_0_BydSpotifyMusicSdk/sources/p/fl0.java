package p;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class fl0 extends bl0 {
    public fl0(byte[] bArr) {
        super(bArr);
        this.c.mark(Integer.MAX_VALUE);
    }

    public final void c(long j) throws IOException {
        int i = this.k;
        if (i > j) {
            this.k = 0;
            this.c.reset();
        } else {
            j -= (long) i;
        }
        b((int) j);
    }

    public fl0(InputStream inputStream) {
        super(inputStream);
        if (inputStream.markSupported()) {
            this.c.mark(Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
    }
}
