package e1;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f13789m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ x f13790n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(x xVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f13790n = xVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        j jVar = new j(this.f13790n, interfaceC2189c);
        jVar.f13789m = obj;
        return jVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Path cross not found for [B:18:0x005a, B:20:0x005e], limit reached: 25 */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0045 -> B:16:0x0048). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.l
            r2 = 1
            if (r1 == 0) goto L19
            if (r1 != r2) goto L11
            java.lang.Object r1 = r8.f13789m
            S8.w r1 = (S8.InterfaceC0725w) r1
            h8.AbstractC1386c.u(r9)
            goto L48
        L11:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L19:
            h8.AbstractC1386c.u(r9)
            java.lang.Object r9 = r8.f13789m
            S8.w r9 = (S8.InterfaceC0725w) r9
            r1 = r9
        L21:
            boolean r9 = S8.AbstractC0727y.s(r1)
            if (r9 == 0) goto L68
            e1.c r9 = e1.C1190c.o
            r8.f13789m = r1
            r8.l = r2
            q7.h r3 = r8.getContext()
            F0.B0 r4 = F0.B0.l
            q7.f r3 = r3.F(r4)
            if (r3 != 0) goto L62
            q7.h r3 = r8.getContext()
            T.T r3 = T.C0735d.I(r3)
            java.lang.Object r9 = r3.o(r9, r8)
            if (r9 != r0) goto L48
            return r0
        L48:
            e1.x r9 = r8.f13790n
            int[] r3 = r9.f13831L
            r4 = 0
            r5 = r3[r4]
            r6 = r3[r2]
            android.view.View r7 = r9.f13835w
            r7.getLocationOnScreen(r3)
            r4 = r3[r4]
            if (r5 != r4) goto L5e
            r3 = r3[r2]
            if (r6 == r3) goto L21
        L5e:
            r9.k()
            goto L21
        L62:
            java.lang.ClassCastException r8 = new java.lang.ClassCastException
            r8.<init>()
            throw r8
        L68:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: e1.j.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
