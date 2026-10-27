package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
final class CompletableResumeNext$ResumeNextObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
    private static final long serialVersionUID = 5018523762564524046L;
    final CompletableObserver downstream;
    final o errorMapper;
    boolean once;

    public CompletableResumeNext$ResumeNextObserver(CompletableObserver completableObserver, o oVar) {
        this.downstream = completableObserver;
        this.errorMapper = oVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        DisposableHelper.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.b(get());
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        this.downstream.onComplete();
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        if (this.once) {
            this.downstream.onError(th);
            return;
        }
        this.once = true;
        try {
            Object objApply = this.errorMapper.apply(th);
            Objects.requireNonNull(objApply, "The errorMapper returned a null CompletableSource");
            ((CompletableSource) objApply).subscribe(this);
        } catch (Throwable th2) {
            he1.R(th2);
            this.downstream.onError(new CompositeException(th, th2));
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        DisposableHelper.c(this, disposable);
    }
}
