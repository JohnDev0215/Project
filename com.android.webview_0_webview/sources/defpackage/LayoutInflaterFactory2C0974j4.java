package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import androidx.fragment.app.BackStackState;
import androidx.fragment.app.FragmentManagerState;
import androidx.fragment.app.FragmentState;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: renamed from: j4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class LayoutInflaterFactory2C0974j4 extends S3 implements LayoutInflater.Factory2 {
    public static boolean U = false;
    public static Field V;
    public static final Interpolator W = new DecelerateInterpolator(2.5f);
    public static final Interpolator X = new DecelerateInterpolator(1.5f);
    public ArrayList A;
    public R3 D;
    public P3 E;
    public K3 F;
    public K3 G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f24J;
    public boolean K;
    public boolean L;
    public ArrayList M;
    public ArrayList N;
    public ArrayList O;
    public ArrayList R;
    public C1030k4 S;
    public ArrayList s;
    public boolean t;
    public SparseArray w;
    public ArrayList x;
    public ArrayList y;
    public ArrayList z;
    public int u = 0;
    public final ArrayList v = new ArrayList();
    public final CopyOnWriteArrayList B = new CopyOnWriteArrayList();
    public int C = 0;
    public Bundle P = null;
    public SparseArray Q = null;
    public Runnable T = new T3(this);

    static {
        new AccelerateInterpolator(2.5f);
        new AccelerateInterpolator(1.5f);
    }

    public static Animation.AnimationListener U(Animation animation) {
        try {
            if (V == null) {
                Field declaredField = Animation.class.getDeclaredField("mListener");
                V = declaredField;
                declaredField.setAccessible(true);
            }
            return (Animation.AnimationListener) V.get(animation);
        } catch (IllegalAccessException e) {
            Log.e("FragmentManager", "Cannot access Animation's mListener field", e);
            return null;
        } catch (NoSuchFieldException e2) {
            Log.e("FragmentManager", "No field with the name mListener is found in Animation class", e2);
            return null;
        }
    }

    public static C0528b4 Y(float f, float f2, float f3, float f4) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(W);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f3, f4);
        alphaAnimation.setInterpolator(X);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new C0528b4(animationSet);
    }

    public static boolean Z(Animator animator) {
        if (animator == null) {
            return false;
        }
        if (animator instanceof ValueAnimator) {
            for (PropertyValuesHolder propertyValuesHolder : ((ValueAnimator) animator).getValues()) {
                if ("alpha".equals(propertyValuesHolder.getPropertyName())) {
                    return true;
                }
            }
        } else if (animator instanceof AnimatorSet) {
            ArrayList<Animator> childAnimations = ((AnimatorSet) animator).getChildAnimations();
            for (int i = 0; i < childAnimations.size(); i++) {
                if (Z(childAnimations.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int k0(int i) {
        if (i == 4097) {
            return 8194;
        }
        if (i != 4099) {
            return i != 8194 ? 0 : 4097;
        }
        return 4099;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void p0(android.view.View r5, defpackage.C0528b4 r6) {
        /*
            if (r5 == 0) goto L68
            if (r6 != 0) goto L5
            goto L68
        L5:
            int r0 = r5.getLayerType()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L43
            boolean r0 = defpackage.AbstractC1251o3.s(r5)
            if (r0 == 0) goto L43
            android.view.animation.Animation r0 = r6.a
            boolean r3 = r0 instanceof android.view.animation.AlphaAnimation
            if (r3 == 0) goto L1a
            goto L33
        L1a:
            boolean r3 = r0 instanceof android.view.animation.AnimationSet
            if (r3 == 0) goto L3a
            android.view.animation.AnimationSet r0 = (android.view.animation.AnimationSet) r0
            java.util.List r0 = r0.getAnimations()
            r3 = 0
        L25:
            int r4 = r0.size()
            if (r3 >= r4) goto L38
            java.lang.Object r4 = r0.get(r3)
            boolean r4 = r4 instanceof android.view.animation.AlphaAnimation
            if (r4 == 0) goto L35
        L33:
            r0 = 1
            goto L40
        L35:
            int r3 = r3 + 1
            goto L25
        L38:
            r0 = 0
            goto L40
        L3a:
            android.animation.Animator r0 = r6.b
            boolean r0 = Z(r0)
        L40:
            if (r0 == 0) goto L43
            goto L44
        L43:
            r1 = 0
        L44:
            if (r1 == 0) goto L68
            android.animation.Animator r0 = r6.b
            if (r0 == 0) goto L53
            c4 r6 = new c4
            r6.<init>(r5)
            r0.addListener(r6)
            goto L68
        L53:
            android.view.animation.Animation r0 = r6.a
            android.view.animation.Animation$AnimationListener r0 = U(r0)
            r1 = 2
            r2 = 0
            r5.setLayerType(r1, r2)
            android.view.animation.Animation r6 = r6.a
            Z3 r1 = new Z3
            r1.<init>(r5, r0)
            r6.setAnimationListener(r1)
        L68:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflaterFactory2C0974j4.p0(android.view.View, b4):void");
    }

    public static void r0(C1030k4 c1030k4) {
        if (c1030k4 == null) {
            return;
        }
        List list = c1030k4.a;
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((K3) it.next()).U = true;
            }
        }
        List list2 = c1030k4.b;
        if (list2 != null) {
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                r0((C1030k4) it2.next());
            }
        }
    }

    public void A(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.A(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void B(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.B(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void C(K3 k3, View view, Bundle bundle, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.C(k3, view, bundle, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void D(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.D(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public boolean E(MenuItem menuItem) {
        if (this.C < 1) {
            return false;
        }
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null && k3.U(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void F(Menu menu) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        if (this.C < 1) {
            return;
        }
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null && !k3.R && (layoutInflaterFactory2C0974j4 = k3.K) != null) {
                layoutInflaterFactory2C0974j4.F(menu);
            }
        }
    }

    public void G(boolean z) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        int size = this.v.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            K3 k3 = (K3) this.v.get(size);
            if (k3 != null && (layoutInflaterFactory2C0974j4 = k3.K) != null) {
                layoutInflaterFactory2C0974j4.G(z);
            }
        }
    }

    public boolean H(Menu menu) {
        if (this.C < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null && k3.V(menu)) {
                z = true;
            }
        }
        return z;
    }

    public void I() {
        this.I = false;
        this.f24J = false;
        K(4);
    }

    public void J() {
        this.I = false;
        this.f24J = false;
        K(3);
    }

    public final void K(int i) {
        try {
            this.t = true;
            b0(i, false);
            this.t = false;
            O();
        } catch (Throwable th) {
            this.t = false;
            throw th;
        }
    }

    public void L(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        int size5;
        String strC = Z7.c(str, "    ");
        SparseArray sparseArray = this.w;
        if (sparseArray != null && (size5 = sparseArray.size()) > 0) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(":");
            for (int i = 0; i < size5; i++) {
                K3 k3 = (K3) this.w.valueAt(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(k3);
                if (k3 != null) {
                    k3.c(strC, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size6 = this.v.size();
        if (size6 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size6; i2++) {
                K3 k32 = (K3) this.v.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(k32.toString());
            }
        }
        ArrayList arrayList = this.y;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i3 = 0; i3 < size4; i3++) {
                K3 k33 = (K3) this.y.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(k33.toString());
            }
        }
        ArrayList arrayList2 = this.x;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i4 = 0; i4 < size3; i4++) {
                C3 c3 = (C3) this.x.get(i4);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i4);
                printWriter.print(": ");
                printWriter.println(c3.toString());
                c3.g(strC, printWriter, true);
            }
        }
        synchronized (this) {
            if (this.z != null && (size2 = this.z.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i5 = 0; i5 < size2; i5++) {
                    Object obj = (C3) this.z.get(i5);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i5);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            if (this.A != null && this.A.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.A.toArray()));
            }
        }
        ArrayList arrayList3 = this.s;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i6 = 0; i6 < size; i6++) {
                Object obj2 = (InterfaceC0807g4) this.s.get(i6);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i6);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.D);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.E);
        if (this.F != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.F);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.C);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.I);
        printWriter.print(" mStopped=");
        printWriter.print(this.f24J);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.K);
        if (this.H) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.H);
        }
    }

    public void M(InterfaceC0807g4 interfaceC0807g4, boolean z) {
        if (!z) {
            e();
        }
        synchronized (this) {
            if (!this.K && this.D != null) {
                if (this.s == null) {
                    this.s = new ArrayList();
                }
                this.s.add(interfaceC0807g4);
                o0();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public final void N(boolean z) {
        if (this.t) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.D == null) {
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        if (Looper.myLooper() != this.D.c.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            e();
        }
        if (this.M == null) {
            this.M = new ArrayList();
            this.N = new ArrayList();
        }
        this.t = true;
        try {
            Q(null, null);
        } finally {
            this.t = false;
        }
    }

    public boolean O() {
        boolean zA;
        N(true);
        boolean z = false;
        while (true) {
            ArrayList arrayList = this.M;
            ArrayList arrayList2 = this.N;
            synchronized (this) {
                if (this.s == null || this.s.size() == 0) {
                    zA = false;
                } else {
                    int size = this.s.size();
                    zA = false;
                    for (int i = 0; i < size; i++) {
                        zA |= ((InterfaceC0807g4) this.s.get(i)).a(arrayList, arrayList2);
                    }
                    this.s.clear();
                    this.D.c.removeCallbacks(this.T);
                }
            }
            if (!zA) {
                break;
            }
            this.t = true;
            try {
                i0(this.M, this.N);
                f();
                z = true;
            } catch (Throwable th) {
                f();
                throw th;
            }
        }
        if (this.L) {
            this.L = false;
            s0();
        }
        d();
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0142  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void P(java.util.ArrayList r20, java.util.ArrayList r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 592
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflaterFactory2C0974j4.P(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }

    public final void Q(ArrayList arrayList, ArrayList arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList arrayList3 = this.R;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            C0919i4 c0919i4 = (C0919i4) this.R.get(i);
            if (arrayList == null || c0919i4.a || (iIndexOf2 = arrayList.indexOf(c0919i4.b)) == -1 || !((Boolean) arrayList2.get(iIndexOf2)).booleanValue()) {
                if ((c0919i4.c == 0) || (arrayList != null && c0919i4.b.k(arrayList, 0, arrayList.size()))) {
                    this.R.remove(i);
                    i--;
                    size--;
                    if (arrayList == null || c0919i4.a || (iIndexOf = arrayList.indexOf(c0919i4.b)) == -1 || !((Boolean) arrayList2.get(iIndexOf)).booleanValue()) {
                        c0919i4.a();
                    } else {
                        C3 c3 = c0919i4.b;
                        c3.a.g(c3, c0919i4.a, false, false);
                    }
                }
            } else {
                C3 c32 = c0919i4.b;
                c32.a.g(c32, c0919i4.a, false, false);
            }
            i++;
        }
    }

    public K3 R(int i) {
        for (int size = this.v.size() - 1; size >= 0; size--) {
            K3 k3 = (K3) this.v.get(size);
            if (k3 != null && k3.O == i) {
                return k3;
            }
        }
        SparseArray sparseArray = this.w;
        if (sparseArray == null) {
            return null;
        }
        for (int size2 = sparseArray.size() - 1; size2 >= 0; size2--) {
            K3 k32 = (K3) this.w.valueAt(size2);
            if (k32 != null && k32.O == i) {
                return k32;
            }
        }
        return null;
    }

    public K3 S(String str) {
        int size = this.v.size();
        while (true) {
            size--;
            if (size >= 0) {
                K3 k3 = (K3) this.v.get(size);
                if (k3 != null && str.equals(k3.Q)) {
                    return k3;
                }
            } else {
                SparseArray sparseArray = this.w;
                if (sparseArray == null) {
                    return null;
                }
                int size2 = sparseArray.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        return null;
                    }
                    K3 k32 = (K3) this.w.valueAt(size2);
                    if (k32 != null && str.equals(k32.Q)) {
                        return k32;
                    }
                }
            }
        }
    }

    public K3 T(String str) {
        K3 k3E;
        SparseArray sparseArray = this.w;
        if (sparseArray == null || str == null) {
            return null;
        }
        int size = sparseArray.size();
        while (true) {
            size--;
            if (size < 0) {
                return null;
            }
            K3 k3 = (K3) this.w.valueAt(size);
            if (k3 != null && (k3E = k3.e(str)) != null) {
                return k3E;
            }
        }
    }

    public boolean V() {
        return this.I || this.f24J;
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public defpackage.C0528b4 W(defpackage.K3 r7, int r8, boolean r9, int r10) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflaterFactory2C0974j4.W(K3, int, boolean, int):b4");
    }

    public void X(K3 k3) {
        if (k3.v >= 0) {
            return;
        }
        int i = this.u;
        this.u = i + 1;
        k3.c0(i, this.F);
        if (this.w == null) {
            this.w = new SparseArray();
        }
        this.w.put(k3.v, k3);
    }

    public final void a(C1083l1 c1083l1) {
        int i = this.C;
        if (i < 1) {
            return;
        }
        int iMin = Math.min(i, 3);
        int size = this.v.size();
        for (int i2 = 0; i2 < size; i2++) {
            K3 k3 = (K3) this.v.get(i2);
            if (k3.s < iMin) {
                c0(k3, iMin, k3.n(), k3.o(), false);
                if (k3.Z != null && !k3.R && k3.e0) {
                    c1083l1.add(k3);
                }
            }
        }
    }

    public void a0(K3 k3) {
        Animator animator;
        if (k3 == null) {
            return;
        }
        int iMin = this.C;
        if (k3.C) {
            iMin = k3.y() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        c0(k3, iMin, k3.o(), k3.p(), false);
        View view = k3.Z;
        if (view != null) {
            ViewGroup viewGroup = k3.Y;
            K3 k32 = null;
            if (viewGroup != null && view != null) {
                int iIndexOf = this.v.indexOf(k3);
                while (true) {
                    iIndexOf--;
                    if (iIndexOf < 0) {
                        break;
                    }
                    K3 k33 = (K3) this.v.get(iIndexOf);
                    if (k33.Y == viewGroup && k33.Z != null) {
                        k32 = k33;
                        break;
                    }
                }
            }
            if (k32 != null) {
                View view2 = k32.Z;
                ViewGroup viewGroup2 = k3.Y;
                int iIndexOfChild = viewGroup2.indexOfChild(view2);
                int iIndexOfChild2 = viewGroup2.indexOfChild(k3.Z);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup2.removeViewAt(iIndexOfChild2);
                    viewGroup2.addView(k3.Z, iIndexOfChild);
                }
            }
            if (k3.e0 && k3.Y != null) {
                float f = k3.g0;
                if (f > 0.0f) {
                    k3.Z.setAlpha(f);
                }
                k3.g0 = 0.0f;
                k3.e0 = false;
                C0528b4 c0528b4W = W(k3, k3.o(), true, k3.p());
                if (c0528b4W != null) {
                    p0(k3.Z, c0528b4W);
                    Animation animation = c0528b4W.a;
                    if (animation != null) {
                        k3.Z.startAnimation(animation);
                    } else {
                        c0528b4W.b.setTarget(k3.Z);
                        c0528b4W.b.start();
                    }
                }
            }
        }
        if (k3.f0) {
            if (k3.Z != null) {
                C0528b4 c0528b4W2 = W(k3, k3.o(), !k3.R, k3.p());
                if (c0528b4W2 == null || (animator = c0528b4W2.b) == null) {
                    if (c0528b4W2 != null) {
                        p0(k3.Z, c0528b4W2);
                        k3.Z.startAnimation(c0528b4W2.a);
                        c0528b4W2.a.start();
                    }
                    k3.Z.setVisibility((!k3.R || k3.x()) ? 0 : 8);
                    if (k3.x()) {
                        k3.b0(false);
                    }
                } else {
                    animator.setTarget(k3.Z);
                    if (!k3.R) {
                        k3.Z.setVisibility(0);
                    } else if (k3.x()) {
                        k3.b0(false);
                    } else {
                        ViewGroup viewGroup3 = k3.Y;
                        View view3 = k3.Z;
                        viewGroup3.startViewTransition(view3);
                        c0528b4W2.b.addListener(new X3(this, viewGroup3, view3, k3));
                    }
                    p0(k3.Z, c0528b4W2);
                    c0528b4W2.b.start();
                }
            }
            if (k3.B && k3.V) {
                this.H = true;
            }
            k3.f0 = false;
        }
    }

    public void b(K3 k3, boolean z) {
        X(k3);
        if (k3.S) {
            return;
        }
        if (this.v.contains(k3)) {
            throw new IllegalStateException("Fragment already added: " + k3);
        }
        synchronized (this.v) {
            this.v.add(k3);
        }
        k3.B = true;
        k3.C = false;
        if (k3.Z == null) {
            k3.f0 = false;
        }
        if (k3.V) {
            this.H = true;
        }
        if (z) {
            c0(k3, this.C, 0, 0, false);
        }
    }

    public void b0(int i, boolean z) {
        R3 r3;
        if (this.D == null && i != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.C) {
            this.C = i;
            if (this.w != null) {
                int size = this.v.size();
                for (int i2 = 0; i2 < size; i2++) {
                    a0((K3) this.v.get(i2));
                }
                int size2 = this.w.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    K3 k3 = (K3) this.w.valueAt(i3);
                    if (k3 != null && ((k3.C || k3.S) && !k3.e0)) {
                        a0(k3);
                    }
                }
                s0();
                if (this.H && (r3 = this.D) != null && this.C == 4) {
                    ((M3) r3).e.invalidateOptionsMenu();
                    this.H = false;
                }
            }
        }
    }

    public void c(K3 k3) {
        if (k3.S) {
            k3.S = false;
            if (k3.B) {
                return;
            }
            if (this.v.contains(k3)) {
                throw new IllegalStateException("Fragment already added: " + k3);
            }
            synchronized (this.v) {
                this.v.add(k3);
            }
            k3.B = true;
            if (k3.V) {
                this.H = true;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:319:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c0(defpackage.K3 r17, int r18, int r19, int r20, boolean r21) {
        /*
            Method dump skipped, instruction units count: 1578
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.LayoutInflaterFactory2C0974j4.c0(K3, int, int, int, boolean):void");
    }

    public final void d() {
        SparseArray sparseArray = this.w;
        if (sparseArray != null) {
            for (int size = sparseArray.size() - 1; size >= 0; size--) {
                if (this.w.valueAt(size) == null) {
                    SparseArray sparseArray2 = this.w;
                    sparseArray2.delete(sparseArray2.keyAt(size));
                }
            }
        }
    }

    public void d0() {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        this.S = null;
        this.I = false;
        this.f24J = false;
        int size = this.v.size();
        for (int i = 0; i < size; i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null && (layoutInflaterFactory2C0974j4 = k3.K) != null) {
                layoutInflaterFactory2C0974j4.d0();
            }
        }
    }

    public final void e() {
        if (V()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    public boolean e0() {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        e();
        O();
        N(true);
        K3 k3 = this.G;
        if (k3 != null && (layoutInflaterFactory2C0974j4 = k3.K) != null && layoutInflaterFactory2C0974j4.e0()) {
            return true;
        }
        boolean zF0 = f0(this.M, this.N, null, -1, 0);
        if (zF0) {
            this.t = true;
            try {
                i0(this.M, this.N);
            } finally {
                f();
            }
        }
        if (this.L) {
            this.L = false;
            s0();
        }
        d();
        return zF0;
    }

    public final void f() {
        this.t = false;
        this.N.clear();
        this.M.clear();
    }

    public boolean f0(ArrayList arrayList, ArrayList arrayList2, String str, int i, int i2) {
        Boolean bool = Boolean.TRUE;
        ArrayList arrayList3 = this.x;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size = arrayList3.size() - 1;
            if (size < 0) {
                return false;
            }
            arrayList.add(this.x.remove(size));
            arrayList2.add(bool);
        } else {
            int i3 = -1;
            if (str != null || i >= 0) {
                int size2 = this.x.size() - 1;
                while (size2 >= 0) {
                    C3 c3 = (C3) this.x.get(size2);
                    if ((str != null && str.equals(c3.j)) || (i >= 0 && i == c3.l)) {
                        break;
                    }
                    size2--;
                }
                if (size2 < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    while (true) {
                        size2--;
                        if (size2 < 0) {
                            break;
                        }
                        C3 c32 = (C3) this.x.get(size2);
                        if (str == null || !str.equals(c32.j)) {
                            if (i < 0 || i != c32.l) {
                                break;
                            }
                        }
                    }
                }
                i3 = size2;
            }
            if (i3 == this.x.size() - 1) {
                return false;
            }
            for (int size3 = this.x.size() - 1; size3 > i3; size3--) {
                arrayList.add(this.x.remove(size3));
                arrayList2.add(bool);
            }
        }
        return true;
    }

    public void g(C3 c3, boolean z, boolean z2, boolean z3) {
        if (z) {
            c3.i(z3);
        } else {
            c3.h();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(c3);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            AbstractC1526t4.o(this, arrayList, arrayList2, 0, 1, true);
        }
        if (z3) {
            b0(this.C, true);
        }
        SparseArray sparseArray = this.w;
        if (sparseArray != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                K3 k3 = (K3) this.w.valueAt(i);
                if (k3 != null && k3.Z != null && k3.e0 && c3.j(k3.P)) {
                    float f = k3.g0;
                    if (f > 0.0f) {
                        k3.Z.setAlpha(f);
                    }
                    if (z3) {
                        k3.g0 = 0.0f;
                    } else {
                        k3.g0 = -1.0f;
                        k3.e0 = false;
                    }
                }
            }
        }
    }

    public final int g0(ArrayList arrayList, ArrayList arrayList2, int i, int i2, C1083l1 c1083l1) {
        boolean z;
        int i3 = i2;
        for (int i4 = i2 - 1; i4 >= i; i4--) {
            C3 c3 = (C3) arrayList.get(i4);
            boolean zBooleanValue = ((Boolean) arrayList2.get(i4)).booleanValue();
            int i5 = 0;
            while (true) {
                if (i5 >= c3.b.size()) {
                    z = false;
                    break;
                }
                if (C3.l((B3) c3.b.get(i5))) {
                    z = true;
                    break;
                }
                i5++;
            }
            if (z && !c3.k(arrayList, i4 + 1, i2)) {
                if (this.R == null) {
                    this.R = new ArrayList();
                }
                C0919i4 c0919i4 = new C0919i4(c3, zBooleanValue);
                this.R.add(c0919i4);
                for (int i6 = 0; i6 < c3.b.size(); i6++) {
                    B3 b3 = (B3) c3.b.get(i6);
                    if (C3.l(b3)) {
                        b3.b.e0(c0919i4);
                    }
                }
                if (zBooleanValue) {
                    c3.h();
                } else {
                    c3.i(false);
                }
                i3--;
                if (i4 != i3) {
                    arrayList.remove(i4);
                    arrayList.add(i3, c3);
                }
                a(c1083l1);
            }
        }
        return i3;
    }

    public void h(K3 k3) {
        if (k3.S) {
            return;
        }
        k3.S = true;
        if (k3.B) {
            synchronized (this.v) {
                this.v.remove(k3);
            }
            if (k3.V) {
                this.H = true;
            }
            k3.B = false;
        }
    }

    public void h0(K3 k3) {
        boolean z = !k3.y();
        if (!k3.S || z) {
            synchronized (this.v) {
                this.v.remove(k3);
            }
            if (k3.V) {
                this.H = true;
            }
            k3.B = false;
            k3.C = true;
        }
    }

    public void i() {
        this.I = false;
        this.f24J = false;
        K(2);
    }

    public final void i0(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 == null || arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        Q(arrayList, arrayList2);
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (!((C3) arrayList.get(i)).s) {
                if (i2 != i) {
                    P(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (((Boolean) arrayList2.get(i)).booleanValue()) {
                    while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((C3) arrayList.get(i2)).s) {
                        i2++;
                    }
                }
                P(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            P(arrayList, arrayList2, i2, size);
        }
    }

    public void j(Configuration configuration) {
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null) {
                k3.X = true;
                LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k3.K;
                if (layoutInflaterFactory2C0974j4 != null) {
                    layoutInflaterFactory2C0974j4.j(configuration);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void j0(Parcelable parcelable, C1030k4 c1030k4) {
        List list;
        List list2;
        if (parcelable == null) {
            return;
        }
        FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
        if (fragmentManagerState.s == null) {
            return;
        }
        Q4 q4 = null;
        if (c1030k4 != null) {
            List list3 = c1030k4.a;
            list = c1030k4.b;
            list2 = c1030k4.c;
            int size = list3 != null ? list3.size() : 0;
            for (int i = 0; i < size; i++) {
                K3 k3 = (K3) list3.get(i);
                int i2 = 0;
                while (true) {
                    FragmentState[] fragmentStateArr = fragmentManagerState.s;
                    if (i2 >= fragmentStateArr.length || fragmentStateArr[i2].t == k3.v) {
                        break;
                    } else {
                        i2++;
                    }
                }
                FragmentState[] fragmentStateArr2 = fragmentManagerState.s;
                if (i2 == fragmentStateArr2.length) {
                    StringBuilder sbF = Z7.f("Could not find active fragment with index ");
                    sbF.append(k3.v);
                    t0(new IllegalStateException(sbF.toString()));
                    throw null;
                }
                FragmentState fragmentState = fragmentStateArr2[i2];
                fragmentState.D = k3;
                k3.u = null;
                k3.H = 0;
                k3.E = false;
                k3.B = false;
                k3.y = null;
                Bundle bundle = fragmentState.C;
                if (bundle != null) {
                    bundle.setClassLoader(this.D.b.getClassLoader());
                    k3.u = fragmentState.C.getSparseParcelableArray("android:view_state");
                    k3.t = fragmentState.C;
                }
            }
        } else {
            list = null;
            list2 = null;
        }
        this.w = new SparseArray(fragmentManagerState.s.length);
        int i3 = 0;
        while (true) {
            FragmentState[] fragmentStateArr3 = fragmentManagerState.s;
            if (i3 >= fragmentStateArr3.length) {
                break;
            }
            FragmentState fragmentState2 = fragmentStateArr3[i3];
            if (fragmentState2 != null) {
                C1030k4 c1030k42 = (list == null || i3 >= list.size()) ? q4 : (C1030k4) list.get(i3);
                if (list2 != null && i3 < list2.size()) {
                    q4 = (Q4) list2.get(i3);
                }
                R3 r3 = this.D;
                P3 p3 = this.E;
                K3 k32 = this.F;
                if (fragmentState2.D == null) {
                    Context context = r3.b;
                    Bundle bundle2 = fragmentState2.A;
                    if (bundle2 != null) {
                        bundle2.setClassLoader(context.getClassLoader());
                    }
                    if (p3 != null) {
                        fragmentState2.D = p3.a(context, fragmentState2.s, fragmentState2.A);
                    } else {
                        fragmentState2.D = K3.v(context, fragmentState2.s, fragmentState2.A);
                    }
                    Bundle bundle3 = fragmentState2.C;
                    if (bundle3 != null) {
                        bundle3.setClassLoader(context.getClassLoader());
                        fragmentState2.D.t = fragmentState2.C;
                    }
                    fragmentState2.D.c0(fragmentState2.t, k32);
                    K3 k33 = fragmentState2.D;
                    k33.D = fragmentState2.u;
                    k33.F = true;
                    k33.O = fragmentState2.v;
                    k33.P = fragmentState2.w;
                    k33.Q = fragmentState2.x;
                    k33.T = fragmentState2.y;
                    k33.S = fragmentState2.z;
                    k33.R = fragmentState2.B;
                    k33.I = r3.d;
                }
                K3 k34 = fragmentState2.D;
                k34.L = c1030k42;
                k34.M = q4;
                this.w.put(k34.v, k34);
                fragmentState2.D = null;
            }
            i3++;
            q4 = null;
        }
        if (c1030k4 != null) {
            List list4 = c1030k4.a;
            int size2 = list4 != null ? list4.size() : 0;
            for (int i4 = 0; i4 < size2; i4++) {
                K3 k35 = (K3) list4.get(i4);
                int i5 = k35.z;
                if (i5 >= 0) {
                    K3 k36 = (K3) this.w.get(i5);
                    k35.y = k36;
                    if (k36 == null) {
                        Log.w("FragmentManager", "Re-attaching retained fragment " + k35 + " target no longer exists: " + k35.z);
                    }
                }
            }
        }
        this.v.clear();
        if (fragmentManagerState.t != null) {
            int i6 = 0;
            while (true) {
                int[] iArr = fragmentManagerState.t;
                if (i6 >= iArr.length) {
                    break;
                }
                K3 k37 = (K3) this.w.get(iArr[i6]);
                if (k37 == null) {
                    StringBuilder sbF2 = Z7.f("No instantiated fragment for index #");
                    sbF2.append(fragmentManagerState.t[i6]);
                    t0(new IllegalStateException(sbF2.toString()));
                    throw null;
                }
                k37.B = true;
                if (this.v.contains(k37)) {
                    throw new IllegalStateException("Already added!");
                }
                synchronized (this.v) {
                    this.v.add(k37);
                }
                i6++;
            }
        }
        if (fragmentManagerState.u != null) {
            this.x = new ArrayList(fragmentManagerState.u.length);
            int i7 = 0;
            while (true) {
                BackStackState[] backStackStateArr = fragmentManagerState.u;
                if (i7 >= backStackStateArr.length) {
                    break;
                }
                BackStackState backStackState = backStackStateArr[i7];
                Objects.requireNonNull(backStackState);
                C3 c3 = new C3(this);
                int i8 = 0;
                while (i8 < backStackState.s.length) {
                    B3 b3 = new B3();
                    int[] iArr2 = backStackState.s;
                    int i9 = i8 + 1;
                    b3.a = iArr2[i8];
                    int i10 = i9 + 1;
                    int i11 = iArr2[i9];
                    if (i11 >= 0) {
                        b3.b = (K3) this.w.get(i11);
                    } else {
                        b3.b = null;
                    }
                    int[] iArr3 = backStackState.s;
                    int i12 = i10 + 1;
                    int i13 = iArr3[i10];
                    b3.c = i13;
                    int i14 = i12 + 1;
                    int i15 = iArr3[i12];
                    b3.d = i15;
                    int i16 = i14 + 1;
                    int i17 = iArr3[i14];
                    b3.e = i17;
                    int i18 = iArr3[i16];
                    b3.f = i18;
                    c3.c = i13;
                    c3.d = i15;
                    c3.e = i17;
                    c3.f = i18;
                    c3.b(b3);
                    i8 = i16 + 1;
                }
                c3.g = backStackState.t;
                c3.h = backStackState.u;
                c3.j = backStackState.v;
                c3.l = backStackState.w;
                c3.i = true;
                c3.m = backStackState.x;
                c3.n = backStackState.y;
                c3.o = backStackState.z;
                c3.p = backStackState.A;
                c3.q = backStackState.B;
                c3.r = backStackState.C;
                c3.s = backStackState.D;
                c3.c(1);
                this.x.add(c3);
                int i19 = c3.l;
                if (i19 >= 0) {
                    synchronized (this) {
                        if (this.z == null) {
                            this.z = new ArrayList();
                        }
                        int size3 = this.z.size();
                        if (i19 < size3) {
                            this.z.set(i19, c3);
                        } else {
                            while (size3 < i19) {
                                this.z.add(null);
                                if (this.A == null) {
                                    this.A = new ArrayList();
                                }
                                this.A.add(Integer.valueOf(size3));
                                size3++;
                            }
                            this.z.add(c3);
                        }
                    }
                }
                i7++;
            }
        } else {
            this.x = null;
        }
        int i20 = fragmentManagerState.v;
        if (i20 >= 0) {
            this.G = (K3) this.w.get(i20);
        }
        this.u = fragmentManagerState.w;
    }

    public boolean k(MenuItem menuItem) {
        if (this.C < 1) {
            return false;
        }
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null && k3.Q(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void l() {
        this.I = false;
        this.f24J = false;
        K(1);
    }

    public Parcelable l0() {
        BackStackState[] backStackStateArr;
        int[] iArr;
        int size;
        Bundle bundle;
        if (this.R != null) {
            while (!this.R.isEmpty()) {
                ((C0919i4) this.R.remove(0)).a();
            }
        }
        SparseArray sparseArray = this.w;
        int size2 = sparseArray == null ? 0 : sparseArray.size();
        int i = 0;
        while (true) {
            backStackStateArr = null;
            if (i >= size2) {
                break;
            }
            K3 k3 = (K3) this.w.valueAt(i);
            if (k3 != null) {
                if (k3.g() != null) {
                    int iU = k3.u();
                    View viewG = k3.g();
                    Animation animation = viewG.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        viewG.clearAnimation();
                    }
                    k3.Y(null);
                    c0(k3, iU, 0, 0, false);
                } else if (k3.h() != null) {
                    k3.h().end();
                }
            }
            i++;
        }
        O();
        this.I = true;
        this.S = null;
        SparseArray sparseArray2 = this.w;
        if (sparseArray2 == null || sparseArray2.size() <= 0) {
            return null;
        }
        int size3 = this.w.size();
        FragmentState[] fragmentStateArr = new FragmentState[size3];
        boolean z = false;
        for (int i2 = 0; i2 < size3; i2++) {
            K3 k32 = (K3) this.w.valueAt(i2);
            if (k32 != null) {
                if (k32.v < 0) {
                    t0(new IllegalStateException("Failure saving state: active " + k32 + " has cleared index: " + k32.v));
                    throw null;
                }
                FragmentState fragmentState = new FragmentState(k32);
                fragmentStateArr[i2] = fragmentState;
                if (k32.s <= 0 || fragmentState.C != null) {
                    fragmentState.C = k32.t;
                } else {
                    if (this.P == null) {
                        this.P = new Bundle();
                    }
                    k32.W(this.P);
                    z(k32, this.P, false);
                    if (this.P.isEmpty()) {
                        bundle = null;
                    } else {
                        bundle = this.P;
                        this.P = null;
                    }
                    if (k32.Z != null) {
                        m0(k32);
                    }
                    if (k32.u != null) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putSparseParcelableArray("android:view_state", k32.u);
                    }
                    if (!k32.c0) {
                        if (bundle == null) {
                            bundle = new Bundle();
                        }
                        bundle.putBoolean("android:user_visible_hint", k32.c0);
                    }
                    fragmentState.C = bundle;
                    K3 k33 = k32.y;
                    if (k33 != null) {
                        if (k33.v < 0) {
                            t0(new IllegalStateException("Failure saving state: " + k32 + " has target not in fragment manager: " + k32.y));
                            throw null;
                        }
                        if (bundle == null) {
                            fragmentState.C = new Bundle();
                        }
                        Bundle bundle2 = fragmentState.C;
                        K3 k34 = k32.y;
                        int i3 = k34.v;
                        if (i3 < 0) {
                            t0(new IllegalStateException(Z7.b("Fragment ", k34, " is not currently in the FragmentManager")));
                            throw null;
                        }
                        bundle2.putInt("android:target_state", i3);
                        int i4 = k32.A;
                        if (i4 != 0) {
                            fragmentState.C.putInt("android:target_req_state", i4);
                        }
                    }
                }
                z = true;
            }
        }
        if (!z) {
            return null;
        }
        int size4 = this.v.size();
        if (size4 > 0) {
            iArr = new int[size4];
            for (int i5 = 0; i5 < size4; i5++) {
                iArr[i5] = ((K3) this.v.get(i5)).v;
                if (iArr[i5] < 0) {
                    StringBuilder sbF = Z7.f("Failure saving state: active ");
                    sbF.append(this.v.get(i5));
                    sbF.append(" has cleared index: ");
                    sbF.append(iArr[i5]);
                    t0(new IllegalStateException(sbF.toString()));
                    throw null;
                }
            }
        } else {
            iArr = null;
        }
        ArrayList arrayList = this.x;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            backStackStateArr = new BackStackState[size];
            for (int i6 = 0; i6 < size; i6++) {
                backStackStateArr[i6] = new BackStackState((C3) this.x.get(i6));
            }
        }
        FragmentManagerState fragmentManagerState = new FragmentManagerState();
        fragmentManagerState.s = fragmentStateArr;
        fragmentManagerState.t = iArr;
        fragmentManagerState.u = backStackStateArr;
        K3 k35 = this.G;
        if (k35 != null) {
            fragmentManagerState.v = k35.v;
        }
        fragmentManagerState.w = this.u;
        n0();
        return fragmentManagerState;
    }

    public boolean m(Menu menu, MenuInflater menuInflater) {
        boolean zM;
        if (this.C < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null) {
                if (k3.R) {
                    zM = false;
                } else {
                    if (k3.V) {
                        k3.D(menu, menuInflater);
                        zM = true;
                    } else {
                        zM = false;
                    }
                    LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k3.K;
                    if (layoutInflaterFactory2C0974j4 != null) {
                        zM |= layoutInflaterFactory2C0974j4.m(menu, menuInflater);
                    }
                }
                if (zM) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(k3);
                    z = true;
                }
            }
        }
        if (this.y != null) {
            for (int i2 = 0; i2 < this.y.size(); i2++) {
                K3 k32 = (K3) this.y.get(i2);
                if (arrayList == null || !arrayList.contains(k32)) {
                    Objects.requireNonNull(k32);
                }
            }
        }
        this.y = arrayList;
        return z;
    }

    public void m0(K3 k3) {
        if (k3.a0 == null) {
            return;
        }
        SparseArray sparseArray = this.Q;
        if (sparseArray == null) {
            this.Q = new SparseArray();
        } else {
            sparseArray.clear();
        }
        k3.a0.saveHierarchyState(this.Q);
        if (this.Q.size() > 0) {
            k3.u = this.Q;
            this.Q = null;
        }
    }

    public void n() {
        this.K = true;
        O();
        K(0);
        this.D = null;
        this.E = null;
        this.F = null;
    }

    public void n0() {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        C1030k4 c1030k4;
        if (this.w != null) {
            arrayList = null;
            arrayList2 = null;
            arrayList3 = null;
            for (int i = 0; i < this.w.size(); i++) {
                K3 k3 = (K3) this.w.valueAt(i);
                if (k3 != null) {
                    if (k3.T) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(k3);
                        K3 k32 = k3.y;
                        k3.z = k32 != null ? k32.v : -1;
                    }
                    LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k3.K;
                    if (layoutInflaterFactory2C0974j4 != null) {
                        layoutInflaterFactory2C0974j4.n0();
                        c1030k4 = k3.K.S;
                    } else {
                        c1030k4 = k3.L;
                    }
                    if (arrayList2 == null && c1030k4 != null) {
                        arrayList2 = new ArrayList(this.w.size());
                        for (int i2 = 0; i2 < i; i2++) {
                            arrayList2.add(null);
                        }
                    }
                    if (arrayList2 != null) {
                        arrayList2.add(c1030k4);
                    }
                    if (arrayList3 == null && k3.M != null) {
                        arrayList3 = new ArrayList(this.w.size());
                        for (int i3 = 0; i3 < i; i3++) {
                            arrayList3.add(null);
                        }
                    }
                    if (arrayList3 != null) {
                        arrayList3.add(k3.M);
                    }
                }
            }
        } else {
            arrayList = null;
            arrayList2 = null;
            arrayList3 = null;
        }
        if (arrayList == null && arrayList2 == null && arrayList3 == null) {
            this.S = null;
        } else {
            this.S = new C1030k4(arrayList, arrayList2, arrayList3);
        }
    }

    public void o() {
        for (int i = 0; i < this.v.size(); i++) {
            K3 k3 = (K3) this.v.get(i);
            if (k3 != null) {
                k3.T();
            }
        }
    }

    public void o0() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = (this.R == null || this.R.isEmpty()) ? false : true;
            if (this.s != null && this.s.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.D.c.removeCallbacks(this.T);
                this.D.c.post(this.T);
            }
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean zIsAssignableFrom;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0751f4.a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        Context context2 = this.D.b;
        C1633v1 c1633v1 = K3.n0;
        try {
            Class<?> clsLoadClass = (Class) c1633v1.get(str2);
            if (clsLoadClass == null) {
                clsLoadClass = context2.getClassLoader().loadClass(str2);
                c1633v1.put(str2, clsLoadClass);
            }
            zIsAssignableFrom = K3.class.isAssignableFrom(clsLoadClass);
        } catch (ClassNotFoundException unused) {
            zIsAssignableFrom = false;
        }
        if (!zIsAssignableFrom) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        K3 k3R = resourceId != -1 ? R(resourceId) : null;
        if (k3R == null && string != null) {
            k3R = S(string);
        }
        if (k3R == null && id != -1) {
            k3R = R(id);
        }
        if (k3R == null) {
            k3R = this.E.a(context, str2, null);
            k3R.D = true;
            k3R.O = resourceId != 0 ? resourceId : id;
            k3R.P = id;
            k3R.Q = string;
            k3R.E = true;
            k3R.I = this;
            R3 r3 = this.D;
            k3R.f4J = r3;
            Context context3 = r3.b;
            k3R.J(attributeSet, k3R.t);
            b(k3R, true);
        } else {
            if (k3R.E) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            k3R.E = true;
            R3 r32 = this.D;
            k3R.f4J = r32;
            if (!k3R.U) {
                Context context4 = r32.b;
                k3R.J(attributeSet, k3R.t);
            }
        }
        K3 k3 = k3R;
        if (this.C >= 1 || !k3.D) {
            c0(k3, this.C, 0, 0, false);
        } else {
            c0(k3, 1, 0, 0, false);
        }
        View view2 = k3.Z;
        if (view2 == null) {
            throw new IllegalStateException(Z7.d("Fragment ", str2, " did not create a view."));
        }
        if (resourceId != 0) {
            view2.setId(resourceId);
        }
        if (k3.Z.getTag() == null) {
            k3.Z.setTag(string);
        }
        return k3.Z;
    }

    public void p(boolean z) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        int size = this.v.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            K3 k3 = (K3) this.v.get(size);
            if (k3 != null && (layoutInflaterFactory2C0974j4 = k3.K) != null) {
                layoutInflaterFactory2C0974j4.p(z);
            }
        }
    }

    public void q(K3 k3, Bundle bundle, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.q(k3, bundle, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void q0(K3 k3) {
        if (k3 == null || (this.w.get(k3.v) == k3 && (k3.f4J == null || k3.I == this))) {
            this.G = k3;
            return;
        }
        throw new IllegalArgumentException("Fragment " + k3 + " is not an active fragment of FragmentManager " + this);
    }

    public void r(K3 k3, Context context, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.r(k3, context, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void s(K3 k3, Bundle bundle, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.s(k3, bundle, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void s0() {
        if (this.w == null) {
            return;
        }
        for (int i = 0; i < this.w.size(); i++) {
            K3 k3 = (K3) this.w.valueAt(i);
            if (k3 != null && k3.b0) {
                if (this.t) {
                    this.L = true;
                } else {
                    k3.b0 = false;
                    c0(k3, this.C, 0, 0, false);
                }
            }
        }
    }

    public void t(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.t(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public final void t0(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new Z2("FragmentManager"));
        R3 r3 = this.D;
        if (r3 == null) {
            try {
                L("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
                throw runtimeException;
            }
        }
        try {
            ((M3) r3).e.dump("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
            throw runtimeException;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        K3 k3 = this.F;
        if (k3 != null) {
            Y2.a(k3, sb);
        } else {
            Y2.a(this.D, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    public void u(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.u(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void v(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.v(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void w(K3 k3, Context context, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.w(k3, context, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void x(K3 k3, Bundle bundle, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.x(k3, bundle, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void y(K3 k3, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.y(k3, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    public void z(K3 k3, Bundle bundle, boolean z) {
        K3 k32 = this.F;
        if (k32 != null) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = k32.I;
            if (layoutInflaterFactory2C0974j4 instanceof LayoutInflaterFactory2C0974j4) {
                layoutInflaterFactory2C0974j4.z(k3, bundle, true);
            }
        }
        Iterator it = this.B.iterator();
        if (it.hasNext()) {
            if (!z) {
                throw null;
            }
            throw null;
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
