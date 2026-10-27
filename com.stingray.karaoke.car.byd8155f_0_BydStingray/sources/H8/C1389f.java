package h8;

import W7.AbstractC0839c;
import i8.C1417b;
import i8.EnumC1416a;
import java.util.Set;
import n7.AbstractC1985B;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: h8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1389f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f15607b = AbstractC1985B.t(EnumC1416a.CLASS);
    public static final Set c = AbstractC1996k.c0(new EnumC1416a[]{EnumC1416a.FILE_FACADE, EnumC1416a.MULTIFILE_CLASS_PART});
    public static final n8.f d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final n8.f f15608e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public B8.i f15609a;

    static {
        new n8.f(new int[]{1, 1, 2}, false);
        d = new n8.f(new int[]{1, 1, 11}, false);
        f15608e = new n8.f(new int[]{1, 1, 13}, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final D8.s a(Q7.D r13, V7.c r14) {
        /*
            r12 = this;
            java.lang.String r4 = "Could not read data from "
            java.lang.String r0 = "descriptor"
            A7.m.f(r13, r0)
            java.lang.String r0 = "kotlinClass"
            A7.m.f(r14, r0)
            i8.b r0 = r14.f9549b
            java.lang.Object r5 = r0.f15785e
            java.lang.String[] r5 = (java.lang.String[]) r5
            if (r5 != 0) goto L18
            java.lang.Object r5 = r0.f15786f
            java.lang.String[] r5 = (java.lang.String[]) r5
        L18:
            r6 = 0
            if (r5 == 0) goto L28
            java.lang.Object r7 = r0.c
            i8.a r7 = (i8.EnumC1416a) r7
            java.util.Set r8 = h8.C1389f.c
            boolean r7 = r8.contains(r7)
            if (r7 == 0) goto L28
            goto L29
        L28:
            r5 = r6
        L29:
            if (r5 != 0) goto L2c
            return r6
        L2c:
            java.lang.Object r7 = r0.d
            n8.f r7 = (n8.f) r7
            java.lang.Object r0 = r0.f15787g
            java.lang.String[] r0 = (java.lang.String[]) r0
            if (r0 != 0) goto L37
            return r6
        L37:
            m7.i r0 = n8.h.h(r5, r0)     // Catch: java.lang.Throwable -> L3c p8.C2142q -> L3e
            goto L73
        L3c:
            r0 = move-exception
            goto L56
        L3e:
            r0 = move-exception
            r5 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L3c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3c
            r8.<init>(r4)     // Catch: java.lang.Throwable -> L3c
            java.lang.String r4 = r14.a()     // Catch: java.lang.Throwable -> L3c
            r8.append(r4)     // Catch: java.lang.Throwable -> L3c
            java.lang.String r4 = r8.toString()     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r4, r5)     // Catch: java.lang.Throwable -> L3c
            throw r0     // Catch: java.lang.Throwable -> L3c
        L56:
            B8.i r4 = r12.c()
            B8.j r4 = r4.c
            r4.getClass()
            B8.i r4 = r12.c()
            java.lang.String r5 = "<this>"
            B8.j r4 = r4.c
            A7.m.f(r4, r5)
            n8.f r4 = n8.f.f20166g
            boolean r4 = r7.b(r4)
            if (r4 != 0) goto Lb6
            r0 = r6
        L73:
            if (r0 != 0) goto L76
            return r6
        L76:
            java.lang.Object r4 = r0.l
            n8.g r4 = (n8.g) r4
            java.lang.Object r0 = r0.f19259m
            j8.C r0 = (j8.C) r0
            h8.h r6 = new h8.h
            r12.d(r14)
            r12.e(r14)
            int r5 = r12.b(r14)
            r6.<init>(r14, r0, r4, r5)
            D8.s r10 = new D8.s
            B8.i r8 = r12.c()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "scope for "
            r1.<init>(r3)
            r1.append(r6)
            java.lang.String r3 = " in "
            r1.append(r3)
            r1.append(r13)
            java.lang.String r9 = r1.toString()
            h8.e r11 = h8.C1388e.f15606m
            r1 = r10
            r2 = r13
            r3 = r0
            r5 = r7
            r7 = r8
            r8 = r9
            r9 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return r10
        Lb6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: h8.C1389f.a(Q7.D, V7.c):D8.s");
    }

    public final int b(V7.c cVar) {
        c().c.getClass();
        int i7 = cVar.f9549b.f15784b;
        if (((i7 & 64) != 0) && (i7 & 32) == 0) {
            return 2;
        }
        return ((i7 & 16) == 0 || (i7 & 32) != 0) ? 1 : 3;
    }

    public final B8.i c() {
        B8.i iVar = this.f15609a;
        if (iVar != null) {
            return iVar;
        }
        A7.m.j("components");
        throw null;
    }

    public final B8.n d(V7.c cVar) {
        c().c.getClass();
        n8.f fVar = (n8.f) cVar.f9549b.d;
        A7.m.f(c().c, "<this>");
        n8.f fVar2 = n8.f.f20166g;
        if (fVar.b(fVar2)) {
            return null;
        }
        n8.f fVar3 = (n8.f) cVar.f9549b.d;
        A7.m.f(c().c, "<this>");
        A7.m.f(c().c, "<this>");
        fVar2.getClass();
        n8.f fVar4 = fVar3.f20168f ? fVar2 : n8.f.f20167h;
        fVar4.getClass();
        int i7 = fVar2.f18823b;
        int i10 = fVar4.f18823b;
        return new B8.n(fVar3, fVar2, fVar2, (i10 <= i7 && (i10 < i7 || fVar4.c <= fVar2.c)) ? fVar2 : fVar4, cVar.a(), AbstractC0839c.a(cVar.f9548a));
    }

    public final boolean e(V7.c cVar) {
        c().c.getClass();
        c().c.getClass();
        C1417b c1417b = cVar.f9549b;
        return (c1417b.f15784b & 2) != 0 && ((n8.f) c1417b.d).equals(d);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final B8.C0092d f(V7.c r7) {
        /*
            r6 = this;
            java.lang.String r0 = "Could not read data from "
            i8.b r1 = r7.f9549b
            java.lang.Object r2 = r1.f15785e
            java.lang.String[] r2 = (java.lang.String[]) r2
            if (r2 != 0) goto Le
            java.lang.Object r2 = r1.f15786f
            java.lang.String[] r2 = (java.lang.String[]) r2
        Le:
            r3 = 0
            if (r2 == 0) goto L1e
            java.lang.Object r4 = r1.c
            i8.a r4 = (i8.EnumC1416a) r4
            java.util.Set r5 = h8.C1389f.f15607b
            boolean r4 = r5.contains(r4)
            if (r4 == 0) goto L1e
            goto L1f
        L1e:
            r2 = r3
        L1f:
            if (r2 != 0) goto L22
            return r3
        L22:
            java.lang.Object r4 = r1.d
            n8.f r4 = (n8.f) r4
            java.lang.Object r1 = r1.f15787g
            java.lang.String[] r1 = (java.lang.String[]) r1
            if (r1 != 0) goto L2d
            return r3
        L2d:
            m7.i r0 = n8.h.f(r2, r1)     // Catch: java.lang.Throwable -> L32 p8.C2142q -> L34
            goto L68
        L32:
            r0 = move-exception
            goto L4b
        L34:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L32
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L32
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L32
            java.lang.String r0 = r7.a()     // Catch: java.lang.Throwable -> L32
            r5.append(r0)     // Catch: java.lang.Throwable -> L32
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L32
            r2.<init>(r0, r1)     // Catch: java.lang.Throwable -> L32
            throw r2     // Catch: java.lang.Throwable -> L32
        L4b:
            B8.i r1 = r6.c()
            B8.j r1 = r1.c
            r1.getClass()
            B8.i r1 = r6.c()
            java.lang.String r2 = "<this>"
            B8.j r1 = r1.c
            A7.m.f(r1, r2)
            n8.f r1 = n8.f.f20166g
            boolean r1 = r4.b(r1)
            if (r1 != 0) goto L88
            r0 = r3
        L68:
            if (r0 != 0) goto L6b
            return r3
        L6b:
            java.lang.Object r1 = r0.l
            n8.g r1 = (n8.g) r1
            java.lang.Object r0 = r0.f19259m
            j8.j r0 = (j8.C1565j) r0
            h8.o r2 = new h8.o
            r6.d(r7)
            r6.e(r7)
            int r6 = r6.b(r7)
            r2.<init>(r7, r6)
            B8.d r6 = new B8.d
            r6.<init>(r1, r0, r4, r2)
            return r6
        L88:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: h8.C1389f.f(V7.c):B8.d");
    }
}
