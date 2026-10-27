package com.apollographql.apollo3.internal;

import com.google.firebase.perf.plugin.util.AsmString;
import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExperimentalCoroutinesApi;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;

/* JADX INFO: compiled from: ChannelWrapper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0004\b<\u0010=J\t\u0010\u0004\u001a\u00020\u0003H\u0097\u0001J\u0013\u0010\u0004\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0097\u0001J\u0019\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0006\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tH\u0096\u0001J.\u0010\u000e\u001a\u00020\u00032#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\nH\u0097\u0001J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0003J\u0018\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00028\u0000H\u0097\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00018\u0000H\u0097\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0016\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096Aø\u0001\u0000ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0019\u0010\u0017J\u0015\u0010\u001b\u001a\u0004\u0018\u00018\u0000H\u0097Aø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0017J\u001b\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00028\u0000H\u0096Aø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0096\u0001ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001e\u0010\u0015J'\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\u0006\u0010\u0011\u001a\u00028\u0000H\u0096\u0001ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b \u0010!J+\u0010#\u001a\u00020\u00032#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00030\nJ\u0012\u0010$\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016R\u001a\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R5\u0010\r\u001a!\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010'R$\u0010)\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u00078\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b)\u0010+R\u0014\u0010,\u001a\u00020\u00078\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\u00078\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00078\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b.\u0010+R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000/8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b0\u00101R#\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00180/8\u0016X\u0096\u0005ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b3\u00101R\u001c\u00106\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000/8VX\u0097\u0005¢\u0006\u0006\u001a\u0004\b5\u00101R&\u0010;\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u000008078\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b9\u0010:\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006>"}, d2 = {"Lcom/apollographql/apollo3/internal/ChannelWrapper;", "E", "Lkotlinx/coroutines/channels/Channel;", "", "cancel", "", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "handler", "invokeOnClose", "Lkotlinx/coroutines/channels/ChannelIterator;", "iterator", "element", "offer", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ChannelResult;", "receiveCatching-JP2dKIU", "receiveCatching", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryReceive-PtdJZtk", "tryReceive", "trySend-JP2dKIU", AsmString.METHOD_DESC_FIREBASE_PERF_URL_CONNECTION_INSTRUMENT_WITH_OBJECT, "trySend", "setInvokeOnClose", "close", "wrapped", "Lkotlinx/coroutines/channels/Channel;", "Lkotlin/jvm/functions/Function1;", "<set-?>", "isClosed", "Z", "()Z", "isClosedForReceive", "isClosedForSend", "isEmpty", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceive", "getOnReceiveCatching", "onReceiveCatching", "getOnReceiveOrNull", "onReceiveOrNull", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "onSend", "<init>", "(Lkotlinx/coroutines/channels/Channel;)V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class ChannelWrapper<E> implements Channel<E> {
    private Function1<? super Throwable, Unit> handler;
    private boolean isClosed;
    private final Channel<E> wrapped;

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ void cancel() {
        this.wrapped.cancel();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public void cancel(CancellationException cause) {
        this.wrapped.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public /* synthetic */ boolean cancel(Throwable cause) {
        return this.wrapped.cancel(cause);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceive() {
        return this.wrapped.getOnReceive();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<ChannelResult<E>> getOnReceiveCatching() {
        return this.wrapped.getOnReceiveCatching();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1<E> getOnReceiveOrNull() {
        return this.wrapped.getOnReceiveOrNull();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public SelectClause2<E, SendChannel<E>> getOnSend() {
        return this.wrapped.getOnSend();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @ExperimentalCoroutinesApi
    /* JADX INFO: renamed from: invokeOnClose */
    public void mo4341invokeOnClose(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.wrapped.mo4341invokeOnClose(handler);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this.wrapped.isClosedForReceive();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this.wrapped.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this.wrapped.isEmpty();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator<E> iterator() {
        return this.wrapped.iterator();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of 'trySend' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(E element) {
        return this.wrapped.offer(element);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in the favour of 'tryReceive'. Please note that the provided replacement does not rethrow channel's close cause as 'poll' did, for the precise replacement please refer to the 'poll' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public E poll() {
        return this.wrapped.poll();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation<? super E> continuation) {
        return this.wrapped.receive(continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: receiveCatching-JP2dKIU, reason: not valid java name */
    public Object mo2617receiveCatchingJP2dKIU(Continuation<? super ChannelResult<? extends E>> continuation) {
        Object objMo2617receiveCatchingJP2dKIU = this.wrapped.mo2617receiveCatchingJP2dKIU(continuation);
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return objMo2617receiveCatchingJP2dKIU;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of 'receiveCatching'. Please note that the provided replacement does not rethrow channel's close cause as 'receiveOrNull' did, for the detailed replacement please refer to the 'receiveOrNull' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    @LowPriorityInOverloadResolution
    public Object receiveOrNull(Continuation<? super E> continuation) {
        return this.wrapped.receiveOrNull(continuation);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public Object send(E e, Continuation<? super Unit> continuation) {
        return this.wrapped.send(e, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* JADX INFO: renamed from: tryReceive-PtdJZtk, reason: not valid java name */
    public Object mo2618tryReceivePtdJZtk() {
        return this.wrapped.mo2618tryReceivePtdJZtk();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: trySend-JP2dKIU */
    public Object mo55trySendJP2dKIU(E element) {
        return this.wrapped.mo55trySendJP2dKIU(element);
    }

    public ChannelWrapper(Channel<E> wrapped) {
        Intrinsics.checkNotNullParameter(wrapped, "wrapped");
        this.wrapped = wrapped;
    }

    public final void setInvokeOnClose(Function1<? super Throwable, Unit> handler) {
        Intrinsics.checkNotNullParameter(handler, "handler");
        this.handler = handler;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* JADX INFO: renamed from: close */
    public boolean cancel(Throwable cause) {
        Function1<? super Throwable, Unit> function1;
        this.isClosed = true;
        boolean zCancel = this.wrapped.cancel(cause);
        if (zCancel && (function1 = this.handler) != null) {
            function1.invoke(cause);
        }
        this.handler = null;
        return zCancel;
    }
}
