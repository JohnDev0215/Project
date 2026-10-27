package io.reactivex;

/* JADX INFO: loaded from: classes5.dex */
public interface ObservableConverter<T, R> {
    R apply(Observable<T> observable);
}
