package androidx.fragment.app;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.i;
import androidx.lifecycle.d;
import defpackage.hp0;
import defpackage.tp0;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/* JADX INFO: compiled from: BackStackRecord.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends i implements FragmentManager.m {
    public final FragmentManager r;
    public boolean s;
    public int t;

    public a(FragmentManager fragmentManager) {
        fragmentManager.F();
        hp0<?> hp0Var = fragmentManager.u;
        if (hp0Var != null) {
            hp0Var.l.getClassLoader();
        }
        this.t = -1;
        this.r = fragmentManager;
    }

    @Override // androidx.fragment.app.FragmentManager.m
    public final boolean a(ArrayList<a> arrayList, ArrayList<Boolean> arrayList2) {
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.g) {
            return true;
        }
        FragmentManager fragmentManager = this.r;
        if (fragmentManager.d == null) {
            fragmentManager.d = new ArrayList<>();
        }
        fragmentManager.d.add(this);
        return true;
    }

    @Override // androidx.fragment.app.i
    public final void d(int i, Fragment fragment, String str, int i2) {
        String str2 = fragment.Q;
        if (str2 != null) {
            tp0.c(fragment, str2);
        }
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            String str3 = fragment.B;
            if (str3 != null && !str.equals(str3)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.B + " now " + str);
            }
            fragment.B = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            }
            int i3 = fragment.z;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.z + " now " + i);
            }
            fragment.z = i;
            fragment.A = i;
        }
        b(new i.a(i2, fragment));
        fragment.v = this.r;
    }

    public final void f(int i) {
        if (this.g) {
            ArrayList<i.a> arrayList = this.a;
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = arrayList.get(i2).b;
                if (fragment != null) {
                    fragment.u += i;
                }
            }
        }
    }

    public final int g() {
        return h(false);
    }

    public final int h(boolean z) {
        if (this.s) {
            throw new IllegalStateException("commit already called");
        }
        this.s = true;
        boolean z2 = this.g;
        FragmentManager fragmentManager = this.r;
        if (z2) {
            this.t = fragmentManager.i.getAndIncrement();
        } else {
            this.t = -1;
        }
        fragmentManager.u(this, z);
        return this.t;
    }

    public final void i() {
        c();
        this.r.x(this, false);
    }

    public final void j(String str, PrintWriter printWriter) {
        String str2;
        printWriter.print(str);
        printWriter.print("mName=");
        printWriter.print(this.i);
        printWriter.print(" mIndex=");
        printWriter.print(this.t);
        printWriter.print(" mCommitted=");
        printWriter.println(this.s);
        if (this.f != 0) {
            printWriter.print(str);
            printWriter.print("mTransition=#");
            printWriter.print(Integer.toHexString(this.f));
        }
        if (this.b != 0 || this.c != 0) {
            printWriter.print(str);
            printWriter.print("mEnterAnim=#");
            printWriter.print(Integer.toHexString(this.b));
            printWriter.print(" mExitAnim=#");
            printWriter.println(Integer.toHexString(this.c));
        }
        if (this.d != 0 || this.e != 0) {
            printWriter.print(str);
            printWriter.print("mPopEnterAnim=#");
            printWriter.print(Integer.toHexString(this.d));
            printWriter.print(" mPopExitAnim=#");
            printWriter.println(Integer.toHexString(this.e));
        }
        if (this.j != 0 || this.k != null) {
            printWriter.print(str);
            printWriter.print("mBreadCrumbTitleRes=#");
            printWriter.print(Integer.toHexString(this.j));
            printWriter.print(" mBreadCrumbTitleText=");
            printWriter.println(this.k);
        }
        if (this.l != 0 || this.m != null) {
            printWriter.print(str);
            printWriter.print("mBreadCrumbShortTitleRes=#");
            printWriter.print(Integer.toHexString(this.l));
            printWriter.print(" mBreadCrumbShortTitleText=");
            printWriter.println(this.m);
        }
        ArrayList<i.a> arrayList = this.a;
        if (arrayList.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            i.a aVar = arrayList.get(i);
            switch (aVar.a) {
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
                    str2 = "cmd=" + aVar.a;
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(str2);
            printWriter.print(" ");
            printWriter.println(aVar.b);
            if (aVar.d != 0 || aVar.e != 0) {
                printWriter.print(str);
                printWriter.print("enterAnim=#");
                printWriter.print(Integer.toHexString(aVar.d));
                printWriter.print(" exitAnim=#");
                printWriter.println(Integer.toHexString(aVar.e));
            }
            if (aVar.f != 0 || aVar.g != 0) {
                printWriter.print(str);
                printWriter.print("popEnterAnim=#");
                printWriter.print(Integer.toHexString(aVar.f));
                printWriter.print(" popExitAnim=#");
                printWriter.println(Integer.toHexString(aVar.g));
            }
        }
    }

    public final a k(Fragment fragment) {
        FragmentManager fragmentManager = fragment.v;
        if (fragmentManager == null || fragmentManager == this.r) {
            b(new i.a(3, fragment));
            return this;
        }
        throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment " + fragment.toString() + " is already attached to a FragmentManager.");
    }

    public final a l(Fragment fragment, d.b bVar) {
        FragmentManager fragmentManager = fragment.v;
        FragmentManager fragmentManager2 = this.r;
        if (fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + fragmentManager2);
        }
        if (bVar == d.b.INITIALIZED && fragment.e > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + bVar + " after the Fragment has been created");
        }
        if (bVar != d.b.DESTROYED) {
            b(new i.a(fragment, bVar));
            return this;
        }
        throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + bVar + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.t >= 0) {
            sb.append(" #");
            sb.append(this.t);
        }
        if (this.i != null) {
            sb.append(" ");
            sb.append(this.i);
        }
        sb.append("}");
        return sb.toString();
    }
}
