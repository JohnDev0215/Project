package kotlinx.coroutines.flow.internal;

import k5.g;
import kotlin.jvm.internal.m;
import kotlinx.coroutines.l1;
import r5.p;

/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
final class SafeCollector_commonKt$checkContext$result$1 extends m implements p<Integer, g.b, Integer> {
    final /* synthetic */ SafeCollector $this_checkContext;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SafeCollector_commonKt$checkContext$result$1(SafeCollector safeCollector) {
        super(2);
        this.$this_checkContext = safeCollector;
    }

    public final int invoke(int i7, g.b bVar) {
        g.c<?> key = bVar.getKey();
        g.b bVar2 = this.$this_checkContext.collectContext.get(key);
        if (key != l1.f7703d) {
            if (bVar != bVar2) {
                return Integer.MIN_VALUE;
            }
            return i7 + 1;
        }
        l1 l1Var = (l1) bVar2;
        l1 l1VarTransitiveCoroutineParent = SafeCollector_commonKt.transitiveCoroutineParent((l1) bVar, l1Var);
        if (l1VarTransitiveCoroutineParent == l1Var) {
            return l1Var == null ? i7 : i7 + 1;
        }
        throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + l1VarTransitiveCoroutineParent + ", expected child of " + l1Var + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'").toString());
    }

    @Override // r5.p
    public /* bridge */ /* synthetic */ Integer invoke(Integer num, g.b bVar) {
        return Integer.valueOf(invoke(num.intValue(), bVar));
    }
}
