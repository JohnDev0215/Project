package e.a.l.b;

import android.os.Handler;
import android.os.Looper;
import e.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f4916a = e.a.l.a.a.d(new CallableC0087a());

    /* JADX INFO: renamed from: e.a.l.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0087a implements Callable<j> {
        CallableC0087a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return b.f4917a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4917a = new e.a.l.b.b(new Handler(Looper.getMainLooper()), false);
    }

    public static j a() {
        return e.a.l.a.a.e(f4916a);
    }
}
