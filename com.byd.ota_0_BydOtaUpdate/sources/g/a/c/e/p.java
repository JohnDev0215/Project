package g.a.c.e;

import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class p implements AlgorithmParameterSpec {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f5264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public byte[] f5265b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5268e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5269f;

    public p(byte[] bArr, byte[] bArr2, int i2) {
        this(bArr, bArr2, i2, -1, null, false);
    }

    public p(byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3) {
        this(bArr, bArr2, i2, i3, bArr3, false);
    }

    public p(byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3, boolean z) {
        if (bArr != null) {
            byte[] bArr4 = new byte[bArr.length];
            this.f5264a = bArr4;
            System.arraycopy(bArr, 0, bArr4, 0, bArr.length);
        } else {
            this.f5264a = null;
        }
        if (bArr2 != null) {
            byte[] bArr5 = new byte[bArr2.length];
            this.f5265b = bArr5;
            System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        } else {
            this.f5265b = null;
        }
        this.f5266c = i2;
        this.f5267d = i3;
        this.f5268e = g.a.f.a.f(bArr3);
        this.f5269f = z;
    }

    public int a() {
        return this.f5267d;
    }

    public byte[] b() {
        return g.a.f.a.f(this.f5264a);
    }

    public byte[] c() {
        return g.a.f.a.f(this.f5265b);
    }

    public int d() {
        return this.f5266c;
    }

    public byte[] e() {
        return g.a.f.a.f(this.f5268e);
    }

    public boolean f() {
        return this.f5269f;
    }
}
