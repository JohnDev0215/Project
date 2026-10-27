package kotlin.coroutines.intrinsics;

import kotlin.Metadata;
import kotlin.Throwable;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.jvm.b.l;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0014¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0007\u001a\u00020\u00068\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"kotlin/coroutines/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1", "Lkotlin/coroutines/jvm/internal/RestrictedContinuationImpl;", "Lkotlin/Any;", "result", "invokeSuspend", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlin/Int;", "label", "I", "Lkotlin/Function1;", "p0", "Lkotlin/coroutines/a;", "p1", "p2", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1 extends RestrictedContinuationImpl {
    final /* synthetic */ l $block;
    final /* synthetic */ kotlin.coroutines.a $completion;
    private int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IntrinsicsKt__IntrinsicsJvmKt$createCoroutineFromSuspendFunction$1(l lVar, kotlin.coroutines.a aVar, kotlin.coroutines.a aVar2) {
        super(aVar2);
        this.$block = lVar;
        this.$completion = aVar;
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
