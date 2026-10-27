package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class o3 extends a {
    public final io.reactivex.rxjava3.functions.o e;

    public o3(ObservableSource observableSource, io.reactivex.rxjava3.functions.o oVar) {
        super(observableSource);
        this.e = oVar;
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
    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        io.reactivex.rxjava3.subjects.c cVar = new io.reactivex.rxjava3.subjects.c();
        boolean z = cVar instanceof io.reactivex.rxjava3.subjects.d;
        io.reactivex.rxjava3.subjects.e dVar = cVar;
        if (!z) {
            dVar = new io.reactivex.rxjava3.subjects.d(cVar);
        }
        try {
            Object objApply = this.e.apply(dVar);
            Objects.requireNonNull(objApply, "The handler returned a null ObservableSource");
            ObservableSource observableSource = (ObservableSource) objApply;
            ObservableRepeatWhen$RepeatWhenObserver observableRepeatWhen$RepeatWhenObserver = new ObservableRepeatWhen$RepeatWhenObserver(observer, dVar, this.c);
            observer.onSubscribe(observableRepeatWhen$RepeatWhenObserver);
            observableSource.subscribe(observableRepeatWhen$RepeatWhenObserver.inner);
            observableRepeatWhen$RepeatWhenObserver.a();
        } catch (Throwable th) {
            he1.R(th);
            observer.onSubscribe(EmptyDisposable.c);
            observer.onError(th);
        }
    }
}
