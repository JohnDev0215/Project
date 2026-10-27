package g.a.e.c.b;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class a implements KeySpec {
    public g.a.e.b.c.a[] E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public short[][] f5696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public short[] f5697d;
    public short[][] s;
    public short[] x;
    public int[] y;

    public a(short[][] sArr, short[] sArr2, short[][] sArr3, short[] sArr4, int[] iArr, g.a.e.b.c.a[] aVarArr) {
        this.f5696c = sArr;
        this.f5697d = sArr2;
        this.s = sArr3;
        this.x = sArr4;
        this.y = iArr;
        this.E3 = aVarArr;
    }

    public short[] a() {
        return this.f5697d;
    }

    public short[] b() {
        return this.x;
    }

    public short[][] c() {
        return this.f5696c;
    }

    public short[][] d() {
        return this.s;
    }

    public g.a.e.b.c.a[] e() {
        return this.E3;
    }

    public int[] f() {
        return this.y;
    }
}
