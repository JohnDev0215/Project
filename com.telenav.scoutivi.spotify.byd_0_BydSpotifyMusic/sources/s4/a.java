package s4;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Callable;
import q4.d;

/* JADX INFO: compiled from: AndroidSchedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final d f9510a = r4.a.d(new CallableC0148a());

    /* JADX INFO: renamed from: s4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidSchedulers.java */
    static class CallableC0148a implements Callable<d> {
        CallableC0148a() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d call() {
            return b.f9511a;
        }
    }

    /* JADX INFO: compiled from: AndroidSchedulers.java */
    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final d f9511a = new s4.b(new Handler(Looper.getMainLooper()), false);
    }

    public static d a() {
        return r4.a.e(f9510a);
    }
}
