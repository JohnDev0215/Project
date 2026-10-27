package defpackage;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: BaseGifImage.java */
/* JADX INFO: loaded from: classes.dex */
public final class od {
    public static final byte[] k = new byte[768];
    public final byte[] a;
    public int b;
    public int c;
    public final int d;
    public boolean e;
    public boolean f;
    public final int[] g = new int[256];
    public int h;
    public int i;
    public int j;

    /* JADX INFO: compiled from: BaseGifImage.java */
    public final class a extends ByteArrayInputStream {
        public a(byte[] bArr) {
            super(bArr);
        }

        public final int getPosition() {
            return ((ByteArrayInputStream) this).pos;
        }
    }

    public od(byte[] bArr) throws IOException {
        this.a = bArr;
        a aVar = new a(bArr);
        aVar.skip(0);
        try {
            a(aVar);
            this.d = aVar.getPosition();
        } catch (IOException unused) {
            this.f = true;
        }
        try {
            aVar.close();
        } catch (IOException unused2) {
        }
    }

    public final void a(a aVar) throws IOException {
        int i = 0;
        if (!(((aVar.read() == 71) && aVar.read() == 73) && aVar.read() == 70)) {
            this.f = true;
            return;
        }
        aVar.skip(3L);
        this.b = aVar.read() | (aVar.read() << 8);
        this.c = aVar.read() | (aVar.read() << 8);
        int i2 = aVar.read();
        this.e = (i2 & 128) != 0;
        this.h = 2 << (i2 & 7);
        this.j = aVar.read();
        aVar.skip(1L);
        if (!this.e || this.f) {
            return;
        }
        int[] iArr = this.g;
        int i3 = this.h;
        byte[] bArr = k;
        synchronized (bArr) {
            int i4 = i3 * 3;
            if (aVar.read(bArr, 0, i4) >= i4) {
                int i5 = 0;
                while (i < i3) {
                    byte[] bArr2 = k;
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    iArr[i] = ((bArr2[i5] & 255) << 16) | (-16777216) | ((bArr2[i6] & 255) << 8) | (bArr2[i7] & 255);
                    i++;
                    i5 = i7 + 1;
                }
            }
        }
        this.i = this.g[this.j];
    }
}
