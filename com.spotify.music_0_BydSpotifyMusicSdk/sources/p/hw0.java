package p;

import com.spotify.eventsender.internalerrors.EventSenderInternalError;

/* JADX INFO: loaded from: classes.dex */
public final class hw0 implements io.reactivex.rxjava3.functions.p {
    public final /* synthetic */ jw0 c;

    public hw0(jw0 jw0Var) {
        this.c = jw0Var;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        Throwable th = (Throwable) obj;
        jw0 jw0Var = this.c;
        ((oj0) jw0Var.k).g(EventSenderInternalError.EVENTS_QUEUE_RESTARTED);
        ((u11) jw0Var.e).y("EventSender queue restarted after an error: " + th.getMessage(), th);
        return true;
    }
}
