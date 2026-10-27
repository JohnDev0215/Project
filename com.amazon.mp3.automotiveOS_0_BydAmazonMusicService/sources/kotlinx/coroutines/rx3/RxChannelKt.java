package kotlinx.coroutines.rx3;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.ObservableSource;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ReceiveChannel;

/* JADX INFO: compiled from: RxChannel.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0001\u001a\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"collect", "", "T", "Lio/reactivex/rxjava3/core/MaybeSource;", "action", "Lkotlin/Function1;", "(Lio/reactivex/rxjava3/core/MaybeSource;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/reactivex/rxjava3/core/ObservableSource;", "(Lio/reactivex/rxjava3/core/ObservableSource;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "kotlinx-coroutines-rx3"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class RxChannelKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxChannelKt$collect$1, reason: invalid class name */
    /* JADX INFO: compiled from: RxChannel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxChannelKt", f = "RxChannel.kt", i = {0, 0}, l = {101}, m = "collect", n = {"action", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RxChannelKt.collect((MaybeSource) null, (Function1) null, this);
        }
    }

    /* JADX INFO: renamed from: kotlinx.coroutines.rx3.RxChannelKt$collect$2, reason: invalid class name */
    /* JADX INFO: compiled from: RxChannel.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 176)
    @DebugMetadata(c = "kotlinx.coroutines.rx3.RxChannelKt", f = "RxChannel.kt", i = {0, 0}, l = {101}, m = "collect", n = {"action", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass2<T> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RxChannelKt.collect((ObservableSource) null, (Function1) null, this);
        }
    }

    @PublishedApi
    public static final <T> ReceiveChannel<T> openSubscription(MaybeSource<T> maybeSource) {
        SubscriptionChannel subscriptionChannel = new SubscriptionChannel();
        maybeSource.subscribe(subscriptionChannel);
        return subscriptionChannel;
    }

    @PublishedApi
    public static final <T> ReceiveChannel<T> openSubscription(ObservableSource<T> observableSource) {
        SubscriptionChannel subscriptionChannel = new SubscriptionChannel();
        observableSource.subscribe(subscriptionChannel);
        return subscriptionChannel;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: all -> 0x0085, TRY_LEAVE, TryCatch #1 {all -> 0x0085, blocks: (B:27:0x0066, B:29:0x006e), top: B:45:0x0066 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0061 -> B:14:0x0037). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object collect(io.reactivex.rxjava3.core.MaybeSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx3.RxChannelKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.rx3.RxChannelKt$collect$1 r0 = (kotlinx.coroutines.rx3.RxChannelKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.rx3.RxChannelKt$collect$1 r0 = new kotlinx.coroutines.rx3.RxChannelKt$collect$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L3a
            r5 = r0
            r0 = r7
            r7 = r2
        L37:
            r2 = r1
            r1 = r5
            goto L65
        L3a:
            r6 = move-exception
            goto L8f
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r6 = openSubscription(r6)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L8b
            r5 = r8
            r8 = r6
            r6 = r5
        L52:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L88
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L88
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L88
            r0.label = r3     // Catch: java.lang.Throwable -> L88
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L88
            if (r2 != r1) goto L61
            return r1
        L61:
            r5 = r0
            r0 = r8
            r8 = r2
            goto L37
        L65:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L85
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L85
            if (r8 == 0) goto L79
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L85
            r7.invoke(r8)     // Catch: java.lang.Throwable -> L85
            r8 = r0
            r0 = r1
            r1 = r2
            goto L52
        L79:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L85:
            r6 = move-exception
            r7 = r0
            goto L8f
        L88:
            r6 = move-exception
            r7 = r8
            goto L8f
        L8b:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L8f:
            throw r6     // Catch: java.lang.Throwable -> L90
        L90:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.RxChannelKt.collect(io.reactivex.rxjava3.core.MaybeSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final <T> Object collect$$forInline(MaybeSource<T> maybeSource, Function1<? super T, Unit> function1, Continuation<? super Unit> continuation) {
        ReceiveChannel receiveChannelOpenSubscription = openSubscription(maybeSource);
        try {
            ChannelIterator it = receiveChannelOpenSubscription.iterator();
            while (true) {
                InlineMarker.mark(3);
                InlineMarker.mark(0);
                Object objHasNext = it.hasNext(null);
                InlineMarker.mark(1);
                if (!((Boolean) objHasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannelOpenSubscription, null);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
                function1.invoke((Object) it.next());
            }
        } finally {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006e A[Catch: all -> 0x0085, TRY_LEAVE, TryCatch #1 {all -> 0x0085, blocks: (B:27:0x0066, B:29:0x006e), top: B:45:0x0066 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0061 -> B:14:0x0037). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Object collect(io.reactivex.rxjava3.core.ObservableSource<T> r6, kotlin.jvm.functions.Function1<? super T, kotlin.Unit> r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof kotlinx.coroutines.rx3.RxChannelKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r8
            kotlinx.coroutines.rx3.RxChannelKt$collect$2 r0 = (kotlinx.coroutines.rx3.RxChannelKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.rx3.RxChannelKt$collect$2 r0 = new kotlinx.coroutines.rx3.RxChannelKt$collect$2
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.channels.ChannelIterator r6 = (kotlinx.coroutines.channels.ChannelIterator) r6
            java.lang.Object r7 = r0.L$1
            kotlinx.coroutines.channels.ReceiveChannel r7 = (kotlinx.coroutines.channels.ReceiveChannel) r7
            java.lang.Object r2 = r0.L$0
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L3a
            r5 = r0
            r0 = r7
            r7 = r2
        L37:
            r2 = r1
            r1 = r5
            goto L65
        L3a:
            r6 = move-exception
            goto L8f
        L3c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L44:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.channels.ReceiveChannel r6 = openSubscription(r6)
            kotlinx.coroutines.channels.ChannelIterator r8 = r6.iterator()     // Catch: java.lang.Throwable -> L8b
            r5 = r8
            r8 = r6
            r6 = r5
        L52:
            r0.L$0 = r7     // Catch: java.lang.Throwable -> L88
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L88
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L88
            r0.label = r3     // Catch: java.lang.Throwable -> L88
            java.lang.Object r2 = r6.hasNext(r0)     // Catch: java.lang.Throwable -> L88
            if (r2 != r1) goto L61
            return r1
        L61:
            r5 = r0
            r0 = r8
            r8 = r2
            goto L37
        L65:
            r4 = 0
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: java.lang.Throwable -> L85
            boolean r8 = r8.booleanValue()     // Catch: java.lang.Throwable -> L85
            if (r8 == 0) goto L79
            java.lang.Object r8 = r6.next()     // Catch: java.lang.Throwable -> L85
            r7.invoke(r8)     // Catch: java.lang.Throwable -> L85
            r8 = r0
            r0 = r1
            r1 = r2
            goto L52
        L79:
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r0, r4)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L85:
            r6 = move-exception
            r7 = r0
            goto L8f
        L88:
            r6 = move-exception
            r7 = r8
            goto L8f
        L8b:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L8f:
            throw r6     // Catch: java.lang.Throwable -> L90
        L90:
            r8 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r7, r6)
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.rx3.RxChannelKt.collect(io.reactivex.rxjava3.core.ObservableSource, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final <T> Object collect$$forInline(ObservableSource<T> observableSource, Function1<? super T, Unit> function1, Continuation<? super Unit> continuation) {
        ReceiveChannel receiveChannelOpenSubscription = openSubscription(observableSource);
        try {
            ChannelIterator it = receiveChannelOpenSubscription.iterator();
            while (true) {
                InlineMarker.mark(3);
                InlineMarker.mark(0);
                Object objHasNext = it.hasNext(null);
                InlineMarker.mark(1);
                if (!((Boolean) objHasNext).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    ChannelsKt.cancelConsumed(receiveChannelOpenSubscription, null);
                    InlineMarker.finallyEnd(1);
                    return Unit.INSTANCE;
                }
                function1.invoke((Object) it.next());
            }
        } finally {
        }
    }
}
