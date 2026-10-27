package p1;

import java.util.Iterator;

/* JADX INFO: renamed from: p1.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2083k extends p {
    @Override // p1.InterfaceC2077e
    public final void a(InterfaceC2077e interfaceC2077e) {
        o1.a aVar = (o1.a) this.f20680b;
        int i7 = aVar.f20233t0;
        C2079g c2079g = this.f20684h;
        Iterator it = c2079g.l.iterator();
        int i10 = 0;
        int i11 = -1;
        while (it.hasNext()) {
            int i12 = ((C2079g) it.next()).f20664g;
            if (i11 == -1 || i12 < i11) {
                i11 = i12;
            }
            if (i10 < i12) {
                i10 = i12;
            }
        }
        if (i7 == 0 || i7 == 2) {
            c2079g.d(i11 + aVar.f20235v0);
        } else {
            c2079g.d(i10 + aVar.f20235v0);
        }
    }

    @Override // p1.p
    public final void d() {
        o1.d dVar = this.f20680b;
        if (dVar instanceof o1.a) {
            C2079g c2079g = this.f20684h;
            c2079g.f20661b = true;
            o1.a aVar = (o1.a) dVar;
            int i7 = aVar.f20233t0;
            boolean z6 = aVar.f20234u0;
            int i10 = 0;
            if (i7 == 0) {
                c2079g.f20662e = 4;
                while (i10 < aVar.f20386s0) {
                    o1.d dVar2 = aVar.f20385r0[i10];
                    if (z6 || dVar2.f20295h0 != 8) {
                        C2079g c2079g2 = dVar2.d.f20684h;
                        c2079g2.f20668k.add(c2079g);
                        c2079g.l.add(c2079g2);
                    }
                    i10++;
                }
                m(this.f20680b.d.f20684h);
                m(this.f20680b.d.f20685i);
                return;
            }
            if (i7 == 1) {
                c2079g.f20662e = 5;
                while (i10 < aVar.f20386s0) {
                    o1.d dVar3 = aVar.f20385r0[i10];
                    if (z6 || dVar3.f20295h0 != 8) {
                        C2079g c2079g3 = dVar3.d.f20685i;
                        c2079g3.f20668k.add(c2079g);
                        c2079g.l.add(c2079g3);
                    }
                    i10++;
                }
                m(this.f20680b.d.f20684h);
                m(this.f20680b.d.f20685i);
                return;
            }
            if (i7 == 2) {
                c2079g.f20662e = 6;
                while (i10 < aVar.f20386s0) {
                    o1.d dVar4 = aVar.f20385r0[i10];
                    if (z6 || dVar4.f20295h0 != 8) {
                        C2079g c2079g4 = dVar4.f20288e.f20684h;
                        c2079g4.f20668k.add(c2079g);
                        c2079g.l.add(c2079g4);
                    }
                    i10++;
                }
                m(this.f20680b.f20288e.f20684h);
                m(this.f20680b.f20288e.f20685i);
                return;
            }
            if (i7 != 3) {
                return;
            }
            c2079g.f20662e = 7;
            while (i10 < aVar.f20386s0) {
                o1.d dVar5 = aVar.f20385r0[i10];
                if (z6 || dVar5.f20295h0 != 8) {
                    C2079g c2079g5 = dVar5.f20288e.f20685i;
                    c2079g5.f20668k.add(c2079g);
                    c2079g.l.add(c2079g5);
                }
                i10++;
            }
            m(this.f20680b.f20288e.f20684h);
            m(this.f20680b.f20288e.f20685i);
        }
    }

    @Override // p1.p
    public final void e() {
        o1.d dVar = this.f20680b;
        if (dVar instanceof o1.a) {
            int i7 = ((o1.a) dVar).f20233t0;
            C2079g c2079g = this.f20684h;
            if (i7 == 0 || i7 == 1) {
                dVar.f20281Z = c2079g.f20664g;
            } else {
                dVar.f20283a0 = c2079g.f20664g;
            }
        }
    }

    @Override // p1.p
    public final void f() {
        this.c = null;
        this.f20684h.c();
    }

    @Override // p1.p
    public final boolean k() {
        return false;
    }

    public final void m(C2079g c2079g) {
        C2079g c2079g2 = this.f20684h;
        c2079g2.f20668k.add(c2079g);
        c2079g.l.add(c2079g2);
    }
}
