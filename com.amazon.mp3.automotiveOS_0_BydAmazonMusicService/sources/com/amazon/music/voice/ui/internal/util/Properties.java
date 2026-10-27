package com.amazon.music.voice.ui.internal.util;

import com.amazon.music.voice.ui.internal.util.BaseProperty;
import com.amazon.music.voice.ui.util.FloatProperty;
import rx.BackpressureOverflow;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/* JADX INFO: loaded from: classes4.dex */
public final class Properties {
    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$toObservable$0() {
    }

    private Properties() {
        throw new IllegalStateException("No instances!");
    }

    public static Observable<Float> toObservable(final FloatProperty floatProperty) {
        return Observable.create(new Observable.OnSubscribe<Float>() { // from class: com.amazon.music.voice.ui.internal.util.Properties.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Float> subscriber) {
                floatProperty.addOnChangedListener(new BaseProperty.OnChangedListener() { // from class: com.amazon.music.voice.ui.internal.util.Properties.1.1
                    @Override // com.amazon.music.voice.ui.internal.util.BaseProperty.OnChangedListener
                    public void onPropertyChanged() {
                        if (floatProperty.hasValue()) {
                            subscriber.onNext(Float.valueOf(floatProperty.get()));
                        }
                    }
                });
                if (floatProperty.hasValue()) {
                    subscriber.onNext(Float.valueOf(floatProperty.get()));
                }
            }
        }).onBackpressureBuffer(1L, new Action0() { // from class: com.amazon.music.voice.ui.internal.util.Properties$$ExternalSyntheticLambda0
            @Override // rx.functions.Action0
            public final void call() {
                Properties.lambda$toObservable$0();
            }
        }, BackpressureOverflow.ON_OVERFLOW_DROP_OLDEST);
    }
}
