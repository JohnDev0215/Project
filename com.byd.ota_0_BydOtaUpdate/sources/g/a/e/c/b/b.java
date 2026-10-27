package g.a.e.c.b;

import java.security.spec.KeySpec;

/* JADX INFO: loaded from: classes.dex */
public class b implements KeySpec {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public short[][] f5698c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public short[][] f5699d;
    public short[] s;
    public int x;

    public b(int i2, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.x = i2;
        this.f5698c = sArr;
        this.f5699d = sArr2;
        this.s = sArr3;
    }

    public short[][] a() {
        return this.f5698c;
    }

    public short[] b() {
        return this.s;
    }

    public short[][] c() {
        return this.f5699d;
    }

    public int d() {
        return this.x;
    }
}
