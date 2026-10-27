package m8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.u;

/* JADX INFO: loaded from: classes.dex */
public final class d extends AbstractC2134i implements u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19284m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b f19285n;
    public c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public c f19286p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public c f19287q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public c f19288r;

    public static d g() {
        d dVar = new d();
        dVar.f19285n = b.f19272r;
        c cVar = c.f19278r;
        dVar.o = cVar;
        dVar.f19286p = cVar;
        dVar.f19287q = cVar;
        dVar.f19288r = cVar;
        return dVar;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        e eVarF = f();
        if (eVarF.b()) {
            return eVarF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        d dVarG = g();
        dVarG.h(f());
        return dVarG;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r3, p8.C2132g r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            j8.a r1 = m8.e.f19290v     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            m8.e r1 = new m8.e     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            m8.e r4 = (m8.e) r4     // Catch: java.lang.Throwable -> Lf
            throw r3     // Catch: java.lang.Throwable -> L17
        L17:
            r3 = move-exception
            r0 = r4
        L19:
            if (r0 == 0) goto L1e
            r2.h(r0)
        L1e:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: m8.d.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((e) abstractC2139n);
        return this;
    }

    public final e f() {
        e eVar = new e(this);
        int i7 = this.f19284m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        eVar.f19292n = this.f19285n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        eVar.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        eVar.f19293p = this.f19286p;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        eVar.f19294q = this.f19287q;
        if ((i7 & 16) == 16) {
            i10 |= 16;
        }
        eVar.f19295r = this.f19288r;
        eVar.f19291m = i10;
        return eVar;
    }

    public final void h(e eVar) {
        c cVar;
        c cVar2;
        c cVar3;
        c cVar4;
        b bVar;
        if (eVar == e.f19289u) {
            return;
        }
        if ((eVar.f19291m & 1) == 1) {
            b bVar2 = eVar.f19292n;
            if ((this.f19284m & 1) != 1 || (bVar = this.f19285n) == b.f19272r) {
                this.f19285n = bVar2;
            } else {
                a aVar = new a(0);
                aVar.h(bVar);
                aVar.h(bVar2);
                this.f19285n = aVar.f();
            }
            this.f19284m |= 1;
        }
        if ((eVar.f19291m & 2) == 2) {
            c cVar5 = eVar.o;
            if ((this.f19284m & 2) != 2 || (cVar4 = this.o) == c.f19278r) {
                this.o = cVar5;
            } else {
                a aVarI = c.i(cVar4);
                aVarI.i(cVar5);
                this.o = aVarI.g();
            }
            this.f19284m |= 2;
        }
        if ((eVar.f19291m & 4) == 4) {
            c cVar6 = eVar.f19293p;
            if ((this.f19284m & 4) != 4 || (cVar3 = this.f19286p) == c.f19278r) {
                this.f19286p = cVar6;
            } else {
                a aVarI2 = c.i(cVar3);
                aVarI2.i(cVar6);
                this.f19286p = aVarI2.g();
            }
            this.f19284m |= 4;
        }
        if ((eVar.f19291m & 8) == 8) {
            c cVar7 = eVar.f19294q;
            if ((this.f19284m & 8) != 8 || (cVar2 = this.f19287q) == c.f19278r) {
                this.f19287q = cVar7;
            } else {
                a aVarI3 = c.i(cVar2);
                aVarI3.i(cVar7);
                this.f19287q = aVarI3.g();
            }
            this.f19284m |= 8;
        }
        if ((eVar.f19291m & 16) == 16) {
            c cVar8 = eVar.f19295r;
            if ((this.f19284m & 16) != 16 || (cVar = this.f19288r) == c.f19278r) {
                this.f19288r = cVar8;
            } else {
                a aVarI4 = c.i(cVar);
                aVarI4.i(cVar8);
                this.f19288r = aVarI4.g();
            }
            this.f19284m |= 16;
        }
        this.l = this.l.d(eVar.l);
    }
}
