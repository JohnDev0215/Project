package io.reactivex;

/* JADX INFO: loaded from: classes5.dex */
public interface ObservableTransformer<Upstream, Downstream> {
    ObservableSource<Downstream> apply(Observable<Upstream> observable);
}
