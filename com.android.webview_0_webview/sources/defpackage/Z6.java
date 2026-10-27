package defpackage;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Z6 implements Cloneable {
    public static final int[] X = {2, 1, 3, 4};
    public static final Q6 Y = new T6();
    public static ThreadLocal Z = new ThreadLocal();
    public ArrayList L;
    public ArrayList M;
    public AbstractC0698e7 U;
    public X6 V;
    public String s = getClass().getName();
    public long t = -1;
    public long u = -1;
    public TimeInterpolator v = null;
    public ArrayList w = new ArrayList();
    public ArrayList x = new ArrayList();
    public ArrayList y = null;
    public ArrayList z = null;
    public ArrayList A = null;
    public ArrayList B = null;
    public ArrayList C = null;
    public ArrayList D = null;
    public ArrayList E = null;
    public ArrayList F = null;
    public ArrayList G = null;
    public C1033k7 H = new C1033k7();
    public C1033k7 I = new C1033k7();

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public C0866h7 f12J = null;
    public int[] K = X;
    public boolean N = false;
    public ArrayList O = new ArrayList();
    public int P = 0;
    public boolean Q = false;
    public boolean R = false;
    public ArrayList S = null;
    public ArrayList T = new ArrayList();
    public Q6 W = Y;

    public static void c(C1033k7 c1033k7, View view, C0977j7 c0977j7) {
        c1033k7.a.put(view, c0977j7);
        int id = view.getId();
        if (id >= 0) {
            if (c1033k7.b.indexOfKey(id) >= 0) {
                c1033k7.b.put(id, null);
            } else {
                c1033k7.b.put(id, view);
            }
        }
        String strP = AbstractC1251o3.p(view);
        if (strP != null) {
            if (c1033k7.d.e(strP) >= 0) {
                c1033k7.d.put(strP, null);
            } else {
                c1033k7.d.put(strP, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                C1194n1 c1194n1 = c1033k7.c;
                if (c1194n1.s) {
                    c1194n1.d();
                }
                if (AbstractC1139m1.b(c1194n1.t, c1194n1.v, itemIdAtPosition) < 0) {
                    view.setHasTransientState(true);
                    c1033k7.c.g(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) c1033k7.c.e(itemIdAtPosition);
                if (view2 != null) {
                    view2.setHasTransientState(false);
                    c1033k7.c.g(itemIdAtPosition, null);
                }
            }
        }
    }

    public static C0971j1 o() {
        C0971j1 c0971j1 = (C0971j1) Z.get();
        if (c0971j1 != null) {
            return c0971j1;
        }
        C0971j1 c0971j12 = new C0971j1();
        Z.set(c0971j12);
        return c0971j12;
    }

    public static boolean t(C0977j7 c0977j7, C0977j7 c0977j72, String str) {
        Object obj = c0977j7.a.get(str);
        Object obj2 = c0977j72.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public void A(X6 x6) {
        this.V = x6;
    }

    public Z6 B(TimeInterpolator timeInterpolator) {
        this.v = timeInterpolator;
        return this;
    }

    public void C(Q6 q6) {
        if (q6 == null) {
            this.W = Y;
        } else {
            this.W = q6;
        }
    }

    public void D(AbstractC0698e7 abstractC0698e7) {
        this.U = abstractC0698e7;
    }

    public Z6 E(long j) {
        this.t = j;
        return this;
    }

    public void F() {
        if (this.P == 0) {
            ArrayList arrayList = this.S;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.S.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((Y6) arrayList2.get(i)).c(this);
                }
            }
            this.R = false;
        }
        this.P++;
    }

    public String G(String str) {
        StringBuilder sbF = Z7.f(str);
        sbF.append(getClass().getSimpleName());
        sbF.append("@");
        sbF.append(Integer.toHexString(hashCode()));
        sbF.append(": ");
        String string = sbF.toString();
        if (this.u != -1) {
            string = string + "dur(" + this.u + ") ";
        }
        if (this.t != -1) {
            string = string + "dly(" + this.t + ") ";
        }
        if (this.v != null) {
            string = string + "interp(" + this.v + ") ";
        }
        if (this.w.size() <= 0 && this.x.size() <= 0) {
            return string;
        }
        String strC = Z7.c(string, "tgts(");
        if (this.w.size() > 0) {
            for (int i = 0; i < this.w.size(); i++) {
                if (i > 0) {
                    strC = Z7.c(strC, ", ");
                }
                StringBuilder sbF2 = Z7.f(strC);
                sbF2.append(this.w.get(i));
                strC = sbF2.toString();
            }
        }
        if (this.x.size() > 0) {
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                if (i2 > 0) {
                    strC = Z7.c(strC, ", ");
                }
                StringBuilder sbF3 = Z7.f(strC);
                sbF3.append(this.x.get(i2));
                strC = sbF3.toString();
            }
        }
        return Z7.c(strC, ")");
    }

    public Z6 a(Y6 y6) {
        if (this.S == null) {
            this.S = new ArrayList();
        }
        this.S.add(y6);
        return this;
    }

    public Z6 b(View view) {
        this.x.add(view);
        return this;
    }

    public abstract void d(C0977j7 c0977j7);

    public final void e(View view, boolean z) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            C0977j7 c0977j7 = new C0977j7();
            c0977j7.b = view;
            if (z) {
                g(c0977j7);
            } else {
                d(c0977j7);
            }
            c0977j7.c.add(this);
            f(c0977j7);
            if (z) {
                c(this.H, view, c0977j7);
            } else {
                c(this.I, view, c0977j7);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                e(viewGroup.getChildAt(i), z);
            }
        }
    }

    public void f(C0977j7 c0977j7) {
    }

    public abstract void g(C0977j7 c0977j7);

    public void h(ViewGroup viewGroup, boolean z) {
        i(z);
        if (this.w.size() <= 0 && this.x.size() <= 0) {
            e(viewGroup, z);
            return;
        }
        for (int i = 0; i < this.w.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(((Integer) this.w.get(i)).intValue());
            if (viewFindViewById != null) {
                C0977j7 c0977j7 = new C0977j7();
                c0977j7.b = viewFindViewById;
                if (z) {
                    g(c0977j7);
                } else {
                    d(c0977j7);
                }
                c0977j7.c.add(this);
                f(c0977j7);
                if (z) {
                    c(this.H, viewFindViewById, c0977j7);
                } else {
                    c(this.I, viewFindViewById, c0977j7);
                }
            }
        }
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            View view = (View) this.x.get(i2);
            C0977j7 c0977j72 = new C0977j7();
            c0977j72.b = view;
            if (z) {
                g(c0977j72);
            } else {
                d(c0977j72);
            }
            c0977j72.c.add(this);
            f(c0977j72);
            if (z) {
                c(this.H, view, c0977j72);
            } else {
                c(this.I, view, c0977j72);
            }
        }
    }

    public void i(boolean z) {
        if (z) {
            this.H.a.clear();
            this.H.b.clear();
            this.H.c.b();
        } else {
            this.I.a.clear();
            this.I.b.clear();
            this.I.c.b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public Z6 clone() {
        try {
            Z6 z6 = (Z6) super.clone();
            z6.T = new ArrayList();
            z6.H = new C1033k7();
            z6.I = new C1033k7();
            z6.L = null;
            z6.M = null;
            return z6;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator k(ViewGroup viewGroup, C0977j7 c0977j7, C0977j7 c0977j72) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void l(android.view.ViewGroup r20, defpackage.C1033k7 r21, defpackage.C1033k7 r22, java.util.ArrayList r23, java.util.ArrayList r24) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Z6.l(android.view.ViewGroup, k7, k7, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void m() {
        int i = this.P - 1;
        this.P = i;
        if (i == 0) {
            ArrayList arrayList = this.S;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.S.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((Y6) arrayList2.get(i2)).d(this);
                }
            }
            for (int i3 = 0; i3 < this.H.c.h(); i3++) {
                View view = (View) this.H.c.i(i3);
                if (view != null) {
                    AbstractC1251o3.H(view, false);
                }
            }
            for (int i4 = 0; i4 < this.I.c.h(); i4++) {
                View view2 = (View) this.I.c.i(i4);
                if (view2 != null) {
                    AbstractC1251o3.H(view2, false);
                }
            }
            this.R = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r7 = r6.M;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r7 = r6.L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
    
        return (defpackage.C0977j7) r7.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.C0977j7 n(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            h7 r0 = r6.f12J
            if (r0 == 0) goto L9
            j7 r7 = r0.n(r7, r8)
            return r7
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList r0 = r6.L
            goto L10
        Le:
            java.util.ArrayList r0 = r6.M
        L10:
            r1 = 0
            if (r0 != 0) goto L14
            return r1
        L14:
            int r2 = r0.size()
            r3 = -1
            r4 = 0
        L1a:
            if (r4 >= r2) goto L2e
            java.lang.Object r5 = r0.get(r4)
            j7 r5 = (defpackage.C0977j7) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList r7 = r6.M
            goto L37
        L35:
            java.util.ArrayList r7 = r6.L
        L37:
            java.lang.Object r7 = r7.get(r3)
            r1 = r7
            j7 r1 = (defpackage.C0977j7) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Z6.n(android.view.View, boolean):j7");
    }

    public String[] p() {
        return null;
    }

    public C0977j7 q(View view, boolean z) {
        C0866h7 c0866h7 = this.f12J;
        if (c0866h7 != null) {
            return c0866h7.q(view, z);
        }
        return (C0977j7) (z ? this.H : this.I).a.get(view);
    }

    public boolean r(C0977j7 c0977j7, C0977j7 c0977j72) {
        if (c0977j7 == null || c0977j72 == null) {
            return false;
        }
        String[] strArrP = p();
        if (strArrP == null) {
            Iterator it = c0977j7.a.keySet().iterator();
            while (it.hasNext()) {
                if (t(c0977j7, c0977j72, (String) it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrP) {
            if (!t(c0977j7, c0977j72, str)) {
            }
        }
        return false;
        return true;
    }

    public boolean s(View view) {
        return (this.w.size() == 0 && this.x.size() == 0) || this.w.contains(Integer.valueOf(view.getId())) || this.x.contains(view);
    }

    public String toString() {
        return G("");
    }

    public void u(View view) {
        if (this.R) {
            return;
        }
        C0971j1 c0971j1O = o();
        int i = c0971j1O.u;
        B7 b7B = AbstractC1419r7.b(view);
        for (int i2 = i - 1; i2 >= 0; i2--) {
            W6 w6 = (W6) c0971j1O.j(i2);
            if (w6.a != null && b7B.equals(w6.d)) {
                ((Animator) c0971j1O.h(i2)).pause();
            }
        }
        ArrayList arrayList = this.S;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.S.clone();
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((Y6) arrayList2.get(i3)).a(this);
            }
        }
        this.Q = true;
    }

    public Z6 v(Y6 y6) {
        ArrayList arrayList = this.S;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(y6);
        if (this.S.size() == 0) {
            this.S = null;
        }
        return this;
    }

    public Z6 w(View view) {
        this.x.remove(view);
        return this;
    }

    public void x(View view) {
        if (this.Q) {
            if (!this.R) {
                C0971j1 c0971j1O = o();
                int i = c0971j1O.u;
                B7 b7B = AbstractC1419r7.b(view);
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    W6 w6 = (W6) c0971j1O.j(i2);
                    if (w6.a != null && b7B.equals(w6.d)) {
                        ((Animator) c0971j1O.h(i2)).resume();
                    }
                }
                ArrayList arrayList = this.S;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.S.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((Y6) arrayList2.get(i3)).b(this);
                    }
                }
            }
            this.Q = false;
        }
    }

    public void y() {
        F();
        C0971j1 c0971j1O = o();
        for (Animator animator : this.T) {
            if (c0971j1O.containsKey(animator)) {
                F();
                if (animator != null) {
                    animator.addListener(new U6(this, c0971j1O));
                    long j = this.u;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.t;
                    if (j2 >= 0) {
                        animator.setStartDelay(j2);
                    }
                    TimeInterpolator timeInterpolator = this.v;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new V6(this));
                    animator.start();
                }
            }
        }
        this.T.clear();
        m();
    }

    public Z6 z(long j) {
        this.u = j;
        return this;
    }
}
