package G;

import A.C0006c;
import A.EnumC0017h0;

/* JADX INFO: renamed from: G.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0306p implements g0.m {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0304n f2605f = new C0304n();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0307q f2606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0006c f2607b;
    public final boolean c;
    public final a1.k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final EnumC0017h0 f2608e;

    public C0306p(InterfaceC0307q interfaceC0307q, C0006c c0006c, boolean z6, a1.k kVar, EnumC0017h0 enumC0017h0) {
        this.f2606a = interfaceC0307q;
        this.f2607b = c0006c;
        this.c = z6;
        this.d = kVar;
        this.f2608e = enumC0017h0;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean i(G.C0303m r5, int r6) {
        /*
            r4 = this;
            r0 = 5
            boolean r0 = C0.c0.g(r6, r0)
            r1 = 1
            if (r0 == 0) goto La
            r0 = r1
            goto Lf
        La:
            r0 = 6
            boolean r0 = C0.c0.g(r6, r0)
        Lf:
            A.h0 r2 = r4.f2608e
            r3 = 0
            if (r0 == 0) goto L1c
            A.h0 r0 = A.EnumC0017h0.f181m
            if (r2 != r0) goto L1a
        L18:
            r0 = r1
            goto L41
        L1a:
            r0 = r3
            goto L41
        L1c:
            r0 = 3
            boolean r0 = C0.c0.g(r6, r0)
            if (r0 == 0) goto L25
            r0 = r1
            goto L2a
        L25:
            r0 = 4
            boolean r0 = C0.c0.g(r6, r0)
        L2a:
            if (r0 == 0) goto L31
            A.h0 r0 = A.EnumC0017h0.l
            if (r2 != r0) goto L1a
            goto L18
        L31:
            boolean r0 = C0.c0.g(r6, r1)
            if (r0 == 0) goto L39
            r0 = r1
            goto L3e
        L39:
            r0 = 2
            boolean r0 = C0.c0.g(r6, r0)
        L3e:
            if (r0 == 0) goto L5d
            goto L1a
        L41:
            if (r0 == 0) goto L44
            return r3
        L44:
            boolean r6 = r4.l(r6)
            if (r6 == 0) goto L58
            int r5 = r5.f2602b
            G.q r4 = r4.f2606a
            int r4 = r4.b()
            int r4 = r4 - r1
            if (r5 >= r4) goto L56
            goto L5c
        L56:
            r1 = r3
            goto L5c
        L58:
            int r4 = r5.f2601a
            if (r4 <= 0) goto L56
        L5c:
            return r1
        L5d:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "Lazy list does not support beyond bounds layout for the specified direction"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: G.C0306p.i(G.m, int):boolean");
    }

    public final boolean l(int i7) {
        if (!C0.c0.g(i7, 1)) {
            if (C0.c0.g(i7, 2)) {
                return true;
            }
            boolean zG = C0.c0.g(i7, 5);
            boolean z6 = this.c;
            if (!zG) {
                if (!C0.c0.g(i7, 6)) {
                    boolean zG2 = C0.c0.g(i7, 3);
                    a1.k kVar = this.d;
                    if (zG2) {
                        int iOrdinal = kVar.ordinal();
                        if (iOrdinal != 0) {
                            if (iOrdinal != 1) {
                                throw new A9.k();
                            }
                            if (!z6) {
                                return true;
                            }
                        }
                    } else {
                        if (!C0.c0.g(i7, 4)) {
                            throw new IllegalStateException("Lazy list does not support beyond bounds layout for the specified direction");
                        }
                        int iOrdinal2 = kVar.ordinal();
                        if (iOrdinal2 != 0) {
                            if (iOrdinal2 != 1) {
                                throw new A9.k();
                            }
                        } else if (!z6) {
                            return true;
                        }
                    }
                } else if (!z6) {
                    return true;
                }
            }
            return z6;
        }
        return false;
    }
}
