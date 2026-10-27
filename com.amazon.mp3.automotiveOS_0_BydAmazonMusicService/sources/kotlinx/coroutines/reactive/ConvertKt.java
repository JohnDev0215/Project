package kotlinx.coroutines.reactive;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.reactivestreams.Publisher;

/* JADX INFO: compiled from: Convert.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a(\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"asPublisher", "Lorg/reactivestreams/Publisher;", "T", "Lkotlinx/coroutines/channels/ReceiveChannel;", "context", "Lkotlin/coroutines/CoroutineContext;", "kotlinx-coroutines-reactive"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ConvertKt {

    /* JADX INFO: renamed from: kotlinx.coroutines.reactive.ConvertKt$asPublisher$1, reason: invalid class name */
    /* JADX INFO: compiled from: Convert.kt */
    @Metadata(bv = {}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u008a@"}, d2 = {"T", "Lkotlinx/coroutines/channels/ProducerScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "kotlinx.coroutines.reactive.ConvertKt$asPublisher$1", f = "Convert.kt", i = {0, 1}, l = {16, 17}, m = "invokeSuspend", n = {"$this$publish", "$this$publish"}, s = {"L$0", "L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ ReceiveChannel $this_asPublisher;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(ReceiveChannel receiveChannel, Continuation continuation) {
            super(2, continuation);
            this.$this_asPublisher = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_asPublisher, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(ProducerScope producerScope, Continuation continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x004a A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x004b  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0068 -> B:12:0x003e). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L30
                if (r1 == r3) goto L23
                if (r1 != r2) goto L1b
                java.lang.Object r1 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
                kotlin.ResultKt.throwOnFailure(r8)
                r8 = r4
                goto L3d
            L1b:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L23:
                java.lang.Object r1 = r7.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r4 = (kotlinx.coroutines.channels.ProducerScope) r4
                kotlin.ResultKt.throwOnFailure(r8)
                r5 = r7
                goto L4f
            L30:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                kotlinx.coroutines.channels.ProducerScope r8 = (kotlinx.coroutines.channels.ProducerScope) r8
                kotlinx.coroutines.channels.ReceiveChannel r1 = r7.$this_asPublisher
                kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
            L3d:
                r4 = r7
            L3e:
                r4.L$0 = r8
                r4.L$1 = r1
                r4.label = r3
                java.lang.Object r5 = r1.hasNext(r4)
                if (r5 != r0) goto L4b
                return r0
            L4b:
                r6 = r4
                r4 = r8
                r8 = r5
                r5 = r6
            L4f:
                java.lang.Boolean r8 = (java.lang.Boolean) r8
                boolean r8 = r8.booleanValue()
                if (r8 == 0) goto L6b
                java.lang.Object r8 = r1.next()
                r5.L$0 = r4
                r5.L$1 = r1
                r5.label = r2
                java.lang.Object r8 = r4.send(r8, r5)
                if (r8 != r0) goto L68
                return r0
            L68:
                r8 = r4
                r4 = r5
                goto L3e
            L6b:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.reactive.ConvertKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in the favour of consumeAsFlow()", replaceWith = @ReplaceWith(expression = "this.consumeAsFlow().asPublisher(context)", imports = {"kotlinx.coroutines.flow.consumeAsFlow"}))
    public static final /* synthetic */ Publisher asPublisher(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return PublishKt.publish(coroutineContext, new AnonymousClass1(receiveChannel, null));
    }

    public static /* synthetic */ Publisher asPublisher$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        return asPublisher(receiveChannel, coroutineContext);
    }
}
