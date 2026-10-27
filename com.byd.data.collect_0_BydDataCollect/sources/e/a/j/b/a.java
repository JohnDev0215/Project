package e.a.j.b;

import android.os.Handler;
import android.os.Looper;
import e.a.h;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final h f4850a = e.a.j.a.a.d(new CallableC0091a());

    /* JADX INFO: renamed from: e.a.j.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0091a implements Callable<h> {
        CallableC0091a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public h call() {
            return b.f4851a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final h f4851a = new e.a.j.b.b(new Handler(Looper.getMainLooper()));
    }

    public static h a() {
        return e.a.j.a.a.e(f4850a);
    }
}
