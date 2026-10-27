package com.amazon.mp3.util.extensions;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Rx.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u001c\u0012\u000e\u0012\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00022\u0010\u0010\u000b\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00030\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/util/extensions/RetryWithDelay;", "Lio/reactivex/functions/Function;", "Lio/reactivex/Observable;", "", "maxRetries", "", "retryDelayMillis", "", "(IJ)V", "retryCount", "apply", "attempts", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    private final int maxRetries;
    private int retryCount;
    private final long retryDelayMillis;

    public RetryWithDelay(int i, long j) {
        this.maxRetries = i;
        this.retryDelayMillis = j;
    }

    @Override // io.reactivex.functions.Function
    public Observable<?> apply(Observable<? extends Throwable> attempts) {
        Intrinsics.checkNotNullParameter(attempts, "attempts");
        Observable observableFlatMap = attempts.flatMap(new Function() { // from class: com.amazon.mp3.util.extensions.RetryWithDelay$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return RetryWithDelay.m1660apply$lambda0(this.f$0, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "attempts.flatMap { throw…rror(throwable)\n        }");
        return observableFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: apply$lambda-0, reason: not valid java name */
    public static final ObservableSource m1660apply$lambda0(RetryWithDelay this$0, Throwable throwable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        int i = this$0.retryCount + 1;
        this$0.retryCount = i;
        if (i < this$0.maxRetries) {
            return Observable.timer(this$0.retryDelayMillis, TimeUnit.MILLISECONDS);
        }
        return Observable.error(throwable);
    }
}
