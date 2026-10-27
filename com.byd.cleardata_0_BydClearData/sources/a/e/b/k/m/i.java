package a.e.b.k.m;

import a.e.b.k.m.f;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class i extends m {
    public i(a.e.b.k.e eVar) {
        super(eVar);
    }

    private void q(f fVar) {
        this.h.k.add(fVar);
        fVar.l.add(this.h);
    }

    @Override // a.e.b.k.m.m, a.e.b.k.m.d
    public void a(d dVar) {
        a.e.b.k.a aVar = (a.e.b.k.a) this.f248b;
        int iI0 = aVar.I0();
        Iterator<f> it = this.h.l.iterator();
        int i = 0;
        int i2 = -1;
        while (it.hasNext()) {
            int i3 = it.next().g;
            if (i2 == -1 || i3 < i2) {
                i2 = i3;
            }
            if (i < i3) {
                i = i3;
            }
        }
        if (iI0 == 0 || iI0 == 2) {
            this.h.d(i2 + aVar.J0());
        } else {
            this.h.d(i + aVar.J0());
        }
    }

    @Override // a.e.b.k.m.m
    void d() {
        m mVar;
        a.e.b.k.e eVar = this.f248b;
        if (eVar instanceof a.e.b.k.a) {
            this.h.f235b = true;
            a.e.b.k.a aVar = (a.e.b.k.a) eVar;
            int iI0 = aVar.I0();
            boolean zH0 = aVar.H0();
            int i = 0;
            if (iI0 == 0) {
                this.h.e = f.a.LEFT;
                while (i < aVar.x0) {
                    a.e.b.k.e eVar2 = aVar.w0[i];
                    if (zH0 || eVar2.M() != 8) {
                        f fVar = eVar2.f.h;
                        fVar.k.add(this.h);
                        this.h.l.add(fVar);
                    }
                    i++;
                }
            } else {
                if (iI0 != 1) {
                    if (iI0 == 2) {
                        this.h.e = f.a.TOP;
                        while (i < aVar.x0) {
                            a.e.b.k.e eVar3 = aVar.w0[i];
                            if (zH0 || eVar3.M() != 8) {
                                f fVar2 = eVar3.g.h;
                                fVar2.k.add(this.h);
                                this.h.l.add(fVar2);
                            }
                            i++;
                        }
                    } else {
                        if (iI0 != 3) {
                            return;
                        }
                        this.h.e = f.a.BOTTOM;
                        while (i < aVar.x0) {
                            a.e.b.k.e eVar4 = aVar.w0[i];
                            if (zH0 || eVar4.M() != 8) {
                                f fVar3 = eVar4.g.i;
                                fVar3.k.add(this.h);
                                this.h.l.add(fVar3);
                            }
                            i++;
                        }
                    }
                    q(this.f248b.g.h);
                    mVar = this.f248b.g;
                    q(mVar.i);
                }
                this.h.e = f.a.RIGHT;
                while (i < aVar.x0) {
                    a.e.b.k.e eVar5 = aVar.w0[i];
                    if (zH0 || eVar5.M() != 8) {
                        f fVar4 = eVar5.f.i;
                        fVar4.k.add(this.h);
                        this.h.l.add(fVar4);
                    }
                    i++;
                }
            }
            q(this.f248b.f.h);
            mVar = this.f248b.f;
            q(mVar.i);
        }
    }

    @Override // a.e.b.k.m.m
    public void e() {
        a.e.b.k.e eVar = this.f248b;
        if (eVar instanceof a.e.b.k.a) {
            int iI0 = ((a.e.b.k.a) eVar).I0();
            if (iI0 == 0 || iI0 == 1) {
                this.f248b.C0(this.h.g);
            } else {
                this.f248b.D0(this.h.g);
            }
        }
    }

    @Override // a.e.b.k.m.m
    void f() {
        this.f249c = null;
        this.h.c();
    }

    @Override // a.e.b.k.m.m
    boolean m() {
        return false;
    }
}
