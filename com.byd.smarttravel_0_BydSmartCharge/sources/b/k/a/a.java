package b.k.a;

import android.util.Log;
import androidx.fragment.app.Fragment;
import b.k.a.h;
import b.k.a.j;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j implements h.k {
    public final h s;
    public boolean t;
    public int u = -1;

    public a(h hVar) {
        this.s = hVar;
    }

    public static boolean r(j.a aVar) {
        Fragment fragment = aVar.f1257b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // b.k.a.h.k
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (h.f1223d) {
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

    @Override // b.k.a.j
    public int e() {
        return i(false);
    }

    @Override // b.k.a.j
    public j f(Fragment fragment) {
        h hVar = fragment.mFragmentManager;
        if (hVar == null || hVar == this.s) {
            return super.f(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // b.k.a.j
    public void g(int i, Fragment fragment, String str, int i2) {
        super.g(i, fragment, str, i2);
        fragment.mFragmentManager = this.s;
    }

    public void h(int i) {
        if (this.h) {
            if (h.f1223d) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f1252a.size();
            for (int i2 = 0; i2 < size; i2++) {
                j.a aVar = this.f1252a.get(i2);
                Fragment fragment = aVar.f1257b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i;
                    if (h.f1223d) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f1257b + " to " + aVar.f1257b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public int i(boolean z) {
        if (this.t) {
            throw new IllegalStateException("commit already called");
        }
        if (h.f1223d) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new b.g.i.b("FragmentManager"));
            j("  ", printWriter);
            printWriter.close();
        }
        this.t = true;
        if (this.h) {
            this.u = this.s.m(this);
        } else {
            this.u = -1;
        }
        this.s.h0(this, z);
        return this.u;
    }

    public void j(String str, PrintWriter printWriter) {
        k(str, printWriter, true);
    }

    public void k(String str, PrintWriter printWriter, boolean z) {
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
            if (this.f1253b != 0 || this.f1254c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1253b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1254c));
            }
            if (this.f1255d != 0 || this.e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1255d));
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
        if (this.f1252a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1252a.size();
        for (int i = 0; i < size; i++) {
            j.a aVar = this.f1252a.get(i);
            switch (aVar.f1256a) {
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
                    str2 = "cmd=" + aVar.f1256a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f1257b);
            if (z) {
                if (aVar.f1258c != 0 || aVar.f1259d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1258c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1259d));
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

    public void l() {
        int size = this.f1252a.size();
        for (int i = 0; i < size; i++) {
            j.a aVar = this.f1252a.get(i);
            Fragment fragment = aVar.f1257b;
            if (fragment != null) {
                fragment.setNextTransition(this.f, this.g);
            }
            switch (aVar.f1256a) {
                case 1:
                    fragment.setNextAnim(aVar.f1258c);
                    this.s.k(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1256a);
                case 3:
                    fragment.setNextAnim(aVar.f1259d);
                    this.s.X0(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f1259d);
                    this.s.B0(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f1258c);
                    this.s.k1(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f1259d);
                    this.s.w(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f1258c);
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
            if (!this.q && aVar.f1256a != 1 && fragment != null) {
                this.s.N0(fragment);
            }
        }
        if (this.q) {
            return;
        }
        h hVar = this.s;
        hVar.O0(hVar.t, true);
    }

    public void m(boolean z) {
        for (int size = this.f1252a.size() - 1; size >= 0; size--) {
            j.a aVar = this.f1252a.get(size);
            Fragment fragment = aVar.f1257b;
            if (fragment != null) {
                fragment.setNextTransition(h.c1(this.f), this.g);
            }
            switch (aVar.f1256a) {
                case 1:
                    fragment.setNextAnim(aVar.f);
                    this.s.X0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f1256a);
                case 3:
                    fragment.setNextAnim(aVar.e);
                    this.s.k(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.e);
                    this.s.k1(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f);
                    this.s.B0(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.e);
                    this.s.p(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f);
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
            if (!this.q && aVar.f1256a != 3 && fragment != null) {
                this.s.N0(fragment);
            }
        }
        if (this.q || !z) {
            return;
        }
        h hVar = this.s;
        hVar.O0(hVar.t, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment n(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<b.k.a.j$a> r5 = r0.f1252a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<b.k.a.j$a> r5 = r0.f1252a
            java.lang.Object r5 = r5.get(r4)
            b.k.a.j$a r5 = (b.k.a.j.a) r5
            int r6 = r5.f1256a
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
            java.util.ArrayList<b.k.a.j$a> r6 = r0.f1252a
            b.k.a.j$a r7 = new b.k.a.j$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f1257b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f1257b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f1257b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<b.k.a.j$a> r3 = r0.f1252a
            b.k.a.j$a r6 = new b.k.a.j$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f1257b
            int r9 = r6.mContainerId
            int r12 = r17.size()
            int r12 = r12 - r8
            r13 = 0
        L62:
            if (r12 < 0) goto La2
            java.lang.Object r14 = r1.get(r12)
            androidx.fragment.app.Fragment r14 = (androidx.fragment.app.Fragment) r14
            int r15 = r14.mContainerId
            if (r15 != r9) goto L9f
            if (r14 != r6) goto L72
            r13 = r8
            goto L9f
        L72:
            if (r14 != r3) goto L81
            java.util.ArrayList<b.k.a.j$a> r3 = r0.f1252a
            b.k.a.j$a r15 = new b.k.a.j$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            b.k.a.j$a r15 = new b.k.a.j$a
            r15.<init>(r10, r14)
            int r2 = r5.f1258c
            r15.f1258c = r2
            int r2 = r5.e
            r15.e = r2
            int r2 = r5.f1259d
            r15.f1259d = r2
            int r2 = r5.f
            r15.f = r2
            java.util.ArrayList<b.k.a.j$a> r2 = r0.f1252a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<b.k.a.j$a> r2 = r0.f1252a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f1256a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f1257b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: b.k.a.a.n(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String o() {
        return this.j;
    }

    public boolean p(int i) {
        int size = this.f1252a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1252a.get(i2).f1257b;
            int i3 = fragment != null ? fragment.mContainerId : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean q(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f1252a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f1252a.get(i4).f1257b;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f1252a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f1252a.get(i7).f1257b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i5) {
                            return true;
                        }
                    }
                }
                i3 = i5;
            }
        }
        return false;
    }

    public boolean s() {
        for (int i = 0; i < this.f1252a.size(); i++) {
            if (r(this.f1252a.get(i))) {
                return true;
            }
        }
        return false;
    }

    public void t() {
        if (this.r != null) {
            for (int i = 0; i < this.r.size(); i++) {
                this.r.get(i).run();
            }
            this.r = null;
        }
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

    public void u(Fragment.e eVar) {
        for (int i = 0; i < this.f1252a.size(); i++) {
            j.a aVar = this.f1252a.get(i);
            if (r(aVar)) {
                aVar.f1257b.setOnStartEnterTransitionListener(eVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment v(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<b.k.a.j$a> r0 = r5.f1252a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<b.k.a.j$a> r2 = r5.f1252a
            java.lang.Object r2 = r2.get(r0)
            b.k.a.j$a r2 = (b.k.a.j.a) r2
            int r3 = r2.f1256a
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
            androidx.fragment.app.Fragment r7 = r2.f1257b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f1257b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f1257b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: b.k.a.a.v(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }
}
