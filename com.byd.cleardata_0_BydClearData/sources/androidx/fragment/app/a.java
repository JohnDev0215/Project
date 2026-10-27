package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.fragment.app.n;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class a extends n implements j.k {
    final j s;
    boolean t;
    int u = -1;

    public a(j jVar) {
        this.s = jVar;
    }

    private static boolean t(n.a aVar) {
        Fragment fragment = aVar.f1336b;
        return (fragment == null || !fragment.m || fragment.I == null || fragment.B || fragment.A || !fragment.a0()) ? false : true;
    }

    @Override // androidx.fragment.app.j.k
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (j.f1292d) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.h) {
            return true;
        }
        this.s.j(this);
        return true;
    }

    @Override // androidx.fragment.app.n
    public int d() {
        return k(false);
    }

    @Override // androidx.fragment.app.n
    public int e() {
        return k(true);
    }

    @Override // androidx.fragment.app.n
    void f(int i, Fragment fragment, String str, int i2) {
        super.f(i, fragment, str, i2);
        fragment.t = this.s;
    }

    @Override // androidx.fragment.app.n
    public n g(Fragment fragment) {
        j jVar = fragment.t;
        if (jVar == null || jVar == this.s) {
            return super.g(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    void j(int i) {
        if (this.h) {
            if (j.f1292d) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f1331a.size();
            for (int i2 = 0; i2 < size; i2++) {
                n.a aVar = this.f1331a.get(i2);
                Fragment fragment = aVar.f1336b;
                if (fragment != null) {
                    fragment.s += i;
                    if (j.f1292d) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f1336b + " to " + aVar.f1336b.s);
                    }
                }
            }
        }
    }

    int k(boolean z) {
        if (this.t) {
            throw new IllegalStateException("commit already called");
        }
        if (j.f1292d) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new a.g.j.b("FragmentManager"));
            l("  ", printWriter);
            printWriter.close();
        }
        this.t = true;
        this.u = this.h ? this.s.m(this) : -1;
        this.s.h0(this, z);
        return this.u;
    }

    public void l(String str, PrintWriter printWriter) {
        m(str, printWriter, true);
    }

    public void m(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.j);
            printWriter.print(" mIndex=");
            printWriter.print(this.u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.t);
            if (this.f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.g));
            }
            if (this.f1332b != 0 || this.f1333c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1332b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1333c));
            }
            if (this.f1334d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1334d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.e));
            }
            if (this.k != 0 || this.l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.l);
            }
            if (this.m != 0 || this.n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.n);
            }
        }
        if (this.f1331a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1331a.size();
        for (int i = 0; i < size; i++) {
            n.a aVar = this.f1331a.get(i);
            switch (aVar.f1335a) {
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
                    str2 = "cmd=" + aVar.f1335a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f1336b);
            if (z) {
                if (aVar.f1337c != 0 || aVar.f1338d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1337c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1338d));
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

    void n() {
        int size = this.f1331a.size();
        for (int i = 0; i < size; i++) {
            n.a aVar = this.f1331a.get(i);
            Fragment fragment = aVar.f1336b;
            if (fragment != null) {
                fragment.t1(this.f, this.g);
            }
            switch (aVar.f1335a) {
                case 1:
                    fragment.s1(aVar.f1337c);
                    this.s.k(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1335a);
                case 3:
                    fragment.s1(aVar.f1338d);
                    this.s.X0(fragment);
                    break;
                case 4:
                    fragment.s1(aVar.f1338d);
                    this.s.B0(fragment);
                    break;
                case 5:
                    fragment.s1(aVar.f1337c);
                    this.s.k1(fragment);
                    break;
                case 6:
                    fragment.s1(aVar.f1338d);
                    this.s.w(fragment);
                    break;
                case 7:
                    fragment.s1(aVar.f1337c);
                    this.s.p(fragment);
                    break;
                case 8:
                    this.s.j1(fragment);
                    break;
                case 9:
                    this.s.j1(null);
                    break;
                case 10:
                    this.s.i1(fragment, aVar.h);
                    break;
            }
            if (!this.q && aVar.f1335a != 1 && fragment != null) {
                this.s.N0(fragment);
            }
        }
        if (this.q) {
            return;
        }
        j jVar = this.s;
        jVar.O0(jVar.t, true);
    }

    void o(boolean z) {
        for (int size = this.f1331a.size() - 1; size >= 0; size--) {
            n.a aVar = this.f1331a.get(size);
            Fragment fragment = aVar.f1336b;
            if (fragment != null) {
                fragment.t1(j.c1(this.f), this.g);
            }
            switch (aVar.f1335a) {
                case 1:
                    fragment.s1(aVar.f);
                    this.s.X0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1335a);
                case 3:
                    fragment.s1(aVar.e);
                    this.s.k(fragment, false);
                    break;
                case 4:
                    fragment.s1(aVar.e);
                    this.s.k1(fragment);
                    break;
                case 5:
                    fragment.s1(aVar.f);
                    this.s.B0(fragment);
                    break;
                case 6:
                    fragment.s1(aVar.e);
                    this.s.p(fragment);
                    break;
                case 7:
                    fragment.s1(aVar.f);
                    this.s.w(fragment);
                    break;
                case 8:
                    this.s.j1(null);
                    break;
                case 9:
                    this.s.j1(fragment);
                    break;
                case 10:
                    this.s.i1(fragment, aVar.g);
                    break;
            }
            if (!this.q && aVar.f1335a != 3 && fragment != null) {
                this.s.N0(fragment);
            }
        }
        if (this.q || !z) {
            return;
        }
        j jVar = this.s;
        jVar.O0(jVar.t, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment p(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<androidx.fragment.app.n$a> r5 = r0.f1331a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<androidx.fragment.app.n$a> r5 = r0.f1331a
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.n$a r5 = (androidx.fragment.app.n.a) r5
            int r6 = r5.f1335a
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
            java.util.ArrayList<androidx.fragment.app.n$a> r6 = r0.f1331a
            androidx.fragment.app.n$a r7 = new androidx.fragment.app.n$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f1336b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f1336b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f1336b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<androidx.fragment.app.n$a> r3 = r0.f1331a
            androidx.fragment.app.n$a r6 = new androidx.fragment.app.n$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f1336b
            int r9 = r6.y
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            androidx.fragment.app.Fragment r14 = (androidx.fragment.app.Fragment) r14
            int r15 = r14.y
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = r8
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<androidx.fragment.app.n$a> r3 = r0.f1331a
            androidx.fragment.app.n$a r15 = new androidx.fragment.app.n$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            androidx.fragment.app.n$a r15 = new androidx.fragment.app.n$a
            r15.<init>(r10, r14)
            int r2 = r5.f1337c
            r15.f1337c = r2
            int r2 = r5.e
            r15.e = r2
            int r2 = r5.f1338d
            r15.f1338d = r2
            int r2 = r5.f
            r15.f = r2
            java.util.ArrayList<androidx.fragment.app.n$a> r2 = r0.f1331a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<androidx.fragment.app.n$a> r2 = r0.f1331a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f1335a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f1336b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.p(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String q() {
        return this.j;
    }

    boolean r(int i) {
        int size = this.f1331a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1331a.get(i2).f1336b;
            int i3 = fragment != null ? fragment.y : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    boolean s(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f1331a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f1331a.get(i4).f1336b;
            int i5 = fragment != null ? fragment.y : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f1331a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f1331a.get(i7).f1336b;
                        if ((fragment2 != null ? fragment2.y : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.u >= 0) {
            sb.append(" #");
            sb.append(this.u);
        }
        if (this.j != null) {
            sb.append(" ");
            sb.append(this.j);
        }
        sb.append("}");
        return sb.toString();
    }

    boolean u() {
        for (int i = 0; i < this.f1331a.size(); i++) {
            if (t(this.f1331a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void v() {
        if (this.r != null) {
            for (int i = 0; i < this.r.size(); i++) {
                this.r.get(i).run();
            }
            this.r = null;
        }
    }

    void w(Fragment.f fVar) {
        for (int i = 0; i < this.f1331a.size(); i++) {
            n.a aVar = this.f1331a.get(i);
            if (t(aVar)) {
                aVar.f1336b.u1(fVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment x(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<androidx.fragment.app.n$a> r0 = r5.f1331a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<androidx.fragment.app.n$a> r2 = r5.f1331a
            java.lang.Object r2 = r2.get(r0)
            androidx.fragment.app.n$a r2 = (androidx.fragment.app.n.a) r2
            int r3 = r2.f1335a
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
            androidx.lifecycle.d$b r3 = r2.g
            r2.h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f1336b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f1336b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f1336b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.x(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }
}
