package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Throwable;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.b.l;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B1\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"kotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$2", "Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/Any;", "result", "invokeSuspend", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Int;", "label", "I", "Lkotlin/Function1;", "p0", "Lkotlin/coroutines/a;", "p1", "Lkotlin/coroutines/CoroutineContext;", "p2", "p3", "p4", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$2 extends ContinuationImpl {
    final /* synthetic */ l $block;
    final /* synthetic */ kotlin.coroutines.a $completion;
    final /* synthetic */ CoroutineContext $context;
    private int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$2(l lVar, kotlin.coroutines.a aVar, CoroutineContext coroutineContext, kotlin.coroutines.a aVar2, CoroutineContext coroutineContext2) {
        super(aVar2, coroutineContext2);
        this.$block = lVar;
        this.$completion = aVar;
        this.$context = coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    protected Object invokeSuspend(@NotNull Object result) throws Throwable {
        int i = this.label;
        if (i == 0) {
            this.label = 1;
            Throwable.b(result);
            return this.$block.invoke(this);
        }
        if (i != 1) {
            throw new IllegalStateException("This coroutine had already completed".toString());
        }
        this.label = 2;
        Throwable.b(result);
        return result;
    }
}
