package defpackage;

import org.chromium.ui.modelutil.h;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class w62 implements k.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ w62(int i, Object obj) {
        this.e = i;
        this.f = obj;
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
    @Override // org.chromium.ui.modelutil.k.a
    public final void a(k kVar, Object obj) {
        int i = this.e;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                h hVar = (h) obj2;
                hVar.q(hVar.indexOf(kVar), 1, obj);
                break;
            default:
                j jVar = (j) obj2;
                jVar.c.b(jVar.b, jVar.a, obj);
                break;
        }
    }
}
