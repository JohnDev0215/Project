package A;

import q7.InterfaceC2189c;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class T extends s7.i implements InterfaceC2785n {
    public A7.z l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f108m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f109n;
    public final /* synthetic */ A7.z o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ V f110p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public T(A7.z zVar, V v10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = zVar;
        this.f110p = v10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        T t10 = new T(this.o, this.f110p, interfaceC2189c);
        t10.f109n = obj;
        return t10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((T) create((InterfaceC2782k) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0041 -> B:25:0x0053). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x004d -> B:24:0x0050). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.f108m
            r2 = 1
            if (r1 == 0) goto L1b
            if (r1 != r2) goto L13
            A7.z r1 = r6.l
            java.lang.Object r3 = r6.f109n
            z7.k r3 = (z7.InterfaceC2782k) r3
            h8.AbstractC1386c.u(r7)
            goto L50
        L13:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L1b:
            h8.AbstractC1386c.u(r7)
            java.lang.Object r7 = r6.f109n
            z7.k r7 = (z7.InterfaceC2782k) r7
            r3 = r7
        L23:
            A7.z r1 = r6.o
            java.lang.Object r7 = r1.l
            boolean r4 = r7 instanceof A.C0051z
            if (r4 != 0) goto L56
            boolean r4 = r7 instanceof A.C0045w
            if (r4 != 0) goto L56
            boolean r4 = r7 instanceof A.C0047x
            r5 = 0
            if (r4 == 0) goto L37
            A.x r7 = (A.C0047x) r7
            goto L38
        L37:
            r7 = r5
        L38:
            if (r7 == 0) goto L3d
            r3.b(r7)
        L3d:
            A.V r7 = r6.f110p
            U8.l r7 = r7.f123E
            if (r7 == 0) goto L53
            r6.f109n = r3
            r6.l = r1
            r6.f108m = r2
            java.lang.Object r7 = r7.c(r6)
            if (r7 != r0) goto L50
            return r0
        L50:
            r5 = r7
            A.A r5 = (A.A) r5
        L53:
            r1.l = r5
            goto L23
        L56:
            m7.q r6 = m7.q.f19267a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: A.T.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
