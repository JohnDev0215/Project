package p;

import android.media.MediaDataSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class al0 extends MediaDataSource {
    public long c;
    public final /* synthetic */ fl0 e;

    public al0(fl0 fl0Var) {
        this.e = fl0Var;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return -1L;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (j < 0) {
            return -1;
        }
        try {
            long j2 = this.c;
            fl0 fl0Var = this.e;
            if (j2 != j) {
                if (j2 >= 0 && j >= j2 + ((long) fl0Var.c.available())) {
                    return -1;
                }
                fl0Var.c(j);
                this.c = j;
            }
            if (i2 > fl0Var.c.available()) {
                i2 = fl0Var.c.available();
            }
            int i3 = fl0Var.read(bArr, i, i2);
            if (i3 >= 0) {
                this.c += (long) i3;
                return i3;
            }
        } catch (IOException unused) {
        }
        this.c = -1L;
        return -1;
    }
}
