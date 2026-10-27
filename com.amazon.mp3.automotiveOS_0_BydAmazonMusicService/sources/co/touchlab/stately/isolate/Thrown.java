package co.touchlab.stately.isolate;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IsoState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lco/touchlab/stately/isolate/Thrown;", "Lco/touchlab/stately/isolate/RunResult;", "", "toString", "", "hashCode", "", "other", "", "equals", "", "throwable", "Ljava/lang/Throwable;", "getThrowable", "()Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "stately-isolate"}, k = 1, mv = {1, 4, 0})
public final /* data */ class Thrown extends RunResult {
    private final Throwable throwable;

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Thrown) && Intrinsics.areEqual(this.throwable, ((Thrown) other).throwable);
        }
        return true;
    }

    public int hashCode() {
        Throwable th = this.throwable;
        if (th != null) {
            return th.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Thrown(throwable=" + this.throwable + ")";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Thrown(Throwable throwable) {
        super(null);
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        this.throwable = throwable;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }
}
