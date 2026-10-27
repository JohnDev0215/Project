package U8;

import S8.InterfaceC0710g;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public abstract class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final t f9251a = new t(-1, null, null, 0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f9252b = X8.a.l("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12);
    public static final int c = X8.a.l("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12);
    public static final B2.q d = new B2.q("BUFFERED", 3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final B2.q f9253e = new B2.q("SHOULD_BUFFER", 3);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final B2.q f9254f = new B2.q("S_RESUMING_BY_RCV", 3);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final B2.q f9255g = new B2.q("RESUMING_BY_EB", 3);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final B2.q f9256h = new B2.q("POISONED", 3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final B2.q f9257i = new B2.q("DONE_RCV", 3);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final B2.q f9258j = new B2.q("INTERRUPTED_SEND", 3);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final B2.q f9259k = new B2.q("INTERRUPTED_RCV", 3);
    public static final B2.q l = new B2.q("CHANNEL_CLOSED", 3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final B2.q f9260m = new B2.q("SUSPEND", 3);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final B2.q f9261n = new B2.q("SUSPEND_NO_WAITER", 3);
    public static final B2.q o = new B2.q("FAILED", 3);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final B2.q f9262p = new B2.q("NO_RECEIVE_RESULT", 3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final B2.q f9263q = new B2.q("CLOSE_HANDLER_CLOSED", 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final B2.q f9264r = new B2.q("CLOSE_HANDLER_INVOKED", 3);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final B2.q f9265s = new B2.q("NO_CLOSE_CAUSE", 3);

    public static final boolean a(InterfaceC0710g interfaceC0710g, Object obj, InterfaceC2786o interfaceC2786o) {
        B2.q qVarI = interfaceC0710g.i(obj, interfaceC2786o);
        if (qVarI == null) {
            return false;
        }
        interfaceC0710g.r(qVarI);
        return true;
    }
}
