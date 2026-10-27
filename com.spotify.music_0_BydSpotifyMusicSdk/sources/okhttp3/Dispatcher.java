package okhttp3;

import java.util.ArrayDeque;
import java.util.concurrent.ThreadPoolExecutor;
import okhttp3.internal.connection.RealCall;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Dispatcher {
    public ThreadPoolExecutor c;
    public final int a = 64;
    public final int b = 5;
    public final ArrayDeque d = new ArrayDeque();
    public final ArrayDeque e = new ArrayDeque();
    public final ArrayDeque f = new ArrayDeque();

    public final void a(ArrayDeque arrayDeque, Object obj) {
        synchronized (this) {
            if (!arrayDeque.remove(obj)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        c();
    }

    public final void b(RealCall.AsyncCall asyncCall) {
        v03.j(asyncCall, "call");
        asyncCall.e.decrementAndGet();
        a(this.e, asyncCall);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c() {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Dispatcher.c():void");
    }
}
