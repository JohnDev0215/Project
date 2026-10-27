package a.h.d.b;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Typeface f715a;

        public a(Typeface typeface) {
            this.f715a = typeface;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.e(this.f715a);
        }
    }

    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f717a;

        public b(int i) {
            this.f717a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.d(this.f717a);
        }
    }

    public static Handler c(Handler handler) {
        return handler == null ? new Handler(Looper.getMainLooper()) : handler;
    }

    public final void a(int i, Handler handler) {
        c(handler).post(new b(i));
    }

    public final void b(Typeface typeface, Handler handler) {
        c(handler).post(new a(typeface));
    }

    public abstract void d(int i);

    public abstract void e(Typeface typeface);
}
