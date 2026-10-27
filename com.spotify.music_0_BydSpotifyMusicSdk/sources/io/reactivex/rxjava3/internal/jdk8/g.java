package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class g extends Observable {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ g(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    public static void a(Observer observer, Stream stream) {
        Disposable disposable = EmptyDisposable.c;
        try {
            Iterator it = stream.iterator();
            if (it.hasNext()) {
                h hVar = new h(observer, it, stream);
                observer.onSubscribe(hVar);
                hVar.a();
                return;
            }
            observer.onSubscribe(disposable);
            observer.onComplete();
            try {
                stream.close();
            } catch (Throwable th) {
                he1.R(th);
                RxJavaPlugins.b(th);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            observer.onSubscribe(disposable);
            observer.onError(th2);
            try {
                stream.close();
            } catch (Throwable th3) {
                he1.R(th3);
                RxJavaPlugins.b(th3);
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ObservableFromCompletionStage$BiConsumerAtomicReference observableFromCompletionStage$BiConsumerAtomicReference = new ObservableFromCompletionStage$BiConsumerAtomicReference();
                ObservableFromCompletionStage$CompletionStageHandler observableFromCompletionStage$CompletionStageHandler = new ObservableFromCompletionStage$CompletionStageHandler(observer, observableFromCompletionStage$BiConsumerAtomicReference);
                observableFromCompletionStage$BiConsumerAtomicReference.lazySet(observableFromCompletionStage$CompletionStageHandler);
                observer.onSubscribe(observableFromCompletionStage$CompletionStageHandler);
                ((CompletionStage) obj).whenComplete(observableFromCompletionStage$BiConsumerAtomicReference);
                break;
            default:
                a(observer, (Stream) obj);
                break;
        }
    }
}
