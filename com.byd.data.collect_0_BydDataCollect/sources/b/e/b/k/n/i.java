package b.e.b.k.n;

import b.e.b.k.n.f;
import java.util.Iterator;

/* JADX INFO: compiled from: HelperReferences.java */
/* JADX INFO: loaded from: classes.dex */
class i extends m {
    public i(b.e.b.k.e eVar) {
        super(eVar);
    }

    private void q(f fVar) {
        this.f2520h.k.add(fVar);
        fVar.l.add(this.f2520h);
    }

    @Override // b.e.b.k.n.m, b.e.b.k.n.d
    public void a(d dVar) {
        b.e.b.k.a aVar = (b.e.b.k.a) this.f2514b;
        int iM0 = aVar.M0();
        Iterator<f> it = this.f2520h.l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = it.next().f2492g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (iM0 == 0 || iM0 == 2) {
            this.f2520h.d(i3 + aVar.N0());
        } else {
            this.f2520h.d(i2 + aVar.N0());
        }
    }

    @Override // b.e.b.k.n.m
    void d() {
        b.e.b.k.e eVar = this.f2514b;
        if (eVar instanceof b.e.b.k.a) {
            this.f2520h.f2487b = true;
            b.e.b.k.a aVar = (b.e.b.k.a) eVar;
            int iM0 = aVar.M0();
            boolean zL0 = aVar.L0();
            int i2 = 0;
            if (iM0 == 0) {
                this.f2520h.f2490e = f.a.LEFT;
                while (i2 < aVar.H0) {
                    b.e.b.k.e eVar2 = aVar.G0[i2];
                    if (zL0 || eVar2.P() != 8) {
                        f fVar = eVar2.f2443f.f2520h;
                        fVar.k.add(this.f2520h);
                        this.f2520h.l.add(fVar);
                    }
                    i2++;
                }
                q(this.f2514b.f2443f.f2520h);
                q(this.f2514b.f2443f.f2521i);
                return;
            }
            if (iM0 == 1) {
                this.f2520h.f2490e = f.a.RIGHT;
                while (i2 < aVar.H0) {
                    b.e.b.k.e eVar3 = aVar.G0[i2];
                    if (zL0 || eVar3.P() != 8) {
                        f fVar2 = eVar3.f2443f.f2521i;
                        fVar2.k.add(this.f2520h);
                        this.f2520h.l.add(fVar2);
                    }
                    i2++;
                }
                q(this.f2514b.f2443f.f2520h);
                q(this.f2514b.f2443f.f2521i);
                return;
            }
            if (iM0 == 2) {
                this.f2520h.f2490e = f.a.TOP;
                while (i2 < aVar.H0) {
                    b.e.b.k.e eVar4 = aVar.G0[i2];
                    if (zL0 || eVar4.P() != 8) {
                        f fVar3 = eVar4.f2444g.f2520h;
                        fVar3.k.add(this.f2520h);
                        this.f2520h.l.add(fVar3);
                    }
                    i2++;
                }
                q(this.f2514b.f2444g.f2520h);
                q(this.f2514b.f2444g.f2521i);
                return;
            }
            if (iM0 != 3) {
                return;
            }
            this.f2520h.f2490e = f.a.BOTTOM;
            while (i2 < aVar.H0) {
                b.e.b.k.e eVar5 = aVar.G0[i2];
                if (zL0 || eVar5.P() != 8) {
                    f fVar4 = eVar5.f2444g.f2521i;
                    fVar4.k.add(this.f2520h);
                    this.f2520h.l.add(fVar4);
                }
                i2++;
            }
            q(this.f2514b.f2444g.f2520h);
            q(this.f2514b.f2444g.f2521i);
        }
    }

    @Override // b.e.b.k.n.m
    public void e() {
        b.e.b.k.e eVar = this.f2514b;
        if (eVar instanceof b.e.b.k.a) {
            int iM0 = ((b.e.b.k.a) eVar).M0();
            if (iM0 == 0 || iM0 == 1) {
                this.f2514b.G0(this.f2520h.f2492g);
            } else {
                this.f2514b.H0(this.f2520h.f2492g);
            }
        }
    }

    @Override // b.e.b.k.n.m
    void f() {
        this.f2515c = null;
        this.f2520h.c();
    }

    @Override // b.e.b.k.n.m
    boolean m() {
        return false;
    }
}
