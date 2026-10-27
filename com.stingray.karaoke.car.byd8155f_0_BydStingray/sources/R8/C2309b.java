package r8;

import F8.K;
import Q7.D;
import Q7.InterfaceC0649b;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0670x;
import Q7.N;
import Q7.Q;
import T7.B;
import java.util.Collection;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: r8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2309b implements G8.c {
    public static final C2309b l = new C2309b();

    public static /* synthetic */ void b(int i7) {
        Object[] objArr = new Object[3];
        if (i7 != 1) {
            objArr[0] = "a";
        } else {
            objArr[0] = "b";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/OverridingUtil$1";
        objArr[2] = "equals";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public static N f(InterfaceC0649b interfaceC0649b) {
        while (interfaceC0649b instanceof InterfaceC0650c) {
            InterfaceC0650c interfaceC0650c = (InterfaceC0650c) interfaceC0649b;
            if (interfaceC0650c.N() != 2) {
                break;
            }
            Collection collectionQ = interfaceC0650c.q();
            A7.m.e(collectionQ, "overriddenDescriptors");
            interfaceC0649b = (InterfaceC0650c) AbstractC1998m.s0(collectionQ);
            if (interfaceC0649b == null) {
                return null;
            }
        }
        return interfaceC0649b.g();
    }

    @Override // G8.c
    public boolean a(K k10, K k11) {
        if (k10 == null) {
            b(0);
            throw null;
        }
        if (k11 != null) {
            return k10.equals(k11);
        }
        b(1);
        throw null;
    }

    public boolean c(InterfaceC0657j interfaceC0657j, InterfaceC0657j interfaceC0657j2, boolean z6, boolean z10) {
        if ((interfaceC0657j instanceof InterfaceC0652e) && (interfaceC0657j2 instanceof InterfaceC0652e)) {
            return A7.m.a(((InterfaceC0652e) interfaceC0657j).C(), ((InterfaceC0652e) interfaceC0657j2).C());
        }
        if ((interfaceC0657j instanceof Q) && (interfaceC0657j2 instanceof Q)) {
            return d((Q) interfaceC0657j, (Q) interfaceC0657j2, z6, C2308a.o);
        }
        if (!(interfaceC0657j instanceof InterfaceC0649b) || !(interfaceC0657j2 instanceof InterfaceC0649b)) {
            return ((interfaceC0657j instanceof D) && (interfaceC0657j2 instanceof D)) ? A7.m.a(((B) ((D) interfaceC0657j)).f8767q, ((B) ((D) interfaceC0657j2)).f8767q) : A7.m.a(interfaceC0657j, interfaceC0657j2);
        }
        InterfaceC0649b interfaceC0649b = (InterfaceC0649b) interfaceC0657j;
        InterfaceC0649b interfaceC0649b2 = (InterfaceC0649b) interfaceC0657j2;
        A7.m.f(interfaceC0649b, "a");
        A7.m.f(interfaceC0649b2, "b");
        boolean z11 = true;
        if (!interfaceC0649b.equals(interfaceC0649b2)) {
            if (!A7.m.a(interfaceC0649b.getName(), interfaceC0649b2.getName()) || ((z10 && (interfaceC0649b instanceof InterfaceC0670x) && (interfaceC0649b2 instanceof InterfaceC0670x) && ((InterfaceC0670x) interfaceC0649b).c0() != ((InterfaceC0670x) interfaceC0649b2).c0()) || ((A7.m.a(interfaceC0649b.o(), interfaceC0649b2.o()) && (!z6 || !A7.m.a(f(interfaceC0649b), f(interfaceC0649b2)))) || d.o(interfaceC0649b) || d.o(interfaceC0649b2) || !e(interfaceC0649b, interfaceC0649b2, C2308a.f21878n, z6)))) {
                return false;
            }
            l lVar = new l(new t4.e(10, interfaceC0649b, interfaceC0649b2, z6));
            if (lVar.m(interfaceC0649b, interfaceC0649b2, null, true).c() != 1 || lVar.m(interfaceC0649b2, interfaceC0649b, null, true).c() != 1) {
                z11 = false;
            }
        }
        return z11;
    }

    public boolean d(Q q4, Q q10, boolean z6, InterfaceC2785n interfaceC2785n) {
        A7.m.f(q4, "a");
        A7.m.f(q10, "b");
        A7.m.f(interfaceC2785n, "equivalentCallables");
        if (q4.equals(q10)) {
            return true;
        }
        return !A7.m.a(q4.o(), q10.o()) && e(q4, q10, interfaceC2785n, z6) && q4.getIndex() == q10.getIndex();
    }

    public boolean e(InterfaceC0657j interfaceC0657j, InterfaceC0657j interfaceC0657j2, InterfaceC2785n interfaceC2785n, boolean z6) {
        InterfaceC0657j interfaceC0657jO = interfaceC0657j.o();
        InterfaceC0657j interfaceC0657jO2 = interfaceC0657j2.o();
        return ((interfaceC0657jO instanceof InterfaceC0650c) || (interfaceC0657jO2 instanceof InterfaceC0650c)) ? ((Boolean) interfaceC2785n.invoke(interfaceC0657jO, interfaceC0657jO2)).booleanValue() : c(interfaceC0657jO, interfaceC0657jO2, z6, true);
    }
}
