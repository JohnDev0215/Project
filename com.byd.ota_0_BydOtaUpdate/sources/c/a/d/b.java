package c.a.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import c.a.b.d.e;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;

/* JADX INFO: compiled from: CrashHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2358a = b.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static b f2359b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public a f2360c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f2361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Thread.UncaughtExceptionHandler f2362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public StringBuffer f2363f = new StringBuffer();

    /* JADX INFO: compiled from: CrashHandler.java */
    public interface a {
        void a();
    }

    public static b d() {
        if (f2359b == null) {
            synchronized (b.class) {
                if (f2359b == null) {
                    f2359b = new b();
                }
            }
        }
        return f2359b;
    }

    public final void a(String str, Object obj) {
        this.f2363f.append("" + str + ":" + obj + "\n");
    }

    public final void b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        String string = stringWriter.toString();
        printWriter.close();
        a("", string);
    }

    public final void c(Context context) {
        this.f2363f.setLength(0);
        this.f2363f.append("\n--------------------------------\n");
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
            if (packageInfo != null) {
                a("versionCode", Integer.valueOf(packageInfo.versionCode));
                a("versionName", packageInfo.versionName);
                a("packageName", packageInfo.packageName);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        this.f2363f.append("--------------------------------\n");
    }

    public final boolean e(Throwable th) {
        if (th == null) {
            return false;
        }
        c(this.f2361d);
        b(th);
        e.b(f2358a, "handleException:" + this.f2363f.toString());
        return true;
    }

    public void f(Context context) {
        this.f2361d = context;
        this.f2362e = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void g(a aVar) {
        this.f2360c = aVar;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        this.f2360c.a();
        if (!e(th) && this.f2362e != null) {
            e.b(f2358a, "uncaughtException");
            this.f2362e.uncaughtException(thread, th);
        } else {
            e.b(f2358a, "killProcess");
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }
}
