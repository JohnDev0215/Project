package com.amazon.mp3.amplifyqueue.utils;

import android.util.Log;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RetryWithDelay.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u001c\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00020\u0001B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001e\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00022\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0016R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/RetryWithDelay;", "Lio/reactivex/rxjava3/functions/Function;", "Lio/reactivex/rxjava3/core/Observable;", "", "maxRetries", "", "retryDelayMillis", "(II)V", "TAG", "", "kotlin.jvm.PlatformType", "retryCount", "apply", "attempts", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RetryWithDelay implements Function<Observable<Throwable>, Observable<?>> {
    private final String TAG = RetryWithDelay.class.getName();
    private final int maxRetries;
    private int retryCount;
    private final int retryDelayMillis;

    public RetryWithDelay(int i, int i2) {
        this.maxRetries = i;
        this.retryDelayMillis = i2;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    public Observable<?> apply(Observable<Throwable> attempts) {
        Intrinsics.checkNotNullParameter(attempts, "attempts");
        return attempts.flatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.utils.RetryWithDelay$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RetryWithDelay.m223apply$lambda0(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: apply$lambda-0, reason: not valid java name */
    public static final ObservableSource m223apply$lambda0(RetryWithDelay this$0, Throwable th) {
        Throwable cause;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.retryCount + 1;
        this$0.retryCount = i;
        if (i < this$0.maxRetries && AmplifyDatastoreUtil.INSTANCE.isErrorShouldRetry(th)) {
            String str = this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("attempt ");
            sb.append(this$0.retryCount);
            sb.append(", previous err=");
            String message = null;
            if (th != null && (cause = th.getCause()) != null) {
                message = cause.getMessage();
            }
            sb.append((Object) message);
            Log.w(str, sb.toString());
            return Observable.timer(this$0.retryDelayMillis, TimeUnit.MILLISECONDS);
        }
        return Observable.error(th);
    }
}
