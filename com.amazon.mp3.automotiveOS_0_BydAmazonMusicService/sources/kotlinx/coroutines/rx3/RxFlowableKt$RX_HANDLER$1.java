package kotlinx.coroutines.rx3;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: RxFlowable.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
/* synthetic */ class RxFlowableKt$RX_HANDLER$1 extends FunctionReferenceImpl implements Function2<Throwable, CoroutineContext, Unit> {
    public static final RxFlowableKt$RX_HANDLER$1 INSTANCE = new RxFlowableKt$RX_HANDLER$1();

    RxFlowableKt$RX_HANDLER$1() {
        super(2, RxCancellableKt.class, "handleUndeliverableException", "handleUndeliverableException(Ljava/lang/Throwable;Lkotlin/coroutines/CoroutineContext;)V", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo4invoke(Throwable th, CoroutineContext coroutineContext) {
        invoke2(th, coroutineContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable th, CoroutineContext coroutineContext) {
        RxCancellableKt.handleUndeliverableException(th, coroutineContext);
    }
}
