package co.touchlab.stately.concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

/* JADX INFO: compiled from: AtomicBoolean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000f\u001a\u00020\u0002¢\u0006\u0004\b\u0010\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002R\u0018\u0010\b\u001a\u00060\u0006j\u0002`\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR$\u0010\n\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lco/touchlab/stately/concurrency/AtomicBoolean;", "", "", "b", "", "boolToInt", "Ljava/util/concurrent/atomic/AtomicInteger;", "Lco/touchlab/stately/concurrency/AtomicInt;", "atom", "Ljava/util/concurrent/atomic/AtomicInteger;", "value", "getValue", "()Z", "setValue", "(Z)V", "value_", "<init>", "stately-concurrency"}, k = 1, mv = {1, 4, 0})
public final class AtomicBoolean {
    private final AtomicInteger atom;

    private final int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    public AtomicBoolean(boolean z) {
        this.atom = new AtomicInteger(boolToInt(z));
    }

    public final boolean getValue() {
        return AtomicIntKt.getValue(this.atom) != 0;
    }
}
