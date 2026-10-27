package K5;

import S8.InterfaceC0725w;
import com.stingray.kca.model.KaraokeItem;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D extends s7.i implements InterfaceC2785n {
    public KaraokeItem l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3608n;
    public final /* synthetic */ F o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ String f3609p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f3610q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f3611r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f3612s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f3613t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ String f3614u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(F f10, String str, boolean z6, int i7, int i10, boolean z10, String str2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = f10;
        this.f3609p = str;
        this.f3610q = z6;
        this.f3611r = i7;
        this.f3612s = i10;
        this.f3613t = z10;
        this.f3614u = str2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new D(this.o, this.f3609p, this.f3610q, this.f3611r, this.f3612s, this.f3613t, this.f3614u, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((D) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006c  */
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
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003e -> B:6:0x0011). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r7.f3608n
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1b
            if (r1 != r3) goto L13
            int r1 = r7.f3607m
            com.stingray.kca.model.KaraokeItem r4 = r7.l
            h8.AbstractC1386c.u(r8)
        L11:
            r8 = r4
            goto L41
        L13:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L1b:
            h8.AbstractC1386c.u(r8)
            r8 = 0
            r1 = r8
            r8 = r2
        L21:
            r4 = 5
            java.lang.String r5 = r7.f3609p
            K5.F r6 = r7.o
            if (r1 >= r4) goto L43
            K5.u r8 = r6.f3618b
            com.stingray.kca.model.KaraokeItem r4 = r8.d(r5)
            if (r4 == 0) goto L32
            r8 = r4
            goto L43
        L32:
            r7.l = r4
            r7.f3607m = r1
            r7.f3608n = r3
            r5 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r8 = S8.AbstractC0727y.i(r5, r7)
            if (r8 != r0) goto L11
            return r0
        L41:
            int r1 = r1 + r3
            goto L21
        L43:
            n7.u r0 = n7.u.l
            boolean r1 = r7.f3610q
            if (r1 == 0) goto L53
            if (r8 == 0) goto L5b
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r8.f13111g = r1
            goto L5b
        L53:
            if (r8 == 0) goto L57
            java.util.List r2 = r8.f13111g
        L57:
            if (r2 != 0) goto L5a
            goto L5b
        L5a:
            r0 = r2
        L5b:
            int r8 = r7.f3612s
            int r1 = r7.f3611r
            if (r1 != 0) goto L68
            int r2 = r0.size()
            if (r2 >= r8) goto L68
            goto L6c
        L68:
            boolean r2 = r7.f3613t
            if (r2 == 0) goto Lb1
        L6c:
            k6.r r0 = r6.d
            V8.N r0 = r0.f18294e
            V8.L r0 = r0.l
            V8.f0 r0 = (V8.f0) r0
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r0 = r0 ^ r3
            java.lang.String r7 = r7.f3614u
            java.util.ArrayList r7 = K5.F.a(r6, r7, r8, r1, r0)
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L93:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto Lac
            java.lang.Object r1 = r7.next()
            r2 = r1
            com.stingray.kca.model.KaraokeItem r2 = (com.stingray.kca.model.KaraokeItem) r2
            java.lang.String r2 = r2.f13107a
            boolean r2 = r8.add(r2)
            if (r2 == 0) goto L93
            r0.add(r1)
            goto L93
        Lac:
            K5.u r7 = r6.f3618b
            r7.c(r5, r0)
        Lb1:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: K5.D.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
