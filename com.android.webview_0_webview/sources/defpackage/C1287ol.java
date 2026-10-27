package defpackage;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: ol, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1287ol extends AbstractC1177ml {
    public final OutputStream h;

    public C1287ol(OutputStream outputStream, int i) {
        super(i);
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        this.h = outputStream;
    }

    @Override // defpackage.AbstractC1342pl
    public void F(byte b) {
        if (this.f == this.e) {
            Z();
        }
        byte[] bArr = this.d;
        int i = this.f;
        this.f = i + 1;
        bArr[i] = b;
        this.g++;
    }

    @Override // defpackage.AbstractC1342pl
    public void G(int i, boolean z) {
        a0(11);
        X((i << 3) | 0);
        byte b = z ? (byte) 1 : (byte) 0;
        byte[] bArr = this.d;
        int i2 = this.f;
        this.f = i2 + 1;
        bArr[i2] = b;
        this.g++;
    }

    @Override // defpackage.AbstractC1342pl
    public void H(int i, AbstractC0731el abstractC0731el) throws IOException {
        S((i << 3) | 2);
        c0(abstractC0731el);
    }

    @Override // defpackage.AbstractC1342pl
    public void I(int i, int i2) {
        a0(14);
        X((i << 3) | 5);
        V(i2);
    }

    @Override // defpackage.AbstractC1342pl
    public void J(int i) {
        a0(4);
        V(i);
    }

    @Override // defpackage.AbstractC1342pl
    public void K(int i, long j) {
        a0(18);
        X((i << 3) | 1);
        W(j);
    }

    @Override // defpackage.AbstractC1342pl
    public void L(long j) {
        a0(8);
        W(j);
    }

    @Override // defpackage.AbstractC1342pl
    public void M(int i, int i2) {
        a0(20);
        X((i << 3) | 0);
        if (i2 >= 0) {
            X(i2);
        } else {
            Y(i2);
        }
    }

    @Override // defpackage.AbstractC1342pl
    public void N(int i) {
        if (i < 0) {
            U(i);
        } else {
            a0(5);
            X(i);
        }
    }

    @Override // defpackage.AbstractC1342pl
    public void O(int i, InterfaceC0509am interfaceC0509am, InterfaceC1178mm interfaceC1178mm) {
        S((i << 3) | 2);
        S(((AbstractC0315Rk) interfaceC0509am).a(interfaceC1178mm));
        interfaceC1178mm.g(interfaceC0509am, this.a);
    }

    @Override // defpackage.AbstractC1342pl
    public void P(int i, String str) throws IOException {
        S((i << 3) | 2);
        d0(str);
    }

    @Override // defpackage.AbstractC1342pl
    public void Q(int i, int i2) {
        S((i << 3) | i2);
    }

    @Override // defpackage.AbstractC1342pl
    public void R(int i, int i2) {
        a0(20);
        X((i << 3) | 0);
        X(i2);
    }

    @Override // defpackage.AbstractC1342pl
    public void S(int i) {
        a0(5);
        X(i);
    }

    @Override // defpackage.AbstractC1342pl
    public void T(int i, long j) {
        a0(20);
        X((i << 3) | 0);
        Y(j);
    }

    @Override // defpackage.AbstractC1342pl
    public void U(long j) {
        a0(10);
        Y(j);
    }

    public final void Z() {
        this.h.write(this.d, 0, this.f);
        this.f = 0;
    }

    @Override // defpackage.AbstractC0387Vk
    public void a(byte[] bArr, int i, int i2) throws IOException {
        b0(bArr, i, i2);
    }

    public final void a0(int i) {
        if (this.e - this.f < i) {
            Z();
        }
    }

    public void b0(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.e;
        int i4 = this.f;
        if (i3 - i4 >= i2) {
            System.arraycopy(bArr, i, this.d, i4, i2);
            this.f += i2;
            this.g += i2;
            return;
        }
        int i5 = i3 - i4;
        System.arraycopy(bArr, i, this.d, i4, i5);
        int i6 = i + i5;
        int i7 = i2 - i5;
        this.f = this.e;
        this.g += i5;
        Z();
        if (i7 <= this.e) {
            System.arraycopy(bArr, i6, this.d, 0, i7);
            this.f = i7;
        } else {
            this.h.write(bArr, i6, i7);
        }
        this.g += i7;
    }

    public void c0(AbstractC0731el abstractC0731el) throws IOException {
        S(abstractC0731el.size());
        C0620cl c0620cl = (C0620cl) abstractC0731el;
        b0(c0620cl.v, c0620cl.o(), c0620cl.size());
    }

    public void d0(String str) throws IOException {
        int iE;
        try {
            int length = str.length() * 3;
            int iZ = AbstractC1342pl.z(length);
            int i = iZ + length;
            if (i > this.e) {
                byte[] bArr = new byte[length];
                int iD = AbstractC0174Jm.d(str, bArr, 0, length);
                S(iD);
                b0(bArr, 0, iD);
                return;
            }
            if (i > this.e - this.f) {
                Z();
            }
            int iZ2 = AbstractC1342pl.z(str.length());
            int i2 = this.f;
            try {
                if (iZ2 == iZ) {
                    int i3 = i2 + iZ2;
                    this.f = i3;
                    int iD2 = AbstractC0174Jm.d(str, this.d, i3, this.e - i3);
                    this.f = i2;
                    iE = (iD2 - i2) - iZ2;
                    X(iE);
                    this.f = iD2;
                } else {
                    iE = AbstractC0174Jm.e(str);
                    X(iE);
                    this.f = AbstractC0174Jm.d(str, this.d, this.f, iE);
                }
                this.g += iE;
            } catch (C0138Hm e) {
                this.g -= this.f - i2;
                this.f = i2;
                throw e;
            } catch (ArrayIndexOutOfBoundsException e2) {
                throw new C1232nl(e2);
            }
        } catch (C0138Hm e3) {
            E(str, e3);
        }
    }
}
