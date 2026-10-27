package c.b.d.b;

import android.os.Handler;
import android.os.Looper;
import c.b.c;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c f2208a;

    /* JADX INFO: renamed from: c.b.d.b.a$a, reason: collision with other inner class name */
    public static final class C0048a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f2209a = new b(new Handler(Looper.getMainLooper()), false);
    }

    static {
        try {
            c cVar = C0048a.f2209a;
            if (cVar == null) {
                throw new NullPointerException("Scheduler Callable returned null");
            }
            f2208a = cVar;
        } catch (Throwable th) {
            throw c.b.h.f.b.a(th);
        }
    }
}
