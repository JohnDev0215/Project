package p1;

/* JADX INFO: renamed from: p1.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2082j extends p {
    @Override // p1.InterfaceC2077e
    public final void a(InterfaceC2077e interfaceC2077e) {
        C2079g c2079g = this.f20684h;
        if (c2079g.c && !c2079g.f20667j) {
            c2079g.d((int) ((((C2079g) c2079g.l.get(0)).f20664g * ((o1.h) this.f20680b).f20379r0) + 0.5f));
        }
    }

    @Override // p1.p
    public final void d() {
        o1.d dVar = this.f20680b;
        o1.h hVar = (o1.h) dVar;
        int i7 = hVar.f20380s0;
        int i10 = hVar.f20381t0;
        int i11 = hVar.f20383v0;
        C2079g c2079g = this.f20684h;
        if (i11 == 1) {
            if (i7 != -1) {
                c2079g.l.add(dVar.f20276U.d.f20684h);
                this.f20680b.f20276U.d.f20684h.f20668k.add(c2079g);
                c2079g.f20663f = i7;
            } else if (i10 != -1) {
                c2079g.l.add(dVar.f20276U.d.f20685i);
                this.f20680b.f20276U.d.f20685i.f20668k.add(c2079g);
                c2079g.f20663f = -i10;
            } else {
                c2079g.f20661b = true;
                c2079g.l.add(dVar.f20276U.d.f20685i);
                this.f20680b.f20276U.d.f20685i.f20668k.add(c2079g);
            }
            m(this.f20680b.d.f20684h);
            m(this.f20680b.d.f20685i);
            return;
        }
        if (i7 != -1) {
            c2079g.l.add(dVar.f20276U.f20288e.f20684h);
            this.f20680b.f20276U.f20288e.f20684h.f20668k.add(c2079g);
            c2079g.f20663f = i7;
        } else if (i10 != -1) {
            c2079g.l.add(dVar.f20276U.f20288e.f20685i);
            this.f20680b.f20276U.f20288e.f20685i.f20668k.add(c2079g);
            c2079g.f20663f = -i10;
        } else {
            c2079g.f20661b = true;
            c2079g.l.add(dVar.f20276U.f20288e.f20685i);
            this.f20680b.f20276U.f20288e.f20685i.f20668k.add(c2079g);
        }
        m(this.f20680b.f20288e.f20684h);
        m(this.f20680b.f20288e.f20685i);
    }

    @Override // p1.p
    public final void e() {
        o1.d dVar = this.f20680b;
        int i7 = ((o1.h) dVar).f20383v0;
        C2079g c2079g = this.f20684h;
        if (i7 == 1) {
            dVar.f20281Z = c2079g.f20664g;
        } else {
            dVar.f20283a0 = c2079g.f20664g;
        }
    }

    @Override // p1.p
    public final void f() {
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
