package c.a.i.b;

import android.os.Handler;
import android.os.Looper;
import c.a.h;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final h f1801a = c.a.i.a.a.d(new CallableC0056a());

    /* JADX INFO: renamed from: c.a.i.b.a$a, reason: collision with other inner class name */
    static class CallableC0056a implements Callable<h> {
        CallableC0056a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public h call() {
            return b.f1802a;
        }
    }

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final h f1802a = new c.a.i.b.b(new Handler(Looper.getMainLooper()));
    }

    public static h a() {
        return c.a.i.a.a.e(f1801a);
    }
}
