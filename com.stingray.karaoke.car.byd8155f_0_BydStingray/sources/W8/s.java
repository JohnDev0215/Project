package W8;

import S8.InterfaceC0725w;
import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class s extends s7.i implements InterfaceC2785n {
    public U8.p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte[] f9934m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f9935n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9936p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public /* synthetic */ Object f9937q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0817f[] f9938r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ s7.i f9939s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0818g f9940t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public s(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c, InterfaceC2786o interfaceC2786o, InterfaceC0817f[] interfaceC0817fArr) {
        super(2, interfaceC2189c);
        this.f9938r = interfaceC0817fArr;
        this.f9939s = (s7.i) interfaceC2786o;
        this.f9940t = interfaceC0818g;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [s7.i, z7.o] */
    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        s sVar = new s(this.f9940t, interfaceC2189c, this.f9939s, this.f9938r);
        sVar.f9937q = obj;
        return sVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((s) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ae, code lost:
    
        if (r6 != 0) goto L19;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00cb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cc A[LOOP:0: B:25:0x00cc->B:38:?, LOOP_START, PHI: r6 r7
  0x00cc: PHI (r6v3 int) = (r6v2 int), (r6v4 int) binds: [B:23:0x00c9, B:38:?] A[DONT_GENERATE, DONT_INLINE]
  0x00cc: PHI (r7v5 n7.x) = (r7v4 n7.x), (r7v11 n7.x) binds: [B:23:0x00c9, B:38:?] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ed  */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r7v1, types: [S8.x, q7.a] */
    /* JADX WARN: Type inference failed for: r7v12, types: [s7.i, z7.o] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0102 -> B:19:0x00ae). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: W8.s.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
