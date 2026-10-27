package m1;

import g1.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import l1.C1731b;

/* JADX INFO: loaded from: classes.dex */
public final class i extends c {
    @Override // l1.g, l1.C1731b, l1.h
    public final void a() {
        q qVar;
        ArrayList arrayList = this.f18433m0;
        Iterator it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            qVar = this.f18431k0;
            if (!zHasNext) {
                break;
            } else {
                qVar.b(it.next()).i();
            }
        }
        C1731b c1731b = null;
        C1731b c1731b2 = null;
        for (Object obj : arrayList) {
            C1731b c1731bB = qVar.b(obj);
            if (c1731b2 == null) {
                Object obj2 = this.f18375R;
                if (obj2 != null) {
                    c1731bB.q(obj2);
                    c1731bB.l(this.f18404n).n(this.f18409t);
                } else {
                    Object obj3 = this.f18376S;
                    if (obj3 != null) {
                        c1731bB.f18401j0 = 10;
                        c1731bB.f18376S = obj3;
                        c1731bB.l(this.f18404n).n(this.f18409t);
                    } else {
                        String string = c1731bB.f18384a.toString();
                        c1731bB.q(0);
                        c1731bB.m(Float.valueOf(x(string))).o(Float.valueOf(w(string)));
                    }
                }
                c1731b2 = c1731bB;
            }
            if (c1731b != null) {
                String string2 = c1731b.f18384a.toString();
                String string3 = c1731bB.f18384a.toString();
                Object obj4 = c1731bB.f18384a;
                c1731b.f18401j0 = 12;
                c1731b.f18378U = obj4;
                c1731b.m(Float.valueOf(v(string2))).o(Float.valueOf(u(string2)));
                Object obj5 = c1731b.f18384a;
                c1731bB.f18401j0 = 10;
                c1731bB.f18376S = obj5;
                c1731bB.m(Float.valueOf(x(string3))).o(Float.valueOf(w(string3)));
            }
            String string4 = obj.toString();
            HashMap map = this.f18897o0;
            float fFloatValue = map.containsKey(string4) ? ((Float) map.get(string4)).floatValue() : -1.0f;
            if (fFloatValue != -1.0f) {
                c1731bB.f18394g = fFloatValue;
            }
            c1731b = c1731bB;
        }
        if (c1731b != null) {
            Object obj6 = this.f18378U;
            if (obj6 != null) {
                c1731b.f18401j0 = 12;
                c1731b.f18378U = obj6;
                c1731b.l(this.o).n(this.f18410u);
            } else {
                Object obj7 = this.f18379V;
                if (obj7 != null) {
                    c1731b.f(obj7);
                    c1731b.l(this.o).n(this.f18410u);
                } else {
                    String string5 = c1731b.f18384a.toString();
                    c1731b.f(0);
                    c1731b.m(Float.valueOf(v(string5))).o(Float.valueOf(u(string5)));
                }
            }
        }
        if (c1731b2 == null) {
            return;
        }
        float f10 = this.f18896n0;
        if (f10 != 0.5f) {
            c1731b2.f18398i = f10;
        }
        int iOrdinal = this.f18902t0.ordinal();
        if (iOrdinal == 0) {
            c1731b2.f18390e = 0;
        } else if (iOrdinal == 1) {
            c1731b2.f18390e = 1;
        } else {
            if (iOrdinal != 2) {
                return;
            }
            c1731b2.f18390e = 2;
        }
    }
}
