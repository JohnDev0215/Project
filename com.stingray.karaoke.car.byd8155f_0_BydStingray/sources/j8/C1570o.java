package j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;

/* JADX INFO: renamed from: j8.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1570o extends AbstractC2134i implements p8.u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f17423m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public EnumC1571p f17424n;
    public List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C1577w f17425p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public EnumC1572q f17426q;

    public static C1570o g() {
        C1570o c1570o = new C1570o();
        c1570o.f17424n = EnumC1571p.RETURNS_CONSTANT;
        c1570o.o = Collections.emptyList();
        c1570o.f17425p = C1577w.f17458w;
        c1570o.f17426q = EnumC1572q.AT_MOST_ONCE;
        return c1570o;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        r rVarF = f();
        if (rVarF.b()) {
            return rVarF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        C1570o c1570oG = g();
        c1570oG.h(f());
        return c1570oG;
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
            j8.a r1 = j8.r.f17434u     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            j8.r r1 = new j8.r     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            j8.r r4 = (j8.r) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: j8.C1570o.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((r) abstractC2139n);
        return this;
    }

    public final r f() {
        r rVar = new r(this);
        int i7 = this.f17423m;
        int i10 = (i7 & 1) != 1 ? 0 : 1;
        rVar.f17436n = this.f17424n;
        if ((i7 & 2) == 2) {
            this.o = Collections.unmodifiableList(this.o);
            this.f17423m &= -3;
        }
        rVar.o = this.o;
        if ((i7 & 4) == 4) {
            i10 |= 2;
        }
        rVar.f17437p = this.f17425p;
        if ((i7 & 8) == 8) {
            i10 |= 4;
        }
        rVar.f17438q = this.f17426q;
        rVar.f17435m = i10;
        return rVar;
    }

    public final void h(r rVar) {
        C1577w c1577w;
        if (rVar == r.f17433t) {
            return;
        }
        if ((rVar.f17435m & 1) == 1) {
            EnumC1571p enumC1571p = rVar.f17436n;
            enumC1571p.getClass();
            this.f17423m |= 1;
            this.f17424n = enumC1571p;
        }
        if (!rVar.o.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = rVar.o;
                this.f17423m &= -3;
            } else {
                if ((this.f17423m & 2) != 2) {
                    this.o = new ArrayList(this.o);
                    this.f17423m |= 2;
                }
                this.o.addAll(rVar.o);
            }
        }
        if ((rVar.f17435m & 2) == 2) {
            C1577w c1577w2 = rVar.f17437p;
            if ((this.f17423m & 4) != 4 || (c1577w = this.f17425p) == C1577w.f17458w) {
                this.f17425p = c1577w2;
            } else {
                C1575u c1575uG = C1575u.g();
                c1575uG.h(c1577w);
                c1575uG.h(c1577w2);
                this.f17425p = c1575uG.f();
            }
            this.f17423m |= 4;
        }
        if ((rVar.f17435m & 4) == 4) {
            EnumC1572q enumC1572q = rVar.f17438q;
            enumC1572q.getClass();
            this.f17423m |= 8;
            this.f17426q = enumC1572q;
        }
        this.l = this.l.d(rVar.l);
    }
}
