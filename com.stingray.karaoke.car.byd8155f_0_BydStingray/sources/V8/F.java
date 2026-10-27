package V8;

import S8.InterfaceC0725w;
import W8.AbstractC0842b;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class F extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ W f9565m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f f9566n;
    public final /* synthetic */ AbstractC0842b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9567p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public F(W w10, InterfaceC0817f interfaceC0817f, K k10, Object obj, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f9565m = w10;
        this.f9566n = interfaceC0817f;
        this.o = (AbstractC0842b) k10;
        this.f9567p = obj;
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [V8.K, W8.b] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new F(this.f9565m, this.f9566n, this.o, this.f9567p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((F) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077  */
    /* JADX WARN: Type inference failed for: r3v0, types: [V8.K, V8.g, W8.b] */
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
            V8.f r2 = r8.f9566n
            W8.b r3 = r8.o
            r4 = 4
            r5 = 3
            r6 = 1
            r7 = 2
            if (r1 == 0) goto L27
            if (r1 == r6) goto L23
            if (r1 == r7) goto L1f
            if (r1 == r5) goto L23
            if (r1 != r4) goto L17
            goto L23
        L17:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L1f:
            h8.AbstractC1386c.u(r9)
            goto L50
        L23:
            h8.AbstractC1386c.u(r9)
            goto L8f
        L27:
            h8.AbstractC1386c.u(r9)
            V8.X r9 = V8.V.f9597a
            V8.W r1 = r8.f9565m
            if (r1 != r9) goto L39
            r8.l = r6
            java.lang.Object r8 = r2.b(r3, r8)
            if (r8 != r0) goto L8f
            return r0
        L39:
            V8.X r9 = V8.V.f9598b
            r6 = 0
            if (r1 != r9) goto L59
            W8.E r9 = r3.i()
            V8.D r1 = new V8.D
            r1.<init>(r7, r6)
            r8.l = r7
            java.lang.Object r9 = V8.AbstractC0828q.k(r9, r1, r8)
            if (r9 != r0) goto L50
            return r0
        L50:
            r8.l = r5
            java.lang.Object r8 = r2.b(r3, r8)
            if (r8 != r0) goto L8f
            return r0
        L59:
            W8.E r9 = r3.i()
            V8.f r9 = r1.a(r9)
            boolean r1 = r9 instanceof V8.d0
            if (r1 == 0) goto L66
            goto L7f
        L66:
            Q5.h2 r1 = V8.AbstractC0828q.f9645a
            boolean r5 = r9 instanceof V8.C0816e
            if (r5 == 0) goto L77
            r5 = r9
            V8.e r5 = (V8.C0816e) r5
            r5.getClass()
            z7.n r5 = r5.f9614m
            if (r5 != r1) goto L77
            goto L7d
        L77:
            V8.e r5 = new V8.e
            r5.<init>(r9, r1)
            r9 = r5
        L7d:
            V8.e r9 = (V8.C0816e) r9
        L7f:
            V8.E r1 = new V8.E
            java.lang.Object r5 = r8.f9567p
            r1.<init>(r2, r3, r5, r6)
            r8.l = r4
            java.lang.Object r8 = V8.AbstractC0828q.g(r9, r1, r8)
            if (r8 != r0) goto L8f
            return r0
        L8f:
            m7.q r8 = m7.q.f19267a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: V8.F.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
