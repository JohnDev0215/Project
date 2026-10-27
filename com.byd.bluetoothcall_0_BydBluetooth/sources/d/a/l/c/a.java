package d.a.l.c;

import android.os.Handler;
import android.os.Looper;
import d.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f4227a = d.a.l.b.a.d(new CallableC0107a());

    /* JADX INFO: renamed from: d.a.l.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0107a implements Callable<j> {
        CallableC0107a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return b.f4228a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4228a = new d.a.l.c.b(new Handler(Looper.getMainLooper()));
    }

    public static j a() {
        return d.a.l.b.a.e(f4227a);
    }
}
