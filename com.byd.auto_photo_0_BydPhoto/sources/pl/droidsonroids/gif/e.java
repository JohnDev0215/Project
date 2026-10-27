package pl.droidsonroids.gif;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes2.dex */
final class e extends ScheduledThreadPoolExecutor {

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final e f3195a = new e();
    }

    private e() {
        super(1, new ThreadPoolExecutor.DiscardPolicy());
    }

    static e a() {
        return b.f3195a;
    }
}
