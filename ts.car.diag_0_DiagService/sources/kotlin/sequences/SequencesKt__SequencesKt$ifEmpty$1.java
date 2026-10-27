package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Throwable;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.b.p;
import kotlin.q;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "T", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@DebugMetadata(c = "kotlin.sequences.SequencesKt__SequencesKt$ifEmpty$1", f = "Sequences.kt", i = {0, 0, 1, 1}, l = {67, 69}, m = "invokeSuspend", n = {"$this$sequence", "iterator", "$this$sequence", "iterator"}, s = {"L$0", "L$1", "L$0", "L$1"})
final class SequencesKt__SequencesKt$ifEmpty$1<T> extends RestrictedSuspendLambda implements p<c<? super T>, kotlin.coroutines.a<? super q>, Object> {
    final /* synthetic */ kotlin.jvm.b.a $defaultValue;
    final /* synthetic */ b $this_ifEmpty;
    Object L$0;
    Object L$1;
    int label;
    private c p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt__SequencesKt$ifEmpty$1(b bVar, kotlin.jvm.b.a aVar, kotlin.coroutines.a aVar2) {
        super(2, aVar2);
        this.$this_ifEmpty = bVar;
        this.$defaultValue = aVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final kotlin.coroutines.a<q> create(@Nullable Object obj, @NotNull kotlin.coroutines.a<?> aVar) {
        kotlin.jvm.internal.q.c(aVar, "completion");
        SequencesKt__SequencesKt$ifEmpty$1 sequencesKt__SequencesKt$ifEmpty$1 = new SequencesKt__SequencesKt$ifEmpty$1(this.$this_ifEmpty, this.$defaultValue, aVar);
        sequencesKt__SequencesKt$ifEmpty$1.p$ = (c) obj;
        return sequencesKt__SequencesKt$ifEmpty$1;
    }

    @Override // kotlin.jvm.b.p
    public final Object invoke(Object obj, kotlin.coroutines.a<? super q> aVar) {
        return ((SequencesKt__SequencesKt$ifEmpty$1) create(obj, aVar)).invokeSuspend(q.f1526a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) throws Throwable {
        Object objA = kotlin.coroutines.intrinsics.c.a();
        int i = this.label;
        if (i == 0) {
            Throwable.b(obj);
            c cVar = this.p$;
            Iterator<? extends T> it = this.$this_ifEmpty.iterator();
            if (it.hasNext()) {
                this.L$0 = cVar;
                this.L$1 = it;
                this.label = 1;
                if (cVar.b(it, this) == objA) {
                    return objA;
                }
            } else {
                b<? extends T> bVar = (b) this.$defaultValue.invoke();
                this.L$0 = cVar;
                this.L$1 = it;
                this.label = 2;
                if (cVar.c(bVar, this) == objA) {
                    return objA;
                }
            }
        } else {
            if (i != 1 && i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Throwable.b(obj);
        }
        return q.f1526a;
    }
}
