package org.chromium.components.signin;

import defpackage.i9;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: ConnectionRetry.java */
/* JADX INFO: loaded from: classes3.dex */
public final class c extends i9<Object> {
    public final /* synthetic */ d h;

    public c(d dVar) {
        this.h = dVar;
    }

    @Override // defpackage.i9
    public final Object b() {
        return this.h.e.run();
    }

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
    @Override // defpackage.i9
    public final void l(Object obj) {
        d dVar = this.h;
        if (obj != null) {
            dVar.e.a(obj);
            return;
        }
        if (dVar.g.get() && dVar.f.incrementAndGet() < 3) {
            NetworkChangeNotifier networkChangeNotifier = NetworkChangeNotifier.f;
            if (networkChangeNotifier != null) {
                networkChangeNotifier.b.a(dVar);
                return;
            }
        }
        dVar.e.b(dVar.g.get());
    }
}
