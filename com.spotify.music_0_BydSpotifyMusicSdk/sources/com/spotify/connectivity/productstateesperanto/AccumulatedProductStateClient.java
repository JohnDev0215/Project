package com.spotify.connectivity.productstateesperanto;

import com.google.common.base.Optional;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.o;
import java.util.Map;
import p.gu;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AccumulatedProductStateClient {
    private final ObservableTransformer<Optional<Map<String, String>>, Map<String, String>> accumulator;
    private final gu coldStartupTimeKeeper;
    private final ProductStateMethods productStateMethods;

    public AccumulatedProductStateClient(ProductStateMethods productStateMethods, gu guVar, ObservableTransformer<Optional<Map<String, String>>, Map<String, String>> observableTransformer) {
        v03.j(productStateMethods, "productStateMethods");
        v03.j(guVar, "coldStartupTimeKeeper");
        v03.j(observableTransformer, "accumulator");
        this.productStateMethods = productStateMethods;
        this.accumulator = observableTransformer;
    }

    public static final /* synthetic */ gu access$getColdStartupTimeKeeper$p(AccumulatedProductStateClient accumulatedProductStateClient) {
        accumulatedProductStateClient.getClass();
        return null;
    }

    public final Observable<Map<String, String>> get() {
        Observable<Map<String, String>> observableCompose = this.productStateMethods.values().publish(new o() { // from class: com.spotify.connectivity.productstateesperanto.AccumulatedProductStateClient.get.1
            @Override // io.reactivex.rxjava3.functions.o
            public final ObservableSource<Map<String, String>> apply(final Observable<Map<String, String>> observable) {
                Single<Map<String, String>> singleSingleOrError = observable.take(1L).singleOrError();
                final AccumulatedProductStateClient accumulatedProductStateClient = AccumulatedProductStateClient.this;
                Single<Map<String, String>> singleDoOnSubscribe = singleSingleOrError.doOnSubscribe(new g() { // from class: com.spotify.connectivity.productstateesperanto.AccumulatedProductStateClient.get.1.1
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Disposable disposable) {
                        AccumulatedProductStateClient.access$getColdStartupTimeKeeper$p(accumulatedProductStateClient);
                        throw null;
                    }
                });
                final AccumulatedProductStateClient accumulatedProductStateClient2 = AccumulatedProductStateClient.this;
                return singleDoOnSubscribe.doOnSuccess(new g() { // from class: com.spotify.connectivity.productstateesperanto.AccumulatedProductStateClient.get.1.2
                    @Override // io.reactivex.rxjava3.functions.g
                    public final void accept(Map<String, String> map) {
                        AccumulatedProductStateClient.access$getColdStartupTimeKeeper$p(accumulatedProductStateClient2);
                        throw null;
                    }
                }).flatMapObservable(new o() { // from class: com.spotify.connectivity.productstateesperanto.AccumulatedProductStateClient.get.1.3
                    @Override // io.reactivex.rxjava3.functions.o
                    public final Observable<Map<String, String>> apply(Map<String, String> map) {
                        return observable.startWithItem(map);
                    }
                });
            }
        }).map(new o() { // from class: com.spotify.connectivity.productstateesperanto.AccumulatedProductStateClient.get.2
            @Override // io.reactivex.rxjava3.functions.o
            public final Optional<Map<String, String>> apply(Map<String, String> map) {
                return Optional.e(map);
            }
        }).compose(this.accumulator);
        v03.i(observableCompose, "fun get(): Observable<Ma…    .compose(accumulator)");
        return observableCompose;
    }
}
