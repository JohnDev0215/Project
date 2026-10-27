package u;

import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: u.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2485y extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public P8.i f22647m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C2453A f22648n;
    public long[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f22649p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f22650q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f22651r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f22652s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public long f22653t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f22654u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public /* synthetic */ Object f22655v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ C2453A f22656w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ P8.i f22657x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2485y(C2453A c2453a, P8.i iVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f22656w = c2453a;
        this.f22657x = iVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C2485y c2485y = new C2485y(this.f22656w, this.f22657x, interfaceC2189c);
        c2485y.f22655v = obj;
        return c2485y;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C2485y) create((P8.l) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0050 -> B:22:0x009e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0052 -> B:14:0x0065). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006e -> B:19:0x0093). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Throwable {
        /*
            r21 = this;
            r0 = r21
            r1 = 1
            r7.a r2 = r7.EnumC2303a.l
            int r3 = r0.f22654u
            r4 = 0
            r5 = 8
            if (r3 == 0) goto L2f
            if (r3 != r1) goto L27
            int r3 = r0.f22652s
            int r6 = r0.f22651r
            long r7 = r0.f22653t
            int r9 = r0.f22650q
            int r10 = r0.f22649p
            long[] r11 = r0.o
            u.A r12 = r0.f22648n
            P8.i r13 = r0.f22647m
            java.lang.Object r14 = r0.f22655v
            P8.l r14 = (P8.l) r14
            h8.AbstractC1386c.u(r22)
            goto L93
        L27:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2f:
            h8.AbstractC1386c.u(r22)
            java.lang.Object r3 = r0.f22655v
            P8.l r3 = (P8.l) r3
            u.A r6 = r0.f22656w
            long[] r7 = r6.f22579a
            int r8 = r7.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto La2
            P8.i r9 = r0.f22657x
            r10 = r4
        L42:
            r11 = r7[r10]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L9e
            int r13 = r10 - r8
            int r13 = ~r13
            int r13 = r13 >>> 31
            int r13 = 8 - r13
            r14 = r3
            r3 = r4
            r19 = r11
            r12 = r6
            r11 = r7
            r6 = r13
            r13 = r9
            r9 = r10
            r10 = r8
            r7 = r19
        L65:
            if (r3 >= r6) goto L96
            r15 = 255(0xff, double:1.26E-321)
            long r15 = r15 & r7
            r17 = 128(0x80, double:6.3E-322)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L93
            int r4 = r9 << 3
            int r4 = r4 + r3
            r13.f5472n = r4
            java.lang.Object[] r5 = r12.f22580b
            r4 = r5[r4]
            r0.f22655v = r14
            r0.f22647m = r13
            r0.f22648n = r12
            r0.o = r11
            r0.f22649p = r10
            r0.f22650q = r9
            r0.f22653t = r7
            r0.f22651r = r6
            r0.f22652s = r3
            r0.f22654u = r1
            r14.c(r4, r0)
            r7.a r0 = r7.EnumC2303a.l
            return r2
        L93:
            long r7 = r7 >> r5
            int r3 = r3 + r1
            goto L65
        L96:
            if (r6 != r5) goto La2
            r8 = r10
            r7 = r11
            r6 = r12
            r3 = r14
            r10 = r9
            r9 = r13
        L9e:
            if (r10 == r8) goto La2
            int r10 = r10 + r1
            goto L42
        La2:
            m7.q r0 = m7.q.f19267a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: u.C2485y.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
