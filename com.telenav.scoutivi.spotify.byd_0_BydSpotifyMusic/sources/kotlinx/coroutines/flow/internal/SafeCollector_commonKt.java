package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import k5.g;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.y;
import kotlinx.coroutines.l1;
import r5.p;

/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SafeCollector_commonKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.SafeCollector_commonKt$unsafeFlow$1, reason: invalid class name */
    /* JADX INFO: compiled from: SafeCollector.common.kt */
    public static final class AnonymousClass1<T> implements Flow<T> {
        final /* synthetic */ p $block;

        public AnonymousClass1(p pVar) {
            this.$block = pVar;
        }

        @Override // kotlinx.coroutines.flow.Flow
        public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
            Object objInvoke = this.$block.invoke(flowCollector, dVar);
            return objInvoke == l5.d.d() ? objInvoke : r.f6563a;
        }

        public Object collect$$forInline(FlowCollector flowCollector, final d dVar) {
            k.a(4);
            new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.internal.SafeCollector_commonKt$unsafeFlow$1$collect$1
                int label;
                /* synthetic */ Object result;

                @Override // kotlin.coroutines.jvm.internal.a
                public final Object invokeSuspend(Object obj) {
                    this.result = obj;
                    this.label |= Integer.MIN_VALUE;
                    return this.this$0.collect(null, this);
                }
            };
            k.a(5);
            this.$block.invoke(flowCollector, dVar);
            return r.f6563a;
        }
    }

    public static final void checkContext(SafeCollector<?> safeCollector, g gVar) {
        if (((Number) gVar.fold(0, new SafeCollector_commonKt$checkContext$result$1(safeCollector))).intValue() == safeCollector.collectContextSize) {
            return;
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + safeCollector.collectContext + ",\n\t\tbut emission happened in " + gVar + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
    }

    public static final l1 transitiveCoroutineParent(l1 l1Var, l1 l1Var2) {
        while (l1Var != null) {
            if (l1Var == l1Var2 || !(l1Var instanceof y)) {
                return l1Var;
            }
            l1Var = ((y) l1Var).getParent$kotlinx_coroutines_core();
        }
        return null;
    }

    public static final <T> Flow<T> unsafeFlow(p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        return new AnonymousClass1(pVar);
    }
}
