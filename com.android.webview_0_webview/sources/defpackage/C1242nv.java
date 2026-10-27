package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: nv, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1242nv {
    public static final C1242nv g;
    public static final C1242nv h;
    public static final C1242nv i;
    public static final C1242nv j;
    public static final C1242nv k;
    public static final C1242nv l;
    public int a;
    public boolean b;
    public boolean c;
    public byte d;
    public byte[] e;
    public boolean f;

    static {
        C1242nv c1242nvB = new C1242nv().b(0);
        g = c1242nvB;
        h = c1242nvB.a();
        C1242nv c1242nvB2 = new C1242nv().b(1);
        i = c1242nvB2;
        c1242nvB2.a();
        C1242nv c1242nvB3 = new C1242nv().b(2);
        j = c1242nvB3;
        c1242nvB3.a();
        C1242nv c1242nv = new C1242nv();
        k = c1242nv;
        c1242nv.f = true;
        C1242nv c1242nvB4 = new C1242nv().c().b(2);
        l = c1242nvB4;
        c1242nvB4.b(2);
        l.b(1);
        l.b(0);
    }

    public C1242nv() {
        this.a = 2;
    }

    public C1242nv a() {
        C1242nv c1242nv = new C1242nv(this);
        c1242nv.b = true;
        return c1242nv;
    }

    public C1242nv b(int i2) {
        C1242nv c1242nv = new C1242nv(this);
        c1242nv.a = i2;
        return c1242nv;
    }

    public C1242nv c() {
        C1242nv c1242nv = new C1242nv(this);
        c1242nv.c = true;
        return c1242nv;
    }

    public C1242nv d() {
        if (!this.c) {
            if (!(this.d != 0)) {
                C1242nv c1242nv = new C1242nv(this);
                c1242nv.c = true;
                return c1242nv;
            }
        }
        return this;
    }

    public C1242nv e(InterfaceC1297ov interfaceC1297ov, Object obj) {
        Objects.requireNonNull((Sz) interfaceC1297ov);
        byte[] bArr = new byte[8];
        bArr[2] = 1;
        bArr[1] = (byte) ((Tz) obj).a;
        C1242nv c1242nv = new C1242nv(this);
        c1242nv.d = (byte) 1;
        c1242nv.e = bArr;
        return c1242nv;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C1242nv)) {
            return false;
        }
        C1242nv c1242nv = (C1242nv) obj;
        return this.a == c1242nv.a && this.b == c1242nv.b && this.c == c1242nv.c && this.d == c1242nv.d && Arrays.equals(this.e, c1242nv.e) && this.f == c1242nv.f;
    }

    public int hashCode() {
        return ((Arrays.hashCode(this.e) + ((((((((1147 + this.a) * 37) + (!this.b ? 1 : 0)) * 37) + (!this.c ? 1 : 0)) * 37) + this.d) * 37)) * 37) + (!this.f ? 1 : 0);
    }

    public C1242nv(C1242nv c1242nv) {
        this.a = c1242nv.a;
        this.b = c1242nv.b;
        this.c = c1242nv.c;
        this.d = c1242nv.d;
        this.e = c1242nv.e;
    }
}
