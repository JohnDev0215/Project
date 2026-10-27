package T;

import java.util.concurrent.CancellationException;
import y0.AbstractC2737o;

/* JADX INFO: loaded from: classes.dex */
public final class Q extends CancellationException {
    public final /* synthetic */ int l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Q(String str, int i7) {
        super(str);
        this.l = i7;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        switch (this.l) {
            case 0:
                setStackTrace(new StackTraceElement[0]);
                break;
            case 1:
                setStackTrace(g0.a.c);
                break;
            case 2:
                setStackTrace(new StackTraceElement[0]);
                break;
            case 3:
                setStackTrace(new StackTraceElement[0]);
                break;
            default:
                setStackTrace(AbstractC2737o.c);
                break;
        }
        return this;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Q() {
        super("The coroutine scope left the composition");
        this.l = 0;
    }
}
