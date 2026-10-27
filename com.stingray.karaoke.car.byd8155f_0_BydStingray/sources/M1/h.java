package m1;

import g1.q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import l1.C1731b;

/* JADX INFO: loaded from: classes.dex */
public final class h extends c {
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
                qVar.b(it.next()).h();
            }
        }
        C1731b c1731b = null;
        C1731b c1731b2 = null;
        for (Object obj : arrayList) {
            C1731b c1731bB = qVar.b(obj);
            if (c1731b2 == null) {
                Object obj2 = this.f18371N;
                if (obj2 != null) {
                    c1731bB.p(obj2);
                    c1731bB.l(this.l).n(this.f18407r);
                } else {
                    Object obj3 = this.f18372O;
                    if (obj3 != null) {
                        c1731bB.f18401j0 = 6;
                        c1731bB.f18372O = obj3;
                        c1731bB.l(this.l).n(this.f18407r);
                    } else {
                        Object obj4 = this.f18367J;
                        if (obj4 != null) {
                            c1731bB.p(obj4);
                            c1731bB.l(this.f18400j).n(this.f18405p);
                        } else {
                            Object obj5 = this.f18368K;
                            if (obj5 != null) {
                                c1731bB.f18401j0 = 6;
                                c1731bB.f18372O = obj5;
                                c1731bB.l(this.f18400j).n(this.f18405p);
                            } else {
                                String string = c1731bB.f18384a.toString();
                                c1731bB.p(0);
                                c1731bB.m(Float.valueOf(x(string))).o(Float.valueOf(w(string)));
                            }
                        }
                    }
                }
                c1731b2 = c1731bB;
            }
            if (c1731b != null) {
                String string2 = c1731b.f18384a.toString();
                String string3 = c1731bB.f18384a.toString();
                Object obj6 = c1731bB.f18384a;
                c1731b.f18401j0 = 7;
                c1731b.f18373P = obj6;
                c1731b.m(Float.valueOf(v(string2))).o(Float.valueOf(u(string2)));
                Object obj7 = c1731b.f18384a;
                c1731bB.f18401j0 = 6;
                c1731bB.f18372O = obj7;
                c1731bB.m(Float.valueOf(x(string3))).o(Float.valueOf(w(string3)));
            }
            String string4 = obj.toString();
            HashMap map = this.f18897o0;
            float fFloatValue = map.containsKey(string4) ? ((Float) map.get(string4)).floatValue() : -1.0f;
            if (fFloatValue != -1.0f) {
                c1731bB.f18392f = fFloatValue;
            }
            c1731b = c1731bB;
        }
        if (c1731b != null) {
            Object obj8 = this.f18373P;
            if (obj8 != null) {
                c1731b.f18401j0 = 7;
                c1731b.f18373P = obj8;
                c1731b.l(this.f18403m).n(this.f18408s);
            } else {
                Object obj9 = this.f18374Q;
                if (obj9 != null) {
                    c1731b.j(obj9);
                    c1731b.l(this.f18403m).n(this.f18408s);
                } else {
                    Object obj10 = this.f18369L;
                    if (obj10 != null) {
                        c1731b.f18401j0 = 7;
                        c1731b.f18373P = obj10;
                        c1731b.l(this.f18402k).n(this.f18406q);
                    } else {
                        Object obj11 = this.f18370M;
                        if (obj11 != null) {
                            c1731b.j(obj11);
                            c1731b.l(this.f18402k).n(this.f18406q);
                        } else {
                            String string5 = c1731b.f18384a.toString();
                            c1731b.j(0);
                            c1731b.m(Float.valueOf(v(string5))).o(Float.valueOf(u(string5)));
                        }
                    }
                }
            }
        }
        if (c1731b2 == null) {
            return;
        }
        float f10 = this.f18896n0;
        if (f10 != 0.5f) {
            c1731b2.f18396h = f10;
        }
        int iOrdinal = this.f18902t0.ordinal();
        if (iOrdinal == 0) {
            c1731b2.d = 0;
        } else if (iOrdinal == 1) {
            c1731b2.d = 1;
        } else {
            if (iOrdinal != 2) {
                return;
            }
            c1731b2.d = 2;
        }
    }
}
