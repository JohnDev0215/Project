package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends Observable {
    public final ObservableSource c;

    public a(ObservableSource observableSource) {
        this.c = observableSource;
    }
}
