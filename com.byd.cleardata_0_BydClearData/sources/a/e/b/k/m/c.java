package a.e.b.k.m;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class c extends m {
    ArrayList<m> k;
    private int l;

    public c(a.e.b.k.e eVar, int i) {
        super(eVar);
        this.k = new ArrayList<>();
        this.f = i;
        q();
    }

    private void q() {
        a.e.b.k.e eVar;
        a.e.b.k.e eVarF = this.f248b;
        do {
            eVar = eVarF;
            eVarF = eVarF.F(this.f);
        } while (eVarF != null);
        this.f248b = eVar;
        this.k.add(eVar.H(this.f));
        a.e.b.k.e eVarD = eVar.D(this.f);
        while (eVarD != null) {
            this.k.add(eVarD.H(this.f));
            eVarD = eVarD.D(this.f);
        }
        for (m mVar : this.k) {
            int i = this.f;
            if (i == 0) {
                mVar.f248b.f215d = this;
            } else if (i == 1) {
                mVar.f248b.e = this;
            }
        }
        if ((this.f == 0 && ((a.e.b.k.f) this.f248b.E()).W0()) && this.k.size() > 1) {
            ArrayList<m> arrayList = this.k;
            this.f248b = arrayList.get(arrayList.size() - 1).f248b;
        }
        this.l = this.f == 0 ? this.f248b.v() : this.f248b.J();
    }

    private a.e.b.k.e r() {
        for (int i = 0; i < this.k.size(); i++) {
            m mVar = this.k.get(i);
            if (mVar.f248b.M() != 8) {
                return mVar.f248b;
            }
        }
        return null;
    }

    private a.e.b.k.e s() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            m mVar = this.k.get(size);
            if (mVar.f248b.M() != 8) {
                return mVar.f248b;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01c7 A[PHI: r1 r22 r23 r24
  0x01c7: PHI (r1v53 int) = (r1v51 int), (r1v59 int) binds: [B:119:0x01c5, B:110:0x019f] A[DONT_GENERATE, DONT_INLINE]
  0x01c7: PHI (r22v3 float) = (r22v2 float), (r22v5 float) binds: [B:119:0x01c5, B:110:0x019f] A[DONT_GENERATE, DONT_INLINE]
  0x01c7: PHI (r23v6 boolean) = (r23v5 boolean), (r23v8 boolean) binds: [B:119:0x01c5, B:110:0x019f] A[DONT_GENERATE, DONT_INLINE]
  0x01c7: PHI (r24v6 int) = (r24v5 int), (r24v8 int) binds: [B:119:0x01c5, B:110:0x019f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00eb  */
    @Override // a.e.b.k.m.m, a.e.b.k.m.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(a.e.b.k.m.d r26) {
        /*
            Method dump skipped, instruction units count: 1032
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.c.a(a.e.b.k.m.d):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a5 A[PHI: r0 r1
  0x00a5: PHI (r0v13 int) = (r0v10 int), (r0v19 int) binds: [B:29:0x00a3, B:19:0x006d] A[DONT_GENERATE, DONT_INLINE]
  0x00a5: PHI (r1v3 a.e.b.k.m.f) = (r1v2 a.e.b.k.m.f), (r1v7 a.e.b.k.m.f) binds: [B:29:0x00a3, B:19:0x006d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // a.e.b.k.m.m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void d() {
        /*
            r5 = this;
            java.util.ArrayList<a.e.b.k.m.m> r0 = r5.k
            java.util.Iterator r0 = r0.iterator()
        L6:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L16
            java.lang.Object r1 = r0.next()
            a.e.b.k.m.m r1 = (a.e.b.k.m.m) r1
            r1.d()
            goto L6
        L16:
            java.util.ArrayList<a.e.b.k.m.m> r0 = r5.k
            int r0 = r0.size()
            r1 = 1
            if (r0 >= r1) goto L20
            return
        L20:
            java.util.ArrayList<a.e.b.k.m.m> r2 = r5.k
            r3 = 0
            java.lang.Object r2 = r2.get(r3)
            a.e.b.k.m.m r2 = (a.e.b.k.m.m) r2
            a.e.b.k.e r2 = r2.f248b
            java.util.ArrayList<a.e.b.k.m.m> r4 = r5.k
            int r0 = r0 - r1
            java.lang.Object r0 = r4.get(r0)
            a.e.b.k.m.m r0 = (a.e.b.k.m.m) r0
            a.e.b.k.e r0 = r0.f248b
            int r4 = r5.f
            if (r4 != 0) goto L70
            a.e.b.k.d r1 = r2.C
            a.e.b.k.d r0 = r0.E
            a.e.b.k.m.f r2 = r5.i(r1, r3)
            int r1 = r1.b()
            a.e.b.k.e r4 = r5.r()
            if (r4 == 0) goto L52
            a.e.b.k.d r1 = r4.C
            int r1 = r1.b()
        L52:
            if (r2 == 0) goto L59
            a.e.b.k.m.f r4 = r5.h
            r5.b(r4, r2, r1)
        L59:
            a.e.b.k.m.f r1 = r5.i(r0, r3)
            int r0 = r0.b()
            a.e.b.k.e r2 = r5.s()
            if (r2 == 0) goto L6d
            a.e.b.k.d r0 = r2.E
            int r0 = r0.b()
        L6d:
            if (r1 == 0) goto Lab
            goto La5
        L70:
            a.e.b.k.d r2 = r2.D
            a.e.b.k.d r0 = r0.F
            a.e.b.k.m.f r3 = r5.i(r2, r1)
            int r2 = r2.b()
            a.e.b.k.e r4 = r5.r()
            if (r4 == 0) goto L88
            a.e.b.k.d r2 = r4.D
            int r2 = r2.b()
        L88:
            if (r3 == 0) goto L8f
            a.e.b.k.m.f r4 = r5.h
            r5.b(r4, r3, r2)
        L8f:
            a.e.b.k.m.f r1 = r5.i(r0, r1)
            int r0 = r0.b()
            a.e.b.k.e r2 = r5.s()
            if (r2 == 0) goto La3
            a.e.b.k.d r0 = r2.F
            int r0 = r0.b()
        La3:
            if (r1 == 0) goto Lab
        La5:
            a.e.b.k.m.f r2 = r5.i
            int r0 = -r0
            r5.b(r2, r1, r0)
        Lab:
            a.e.b.k.m.f r0 = r5.h
            r0.f234a = r5
            a.e.b.k.m.f r0 = r5.i
            r0.f234a = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.c.d():void");
    }

    @Override // a.e.b.k.m.m
    public void e() {
        for (int i = 0; i < this.k.size(); i++) {
            this.k.get(i).e();
        }
    }

    @Override // a.e.b.k.m.m
    void f() {
        this.f249c = null;
        Iterator<m> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // a.e.b.k.m.m
    public long j() {
        int size = this.k.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            m mVar = this.k.get(i);
            j = j + ((long) mVar.h.f) + mVar.j() + ((long) mVar.i.f);
        }
        return j;
    }

    @Override // a.e.b.k.m.m
    boolean m() {
        int size = this.k.size();
        for (int i = 0; i < size; i++) {
            if (!this.k.get(i).m()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.f == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        Iterator<m> it = this.k.iterator();
        while (it.hasNext()) {
            String str = string + "<";
            string = (str + it.next()) + "> ";
        }
        return string;
    }
}
