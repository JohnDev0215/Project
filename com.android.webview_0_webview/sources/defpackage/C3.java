package defpackage;

import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C3 extends AbstractC1197n4 implements InterfaceC0807g4 {
    public final LayoutInflaterFactory2C0974j4 a;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public boolean i;
    public String j;
    public boolean k;
    public int m;
    public CharSequence n;
    public int o;
    public CharSequence p;
    public ArrayList q;
    public ArrayList r;
    public ArrayList b = new ArrayList();
    public int l = -1;
    public boolean s = false;

    public C3(LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4) {
        this.a = layoutInflaterFactory2C0974j4;
    }

    public static boolean l(B3 b3) {
        K3 k3 = b3.b;
        if (k3 == null || !k3.B || k3.Z == null || k3.S || k3.R) {
            return false;
        }
        H3 h3 = k3.d0;
        return false;
    }

    @Override // defpackage.InterfaceC0807g4
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        boolean z = LayoutInflaterFactory2C0974j4.U;
        arrayList.add(this);
        arrayList2.add(Boolean.FALSE);
        if (!this.i) {
            return true;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.a;
        if (layoutInflaterFactory2C0974j4.x == null) {
            layoutInflaterFactory2C0974j4.x = new ArrayList();
        }
        layoutInflaterFactory2C0974j4.x.add(this);
        return true;
    }

    public void b(B3 b3) {
        this.b.add(b3);
        b3.c = this.c;
        b3.d = this.d;
        b3.e = this.e;
        b3.f = this.f;
    }

    public void c(int i) {
        if (this.i) {
            boolean z = LayoutInflaterFactory2C0974j4.U;
            int size = this.b.size();
            for (int i2 = 0; i2 < size; i2++) {
                K3 k3 = ((B3) this.b.get(i2)).b;
                if (k3 != null) {
                    k3.H += i;
                    boolean z2 = LayoutInflaterFactory2C0974j4.U;
                }
            }
        }
    }

    public int d() {
        return e(false);
    }

    public int e(boolean z) {
        int size;
        if (this.k) {
            throw new IllegalStateException("commit already called");
        }
        boolean z2 = LayoutInflaterFactory2C0974j4.U;
        this.k = true;
        if (this.i) {
            LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.a;
            synchronized (layoutInflaterFactory2C0974j4) {
                if (layoutInflaterFactory2C0974j4.A == null || layoutInflaterFactory2C0974j4.A.size() <= 0) {
                    if (layoutInflaterFactory2C0974j4.z == null) {
                        layoutInflaterFactory2C0974j4.z = new ArrayList();
                    }
                    size = layoutInflaterFactory2C0974j4.z.size();
                    layoutInflaterFactory2C0974j4.z.add(this);
                } else {
                    size = ((Integer) layoutInflaterFactory2C0974j4.A.remove(layoutInflaterFactory2C0974j4.A.size() - 1)).intValue();
                    layoutInflaterFactory2C0974j4.z.set(size, this);
                }
            }
            this.l = size;
        } else {
            this.l = -1;
        }
        this.a.M(this, z);
        return this.l;
    }

    public final void f(int i, K3 k3, String str, int i2) {
        Class<?> cls = k3.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            StringBuilder sbF = Z7.f("Fragment ");
            sbF.append(cls.getCanonicalName());
            sbF.append(" must be a public static class to be  properly recreated from");
            sbF.append(" instance state.");
            throw new IllegalStateException(sbF.toString());
        }
        k3.I = this.a;
        if (str != null) {
            String str2 = k3.Q;
            if (str2 != null && !str.equals(str2)) {
                throw new IllegalStateException("Can't change tag of fragment " + k3 + ": was " + k3.Q + " now " + str);
            }
            k3.Q = str;
        }
        if (i != 0) {
            if (i == -1) {
                throw new IllegalArgumentException("Can't add fragment " + k3 + " with tag " + str + " to container view with no id");
            }
            int i3 = k3.O;
            if (i3 != 0 && i3 != i) {
                throw new IllegalStateException("Can't change container ID of fragment " + k3 + ": was " + k3.O + " now " + i);
            }
            k3.O = i;
            k3.P = i;
        }
        b(new B3(i2, k3));
    }

    public void g(String str, PrintWriter printWriter, boolean z) {
        String string;
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.j);
            printWriter.print(" mIndex=");
            printWriter.print(this.l);
            printWriter.print(" mCommitted=");
            printWriter.println(this.k);
            if (this.g != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.g));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.h));
            }
            if (this.c != 0 || this.d != 0) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.c));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.d));
            }
            if (this.e != 0 || this.f != 0) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.e));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f));
            }
            if (this.m != 0 || this.n != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.m));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.n);
            }
            if (this.o != 0 || this.p != null) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.o));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.p);
            }
        }
        if (this.b.isEmpty()) {
            return;
        }
        printWriter.print(str);
        printWriter.println("Operations:");
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            B3 b3 = (B3) this.b.get(i);
            switch (b3.a) {
                case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
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
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    string = "HIDE";
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    string = "SHOW";
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
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
                default:
                    StringBuilder sbF = Z7.f("cmd=");
                    sbF.append(b3.a);
                    string = sbF.toString();
                    break;
            }
            printWriter.print(str);
            printWriter.print("  Op #");
            printWriter.print(i);
            printWriter.print(": ");
            printWriter.print(string);
            printWriter.print(" ");
            printWriter.println(b3.b);
            if (z) {
                if (b3.c != 0 || b3.d != 0) {
                    printWriter.print(str);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString(b3.c));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString(b3.d));
                }
                if (b3.e != 0 || b3.f != 0) {
                    printWriter.print(str);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString(b3.e));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString(b3.f));
                }
            }
        }
    }

    public void h() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            B3 b3 = (B3) this.b.get(i);
            K3 k3 = b3.b;
            if (k3 != null) {
                int i2 = this.g;
                int i3 = this.h;
                if (k3.d0 != null || i2 != 0 || i3 != 0) {
                    k3.d();
                    H3 h3 = k3.d0;
                    h3.e = i2;
                    h3.f = i3;
                }
            }
            switch (b3.a) {
                case 1:
                    k3.d0(b3.c);
                    this.a.b(k3, false);
                    break;
                case 2:
                default:
                    StringBuilder sbF = Z7.f("Unknown cmd: ");
                    sbF.append(b3.a);
                    throw new IllegalArgumentException(sbF.toString());
                case 3:
                    k3.d0(b3.d);
                    this.a.h0(k3);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    k3.d0(b3.d);
                    Objects.requireNonNull(this.a);
                    if (!k3.R) {
                        k3.R = true;
                        k3.f0 = !k3.f0;
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    k3.d0(b3.c);
                    Objects.requireNonNull(this.a);
                    if (k3.R) {
                        k3.R = false;
                        k3.f0 = !k3.f0;
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    k3.d0(b3.d);
                    this.a.h(k3);
                    break;
                case 7:
                    k3.d0(b3.c);
                    this.a.c(k3);
                    break;
                case 8:
                    this.a.q0(k3);
                    break;
                case 9:
                    this.a.q0(null);
                    break;
            }
            if (!this.s && b3.a != 1 && k3 != null) {
                this.a.a0(k3);
            }
        }
        if (this.s) {
            return;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.a;
        layoutInflaterFactory2C0974j4.b0(layoutInflaterFactory2C0974j4.C, true);
    }

    public void i(boolean z) {
        for (int size = this.b.size() - 1; size >= 0; size--) {
            B3 b3 = (B3) this.b.get(size);
            K3 k3 = b3.b;
            if (k3 != null) {
                int iK0 = LayoutInflaterFactory2C0974j4.k0(this.g);
                int i = this.h;
                if (k3.d0 != null || iK0 != 0 || i != 0) {
                    k3.d();
                    H3 h3 = k3.d0;
                    h3.e = iK0;
                    h3.f = i;
                }
            }
            switch (b3.a) {
                case 1:
                    k3.d0(b3.f);
                    this.a.h0(k3);
                    break;
                case 2:
                default:
                    StringBuilder sbF = Z7.f("Unknown cmd: ");
                    sbF.append(b3.a);
                    throw new IllegalArgumentException(sbF.toString());
                case 3:
                    k3.d0(b3.e);
                    this.a.b(k3, false);
                    break;
                case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                    k3.d0(b3.e);
                    Objects.requireNonNull(this.a);
                    if (k3.R) {
                        k3.R = false;
                        k3.f0 = !k3.f0;
                    }
                    break;
                case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                    k3.d0(b3.f);
                    Objects.requireNonNull(this.a);
                    if (!k3.R) {
                        k3.R = true;
                        k3.f0 = !k3.f0;
                    }
                    break;
                case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                    k3.d0(b3.e);
                    this.a.c(k3);
                    break;
                case 7:
                    k3.d0(b3.f);
                    this.a.h(k3);
                    break;
                case 8:
                    this.a.q0(null);
                    break;
                case 9:
                    this.a.q0(k3);
                    break;
            }
            if (!this.s && b3.a != 3 && k3 != null) {
                this.a.a0(k3);
            }
        }
        if (this.s || !z) {
            return;
        }
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4 = this.a;
        layoutInflaterFactory2C0974j4.b0(layoutInflaterFactory2C0974j4.C, true);
    }

    public boolean j(int i) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            K3 k3 = ((B3) this.b.get(i2)).b;
            int i3 = k3 != null ? k3.P : 0;
            if (i3 != 0 && i3 == i) {
                return true;
            }
        }
        return false;
    }

    public boolean k(ArrayList arrayList, int i, int i2) {
        if (i2 == i) {
            return false;
        }
        int size = this.b.size();
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            K3 k3 = ((B3) this.b.get(i4)).b;
            int i5 = k3 != null ? k3.P : 0;
            if (i5 != 0 && i5 != i3) {
                for (int i6 = i; i6 < i2; i6++) {
                    C3 c3 = (C3) arrayList.get(i6);
                    int size2 = c3.b.size();
                    for (int i7 = 0; i7 < size2; i7++) {
                        K3 k32 = ((B3) c3.b.get(i7)).b;
                        if ((k32 != null ? k32.P : 0) == i5) {
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
        if (this.l >= 0) {
            sb.append(" #");
            sb.append(this.l);
        }
        if (this.j != null) {
            sb.append(" ");
            sb.append(this.j);
        }
        sb.append("}");
        return sb.toString();
    }
}
