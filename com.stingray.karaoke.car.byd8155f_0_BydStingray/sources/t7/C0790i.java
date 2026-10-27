package T7;

import F8.W;
import Q7.C0661n;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0656i;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0666t;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import o8.AbstractC2063h;
import o8.C2061f;

/* JADX INFO: renamed from: T7.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0790i extends t implements InterfaceC0656i {

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final boolean f8843P;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0790i(InterfaceC0652e interfaceC0652e, InterfaceC0656i interfaceC0656i, R7.h hVar, boolean z6, int i7, Q7.N n10) {
        super(i7, interfaceC0652e, interfaceC0656i, n10, hVar, AbstractC2063h.f20577e);
        if (interfaceC0652e == null) {
            K0(0);
            throw null;
        }
        if (hVar == null) {
            K0(1);
            throw null;
        }
        if (i7 == 0) {
            K0(2);
            throw null;
        }
        if (n10 == null) {
            K0(3);
            throw null;
        }
        this.f8843P = z6;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void K0(int r8) {
        /*
            Method dump skipped, instruction units count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T7.C0790i.K0(int):void");
    }

    @Override // Q7.InterfaceC0656i
    public final InterfaceC0652e J() {
        InterfaceC0652e interfaceC0652eO = o();
        if (interfaceC0652eO != null) {
            return interfaceC0652eO;
        }
        K0(18);
        throw null;
    }

    @Override // T7.t, Q7.InterfaceC0657j
    public final Object S(InterfaceC0659l interfaceC0659l, Object obj) {
        return interfaceC0659l.B(this, obj);
    }

    @Override // T7.t, Q7.InterfaceC0650c
    public final InterfaceC0650c a0(InterfaceC0652e interfaceC0652e, int i7, C0661n c0661n) {
        return (C0790i) c1(interfaceC0652e, i7, c0661n);
    }

    @Override // T7.t, Q7.InterfaceC0650c
    public final void h0(Collection collection) {
        if (collection != null) {
            return;
        }
        K0(22);
        throw null;
    }

    @Override // T7.t
    /* JADX INFO: renamed from: n1, reason: merged with bridge method [inline-methods] */
    public C0790i e1(int i7, InterfaceC0657j interfaceC0657j, InterfaceC0666t interfaceC0666t, Q7.N n10, R7.h hVar, C2061f c2061f) {
        if (interfaceC0657j == null) {
            K0(23);
            throw null;
        }
        if (i7 == 0) {
            K0(24);
            throw null;
        }
        if (hVar == null) {
            K0(25);
            throw null;
        }
        if (i7 == 1 || i7 == 4) {
            return new C0790i((InterfaceC0652e) interfaceC0657j, this, hVar, this.f8843P, 1, n10);
        }
        throw new IllegalStateException("Attempt at creating a constructor that is not a declaration: \ncopy from: " + this + "\nnewOwner: " + interfaceC0657j + "\nkind: " + M5.b.u(i7));
    }

    @Override // T7.AbstractC0795n, Q7.InterfaceC0657j
    /* JADX INFO: renamed from: o1, reason: merged with bridge method [inline-methods] */
    public final InterfaceC0652e o() {
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) super.o();
        if (interfaceC0652e != null) {
            return interfaceC0652e;
        }
        K0(17);
        throw null;
    }

    @Override // T7.AbstractC0795n
    /* JADX INFO: renamed from: p1, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final C0790i b1() {
        C0790i c0790i = (C0790i) super.b1();
        if (c0790i != null) {
            return c0790i;
        }
        K0(19);
        throw null;
    }

    @Override // T7.t, Q7.InterfaceC0650c, Q7.InterfaceC0649b
    public final Collection q() {
        Set setEmptySet = Collections.emptySet();
        if (setEmptySet != null) {
            return setEmptySet;
        }
        K0(21);
        throw null;
    }

    public final void q1(List list, C0661n c0661n) {
        if (list == null) {
            K0(13);
            throw null;
        }
        if (c0661n != null) {
            r1(list, c0661n, o().s());
        } else {
            K0(14);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r1(java.util.List r12, Q7.C0661n r13, java.util.List r14) {
        /*
            r11 = this;
            r0 = 0
            if (r12 == 0) goto L62
            if (r13 == 0) goto L5c
            if (r14 == 0) goto L56
            Q7.e r1 = r11.o()
            boolean r2 = r1.d0()
            if (r2 == 0) goto L21
            Q7.j r1 = r1.o()
            boolean r2 = r1 instanceof Q7.InterfaceC0652e
            if (r2 == 0) goto L21
            Q7.e r1 = (Q7.InterfaceC0652e) r1
            T7.u r1 = r1.J0()
            r4 = r1
            goto L22
        L21:
            r4 = r0
        L22:
            Q7.e r1 = r11.o()
            java.util.List r2 = r1.A0()
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L3e
            java.util.List r1 = r1.A0()
            if (r1 == 0) goto L38
        L36:
            r5 = r1
            goto L45
        L38:
            r11 = 15
            K0(r11)
            throw r0
        L3e:
            java.util.List r1 = java.util.Collections.emptyList()
            if (r1 == 0) goto L50
            goto L36
        L45:
            r3 = 0
            r8 = 0
            r9 = 1
            r2 = r11
            r6 = r14
            r7 = r12
            r10 = r13
            r2.h1(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        L50:
            r11 = 16
            K0(r11)
            throw r0
        L56:
            r11 = 12
            K0(r11)
            throw r0
        L5c:
            r11 = 11
            K0(r11)
            throw r0
        L62:
            r11 = 10
            K0(r11)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: T7.C0790i.r1(java.util.List, Q7.n, java.util.List):void");
    }

    @Override // T7.t, Q7.InterfaceC0666t, Q7.P
    /* JADX INFO: renamed from: s1, reason: merged with bridge method [inline-methods] */
    public final C0790i f(W w10) {
        if (w10 != null) {
            return (C0790i) super.f(w10);
        }
        K0(20);
        throw null;
    }
}
