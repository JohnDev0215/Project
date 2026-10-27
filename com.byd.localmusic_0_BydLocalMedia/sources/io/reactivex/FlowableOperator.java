package io.reactivex;

import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes5.dex */
public interface FlowableOperator<Downstream, Upstream> {
    Subscriber<? super Upstream> apply(Subscriber<? super Downstream> subscriber) throws Exception;
}
