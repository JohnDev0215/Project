package A;

import Q.C0513j0;
import S8.AbstractC0727y;
import r7.EnumC2303a;
import y0.C2722B;
import z7.InterfaceC2782k;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class r1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final W f263a = new W(3, null, 2);

    /* JADX WARN: Removed duplicated region for block: B:20:0x004d A[LOOP:0: B:19:0x004b->B:20:0x004d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:18:0x0041). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(y0.z r8, s7.AbstractC2408a r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof A.V0
            if (r0 == 0) goto L13
            r0 = r9
            A.V0 r0 = (A.V0) r0
            int r1 = r0.f128n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f128n = r1
            goto L18
        L13:
            A.V0 r0 = new A.V0
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f127m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f128n
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            y0.z r8 = r0.l
            h8.AbstractC1386c.u(r9)
            goto L41
        L29:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L31:
            h8.AbstractC1386c.u(r9)
        L34:
            r0.l = r8
            r0.f128n = r3
            y0.i r9 = y0.EnumC2731i.f24067m
            java.lang.Object r9 = r8.a(r9, r0)
            if (r9 != r1) goto L41
            goto L71
        L41:
            y0.h r9 = (y0.C2730h) r9
            java.lang.Object r2 = r9.f24065a
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L4b:
            if (r6 >= r4) goto L59
            java.lang.Object r7 = r2.get(r6)
            y0.q r7 = (y0.q) r7
            r7.a()
            int r6 = r6 + 1
            goto L4b
        L59:
            java.lang.Object r9 = r9.f24065a
            int r2 = r9.size()
        L5f:
            if (r5 >= r2) goto L6f
            java.lang.Object r4 = r9.get(r5)
            y0.q r4 = (y0.q) r4
            boolean r4 = r4.d
            if (r4 == 0) goto L6c
            goto L34
        L6c:
            int r5 = r5 + 1
            goto L5f
        L6f:
            m7.q r1 = m7.q.f19267a
        L71:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: A.r1.a(y0.z, s7.a):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0048 -> B:18:0x004b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object b(y0.z r10, boolean r11, y0.EnumC2731i r12, s7.AbstractC2408a r13) {
        /*
            boolean r0 = r13 instanceof A.T0
            if (r0 == 0) goto L13
            r0 = r13
            A.T0 r0 = (A.T0) r0
            int r1 = r0.f113p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f113p = r1
            goto L18
        L13:
            A.T0 r0 = new A.T0
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f113p
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            boolean r10 = r0.f112n
            y0.i r11 = r0.f111m
            y0.z r12 = r0.l
            h8.AbstractC1386c.u(r13)
            r9 = r11
            r11 = r10
            r10 = r12
            r12 = r9
            goto L4b
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            h8.AbstractC1386c.u(r13)
        L3c:
            r0.l = r10
            r0.f111m = r12
            r0.f112n = r11
            r0.f113p = r3
            java.lang.Object r13 = r10.a(r12, r0)
            if (r13 != r1) goto L4b
            return r1
        L4b:
            y0.h r13 = (y0.C2730h) r13
            java.lang.Object r2 = r13.f24065a
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L55:
            if (r6 >= r4) goto L7c
            java.lang.Object r7 = r2.get(r6)
            y0.q r7 = (y0.q) r7
            if (r11 == 0) goto L71
            boolean r8 = r7.b()
            if (r8 != 0) goto L6f
            boolean r8 = r7.f24082h
            if (r8 != 0) goto L6f
            boolean r7 = r7.d
            if (r7 == 0) goto L6f
            r7 = r3
            goto L75
        L6f:
            r7 = r5
            goto L75
        L71:
            boolean r7 = y0.AbstractC2737o.a(r7)
        L75:
            if (r7 != 0) goto L79
            r2 = r5
            goto L7d
        L79:
            int r6 = r6 + 1
            goto L55
        L7c:
            r2 = r3
        L7d:
            if (r2 == 0) goto L3c
            java.lang.Object r10 = r13.f24065a
            java.lang.Object r10 = r10.get(r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: A.r1.b(y0.z, boolean, y0.i, s7.a):java.lang.Object");
    }

    public static Object d(C2722B c2722b, C0513j0 c0513j0, InterfaceC2782k interfaceC2782k, s7.i iVar, int i7) {
        InterfaceC2786o interfaceC2786o = c0513j0;
        if ((i7 & 4) != 0) {
            interfaceC2786o = f263a;
        }
        Object objH = AbstractC0727y.h(new p1(c2722b, interfaceC2786o, null, null, interfaceC2782k, null), iVar);
        return objH == EnumC2303a.l ? objH : m7.q.f19267a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x007b, code lost:
    
        r0 = r12.size();
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
    
        if (r9 >= r0) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0082, code lost:
    
        r10 = (y0.q) r12.get(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008c, code lost:
    
        if (r10.b() != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
    
        if (y0.AbstractC2737o.g(r10, r8.f24110p.f24046H, r8.b()) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x009d, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a1, code lost:
    
        return null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a2, code lost:
    
        r0 = y0.EnumC2731i.f24068n;
        r1.l = r8;
        r1.f259m = r3;
        r1.o = 2;
        r0 = r8.a(r0, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00af, code lost:
    
        if (r0 != r2) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b1, code lost:
    
        return r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0059 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00af -> B:13:0x0030). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object e(y0.z r17, y0.EnumC2731i r18, s7.AbstractC2408a r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: A.r1.e(y0.z, y0.i, s7.a):java.lang.Object");
    }
}
