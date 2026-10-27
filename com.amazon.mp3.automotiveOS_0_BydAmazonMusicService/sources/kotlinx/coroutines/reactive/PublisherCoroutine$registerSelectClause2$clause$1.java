package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: Publish.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000\"\u0006\b\u0001\u0010\u0001 \u0000H\u008a@"}, d2 = {"R", "T", "<anonymous>"}, k = 3, mv = {1, 6, 0})
@DebugMetadata(c = "kotlinx.coroutines.reactive.PublisherCoroutine$registerSelectClause2$clause$1", f = "Publish.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {})
final class PublisherCoroutine$registerSelectClause2$clause$1<R> extends SuspendLambda implements Function1<Continuation<? super R>, Object> {
    final /* synthetic */ Function2<SendChannel<? super T>, Continuation<? super R>, Object> $block;
    final /* synthetic */ T $element;
    int label;
    final /* synthetic */ PublisherCoroutine<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PublisherCoroutine$registerSelectClause2$clause$1(PublisherCoroutine<? super T> publisherCoroutine, T t, Function2<? super SendChannel<? super T>, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super PublisherCoroutine$registerSelectClause2$clause$1> continuation) {
        super(1, continuation);
        this.this$0 = publisherCoroutine;
        this.$element = t;
        this.$block = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new PublisherCoroutine$registerSelectClause2$clause$1(this.this$0, this.$element, this.$block, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super R> continuation) {
        return ((PublisherCoroutine$registerSelectClause2$clause$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Throwable thDoLockedNext = this.this$0.doLockedNext(this.$element);
            if (thDoLockedNext != null) {
                throw thDoLockedNext;
            }
            Function2<SendChannel<? super T>, Continuation<? super R>, Object> function2 = this.$block;
            Continuation continuation = this.this$0;
            this.label = 1;
            obj = function2.mo4invoke(continuation, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
