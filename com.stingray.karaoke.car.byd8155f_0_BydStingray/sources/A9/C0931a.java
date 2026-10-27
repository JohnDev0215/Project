package a9;

import A7.j;
import S8.C0711h;
import S8.F;
import U8.l;
import X8.r;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: a9.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0931a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f11219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j f11220b;
    public final j c;
    public final InterfaceC2785n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0711h f11221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public r f11222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f11223g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ C0933c f11224h;

    /* JADX WARN: Multi-variable type inference failed */
    public C0931a(C0933c c0933c, l lVar, InterfaceC2786o interfaceC2786o, InterfaceC2786o interfaceC2786o2, InterfaceC2785n interfaceC2785n, C0711h c0711h) {
        this.f11224h = c0933c;
        this.f11219a = lVar;
        this.f11220b = (j) interfaceC2786o;
        this.c = (j) interfaceC2786o2;
        this.d = interfaceC2785n;
        this.f11221e = c0711h;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void a() {
        r rVar = this.f11222f;
        if (rVar != 0) {
            rVar.h(this.f11223g, this.f11224h.l);
            return;
        }
        F f10 = rVar instanceof F ? (F) rVar : null;
        if (f10 != null) {
            f10.a();
        }
    }
}
