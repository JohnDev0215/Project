package p;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowId;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class wn2 implements Cloneable {
    public static final int[] C = {2, 1, 3, 4};
    public static final zb0 D = new zb0();
    public static final ThreadLocal E = new ThreadLocal();
    public q30 A;
    public ArrayList s;
    public ArrayList t;
    public final String c = getClass().getName();
    public long e = -1;
    public long k = -1;
    public TimeInterpolator l = null;
    public final ArrayList m = new ArrayList();
    public final ArrayList n = new ArrayList();
    public gh2 o = new gh2(5);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public gh2 f204p = new gh2(5);
    public bo2 q = null;
    public final int[] r = C;
    public final ArrayList u = new ArrayList();
    public int v = 0;
    public boolean w = false;
    public boolean x = false;
    public ArrayList y = null;
    public ArrayList z = new ArrayList();
    public zb0 B = D;

    public static void e(gh2 gh2Var, View view, do2 do2Var) {
        ((nb) gh2Var.e).put(view, do2Var);
        int id = view.getId();
        if (id >= 0) {
            if (((SparseArray) gh2Var.k).indexOfKey(id) >= 0) {
                ((SparseArray) gh2Var.k).put(id, null);
            } else {
                ((SparseArray) gh2Var.k).put(id, view);
            }
        }
        WeakHashMap weakHashMap = zt2.a;
        String strK = ot2.k(view);
        if (strK != null) {
            if (((nb) gh2Var.m).containsKey(strK)) {
                ((nb) gh2Var.m).put(strK, null);
            } else {
                ((nb) gh2Var.m).put(strK, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                wd1 wd1Var = (wd1) gh2Var.l;
                if (wd1Var.c) {
                    int i = wd1Var.l;
                    long[] jArr = wd1Var.e;
                    Object[] objArr = wd1Var.k;
                    int i2 = 0;
                    for (int i3 = 0; i3 < i; i3++) {
                        Object obj = objArr[i3];
                        if (obj != xd1.a) {
                            if (i3 != i2) {
                                jArr[i2] = jArr[i3];
                                objArr[i2] = obj;
                                objArr[i3] = null;
                            }
                            i2++;
                        }
                    }
                    wd1Var.c = false;
                    wd1Var.l = i2;
                }
                if (zu.j(wd1Var.e, wd1Var.l, itemIdAtPosition) < 0) {
                    it2.r(view, true);
                    ((wd1) gh2Var.l).g(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) ((wd1) gh2Var.l).e(itemIdAtPosition);
                if (view2 != null) {
                    it2.r(view2, false);
                    ((wd1) gh2Var.l).g(itemIdAtPosition, null);
                }
            }
        }
    }

    public static nb s() {
        ThreadLocal threadLocal = E;
        nb nbVar = (nb) threadLocal.get();
        if (nbVar != null) {
            return nbVar;
        }
        nb nbVar2 = new nb();
        threadLocal.set(nbVar2);
        return nbVar2;
    }

    public static boolean x(do2 do2Var, do2 do2Var2, String str) {
        Object obj = do2Var.a.get(str);
        Object obj2 = do2Var2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public void A(View view) {
        this.n.remove(view);
    }

    public void B(ViewGroup viewGroup) {
        if (this.w) {
            if (!this.x) {
                nb nbVarS = s();
                int i = nbVarS.k;
                iv2 iv2Var = dv2.a;
                WindowId windowId = viewGroup.getWindowId();
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    tn2 tn2Var = (tn2) nbVarS.i(i2);
                    if (tn2Var.a != null) {
                        qw2 qw2Var = tn2Var.d;
                        if ((qw2Var instanceof pw2) && ((pw2) qw2Var).a.equals(windowId)) {
                            ((Animator) nbVarS.f(i2)).resume();
                        }
                    }
                }
                ArrayList arrayList = this.y;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.y.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((vn2) arrayList2.get(i3)).d();
                    }
                }
            }
            this.w = false;
        }
    }

    public void C() {
        L();
        nb nbVarS = s();
        for (Animator animator : this.z) {
            if (nbVarS.containsKey(animator)) {
                L();
                if (animator != null) {
                    animator.addListener(new sn2(this, nbVarS));
                    long j = this.k;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.e;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.l;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new u3(1, this));
                    animator.start();
                }
            }
        }
        this.z.clear();
        p();
    }

    public void D(long j) {
        this.k = j;
    }

    public void F(q30 q30Var) {
        this.A = q30Var;
    }

    public void G(TimeInterpolator timeInterpolator) {
        this.l = timeInterpolator;
    }

    public void H(zb0 zb0Var) {
        if (zb0Var == null) {
            this.B = D;
        } else {
            this.B = zb0Var;
        }
    }

    public void I() {
    }

    public void K(long j) {
        this.e = j;
    }

    public final void L() {
        if (this.v == 0) {
            ArrayList arrayList = this.y;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.y.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((vn2) arrayList2.get(i)).b();
                }
            }
            this.x = false;
        }
        this.v++;
    }

    public String M(String str) {
        String string = str + getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + ": ";
        if (this.k != -1) {
            StringBuilder sbF = hf2.f(string, "dur(");
            sbF.append(this.k);
            sbF.append(") ");
            string = sbF.toString();
        }
        if (this.e != -1) {
            StringBuilder sbF2 = hf2.f(string, "dly(");
            sbF2.append(this.e);
            sbF2.append(") ");
            string = sbF2.toString();
        }
        if (this.l != null) {
            StringBuilder sbF3 = hf2.f(string, "interp(");
            sbF3.append(this.l);
            sbF3.append(") ");
            string = sbF3.toString();
        }
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        ArrayList arrayList2 = this.n;
        if (size <= 0 && arrayList2.size() <= 0) {
            return string;
        }
        String strH = u4.h(string, "tgts(");
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i > 0) {
                    strH = u4.h(strH, ", ");
                }
                strH = strH + arrayList.get(i);
            }
        }
        if (arrayList2.size() > 0) {
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (i2 > 0) {
                    strH = u4.h(strH, ", ");
                }
                strH = strH + arrayList2.get(i2);
            }
        }
        return u4.h(strH, ")");
    }

    public void a(vn2 vn2Var) {
        if (this.y == null) {
            this.y = new ArrayList();
        }
        this.y.add(vn2Var);
    }

    public void d(View view) {
        this.n.add(view);
    }

    public abstract void f(do2 do2Var);

    public final void g(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            do2 do2Var = new do2(view);
            if (z) {
                j(do2Var);
            } else {
                f(do2Var);
            }
            do2Var.c.add(this);
            h(do2Var);
            if (z) {
                e(this.o, view, do2Var);
            } else {
                e(this.f204p, view, do2Var);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                g(viewGroup.getChildAt(i), z);
            }
        }
    }

    public void h(do2 do2Var) {
    }

    public abstract void j(do2 do2Var);

    public final void k(ViewGroup viewGroup, boolean z) {
        l(z);
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        ArrayList arrayList2 = this.n;
        if (size <= 0 && arrayList2.size() <= 0) {
            g(viewGroup, z);
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(((Integer) arrayList.get(i)).intValue());
            if (viewFindViewById != null) {
                do2 do2Var = new do2(viewFindViewById);
                if (z) {
                    j(do2Var);
                } else {
                    f(do2Var);
                }
                do2Var.c.add(this);
                h(do2Var);
                if (z) {
                    e(this.o, viewFindViewById, do2Var);
                } else {
                    e(this.f204p, viewFindViewById, do2Var);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            View view = (View) arrayList2.get(i2);
            do2 do2Var2 = new do2(view);
            if (z) {
                j(do2Var2);
            } else {
                f(do2Var2);
            }
            do2Var2.c.add(this);
            h(do2Var2);
            if (z) {
                e(this.o, view, do2Var2);
            } else {
                e(this.f204p, view, do2Var2);
            }
        }
    }

    public final void l(boolean z) {
        if (z) {
            ((nb) this.o.e).clear();
            ((SparseArray) this.o.k).clear();
            ((wd1) this.o.l).a();
        } else {
            ((nb) this.f204p.e).clear();
            ((SparseArray) this.f204p.k).clear();
            ((wd1) this.f204p.l).a();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public wn2 clone() {
        try {
            wn2 wn2Var = (wn2) super.clone();
            wn2Var.z = new ArrayList();
            wn2Var.o = new gh2(5);
            wn2Var.f204p = new gh2(5);
            wn2Var.s = null;
            wn2Var.t = null;
            return wn2Var;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator n(ViewGroup viewGroup, do2 do2Var, do2 do2Var2) {
        return null;
    }

    public void o(ViewGroup viewGroup, gh2 gh2Var, gh2 gh2Var2, ArrayList arrayList, ArrayList arrayList2) {
        Animator animatorN;
        int i;
        View view;
        do2 do2Var;
        Animator animator;
        do2 do2Var2;
        nb nbVarS = s();
        SparseIntArray sparseIntArray = new SparseIntArray();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            do2 do2Var3 = (do2) arrayList.get(i2);
            do2 do2Var4 = (do2) arrayList2.get(i2);
            if (do2Var3 != null && !do2Var3.c.contains(this)) {
                do2Var3 = null;
            }
            if (do2Var4 != null && !do2Var4.c.contains(this)) {
                do2Var4 = null;
            }
            if (!(do2Var3 == null && do2Var4 == null) && ((do2Var3 == null || do2Var4 == null || v(do2Var3, do2Var4)) && (animatorN = n(viewGroup, do2Var3, do2Var4)) != null)) {
                String str = this.c;
                if (do2Var4 != null) {
                    String[] strArrT = t();
                    view = do2Var4.b;
                    if (strArrT != null && strArrT.length > 0) {
                        do2Var2 = new do2(view);
                        do2 do2Var5 = (do2) ((nb) gh2Var2.e).get(view);
                        i = size;
                        if (do2Var5 != null) {
                            int i3 = 0;
                            while (i3 < strArrT.length) {
                                HashMap map = do2Var2.a;
                                String str2 = strArrT[i3];
                                map.put(str2, do2Var5.a.get(str2));
                                i3++;
                                strArrT = strArrT;
                            }
                        }
                        int i4 = nbVarS.k;
                        int i5 = 0;
                        while (true) {
                            if (i5 >= i4) {
                                animator = animatorN;
                                break;
                            }
                            tn2 tn2Var = (tn2) nbVarS.get((Animator) nbVarS.f(i5));
                            if (tn2Var.c != null && tn2Var.a == view && tn2Var.b.equals(str) && tn2Var.c.equals(do2Var2)) {
                                animator = null;
                                break;
                            }
                            i5++;
                        }
                    } else {
                        i = size;
                        animator = animatorN;
                        do2Var2 = null;
                    }
                    animatorN = animator;
                    do2Var = do2Var2;
                } else {
                    i = size;
                    view = do2Var3.b;
                    do2Var = null;
                }
                if (animatorN != null) {
                    iv2 iv2Var = dv2.a;
                    pw2 pw2Var = new pw2(viewGroup);
                    tn2 tn2Var2 = new tn2();
                    tn2Var2.a = view;
                    tn2Var2.b = str;
                    tn2Var2.c = do2Var;
                    tn2Var2.d = pw2Var;
                    tn2Var2.e = this;
                    nbVarS.put(animatorN, tn2Var2);
                    this.z.add(animatorN);
                }
            } else {
                i = size;
            }
            i2++;
            size = i;
        }
        if (sparseIntArray.size() != 0) {
            for (int i6 = 0; i6 < sparseIntArray.size(); i6++) {
                Animator animator2 = (Animator) this.z.get(sparseIntArray.keyAt(i6));
                animator2.setStartDelay(animator2.getStartDelay() + (((long) sparseIntArray.valueAt(i6)) - Long.MAX_VALUE));
            }
        }
    }

    public final void p() {
        int i = this.v - 1;
        this.v = i;
        if (i == 0) {
            ArrayList arrayList = this.y;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.y.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((vn2) arrayList2.get(i2)).a(this);
                }
            }
            for (int i3 = 0; i3 < ((wd1) this.o.l).h(); i3++) {
                View view = (View) ((wd1) this.o.l).j(i3);
                if (view != null) {
                    WeakHashMap weakHashMap = zt2.a;
                    it2.r(view, false);
                }
            }
            for (int i4 = 0; i4 < ((wd1) this.f204p.l).h(); i4++) {
                View view2 = (View) ((wd1) this.f204p.l).j(i4);
                if (view2 != null) {
                    WeakHashMap weakHashMap2 = zt2.a;
                    it2.r(view2, false);
                }
            }
            this.x = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x002d, code lost:
    
        if (r3 < 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002f, code lost:
    
        if (r7 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0031, code lost:
    
        r6 = r5.t;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0034, code lost:
    
        r6 = r5.s;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003d, code lost:
    
        return (p.do2) r6.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.do2 r(android.view.View r6, boolean r7) {
        /*
            r5 = this;
            p.bo2 r0 = r5.q
            if (r0 == 0) goto L9
            p.do2 r6 = r0.r(r6, r7)
            return r6
        L9:
            if (r7 == 0) goto Le
            java.util.ArrayList r0 = r5.s
            goto L10
        Le:
            java.util.ArrayList r0 = r5.t
        L10:
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            int r2 = r0.size()
            r3 = 0
        L19:
            if (r3 >= r2) goto L2c
            java.lang.Object r4 = r0.get(r3)
            p.do2 r4 = (p.do2) r4
            if (r4 != 0) goto L24
            return r1
        L24:
            android.view.View r4 = r4.b
            if (r4 != r6) goto L29
            goto L2d
        L29:
            int r3 = r3 + 1
            goto L19
        L2c:
            r3 = -1
        L2d:
            if (r3 < 0) goto L3d
            if (r7 == 0) goto L34
            java.util.ArrayList r6 = r5.t
            goto L36
        L34:
            java.util.ArrayList r6 = r5.s
        L36:
            java.lang.Object r6 = r6.get(r3)
            r1 = r6
            p.do2 r1 = (p.do2) r1
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.wn2.r(android.view.View, boolean):p.do2");
    }

    public String[] t() {
        return null;
    }

    public final String toString() {
        return M("");
    }

    public final do2 u(View view, boolean z) {
        bo2 bo2Var = this.q;
        if (bo2Var != null) {
            return bo2Var.u(view, z);
        }
        return (do2) ((nb) (z ? this.o : this.f204p).e).get(view);
    }

    public boolean v(do2 do2Var, do2 do2Var2) {
        if (do2Var == null || do2Var2 == null) {
            return false;
        }
        String[] strArrT = t();
        if (strArrT == null) {
            Iterator it = do2Var.a.keySet().iterator();
            while (it.hasNext()) {
                if (x(do2Var, do2Var2, (String) it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrT) {
            if (!x(do2Var, do2Var2, str)) {
            }
        }
        return false;
        return true;
    }

    public final boolean w(View view) {
        int id = view.getId();
        ArrayList arrayList = this.m;
        int size = arrayList.size();
        ArrayList arrayList2 = this.n;
        return (size == 0 && arrayList2.size() == 0) || arrayList.contains(Integer.valueOf(id)) || arrayList2.contains(view);
    }

    public void y(View view) {
        if (this.x) {
            return;
        }
        nb nbVarS = s();
        int i = nbVarS.k;
        iv2 iv2Var = dv2.a;
        WindowId windowId = view.getWindowId();
        for (int i2 = i - 1; i2 >= 0; i2--) {
            tn2 tn2Var = (tn2) nbVarS.i(i2);
            if (tn2Var.a != null) {
                qw2 qw2Var = tn2Var.d;
                if ((qw2Var instanceof pw2) && ((pw2) qw2Var).a.equals(windowId)) {
                    ((Animator) nbVarS.f(i2)).pause();
                }
            }
        }
        ArrayList arrayList = this.y;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.y.clone();
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((vn2) arrayList2.get(i3)).c();
            }
        }
        this.w = true;
    }

    public void z(vn2 vn2Var) {
        ArrayList arrayList = this.y;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(vn2Var);
        if (this.y.size() == 0) {
            this.y = null;
        }
    }
}
