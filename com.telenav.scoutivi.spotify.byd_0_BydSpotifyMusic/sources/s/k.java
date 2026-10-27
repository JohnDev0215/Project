package s;

import java.util.Iterator;
import s.f;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
class k extends p {
    public k(r.e eVar) {
        super(eVar);
    }

    private void q(f fVar) {
        this.f9459h.f9417k.add(fVar);
        fVar.f9418l.add(this.f9459h);
    }

    @Override // s.p, s.d
    public void a(d dVar) {
        r.a aVar = (r.a) this.f9453b;
        int iY1 = aVar.y1();
        Iterator<f> it = this.f9459h.f9418l.iterator();
        int i7 = 0;
        int i8 = -1;
        while (it.hasNext()) {
            int i9 = it.next().f9413g;
            if (i8 == -1 || i9 < i8) {
                i8 = i9;
            }
            if (i7 < i9) {
                i7 = i9;
            }
        }
        if (iY1 == 0 || iY1 == 2) {
            this.f9459h.d(i8 + aVar.z1());
        } else {
            this.f9459h.d(i7 + aVar.z1());
        }
    }

    @Override // s.p
    void d() {
        r.e eVar = this.f9453b;
        if (eVar instanceof r.a) {
            this.f9459h.f9408b = true;
            r.a aVar = (r.a) eVar;
            int iY1 = aVar.y1();
            boolean zX1 = aVar.x1();
            int i7 = 0;
            if (iY1 == 0) {
                this.f9459h.f9411e = f.a.LEFT;
                while (i7 < aVar.V0) {
                    r.e eVar2 = aVar.U0[i7];
                    if (zX1 || eVar2.X() != 8) {
                        f fVar = eVar2.f9212e.f9459h;
                        fVar.f9417k.add(this.f9459h);
                        this.f9459h.f9418l.add(fVar);
                    }
                    i7++;
                }
                q(this.f9453b.f9212e.f9459h);
                q(this.f9453b.f9212e.f9460i);
                return;
            }
            if (iY1 == 1) {
                this.f9459h.f9411e = f.a.RIGHT;
                while (i7 < aVar.V0) {
                    r.e eVar3 = aVar.U0[i7];
                    if (zX1 || eVar3.X() != 8) {
                        f fVar2 = eVar3.f9212e.f9460i;
                        fVar2.f9417k.add(this.f9459h);
                        this.f9459h.f9418l.add(fVar2);
                    }
                    i7++;
                }
                q(this.f9453b.f9212e.f9459h);
                q(this.f9453b.f9212e.f9460i);
                return;
            }
            if (iY1 == 2) {
                this.f9459h.f9411e = f.a.TOP;
                while (i7 < aVar.V0) {
                    r.e eVar4 = aVar.U0[i7];
                    if (zX1 || eVar4.X() != 8) {
                        f fVar3 = eVar4.f9214f.f9459h;
                        fVar3.f9417k.add(this.f9459h);
                        this.f9459h.f9418l.add(fVar3);
                    }
                    i7++;
                }
                q(this.f9453b.f9214f.f9459h);
                q(this.f9453b.f9214f.f9460i);
                return;
            }
            if (iY1 != 3) {
                return;
            }
            this.f9459h.f9411e = f.a.BOTTOM;
            while (i7 < aVar.V0) {
                r.e eVar5 = aVar.U0[i7];
                if (zX1 || eVar5.X() != 8) {
                    f fVar4 = eVar5.f9214f.f9460i;
                    fVar4.f9417k.add(this.f9459h);
                    this.f9459h.f9418l.add(fVar4);
                }
                i7++;
            }
            q(this.f9453b.f9214f.f9459h);
            q(this.f9453b.f9214f.f9460i);
        }
    }

    @Override // s.p
    public void e() {
        r.e eVar = this.f9453b;
        if (eVar instanceof r.a) {
            int iY1 = ((r.a) eVar).y1();
            if (iY1 == 0 || iY1 == 1) {
                this.f9453b.p1(this.f9459h.f9413g);
            } else {
                this.f9453b.q1(this.f9459h.f9413g);
            }
        }
    }

    @Override // s.p
    void f() {
        this.f9454c = null;
        this.f9459h.c();
    }

    @Override // s.p
    boolean m() {
        return false;
    }
}
