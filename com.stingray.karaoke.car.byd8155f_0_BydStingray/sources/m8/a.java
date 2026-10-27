package m8;

import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.u;

/* JADX INFO: loaded from: classes.dex */
public final class a extends AbstractC2134i implements u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19269m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19270n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f19271p;

    public /* synthetic */ a(int i7) {
        this.f19269m = i7;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        switch (this.f19269m) {
            case 0:
                b bVarF = f();
                if (bVarF.b()) {
                    return bVarF;
                }
                throw new A9.k();
            default:
                c cVarG = g();
                if (cVarG.b()) {
                    return cVarG;
                }
                throw new A9.k();
        }
    }

    public final Object clone() {
        switch (this.f19269m) {
            case 0:
                a aVar = new a(0);
                aVar.h(f());
                return aVar;
            default:
                a aVar2 = new a(1);
                aVar2.i(g());
                return aVar2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0020  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003f  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r2, p8.C2132g r3) throws java.lang.Throwable {
        /*
            r1 = this;
            int r3 = r1.f19269m
            switch(r3) {
                case 0: goto L24;
                default: goto L5;
            }
        L5:
            r3 = 0
            j8.a r0 = m8.c.f19279s     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r0.getClass()     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            m8.c r0 = new m8.c     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L14 p8.C2142q -> L16
            r1.i(r0)
            return r1
        L14:
            r2 = move-exception
            goto L1e
        L16:
            r2 = move-exception
            p8.a r0 = r2.l     // Catch: java.lang.Throwable -> L14
            m8.c r0 = (m8.c) r0     // Catch: java.lang.Throwable -> L14
            throw r2     // Catch: java.lang.Throwable -> L1c
        L1c:
            r2 = move-exception
            r3 = r0
        L1e:
            if (r3 == 0) goto L23
            r1.i(r3)
        L23:
            throw r2
        L24:
            r3 = 0
            j8.a r0 = m8.b.f19273s     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r0.getClass()     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            m8.b r0 = new m8.b     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L33 p8.C2142q -> L35
            r1.h(r0)
            return r1
        L33:
            r2 = move-exception
            goto L3d
        L35:
            r2 = move-exception
            p8.a r0 = r2.l     // Catch: java.lang.Throwable -> L33
            m8.b r0 = (m8.b) r0     // Catch: java.lang.Throwable -> L33
            throw r2     // Catch: java.lang.Throwable -> L3b
        L3b:
            r2 = move-exception
            r3 = r0
        L3d:
            if (r3 == 0) goto L42
            r1.h(r3)
        L42:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: m8.a.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        switch (this.f19269m) {
            case 0:
                h((b) abstractC2139n);
                break;
            default:
                i((c) abstractC2139n);
                break;
        }
        return this;
    }

    public b f() {
        b bVar = new b(this);
        int i7 = this.f19270n;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        bVar.f19275n = this.o;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        bVar.o = this.f19271p;
        bVar.f19274m = i10;
        return bVar;
    }

    public c g() {
        c cVar = new c(this);
        int i7 = this.f19270n;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        cVar.f19281n = this.o;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        cVar.o = this.f19271p;
        cVar.f19280m = i10;
        return cVar;
    }

    public void h(b bVar) {
        if (bVar == b.f19272r) {
            return;
        }
        int i7 = bVar.f19274m;
        if ((i7 & 1) == 1) {
            int i10 = bVar.f19275n;
            this.f19270n = 1 | this.f19270n;
            this.o = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = bVar.o;
            this.f19270n = 2 | this.f19270n;
            this.f19271p = i11;
        }
        this.l = this.l.d(bVar.l);
    }

    public void i(c cVar) {
        if (cVar == c.f19278r) {
            return;
        }
        int i7 = cVar.f19280m;
        if ((i7 & 1) == 1) {
            int i10 = cVar.f19281n;
            this.f19270n = 1 | this.f19270n;
            this.o = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = cVar.o;
            this.f19270n = 2 | this.f19270n;
            this.f19271p = i11;
        }
        this.l = this.l.d(cVar.l);
    }
}
