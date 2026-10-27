package rxdogtag2;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public interface ObserverHandler {
    default CompletableObserver handle(Completable completable, CompletableObserver completableObserver) {
        return completableObserver;
    }

    default MaybeObserver handle(Maybe maybe, MaybeObserver maybeObserver) {
        return maybeObserver;
    }

    default Observer handle(Observable observable, Observer observer) {
        return observer;
    }

    default SingleObserver handle(Single single, SingleObserver singleObserver) {
        return singleObserver;
    }

    default rg2 handle(Flowable flowable, rg2 rg2Var) {
        return rg2Var;
    }
}
