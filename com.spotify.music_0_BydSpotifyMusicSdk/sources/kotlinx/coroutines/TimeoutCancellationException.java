package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import p.p61;

/* JADX INFO: loaded from: classes.dex */
public final class TimeoutCancellationException extends CancellationException {
    public final transient p61 c;

    public TimeoutCancellationException(String str, p61 p61Var) {
        super(str);
        this.c = p61Var;
    }
}
