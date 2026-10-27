package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes7.dex */
public abstract class FlowableProcessor<T> extends Flowable<T> implements Subscriber, FlowableSubscriber<T> {
    public final FlowableProcessor<T> toSerialized() {
        return this instanceof SerializedProcessor ? this : new SerializedProcessor(this);
    }
}
