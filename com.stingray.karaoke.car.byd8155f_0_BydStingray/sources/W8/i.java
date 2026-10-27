package W8;

import U8.EnumC0810a;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends g {
    public final InterfaceC0817f o;

    public i(int i7, EnumC0810a enumC0810a, InterfaceC0817f interfaceC0817f, InterfaceC2194h interfaceC2194h) {
        super(interfaceC2194h, i7, enumC0810a);
        this.o = interfaceC0817f;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0071  */
    @Override // W8.g, V8.InterfaceC0817f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(V8.InterfaceC0818g r6, q7.InterfaceC2189c r7) {
        /*
            r5 = this;
            m7.q r0 = m7.q.f19267a
            int r1 = r5.f9916m
            r2 = -3
            if (r1 != r2) goto L71
            q7.h r1 = r7.getContext()
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            Q5.h2 r3 = new Q5.h2
            r4 = 1
            r3.<init>(r4)
            q7.h r4 = r5.l
            java.lang.Object r2 = r4.j(r2, r3)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L26
            q7.h r2 = r1.v(r4)
            goto L2b
        L26:
            r2 = 0
            q7.h r2 = S8.AbstractC0727y.l(r1, r4, r2)
        L2b:
            boolean r3 = A7.m.a(r2, r1)
            if (r3 == 0) goto L3b
            java.lang.Object r5 = r5.i(r6, r7)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto L7a
        L39:
            r0 = r5
            goto L7a
        L3b:
            q7.d r3 = q7.C2190d.l
            q7.f r4 = r2.F(r3)
            q7.f r1 = r1.F(r3)
            boolean r1 = A7.m.a(r4, r1)
            if (r1 == 0) goto L71
            q7.h r1 = r7.getContext()
            boolean r3 = r6 instanceof W8.C
            if (r3 != 0) goto L5e
            boolean r3 = r6 instanceof W8.y
            if (r3 == 0) goto L58
            goto L5e
        L58:
            R.l r3 = new R.l
            r3.<init>(r6, r1)
            r6 = r3
        L5e:
            W8.h r1 = new W8.h
            r3 = 0
            r1.<init>(r5, r3)
            java.lang.Object r5 = X8.a.m(r2)
            java.lang.Object r5 = W8.AbstractC0843c.b(r2, r6, r5, r1, r7)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto L7a
            goto L39
        L71:
            java.lang.Object r5 = super.b(r6, r7)
            r7.a r6 = r7.EnumC2303a.l
            if (r5 != r6) goto L7a
            goto L39
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: W8.i.b(V8.g, q7.c):java.lang.Object");
    }

    @Override // W8.g
    public final Object d(U8.x xVar, C0846f c0846f) {
        Object objI = i(new C(xVar), c0846f);
        return objI == EnumC2303a.l ? objI : m7.q.f19267a;
    }

    public abstract Object i(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c);

    @Override // W8.g
    public final String toString() {
        return this.o + " -> " + super.toString();
    }
}
