package OooO00o.OooO00o.OooO0o0.OooO0o;

import com.android.volley.BuildConfig;
import java.lang.Thread;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Thread.UncaughtExceptionHandler f495a = Thread.getDefaultUncaughtExceptionHandler();

    public interface a {
        void a(String str);
    }

    public static Thread.UncaughtExceptionHandler a(final a aVar) {
        return new Thread.UncaughtExceptionHandler() { // from class: OooO00o.OooO00o.OooO0o0.OooO0o.a
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                c.b(aVar, thread, th);
            }
        };
    }

    public static /* synthetic */ void b(a aVar, Thread thread, Throwable th) {
        if (aVar != null) {
            aVar.a(th + BuildConfig.FLAVOR);
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = f495a;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    public static void c(a aVar) {
        Thread.setDefaultUncaughtExceptionHandler(a(aVar));
    }
}
