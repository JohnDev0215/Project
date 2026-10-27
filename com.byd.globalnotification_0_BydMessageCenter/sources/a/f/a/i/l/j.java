package a.f.a.i.l;

import a.f.a.i.l.f;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class j extends o {
    public j(a.f.a.i.d dVar) {
        super(dVar);
    }

    @Override // a.f.a.i.l.o, a.f.a.i.l.d
    public void a(d dVar) {
        a.f.a.i.a aVar = (a.f.a.i.a) this.f607b;
        int i = aVar.x0;
        int i2 = 0;
        Iterator<f> it = this.h.l.iterator();
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = it.next().g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i == 0 || i == 2) {
            this.h.c(i3 + aVar.z0);
        } else {
            this.h.c(i2 + aVar.z0);
        }
    }

    @Override // a.f.a.i.l.o
    public void d() {
        o oVar;
        a.f.a.i.d dVar = this.f607b;
        if (dVar instanceof a.f.a.i.a) {
            f fVar = this.h;
            fVar.f583b = true;
            a.f.a.i.a aVar = (a.f.a.i.a) dVar;
            int i = aVar.x0;
            boolean z = aVar.y0;
            int i2 = 0;
            if (i == 0) {
                fVar.f586e = f.a.LEFT;
                while (i2 < aVar.w0) {
                    a.f.a.i.d dVar2 = aVar.v0[i2];
                    if (z || dVar2.k0 != 8) {
                        f fVar2 = dVar2.f558d.h;
                        fVar2.k.add(this.h);
                        this.h.l.add(fVar2);
                    }
                    i2++;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        fVar.f586e = f.a.TOP;
                        while (i2 < aVar.w0) {
                            a.f.a.i.d dVar3 = aVar.v0[i2];
                            if (z || dVar3.k0 != 8) {
                                f fVar3 = dVar3.f559e.h;
                                fVar3.k.add(this.h);
                                this.h.l.add(fVar3);
                            }
                            i2++;
                        }
                    } else {
                        if (i != 3) {
                            return;
                        }
                        fVar.f586e = f.a.BOTTOM;
                        while (i2 < aVar.w0) {
                            a.f.a.i.d dVar4 = aVar.v0[i2];
                            if (z || dVar4.k0 != 8) {
                                f fVar4 = dVar4.f559e.i;
                                fVar4.k.add(this.h);
                                this.h.l.add(fVar4);
                            }
                            i2++;
                        }
                    }
                    m(this.f607b.f559e.h);
                    oVar = this.f607b.f559e;
                    m(oVar.i);
                }
                fVar.f586e = f.a.RIGHT;
                while (i2 < aVar.w0) {
                    a.f.a.i.d dVar5 = aVar.v0[i2];
                    if (z || dVar5.k0 != 8) {
                        f fVar5 = dVar5.f558d.i;
                        fVar5.k.add(this.h);
                        this.h.l.add(fVar5);
                    }
                    i2++;
                }
            }
            m(this.f607b.f558d.h);
            oVar = this.f607b.f558d;
            m(oVar.i);
        }
    }

    @Override // a.f.a.i.l.o
    public void e() {
        a.f.a.i.d dVar = this.f607b;
        if (dVar instanceof a.f.a.i.a) {
            int i = ((a.f.a.i.a) dVar).x0;
            if (i != 0 && i != 1) {
                dVar.d0 = this.h.g;
            } else {
                this.f607b.c0 = this.h.g;
            }
        }
    }

    @Override // a.f.a.i.l.o
    public void f() {
        this.f608c = null;
        this.h.b();
    }

    @Override // a.f.a.i.l.o
    public boolean k() {
        return false;
    }

    public final void m(f fVar) {
        this.h.k.add(fVar);
        fVar.l.add(this.h);
    }
}
