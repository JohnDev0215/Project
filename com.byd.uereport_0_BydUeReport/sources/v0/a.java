package v0;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import java.io.PrintWriter;
import java.util.ArrayList;
import v0.h;
import v0.j;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j implements h.k {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final h f8354s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f8355t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f8356u = -1;

    public a(h hVar) {
        this.f8354s = hVar;
    }

    public static boolean x(j.a aVar) {
        Fragment fragment = aVar.f8441b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    public void A(Fragment.e eVar) {
        for (int i8 = 0; i8 < this.f8422a.size(); i8++) {
            j.a aVar = this.f8422a.get(i8);
            if (x(aVar)) {
                aVar.f8441b.setOnStartEnterTransitionListener(eVar);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment B(java.util.ArrayList<androidx.fragment.app.Fragment> r6, androidx.fragment.app.Fragment r7) {
        /*
            r5 = this;
            java.util.ArrayList<v0.j$a> r0 = r5.f8422a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<v0.j$a> r2 = r5.f8422a
            java.lang.Object r2 = r2.get(r0)
            v0.j$a r2 = (v0.j.a) r2
            int r3 = r2.f8440a
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
            androidx.lifecycle.Lifecycle$State r3 = r2.f8446g
            r2.f8447h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f8441b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f8441b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f8441b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.a.B(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    @Override // v0.h.k
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (h.f8366f) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f8429h) {
            return true;
        }
        this.f8354s.q(this);
        return true;
    }

    @Override // v0.j
    public int f() {
        return o(false);
    }

    @Override // v0.j
    public void g() {
        i();
        this.f8354s.s0(this, false);
    }

    @Override // v0.j
    public j h(Fragment fragment) {
        h hVar = fragment.mFragmentManager;
        if (hVar == null || hVar == this.f8354s) {
            return super.h(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // v0.j
    public void j(int i8, Fragment fragment, String str, int i9) {
        super.j(i8, fragment, str, i9);
        fragment.mFragmentManager = this.f8354s;
    }

    @Override // v0.j
    public boolean k() {
        return this.f8422a.isEmpty();
    }

    @Override // v0.j
    public j l(Fragment fragment) {
        h hVar = fragment.mFragmentManager;
        if (hVar == null || hVar == this.f8354s) {
            return super.l(fragment);
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // v0.j
    public j m(Fragment fragment, Lifecycle.State state) {
        if (fragment.mFragmentManager != this.f8354s) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.f8354s);
        }
        Lifecycle.State state2 = Lifecycle.State.CREATED;
        if (state.isAtLeast(state2)) {
            return super.m(fragment, state);
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle below " + state2);
    }

    public void n(int i8) {
        if (this.f8429h) {
            if (h.f8366f) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i8);
            }
            int size = this.f8422a.size();
            for (int i9 = 0; i9 < size; i9++) {
                j.a aVar = this.f8422a.get(i9);
                Fragment fragment = aVar.f8441b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i8;
                    if (h.f8366f) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f8441b + " to " + aVar.f8441b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public int o(boolean z7) {
        if (this.f8355t) {
            throw new IllegalStateException("commit already called");
        }
        if (h.f8366f) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new n0.b("FragmentManager"));
            p("  ", printWriter);
            printWriter.close();
        }
        this.f8355t = true;
        if (this.f8429h) {
            this.f8356u = this.f8354s.t(this);
        } else {
            this.f8356u = -1;
        }
        this.f8354s.o0(this, z7);
        return this.f8356u;
    }

    public void p(String str, PrintWriter printWriter) {
        q(str, printWriter, true);
    }

    public void q(String str, PrintWriter printWriter, boolean z7) {
        String str2;
        if (z7) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f8431j);
            printWriter.print(" mIndex=");
            printWriter.print(this.f8356u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f8355t);
            if (this.f8427f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f8427f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f8428g));
            }
            if (this.f8423b != 0 || this.f8424c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f8423b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f8424c));
            }
            if (this.f8425d != 0 || this.f8426e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f8425d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f8426e));
            }
            if (this.f8432k != 0 || this.f8433l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f8432k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f8433l);
            }
            if (this.f8434m != 0 || this.f8435n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f8434m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f8435n);
            }
        }
        if (this.f8422a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f8422a.size();
        for (int i8 = 0; i8 < size; i8++) {
            j.a aVar = this.f8422a.get(i8);
            switch (aVar.f8440a) {
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
                    str2 = "cmd=" + aVar.f8440a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i8);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f8441b);
            if (z7) {
                if (aVar.f8442c != 0 || aVar.f8443d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f8442c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f8443d));
                }
                if (aVar.f8444e != 0 || aVar.f8445f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f8444e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f8445f));
                }
            }
        }
    }

    public void r() {
        int size = this.f8422a.size();
        for (int i8 = 0; i8 < size; i8++) {
            j.a aVar = this.f8422a.get(i8);
            Fragment fragment = aVar.f8441b;
            if (fragment != null) {
                fragment.setNextTransition(this.f8427f, this.f8428g);
            }
            switch (aVar.f8440a) {
                case 1:
                    fragment.setNextAnim(aVar.f8442c);
                    this.f8354s.r(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f8440a);
                case 3:
                    fragment.setNextAnim(aVar.f8443d);
                    this.f8354s.b1(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f8443d);
                    this.f8354s.I0(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f8442c);
                    this.f8354s.o1(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f8443d);
                    this.f8354s.D(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f8442c);
                    this.f8354s.w(fragment);
                    break;
                case 8:
                    this.f8354s.n1(fragment);
                    break;
                case 9:
                    this.f8354s.n1(null);
                    break;
                case 10:
                    this.f8354s.m1(fragment, aVar.f8447h);
                    break;
            }
            if (!this.f8438q && aVar.f8440a != 1 && fragment != null) {
                this.f8354s.S0(fragment);
            }
        }
        if (this.f8438q) {
            return;
        }
        h hVar = this.f8354s;
        hVar.T0(hVar.f8382v, true);
    }

    public void s(boolean z7) {
        for (int size = this.f8422a.size() - 1; size >= 0; size--) {
            j.a aVar = this.f8422a.get(size);
            Fragment fragment = aVar.f8441b;
            if (fragment != null) {
                fragment.setNextTransition(h.g1(this.f8427f), this.f8428g);
            }
            switch (aVar.f8440a) {
                case 1:
                    fragment.setNextAnim(aVar.f8445f);
                    this.f8354s.b1(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f8440a);
                case 3:
                    fragment.setNextAnim(aVar.f8444e);
                    this.f8354s.r(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f8444e);
                    this.f8354s.o1(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f8445f);
                    this.f8354s.I0(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f8444e);
                    this.f8354s.w(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f8445f);
                    this.f8354s.D(fragment);
                    break;
                case 8:
                    this.f8354s.n1(null);
                    break;
                case 9:
                    this.f8354s.n1(fragment);
                    break;
                case 10:
                    this.f8354s.m1(fragment, aVar.f8446g);
                    break;
            }
            if (!this.f8438q && aVar.f8440a != 3 && fragment != null) {
                this.f8354s.S0(fragment);
            }
        }
        if (this.f8438q || !z7) {
            return;
        }
        h hVar = this.f8354s;
        hVar.T0(hVar.f8382v, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.fragment.app.Fragment t(java.util.ArrayList<androidx.fragment.app.Fragment> r17, androidx.fragment.app.Fragment r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r3 = r18
            r4 = 0
        L7:
            java.util.ArrayList<v0.j$a> r5 = r0.f8422a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<v0.j$a> r5 = r0.f8422a
            java.lang.Object r5 = r5.get(r4)
            v0.j$a r5 = (v0.j.a) r5
            int r6 = r5.f8440a
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
            java.util.ArrayList<v0.j$a> r6 = r0.f8422a
            v0.j$a r7 = new v0.j$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f8441b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f8441b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f8441b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<v0.j$a> r3 = r0.f8422a
            v0.j$a r6 = new v0.j$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f8441b
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
            java.util.ArrayList<v0.j$a> r3 = r0.f8422a
            v0.j$a r15 = new v0.j$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            v0.j$a r15 = new v0.j$a
            r15.<init>(r10, r14)
            int r2 = r5.f8442c
            r15.f8442c = r2
            int r2 = r5.f8444e
            r15.f8444e = r2
            int r2 = r5.f8443d
            r15.f8443d = r2
            int r2 = r5.f8445f
            r15.f8445f = r2
            java.util.ArrayList<v0.j$a> r2 = r0.f8422a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<v0.j$a> r2 = r0.f8422a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f8440a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f8441b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: v0.a.t(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f8356u >= 0) {
            sb.append(" #");
            sb.append(this.f8356u);
        }
        if (this.f8431j != null) {
            sb.append(" ");
            sb.append(this.f8431j);
        }
        sb.append("}");
        return sb.toString();
    }

    public String u() {
        return this.f8431j;
    }

    public boolean v(int i8) {
        int size = this.f8422a.size();
        for (int i9 = 0; i9 < size; i9++) {
            Fragment fragment = this.f8422a.get(i9).f8441b;
            int i10 = fragment != null ? fragment.mContainerId : 0;
            if (i10 != 0 && i10 == i8) {
                return true;
            }
        }
        return false;
    }

    public boolean w(ArrayList<a> arrayList, int i8, int i9) {
        if (i9 == i8) {
            return false;
        }
        int size = this.f8422a.size();
        int i10 = -1;
        for (int i11 = 0; i11 < size; i11++) {
            Fragment fragment = this.f8422a.get(i11).f8441b;
            int i12 = fragment != null ? fragment.mContainerId : 0;
            if (i12 != 0 && i12 != i10) {
                for (int i13 = i8; i13 < i9; i13++) {
                    a aVar = arrayList.get(i13);
                    int size2 = aVar.f8422a.size();
                    for (int i14 = 0; i14 < size2; i14++) {
                        Fragment fragment2 = aVar.f8422a.get(i14).f8441b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i12) {
                            return true;
                        }
                    }
                }
                i10 = i12;
            }
        }
        return false;
    }

    public boolean y() {
        for (int i8 = 0; i8 < this.f8422a.size(); i8++) {
            if (x(this.f8422a.get(i8))) {
                return true;
            }
        }
        return false;
    }

    public void z() {
        if (this.f8439r != null) {
            for (int i8 = 0; i8 < this.f8439r.size(); i8++) {
                this.f8439r.get(i8).run();
            }
            this.f8439r = null;
        }
    }
}
