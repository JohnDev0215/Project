package W1;

import android.media.MediaDataSource;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class a extends MediaDataSource {
    public long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f9671m;

    public a(f fVar) {
        this.f9671m = fVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // android.media.MediaDataSource
    public final long getSize() {
        return -1L;
    }

    @Override // android.media.MediaDataSource
    public final int readAt(long j3, byte[] bArr, int i7, int i10) {
        if (i10 == 0) {
            return 0;
        }
        if (j3 < 0) {
            return -1;
        }
        try {
            long j10 = this.l;
            f fVar = this.f9671m;
            if (j10 != j3) {
                if (j10 >= 0 && j3 >= j10 + ((long) fVar.l.available())) {
                    return -1;
                }
                fVar.g(j3);
                this.l = j3;
            }
            if (i10 > fVar.l.available()) {
                i10 = fVar.l.available();
            }
            int i11 = fVar.read(bArr, i7, i10);
            if (i11 >= 0) {
                this.l += (long) i11;
                return i11;
            }
        } catch (IOException unused) {
        }
        this.l = -1L;
        return -1;
    }
}
