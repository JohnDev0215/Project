package P1;

import A7.m;
import N1.InterfaceC0421b;
import r9.v;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public class c implements InterfaceC0421b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f5270a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f5271b;
    public final a c;

    public c(v vVar, z zVar) {
        m.f(vVar, "fileSystem");
        m.f(zVar, "path");
        this.f5270a = vVar;
        this.f5271b = zVar;
        this.c = new a();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0085 A[Catch: FileNotFoundException -> 0x0089, TRY_ENTER, TryCatch #3 {FileNotFoundException -> 0x0089, blocks: (B:43:0x0085, B:46:0x008d, B:20:0x0043), top: B:60:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008d A[Catch: FileNotFoundException -> 0x0089, TRY_LEAVE, TryCatch #3 {FileNotFoundException -> 0x0089, blocks: (B:43:0x0085, B:46:0x008d, B:20:0x0043), top: B:60:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object a(P1.c r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            boolean r0 = r7 instanceof P1.b
            if (r0 == 0) goto L13
            r0 = r7
            P1.b r0 = (P1.b) r0
            int r1 = r0.f5269p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5269p = r1
            goto L18
        L13:
            P1.b r0 = new P1.b
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.f5268n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f5269p
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            r9.D r6 = r0.f5267m
            P1.c r0 = r0.l
            h8.AbstractC1386c.u(r7)     // Catch: java.lang.Throwable -> L2c
            goto L62
        L2c:
            r7 = move-exception
            goto L72
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            h8.AbstractC1386c.u(r7)
            P1.a r7 = r6.c
            java.util.concurrent.atomic.AtomicBoolean r7 = r7.f5266a
            boolean r7 = r7.get()
            if (r7 != 0) goto L9f
            r9.v r7 = r6.f5270a     // Catch: java.io.FileNotFoundException -> L89
            r9.z r2 = r6.f5271b     // Catch: java.io.FileNotFoundException -> L89
            r9.J r7 = r7.k(r2)     // Catch: java.io.FileNotFoundException -> L89
            r9.D r7 = r9.AbstractC2312b.c(r7)     // Catch: java.io.FileNotFoundException -> L89
            S1.h r2 = S1.h.f7872a     // Catch: java.lang.Throwable -> L70
            r0.l = r6     // Catch: java.lang.Throwable -> L70
            r0.f5267m = r7     // Catch: java.lang.Throwable -> L70
            r0.f5269p = r3     // Catch: java.lang.Throwable -> L70
            S1.b r0 = r2.a(r7)     // Catch: java.lang.Throwable -> L70
            if (r0 != r1) goto L5e
            return r1
        L5e:
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
        L62:
            if (r6 == 0) goto L69
            r6.close()     // Catch: java.lang.Throwable -> L68
            goto L69
        L68:
            r4 = move-exception
        L69:
            r6 = r0
            goto L83
        L6b:
            r5 = r0
            r0 = r6
            r6 = r7
            r7 = r5
            goto L72
        L70:
            r0 = move-exception
            goto L6b
        L72:
            if (r6 == 0) goto L7f
            r6.close()     // Catch: java.lang.Throwable -> L78
            goto L7f
        L78:
            r6 = move-exception
            h6.q.l(r7, r6)     // Catch: java.io.FileNotFoundException -> L7d
            goto L7f
        L7d:
            r6 = move-exception
            goto L8e
        L7f:
            r6 = r0
            r5 = r4
            r4 = r7
            r7 = r5
        L83:
            if (r4 != 0) goto L8d
            A7.m.c(r7)     // Catch: java.io.FileNotFoundException -> L89
            goto L9d
        L89:
            r7 = move-exception
            r0 = r6
            r6 = r7
            goto L8e
        L8d:
            throw r4     // Catch: java.io.FileNotFoundException -> L89
        L8e:
            r9.v r7 = r0.f5270a
            r9.z r0 = r0.f5271b
            boolean r7 = r7.e(r0)
            if (r7 != 0) goto L9e
            S1.b r7 = new S1.b
            r7.<init>(r3)
        L9d:
            return r7
        L9e:
            throw r6
        L9f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "This scope has already been closed."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.c.a(P1.c, s7.c):java.lang.Object");
    }

    @Override // N1.InterfaceC0421b
    public final void close() {
        this.c.f5266a.set(true);
    }
}
