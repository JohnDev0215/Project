package co.touchlab.stately.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AtomicInt.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\",\u0010\u0003\u001a\u00020\u0002*\u00060\u0000j\u0002`\u00012\u0006\u0010\u0003\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Ljava/util/concurrent/atomic/AtomicInteger;", "Lco/touchlab/stately/concurrency/AtomicInt;", "", "value", "getValue", "(Ljava/util/concurrent/atomic/AtomicInteger;)I", "setValue", "(Ljava/util/concurrent/atomic/AtomicInteger;I)V", "stately-concurrency"}, k = 2, mv = {1, 4, 0})
public final class AtomicIntKt {
    public static final int getValue(AtomicInteger value) {
        Intrinsics.checkNotNullParameter(value, "$this$value");
        return value.get();
    }
}
