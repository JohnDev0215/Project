package W1;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class f extends b {
    public f(byte[] bArr) {
        super(bArr);
        this.l.mark(Integer.MAX_VALUE);
    }

    public final void g(long j3) throws IOException {
        int i7 = this.f9672m;
        if (i7 > j3) {
            this.f9672m = 0;
            this.l.reset();
        } else {
            j3 -= (long) i7;
        }
        b((int) j3);
    }

    public f(InputStream inputStream) {
        super(inputStream);
        if (inputStream.markSupported()) {
            this.l.mark(Integer.MAX_VALUE);
            return;
        }
        throw new IllegalArgumentException("Cannot create SeekableByteOrderedDataInputStream with stream that does not support mark/reset");
    }
}
