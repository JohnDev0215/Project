package m8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p8.AbstractC2126a;
import p8.AbstractC2134i;
import p8.AbstractC2139n;
import p8.u;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractC2134i implements u {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f19298m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f19299n;
    public List o;

    public static f g() {
        f fVar = new f();
        fVar.f19299n = Collections.emptyList();
        fVar.o = Collections.emptyList();
        return fVar;
    }

    @Override // p8.AbstractC2134i
    public final AbstractC2126a c() {
        j jVarF = f();
        if (jVarF.b()) {
            return jVarF;
        }
        throw new A9.k();
    }

    public final Object clone() {
        f fVarG = g();
        fVarG.h(f());
        return fVarG;
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
            j8.a r1 = m8.j.f19322s     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.getClass()     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            m8.j r1 = new m8.j     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r1.<init>(r3, r4)     // Catch: java.lang.Throwable -> Lf p8.C2142q -> L11
            r2.h(r1)
            return r2
        Lf:
            r3 = move-exception
            goto L19
        L11:
            r3 = move-exception
            p8.a r4 = r3.l     // Catch: java.lang.Throwable -> Lf
            m8.j r4 = (m8.j) r4     // Catch: java.lang.Throwable -> Lf
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
        throw new UnsupportedOperationException("Method not decompiled: m8.f.d(p8.e, p8.g):p8.i");
    }

    @Override // p8.AbstractC2134i
    public final /* bridge */ /* synthetic */ AbstractC2134i e(AbstractC2139n abstractC2139n) {
        h((j) abstractC2139n);
        return this;
    }

    public final j f() {
        j jVar = new j(this);
        if ((this.f19298m & 1) == 1) {
            this.f19299n = Collections.unmodifiableList(this.f19299n);
            this.f19298m &= -2;
        }
        jVar.f19323m = this.f19299n;
        if ((this.f19298m & 2) == 2) {
            this.o = Collections.unmodifiableList(this.o);
            this.f19298m &= -3;
        }
        jVar.f19324n = this.o;
        return jVar;
    }

    public final void h(j jVar) {
        if (jVar == j.f19321r) {
            return;
        }
        if (!jVar.f19323m.isEmpty()) {
            if (this.f19299n.isEmpty()) {
                this.f19299n = jVar.f19323m;
                this.f19298m &= -2;
            } else {
                if ((this.f19298m & 1) != 1) {
                    this.f19299n = new ArrayList(this.f19299n);
                    this.f19298m |= 1;
                }
                this.f19299n.addAll(jVar.f19323m);
            }
        }
        if (!jVar.f19324n.isEmpty()) {
            if (this.o.isEmpty()) {
                this.o = jVar.f19324n;
                this.f19298m &= -3;
            } else {
                if ((this.f19298m & 2) != 2) {
                    this.o = new ArrayList(this.o);
                    this.f19298m |= 2;
                }
                this.o.addAll(jVar.f19324n);
            }
        }
        this.l = this.l.d(jVar.l);
    }
}
