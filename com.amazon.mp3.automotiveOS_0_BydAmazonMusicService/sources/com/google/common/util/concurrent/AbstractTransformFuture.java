package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.FluentFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractTransformFuture<I, O, F, T> extends FluentFuture.TrustedFuture<O> implements Runnable {

    @CheckForNull
    F function;

    @CheckForNull
    ListenableFuture<? extends I> inputFuture;

    abstract T doTransform(F f, I i) throws Exception;

    abstract void setResult(T t);

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        TransformFuture transformFuture = new TransformFuture(listenableFuture, function);
        listenableFuture.addListener(transformFuture, MoreExecutors.rejectionPropagatingExecutor(executor, transformFuture));
        return transformFuture;
    }

    AbstractTransformFuture(ListenableFuture<? extends I> listenableFuture, F f) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        this.function = (F) Preconditions.checkNotNull(f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.Runnable
    public final void run() {
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        if ((isCancelled() | (listenableFuture == null)) || (f == null)) {
            return;
        }
        this.inputFuture = null;
        if (listenableFuture.isCancelled()) {
            setFuture(listenableFuture);
            return;
        }
        try {
            try {
                Object objDoTransform = doTransform(f, Futures.getDone(listenableFuture));
                this.function = null;
                setResult(objDoTransform);
            } catch (Throwable th) {
                try {
                    setException(th);
                } finally {
                    this.function = null;
                }
            }
        } catch (Error e) {
            setException(e);
        } catch (CancellationException unused) {
            cancel(false);
        } catch (RuntimeException e2) {
            setException(e2);
        } catch (ExecutionException e3) {
            setException(e3.getCause());
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    @CheckForNull
    protected String pendingToString() {
        String string;
        ListenableFuture<? extends I> listenableFuture = this.inputFuture;
        F f = this.function;
        String strPendingToString = super.pendingToString();
        if (listenableFuture != null) {
            String strValueOf = String.valueOf(listenableFuture);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 16);
            sb.append("inputFuture=[");
            sb.append(strValueOf);
            sb.append("], ");
            string = sb.toString();
        } else {
            string = "";
        }
        if (f == null) {
            if (strPendingToString == null) {
                return null;
            }
            String strValueOf2 = String.valueOf(string);
            return strPendingToString.length() != 0 ? strValueOf2.concat(strPendingToString) : new String(strValueOf2);
        }
        String strValueOf3 = String.valueOf(f);
        StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 11 + strValueOf3.length());
        sb2.append(string);
        sb2.append("function=[");
        sb2.append(strValueOf3);
        sb2.append("]");
        return sb2.toString();
    }

    private static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        TransformFuture(ListenableFuture<? extends I> listenableFuture, Function<? super I, ? extends O> function) {
            super(listenableFuture, function);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public O doTransform(Function<? super I, ? extends O> function, I i) {
            return function.apply(i);
        }

        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        void setResult(O o) {
            set(o);
        }
    }
}
