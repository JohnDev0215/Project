package V;

import P8.l;
import m7.q;
import q7.InterfaceC2189c;
import s7.h;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object[] f9280m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long[] f9281n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9282p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9283q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f9284r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public long f9285s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f9286t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public /* synthetic */ Object f9287u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ f f9288v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(f fVar, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.f9288v = fVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        e eVar = new e(this.f9288v, interfaceC2189c);
        eVar.f9287u = obj;
        return eVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((l) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0095  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x004f -> B:22:0x0093). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0051 -> B:14:0x0062). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006b -> B:19:0x008a). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
        /*
            r20 = this;
            r0 = r20
            r1 = 1
            r7.a r2 = r7.EnumC2303a.l
            int r3 = r0.f9286t
            r4 = 0
            r5 = 8
            if (r3 == 0) goto L2c
            if (r3 != r1) goto L24
            int r3 = r0.f9284r
            int r6 = r0.f9283q
            long r7 = r0.f9285s
            int r9 = r0.f9282p
            int r10 = r0.o
            long[] r11 = r0.f9281n
            java.lang.Object[] r12 = r0.f9280m
            java.lang.Object r13 = r0.f9287u
            P8.l r13 = (P8.l) r13
            h8.AbstractC1386c.u(r21)
            goto L8a
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            h8.AbstractC1386c.u(r21)
            java.lang.Object r3 = r0.f9287u
            P8.l r3 = (P8.l) r3
            V.f r6 = r0.f9288v
            u.A r6 = r6.l
            java.lang.Object[] r7 = r6.f22580b
            long[] r6 = r6.f22579a
            int r8 = r6.length
            int r8 = r8 + (-2)
            if (r8 < 0) goto L97
            r9 = r4
        L41:
            r10 = r6[r9]
            long r12 = ~r10
            r14 = 7
            long r12 = r12 << r14
            long r12 = r12 & r10
            r14 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r12 = r12 & r14
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L93
            int r12 = r9 - r8
            int r12 = ~r12
            int r12 = r12 >>> 31
            int r12 = 8 - r12
            r13 = r3
            r3 = r4
            r18 = r10
            r11 = r6
            r10 = r8
            r6 = r12
            r12 = r7
            r7 = r18
        L62:
            if (r3 >= r6) goto L8d
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r7
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L8a
            int r4 = r9 << 3
            int r4 = r4 + r3
            r4 = r12[r4]
            r0.f9287u = r13
            r0.f9280m = r12
            r0.f9281n = r11
            r0.o = r10
            r0.f9282p = r9
            r0.f9285s = r7
            r0.f9283q = r6
            r0.f9284r = r3
            r0.f9286t = r1
            r13.c(r4, r0)
            r7.a r0 = r7.EnumC2303a.l
            return r2
        L8a:
            long r7 = r7 >> r5
            int r3 = r3 + r1
            goto L62
        L8d:
            if (r6 != r5) goto L97
            r8 = r10
            r6 = r11
            r7 = r12
            r3 = r13
        L93:
            if (r9 == r8) goto L97
            int r9 = r9 + r1
            goto L41
        L97:
            m7.q r0 = m7.q.f19267a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: V.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
