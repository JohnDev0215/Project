package T;

import h2.AbstractC1346c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;

/* JADX INFO: renamed from: T.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0746i0 implements Y2.d {
    public final List l;

    public /* synthetic */ C0746i0(List list) {
        this.l = list;
    }

    @Override // Y2.d
    public int a(long j3) {
        return j3 < 0 ? 0 : -1;
    }

    public void b(int i7) {
        List list = this.l;
        if (list.isEmpty() || !(((Number) list.get(0)).intValue() == i7 || ((Number) list.get(list.size() - 1)).intValue() == i7)) {
            int size = list.size();
            list.add(Integer.valueOf(i7));
            while (size > 0) {
                int i10 = ((size + 1) >>> 1) - 1;
                int iIntValue = ((Number) list.get(i10)).intValue();
                if (i7 <= iIntValue) {
                    break;
                }
                list.set(size, Integer.valueOf(iIntValue));
                size = i10;
            }
            list.set(size, Integer.valueOf(i7));
        }
    }

    public j8.Q c(int i7) {
        return (j8.Q) this.l.get(i7);
    }

    @Override // Y2.d
    public long d(int i7) {
        AbstractC1346c.c(i7 == 0);
        return 0L;
    }

    public int e() {
        int iIntValue;
        List list = this.l;
        if (!(list.size() > 0)) {
            C0735d.y("Set is empty");
            throw null;
        }
        int iIntValue2 = ((Number) list.get(0)).intValue();
        while (!list.isEmpty() && ((Number) list.get(0)).intValue() == iIntValue2) {
            list.set(0, AbstractC1998m.h0(list));
            list.remove(list.size() - 1);
            int size = list.size();
            int size2 = list.size() >>> 1;
            int i7 = 0;
            while (i7 < size2) {
                int iIntValue3 = ((Number) list.get(i7)).intValue();
                int i10 = (i7 + 1) * 2;
                int i11 = i10 - 1;
                int iIntValue4 = ((Number) list.get(i11)).intValue();
                if (i10 >= size || (iIntValue = ((Number) list.get(i10)).intValue()) <= iIntValue4) {
                    if (iIntValue4 > iIntValue3) {
                        list.set(i7, Integer.valueOf(iIntValue4));
                        list.set(i11, Integer.valueOf(iIntValue3));
                        i7 = i11;
                    }
                } else if (iIntValue > iIntValue3) {
                    list.set(i7, Integer.valueOf(iIntValue));
                    list.set(i10, Integer.valueOf(iIntValue3));
                    i7 = i10;
                }
            }
        }
        return iIntValue2;
    }

    @Override // Y2.d
    public List g(long j3) {
        return j3 >= 0 ? this.l : Collections.emptyList();
    }

    @Override // Y2.d
    public int h() {
        return 1;
    }

    public C0746i0(j8.X x8) {
        A7.m.f(x8, "typeTable");
        List list = x8.f17243n;
        if ((x8.f17242m & 1) == 1) {
            int i7 = x8.o;
            A7.m.e(list, "typeTable.typeList");
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            int i10 = 0;
            for (Object obj : list) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    AbstractC1999n.I();
                    throw null;
                }
                j8.Q qG = (j8.Q) obj;
                if (i10 >= i7) {
                    qG.getClass();
                    j8.P pR = j8.Q.r(qG);
                    pR.o |= 2;
                    pR.f17168q = true;
                    qG = pR.g();
                    if (!qG.b()) {
                        throw new A9.k();
                    }
                }
                arrayList.add(qG);
                i10 = i11;
            }
            list = arrayList;
        }
        A7.m.e(list, "run {\n        val origin… else originalTypes\n    }");
        this.l = list;
    }

    public C0746i0() {
        this.l = new ArrayList();
    }
}
