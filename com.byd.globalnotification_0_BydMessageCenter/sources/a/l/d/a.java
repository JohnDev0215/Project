package a.l.d;

import a.l.d.r;
import a.l.d.z;
import android.util.Log;
import androidx.fragment.app.Fragment;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class a extends z implements r.e {
    public final r q;
    public int r;

    /* JADX WARN: Illegal instructions before constructor call */
    public a(r rVar) {
        n nVarH = rVar.H();
        o<?> oVar = rVar.n;
        super(nVarH, oVar != null ? oVar.f1018b.getClassLoader() : null);
        this.r = -1;
        this.q = rVar;
    }

    public static boolean j(z.a aVar) {
        Fragment fragment = aVar.f1068b;
        if (fragment == null || !fragment.k || fragment.F == null || fragment.z || fragment.y) {
            return false;
        }
        Fragment.b bVar = fragment.I;
        return bVar == null ? false : bVar.p;
    }

    @Override // a.l.d.r.e
    public boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        if (r.J(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.g) {
            return true;
        }
        r rVar = this.q;
        if (rVar.f1028d == null) {
            rVar.f1028d = new ArrayList<>();
        }
        rVar.f1028d.add(this);
        return true;
    }

    @Override // a.l.d.z
    public void c(int i, Fragment fragment, String str, int i2) {
        super.c(i, fragment, str, i2);
        fragment.r = this.q;
    }

    public void d(int i) {
        if (this.g) {
            if (r.J(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            int size = this.f1061a.size();
            for (int i2 = 0; i2 < size; i2++) {
                z.a aVar = this.f1061a.get(i2);
                Fragment fragment = aVar.f1068b;
                if (fragment != null) {
                    fragment.q += i;
                    if (r.J(2)) {
                        StringBuilder sbD = b.a.a.a.a.d("Bump nesting of ");
                        sbD.append(aVar.f1068b);
                        sbD.append(" to ");
                        sbD.append(aVar.f1068b.q);
                        Log.v("FragmentManager", sbD.toString());
                    }
                }
            }
        }
    }

    public void e(String str, PrintWriter printWriter, boolean z) {
        String string;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.h);
            printWriter.print(" mIndex=");
            printWriter.print(this.r);
            printWriter.print(" mCommitted=");
            printWriter.println(false);
            if (this.f1066f != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f1066f));
            }
            if (this.f1062b != 0 || this.f1063c != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1062b));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1063c));
            }
            if (this.f1064d != 0 || this.f1065e != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f1064d));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f1065e));
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
        if (this.f1061a.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.f1061a.size();
        for (int i = 0; i < size; i++) {
            z.a aVar = this.f1061a.get(i);
            switch (aVar.f1067a) {
                case 0:
                    string = "NULL";
                    break;
                case 1:
                    string = "ADD";
                    break;
                case 2:
                    string = "REPLACE";
                    break;
                case 3:
                    string = "REMOVE";
                    break;
                case 4:
                    string = "HIDE";
                    break;
                case 5:
                    string = "SHOW";
                    break;
                case 6:
                    string = "DETACH";
                    break;
                case 7:
                    string = "ATTACH";
                    break;
                case 8:
                    string = "SET_PRIMARY_NAV";
                    break;
                case 9:
                    string = "UNSET_PRIMARY_NAV";
                    break;
                case 10:
                    string = "OP_SET_MAX_LIFECYCLE";
                    break;
                default:
                    StringBuilder sbD = b.a.a.a.a.d("cmd=");
                    sbD.append(aVar.f1067a);
                    string = sbD.toString();
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(string);
            printWriter.print(" ");
            printWriter.println(aVar.f1068b);
            if (z) {
                if (aVar.f1069c != 0 || aVar.f1070d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1069c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1070d));
                }
                if (aVar.f1071e != 0 || aVar.f1072f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(aVar.f1071e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(aVar.f1072f));
                }
            }
        }
    }

    public void f() {
        int size = this.f1061a.size();
        for (int i = 0; i < size; i++) {
            z.a aVar = this.f1061a.get(i);
            Fragment fragment = aVar.f1068b;
            if (fragment != null) {
                int i2 = this.f1066f;
                if (fragment.I != null || i2 != 0) {
                    fragment.g();
                    fragment.I.f1565e = i2;
                }
            }
            switch (aVar.f1067a) {
                case 1:
                    fragment.L(aVar.f1069c);
                    this.q.Y(fragment, false);
                    this.q.b(fragment);
                    break;
                case 2:
                default:
                    StringBuilder sbD = b.a.a.a.a.d("Unknown cmd: ");
                    sbD.append(aVar.f1067a);
                    throw new IllegalArgumentException(sbD.toString());
                case 3:
                    fragment.L(aVar.f1070d);
                    this.q.T(fragment);
                    break;
                case 4:
                    fragment.L(aVar.f1070d);
                    this.q.I(fragment);
                    break;
                case 5:
                    fragment.L(aVar.f1069c);
                    this.q.Y(fragment, false);
                    this.q.c0(fragment);
                    break;
                case 6:
                    fragment.L(aVar.f1070d);
                    this.q.i(fragment);
                    break;
                case 7:
                    fragment.L(aVar.f1069c);
                    this.q.Y(fragment, false);
                    this.q.d(fragment);
                    break;
                case 8:
                    this.q.a0(fragment);
                    break;
                case 9:
                    this.q.a0(null);
                    break;
                case 10:
                    this.q.Z(fragment, aVar.h);
                    break;
            }
            if (!this.o && aVar.f1067a != 1 && fragment != null) {
                this.q.O(fragment);
            }
        }
        if (this.o) {
            return;
        }
        r rVar = this.q;
        rVar.P(rVar.m, true);
    }

    public void g(boolean z) {
        for (int size = this.f1061a.size() - 1; size >= 0; size--) {
            z.a aVar = this.f1061a.get(size);
            Fragment fragment = aVar.f1068b;
            if (fragment != null) {
                int i = this.f1066f;
                int i2 = i != 4097 ? i != 4099 ? i != 8194 ? 0 : 4097 : 4099 : 8194;
                if (fragment.I != null || i2 != 0) {
                    fragment.g();
                    fragment.I.f1565e = i2;
                }
            }
            switch (aVar.f1067a) {
                case 1:
                    fragment.L(aVar.f1072f);
                    this.q.Y(fragment, true);
                    this.q.T(fragment);
                    break;
                case 2:
                default:
                    StringBuilder sbD = b.a.a.a.a.d("Unknown cmd: ");
                    sbD.append(aVar.f1067a);
                    throw new IllegalArgumentException(sbD.toString());
                case 3:
                    fragment.L(aVar.f1071e);
                    this.q.b(fragment);
                    break;
                case 4:
                    fragment.L(aVar.f1071e);
                    this.q.c0(fragment);
                    break;
                case 5:
                    fragment.L(aVar.f1072f);
                    this.q.Y(fragment, true);
                    this.q.I(fragment);
                    break;
                case 6:
                    fragment.L(aVar.f1071e);
                    this.q.d(fragment);
                    break;
                case 7:
                    fragment.L(aVar.f1072f);
                    this.q.Y(fragment, true);
                    this.q.i(fragment);
                    break;
                case 8:
                    this.q.a0(null);
                    break;
                case 9:
                    this.q.a0(fragment);
                    break;
                case 10:
                    this.q.Z(fragment, aVar.g);
                    break;
            }
            if (!this.o && aVar.f1067a != 3 && fragment != null) {
                this.q.O(fragment);
            }
        }
        if (this.o || !z) {
            return;
        }
        r rVar = this.q;
        rVar.P(rVar.m, true);
    }

    public boolean h(int i) {
        int size = this.f1061a.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1061a.get(i2).f1068b;
            int i3 = fragment != null ? fragment.w : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean i(ArrayList<a> arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.f1061a.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            Fragment fragment = this.f1061a.get(i4).f1068b;
            int i5 = fragment != null ? fragment.w : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    a aVar = arrayList.get(i6);
                    int size2 = aVar.f1061a.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        Fragment fragment2 = aVar.f1061a.get(i7).f1068b;
                        if ((fragment2 != null ? fragment2.w : 0) == i5) {
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
        if (this.r >= 0) {
            sb.append(" #");
            sb.append(this.r);
        }
        if (this.h != null) {
            sb.append(" ");
            sb.append(this.h);
        }
        sb.append("}");
        return sb.toString();
    }
}
