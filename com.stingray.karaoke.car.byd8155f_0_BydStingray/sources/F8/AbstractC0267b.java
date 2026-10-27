package F8;

import Q7.InterfaceC0652e;
import o8.C2061f;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: F8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0267b extends AbstractC0272g {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC0267b(E8.o oVar) {
        super(oVar);
        if (oVar != null) {
        } else {
            h(0);
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void h(int r9) {
        /*
            r0 = 4
            r1 = 3
            r2 = 1
            if (r9 == r2) goto Lc
            if (r9 == r1) goto Lc
            if (r9 == r0) goto Lc
            java.lang.String r3 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            goto Le
        Lc:
            java.lang.String r3 = "@NotNull method %s.%s must not return null"
        Le:
            r4 = 2
            if (r9 == r2) goto L17
            if (r9 == r1) goto L17
            if (r9 == r0) goto L17
            r5 = r1
            goto L18
        L17:
            r5 = r4
        L18:
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = "kotlin/reflect/jvm/internal/impl/types/AbstractClassTypeConstructor"
            r7 = 0
            if (r9 == r2) goto L2f
            if (r9 == r4) goto L2a
            if (r9 == r1) goto L2f
            if (r9 == r0) goto L2f
            java.lang.String r8 = "storageManager"
            r5[r7] = r8
            goto L31
        L2a:
            java.lang.String r8 = "classifier"
            r5[r7] = r8
            goto L31
        L2f:
            r5[r7] = r6
        L31:
            if (r9 == r2) goto L3f
            if (r9 == r1) goto L3a
            if (r9 == r0) goto L3a
            r5[r2] = r6
            goto L43
        L3a:
            java.lang.String r6 = "getAdditionalNeighboursInSupertypeGraph"
            r5[r2] = r6
            goto L43
        L3f:
            java.lang.String r6 = "getBuiltIns"
            r5[r2] = r6
        L43:
            if (r9 == r2) goto L54
            if (r9 == r4) goto L50
            if (r9 == r1) goto L54
            if (r9 == r0) goto L54
            java.lang.String r6 = "<init>"
            r5[r4] = r6
            goto L54
        L50:
            java.lang.String r6 = "isSameClassifier"
            r5[r4] = r6
        L54:
            java.lang.String r3 = java.lang.String.format(r3, r5)
            if (r9 == r2) goto L64
            if (r9 == r1) goto L64
            if (r9 == r0) goto L64
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            r9.<init>(r3)
            goto L69
        L64:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r3)
        L69:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.AbstractC0267b.h(int):void");
    }

    @Override // F8.AbstractC0272g
    public final AbstractC0287w c() {
        InterfaceC0652e interfaceC0652eN = n();
        if (interfaceC0652eN == null) {
            N7.i.a(107);
            throw null;
        }
        C2061f c2061f = N7.i.f4796e;
        if (N7.i.b(interfaceC0652eN, N7.n.f4843a) || N7.i.b(interfaceC0652eN, N7.n.f4845b)) {
            return null;
        }
        return l().e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x006f, code lost:
    
        r4 = true;
     */
    @Override // F8.AbstractC0272g
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean f(Q7.InterfaceC0654g r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof Q7.InterfaceC0652e
            r1 = 0
            if (r0 == 0) goto L73
            Q7.e r4 = r4.n()
            java.lang.String r0 = "first"
            A7.m.f(r4, r0)
            o8.f r0 = r4.getName()
            o8.f r2 = r5.getName()
            boolean r0 = A7.m.a(r0, r2)
            r2 = 1
            if (r0 != 0) goto L1f
        L1d:
            r4 = r1
            goto L70
        L1f:
            Q7.j r4 = r4.o()
            Q7.j r5 = r5.o()
        L27:
            if (r4 == 0) goto L6f
            if (r5 == 0) goto L6f
            boolean r0 = r4 instanceof Q7.InterfaceC0671y
            if (r0 == 0) goto L32
            boolean r4 = r5 instanceof Q7.InterfaceC0671y
            goto L70
        L32:
            boolean r0 = r5 instanceof Q7.InterfaceC0671y
            if (r0 == 0) goto L37
            goto L1d
        L37:
            boolean r0 = r4 instanceof Q7.D
            if (r0 == 0) goto L52
            boolean r0 = r5 instanceof Q7.D
            if (r0 == 0) goto L1d
            Q7.D r4 = (Q7.D) r4
            T7.B r4 = (T7.B) r4
            Q7.D r5 = (Q7.D) r5
            T7.B r5 = (T7.B) r5
            o8.c r4 = r4.f8767q
            o8.c r5 = r5.f8767q
            boolean r4 = A7.m.a(r4, r5)
            if (r4 == 0) goto L1d
            goto L6f
        L52:
            boolean r0 = r5 instanceof Q7.D
            if (r0 == 0) goto L57
            goto L1d
        L57:
            o8.f r0 = r4.getName()
            o8.f r3 = r5.getName()
            boolean r0 = A7.m.a(r0, r3)
            if (r0 != 0) goto L66
            goto L1d
        L66:
            Q7.j r4 = r4.o()
            Q7.j r5 = r5.o()
            goto L27
        L6f:
            r4 = r2
        L70:
            if (r4 == 0) goto L73
            r1 = r2
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.AbstractC0267b.f(Q7.g):boolean");
    }

    @Override // F8.K
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public abstract InterfaceC0652e n();

    @Override // F8.K
    public final N7.i l() {
        N7.i iVarE = AbstractC2564d.e(n());
        if (iVarE != null) {
            return iVarE;
        }
        h(1);
        throw null;
    }
}
