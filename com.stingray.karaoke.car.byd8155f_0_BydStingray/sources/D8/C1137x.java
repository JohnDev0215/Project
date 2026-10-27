package d8;

import b8.C1012g;
import c8.C1036a;
import java.util.LinkedHashSet;
import java.util.List;
import n7.AbstractC1998m;
import o8.C2061f;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1137x extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13630m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ z f13631n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1137x(z zVar, int i7) {
        super(0);
        this.f13630m = i7;
        this.f13631n = zVar;
    }

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
    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f13630m) {
            case 0:
                y8.f fVar = y8.f.f24196m;
                y8.n.f24214a.getClass();
                y8.k kVar = y8.k.f24208n;
                z zVar = this.f13631n;
                zVar.getClass();
                A7.m.f(fVar, "kindFilter");
                Y7.b bVar = Y7.b.o;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                if (fVar.a(y8.f.l)) {
                    for (C2061f c2061f : zVar.h(fVar, kVar)) {
                        kVar.b(c2061f);
                        O8.l.a(linkedHashSet, zVar.c(c2061f, bVar));
                    }
                }
                boolean zA = fVar.a(y8.f.f24193i);
                List list = fVar.f24202a;
                if (zA && !list.contains(y8.b.f24184a)) {
                    for (C2061f c2061f2 : zVar.i(fVar, kVar)) {
                        kVar.b(c2061f2);
                        linkedHashSet.addAll(zVar.a(c2061f2, bVar));
                    }
                }
                if (fVar.a(y8.f.f24194j) && !list.contains(y8.b.f24184a)) {
                    for (C2061f c2061f3 : zVar.o(fVar)) {
                        kVar.b(c2061f3);
                        linkedHashSet.addAll(zVar.b(c2061f3, bVar));
                    }
                }
                return AbstractC1998m.z0(linkedHashSet);
            case 1:
                return this.f13631n.h(y8.f.o, null);
            case 2:
                return this.f13631n.k();
            case 3:
                return this.f13631n.i(y8.f.f24198p, null);
            case 4:
                return this.f13631n.o(y8.f.f24199q);
            default:
                ((C1036a) this.f13631n.f13635b.f4399m).f12416h.getClass();
                return null;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1137x(z zVar, W7.t tVar, C1012g c1012g) {
        super(0);
        this.f13630m = 5;
        this.f13631n = zVar;
    }
}
