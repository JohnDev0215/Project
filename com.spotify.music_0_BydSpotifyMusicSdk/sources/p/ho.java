package p;

import com.spotify.connectivity.httpwebgate.WebgateAuthorizer;

/* JADX INFO: loaded from: classes.dex */
public abstract class ho {
    public static final er a = new er(-1, null, null, 0);
    public static final int b = v03.O("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12);
    public static final int c = v03.O("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", WebgateAuthorizer.COSMOS_TIMEOUT_MS, 0, 0, 12);
    public static final tz2 d = new tz2("BUFFERED", 3);
    public static final tz2 e = new tz2("SHOULD_BUFFER", 3);
    public static final tz2 f = new tz2("S_RESUMING_BY_RCV", 3);
    public static final tz2 g = new tz2("RESUMING_BY_EB", 3);
    public static final tz2 h = new tz2("POISONED", 3);
    public static final tz2 i = new tz2("DONE_RCV", 3);
    public static final tz2 j = new tz2("INTERRUPTED_SEND", 3);
    public static final tz2 k = new tz2("INTERRUPTED_RCV", 3);
    public static final tz2 l = new tz2("CHANNEL_CLOSED", 3);
    public static final tz2 m = new tz2("SUSPEND", 3);
    public static final tz2 n = new tz2("SUSPEND_NO_WAITER", 3);
    public static final tz2 o = new tz2("FAILED", 3);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final tz2 f129p = new tz2("NO_RECEIVE_RESULT", 3);
    public static final tz2 q = new tz2("CLOSE_HANDLER_CLOSED", 3);
    public static final tz2 r = new tz2("CLOSE_HANDLER_INVOKED", 3);
    public static final tz2 s = new tz2("NO_CLOSE_CAUSE", 3);

    public static final boolean a(yp ypVar, Object obj, ry0 ry0Var) {
        zp zpVar = (zp) ypVar;
        if (zpVar.D(obj, ry0Var) == null) {
            return false;
        }
        zpVar.o(zpVar.k);
        return true;
    }
}
