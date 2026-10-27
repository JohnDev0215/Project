package androidx.fragment.app;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.r;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class a extends r implements FragmentManager.n {
    final FragmentManager q;
    boolean r;
    int s;

    a(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager.p0(), fragmentManager.s0() != null ? fragmentManager.s0().k().getClassLoader() : null);
        this.s = -1;
        this.q = fragmentManager;
    }

    private static boolean w(r.a aVar) {
        Fragment fragment = aVar.f715b;
        return (fragment == null || !fragment.n || fragment.J == null || fragment.C || fragment.B || !fragment.S()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment A(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<androidx.fragment.app.r$a> r0 = r5.f711a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r5.f711a
            java.lang.Object r2 = r2.get(r0)
            androidx.fragment.app.r$a r2 = (androidx.fragment.app.r.a) r2
            int r3 = r2.f714a
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
            androidx.lifecycle.Lifecycle$State r3 = r2.g
            r2.h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f715b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f715b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f715b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.A(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    @Override // androidx.fragment.app.FragmentManager.n
    public boolean a(@NonNull ArrayList<a> arrayList, @NonNull ArrayList<Boolean> arrayList2) {
        if (FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.g) {
            return true;
        }
        this.q.e(this);
        return true;
    }

    @Override // androidx.fragment.app.r
    public int f() {
        return n(false);
    }

    @Override // androidx.fragment.app.r
    public int g() {
        return n(true);
    }

    @Override // androidx.fragment.app.r
    public void h() {
        i();
        this.q.b0(this, true);
    }

    @Override // androidx.fragment.app.r
    void j(int i, Fragment fragment, @Nullable String str, int i2) {
        super.j(i, fragment, str, i2);
        fragment.u = this.q;
    }

    @Override // androidx.fragment.app.r
    @NonNull
    public r k(@NonNull Fragment fragment) {
        FragmentManager fragmentManager = fragment.u;
        if (fragmentManager == null || fragmentManager == this.q) {
            super.k(fragment);
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    void m(int i) {
        if (this.g) {
            if (FragmentManager.D0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f711a.size();
            for (int i2 = 0; i2 < size; i2++) {
                r.a aVar = this.f711a.get(i2);
                Fragment fragment = aVar.f715b;
                if (fragment != null) {
                    fragment.t += i;
                    if (FragmentManager.D0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f715b + " to " + aVar.f715b.t);
                    }
                }
            }
        }
    }

    int n(boolean z) {
        if (this.r) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.D0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new w("FragmentManager"));
            o("  ", printWriter);
            printWriter.close();
        }
        this.r = true;
        if (this.g) {
            this.s = this.q.i();
        } else {
            this.s = -1;
        }
        this.q.Y(this, z);
        return this.s;
    }

    public void o(String str, PrintWriter printWriter) {
        p(str, printWriter, true);
    }

    public void p(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.h);
            printWriter.print(" mIndex=");
            printWriter.print(this.s);
            printWriter.print(" mCommitted=");
            printWriter.println(this.r);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
            }
            if (this.f712b != 0 || this.f713c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f712b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f713c));
            }
            if (this.d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.i != 0 || this.j != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.i));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.j);
            }
            if (this.k != 0 || this.l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.l);
            }
        }
        if (this.f711a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f711a.size();
        for (int i = 0; i < size; i++) {
            r.a aVar = this.f711a.get(i);
            switch (aVar.f714a) {
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
                    str2 = "cmd=" + aVar.f714a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f715b);
            if (z) {
                if (aVar.f716c != 0 || aVar.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f716c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.d));
                }
                if (aVar.e != 0 || aVar.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f));
                }
            }
        }
    }

    void q() {
        int size = this.f711a.size();
        for (int i = 0; i < size; i++) {
            r.a aVar = this.f711a.get(i);
            Fragment fragment = aVar.f715b;
            if (fragment != null) {
                fragment.n1(this.f);
                fragment.p1(this.m, this.n);
            }
            switch (aVar.f714a) {
                case 1:
                    fragment.m1(aVar.f716c);
                    this.q.d1(fragment, false);
                    this.q.g(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f714a);
                case 3:
                    fragment.m1(aVar.d);
                    this.q.W0(fragment);
                    break;
                case 4:
                    fragment.m1(aVar.d);
                    this.q.B0(fragment);
                    break;
                case 5:
                    fragment.m1(aVar.f716c);
                    this.q.d1(fragment, false);
                    this.q.h1(fragment);
                    break;
                case 6:
                    fragment.m1(aVar.d);
                    this.q.x(fragment);
                    break;
                case 7:
                    fragment.m1(aVar.f716c);
                    this.q.d1(fragment, false);
                    this.q.k(fragment);
                    break;
                case 8:
                    this.q.f1(fragment);
                    break;
                case 9:
                    this.q.f1(null);
                    break;
                case 10:
                    this.q.e1(fragment, aVar.h);
                    break;
            }
            if (!this.o && aVar.f714a != 1 && fragment != null) {
                if (FragmentManager.P) {
                    this.q.v(fragment).k();
                } else {
                    this.q.K0(fragment);
                }
            }
        }
        if (this.o) {
            return;
        }
        FragmentManager fragmentManager = this.q;
        fragmentManager.L0(fragmentManager.q, true);
    }

    void r(boolean z) {
        for (int size = this.f711a.size() - 1; size >= 0; size--) {
            r.a aVar = this.f711a.get(size);
            Fragment fragment = aVar.f715b;
            if (fragment != null) {
                fragment.n1(FragmentManager.a1(this.f));
                fragment.p1(this.n, this.m);
            }
            switch (aVar.f714a) {
                case 1:
                    fragment.m1(aVar.f);
                    this.q.d1(fragment, true);
                    this.q.W0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f714a);
                case 3:
                    fragment.m1(aVar.e);
                    this.q.g(fragment);
                    break;
                case 4:
                    fragment.m1(aVar.e);
                    this.q.h1(fragment);
                    break;
                case 5:
                    fragment.m1(aVar.f);
                    this.q.d1(fragment, true);
                    this.q.B0(fragment);
                    break;
                case 6:
                    fragment.m1(aVar.e);
                    this.q.k(fragment);
                    break;
                case 7:
                    fragment.m1(aVar.f);
                    this.q.d1(fragment, true);
                    this.q.x(fragment);
                    break;
                case 8:
                    this.q.f1(null);
                    break;
                case 9:
                    this.q.f1(fragment);
                    break;
                case 10:
                    this.q.e1(fragment, aVar.g);
                    break;
            }
            if (!this.o && aVar.f714a != 3 && fragment != null) {
                if (FragmentManager.P) {
                    this.q.v(fragment).k();
                } else {
                    this.q.K0(fragment);
                }
            }
        }
        if (this.o || !z) {
            return;
        }
        FragmentManager fragmentManager = this.q;
        fragmentManager.L0(fragmentManager.q, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment s(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<androidx.fragment.app.r$a> r5 = r0.f711a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<androidx.fragment.app.r$a> r5 = r0.f711a
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.r$a r5 = (androidx.fragment.app.r.a) r5
            int r6 = r5.f714a
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
            java.util.ArrayList<androidx.fragment.app.r$a> r6 = r0.f711a
            androidx.fragment.app.r$a r7 = new androidx.fragment.app.r$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f715b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f715b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f715b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<androidx.fragment.app.r$a> r3 = r0.f711a
            androidx.fragment.app.r$a r6 = new androidx.fragment.app.r$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f715b
            int r9 = r6.z
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            androidx.fragment.app.Fragment r14 = (androidx.fragment.app.Fragment) r14
            int r15 = r14.z
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = r8
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<androidx.fragment.app.r$a> r3 = r0.f711a
            androidx.fragment.app.r$a r15 = new androidx.fragment.app.r$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            androidx.fragment.app.r$a r15 = new androidx.fragment.app.r$a
            r15.<init>(r10, r14)
            int r2 = r5.f716c
            r15.f716c = r2
            int r2 = r5.e
            r15.e = r2
            int r2 = r5.d
            r15.d = r2
            int r2 = r5.f
            r15.f = r2
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r0.f711a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<androidx.fragment.app.r$a> r2 = r0.f711a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f714a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f715b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.s(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    @Nullable
    public String t() {
        return this.h;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.s >= 0) {
            sb.append(" #");
            sb.append(this.s);
        }
        if (this.h != null) {
            sb.append(" ");
            sb.append(this.h);
        }
        sb.append("}");
        return sb.toString();
    }

    boolean u(int i) {
        int size = this.f711a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f711a.get(i2).f715b;
            int i3 = fragment != null ? fragment.z : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    boolean v(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f711a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f711a.get(i4).f715b;
            int i5 = fragment != null ? fragment.z : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f711a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f711a.get(i7).f715b;
                        if ((fragment2 != null ? fragment2.z : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    boolean x() {
        for (int i = 0; i < this.f711a.size(); i++) {
            if (w(this.f711a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void y() {
        if (this.p != null) {
            for (int i = 0; i < this.p.size(); i++) {
                this.p.get(i).run();
            }
            this.p = null;
        }
    }

    void z(Fragment.d dVar) {
        for (int i = 0; i < this.f711a.size(); i++) {
            r.a aVar = this.f711a.get(i);
            if (w(aVar)) {
                aVar.f715b.o1(dVar);
            }
        }
    }
}
