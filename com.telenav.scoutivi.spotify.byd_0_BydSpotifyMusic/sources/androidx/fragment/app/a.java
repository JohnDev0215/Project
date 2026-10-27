package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.r;
import androidx.lifecycle.i;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
final class a extends r implements FragmentManager.n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    final FragmentManager f2240t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    boolean f2241u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    int f2242v;

    a(FragmentManager fragmentManager) {
        super(fragmentManager.q0(), fragmentManager.t0() != null ? fragmentManager.t0().j().getClassLoader() : null);
        this.f2242v = -1;
        this.f2240t = fragmentManager;
    }

    private static boolean H(r.a aVar) {
        Fragment fragment = aVar.f2386b;
        return (fragment == null || !fragment.f2121q || fragment.M == null || fragment.F || fragment.E || !fragment.h0()) ? false : true;
    }

    public void A(String str, PrintWriter printWriter, boolean z6) {
        String str2;
        if (z6) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f2376k);
            printWriter.print(" mIndex=");
            printWriter.print(this.f2242v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f2241u);
            if (this.f2373h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f2373h));
            }
            if (this.f2369d != 0 || this.f2370e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2369d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2370e));
            }
            if (this.f2371f != 0 || this.f2372g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f2371f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f2372g));
            }
            if (this.f2377l != 0 || this.f2378m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f2377l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f2378m);
            }
            if (this.f2379n != 0 || this.f2380o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f2379n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f2380o);
            }
        }
        if (this.f2368c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f2368c.size();
        for (int i7 = 0; i7 < size; i7++) {
            r.a aVar = this.f2368c.get(i7);
            switch (aVar.f2385a) {
                case 0:
                    str2 = "NULL";
                    break;
                case 1:
                    str2 = "ADD";
                    break;
                case 2:
                    str2 = "REPLACE";
                    break;
                case 3:
                    str2 = "REMOVE";
                    break;
                case 4:
                    str2 = "HIDE";
                    break;
                case 5:
                    str2 = "SHOW";
                    break;
                case 6:
                    str2 = "DETACH";
                    break;
                case 7:
                    str2 = "ATTACH";
                    break;
                case 8:
                    str2 = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    str2 = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    str2 = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    str2 = "cmd=" + aVar.f2385a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i7);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f2386b);
            if (z6) {
                if (aVar.f2387c != 0 || aVar.f2388d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f2387c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f2388d));
                }
                if (aVar.f2389e != 0 || aVar.f2390f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f2389e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f2390f));
                }
            }
        }
    }

    void B() {
        int size = this.f2368c.size();
        for (int i7 = 0; i7 < size; i7++) {
            r.a aVar = this.f2368c.get(i7);
            Fragment fragment = aVar.f2386b;
            if (fragment != null) {
                fragment.J1(false);
                fragment.H1(this.f2373h);
                fragment.L1(this.f2381p, this.f2382q);
            }
            switch (aVar.f2385a) {
                case 1:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, false);
                    this.f2240t.g(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f2385a);
                case 3:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.c1(fragment);
                    break;
                case 4:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.C0(fragment);
                    break;
                case 5:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, false);
                    this.f2240t.o1(fragment);
                    break;
                case 6:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.x(fragment);
                    break;
                case 7:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, false);
                    this.f2240t.k(fragment);
                    break;
                case 8:
                    this.f2240t.m1(fragment);
                    break;
                case 9:
                    this.f2240t.m1(null);
                    break;
                case 10:
                    this.f2240t.l1(fragment, aVar.f2392h);
                    break;
            }
            if (!this.f2383r && aVar.f2385a != 1 && fragment != null && !FragmentManager.P) {
                this.f2240t.M0(fragment);
            }
        }
        if (this.f2383r || FragmentManager.P) {
            return;
        }
        FragmentManager fragmentManager = this.f2240t;
        fragmentManager.N0(fragmentManager.f2181q, true);
    }

    void C(boolean z6) {
        for (int size = this.f2368c.size() - 1; size >= 0; size--) {
            r.a aVar = this.f2368c.get(size);
            Fragment fragment = aVar.f2386b;
            if (fragment != null) {
                fragment.J1(true);
                fragment.H1(FragmentManager.g1(this.f2373h));
                fragment.L1(this.f2382q, this.f2381p);
            }
            switch (aVar.f2385a) {
                case 1:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, true);
                    this.f2240t.c1(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f2385a);
                case 3:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.g(fragment);
                    break;
                case 4:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.o1(fragment);
                    break;
                case 5:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, true);
                    this.f2240t.C0(fragment);
                    break;
                case 6:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k(fragment);
                    break;
                case 7:
                    fragment.A1(aVar.f2387c, aVar.f2388d, aVar.f2389e, aVar.f2390f);
                    this.f2240t.k1(fragment, true);
                    this.f2240t.x(fragment);
                    break;
                case 8:
                    this.f2240t.m1(null);
                    break;
                case 9:
                    this.f2240t.m1(fragment);
                    break;
                case 10:
                    this.f2240t.l1(fragment, aVar.f2391g);
                    break;
            }
            if (!this.f2383r && aVar.f2385a != 3 && fragment != null && !FragmentManager.P) {
                this.f2240t.M0(fragment);
            }
        }
        if (this.f2383r || !z6 || FragmentManager.P) {
            return;
        }
        FragmentManager fragmentManager = this.f2240t;
        fragmentManager.N0(fragmentManager.f2181q, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment D(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<androidx.fragment.app.r$a> r5 = r0.f2368c
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<androidx.fragment.app.r$a> r5 = r0.f2368c
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.r$a r5 = (androidx.fragment.app.r.a) r5
            int r6 = r5.f2385a
            r7 = 0
            r8 = 1
            if (r6 == r8) goto Lb2
            r9 = 2
            r10 = 3
            r11 = 9
            if (r6 == r9) goto L58
            if (r6 == r10) goto L41
            r9 = 6
            if (r6 == r9) goto L41
            r7 = 7
            if (r6 == r7) goto Lb2
            r7 = 8
            if (r6 == r7) goto L31
            goto Lb7
        L31:
            java.util.ArrayList<androidx.fragment.app.r$a> r6 = r0.f2368c
            androidx.fragment.app.r$a r7 = new androidx.fragment.app.r$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f2386b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f2386b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f2386b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<androidx.fragment.app.r$a> r3 = r0.f2368c
            androidx.fragment.app.r$a r6 = new androidx.fragment.app.r$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f2386b
            int r9 = r6.C
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            androidx.fragment.app.Fragment r14 = (androidx.fragment.app.Fragment) r14
            int r15 = r14.C
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = r8
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<androidx.fragment.app.r$a> r3 = r0.f2368c
            androidx.fragment.app.r$a r15 = new androidx.fragment.app.r$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            androidx.fragment.app.r$a r15 = new androidx.fragment.app.r$a
            r15.<init>(r10, r14)
            int r2 = r5.f2387c
            r15.f2387c = r2
            int r2 = r5.f2389e
            r15.f2389e = r2
            int r2 = r5.f2388d
            r15.f2388d = r2
            int r2 = r5.f2390f
            r15.f2390f = r2
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r0.f2368c
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r0.f2368c
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f2385a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f2386b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.D(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String E() {
        return this.f2376k;
    }

    boolean F(int i7) {
        int size = this.f2368c.size();
        for (int i8 = 0; i8 < size; i8++) {
            Fragment fragment = this.f2368c.get(i8).f2386b;
            int i9 = fragment != null ? fragment.C : 0;
            if (i9 != 0 && i9 == i7) {
                return true;
            }
        }
        return false;
    }

    boolean G(ArrayList<a> arrayList, int i7, int i8) {
        if (i8 == i7) {
            return false;
        }
        int size = this.f2368c.size();
        int i9 = -1;
        for (int i10 = 0; i10 < size; i10++) {
            Fragment fragment = this.f2368c.get(i10).f2386b;
            int i11 = fragment != null ? fragment.C : 0;
            if (i11 != 0 && i11 != i9) {
                for (int i12 = i7; i12 < i8; i12++) {
                    a aVar = arrayList.get(i12);
                    int size2 = aVar.f2368c.size();
                    for (int i13 = 0; i13 < size2; i13++) {
                        Fragment fragment2 = aVar.f2368c.get(i13).f2386b;
                        if ((fragment2 != null ? fragment2.C : 0) == i11) {
                            return true;
                        }
                    }
                }
                i9 = i11;
            }
        }
        return false;
    }

    boolean I() {
        for (int i7 = 0; i7 < this.f2368c.size(); i7++) {
            if (H(this.f2368c.get(i7))) {
                return true;
            }
        }
        return false;
    }

    public void J() {
        if (this.f2384s != null) {
            for (int i7 = 0; i7 < this.f2384s.size(); i7++) {
                this.f2384s.get(i7).run();
            }
            this.f2384s = null;
        }
    }

    void K(Fragment.h hVar) {
        for (int i7 = 0; i7 < this.f2368c.size(); i7++) {
            r.a aVar = this.f2368c.get(i7);
            if (H(aVar)) {
                aVar.f2386b.I1(hVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment L(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<androidx.fragment.app.r$a> r0 = r5.f2368c
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r5.f2368c
            java.lang.Object r2 = r2.get(r0)
            androidx.fragment.app.r$a r2 = (androidx.fragment.app.r.a) r2
            int r3 = r2.f2385a
            if (r3 == r1) goto L2d
            r4 = 3
            if (r3 == r4) goto L27
            switch(r3) {
                case 6: goto L27;
                case 7: goto L2d;
                case 8: goto L25;
                case 9: goto L22;
                case 10: goto L1d;
                default: goto L1c;
            }
        L1c:
            goto L32
        L1d:
            androidx.lifecycle.i$c r3 = r2.f2391g
            r2.f2392h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f2386b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f2386b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f2386b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.L(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    @Override // androidx.fragment.app.FragmentManager.n
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f2374i) {
            return true;
        }
        this.f2240t.e(this);
        return true;
    }

    @Override // androidx.fragment.app.r
    public int i() {
        return y(false);
    }

    @Override // androidx.fragment.app.r
    public int j() {
        return y(true);
    }

    @Override // androidx.fragment.app.r
    public void k() {
        m();
        this.f2240t.b0(this, false);
    }

    @Override // androidx.fragment.app.r
    public void l() {
        m();
        this.f2240t.b0(this, true);
    }

    @Override // androidx.fragment.app.r
    void n(int i7, Fragment fragment, String str, int i8) {
        super.n(i7, fragment, str, i8);
        fragment.f2128x = this.f2240t;
    }

    @Override // androidx.fragment.app.r
    public boolean o() {
        return this.f2368c.isEmpty();
    }

    @Override // androidx.fragment.app.r
    public r p(Fragment fragment) {
        FragmentManager fragmentManager = fragment.f2128x;
        if (fragmentManager == null || fragmentManager == this.f2240t) {
            return super.p(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.r
    public r t(Fragment fragment, i.c cVar) {
        if (fragment.f2128x != this.f2240t) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.f2240t);
        }
        if (cVar == i.c.INITIALIZED && fragment.f2109e > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + cVar + " after the Fragment has been created");
        }
        if (cVar != i.c.DESTROYED) {
            return super.t(fragment, cVar);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + cVar + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f2242v >= 0) {
            sb.append(" #");
            sb.append(this.f2242v);
        }
        if (this.f2376k != null) {
            sb.append(" ");
            sb.append(this.f2376k);
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // androidx.fragment.app.r
    public r u(Fragment fragment) {
        FragmentManager fragmentManager;
        if (fragment == null || (fragmentManager = fragment.f2128x) == null || fragmentManager == this.f2240t) {
            return super.u(fragment);
        }
        throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.r
    public r w(Fragment fragment) {
        FragmentManager fragmentManager = fragment.f2128x;
        if (fragmentManager == null || fragmentManager == this.f2240t) {
            return super.w(fragment);
        }
        throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    void x(int i7) {
        if (this.f2374i) {
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i7);
            }
            int size = this.f2368c.size();
            for (int i8 = 0; i8 < size; i8++) {
                r.a aVar = this.f2368c.get(i8);
                Fragment fragment = aVar.f2386b;
                if (fragment != null) {
                    fragment.f2127w += i7;
                    if (FragmentManager.F0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f2386b + " to " + aVar.f2386b.f2127w);
                    }
                }
            }
        }
    }

    int y(boolean z6) {
        if (this.f2241u) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new w("FragmentManager"));
            z("  ", printWriter);
            printWriter.close();
        }
        this.f2241u = true;
        if (this.f2374i) {
            this.f2242v = this.f2240t.i();
        } else {
            this.f2242v = -1;
        }
        this.f2240t.Y(this, z6);
        return this.f2242v;
    }

    public void z(String str, PrintWriter printWriter) {
        A(str, printWriter, true);
    }
}
