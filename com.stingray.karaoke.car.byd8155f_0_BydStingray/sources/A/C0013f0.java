package A;

import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: A.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0013f0 extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f169m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f170n;
    public final /* synthetic */ InterfaceC2194h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ s7.h f171p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public C0013f0(InterfaceC2194h interfaceC2194h, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = interfaceC2194h;
        this.f171p = (s7.h) interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.h, z7.n] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0013f0 c0013f0 = new C0013f0(this.o, this.f171p, interfaceC2189c);
        c0013f0.f170n = obj;
        return c0013f0;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0013f0) create((y0.z) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0059 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, y0.z] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.Object, y0.z] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [s7.h, z7.n] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v7 */
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
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0057 -> B:12:0x0028). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x006c -> B:12:0x0028). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
        /*
            r8 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r8.f169m
            q7.h r2 = r8.o
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L34
            if (r1 == r5) goto L2c
            if (r1 == r4) goto L21
            if (r1 != r3) goto L19
            java.lang.Object r1 = r8.f170n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r9)
            goto L28
        L19:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L21:
            java.lang.Object r1 = r8.f170n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r9)     // Catch: java.util.concurrent.CancellationException -> L2a
        L28:
            r9 = r1
            goto L3b
        L2a:
            r9 = move-exception
            goto L5e
        L2c:
            java.lang.Object r1 = r8.f170n
            y0.z r1 = (y0.z) r1
            h8.AbstractC1386c.u(r9)     // Catch: java.util.concurrent.CancellationException -> L2a
            goto L4f
        L34:
            h8.AbstractC1386c.u(r9)
            java.lang.Object r9 = r8.f170n
            y0.z r9 = (y0.z) r9
        L3b:
            boolean r1 = S8.AbstractC0727y.t(r2)
            if (r1 == 0) goto L70
            s7.h r1 = r8.f171p     // Catch: java.util.concurrent.CancellationException -> L5a
            r8.f170n = r9     // Catch: java.util.concurrent.CancellationException -> L5a
            r8.f169m = r5     // Catch: java.util.concurrent.CancellationException -> L5a
            java.lang.Object r1 = r1.invoke(r9, r8)     // Catch: java.util.concurrent.CancellationException -> L5a
            if (r1 != r0) goto L4e
            return r0
        L4e:
            r1 = r9
        L4f:
            r8.f170n = r1     // Catch: java.util.concurrent.CancellationException -> L2a
            r8.f169m = r4     // Catch: java.util.concurrent.CancellationException -> L2a
            java.lang.Object r9 = C7.a.v(r1, r8)     // Catch: java.util.concurrent.CancellationException -> L2a
            if (r9 != r0) goto L28
            return r0
        L5a:
            r1 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
        L5e:
            boolean r6 = S8.AbstractC0727y.t(r2)
            if (r6 == 0) goto L6f
            r8.f170n = r1
            r8.f169m = r3
            java.lang.Object r9 = C7.a.v(r1, r8)
            if (r9 != r0) goto L28
            return r0
        L6f:
            throw r9
        L70:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: A.C0013f0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
