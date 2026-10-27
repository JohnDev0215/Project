package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

/* JADX INFO: compiled from: ViewGroup.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Landroid/view/View;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "androidx.core.view.ViewGroupKt$descendants$1", f = "ViewGroup.kt", i = {0, 0, 0, 0, 1, 1, 1, 1}, l = {97, 99}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "index$iv", "child", "$this$sequence", "$this$forEach$iv", "index$iv", "child"}, s = {"L$0", "L$1", "I$0", "L$2", "L$0", "L$1", "I$0", "L$2"})
final class ViewGroupKt$descendants$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super View>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ViewGroup $this_descendants;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private SequenceScope p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ViewGroupKt$descendants$1(ViewGroup viewGroup, Continuation continuation) {
        super(2, continuation);
        this.$this_descendants = viewGroup;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1 = new ViewGroupKt$descendants$1(this.$this_descendants, completion);
        viewGroupKt$descendants$1.p$ = (SequenceScope) obj;
        return viewGroupKt$descendants$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super View> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ViewGroupKt$descendants$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00bd  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x00b0 -> B:22:0x00b3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x00b7 -> B:24:0x00ba). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L5d
            r5 = 0
            if (r1 == r3) goto L3b
            if (r1 != r2) goto L33
            r1 = r5
            r6 = r4
            r7 = r4
            r8 = r5
            java.lang.Object r9 = r13.L$2
            r5 = r9
            android.view.View r5 = (android.view.View) r5
            int r9 = r13.I$1
            int r4 = r13.I$0
            java.lang.Object r10 = r13.L$1
            r8 = r10
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            java.lang.Object r10 = r13.L$0
            r1 = r10
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r9
            r9 = r5
            r5 = r4
            r4 = r1
            r1 = r0
            r0 = r14
            r14 = r13
            goto Lb3
        L33:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3b:
            r1 = r5
            r6 = r4
            r7 = r4
            r8 = r5
            java.lang.Object r9 = r13.L$2
            r5 = r9
            android.view.View r5 = (android.view.View) r5
            int r9 = r13.I$1
            int r4 = r13.I$0
            java.lang.Object r10 = r13.L$1
            r8 = r10
            android.view.ViewGroup r8 = (android.view.ViewGroup) r8
            java.lang.Object r10 = r13.L$0
            r1 = r10
            kotlin.sequences.SequenceScope r1 = (kotlin.sequences.SequenceScope) r1
            kotlin.ResultKt.throwOnFailure(r14)
            r10 = r9
            r9 = r5
            r5 = r4
            r4 = r1
            r1 = r0
            r0 = r14
            r14 = r13
            goto L95
        L5d:
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.sequences.SequenceScope r1 = r13.p$
            android.view.ViewGroup r5 = r13.$this_descendants
            r6 = 0
            int r7 = r5.getChildCount()
            r8 = r7
            r7 = r6
            r6 = r5
            r5 = r4
            r4 = r1
            r1 = r0
            r0 = r14
            r14 = r13
        L71:
            if (r5 >= r8) goto Lbd
            android.view.View r9 = r6.getChildAt(r5)
            java.lang.String r10 = "getChildAt(index)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r10)
            r10 = 0
            r14.L$0 = r4
            r14.L$1 = r6
            r14.I$0 = r5
            r14.I$1 = r8
            r14.L$2 = r9
            r14.label = r3
            java.lang.Object r11 = r4.yield(r9, r14)
            if (r11 != r1) goto L90
            return r1
        L90:
            r12 = r8
            r8 = r6
            r6 = r7
            r7 = r10
            r10 = r12
        L95:
            boolean r11 = r9 instanceof android.view.ViewGroup
            if (r11 == 0) goto Lb7
            r11 = r9
            android.view.ViewGroup r11 = (android.view.ViewGroup) r11
            kotlin.sequences.Sequence r11 = androidx.core.view.ViewGroupKt.getDescendants(r11)
            r14.L$0 = r4
            r14.L$1 = r8
            r14.I$0 = r5
            r14.I$1 = r10
            r14.L$2 = r9
            r14.label = r2
            java.lang.Object r11 = r4.yieldAll(r11, r14)
            if (r11 != r1) goto Lb3
            return r1
        Lb3:
            r7 = r6
            r6 = r8
            r8 = r10
            goto Lba
        Lb7:
            r7 = r6
            r6 = r8
            r8 = r10
        Lba:
            int r5 = r5 + r3
            goto L71
        Lbd:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewGroupKt$descendants$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
