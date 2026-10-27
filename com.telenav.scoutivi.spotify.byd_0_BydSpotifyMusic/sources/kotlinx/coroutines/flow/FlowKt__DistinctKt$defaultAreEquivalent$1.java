package kotlinx.coroutines.flow;

import kotlin.jvm.internal.l;
import kotlin.jvm.internal.m;
import r5.p;

/* JADX INFO: compiled from: Distinct.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowKt__DistinctKt$defaultAreEquivalent$1 extends m implements p<Object, Object, Boolean> {
    public static final FlowKt__DistinctKt$defaultAreEquivalent$1 INSTANCE = new FlowKt__DistinctKt$defaultAreEquivalent$1();

    FlowKt__DistinctKt$defaultAreEquivalent$1() {
        super(2);
    }

    @Override // r5.p
    public /* bridge */ /* synthetic */ Boolean invoke(Object obj, Object obj2) {
        return Boolean.valueOf(invoke2(obj, obj2));
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final boolean invoke2(Object obj, Object obj2) {
        return l.a(obj, obj2);
    }
}
