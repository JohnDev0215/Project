package b.b.g.r;

import android.content.Context;
import b.b.g.p.d;
import java.lang.Thread;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AppCrashHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public class d implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b f1792a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public WeakReference<Context> f1793b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Thread.UncaughtExceptionHandler f1794c;

    /* JADX INFO: compiled from: AppCrashHandler.java */
    public interface b {
        void a(Throwable th);
    }

    /* JADX INFO: compiled from: AppCrashHandler.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static volatile d f1795a = new d();

        public static d a() {
            return f1795a;
        }
    }

    public void a(Context context) {
        this.f1794c = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.f1793b = new WeakReference<>(context.getApplicationContext());
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        b.b.m.c.a.d("AppCrashHandler", "---uncaughtException---ex = " + th.toString());
        if (this.f1793b == null) {
            return;
        }
        b.b.m.c.a.a("AppCrashHandler", "---uncaughtException---DO START---");
        b.b.g.p.d dVarA = d.g.a();
        dVarA.W(this.f1793b.get());
        dVarA.H0(this.f1793b.get(), 1);
        b bVar = this.f1792a;
        if (bVar != null) {
            bVar.a(th);
        }
        b.b.m.c.a.a("AppCrashHandler", "---uncaughtException---DO END---");
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f1794c;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        } else {
            th.printStackTrace();
        }
        this.f1793b = null;
        this.f1792a = null;
    }

    public d() {
    }
}
