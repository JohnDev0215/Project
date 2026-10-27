package z;

import m7.q;
import q0.C2162a;
import q7.InterfaceC2189c;
import y0.z;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class e extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f24233m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f24234n;
    public final /* synthetic */ C2162a o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(C2162a c2162a, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = c2162a;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        e eVar = new e(this.o, interfaceC2189c);
        eVar.f24234n = obj;
        return eVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((e) create((z) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005c  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f24233m
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L20
            if (r1 == r3) goto L18
            if (r1 != r2) goto L10
            h8.AbstractC1386c.u(r8)
            goto L58
        L10:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L18:
            java.lang.Object r1 = r7.f24234n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r8)
            goto L33
        L20:
            h8.AbstractC1386c.u(r8)
            java.lang.Object r8 = r7.f24234n
            r1 = r8
            y0.z r1 = (y0.z) r1
            r7.f24234n = r1
            r7.f24233m = r3
            java.lang.Object r8 = h6.m.d(r1, r7)
            if (r8 != r0) goto L33
            return r0
        L33:
            y0.q r8 = (y0.q) r8
            r8.a()
            q0.a r3 = r7.o
            z.k r4 = new z.k
            long r5 = r8.c
            r4.<init>(r5)
            java.lang.Object r8 = r3.f21118n
            z.m r8 = (z.m) r8
            T.e0 r8 = r8.f24251a
            r8.setValue(r4)
            r8 = 0
            r7.f24234n = r8
            r7.f24233m = r2
            y0.i r8 = y0.EnumC2731i.f24067m
            java.lang.Object r8 = A.r1.e(r1, r8, r7)
            if (r8 != r0) goto L58
            return r0
        L58:
            y0.q r8 = (y0.q) r8
            if (r8 == 0) goto L5f
            r8.a()
        L5f:
            m7.q r7 = m7.q.f19267a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: z.e.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
