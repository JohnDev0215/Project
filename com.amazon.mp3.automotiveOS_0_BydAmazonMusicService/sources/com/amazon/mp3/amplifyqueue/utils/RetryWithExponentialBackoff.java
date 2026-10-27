package com.amazon.mp3.amplifyqueue.utils;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LoggingProvider;
import com.amplifyframework.api.ApiException;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import org.reactivestreams.Publisher;

/* JADX INFO: compiled from: RetryWithExponentialBackoff.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u00002\u001a\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0001B7\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00042\u000e\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H\u0016R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n \u0013*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/amplifyqueue/utils/RetryWithExponentialBackoff;", "Lio/reactivex/rxjava3/functions/Function;", "Lio/reactivex/rxjava3/core/Flowable;", "", "Lorg/reactivestreams/Publisher;", "eventName", "", "maxRetries", "", "withJitter", "", "defaultScheduler", "Lio/reactivex/rxjava3/core/Scheduler;", "loggingProvider", "Lcom/amazon/music/platform/providers/LoggingProvider;", "(Ljava/lang/String;IZLio/reactivex/rxjava3/core/Scheduler;Lcom/amazon/music/platform/providers/LoggingProvider;)V", "BASE", "", "TAG", "kotlin.jvm.PlatformType", "maxJitterDelayInMillis", "", "retryAttempts", "apply", "attempt", "amplifyqueue_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RetryWithExponentialBackoff implements Function<Flowable<Throwable>, Publisher<?>> {
    private final double BASE;
    private final String TAG;
    private final Scheduler defaultScheduler;
    private final String eventName;
    private final LoggingProvider loggingProvider;
    private final long maxJitterDelayInMillis;
    private final int maxRetries;
    private int retryAttempts;
    private final boolean withJitter;

    public RetryWithExponentialBackoff(String eventName, int i, boolean z, Scheduler defaultScheduler, LoggingProvider loggingProvider) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(defaultScheduler, "defaultScheduler");
        this.eventName = eventName;
        this.maxRetries = i;
        this.withJitter = z;
        this.defaultScheduler = defaultScheduler;
        this.loggingProvider = loggingProvider;
        this.BASE = 3.0d;
        this.maxJitterDelayInMillis = 500L;
        this.TAG = RetryWithExponentialBackoff.class.getSimpleName();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ RetryWithExponentialBackoff(String str, int i, boolean z, Scheduler scheduler, LoggingProvider loggingProvider, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        int i3 = (i2 & 2) != 0 ? 3 : i;
        boolean z2 = (i2 & 4) != 0 ? true : z;
        if ((i2 & 8) != 0) {
            scheduler = Schedulers.computation();
            Intrinsics.checkNotNullExpressionValue(scheduler, "computation()");
        }
        Scheduler scheduler2 = scheduler;
        if ((i2 & 16) != 0) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LoggingProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            loggingProvider = (LoggingProvider) (interfaceProvider instanceof LoggingProvider ? interfaceProvider : null);
        }
        this(str, i3, z2, scheduler2, loggingProvider);
    }

    @Override // io.reactivex.rxjava3.functions.Function
    public Publisher<?> apply(Flowable<Throwable> attempt) {
        Intrinsics.checkNotNullParameter(attempt, "attempt");
        Publisher publisherFlatMap = attempt.flatMap(new Function() { // from class: com.amazon.mp3.amplifyqueue.utils.RetryWithExponentialBackoff$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RetryWithExponentialBackoff.m225apply$lambda0(this.f$0, (Throwable) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(publisherFlatMap, "attempt.flatMap { error …}\n            }\n        }");
        return publisherFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: apply$lambda-0, reason: not valid java name */
    public static final Publisher m225apply$lambda0(RetryWithExponentialBackoff this$0, Throwable th) {
        Throwable cause;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (th instanceof ApiException.NonRetryableException) {
            LoggingProvider loggingProvider = this$0.loggingProvider;
            if (loggingProvider != null) {
                String TAG = this$0.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
                StringBuilder sb = new StringBuilder();
                sb.append("event ");
                sb.append(this$0.eventName);
                sb.append(" failed due to non-retryable ApiException, previous err=");
                Throwable cause2 = th.getCause();
                sb.append((Object) (cause2 != null ? cause2.getMessage() : null));
                loggingProvider.logWarning(TAG, sb.toString());
            }
            return Flowable.error(th);
        }
        if (this$0.retryAttempts < this$0.maxRetries) {
            LoggingProvider loggingProvider2 = this$0.loggingProvider;
            if (loggingProvider2 != null) {
                String TAG2 = this$0.TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                StringBuilder sb2 = new StringBuilder();
                sb2.append("event ");
                sb2.append(this$0.eventName);
                sb2.append(" with attempt ");
                sb2.append(this$0.retryAttempts);
                sb2.append(" and maxRetries ");
                sb2.append(this$0.maxRetries);
                sb2.append(", previous err=");
                if (th != null && (cause = th.getCause()) != null) {
                    message = cause.getMessage();
                }
                sb2.append((Object) message);
                loggingProvider2.logWarning(TAG2, sb2.toString());
            }
            Random.Companion companion = Random.INSTANCE;
            long j = this$0.maxJitterDelayInMillis;
            long jNextLong = companion.nextLong(-j, j + 1);
            TimeUnit timeUnit = TimeUnit.SECONDS;
            double d = this$0.BASE;
            int i = this$0.retryAttempts;
            this$0.retryAttempts = i + 1;
            long millis = timeUnit.toMillis((long) Math.pow(d, i));
            if (this$0.withJitter) {
                millis += jNextLong;
            }
            return Flowable.just(Long.valueOf(millis)).delay(millis, TimeUnit.MILLISECONDS, this$0.defaultScheduler);
        }
        return Flowable.error(th);
    }
}
