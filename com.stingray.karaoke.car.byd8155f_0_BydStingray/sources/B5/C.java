package B5;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class C extends InputStream {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f645m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ D f646n;

    public C(D d, B b10) {
        this.f646n = d;
        this.l = d.v(b10.f643a + 4);
        this.f645m = b10.f644b;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i7, int i10) {
        if ((i7 | i10) < 0 || i10 > bArr.length - i7) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i11 = this.f645m;
        if (i11 == 0) {
            return -1;
        }
        if (i10 > i11) {
            i10 = i11;
        }
        int i12 = this.l;
        D d = this.f646n;
        d.o(i12, i7, i10, bArr);
        this.l = d.v(this.l + i10);
        this.f645m -= i10;
        return i10;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.f645m == 0) {
            return -1;
        }
        D d = this.f646n;
        d.l.seek(this.l);
        int i7 = d.l.read();
        this.l = d.v(this.l + 1);
        this.f645m--;
        return i7;
    }
}
