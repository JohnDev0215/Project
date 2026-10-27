package X5;

import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: X5.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0884t extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f10380m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ long f10381n;
    public final /* synthetic */ long o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0884t(long j3, long j10, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f10381n = j3;
        this.o = j10;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0884t c0884t = new C0884t(this.f10381n, this.o, interfaceC2189c);
        c0884t.f10380m = obj;
        return c0884t;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0884t) create((InterfaceC0818g) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x005e -> B:15:0x003d). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r6.l
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L28
            if (r1 == r4) goto L20
            if (r1 == r3) goto L18
            if (r1 != r2) goto L10
            goto L20
        L10:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L18:
            java.lang.Object r1 = r6.f10380m
            V8.g r1 = (V8.InterfaceC0818g) r1
            h8.AbstractC1386c.u(r7)
            goto L54
        L20:
            java.lang.Object r1 = r6.f10380m
            V8.g r1 = (V8.InterfaceC0818g) r1
            h8.AbstractC1386c.u(r7)
            goto L3d
        L28:
            h8.AbstractC1386c.u(r7)
            java.lang.Object r7 = r6.f10380m
            r1 = r7
            V8.g r1 = (V8.InterfaceC0818g) r1
            r6.f10380m = r1
            r6.l = r4
            long r4 = r6.f10381n
            java.lang.Object r7 = S8.AbstractC0727y.j(r4, r6)
            if (r7 != r0) goto L3d
            return r0
        L3d:
            q7.h r7 = r6.getContext()
            boolean r7 = S8.AbstractC0727y.t(r7)
            m7.q r4 = m7.q.f19267a
            if (r7 == 0) goto L61
            r6.f10380m = r1
            r6.l = r3
            java.lang.Object r7 = r1.f(r4, r6)
            if (r7 != r0) goto L54
            return r0
        L54:
            r6.f10380m = r1
            r6.l = r2
            long r4 = r6.o
            java.lang.Object r7 = S8.AbstractC0727y.j(r4, r6)
            if (r7 != r0) goto L3d
            return r0
        L61:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: X5.C0884t.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
