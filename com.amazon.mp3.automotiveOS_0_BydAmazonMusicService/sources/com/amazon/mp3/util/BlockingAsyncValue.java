package com.amazon.mp3.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BlockingAsyncValue.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB\u001f\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\rR\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/util/BlockingAsyncValue;", "T", "Lcom/amazon/mp3/util/Value;", "executorService", "Ljava/util/concurrent/ExecutorService;", "getter", "Lkotlin/Function0;", "(Ljava/util/concurrent/ExecutorService;Lkotlin/jvm/functions/Function0;)V", "future", "Ljava/util/concurrent/Future;", "value", "Ljava/lang/Object;", "get", "()Ljava/lang/Object;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BlockingAsyncValue<T> implements Value<T> {
    private static final ExecutorService backgroundExecutor = Executors.newSingleThreadExecutor();
    private Future<T> future;
    private T value;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public BlockingAsyncValue(Function0<? extends T> getter) {
        this(null, getter, 1, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(getter, "getter");
    }

    @JvmOverloads
    public BlockingAsyncValue(ExecutorService executorService, final Function0<? extends T> getter) {
        Intrinsics.checkNotNullParameter(executorService, "executorService");
        Intrinsics.checkNotNullParameter(getter, "getter");
        this.future = executorService.submit(new Callable() { // from class: com.amazon.mp3.util.BlockingAsyncValue$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BlockingAsyncValue.m1649future$lambda1(getter, this);
            }
        });
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ BlockingAsyncValue(ExecutorService backgroundExecutor2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            backgroundExecutor2 = backgroundExecutor;
            Intrinsics.checkNotNullExpressionValue(backgroundExecutor2, "backgroundExecutor");
        }
        this(backgroundExecutor2, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: future$lambda-1, reason: not valid java name */
    public static final Object m1649future$lambda1(Function0 getter, BlockingAsyncValue this$0) {
        Intrinsics.checkNotNullParameter(getter, "$getter");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        T t = (T) getter.invoke();
        if (t == null) {
            return null;
        }
        this$0.value = t;
        return t;
    }

    @Override // com.amazon.mp3.util.Value
    public T get() {
        T t = this.value;
        if (t != null) {
            return t;
        }
        try {
            Future<T> future = this.future;
            if (future == null) {
                return null;
            }
            return future.get();
        } catch (Exception unused) {
            return null;
        }
    }
}
