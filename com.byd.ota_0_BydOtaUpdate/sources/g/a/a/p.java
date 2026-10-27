package g.a.a;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public OutputStream f3949a;

    public class a extends p {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3950b;

        public a(OutputStream outputStream) {
            super(outputStream);
            this.f3950b = true;
        }

        @Override // g.a.a.p
        public void c(int i2) throws IOException {
            if (this.f3950b) {
                this.f3950b = false;
            } else {
                super.c(i2);
            }
        }
    }

    public p(OutputStream outputStream) {
        this.f3949a = outputStream;
    }

    public p a() {
        return new a1(this.f3949a);
    }

    public p b() {
        return new n1(this.f3949a);
    }

    public void c(int i2) throws IOException {
        this.f3949a.write(i2);
    }

    public void d(byte[] bArr) throws IOException {
        this.f3949a.write(bArr);
    }

    public void e(byte[] bArr, int i2, int i3) throws IOException {
        this.f3949a.write(bArr, i2, i3);
    }

    public void f(int i2, int i3, byte[] bArr) throws IOException {
        k(i2, i3);
        i(bArr.length);
        d(bArr);
    }

    public void g(int i2, byte[] bArr) throws IOException {
        c(i2);
        i(bArr.length);
        d(bArr);
    }

    public void h(q qVar) throws IOException {
        if (qVar == null) {
            throw new IOException("null object detected");
        }
        qVar.h(new a(this.f3949a));
    }

    public void i(int i2) throws IOException {
        if (i2 <= 127) {
            c((byte) i2);
            return;
        }
        int i3 = i2;
        int i4 = 1;
        while (true) {
            i3 >>>= 8;
            if (i3 == 0) {
                break;
            } else {
                i4++;
            }
        }
        c((byte) (i4 | 128));
        for (int i5 = (i4 - 1) * 8; i5 >= 0; i5 -= 8) {
            c((byte) (i2 >> i5));
        }
    }

    public void j(e eVar) throws IOException {
        if (eVar == null) {
            throw new IOException("null object detected");
        }
        eVar.c().h(this);
    }

    public void k(int i2, int i3) throws IOException {
        if (i3 < 31) {
            c(i2 | i3);
            return;
        }
        c(i2 | 31);
        if (i3 < 128) {
            c(i3);
            return;
        }
        byte[] bArr = new byte[5];
        int i4 = 4;
        bArr[4] = (byte) (i3 & 127);
        do {
            i3 >>= 7;
            i4--;
            bArr[i4] = (byte) ((i3 & 127) | 128);
        } while (i3 > 127);
        e(bArr, i4, 5 - i4);
    }
}
