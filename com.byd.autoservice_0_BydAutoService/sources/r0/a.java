package r0;

import android.util.Log;
import androidx.fragment.app.Fragment;
import java.io.PrintWriter;
import java.util.ArrayList;
import r0.h;
import r0.j;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends j implements h.k {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final h f4674s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4675t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f4676u = -1;

    public a(h hVar) {
        this.f4674s = hVar;
    }

    public static boolean r(j.a aVar) {
        Fragment fragment = aVar.f4763b;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    @Override // r0.h.k
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (h.f4686d) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.f4751h) {
            return true;
        }
        this.f4674s.j(this);
        return true;
    }

    @Override // r0.j
    public int e() {
        return i(false);
    }

    @Override // r0.j
    public j f(Fragment fragment) {
        h hVar = fragment.mFragmentManager;
        if (hVar == null || hVar == this.f4674s) {
            return super.f(fragment);
        }
        throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    @Override // r0.j
    public void g(int i3, Fragment fragment, String str, int i4) {
        super.g(i3, fragment, str, i4);
        fragment.mFragmentManager = this.f4674s;
    }

    public void h(int i3) {
        if (this.f4751h) {
            if (h.f4686d) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i3);
            }
            int size = this.f4744a.size();
            for (int i4 = 0; i4 < size; i4++) {
                j.a aVar = this.f4744a.get(i4);
                Fragment fragment = aVar.f4763b;
                if (fragment != null) {
                    fragment.mBackStackNesting += i3;
                    if (h.f4686d) {
                        Log.v("FragmentManager", "Bump nesting of " + aVar.f4763b + " to " + aVar.f4763b.mBackStackNesting);
                    }
                }
            }
        }
    }

    public int i(boolean z3) {
        if (this.f4675t) {
            throw new IllegalStateException("commit already called");
        }
        if (h.f4686d) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter = new PrintWriter(new k0.b("FragmentManager"));
            j("  ", printWriter);
            printWriter.close();
        }
        this.f4675t = true;
        if (this.f4751h) {
            this.f4676u = this.f4674s.m(this);
        } else {
            this.f4676u = -1;
        }
        this.f4674s.h0(this, z3);
        return this.f4676u;
    }

    public void j(String str, PrintWriter printWriter) {
        k(str, printWriter, true);
    }

    public void k(String str, PrintWriter printWriter, boolean z3) {
        String str2;
        if (z3) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f4753j);
            printWriter.print(" mIndex=");
            printWriter.print(this.f4676u);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f4675t);
            if (this.f4749f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f4749f));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f4750g));
            }
            if (this.f4745b != 0 || this.f4746c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f4745b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f4746c));
            }
            if (this.f4747d != 0 || this.f4748e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f4747d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f4748e));
            }
            if (this.f4754k != 0 || this.f4755l != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f4754k));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f4755l);
            }
            if (this.f4756m != 0 || this.f4757n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f4756m));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f4757n);
            }
        }
        if (this.f4744a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f4744a.size();
        for (int i3 = 0; i3 < size; i3++) {
            j.a aVar = this.f4744a.get(i3);
            switch (aVar.f4762a) {
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
                    str2 = "cmd=" + aVar.f4762a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i3);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.f4763b);
            if (z3) {
                if (aVar.f4764c != 0 || aVar.f4765d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f4764c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f4765d));
                }
                if (aVar.f4766e != 0 || aVar.f4767f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f4766e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f4767f));
                }
            }
        }
    }

    public void l() {
        int size = this.f4744a.size();
        for (int i3 = 0; i3 < size; i3++) {
            j.a aVar = this.f4744a.get(i3);
            Fragment fragment = aVar.f4763b;
            if (fragment != null) {
                fragment.setNextTransition(this.f4749f, this.f4750g);
            }
            switch (aVar.f4762a) {
                case 1:
                    fragment.setNextAnim(aVar.f4764c);
                    this.f4674s.k(fragment, false);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f4762a);
                case 3:
                    fragment.setNextAnim(aVar.f4765d);
                    this.f4674s.X0(fragment);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f4765d);
                    this.f4674s.B0(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f4764c);
                    this.f4674s.k1(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f4765d);
                    this.f4674s.w(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f4764c);
                    this.f4674s.p(fragment);
                    break;
                case 8:
                    this.f4674s.j1(fragment);
                    break;
                case 9:
                    this.f4674s.j1(null);
                    break;
                case 10:
                    this.f4674s.i1(fragment, aVar.f4769h);
                    break;
            }
            if (!this.f4760q && aVar.f4762a != 1 && fragment != null) {
                this.f4674s.N0(fragment);
            }
        }
        if (this.f4760q) {
            return;
        }
        h hVar = this.f4674s;
        hVar.O0(hVar.f4702t, true);
    }

    public void m(boolean z3) {
        for (int size = this.f4744a.size() - 1; size >= 0; size--) {
            j.a aVar = this.f4744a.get(size);
            Fragment fragment = aVar.f4763b;
            if (fragment != null) {
                fragment.setNextTransition(h.c1(this.f4749f), this.f4750g);
            }
            switch (aVar.f4762a) {
                case 1:
                    fragment.setNextAnim(aVar.f4767f);
                    this.f4674s.X0(fragment);
                    break;
                case 2:
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + aVar.f4762a);
                case 3:
                    fragment.setNextAnim(aVar.f4766e);
                    this.f4674s.k(fragment, false);
                    break;
                case 4:
                    fragment.setNextAnim(aVar.f4766e);
                    this.f4674s.k1(fragment);
                    break;
                case 5:
                    fragment.setNextAnim(aVar.f4767f);
                    this.f4674s.B0(fragment);
                    break;
                case 6:
                    fragment.setNextAnim(aVar.f4766e);
                    this.f4674s.p(fragment);
                    break;
                case 7:
                    fragment.setNextAnim(aVar.f4767f);
                    this.f4674s.w(fragment);
                    break;
                case 8:
                    this.f4674s.j1(null);
                    break;
                case 9:
                    this.f4674s.j1(fragment);
                    break;
                case 10:
                    this.f4674s.i1(fragment, aVar.f4768g);
                    break;
            }
            if (!this.f4760q && aVar.f4762a != 3 && fragment != null) {
                this.f4674s.N0(fragment);
            }
        }
        if (this.f4760q || !z3) {
            return;
        }
        h hVar = this.f4674s;
        hVar.O0(hVar.f4702t, true);
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
            java.util.ArrayList<r0.j$a> r5 = r0.f4744a
            int r5 = r5.size()
            if (r4 >= r5) goto Lba
            java.util.ArrayList<r0.j$a> r5 = r0.f4744a
            java.lang.Object r5 = r5.get(r4)
            r0.j$a r5 = (r0.j.a) r5
            int r6 = r5.f4762a
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
            java.util.ArrayList<r0.j$a> r6 = r0.f4744a
            r0.j$a r7 = new r0.j$a
            r7.<init>(r11, r3)
            r6.add(r4, r7)
            int r4 = r4 + 1
            androidx.fragment.app.Fragment r3 = r5.f4763b
            goto Lb7
        L41:
            androidx.fragment.app.Fragment r6 = r5.f4763b
            r1.remove(r6)
            androidx.fragment.app.Fragment r5 = r5.f4763b
            if (r5 != r3) goto Lb7
            java.util.ArrayList<r0.j$a> r3 = r0.f4744a
            r0.j$a r6 = new r0.j$a
            r6.<init>(r11, r5)
            r3.add(r4, r6)
            int r4 = r4 + 1
            r3 = r7
            goto Lb7
        L58:
            androidx.fragment.app.Fragment r6 = r5.f4763b
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
            java.util.ArrayList<r0.j$a> r3 = r0.f4744a
            r0.j$a r15 = new r0.j$a
            r15.<init>(r11, r14)
            r3.add(r4, r15)
            int r4 = r4 + 1
            r3 = r7
        L81:
            r0.j$a r15 = new r0.j$a
            r15.<init>(r10, r14)
            int r2 = r5.f4764c
            r15.f4764c = r2
            int r2 = r5.f4766e
            r15.f4766e = r2
            int r2 = r5.f4765d
            r15.f4765d = r2
            int r2 = r5.f4767f
            r15.f4767f = r2
            java.util.ArrayList<r0.j$a> r2 = r0.f4744a
            r2.add(r4, r15)
            r1.remove(r14)
            int r4 = r4 + r8
        L9f:
            int r12 = r12 + (-1)
            goto L62
        La2:
            if (r13 == 0) goto Lac
            java.util.ArrayList<r0.j$a> r2 = r0.f4744a
            r2.remove(r4)
            int r4 = r4 + (-1)
            goto Lb7
        Lac:
            r5.f4762a = r8
            r1.add(r6)
            goto Lb7
        Lb2:
            androidx.fragment.app.Fragment r2 = r5.f4763b
            r1.add(r2)
        Lb7:
            int r4 = r4 + r8
            goto L7
        Lba:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.a.n(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }

    public String o() {
        return this.f4753j;
    }

    public boolean p(int i3) {
        int size = this.f4744a.size();
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f4744a.get(i4).f4763b;
            int i5 = fragment != null ? fragment.mContainerId : 0;
            if (i5 != 0 && i5 == i3) {
                return true;
            }
        }
        return false;
    }

    public boolean q(ArrayList<a> arrayList, int i3, int i4) {
        if (i4 == i3) {
            return false;
        }
        int size = this.f4744a.size();
        int i5 = -1;
        for (int i6 = 0; i6 < size; i6++) {
            Fragment fragment = this.f4744a.get(i6).f4763b;
            int i7 = fragment != null ? fragment.mContainerId : 0;
            if (i7 != 0 && i7 != i5) {
                for (int i8 = i3; i8 < i4; i8++) {
                    a aVar = arrayList.get(i8);
                    int size2 = aVar.f4744a.size();
                    for (int i9 = 0; i9 < size2; i9++) {
                        Fragment fragment2 = aVar.f4744a.get(i9).f4763b;
                        if ((fragment2 != null ? fragment2.mContainerId : 0) == i7) {
                            return true;
                        }
                    }
                }
                i5 = i7;
            }
        }
        return false;
    }

    public boolean s() {
        for (int i3 = 0; i3 < this.f4744a.size(); i3++) {
            if (r(this.f4744a.get(i3))) {
                return true;
            }
        }
        return false;
    }

    public void t() {
        if (this.f4761r != null) {
            for (int i3 = 0; i3 < this.f4761r.size(); i3++) {
                this.f4761r.get(i3).run();
            }
            this.f4761r = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f4676u >= 0) {
            sb.append(" #");
            sb.append(this.f4676u);
        }
        if (this.f4753j != null) {
            sb.append(" ");
            sb.append(this.f4753j);
        }
        sb.append("}");
        return sb.toString();
    }

    public void u(Fragment.e eVar) {
        for (int i3 = 0; i3 < this.f4744a.size(); i3++) {
            j.a aVar = this.f4744a.get(i3);
            if (r(aVar)) {
                aVar.f4763b.setOnStartEnterTransitionListener(eVar);
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
            java.util.ArrayList<r0.j$a> r0 = r5.f4744a
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            if (r0 < 0) goto L35
            java.util.ArrayList<r0.j$a> r2 = r5.f4744a
            java.lang.Object r2 = r2.get(r0)
            r0.j$a r2 = (r0.j.a) r2
            int r3 = r2.f4762a
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
            androidx.lifecycle.Lifecycle$State r3 = r2.f4768g
            r2.f4769h = r3
            goto L32
        L22:
            androidx.fragment.app.Fragment r7 = r2.f4763b
            goto L32
        L25:
            r7 = 0
            goto L32
        L27:
            androidx.fragment.app.Fragment r2 = r2.f4763b
            r6.add(r2)
            goto L32
        L2d:
            androidx.fragment.app.Fragment r2 = r2.f4763b
            r6.remove(r2)
        L32:
            int r0 = r0 + (-1)
            goto L8
        L35:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: r0.a.v(java.util.ArrayList, androidx.fragment.app.Fragment):androidx.fragment.app.Fragment");
    }
}
