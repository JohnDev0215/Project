package androidx.paging.multicast;

import androidx.core.app.NotificationCompat;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: StoreRealActor.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b \u0018\u0000 \u0015*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\u0015B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0011\u0010\r\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\bH\u0002J\u0019\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\b\u0010\u0013\u001a\u00020\bH\u0016J\u0019\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00028\u0000H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Landroidx/paging/multicast/StoreRealActor;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/CoroutineScope;)V", "closeCompleted", "Lkotlinx/coroutines/CompletableDeferred;", "", "didClose", "Ljava/util/concurrent/atomic/AtomicBoolean;", "inboundChannel", "Lkotlinx/coroutines/channels/Channel;", "close", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doClose", "handle", NotificationCompat.CATEGORY_MESSAGE, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onClosed", "send", "Companion", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
public abstract class StoreRealActor<T> {
    private final CompletableDeferred<Unit> closeCompleted;
    private final AtomicBoolean didClose;
    private final Channel<Object> inboundChannel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object CLOSE_TOKEN = new Object();

    /* JADX INFO: renamed from: androidx.paging.multicast.StoreRealActor$close$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StoreRealActor.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor", f = "StoreRealActor.kt", i = {0}, l = {74, 76}, m = "close", n = {"this"}, s = {"L$0"})
    static final class C02571 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02571(StoreRealActor<T> storeRealActor, Continuation<? super C02571> continuation) {
            super(continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.close(this);
        }
    }

    public abstract Object handle(T t, Continuation<? super Unit> continuation);

    public void onClosed() {
    }

    public StoreRealActor(CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Channel<Object> channelChannel$default = ChannelKt.Channel$default(0, null, null, 6, null);
        this.inboundChannel = channelChannel$default;
        this.closeCompleted = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        this.didClose = new AtomicBoolean(false);
        FlowKt.launchIn(FlowKt.onCompletion(FlowKt.onEach(FlowKt.consumeAsFlow(channelChannel$default), new AnonymousClass1(this, null)), new AnonymousClass2(this, null)), scope);
    }

    /* JADX INFO: renamed from: androidx.paging.multicast.StoreRealActor$1, reason: invalid class name */
    /* JADX INFO: compiled from: StoreRealActor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"T", "", NotificationCompat.CATEGORY_MESSAGE, "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor$1", f = "StoreRealActor.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(StoreRealActor<T> storeRealActor, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(Object obj, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to androidx.paging.multicast.StoreRealActor$1 for r3v1 'this'  kotlin.coroutines.Continuation
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r3.label
                r2 = 1
                if (r1 == 0) goto L17
                if (r1 != r2) goto Lf
                kotlin.ResultKt.throwOnFailure(r4)
                goto L35
            Lf:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            L17:
                kotlin.ResultKt.throwOnFailure(r4)
                java.lang.Object r4 = r3.L$0
                androidx.paging.multicast.StoreRealActor$Companion r1 = androidx.paging.multicast.StoreRealActor.INSTANCE
                java.lang.Object r1 = r1.getCLOSE_TOKEN()
                if (r4 != r1) goto L2a
                androidx.paging.multicast.StoreRealActor<T> r4 = r3.this$0
                androidx.paging.multicast.StoreRealActor.access$doClose(r4)
                goto L35
            L2a:
                androidx.paging.multicast.StoreRealActor<T> r1 = r3.this$0
                r3.label = r2
                java.lang.Object r4 = r1.handle(r4, r3)
                if (r4 != r0) goto L35
                return r0
            L35:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.StoreRealActor.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.paging.multicast.StoreRealActor$2, reason: invalid class name */
    /* JADX INFO: compiled from: StoreRealActor.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "", "", "it", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.multicast.StoreRealActor$2", f = "StoreRealActor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super Object>, Throwable, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ StoreRealActor<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(StoreRealActor<T> storeRealActor, Continuation<? super AnonymousClass2> continuation) {
            super(3, continuation);
            this.this$0 = storeRealActor;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Object> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<Object>) flowCollector, th, continuation);
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<Object> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return new AnonymousClass2(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.this$0.doClose();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doClose() {
        if (this.didClose.compareAndSet(false, true)) {
            try {
                onClosed();
            } finally {
                SendChannel.DefaultImpls.close$default(this.inboundChannel, null, 1, null);
                this.closeCompleted.complete(Unit.INSTANCE);
            }
        }
    }

    public final Object send(T t, Continuation<? super Unit> continuation) {
        Object objSend = this.inboundChannel.send(t, continuation);
        return objSend == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSend : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object close(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.multicast.StoreRealActor.C02571
            if (r0 == 0) goto L13
            r0 = r6
            androidx.paging.multicast.StoreRealActor$close$1 r0 = (androidx.paging.multicast.StoreRealActor.C02571) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.paging.multicast.StoreRealActor$close$1 r0 = new androidx.paging.multicast.StoreRealActor$close$1
            r0.<init>(r5, r6)
        L18:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3c
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            kotlin.ResultKt.throwOnFailure(r6)
            goto L5d
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L34:
            java.lang.Object r2 = r0.L$0
            androidx.paging.multicast.StoreRealActor r2 = (androidx.paging.multicast.StoreRealActor) r2
            kotlin.ResultKt.throwOnFailure(r6)
            goto L4f
        L3c:
            kotlin.ResultKt.throwOnFailure(r6)
            kotlinx.coroutines.channels.Channel<java.lang.Object> r6 = r5.inboundChannel
            java.lang.Object r2 = androidx.paging.multicast.StoreRealActor.CLOSE_TOKEN
            r0.L$0 = r5
            r0.label = r4
            java.lang.Object r6 = r6.send(r2, r0)
            if (r6 != r1) goto L4e
            return r1
        L4e:
            r2 = r5
        L4f:
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit> r6 = r2.closeCompleted
            r2 = 0
            r0.L$0 = r2
            r0.label = r3
            java.lang.Object r6 = r6.await(r0)
            if (r6 != r1) goto L5d
            return r1
        L5d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.multicast.StoreRealActor.close(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: compiled from: StoreRealActor.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/paging/multicast/StoreRealActor$Companion;", "", "()V", "CLOSE_TOKEN", "getCLOSE_TOKEN", "()Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getCLOSE_TOKEN() {
            return StoreRealActor.CLOSE_TOKEN;
        }
    }
}
