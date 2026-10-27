package com.amazon.music.signin;

import androidx.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/* JADX INFO: loaded from: classes4.dex */
public class RxSignInRetryStrategyUtility {
    private static final Logger LOG = LoggerFactory.getLogger(RxSignInRetryStrategyUtility.class.getSimpleName());

    private static final class LinkCodeError {
        final Integer errorNumber;
        final Throwable throwableError;

        public LinkCodeError(int i, @NonNull Throwable th) {
            this.errorNumber = Integer.valueOf(i);
            this.throwableError = th;
        }
    }

    public static final class RetryLimitError extends Error {
        public RetryLimitError() {
        }

        public RetryLimitError(String str) {
            super(str);
        }
    }

    protected static final class AuthenticationFailedError extends Error {
        public AuthenticationFailedError() {
        }

        public AuthenticationFailedError(String str) {
            super(str);
        }
    }

    public static long backoffWaitTime(long j, long j2, int i) {
        return (long) Math.min(j * Math.pow(2.0d, i), j2);
    }

    public static Func1<Observable<? extends Throwable>, Observable<?>> exponentialBackoffStrategy(final int i, final int i2, final long j, final long j2) {
        return new Func1<Observable<? extends Throwable>, Observable<?>>() { // from class: com.amazon.music.signin.RxSignInRetryStrategyUtility.1
            @Override // rx.functions.Func1
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.zipWith(Observable.range(i, i2 + 1), new Func2<Throwable, Integer, LinkCodeError>() { // from class: com.amazon.music.signin.RxSignInRetryStrategyUtility.1.2
                    @Override // rx.functions.Func2
                    public LinkCodeError call(Throwable th, Integer num) {
                        return new LinkCodeError(num.intValue(), th);
                    }
                }).flatMap(new Func1<LinkCodeError, Observable<?>>() { // from class: com.amazon.music.signin.RxSignInRetryStrategyUtility.1.1
                    @Override // rx.functions.Func1
                    public Observable<?> call(LinkCodeError linkCodeError) {
                        if (linkCodeError.throwableError instanceof InternalError) {
                            return Observable.error(new Error("An internal error occurred."));
                        }
                        int iIntValue = linkCodeError.errorNumber.intValue();
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        if (iIntValue != i + i2) {
                            RxSignInRetryStrategyUtility.LOG.debug("Trying server for link code... attempt " + linkCodeError.errorNumber + "...");
                            AnonymousClass1 anonymousClass12 = AnonymousClass1.this;
                            return Observable.timer(RxSignInRetryStrategyUtility.backoffWaitTime(j, j2, linkCodeError.errorNumber.intValue()), TimeUnit.MILLISECONDS);
                        }
                        return Observable.error(new RetryLimitError("Max retry limit reached."));
                    }
                });
            }
        };
    }

    public static Func1<Observable<? extends Throwable>, Observable<?>> iterativePollingStrategy(final long j) {
        return new Func1<Observable<? extends Throwable>, Observable<?>>() { // from class: com.amazon.music.signin.RxSignInRetryStrategyUtility.2
            @Override // rx.functions.Func1
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() { // from class: com.amazon.music.signin.RxSignInRetryStrategyUtility.2.1
                    @Override // rx.functions.Func1
                    public Observable<?> call(Throwable th) {
                        if (th instanceof AuthenticationFailedError) {
                            RxSignInRetryStrategyUtility.LOG.error("Authentication failed. Retrying.");
                            return Observable.timer(j, TimeUnit.SECONDS);
                        }
                        return Observable.error(th);
                    }
                });
            }
        };
    }
}
