package m8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.u;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC2134i implements u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19300m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f19301n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f19302p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public h f19303q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List f19304r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public List f19305s;

    public static g g() {
        g gVar = new g();
        gVar.f19301n = 1;
        gVar.f19302p = "";
        gVar.f19303q = h.NONE;
        gVar.f19304r = Collections.emptyList();
        gVar.f19305s = Collections.emptyList();
        return gVar;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        i iVarF = f();
        if (iVarF.b()) {
            return iVarF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        g gVarG = g();
        gVarG.h(f());
        return gVarG;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x001b  */
    @Override // p8.AbstractC2134i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p8.AbstractC2134i d(p8.C2130e r2, p8.C2132g r3) throws java.lang.Throwable {
        /*
            r1 = this;
            r3 = 0
            j8.a r0 = m8.i.f19310y     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            m8.i r0 = new m8.i     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r0.<init>(r2)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.h(r0)
            return r1
        Lf:
            r2 = move-exception
            goto L19
        L11:
            r2 = move-exception
            p8.a r0 = r2.l     // Catch: java.lang.Throwable -> Lf
            m8.i r0 = (m8.i) r0     // Catch: java.lang.Throwable -> Lf
            throw r2     // Catch: java.lang.Throwable -> L17
        L17:
            r2 = move-exception
            r3 = r0
        L19:
            if (r3 == 0) goto L1e
            r1.h(r3)
        L1e:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: m8.g.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((i) abstractC2139n);
        return this;
    }

    public final i f() {
        i iVar = new i(this);
        int i7 = this.f19300m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        iVar.f19312n = this.f19301n;
        if ((i7 & 2) == 2) {
            i10 |= 2;
        }
        iVar.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 4;
        }
        iVar.f19313p = this.f19302p;
        if ((i7 & 8) == 8) {
            i10 |= 8;
        }
        iVar.f19314q = this.f19303q;
        if ((i7 & 16) == 16) {
            this.f19304r = Collections.unmodifiableList(this.f19304r);
            this.f19300m &= -17;
        }
        iVar.f19315r = this.f19304r;
        if ((this.f19300m & 32) == 32) {
            this.f19305s = Collections.unmodifiableList(this.f19305s);
            this.f19300m &= -33;
        }
        iVar.f19317t = this.f19305s;
        iVar.f19311m = i10;
        return iVar;
    }

    public final void h(i iVar) {
        if (iVar == i.f19309x) {
            return;
        }
        int i7 = iVar.f19311m;
        if ((i7 & 1) == 1) {
            int i10 = iVar.f19312n;
            this.f19300m = 1 | this.f19300m;
            this.f19301n = i10;
        }
        if ((i7 & 2) == 2) {
            int i11 = iVar.o;
            this.f19300m = 2 | this.f19300m;
            this.o = i11;
        }
        if ((i7 & 4) == 4) {
            this.f19300m |= 4;
            this.f19302p = iVar.f19313p;
        }
        if ((i7 & 8) == 8) {
            h hVar = iVar.f19314q;
            hVar.getClass();
            this.f19300m = 8 | this.f19300m;
            this.f19303q = hVar;
        }
        if (!iVar.f19315r.isEmpty()) {
            if (this.f19304r.isEmpty()) {
                this.f19304r = iVar.f19315r;
                this.f19300m &= -17;
            } else {
                if ((this.f19300m & 16) != 16) {
                    this.f19304r = new ArrayList(this.f19304r);
                    this.f19300m |= 16;
                }
                this.f19304r.addAll(iVar.f19315r);
            }
        }
        if (!iVar.f19317t.isEmpty()) {
            if (this.f19305s.isEmpty()) {
                this.f19305s = iVar.f19317t;
                this.f19300m &= -33;
            } else {
                if ((this.f19300m & 32) != 32) {
                    this.f19305s = new ArrayList(this.f19305s);
                    this.f19300m |= 32;
                }
                this.f19305s.addAll(iVar.f19317t);
            }
        }
        this.l = this.l.d(iVar.l);
    }
}
