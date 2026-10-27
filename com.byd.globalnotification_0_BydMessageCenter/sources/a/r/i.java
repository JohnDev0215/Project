package a.r;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class i implements Cloneable {
    public static final int[] F = {2, 1, 3, 4};
    public static final e G = new a();
    public static ThreadLocal<a.e.a<Animator, b>> H = new ThreadLocal<>();
    public n C;
    public c D;
    public ArrayList<p> t;
    public ArrayList<p> u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1325a = getClass().getName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1326b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1327c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TimeInterpolator f1328d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<Integer> f1329e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<View> f1330f = new ArrayList<>();
    public ArrayList<String> g = null;
    public ArrayList<Class<?>> h = null;
    public ArrayList<Integer> i = null;
    public ArrayList<View> j = null;
    public ArrayList<Class<?>> k = null;
    public ArrayList<String> l = null;
    public ArrayList<Integer> m = null;
    public ArrayList<View> n = null;
    public ArrayList<Class<?>> o = null;
    public q p = new q();
    public q q = new q();
    public o r = null;
    public int[] s = F;
    public boolean v = false;
    public ArrayList<Animator> w = new ArrayList<>();
    public int x = 0;
    public boolean y = false;
    public boolean z = false;
    public ArrayList<d> A = null;
    public ArrayList<Animator> B = new ArrayList<>();
    public e E = G;

    public static class a extends e {
        @Override // a.r.e
        public Path a(float f2, float f3, float f4, float f5) {
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f4, f5);
            return path;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public View f1331a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public String f1332b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public p f1333c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d0 f1334d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public i f1335e;

        public b(View view, String str, i iVar, d0 d0Var, p pVar) {
            this.f1331a = view;
            this.f1332b = str;
            this.f1333c = pVar;
            this.f1334d = d0Var;
            this.f1335e = iVar;
        }
    }

    public static abstract class c {
    }

    public interface d {
        void a(i iVar);

        void b(i iVar);

        void c(i iVar);

        void d(i iVar);

        void e(i iVar);
    }

    public static void c(q qVar, View view, p pVar) {
        qVar.f1351a.put(view, pVar);
        int id = view.getId();
        if (id >= 0) {
            if (qVar.f1352b.indexOfKey(id) >= 0) {
                qVar.f1352b.put(id, null);
            } else {
                qVar.f1352b.put(id, view);
            }
        }
        String strV = a.h.k.q.v(view);
        if (strV != null) {
            if (qVar.f1354d.e(strV) >= 0) {
                qVar.f1354d.put(strV, null);
            } else {
                qVar.f1354d.put(strV, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                a.e.e<View> eVar = qVar.f1353c;
                if (eVar.f460a) {
                    eVar.c();
                }
                if (a.e.d.b(eVar.f461b, eVar.f463d, itemIdAtPosition) < 0) {
                    view.setHasTransientState(true);
                    qVar.f1353c.f(itemIdAtPosition, view);
                    return;
                }
                View viewD = qVar.f1353c.d(itemIdAtPosition);
                if (viewD != null) {
                    viewD.setHasTransientState(false);
                    qVar.f1353c.f(itemIdAtPosition, null);
                }
            }
        }
    }

    public static a.e.a<Animator, b> p() {
        a.e.a<Animator, b> aVar = H.get();
        if (aVar != null) {
            return aVar;
        }
        a.e.a<Animator, b> aVar2 = new a.e.a<>();
        H.set(aVar2);
        return aVar2;
    }

    public static boolean u(p pVar, p pVar2, String str) {
        Object obj = pVar.f1348a.get(str);
        Object obj2 = pVar2.f1348a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return true ^ obj.equals(obj2);
    }

    public i A(long j) {
        this.f1327c = j;
        return this;
    }

    public void B(c cVar) {
        this.D = cVar;
    }

    public i C(TimeInterpolator timeInterpolator) {
        this.f1328d = timeInterpolator;
        return this;
    }

    public void D(e eVar) {
        if (eVar == null) {
            eVar = G;
        }
        this.E = eVar;
    }

    public void E(n nVar) {
        this.C = nVar;
    }

    public i F(long j) {
        this.f1326b = j;
        return this;
    }

    public void G() {
        if (this.x == 0) {
            ArrayList<d> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((d) arrayList2.get(i)).a(this);
                }
            }
            this.z = false;
        }
        this.x++;
    }

    public String H(String str) {
        StringBuilder sbD = b.a.a.a.a.d(str);
        sbD.append(getClass().getSimpleName());
        sbD.append("@");
        sbD.append(Integer.toHexString(hashCode()));
        sbD.append(": ");
        String string = sbD.toString();
        if (this.f1327c != -1) {
            string = string + "dur(" + this.f1327c + ") ";
        }
        if (this.f1326b != -1) {
            string = string + "dly(" + this.f1326b + ") ";
        }
        if (this.f1328d != null) {
            string = string + "interp(" + this.f1328d + ") ";
        }
        if (this.f1329e.size() <= 0 && this.f1330f.size() <= 0) {
            return string;
        }
        String strC = b.a.a.a.a.c(string, "tgts(");
        if (this.f1329e.size() > 0) {
            for (int i = 0; i < this.f1329e.size(); i++) {
                if (i > 0) {
                    strC = b.a.a.a.a.c(strC, ", ");
                }
                StringBuilder sbD2 = b.a.a.a.a.d(strC);
                sbD2.append(this.f1329e.get(i));
                strC = sbD2.toString();
            }
        }
        if (this.f1330f.size() > 0) {
            for (int i2 = 0; i2 < this.f1330f.size(); i2++) {
                if (i2 > 0) {
                    strC = b.a.a.a.a.c(strC, ", ");
                }
                StringBuilder sbD3 = b.a.a.a.a.d(strC);
                sbD3.append(this.f1330f.get(i2));
                strC = sbD3.toString();
            }
        }
        return b.a.a.a.a.c(strC, ")");
    }

    public i a(d dVar) {
        if (this.A == null) {
            this.A = new ArrayList<>();
        }
        this.A.add(dVar);
        return this;
    }

    public i b(View view) {
        this.f1330f.add(view);
        return this;
    }

    public void d() {
        for (int size = this.w.size() - 1; size >= 0; size--) {
            this.w.get(size).cancel();
        }
        ArrayList<d> arrayList = this.A;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        ArrayList arrayList2 = (ArrayList) this.A.clone();
        int size2 = arrayList2.size();
        for (int i = 0; i < size2; i++) {
            ((d) arrayList2.get(i)).b(this);
        }
    }

    public abstract void e(p pVar);

    public final void f(View view, boolean z) {
        if (view == null) {
            return;
        }
        int id = view.getId();
        ArrayList<Integer> arrayList = this.i;
        if (arrayList == null || !arrayList.contains(Integer.valueOf(id))) {
            ArrayList<View> arrayList2 = this.j;
            if (arrayList2 == null || !arrayList2.contains(view)) {
                ArrayList<Class<?>> arrayList3 = this.k;
                if (arrayList3 != null) {
                    int size = arrayList3.size();
                    for (int i = 0; i < size; i++) {
                        if (this.k.get(i).isInstance(view)) {
                            return;
                        }
                    }
                }
                if (view.getParent() instanceof ViewGroup) {
                    p pVar = new p(view);
                    if (z) {
                        h(pVar);
                    } else {
                        e(pVar);
                    }
                    pVar.f1350c.add(this);
                    g(pVar);
                    c(z ? this.p : this.q, view, pVar);
                }
                if (view instanceof ViewGroup) {
                    ArrayList<Integer> arrayList4 = this.m;
                    if (arrayList4 == null || !arrayList4.contains(Integer.valueOf(id))) {
                        ArrayList<View> arrayList5 = this.n;
                        if (arrayList5 == null || !arrayList5.contains(view)) {
                            ArrayList<Class<?>> arrayList6 = this.o;
                            if (arrayList6 != null) {
                                int size2 = arrayList6.size();
                                for (int i2 = 0; i2 < size2; i2++) {
                                    if (this.o.get(i2).isInstance(view)) {
                                        return;
                                    }
                                }
                            }
                            ViewGroup viewGroup = (ViewGroup) view;
                            for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
                                f(viewGroup.getChildAt(i3), z);
                            }
                        }
                    }
                }
            }
        }
    }

    public void g(p pVar) {
    }

    public abstract void h(p pVar);

    public void i(ViewGroup viewGroup, boolean z) {
        ArrayList<String> arrayList;
        ArrayList<Class<?>> arrayList2;
        j(z);
        if ((this.f1329e.size() <= 0 && this.f1330f.size() <= 0) || (((arrayList = this.g) != null && !arrayList.isEmpty()) || ((arrayList2 = this.h) != null && !arrayList2.isEmpty()))) {
            f(viewGroup, z);
            return;
        }
        for (int i = 0; i < this.f1329e.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(this.f1329e.get(i).intValue());
            if (viewFindViewById != null) {
                p pVar = new p(viewFindViewById);
                if (z) {
                    h(pVar);
                } else {
                    e(pVar);
                }
                pVar.f1350c.add(this);
                g(pVar);
                c(z ? this.p : this.q, viewFindViewById, pVar);
            }
        }
        for (int i2 = 0; i2 < this.f1330f.size(); i2++) {
            View view = this.f1330f.get(i2);
            p pVar2 = new p(view);
            if (z) {
                h(pVar2);
            } else {
                e(pVar2);
            }
            pVar2.f1350c.add(this);
            g(pVar2);
            c(z ? this.p : this.q, view, pVar2);
        }
    }

    public void j(boolean z) {
        q qVar;
        if (z) {
            this.p.f1351a.clear();
            this.p.f1352b.clear();
            qVar = this.p;
        } else {
            this.q.f1351a.clear();
            this.q.f1352b.clear();
            qVar = this.q;
        }
        qVar.f1353c.a();
    }

    @Override // 
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public i clone() {
        try {
            i iVar = (i) super.clone();
            iVar.B = new ArrayList<>();
            iVar.p = new q();
            iVar.q = new q();
            iVar.t = null;
            iVar.u = null;
            return iVar;
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Animator l(ViewGroup viewGroup, p pVar, p pVar2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(android.view.ViewGroup r20, a.r.q r21, a.r.q r22, java.util.ArrayList<a.r.p> r23, java.util.ArrayList<a.r.p> r24) {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.r.i.m(android.view.ViewGroup, a.r.q, a.r.q, java.util.ArrayList, java.util.ArrayList):void");
    }

    public void n() {
        int i = this.x - 1;
        this.x = i;
        if (i == 0) {
            ArrayList<d> arrayList = this.A;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.A.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((d) arrayList2.get(i2)).c(this);
                }
            }
            for (int i3 = 0; i3 < this.p.f1353c.g(); i3++) {
                View viewH = this.p.f1353c.h(i3);
                if (viewH != null) {
                    a.h.k.q.a0(viewH, false);
                }
            }
            for (int i4 = 0; i4 < this.q.f1353c.g(); i4++) {
                View viewH2 = this.q.f1353c.h(i4);
                if (viewH2 != null) {
                    a.h.k.q.a0(viewH2, false);
                }
            }
            this.z = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x002e, code lost:
    
        if (r3 < 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0030, code lost:
    
        if (r8 == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0032, code lost:
    
        r6 = r6.u;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0035, code lost:
    
        r6 = r6.t;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
    
        return r6.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public a.r.p o(android.view.View r7, boolean r8) {
        /*
            r6 = this;
            a.r.o r0 = r6.r
            if (r0 == 0) goto L9
            a.r.p r6 = r0.o(r7, r8)
            return r6
        L9:
            if (r8 == 0) goto Le
            java.util.ArrayList<a.r.p> r0 = r6.t
            goto L10
        Le:
            java.util.ArrayList<a.r.p> r0 = r6.u
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
            a.r.p r5 = (a.r.p) r5
            if (r5 != 0) goto L25
            return r1
        L25:
            android.view.View r5 = r5.f1349b
            if (r5 != r7) goto L2b
            r3 = r4
            goto L2e
        L2b:
            int r4 = r4 + 1
            goto L1a
        L2e:
            if (r3 < 0) goto L3e
            if (r8 == 0) goto L35
            java.util.ArrayList<a.r.p> r6 = r6.u
            goto L37
        L35:
            java.util.ArrayList<a.r.p> r6 = r6.t
        L37:
            java.lang.Object r6 = r6.get(r3)
            r1 = r6
            a.r.p r1 = (a.r.p) r1
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: a.r.i.o(android.view.View, boolean):a.r.p");
    }

    public String[] q() {
        return null;
    }

    public p r(View view, boolean z) {
        o oVar = this.r;
        if (oVar != null) {
            return oVar.r(view, z);
        }
        return (z ? this.p : this.q).f1351a.getOrDefault(view, null);
    }

    public boolean s(p pVar, p pVar2) {
        if (pVar == null || pVar2 == null) {
            return false;
        }
        String[] strArrQ = q();
        if (strArrQ == null) {
            Iterator<String> it = pVar.f1348a.keySet().iterator();
            while (it.hasNext()) {
                if (u(pVar, pVar2, it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrQ) {
            if (!u(pVar, pVar2, str)) {
            }
        }
        return false;
        return true;
    }

    public boolean t(View view) {
        ArrayList<Class<?>> arrayList;
        ArrayList<String> arrayList2;
        int id = view.getId();
        ArrayList<Integer> arrayList3 = this.i;
        if (arrayList3 != null && arrayList3.contains(Integer.valueOf(id))) {
            return false;
        }
        ArrayList<View> arrayList4 = this.j;
        if (arrayList4 != null && arrayList4.contains(view)) {
            return false;
        }
        ArrayList<Class<?>> arrayList5 = this.k;
        if (arrayList5 != null) {
            int size = arrayList5.size();
            for (int i = 0; i < size; i++) {
                if (this.k.get(i).isInstance(view)) {
                    return false;
                }
            }
        }
        if (this.l != null && a.h.k.q.v(view) != null && this.l.contains(view.getTransitionName())) {
            return false;
        }
        if ((this.f1329e.size() == 0 && this.f1330f.size() == 0 && (((arrayList = this.h) == null || arrayList.isEmpty()) && ((arrayList2 = this.g) == null || arrayList2.isEmpty()))) || this.f1329e.contains(Integer.valueOf(id)) || this.f1330f.contains(view)) {
            return true;
        }
        ArrayList<String> arrayList6 = this.g;
        if (arrayList6 != null && arrayList6.contains(a.h.k.q.v(view))) {
            return true;
        }
        if (this.h != null) {
            for (int i2 = 0; i2 < this.h.size(); i2++) {
                if (this.h.get(i2).isInstance(view)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        return H("");
    }

    public void v(View view) {
        if (this.z) {
            return;
        }
        a.e.a<Animator, b> aVarP = p();
        int i = aVarP.f490c;
        d0 d0VarC = t.c(view);
        for (int i2 = i - 1; i2 >= 0; i2--) {
            b bVarK = aVarP.k(i2);
            if (bVarK.f1331a != null && d0VarC.equals(bVarK.f1334d)) {
                aVarP.h(i2).pause();
            }
        }
        ArrayList<d> arrayList = this.A;
        if (arrayList != null && arrayList.size() > 0) {
            ArrayList arrayList2 = (ArrayList) this.A.clone();
            int size = arrayList2.size();
            for (int i3 = 0; i3 < size; i3++) {
                ((d) arrayList2.get(i3)).d(this);
            }
        }
        this.y = true;
    }

    public i w(d dVar) {
        ArrayList<d> arrayList = this.A;
        if (arrayList == null) {
            return this;
        }
        arrayList.remove(dVar);
        if (this.A.size() == 0) {
            this.A = null;
        }
        return this;
    }

    public i x(View view) {
        this.f1330f.remove(view);
        return this;
    }

    public void y(View view) {
        if (this.y) {
            if (!this.z) {
                a.e.a<Animator, b> aVarP = p();
                int i = aVarP.f490c;
                d0 d0VarC = t.c(view);
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    b bVarK = aVarP.k(i2);
                    if (bVarK.f1331a != null && d0VarC.equals(bVarK.f1334d)) {
                        aVarP.h(i2).resume();
                    }
                }
                ArrayList<d> arrayList = this.A;
                if (arrayList != null && arrayList.size() > 0) {
                    ArrayList arrayList2 = (ArrayList) this.A.clone();
                    int size = arrayList2.size();
                    for (int i3 = 0; i3 < size; i3++) {
                        ((d) arrayList2.get(i3)).e(this);
                    }
                }
            }
            this.y = false;
        }
    }

    public void z() {
        G();
        a.e.a<Animator, b> aVarP = p();
        for (Animator animator : this.B) {
            if (aVarP.containsKey(animator)) {
                G();
                if (animator != null) {
                    animator.addListener(new j(this, aVarP));
                    long j = this.f1327c;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.f1326b;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.f1328d;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new k(this));
                    animator.start();
                }
            }
        }
        this.B.clear();
        n();
    }
}
