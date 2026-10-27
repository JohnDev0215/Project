package r8;

import F8.A;
import F8.AbstractC0287w;
import Q7.C0667u;
import Q7.C0672z;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.L;
import Q7.S;
import Q7.U;
import T7.I;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    static {
        C2057b.j(new C2058c("kotlin.jvm.JvmInline"));
    }

    public static final boolean a(InterfaceC0650c interfaceC0650c) {
        A7.m.f(interfaceC0650c, "<this>");
        if (interfaceC0650c instanceof I) {
            L lC1 = ((I) interfaceC0650c).c1();
            A7.m.e(lC1, "correspondingProperty");
            if (d(lC1)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean b(InterfaceC0657j interfaceC0657j) {
        A7.m.f(interfaceC0657j, "<this>");
        return (interfaceC0657j instanceof InterfaceC0652e) && (((InterfaceC0652e) interfaceC0657j).m0() instanceof C0667u);
    }

    public static final boolean c(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN != null) {
            return b(interfaceC0654gN);
        }
        return false;
    }

    public static final boolean d(U u4) {
        if (u4.K() == null) {
            InterfaceC0657j interfaceC0657jO = u4.o();
            C2061f c2061f = null;
            InterfaceC0652e interfaceC0652e = interfaceC0657jO instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657jO : null;
            if (interfaceC0652e != null) {
                int i7 = AbstractC2564d.f23040a;
                S sM0 = interfaceC0652e.m0();
                C0667u c0667u = sM0 instanceof C0667u ? (C0667u) sM0 : null;
                if (c0667u != null) {
                    c2061f = c0667u.f7207a;
                }
            }
            if (A7.m.a(c2061f, u4.getName())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean e(InterfaceC0657j interfaceC0657j) {
        return b(interfaceC0657j) || ((interfaceC0657j instanceof InterfaceC0652e) && (((InterfaceC0652e) interfaceC0657j).m0() instanceof C0672z));
    }

    public static final A f(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "<this>");
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        InterfaceC0652e interfaceC0652e = interfaceC0654gN instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0654gN : null;
        if (interfaceC0652e == null) {
            return null;
        }
        int i7 = AbstractC2564d.f23040a;
        S sM0 = interfaceC0652e.m0();
        C0667u c0667u = sM0 instanceof C0667u ? (C0667u) sM0 : null;
        if (c0667u != null) {
            return (A) c0667u.f7208b;
        }
        return null;
    }
}
