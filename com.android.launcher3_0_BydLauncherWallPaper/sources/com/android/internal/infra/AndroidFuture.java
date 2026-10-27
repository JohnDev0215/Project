package com.android.internal.infra;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.ExceptionUtils;
import android.util.Log;
import com.android.internal.infra.IAndroidFuture;
import com.android.internal.util.ConcurrentUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidFuture<T> extends CompletableFuture<T> implements Parcelable {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = "AndroidFuture";
    private BiConsumer<? super T, ? super Throwable> mListener;
    private Executor mListenerExecutor;
    private final Object mLock;
    private final IAndroidFuture mRemoteOrigin;
    private Handler mTimeoutHandler;
    private static final StackTraceElement[] EMPTY_STACK_TRACE = new StackTraceElement[0];
    public static final Parcelable.Creator<AndroidFuture> CREATOR = new Parcelable.Creator<AndroidFuture>() { // from class: com.android.internal.infra.AndroidFuture.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AndroidFuture createFromParcel(Parcel parcel) {
            return new AndroidFuture(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AndroidFuture[] newArray(int i) {
            return new AndroidFuture[i];
        }
    };

    static /* synthetic */ Object lambda$thenCombine$1(Object obj, Void r1) {
        return obj;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AndroidFuture() {
        this.mLock = new Object();
        this.mListenerExecutor = ConcurrentUtils.DIRECT_EXECUTOR;
        this.mTimeoutHandler = Handler.getMain();
        this.mRemoteOrigin = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    AndroidFuture(Parcel parcel) {
        this.mLock = new Object();
        this.mListenerExecutor = ConcurrentUtils.DIRECT_EXECUTOR;
        this.mTimeoutHandler = Handler.getMain();
        if (parcel.readBoolean()) {
            if (parcel.readBoolean()) {
                completeExceptionally(unparcelException(parcel));
            } else {
                complete(parcel.readValue(null));
            }
            this.mRemoteOrigin = null;
            return;
        }
        this.mRemoteOrigin = IAndroidFuture.Stub.asInterface(parcel.readStrongBinder());
    }

    public static <U> AndroidFuture<U> completedFuture(U u) {
        AndroidFuture<U> androidFuture = new AndroidFuture<>();
        androidFuture.complete(u);
        return androidFuture;
    }

    @Override // java.util.concurrent.CompletableFuture
    public boolean complete(T t) {
        boolean zComplete = super.complete(t);
        if (zComplete) {
            onCompleted(t, null);
        }
        return zComplete;
    }

    @Override // java.util.concurrent.CompletableFuture
    public boolean completeExceptionally(Throwable th) {
        boolean zCompleteExceptionally = super.completeExceptionally(th);
        if (zCompleteExceptionally) {
            onCompleted(null, th);
        }
        return zCompleteExceptionally;
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        boolean zCancel = super.cancel(z);
        if (zCancel) {
            try {
                get();
                throw new IllegalStateException("Expected CancellationException");
            } catch (CancellationException e) {
                onCompleted(null, e);
            } catch (Throwable th) {
                throw new IllegalStateException("Expected CancellationException", th);
            }
        }
        return zCancel;
    }

    protected void onCompleted(T t, Throwable th) {
        BiConsumer<? super T, ? super Throwable> biConsumer;
        cancelTimeout();
        synchronized (this.mLock) {
            biConsumer = this.mListener;
            this.mListener = null;
        }
        if (biConsumer != null) {
            callListenerAsync(biConsumer, t, th);
        }
        IAndroidFuture iAndroidFuture = this.mRemoteOrigin;
        if (iAndroidFuture != null) {
            try {
                iAndroidFuture.complete(this);
            } catch (RemoteException e) {
                Log.e(LOG_TAG, "Failed to propagate completion", e);
            }
        }
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public AndroidFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> biConsumer) {
        return whenCompleteAsync((BiConsumer) biConsumer, ConcurrentUtils.DIRECT_EXECUTOR);
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public AndroidFuture<T> whenCompleteAsync(final BiConsumer<? super T, ? super Throwable> biConsumer, Executor executor) {
        Preconditions.checkNotNull(biConsumer);
        Preconditions.checkNotNull(executor);
        synchronized (this.mLock) {
            if (!isDone()) {
                final BiConsumer<? super T, ? super Throwable> biConsumer2 = this.mListener;
                if (biConsumer2 != null && executor != this.mListenerExecutor) {
                    super.whenCompleteAsync((BiConsumer) biConsumer, executor);
                    return this;
                }
                this.mListenerExecutor = executor;
                if (biConsumer2 != null) {
                    biConsumer = new BiConsumer() { // from class: com.android.internal.infra.-$$Lambda$AndroidFuture$kx741v6MzvOXSJxFsggc3kWLfOg
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            AndroidFuture.lambda$whenCompleteAsync$0(biConsumer2, biConsumer, obj, (Throwable) obj2);
                        }
                    };
                }
                this.mListener = biConsumer;
                return this;
            }
            T t = null;
            try {
                th = null;
                t = get();
            } catch (ExecutionException e) {
                th = e.getCause();
            } catch (Throwable th) {
                th = th;
            }
            callListenerAsync(biConsumer, t, th);
            return this;
        }
    }

    static /* synthetic */ void lambda$whenCompleteAsync$0(BiConsumer biConsumer, BiConsumer biConsumer2, Object obj, Throwable th) {
        callListener(biConsumer, obj, th);
        callListener(biConsumer2, obj, th);
    }

    private void callListenerAsync(BiConsumer<? super T, ? super Throwable> biConsumer, T t, Throwable th) {
        if (this.mListenerExecutor == ConcurrentUtils.DIRECT_EXECUTOR) {
            callListener(biConsumer, t, th);
        } else {
            this.mListenerExecutor.execute(PooledLambda.obtainRunnable(new TriConsumer() { // from class: com.android.internal.infra.-$$Lambda$WEYP2nH1aLjoEoTBPtgGX_ZG53Y
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    AndroidFuture.callListener((BiConsumer) obj, obj2, (Throwable) obj3);
                }
            }, biConsumer, t, th).recycleOnUse());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <TT> void callListener(BiConsumer<? super TT, ? super Throwable> biConsumer, TT tt, Throwable th) {
        try {
            biConsumer.accept(tt, th);
        } catch (Throwable th2) {
            try {
                if (th == null) {
                    biConsumer.accept(null, th2);
                    return;
                }
                throw ExceptionUtils.appendCause(th2, th);
            } catch (Throwable th3) {
                Log.e(LOG_TAG, "Failed to call whenComplete listener. res = " + tt, th3);
            }
        }
    }

    @Override // java.util.concurrent.CompletableFuture
    public AndroidFuture<T> orTimeout(long j, TimeUnit timeUnit) {
        Message messageObtainMessage = PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.infra.-$$Lambda$M-7auAOO_zyxtVlCAn5eFvDKrX0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AndroidFuture) obj).triggerTimeout();
            }
        }, this);
        messageObtainMessage.obj = this;
        this.mTimeoutHandler.sendMessageDelayed(messageObtainMessage, timeUnit.toMillis(j));
        return this;
    }

    void triggerTimeout() {
        cancelTimeout();
        if (isDone()) {
            return;
        }
        completeExceptionally(new TimeoutException());
    }

    public AndroidFuture<T> cancelTimeout() {
        this.mTimeoutHandler.removeCallbacksAndMessages(this);
        return this;
    }

    public AndroidFuture<T> setTimeoutHandler(Handler handler) {
        cancelTimeout();
        this.mTimeoutHandler = (Handler) Preconditions.checkNotNull(handler);
        return this;
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public <U> AndroidFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> function) {
        return thenComposeAsync((Function) function, ConcurrentUtils.DIRECT_EXECUTOR);
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public <U> AndroidFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> function, Executor executor) {
        return new ThenComposeAsync(this, function, executor);
    }

    private static class ThenComposeAsync<T, U> extends AndroidFuture<U> implements BiConsumer<Object, Throwable>, Runnable {
        private final Executor mExecutor;
        private volatile Function<? super T, ? extends CompletionStage<U>> mFn;
        private volatile T mSourceResult = null;

        ThenComposeAsync(AndroidFuture<T> androidFuture, Function<? super T, ? extends CompletionStage<U>> function, Executor executor) {
            this.mFn = (Function) Preconditions.checkNotNull(function);
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            androidFuture.whenComplete((BiConsumer) this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.function.BiConsumer
        public void accept(Object obj, Throwable th) {
            if (th != null) {
                completeExceptionally(th);
            } else if (this.mFn != null) {
                this.mSourceResult = obj;
                this.mExecutor.execute(this);
            } else {
                complete(obj);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                CompletionStage completionStage = (CompletionStage) Preconditions.checkNotNull(this.mFn.apply(this.mSourceResult));
                this.mFn = null;
                completionStage.whenComplete(this);
            } catch (Throwable th) {
                try {
                    completeExceptionally(th);
                } finally {
                    this.mFn = null;
                }
            }
        }
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public <U> AndroidFuture<U> thenApply(Function<? super T, ? extends U> function) {
        return thenApplyAsync((Function) function, ConcurrentUtils.DIRECT_EXECUTOR);
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public <U> AndroidFuture<U> thenApplyAsync(Function<? super T, ? extends U> function, Executor executor) {
        return new ThenApplyAsync(this, function, executor);
    }

    private static class ThenApplyAsync<T, U> extends AndroidFuture<U> implements BiConsumer<T, Throwable>, Runnable {
        private final Executor mExecutor;
        private final Function<? super T, ? extends U> mFn;
        private volatile T mSourceResult = null;

        ThenApplyAsync(AndroidFuture<T> androidFuture, Function<? super T, ? extends U> function, Executor executor) {
            this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
            this.mFn = (Function) Preconditions.checkNotNull(function);
            androidFuture.whenComplete((BiConsumer) this);
        }

        @Override // java.util.function.BiConsumer
        public void accept(T t, Throwable th) {
            if (th != null) {
                completeExceptionally(th);
            } else {
                this.mSourceResult = t;
                this.mExecutor.execute(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                complete(this.mFn.apply(this.mSourceResult));
            } catch (Throwable th) {
                completeExceptionally(th);
            }
        }
    }

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.CompletionStage
    public <U, V> AndroidFuture<V> thenCombine(CompletionStage<? extends U> completionStage, BiFunction<? super T, ? super U, ? extends V> biFunction) {
        return new ThenCombine(this, completionStage, biFunction);
    }

    public AndroidFuture<T> thenCombine(CompletionStage<Void> completionStage) {
        return (AndroidFuture<T>) thenCombine((CompletionStage) completionStage, (BiFunction) new BiFunction() { // from class: com.android.internal.infra.-$$Lambda$AndroidFuture$LOw8vM8drud4PCA603nMN_AlSIA
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return AndroidFuture.lambda$thenCombine$1(obj, (Void) obj2);
            }
        });
    }

    private static class ThenCombine<T, U, V> extends AndroidFuture<V> implements BiConsumer<Object, Throwable> {
        private final BiFunction<? super T, ? super U, ? extends V> mCombineResults;
        private volatile T mResultT = null;
        private volatile CompletionStage<? extends U> mSourceU;

        ThenCombine(CompletableFuture<T> completableFuture, CompletionStage<? extends U> completionStage, BiFunction<? super T, ? super U, ? extends V> biFunction) {
            this.mSourceU = (CompletionStage) Preconditions.checkNotNull(completionStage);
            this.mCombineResults = (BiFunction) Preconditions.checkNotNull(biFunction);
            completableFuture.whenComplete((BiConsumer) this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.function.BiConsumer
        public void accept(Object obj, Throwable th) {
            if (th != null) {
                completeExceptionally(th);
                return;
            }
            if (this.mSourceU != null) {
                this.mResultT = obj;
                this.mSourceU.whenComplete(this);
            } else {
                try {
                    complete(this.mCombineResults.apply(this.mResultT, obj));
                } catch (Throwable th2) {
                    completeExceptionally(th2);
                }
            }
        }
    }

    public static <T> AndroidFuture<T> supply(Supplier<T> supplier) {
        return supplyAsync((Supplier) supplier, ConcurrentUtils.DIRECT_EXECUTOR);
    }

    public static <T> AndroidFuture<T> supplyAsync(Supplier<T> supplier, Executor executor) {
        return new SupplyAsync(supplier, executor);
    }

    private static class SupplyAsync<T> extends AndroidFuture<T> implements Runnable {
        private final Supplier<T> mSupplier;

        SupplyAsync(Supplier<T> supplier, Executor executor) {
            this.mSupplier = supplier;
            executor.execute(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                complete(this.mSupplier.get());
            } catch (Throwable th) {
                completeExceptionally(th);
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        boolean zIsDone = isDone();
        parcel.writeBoolean(zIsDone);
        if (zIsDone) {
            try {
                T t = get();
                parcel.writeBoolean(false);
                parcel.writeValue(t);
                return;
            } catch (Throwable th) {
                parcel.writeBoolean(true);
                parcelException(parcel, this.unwrapExecutionException(th));
                return;
            }
        }
        parcel.writeStrongBinder(new IAndroidFuture.Stub() { // from class: com.android.internal.infra.AndroidFuture.1
            @Override // com.android.internal.infra.IAndroidFuture
            public void complete(AndroidFuture androidFuture) {
                boolean zCompleteExceptionally;
                try {
                    zCompleteExceptionally = AndroidFuture.this.complete(androidFuture.get());
                } catch (Throwable th2) {
                    AndroidFuture androidFuture2 = AndroidFuture.this;
                    zCompleteExceptionally = androidFuture2.completeExceptionally(androidFuture2.unwrapExecutionException(th2));
                }
                if (zCompleteExceptionally) {
                    return;
                }
                Log.w(AndroidFuture.LOG_TAG, "Remote result " + androidFuture + " ignored, as local future is already completed: " + AndroidFuture.this);
            }
        }.asBinder());
    }

    Throwable unwrapExecutionException(Throwable th) {
        return th instanceof ExecutionException ? th.getCause() : th;
    }

    private static void parcelException(Parcel parcel, Throwable th) {
        parcel.writeBoolean(th == null);
        if (th == null) {
            return;
        }
        parcel.writeInt(Parcel.getExceptionCode(th));
        parcel.writeString(th.getClass().getName());
        parcel.writeString(th.getMessage());
        parcel.writeStackTrace(th);
        parcelException(parcel, th.getCause());
    }

    private static Throwable unparcelException(Parcel parcel) {
        if (parcel.readBoolean()) {
            return null;
        }
        int i = parcel.readInt();
        String string = parcel.readString();
        String str = parcel.readString() + "\n" + (parcel.readInt() > 0 ? parcel.readString() : "\t<stack trace unavailable>");
        Exception excCreateExceptionOrNull = parcel.createExceptionOrNull(i, str);
        Exception runtimeException = excCreateExceptionOrNull;
        if (excCreateExceptionOrNull == null) {
            runtimeException = new RuntimeException(string + ": " + str);
        }
        runtimeException.setStackTrace(EMPTY_STACK_TRACE);
        if (unparcelException(parcel) != null) {
            runtimeException.initCause(runtimeException);
        }
        return runtimeException;
    }
}
