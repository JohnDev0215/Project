package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.InflateException;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import defpackage.C0173a;
import defpackage.af3;
import defpackage.bf3;
import defpackage.df3;
import defpackage.fh3;
import defpackage.gq2;
import defpackage.in3;
import defpackage.m8;
import defpackage.mt3;
import defpackage.no3;
import defpackage.nt3;
import defpackage.om3;
import defpackage.pk;
import defpackage.sa1;
import defpackage.sv2;
import defpackage.vd1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class Transition implements Cloneable {
    public final String e;
    public long f;
    public long g;
    public TimeInterpolator h;
    public final ArrayList<Integer> i;
    public final ArrayList<View> j;
    public pk k;
    public pk l;
    public TransitionSet m;
    public int[] n;
    public ArrayList<df3> o;
    public ArrayList<df3> p;
    public final ArrayList<Animator> q;
    public int r;
    public boolean s;
    public boolean t;
    public ArrayList<d> u;
    public ArrayList<Animator> v;
    public vd1 w;
    public c x;
    public PathMotion y;
    public static final int[] z = {2, 1, 3, 4};
    public static final a A = new a();
    public static final ThreadLocal<m8<Animator, b>> B = new ThreadLocal<>();

    public class a extends PathMotion {
        @Override // androidx.transition.PathMotion
        public final Path a(float f, float f2, float f3, float f4) {
            Path path = new Path();
            path.moveTo(f, f2);
            path.lineTo(f3, f4);
            return path;
        }
    }

    public static class b {
        public final View a;
        public final String b;
        public final df3 c;
        public final nt3 d;
        public final Transition e;

        public b(View view, String str, Transition transition, mt3 mt3Var, df3 df3Var) {
            this.a = view;
            this.b = str;
            this.c = df3Var;
            this.d = mt3Var;
            this.e = transition;
        }
    }

    public static abstract class c {
        public abstract Rect a();
    }

    public interface d {
        void a();

        void b(Transition transition);

        void c();

        void d(Transition transition);

        void e();
    }

    public Transition() {
        this.e = getClass().getName();
        this.f = -1L;
        this.g = -1L;
        this.h = null;
        this.i = new ArrayList<>();
        this.j = new ArrayList<>();
        this.k = new pk(1);
        this.l = new pk(1);
        this.m = null;
        this.n = z;
        this.q = new ArrayList<>();
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = null;
        this.v = new ArrayList<>();
        this.y = A;
    }

    public static boolean A(df3 df3Var, df3 df3Var2, String str) {
        Object obj = df3Var.a.get(str);
        Object obj2 = df3Var2.a.get(str);
        if (obj == null && obj2 == null) {
            return false;
        }
        if (obj == null || obj2 == null) {
            return true;
        }
        return !obj.equals(obj2);
    }

    public static void g(pk pkVar, View view, df3 df3Var) {
        ((m8) pkVar.a).put(view, df3Var);
        int id = view.getId();
        if (id >= 0) {
            SparseArray sparseArray = (SparseArray) pkVar.b;
            if (sparseArray.indexOfKey(id) >= 0) {
                sparseArray.put(id, null);
            } else {
                sparseArray.put(id, view);
            }
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        String strF = om3.i.f(view);
        if (strF != null) {
            if (((m8) pkVar.d).containsKey(strF)) {
                ((m8) pkVar.d).put(strF, null);
            } else {
                ((m8) pkVar.d).put(strF, view);
            }
        }
        if (view.getParent() instanceof ListView) {
            ListView listView = (ListView) view.getParent();
            if (listView.getAdapter().hasStableIds()) {
                long itemIdAtPosition = listView.getItemIdAtPosition(listView.getPositionForView(view));
                sa1 sa1Var = (sa1) pkVar.c;
                if (sa1Var.i(itemIdAtPosition) < 0) {
                    om3.d.o(view, true);
                    sa1Var.k(itemIdAtPosition, view);
                    return;
                }
                View view2 = (View) sa1Var.g(itemIdAtPosition);
                if (view2 != null) {
                    om3.d.o(view2, false);
                    sa1Var.k(itemIdAtPosition, null);
                }
            }
        }
    }

    public static m8<Animator, b> u() {
        ThreadLocal<m8<Animator, b>> threadLocal = B;
        m8<Animator, b> m8Var = threadLocal.get();
        if (m8Var != null) {
            return m8Var;
        }
        m8<Animator, b> m8Var2 = new m8<>();
        threadLocal.set(m8Var2);
        return m8Var2;
    }

    public void B(View view) {
        if (this.t) {
            return;
        }
        ArrayList<Animator> arrayList = this.q;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            arrayList.get(size).pause();
        }
        ArrayList<d> arrayList2 = this.u;
        if (arrayList2 != null && arrayList2.size() > 0) {
            ArrayList arrayList3 = (ArrayList) this.u.clone();
            int size2 = arrayList3.size();
            for (int i = 0; i < size2; i++) {
                ((d) arrayList3.get(i)).a();
            }
        }
        this.s = true;
    }

    public void C(d dVar) {
        ArrayList<d> arrayList = this.u;
        if (arrayList == null) {
            return;
        }
        arrayList.remove(dVar);
        if (this.u.size() == 0) {
            this.u = null;
        }
    }

    public void D(View view) {
        this.j.remove(view);
    }

    public void E(ViewGroup viewGroup) {
        if (this.s) {
            if (!this.t) {
                ArrayList<Animator> arrayList = this.q;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    } else {
                        arrayList.get(size).resume();
                    }
                }
                ArrayList<d> arrayList2 = this.u;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    ArrayList arrayList3 = (ArrayList) this.u.clone();
                    int size2 = arrayList3.size();
                    for (int i = 0; i < size2; i++) {
                        ((d) arrayList3.get(i)).e();
                    }
                }
            }
            this.s = false;
        }
    }

    public void F() {
        O();
        m8<Animator, b> m8VarU = u();
        for (Animator animator : this.v) {
            if (m8VarU.containsKey(animator)) {
                O();
                if (animator != null) {
                    animator.addListener(new af3(this, m8VarU));
                    long j = this.g;
                    if (j >= 0) {
                        animator.setDuration(j);
                    }
                    long j2 = this.f;
                    if (j2 >= 0) {
                        animator.setStartDelay(animator.getStartDelay() + j2);
                    }
                    TimeInterpolator timeInterpolator = this.h;
                    if (timeInterpolator != null) {
                        animator.setInterpolator(timeInterpolator);
                    }
                    animator.addListener(new bf3(this));
                    animator.start();
                }
            }
        }
        this.v.clear();
        r();
    }

    public void H(long j) {
        this.g = j;
    }

    public void I(c cVar) {
        this.x = cVar;
    }

    public void J(TimeInterpolator timeInterpolator) {
        this.h = timeInterpolator;
    }

    public void K(PathMotion pathMotion) {
        if (pathMotion == null) {
            this.y = A;
        } else {
            this.y = pathMotion;
        }
    }

    public void M(vd1 vd1Var) {
        this.w = vd1Var;
    }

    public void N(long j) {
        this.f = j;
    }

    public final void O() {
        if (this.r == 0) {
            ArrayList<d> arrayList = this.u;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.u.clone();
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    ((d) arrayList2.get(i)).b(this);
                }
            }
            this.t = false;
        }
        this.r++;
    }

    public String P(String str) {
        StringBuilder sbJ = C0173a.j(str);
        sbJ.append(getClass().getSimpleName());
        sbJ.append("@");
        sbJ.append(Integer.toHexString(hashCode()));
        sbJ.append(": ");
        String string = sbJ.toString();
        if (this.g != -1) {
            StringBuilder sbK = C0173a.k(string, "dur(");
            sbK.append(this.g);
            sbK.append(") ");
            string = sbK.toString();
        }
        if (this.f != -1) {
            StringBuilder sbK2 = C0173a.k(string, "dly(");
            sbK2.append(this.f);
            sbK2.append(") ");
            string = sbK2.toString();
        }
        if (this.h != null) {
            StringBuilder sbK3 = C0173a.k(string, "interp(");
            sbK3.append(this.h);
            sbK3.append(") ");
            string = sbK3.toString();
        }
        ArrayList<Integer> arrayList = this.i;
        int size = arrayList.size();
        ArrayList<View> arrayList2 = this.j;
        if (size <= 0 && arrayList2.size() <= 0) {
            return string;
        }
        String strF = gq2.f(string, "tgts(");
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i > 0) {
                    strF = gq2.f(strF, ", ");
                }
                StringBuilder sbJ2 = C0173a.j(strF);
                sbJ2.append(arrayList.get(i));
                strF = sbJ2.toString();
            }
        }
        if (arrayList2.size() > 0) {
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (i2 > 0) {
                    strF = gq2.f(strF, ", ");
                }
                StringBuilder sbJ3 = C0173a.j(strF);
                sbJ3.append(arrayList2.get(i2));
                strF = sbJ3.toString();
            }
        }
        return gq2.f(strF, ")");
    }

    public void b(d dVar) {
        if (this.u == null) {
            this.u = new ArrayList<>();
        }
        this.u.add(dVar);
    }

    public void e(View view) {
        this.j.add(view);
    }

    public void h() {
        ArrayList<Animator> arrayList = this.q;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            } else {
                arrayList.get(size).cancel();
            }
        }
        ArrayList<d> arrayList2 = this.u;
        if (arrayList2 == null || arrayList2.size() <= 0) {
            return;
        }
        ArrayList arrayList3 = (ArrayList) this.u.clone();
        int size2 = arrayList3.size();
        for (int i = 0; i < size2; i++) {
            ((d) arrayList3.get(i)).c();
        }
    }

    public abstract void i(df3 df3Var);

    public final void j(View view, boolean z2) {
        if (view == null) {
            return;
        }
        view.getId();
        if (view.getParent() instanceof ViewGroup) {
            df3 df3Var = new df3(view);
            if (z2) {
                l(df3Var);
            } else {
                i(df3Var);
            }
            df3Var.c.add(this);
            k(df3Var);
            if (z2) {
                g(this.k, view, df3Var);
            } else {
                g(this.l, view, df3Var);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                j(viewGroup.getChildAt(i), z2);
            }
        }
    }

    public void k(df3 df3Var) {
        boolean z2;
        if (this.w != null) {
            HashMap map = df3Var.a;
            if (map.isEmpty()) {
                return;
            }
            this.w.getClass();
            String[] strArr = no3.k;
            int i = 0;
            while (true) {
                if (i >= 2) {
                    z2 = true;
                    break;
                } else {
                    if (!map.containsKey(strArr[i])) {
                        z2 = false;
                        break;
                    }
                    i++;
                }
            }
            if (z2) {
                return;
            }
            ((no3) this.w).getClass();
            Integer numValueOf = (Integer) map.get("android:visibility:visibility");
            View view = df3Var.b;
            if (numValueOf == null) {
                numValueOf = Integer.valueOf(view.getVisibility());
            }
            map.put("android:visibilityPropagation:visibility", numValueOf);
            int[] iArr = {iRound, 0};
            view.getLocationOnScreen(iArr);
            int iRound = Math.round(view.getTranslationX()) + iArr[0];
            iArr[0] = (view.getWidth() / 2) + iRound;
            int iRound2 = Math.round(view.getTranslationY()) + iArr[1];
            iArr[1] = iRound2;
            iArr[1] = (view.getHeight() / 2) + iRound2;
            map.put("android:visibilityPropagation:center", iArr);
        }
    }

    public abstract void l(df3 df3Var);

    public final void m(ViewGroup viewGroup, boolean z2) {
        n(z2);
        ArrayList<Integer> arrayList = this.i;
        int size = arrayList.size();
        ArrayList<View> arrayList2 = this.j;
        if (size <= 0 && arrayList2.size() <= 0) {
            j(viewGroup, z2);
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            View viewFindViewById = viewGroup.findViewById(arrayList.get(i).intValue());
            if (viewFindViewById != null) {
                df3 df3Var = new df3(viewFindViewById);
                if (z2) {
                    l(df3Var);
                } else {
                    i(df3Var);
                }
                df3Var.c.add(this);
                k(df3Var);
                if (z2) {
                    g(this.k, viewFindViewById, df3Var);
                } else {
                    g(this.l, viewFindViewById, df3Var);
                }
            }
        }
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            View view = arrayList2.get(i2);
            df3 df3Var2 = new df3(view);
            if (z2) {
                l(df3Var2);
            } else {
                i(df3Var2);
            }
            df3Var2.c.add(this);
            k(df3Var2);
            if (z2) {
                g(this.k, view, df3Var2);
            } else {
                g(this.l, view, df3Var2);
            }
        }
    }

    public final void n(boolean z2) {
        if (z2) {
            ((m8) this.k.a).clear();
            ((SparseArray) this.k.b).clear();
            ((sa1) this.k.c).b();
        } else {
            ((m8) this.l.a).clear();
            ((SparseArray) this.l.b).clear();
            ((sa1) this.l.c).b();
        }
    }

    @Override // 
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public Transition clone() {
        try {
            Transition transition = (Transition) super.clone();
            transition.v = new ArrayList<>();
            transition.k = new pk(1);
            transition.l = new pk(1);
            transition.o = null;
            transition.p = null;
            return transition;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public Animator p(ViewGroup viewGroup, df3 df3Var, df3 df3Var2) {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void q(android.view.ViewGroup r20, defpackage.pk r21, defpackage.pk r22, java.util.ArrayList<defpackage.df3> r23, java.util.ArrayList<defpackage.df3> r24) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.q(android.view.ViewGroup, pk, pk, java.util.ArrayList, java.util.ArrayList):void");
    }

    public final void r() {
        int i = this.r - 1;
        this.r = i;
        if (i == 0) {
            ArrayList<d> arrayList = this.u;
            if (arrayList != null && arrayList.size() > 0) {
                ArrayList arrayList2 = (ArrayList) this.u.clone();
                int size = arrayList2.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((d) arrayList2.get(i2)).d(this);
                }
            }
            for (int i3 = 0; i3 < ((sa1) this.k.c).m(); i3++) {
                View view = (View) ((sa1) this.k.c).n(i3);
                if (view != null) {
                    WeakHashMap<View, in3> weakHashMap = om3.a;
                    om3.d.o(view, false);
                }
            }
            for (int i4 = 0; i4 < ((sa1) this.l.c).m(); i4++) {
                View view2 = (View) ((sa1) this.l.c).n(i4);
                if (view2 != null) {
                    WeakHashMap<View, in3> weakHashMap2 = om3.a;
                    om3.d.o(view2, false);
                }
            }
            this.t = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x002d, code lost:
    
        if (r3 < 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x002f, code lost:
    
        if (r7 == false) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0031, code lost:
    
        r5 = r5.p;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0034, code lost:
    
        r5 = r5.o;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003d, code lost:
    
        return r5.get(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.df3 s(android.view.View r6, boolean r7) {
        /*
            r5 = this;
            androidx.transition.TransitionSet r0 = r5.m
            if (r0 == 0) goto L9
            df3 r5 = r0.s(r6, r7)
            return r5
        L9:
            if (r7 == 0) goto Le
            java.util.ArrayList<df3> r0 = r5.o
            goto L10
        Le:
            java.util.ArrayList<df3> r0 = r5.p
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
            df3 r4 = (defpackage.df3) r4
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
            java.util.ArrayList<df3> r5 = r5.p
            goto L36
        L34:
            java.util.ArrayList<df3> r5 = r5.o
        L36:
            java.lang.Object r5 = r5.get(r3)
            r1 = r5
            df3 r1 = (defpackage.df3) r1
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Transition.s(android.view.View, boolean):df3");
    }

    public final String toString() {
        return P("");
    }

    public String[] v() {
        return null;
    }

    public final df3 x(View view, boolean z2) {
        TransitionSet transitionSet = this.m;
        if (transitionSet != null) {
            return transitionSet.x(view, z2);
        }
        return (df3) ((m8) (z2 ? this.k : this.l).a).get(view);
    }

    public boolean y(df3 df3Var, df3 df3Var2) {
        if (df3Var == null || df3Var2 == null) {
            return false;
        }
        String[] strArrV = v();
        if (strArrV == null) {
            Iterator it = df3Var.a.keySet().iterator();
            while (it.hasNext()) {
                if (A(df3Var, df3Var2, (String) it.next())) {
                }
            }
            return false;
        }
        for (String str : strArrV) {
            if (!A(df3Var, df3Var2, str)) {
            }
        }
        return false;
        return true;
    }

    public final boolean z(View view) {
        int id = view.getId();
        ArrayList<Integer> arrayList = this.i;
        int size = arrayList.size();
        ArrayList<View> arrayList2 = this.j;
        return (size == 0 && arrayList2.size() == 0) || arrayList.contains(Integer.valueOf(id)) || arrayList2.contains(view);
    }

    @SuppressLint({"RestrictedApi"})
    public Transition(Context context, AttributeSet attributeSet) {
        boolean z2;
        this.e = getClass().getName();
        this.f = -1L;
        this.g = -1L;
        this.h = null;
        this.i = new ArrayList<>();
        this.j = new ArrayList<>();
        this.k = new pk(1);
        this.l = new pk(1);
        this.m = null;
        int[] iArr = z;
        this.n = iArr;
        this.q = new ArrayList<>();
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = null;
        this.v = new ArrayList<>();
        this.y = A;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.a);
        XmlResourceParser xmlResourceParser = (XmlResourceParser) attributeSet;
        long jD = fh3.d(typedArrayObtainStyledAttributes, xmlResourceParser, "duration", 1, -1);
        if (jD >= 0) {
            H(jD);
        }
        long jD2 = fh3.d(typedArrayObtainStyledAttributes, xmlResourceParser, "startDelay", 2, -1);
        if (jD2 > 0) {
            N(jD2);
        }
        int resourceId = !fh3.g(xmlResourceParser, "interpolator") ? 0 : typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId > 0) {
            J(AnimationUtils.loadInterpolator(context, resourceId));
        }
        String strE = fh3.e(typedArrayObtainStyledAttributes, xmlResourceParser, "matchOrder", 3);
        if (strE != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(strE, ",");
            int[] iArr2 = new int[stringTokenizer.countTokens()];
            int i = 0;
            while (stringTokenizer.hasMoreTokens()) {
                String strTrim = stringTokenizer.nextToken().trim();
                if ("id".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 3;
                } else if ("instance".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 1;
                } else if ("name".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 2;
                } else if ("itemId".equalsIgnoreCase(strTrim)) {
                    iArr2[i] = 4;
                } else if (strTrim.isEmpty()) {
                    int[] iArr3 = new int[iArr2.length - 1];
                    System.arraycopy(iArr2, 0, iArr3, 0, i);
                    i--;
                    iArr2 = iArr3;
                } else {
                    throw new InflateException(gq2.g("Unknown match type in matchOrder: '", strTrim, "'"));
                }
                i++;
            }
            if (iArr2.length == 0) {
                this.n = iArr;
            } else {
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    int i3 = iArr2[i2];
                    if (!(i3 >= 1 && i3 <= 4)) {
                        throw new IllegalArgumentException("matches contains invalid value");
                    }
                    int i4 = 0;
                    while (true) {
                        if (i4 >= i2) {
                            z2 = false;
                            break;
                        } else {
                            if (iArr2[i4] == i3) {
                                z2 = true;
                                break;
                            }
                            i4++;
                        }
                    }
                    if (z2) {
                        throw new IllegalArgumentException("matches contains a duplicate value");
                    }
                }
                this.n = (int[]) iArr2.clone();
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
