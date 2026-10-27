package g.a.e.a;

import g.a.a.c1;
import g.a.a.h1;
import g.a.a.q;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class k extends g.a.a.l {
    public final byte[] E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5588c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f5589d;
    public final byte[] s;
    public final byte[] x;
    public final byte[] y;

    public k(int i2, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        this.f5588c = i2;
        this.f5589d = g.a.f.a.f(bArr);
        this.s = g.a.f.a.f(bArr2);
        this.x = g.a.f.a.f(bArr3);
        this.y = g.a.f.a.f(bArr4);
        this.E3 = g.a.f.a.f(bArr5);
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(0L));
        g.a.a.f fVar2 = new g.a.a.f();
        fVar2.a(new g.a.a.j(this.f5588c));
        fVar2.a(new y0(this.f5589d));
        fVar2.a(new y0(this.s));
        fVar2.a(new y0(this.x));
        fVar2.a(new y0(this.y));
        fVar.a(new c1(fVar2));
        fVar.a(new h1(true, 0, new y0(this.E3)));
        return new c1(fVar);
    }
}
