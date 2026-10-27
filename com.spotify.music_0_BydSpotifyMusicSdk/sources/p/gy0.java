package p;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class gy0 extends dy0 {
    @Override // p.dy0
    public final void a(View view, Object obj) {
        ((wn2) obj).d(view);
    }

    @Override // p.dy0
    public final void b(Object obj, ArrayList arrayList) {
        wn2 wn2Var = (wn2) obj;
        if (wn2Var == null) {
            return;
        }
        int i = 0;
        if (wn2Var instanceof bo2) {
            bo2 bo2Var = (bo2) wn2Var;
            int size = bo2Var.F.size();
            while (i < size) {
                b((i < 0 || i >= bo2Var.F.size()) ? null : (wn2) bo2Var.F.get(i), arrayList);
                i++;
            }
            return;
        }
        if (dy0.h(wn2Var.m) && dy0.h(null) && dy0.h(null) && dy0.h(wn2Var.n)) {
            int size2 = arrayList.size();
            while (i < size2) {
                wn2Var.d((View) arrayList.get(i));
                i++;
            }
        }
    }

    @Override // p.dy0
    public final void c(ViewGroup viewGroup, Object obj) {
        zn2.a(viewGroup, (wn2) obj);
    }

    @Override // p.dy0
    public final boolean e(Object obj) {
        return obj instanceof wn2;
    }

    @Override // p.dy0
    public final Object f(Object obj) {
        if (obj != null) {
            return ((wn2) obj).clone();
        }
        return null;
    }

    @Override // p.dy0
    public final Object i(Object obj, Object obj2, Object obj3) {
        wn2 wn2Var = (wn2) obj;
        wn2 wn2Var2 = (wn2) obj2;
        wn2 wn2Var3 = (wn2) obj3;
        if (wn2Var != null && wn2Var2 != null) {
            bo2 bo2Var = new bo2();
            bo2Var.N(wn2Var);
            bo2Var.N(wn2Var2);
            bo2Var.G = false;
            wn2Var = bo2Var;
        } else if (wn2Var == null) {
            wn2Var = wn2Var2 != null ? wn2Var2 : null;
        }
        if (wn2Var3 == null) {
            return wn2Var;
        }
        bo2 bo2Var2 = new bo2();
        if (wn2Var != null) {
            bo2Var2.N(wn2Var);
        }
        bo2Var2.N(wn2Var3);
        return bo2Var2;
    }

    @Override // p.dy0
    public final Object j(Object obj, Object obj2) {
        bo2 bo2Var = new bo2();
        if (obj != null) {
            bo2Var.N((wn2) obj);
        }
        bo2Var.N((wn2) obj2);
        return bo2Var;
    }

    @Override // p.dy0
    public final void k(Object obj, View view, ArrayList arrayList) {
        ((wn2) obj).a(new ey0(view, arrayList));
    }

    @Override // p.dy0
    public final void l(Object obj, Object obj2, ArrayList arrayList, Object obj3, ArrayList arrayList2) {
        ((wn2) obj).a(new fy0(this, obj2, arrayList, obj3, arrayList2));
    }

    @Override // p.dy0
    public final void m(View view, Object obj) {
        if (view != null) {
            dy0.g(view, new Rect());
            ((wn2) obj).F(new e2());
        }
    }

    @Override // p.dy0
    public final void n(Object obj, Rect rect) {
        ((wn2) obj).F(new e2());
    }

    @Override // p.dy0
    public final void p(Object obj, View view, ArrayList arrayList) {
        bo2 bo2Var = (bo2) obj;
        ArrayList arrayList2 = bo2Var.n;
        arrayList2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            dy0.d((View) arrayList.get(i), arrayList2);
        }
        arrayList2.add(view);
        arrayList.add(view);
        b(bo2Var, arrayList);
    }

    @Override // p.dy0
    public final void q(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        bo2 bo2Var = (bo2) obj;
        if (bo2Var != null) {
            ArrayList arrayList3 = bo2Var.n;
            arrayList3.clear();
            arrayList3.addAll(arrayList2);
            s(bo2Var, arrayList, arrayList2);
        }
    }

    @Override // p.dy0
    public final Object r(Object obj) {
        if (obj == null) {
            return null;
        }
        bo2 bo2Var = new bo2();
        bo2Var.N((wn2) obj);
        return bo2Var;
    }

    public final void s(Object obj, ArrayList arrayList, ArrayList arrayList2) {
        wn2 wn2Var = (wn2) obj;
        int i = 0;
        if (wn2Var instanceof bo2) {
            bo2 bo2Var = (bo2) wn2Var;
            int size = bo2Var.F.size();
            while (i < size) {
                s((i < 0 || i >= bo2Var.F.size()) ? null : (wn2) bo2Var.F.get(i), arrayList, arrayList2);
                i++;
            }
            return;
        }
        if (dy0.h(wn2Var.m) && dy0.h(null) && dy0.h(null)) {
            ArrayList arrayList3 = wn2Var.n;
            if (arrayList3.size() == arrayList.size() && arrayList3.containsAll(arrayList)) {
                int size2 = arrayList2 == null ? 0 : arrayList2.size();
                while (i < size2) {
                    wn2Var.d((View) arrayList2.get(i));
                    i++;
                }
                for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                    wn2Var.A((View) arrayList.get(size3));
                }
            }
        }
    }
}
