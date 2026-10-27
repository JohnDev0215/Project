package io.reactivex.rxjava3.internal.operators.observable;

import java.util.Collection;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class v extends io.reactivex.rxjava3.observers.a {
    public final w e;

    public v(w wVar) {
        this.e = wVar;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.e.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        this.e.onError(th);
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
    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        w wVar = this.e;
        wVar.getClass();
        try {
            Object obj2 = wVar.n.get();
            Objects.requireNonNull(obj2, "The buffer supplied is null");
            Collection collection = (Collection) obj2;
            synchronized (wVar) {
                try {
                    Collection collection2 = wVar.r;
                    if (collection2 != null) {
                        wVar.r = collection;
                        wVar.g(collection2, wVar);
                    }
                } finally {
                }
            }
        } catch (Throwable th) {
            he1.R(th);
            wVar.dispose();
            wVar.e.onError(th);
        }
    }
}
