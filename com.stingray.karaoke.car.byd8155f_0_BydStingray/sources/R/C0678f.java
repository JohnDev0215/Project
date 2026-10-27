package R;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: R.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0678f extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ w f7291m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f7292n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0678f(w wVar, float f10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f7291m = wVar;
        this.f7292n = f10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new C0678f(this.f7291m, this.f7292n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0678f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062 A[RETURN] */
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
            m7.q r2 = m7.q.f19267a
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            h8.AbstractC1386c.u(r9)
            goto L63
        Lf:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L17:
            h8.AbstractC1386c.u(r9)
            r8.l = r3
            R.w r9 = r8.f7291m
            T.e0 r1 = r9.f7329f
            java.lang.Object r1 = r1.getValue()
            float r3 = r9.e()
            float r4 = r8.f7292n
            java.lang.Object r3 = r9.c(r3, r4, r1)
            z7.k r5 = r9.c
            java.lang.Object r5 = r5.b(r3)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            y.j0 r6 = y.j0.l
            r7 = 0
            if (r5 == 0) goto L51
            R.h r1 = new R.h
            r1.<init>(r9, r4, r7)
            java.lang.Object r8 = r9.a(r3, r6, r1, r8)
            if (r8 != r0) goto L4b
            goto L4c
        L4b:
            r8 = r2
        L4c:
            if (r8 != r0) goto L4f
            goto L60
        L4f:
            r8 = r2
            goto L60
        L51:
            R.h r3 = new R.h
            r3.<init>(r9, r4, r7)
            java.lang.Object r8 = r9.a(r1, r6, r3, r8)
            if (r8 != r0) goto L5d
            goto L5e
        L5d:
            r8 = r2
        L5e:
            if (r8 != r0) goto L4f
        L60:
            if (r8 != r0) goto L63
            return r0
        L63:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: R.C0678f.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
