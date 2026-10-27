package androidx.fragment.app;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.j;
import androidx.fragment.app.o;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
final class a extends o implements j.h {
    final j t;
    boolean u;
    int v;

    /* JADX WARN: Illegal instructions before constructor call */
    a(j jVar) {
        f fVarF0 = jVar.f0();
        g<?> gVar = jVar.p;
        super(fVarF0, gVar != null ? gVar.h().getClassLoader() : null);
        this.v = -1;
        this.t = jVar;
    }

    private static boolean F(o.a aVar) {
        Fragment fragment = aVar.f1492b;
        return (fragment == null || !fragment.m || fragment.I == null || fragment.B || fragment.A || !fragment.S()) ? false : true;
    }

    void A(boolean z) {
        for (int size = this.f1484c.size() - 1; size >= 0; size--) {
            o.a aVar = this.f1484c.get(size);
            Fragment fragment = aVar.f1492b;
            if (fragment != null) {
                fragment.l1(j.Q0(this.f1489h));
            }
            switch (aVar.f1491a) {
                case 1:
                    fragment.k1(aVar.f1496f);
                    this.t.T0(fragment, true);
                    this.t.L0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1491a);
                case 3:
                    fragment.k1(aVar.f1495e);
                    this.t.d(fragment);
                    break;
                case 4:
                    fragment.k1(aVar.f1495e);
                    this.t.X0(fragment);
                    break;
                case 5:
                    fragment.k1(aVar.f1496f);
                    this.t.T0(fragment, true);
                    this.t.o0(fragment);
                    break;
                case 6:
                    fragment.k1(aVar.f1495e);
                    this.t.h(fragment);
                    break;
                case 7:
                    fragment.k1(aVar.f1496f);
                    this.t.T0(fragment, true);
                    this.t.q(fragment);
                    break;
                case 8:
                    this.t.V0(null);
                    break;
                case 9:
                    this.t.V0(fragment);
                    break;
                case 10:
                    this.t.U0(fragment, aVar.f1497g);
                    break;
            }
            if (!this.r && aVar.f1491a != 3 && fragment != null) {
                this.t.y0(fragment);
            }
        }
        if (this.r || !z) {
            return;
        }
        j jVar = this.t;
        jVar.z0(jVar.o, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment B(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<androidx.fragment.app.o$a> r5 = r0.f1484c
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<androidx.fragment.app.o$a> r5 = r0.f1484c
            java.lang.Object r5 = r5.get(r4)
            androidx.fragment.app.o$a r5 = (androidx.fragment.app.o.a) r5
            int r6 = r5.f1491a
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
            java.util.ArrayList<androidx.fragment.app.o$a> r6 = r0.f1484c
            androidx.fragment.app.o$a r7 = new androidx.fragment.app.o$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f1492b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f1492b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f1492b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<androidx.fragment.app.o$a> r3 = r0.f1484c
            androidx.fragment.app.o$a r6 = new androidx.fragment.app.o$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f1492b
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
            java.util.ArrayList<androidx.fragment.app.o$a> r3 = r0.f1484c
            androidx.fragment.app.o$a r15 = new androidx.fragment.app.o$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            androidx.fragment.app.o$a r15 = new androidx.fragment.app.o$a
            r15.<init>(r10, r14)
            int r2 = r5.f1493c
            r15.f1493c = r2
            int r2 = r5.f1495e
            r15.f1495e = r2
            int r2 = r5.f1494d
            r15.f1494d = r2
            int r2 = r5.f1496f
            r15.f1496f = r2
            java.util.ArrayList<androidx.fragment.app.o$a> r2 = r0.f1484c
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<androidx.fragment.app.o$a> r2 = r0.f1484c
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f1491a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f1492b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.B(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String C() {
        return this.k;
    }

    boolean D(int i2) {
        int size = this.f1484c.size();
        for (int i3 = 0; i3 < size; i3++) {
            Fragment fragment = this.f1484c.get(i3).f1492b;
            int i4 = fragment != null ? fragment.y : 0;
            if (i4 != 0 && i4 == i2) {
                return true;
            }
        }
        return false;
    }

    boolean E(ArrayList<a> arrayList, int i2, int i3) {
        if (i3 == i2) {
            return false;
        }
        int size = this.f1484c.size();
        int i4 = -1;
        for (int i5 = 0; i5 < size; i5++) {
            Fragment fragment = this.f1484c.get(i5).f1492b;
            int i6 = fragment != null ? fragment.y : 0;
            if (i6 != 0 && i6 != i4) {
                for (int i7 = i2; i7 < i3; i7++) {
                    a aVar = arrayList.get(i7);
                    int size2 = aVar.f1484c.size();
                    for (int i8 = 0; i8 < size2; i8++) {
                        Fragment fragment2 = aVar.f1484c.get(i8).f1492b;
                        if ((fragment2 != null ? fragment2.y : 0) == i6) {
                            return true;
                        }
                    }
                }
                i4 = i6;
            }
        }
        return false;
    }

    boolean G() {
        for (int i2 = 0; i2 < this.f1484c.size(); i2++) {
            if (F(this.f1484c.get(i2))) {
                return true;
            }
        }
        return false;
    }

    public void H() {
        if (this.s != null) {
            for (int i2 = 0; i2 < this.s.size(); i2++) {
                this.s.get(i2).run();
            }
            this.s = null;
        }
    }

    void I(Fragment.f fVar) {
        for (int i2 = 0; i2 < this.f1484c.size(); i2++) {
            o.a aVar = this.f1484c.get(i2);
            if (F(aVar)) {
                aVar.f1492b.m1(fVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    androidx.fragment.app.Fragment J(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<androidx.fragment.app.o$a> r0 = r5.f1484c
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<androidx.fragment.app.o$a> r2 = r5.f1484c
            java.lang.Object r2 = r2.get(r0)
            androidx.fragment.app.o$a r2 = (androidx.fragment.app.o.a) r2
            int r3 = r2.f1491a
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
            androidx.lifecycle.d$b r3 = r2.f1497g
            r2.f1498h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f1492b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f1492b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f1492b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.a.J(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    @Override // androidx.fragment.app.j.h
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (j.q0(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f1490i) {
            return true;
        }
        this.t.b(this);
        return true;
    }

    @Override // androidx.fragment.app.o
    public int i() {
        return w(false);
    }

    @Override // androidx.fragment.app.o
    public int j() {
        return w(true);
    }

    @Override // androidx.fragment.app.o
    public void k() {
        n();
        this.t.R(this, false);
    }

    @Override // androidx.fragment.app.o
    public void l() {
        n();
        this.t.R(this, true);
    }

    @Override // androidx.fragment.app.o
    public o m(Fragment fragment) {
        j jVar = fragment.t;
        if (jVar == null || jVar == this.t) {
            return super.m(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.o
    void o(int i2, Fragment fragment, String str, int i3) {
        super.o(i2, fragment, str, i3);
        fragment.t = this.t;
    }

    @Override // androidx.fragment.app.o
    public o p(Fragment fragment) {
        j jVar = fragment.t;
        if (jVar == null || jVar == this.t) {
            return super.p(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // androidx.fragment.app.o
    public o t(Fragment fragment) {
        j jVar;
        if (fragment == null || (jVar = fragment.t) == null || jVar == this.t) {
            return super.t(fragment);
        }
        throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.v >= 0) {
            sb.append(" #");
            sb.append(this.v);
        }
        if (this.k != null) {
            sb.append(" ");
            sb.append(this.k);
        }
        sb.append("}");
        return sb.toString();
    }

    void v(int i2) {
        if (this.f1490i) {
            if (j.q0(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i2);
            }
            int size = this.f1484c.size();
            for (int i3 = 0; i3 < size; i3++) {
                o.a aVar = this.f1484c.get(i3);
                Fragment fragment = aVar.f1492b;
                if (fragment != null) {
                    fragment.s += i2;
                    if (j.q0(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f1492b + " to " + aVar.f1492b.s);
                    }
                }
            }
        }
    }

    int w(boolean z) {
        if (this.u) {
            throw new IllegalStateException("commit already called");
        }
        if (j.q0(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new b.g.j.b("FragmentManager"));
            x("  ", printWriter);
            printWriter.close();
        }
        this.u = true;
        if (this.f1490i) {
            this.v = this.t.f();
        } else {
            this.v = -1;
        }
        this.t.O(this, z);
        return this.v;
    }

    public void x(String str, PrintWriter printWriter) {
        y(str, printWriter, true);
    }

    public void y(String str, PrintWriter printWriter, boolean z) {
        String str2;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.k);
            printWriter.print(" mIndex=");
            printWriter.print(this.v);
            printWriter.print(" mCommitted=");
            printWriter.println(this.u);
            if (this.f1489h != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1489h));
            }
            if (this.f1485d != 0 || this.f1486e != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1485d));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1486e));
            }
            if (this.f1487f != 0 || this.f1488g != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1487f));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1488g));
            }
            if (this.l != 0 || this.m != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.l));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.m);
            }
            if (this.n != 0 || this.o != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.n));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.o);
            }
        }
        if (this.f1484c.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1484c.size();
        for (int i2 = 0; i2 < size; i2++) {
            o.a aVar = this.f1484c.get(i2);
            switch (aVar.f1491a) {
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
                    str2 = "cmd=" + aVar.f1491a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i2);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f1492b);
            if (z) {
                if (aVar.f1493c != 0 || aVar.f1494d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1493c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1494d));
                }
                if (aVar.f1495e != 0 || aVar.f1496f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1495e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1496f));
                }
            }
        }
    }

    void z() {
        int size = this.f1484c.size();
        for (int i2 = 0; i2 < size; i2++) {
            o.a aVar = this.f1484c.get(i2);
            Fragment fragment = aVar.f1492b;
            if (fragment != null) {
                fragment.l1(this.f1489h);
            }
            switch (aVar.f1491a) {
                case 1:
                    fragment.k1(aVar.f1493c);
                    this.t.T0(fragment, false);
                    this.t.d(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1491a);
                case 3:
                    fragment.k1(aVar.f1494d);
                    this.t.L0(fragment);
                    break;
                case 4:
                    fragment.k1(aVar.f1494d);
                    this.t.o0(fragment);
                    break;
                case 5:
                    fragment.k1(aVar.f1493c);
                    this.t.T0(fragment, false);
                    this.t.X0(fragment);
                    break;
                case 6:
                    fragment.k1(aVar.f1494d);
                    this.t.q(fragment);
                    break;
                case 7:
                    fragment.k1(aVar.f1493c);
                    this.t.T0(fragment, false);
                    this.t.h(fragment);
                    break;
                case 8:
                    this.t.V0(fragment);
                    break;
                case 9:
                    this.t.V0(null);
                    break;
                case 10:
                    this.t.U0(fragment, aVar.f1498h);
                    break;
            }
            if (!this.r && aVar.f1491a != 1 && fragment != null) {
                this.t.y0(fragment);
            }
        }
        if (this.r) {
            return;
        }
        j jVar = this.t;
        jVar.z0(jVar.o, true);
    }
}
