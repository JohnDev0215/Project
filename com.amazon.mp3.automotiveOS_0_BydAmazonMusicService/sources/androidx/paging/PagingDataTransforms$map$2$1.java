package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: PagingDataTransforms.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0012\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0000*\u00020\u0003H\u008a@"}, d2 = {"", "T", "R", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/paging/PageEvent;", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$2$1", f = "PagingDataTransforms.kt", i = {}, l = {57}, m = "invokeSuspend", n = {}, s = {})
final class PagingDataTransforms$map$2$1<R> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PageEvent<R>>, Object> {
    final /* synthetic */ PageEvent<T> $event;
    final /* synthetic */ Function1<T, R> $transform;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    PagingDataTransforms$map$2$1(PageEvent<T> pageEvent, Function1<? super T, ? extends R> function1, Continuation<? super PagingDataTransforms$map$2$1> continuation) {
        super(2, continuation);
        this.$event = pageEvent;
        this.$transform = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PagingDataTransforms$map$2$1(this.$event, this.$transform, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super PageEvent<R>> continuation) {
        return ((PagingDataTransforms$map$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.paging.PagingDataTransforms$map$2$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: PagingDataTransforms.kt */
    @Metadata(bv = {}, d1 = {"\u0000\b\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0004\u001a\u00028\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0002*\u00020\u00002\u0006\u0010\u0003\u001a\u00028\u0000H\u008a@"}, d2 = {"", "T", "R", "it", "<anonymous>"}, k = 3, mv = {1, 5, 1})
    @DebugMetadata(c = "androidx.paging.PagingDataTransforms$map$2$1$1", f = "PagingDataTransforms.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<T> extends SuspendLambda implements Function2<T, Continuation<? super R>, Object> {
        final /* synthetic */ Function1<T, R> $transform;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super T, ? extends R> function1, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$transform = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$transform, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Object mo4invoke(T t, Continuation<? super R> continuation) {
            return ((AnonymousClass1) create(t, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.$transform.invoke((T) this.L$0);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type kotlin.coroutines.Continuation to androidx.paging.PagingDataTransforms$map$2$1<R> for r5v1 'this'  kotlin.coroutines.Continuation
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            kotlin.ResultKt.throwOnFailure(r6)
            goto L2d
        Lf:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L17:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.PageEvent<T> r6 = r5.$event
            androidx.paging.PagingDataTransforms$map$2$1$1 r1 = new androidx.paging.PagingDataTransforms$map$2$1$1
            kotlin.jvm.functions.Function1<T, R> r3 = r5.$transform
            r4 = 0
            r1.<init>(r3, r4)
            r5.label = r2
            java.lang.Object r6 = r6.map(r1, r5)
            if (r6 != r0) goto L2d
            return r0
        L2d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataTransforms$map$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
