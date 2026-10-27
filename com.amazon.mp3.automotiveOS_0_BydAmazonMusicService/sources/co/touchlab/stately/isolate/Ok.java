package co.touchlab.stately.isolate;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IsoState.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0080\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u0004\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007HÖ\u0003R\u0017\u0010\u000b\u001a\u00028\u00008\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lco/touchlab/stately/isolate/Ok;", "T", "Lco/touchlab/stately/isolate/RunResult;", "", "toString", "", "hashCode", "", "other", "", "equals", "result", "Ljava/lang/Object;", "getResult", "()Ljava/lang/Object;", "<init>", "(Ljava/lang/Object;)V", "stately-isolate"}, k = 1, mv = {1, 4, 0})
public final /* data */ class Ok<T> extends RunResult {
    private final T result;

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof Ok) && Intrinsics.areEqual(this.result, ((Ok) other).result);
        }
        return true;
    }

    public int hashCode() {
        T t = this.result;
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Ok(result=" + this.result + ")";
    }

    public Ok(T t) {
        super(null);
        this.result = t;
    }

    public final T getResult() {
        return this.result;
    }
}
