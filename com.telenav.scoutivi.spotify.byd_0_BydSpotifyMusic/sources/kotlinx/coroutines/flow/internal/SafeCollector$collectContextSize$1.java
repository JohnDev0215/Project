package kotlinx.coroutines.flow.internal;

import k5.g;
import kotlin.jvm.internal.m;
import r5.p;

/* JADX INFO: compiled from: SafeCollector.kt */
/* JADX INFO: loaded from: classes.dex */
final class SafeCollector$collectContextSize$1 extends m implements p<Integer, g.b, Integer> {
    public static final SafeCollector$collectContextSize$1 INSTANCE = new SafeCollector$collectContextSize$1();

    SafeCollector$collectContextSize$1() {
        super(2);
    }

    public final int invoke(int i7, g.b bVar) {
        return i7 + 1;
    }

    @Override // r5.p
    public /* bridge */ /* synthetic */ Integer invoke(Integer num, g.b bVar) {
        return Integer.valueOf(invoke(num.intValue(), bVar));
    }
}
