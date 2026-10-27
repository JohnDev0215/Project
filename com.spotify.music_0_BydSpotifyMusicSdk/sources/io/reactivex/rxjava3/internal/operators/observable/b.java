package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterable {
    public final ObservableSource c;
    public final int e;

    public b(ObservableSource observableSource, int i) {
        this.c = observableSource;
        this.e = i;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        BlockingObservableIterable$BlockingObservableIterator blockingObservableIterable$BlockingObservableIterator = new BlockingObservableIterable$BlockingObservableIterator(this.e);
        this.c.subscribe(blockingObservableIterable$BlockingObservableIterator);
        return blockingObservableIterable$BlockingObservableIterator;
    }
}
