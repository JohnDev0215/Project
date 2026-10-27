package p;

import com.spotify.eventsender.droppedevents.EventState;
import com.spotify.eventsender.internalerrors.EventSenderInternalError;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class iw0 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ jw0 e;

    public /* synthetic */ iw0(jw0 jw0Var, int i) {
        this.c = i;
        this.e = jw0Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        jw0 jw0Var = this.e;
        switch (i) {
            case 0:
                Pair pair = (Pair) obj;
                String str = (String) pair.a();
                Throwable th = (Throwable) pair.b();
                if (th != null) {
                    ((wb0) jw0Var.c).g(str, EventState.DROPPED);
                    ((u11) jw0Var.e).y("Event " + str + " persisting failed: " + th.getMessage(), th);
                } else {
                    ((wb0) jw0Var.c).g(str, EventState.PERSISTED);
                    ((u11) jw0Var.e).getClass();
                }
                break;
            default:
                Throwable th2 = (Throwable) obj;
                ((oj0) jw0Var.k).g(EventSenderInternalError.EVENTS_QUEUE_PERMANENTLY_STOPPED);
                ((u11) jw0Var.e).y("EventSender queue permanently stopped: " + th2.getMessage(), th2);
                break;
        }
    }
}
