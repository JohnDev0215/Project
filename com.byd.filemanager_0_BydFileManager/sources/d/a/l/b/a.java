package d.a.l.b;

import android.os.Handler;
import android.os.Looper;
import d.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final j f4519a = d.a.l.a.a.d(new CallableC0111a());

    /* JADX INFO: renamed from: d.a.l.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0111a implements Callable<j> {
        CallableC0111a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return b.f4520a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4520a = new d.a.l.b.b(new Handler(Looper.getMainLooper()));
    }

    public static j a() {
        return d.a.l.a.a.e(f4519a);
    }
}
