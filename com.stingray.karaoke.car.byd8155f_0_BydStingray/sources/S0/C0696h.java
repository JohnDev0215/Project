package S0;

import T.C0735d;
import T.C0738e0;
import T.Q0;
import T.S;
import java.util.List;
import l3.P;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: S0.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0696h implements Q0 {
    public final List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final L f7837m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final P f7838n;
    public final InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final P6.a f7839p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0738e0 f7840q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7841r = true;

    public C0696h(List list, Object obj, L l, P p10, InterfaceC2782k interfaceC2782k, P6.a aVar) {
        this.l = list;
        this.f7837m = l;
        this.f7838n = p10;
        this.o = interfaceC2782k;
        this.f7839p = aVar;
        this.f7840q = C0735d.O(obj, S.f8224q);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0065 A[Catch: all -> 0x00c2, TryCatch #0 {all -> 0x00c2, blocks: (B:27:0x0065, B:29:0x0073, B:34:0x0095, B:36:0x00a3, B:42:0x00c4, B:25:0x005c), top: B:52:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0095 A[Catch: all -> 0x00c2, TRY_LEAVE, TryCatch #0 {all -> 0x00c2, blocks: (B:27:0x0065, B:29:0x0073, B:34:0x0095, B:36:0x00a3, B:42:0x00c4, B:25:0x005c), top: B:52:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c4 A[Catch: all -> 0x00c2, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x00c2, blocks: (B:27:0x0065, B:29:0x0073, B:34:0x0095, B:36:0x00a3, B:42:0x00c4, B:25:0x005c), top: B:52:0x005c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0071 -> B:47:0x00e0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00d7 -> B:46:0x00dc). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(s7.AbstractC2410c r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: S0.C0696h.b(s7.c):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(S0.G r6, s7.AbstractC2410c r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof S0.C0694f
            if (r0 == 0) goto L13
            r0 = r7
            S0.f r0 = (S0.C0694f) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            S0.f r0 = new S0.f
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.f7833m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            S0.G r6 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            goto L4b
        L2a:
            r5 = move-exception
            goto L4d
        L2c:
            r5 = move-exception
            goto L76
        L2e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L36:
            h8.AbstractC1386c.u(r7)
            S0.g r7 = new S0.g     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            r7.<init>(r5, r6, r4)     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            r0.l = r6     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            r0.o = r3     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            r2 = 15000(0x3a98, double:7.411E-320)
            java.lang.Object r7 = S8.AbstractC0727y.G(r2, r7, r0)     // Catch: java.lang.Exception -> L2a java.util.concurrent.CancellationException -> L2c
            if (r7 != r1) goto L4b
            return r1
        L4b:
            r4 = r7
            goto L80
        L4d:
            q7.h r7 = r0.getContext()
            S8.t r1 = S8.C0722t.l
            q7.f r7 = r7.F(r1)
            S8.u r7 = (S8.InterfaceC0723u) r7
            if (r7 == 0) goto L80
            q7.h r0 = r0.getContext()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Unable to load font "
            r2.<init>(r3)
            r2.append(r6)
            java.lang.String r6 = r2.toString()
            r1.<init>(r6, r5)
            r7.G(r1, r0)
            goto L80
        L76:
            q7.h r6 = r0.getContext()
            boolean r6 = S8.AbstractC0727y.t(r6)
            if (r6 == 0) goto L81
        L80:
            return r4
        L81:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: S0.C0696h.c(S0.G, s7.c):java.lang.Object");
    }

    @Override // T.Q0
    public final Object getValue() {
        return this.f7840q.getValue();
    }
}
