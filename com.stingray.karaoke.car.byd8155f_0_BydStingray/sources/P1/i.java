package P1;

import A7.m;
import N1.InterfaceC0421b;
import N1.b0;
import r9.v;
import r9.z;

/* JADX INFO: loaded from: classes.dex */
public final class i implements InterfaceC0421b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f5288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f5289b;
    public final b0 c;
    public final e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a f5290e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b9.c f5291f;

    public i(v vVar, z zVar, b0 b0Var, e eVar) {
        m.f(vVar, "fileSystem");
        m.f(zVar, "path");
        m.f(b0Var, "coordinator");
        this.f5288a = vVar;
        this.f5289b = zVar;
        this.c = b0Var;
        this.d = eVar;
        this.f5290e = new a();
        this.f5291f = b9.d.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [N1.d0] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(N1.d0 r9, s7.AbstractC2410c r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof P1.g
            if (r0 == 0) goto L13
            r0 = r10
            P1.g r0 = (P1.g) r0
            int r1 = r0.f5282q
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f5282q = r1
            goto L18
        L13:
            P1.g r0 = new P1.g
            r0.<init>(r8, r10)
        L18:
            java.lang.Object r10 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f5282q
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 != r4) goto L34
            boolean r8 = r0.f5280n
            P1.c r9 = r0.f5279m
            P1.i r0 = r0.l
            h8.AbstractC1386c.u(r10)     // Catch: java.lang.Throwable -> L2e
            goto L6f
        L2e:
            r10 = move-exception
            r7 = r10
            r10 = r8
            r8 = r0
            r0 = r7
            goto L87
        L34:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3c:
            h8.AbstractC1386c.u(r10)
            P1.a r10 = r8.f5290e
            java.util.concurrent.atomic.AtomicBoolean r10 = r10.f5266a
            boolean r10 = r10.get()
            if (r10 != 0) goto L99
            b9.c r10 = r8.f5291f
            boolean r10 = r10.d(r3)
            P1.c r2 = new P1.c     // Catch: java.lang.Throwable -> L90
            r9.v r5 = r8.f5288a     // Catch: java.lang.Throwable -> L90
            r9.z r6 = r8.f5289b     // Catch: java.lang.Throwable -> L90
            r2.<init>(r5, r6)     // Catch: java.lang.Throwable -> L90
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r10)     // Catch: java.lang.Throwable -> L84
            r0.l = r8     // Catch: java.lang.Throwable -> L84
            r0.f5279m = r2     // Catch: java.lang.Throwable -> L84
            r0.f5280n = r10     // Catch: java.lang.Throwable -> L84
            r0.f5282q = r4     // Catch: java.lang.Throwable -> L84
            java.lang.Object r9 = r9.g(r2, r5, r0)     // Catch: java.lang.Throwable -> L84
            if (r9 != r1) goto L6b
            return r1
        L6b:
            r0 = r8
            r8 = r10
            r10 = r9
            r9 = r2
        L6f:
            r9.close()     // Catch: java.lang.Throwable -> L74
            r9 = r3
            goto L75
        L74:
            r9 = move-exception
        L75:
            if (r9 != 0) goto L7f
            if (r8 == 0) goto L7e
            b9.c r8 = r0.f5291f
            r8.e(r3)
        L7e:
            return r10
        L7f:
            throw r9     // Catch: java.lang.Throwable -> L80
        L80:
            r9 = move-exception
            r10 = r8
            r8 = r0
            goto L91
        L84:
            r9 = move-exception
            r0 = r9
            r9 = r2
        L87:
            r9.close()     // Catch: java.lang.Throwable -> L8b
            goto L8f
        L8b:
            r9 = move-exception
            h6.q.l(r0, r9)     // Catch: java.lang.Throwable -> L90
        L8f:
            throw r0     // Catch: java.lang.Throwable -> L90
        L90:
            r9 = move-exception
        L91:
            if (r10 == 0) goto L98
            b9.c r8 = r8.f5291f
            r8.e(r3)
        L98:
            throw r9
        L99:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "StorageConnection has already been disposed."
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.i.a(N1.d0, s7.c):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00bc A[Catch: all -> 0x00cc, IOException -> 0x00cf, TRY_ENTER, TryCatch #9 {IOException -> 0x00cf, all -> 0x00cc, blocks: (B:40:0x00bc, B:42:0x00c4, B:50:0x00da, B:57:0x00e9, B:56:0x00e6), top: B:88:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00da A[Catch: all -> 0x00cc, IOException -> 0x00cf, TRY_ENTER, TRY_LEAVE, TryCatch #9 {IOException -> 0x00cf, all -> 0x00cc, blocks: (B:40:0x00bc, B:42:0x00c4, B:50:0x00da, B:57:0x00e9, B:56:0x00e6), top: B:88:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(N1.P r10, s7.AbstractC2410c r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.i.b(N1.P, s7.c):java.lang.Object");
    }

    @Override // N1.InterfaceC0421b
    public final void close() {
        this.f5290e.f5266a.set(true);
        this.d.a();
    }
}
