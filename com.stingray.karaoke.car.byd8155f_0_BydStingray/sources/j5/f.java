package J5;

import S8.InterfaceC0725w;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class f extends s7.i implements InterfaceC2785n {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3096m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h f3097n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(h hVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f3097n = hVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new f(this.f3097n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0079 A[Catch: Exception -> 0x0016, TryCatch #0 {Exception -> 0x0016, blocks: (B:7:0x0012, B:23:0x006d, B:25:0x0079, B:27:0x0092, B:13:0x0021, B:19:0x0047, B:16:0x0028), top: B:32:0x000a }] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f3096m
            J5.h r2 = r8.f3097n
            r3 = 2
            r4 = 1
            K5.t r5 = r2.d
            if (r1 == 0) goto L25
            if (r1 == r4) goto L21
            if (r1 != r3) goto L19
            java.lang.String r8 = r8.l
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Exception -> L16
            goto L6d
        L16:
            r8 = move-exception
            goto L9a
        L19:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L21:
            h8.AbstractC1386c.u(r9)     // Catch: java.lang.Exception -> L16
            goto L47
        L25:
            h8.AbstractC1386c.u(r9)
            android.content.Context r9 = r5.f3657a     // Catch: java.lang.Exception -> L16
            N1.i r9 = r5.a(r9)     // Catch: java.lang.Exception -> L16
            N1.a r9 = (N1.C0420a) r9     // Catch: java.lang.Exception -> L16
            java.lang.Object r9 = r9.l     // Catch: java.lang.Exception -> L16
            N1.i r9 = (N1.InterfaceC0428i) r9     // Catch: java.lang.Exception -> L16
            V8.f r9 = r9.d()     // Catch: java.lang.Exception -> L16
            K5.e r1 = new K5.e     // Catch: java.lang.Exception -> L16
            r6 = 2
            r1.<init>(r9, r6)     // Catch: java.lang.Exception -> L16
            r8.f3096m = r4     // Catch: java.lang.Exception -> L16
            java.lang.Object r9 = V8.AbstractC0828q.j(r1, r8)     // Catch: java.lang.Exception -> L16
            if (r9 != r0) goto L47
            return r0
        L47:
            java.lang.String r9 = (java.lang.String) r9     // Catch: java.lang.Exception -> L16
            android.content.Context r1 = r5.f3657a     // Catch: java.lang.Exception -> L16
            N1.i r1 = r5.a(r1)     // Catch: java.lang.Exception -> L16
            N1.a r1 = (N1.C0420a) r1     // Catch: java.lang.Exception -> L16
            java.lang.Object r1 = r1.l     // Catch: java.lang.Exception -> L16
            N1.i r1 = (N1.InterfaceC0428i) r1     // Catch: java.lang.Exception -> L16
            V8.f r1 = r1.d()     // Catch: java.lang.Exception -> L16
            K5.e r4 = new K5.e     // Catch: java.lang.Exception -> L16
            r5 = 1
            r4.<init>(r1, r5)     // Catch: java.lang.Exception -> L16
            r8.l = r9     // Catch: java.lang.Exception -> L16
            r8.f3096m = r3     // Catch: java.lang.Exception -> L16
            java.lang.Object r8 = V8.AbstractC0828q.j(r4, r8)     // Catch: java.lang.Exception -> L16
            if (r8 != r0) goto L6a
            return r0
        L6a:
            r7 = r9
            r9 = r8
            r8 = r7
        L6d:
            java.lang.Number r9 = (java.lang.Number) r9     // Catch: java.lang.Exception -> L16
            long r0 = r9.longValue()     // Catch: java.lang.Exception -> L16
            int r9 = r8.length()     // Catch: java.lang.Exception -> L16
            if (r9 <= 0) goto La4
            J5.e r9 = new J5.e     // Catch: java.lang.Exception -> L16
            r9.<init>()     // Catch: java.lang.Exception -> L16
            java.lang.reflect.Type r9 = r9.f21254b     // Catch: java.lang.Exception -> L16
            java.lang.String r3 = "getType(...)"
            A7.m.e(r9, r3)     // Catch: java.lang.Exception -> L16
            j5.m r3 = new j5.m     // Catch: java.lang.Exception -> L16
            r3.<init>()     // Catch: java.lang.Exception -> L16
            java.lang.Object r8 = r3.b(r8, r9)     // Catch: java.lang.Exception -> L16
            com.stingray.kca.model.LoginInfo r8 = (com.stingray.kca.model.LoginInfo) r8     // Catch: java.lang.Exception -> L16
            if (r8 == 0) goto La4
            r2.f3104g = r8     // Catch: java.lang.Exception -> L16
            r2.f3103f = r0     // Catch: java.lang.Exception -> L16
            r2.c()     // Catch: java.lang.Exception -> L16
            goto La4
        L9a:
            B9.a r9 = timber.log.Timber.f22577a
            java.lang.String r0 = "LoginManager load savedLoginInfo failed: "
            r9.e(r0)
            r9.a(r8)
        La4:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: J5.f.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
